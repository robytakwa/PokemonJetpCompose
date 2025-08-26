package com.codeid.pokemon.data.remote

import com.codeid.pokemon.domain.model.Ability
import com.codeid.pokemon.domain.model.Pokemon

class PokemonRemoteDataSource(
    private val api: PokemonApiService
) {

    suspend fun fetchPokemonList(limit: Int, offset: Int): List<Pokemon> {
        val response = api.getPokemonList(limit, offset)
        return response.results.map {
            Pokemon(name = it.name, url = it.url)
        }
    }

    suspend fun fetchPokemonDetail(name: String): Pokemon {
        val detail = api.getPokemonDetail(name)
        val abilities = detail.abilities.map { Ability(it.ability.name) }

        return Pokemon(
            name = detail.name,
            url = detail.sprites.front_default ?: "",
            abilities = abilities
        )
    }

    suspend fun searchPokemon(name: String): Pokemon? {
        return try {
            fetchPokemonDetail(name)
        } catch (e: Exception) {
            null
        }
    }
}
