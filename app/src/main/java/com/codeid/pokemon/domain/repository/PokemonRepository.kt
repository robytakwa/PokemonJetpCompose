package com.codeid.pokemon.domain.repository

import com.codeid.pokemon.domain.model.Pokemon
import com.codeid.pokemon.domain.model.User


interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): List<Pokemon>
    suspend fun getPokemonDetail(name: String): Pokemon
    suspend fun searchPokemon(name: String): Pokemon
    suspend fun savePokemonList(pokemons: List<Pokemon>)
    suspend fun getCachedPokemonList(): List<Pokemon>
    suspend fun registerUser(user: User)
    suspend fun loginUser(email: String, password: String): User?
    suspend fun getUserByEmail(email: String): User?

}