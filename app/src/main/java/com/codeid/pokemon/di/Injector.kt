package com.codeid.pokemon.di

import android.content.Context
import com.codeid.pokemon.data.remote.PokemonRemoteDataSource
import com.codeid.pokemon.data.repository.PokemonRepositoryImpl
import com.codeid.pokemon.domain.repository.PokemonRepository
import com.codeid.pokemon.domain.usecase.GetPokemonListUseCase
import com.codeid.pokemon.domain.usecase.LoginUseCase
import com.codeid.pokemon.domain.usecase.RegisterUseCase
import com.codeid.pokemon.domain.usecase.SavePokemonListUseCase
import com.codeid.pokemon.utils.RetrofitBuilder
import com.codeid.pokemon.data.local.AppDatabase
import com.codeid.pokemon.domain.usecase.GetPokemonDetailUseCase

object Injector {

    fun provideRepository(context: Context): PokemonRepository {
        val apiService = RetrofitBuilder.apiService
        val remoteDataSource = PokemonRemoteDataSource(apiService)
        val database = AppDatabase.getInstance(context)
        return PokemonRepositoryImpl(remoteDataSource, database)
    }

    fun provideGetPokemonListUseCase(context: Context): GetPokemonListUseCase =
        GetPokemonListUseCase(provideRepository(context))

    fun provideSavePokemonListUseCase(context: Context): SavePokemonListUseCase =
        SavePokemonListUseCase(provideRepository(context))

    fun provideLoginUseCase(context: Context): LoginUseCase =
        LoginUseCase(provideRepository(context))

    fun provideRegisterUseCase(context: Context): RegisterUseCase =
        RegisterUseCase(provideRepository(context))

    fun provideDetailUseCase(context: Context): GetPokemonDetailUseCase =
        GetPokemonDetailUseCase(provideRepository(context))
}
