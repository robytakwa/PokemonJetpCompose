package com.codeid.pokemon.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codeid.pokemon.domain.model.Pokemon
import com.codeid.pokemon.presentation.common.ViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    onPokemonClick: (String) -> Unit,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory.inject(LocalContext.current))
) {
    var query by remember { mutableStateOf("") }
    var pokemons by remember { mutableStateOf(listOf<Pokemon>()) }
    var isLoading by remember { mutableStateOf(false) }
    var offset by remember { mutableStateOf(0) }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        isLoading = true
        val initial = viewModel.getPokemons(offset)
        pokemons = pokemons + initial
        offset += initial.size
        isLoading = false
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = {
                    query = it
                    pokemons = if (query.isNotEmpty()) {
                        viewModel.searchPokemon(query, pokemons)
                    } else {
                        pokemons
                    }
                },
                placeholder = { Text(text = "Search Pokémon") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                singleLine = true,
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(pokemons) { pokemon ->
                    PokemonItem(
                        pokemon = pokemon,
                        onClick = { onPokemonClick(pokemon.name) }
                    )
                }

                item {
                    if (!isLoading) {
                        LaunchedEffect(pokemons.size) {
                            coroutineScope.launch {
                                isLoading = true
                                val newPokemons = viewModel.getPokemons(offset)
                                offset += newPokemons.size
                                pokemons = pokemons + newPokemons
                                isLoading = false
                            }
                        }
                    }
                }
            }
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun PokemonItem(
    pokemon: Pokemon,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = pokemon.name.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
