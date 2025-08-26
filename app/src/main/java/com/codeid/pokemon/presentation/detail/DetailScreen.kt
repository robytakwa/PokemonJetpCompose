package com.codeid.pokemon.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codeid.pokemon.presentation.common.ViewModelFactory

@Composable
fun DetailScreen(
    pokemonName: String,
    viewModel: DetailViewModel = viewModel(factory = ViewModelFactory.inject(LocalContext.current))
) {
    var name by remember { mutableStateOf("") }
    var abilities by remember { mutableStateOf(emptyList<String>()) }

    LaunchedEffect(pokemonName) {
        try {
            val pokemon = viewModel.getDetail(pokemonName)
            name = pokemon.name
            abilities = pokemon.abilities.map { it.name }
        } catch (e: Exception) {
            name = "Failed to load detail"
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = name.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "Abilities:\n${abilities.joinToString("\n")}",
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
