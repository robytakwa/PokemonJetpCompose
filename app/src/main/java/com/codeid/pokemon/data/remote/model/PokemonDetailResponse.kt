package com.codeid.pokemon.data.remote.model


data class PokemonDetailResponse(
    val name: String,
    val abilities: List<AbilityWrapper>,
    val sprites: Sprites
)

data class AbilityWrapper(
    val ability: AbilityName
)

data class AbilityName(
    val name: String
)

data class Sprites(
    val front_default: String?
)
