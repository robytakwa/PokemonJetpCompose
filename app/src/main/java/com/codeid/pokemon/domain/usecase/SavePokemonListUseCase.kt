package com.codeid.pokemon.domain.usecase

import com.codeid.pokemon.domain.model.Pokemon
import com.codeid.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class SavePokemonListUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(pokemons: List<Pokemon>) {
        repository.savePokemonList(pokemons)
    }
}