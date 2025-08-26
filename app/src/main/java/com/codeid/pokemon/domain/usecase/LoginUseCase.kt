package com.codeid.pokemon.domain.usecase

import com.codeid.pokemon.domain.model.User
import com.codeid.pokemon.domain.repository.PokemonRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(email: String, password: String): User? {
        return repository.loginUser(email, password)
    }
}