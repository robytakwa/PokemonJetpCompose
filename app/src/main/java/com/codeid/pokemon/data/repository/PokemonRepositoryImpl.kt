package com.codeid.pokemon.data.repository

import com.codeid.pokemon.data.local.AppDatabase
import com.codeid.pokemon.data.local.UserEntity
import com.codeid.pokemon.data.remote.PokemonRemoteDataSource
import com.codeid.pokemon.domain.model.Pokemon
import com.codeid.pokemon.domain.model.User
import com.codeid.pokemon.domain.repository.PokemonRepository

class PokemonRepositoryImpl(
    private val remoteDataSource: PokemonRemoteDataSource,
    private val database: AppDatabase
) : PokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): List<Pokemon> {
        return remoteDataSource.fetchPokemonList(limit, offset)
    }

    override suspend fun getPokemonDetail(name: String): Pokemon {
        return remoteDataSource.fetchPokemonDetail(name)
    }

    override suspend fun searchPokemon(name: String): Pokemon {
        return remoteDataSource.fetchPokemonDetail(name)
    }

    override suspend fun savePokemonList(pokemons: List<Pokemon>) {
    }

    override suspend fun getCachedPokemonList(): List<Pokemon> {
        return emptyList()
    }

    override suspend fun registerUser(user: User) {
        val entity = UserEntity( username = user.username,email = user.email,  password = user.password)
        database.userDao().insert(user = entity)
    }

    override suspend fun loginUser(email: String, password: String): User? {
        val entity = database.userDao().login(email, password)
        return entity?.let { User(it.username,it.email, it.password) }
    }

    override suspend fun getUserByEmail(email: String): User? {
        val entity = database.userDao().getUserByEmail(email)
        return entity?.let { User(it.username, it.email, it.password) }
    }
}
