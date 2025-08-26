package com.codeid.pokemon.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeid.pokemon.domain.model.Pokemon
import com.codeid.pokemon.domain.usecase.GetPokemonListUseCase
import com.codeid.pokemon.domain.usecase.SavePokemonListUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val savePokemonListUseCase: SavePokemonListUseCase
) : ViewModel() {

    suspend fun getPokemons(offset: Int = 0): List<Pokemon> {
        val pokemons = getPokemonListUseCase(limit = 10, offset = offset)
        viewModelScope.launch { savePokemonListUseCase(pokemons) }
        return pokemons
    }

    fun searchPokemon(query: String, pokemons: List<Pokemon>): List<Pokemon> {
        return if (query.isEmpty()) pokemons
        else pokemons.filter { it.name.contains(query, ignoreCase = true) }
    }



    fun searchPokemonByName(pokemons: List<Pokemon>, query: String): List<Pokemon> {
        return pokemons.filter { it.name.contains(query, ignoreCase = true) }
    }
}