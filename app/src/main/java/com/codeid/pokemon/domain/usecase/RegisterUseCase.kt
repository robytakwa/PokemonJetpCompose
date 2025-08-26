package com.codeid.pokemon.domain.usecase

import com.codeid.pokemon.domain.model.User
import com.codeid.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(user: User) {
        repository.registerUser(user)
    }
}