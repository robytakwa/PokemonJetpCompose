package com.codeid.pokemon.presentation.detail

import androidx.lifecycle.ViewModel
import com.codeid.pokemon.domain.model.Pokemon
import com.codeid.pokemon.domain.usecase.GetPokemonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class DetailViewModel (
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : ViewModel() {
    suspend fun getDetail(name: String): Pokemon {
        return getPokemonDetailUseCase(name)
    }
}