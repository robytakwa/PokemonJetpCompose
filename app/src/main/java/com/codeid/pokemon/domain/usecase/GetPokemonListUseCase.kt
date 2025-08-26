package com.codeid.pokemon.domain.usecase

import com.codeid.pokemon.domain.model.Pokemon
import com.codeid.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(limit: Int, offset: Int): List<Pokemon> {
        return repository.getPokemonList(limit, offset)
    }
}