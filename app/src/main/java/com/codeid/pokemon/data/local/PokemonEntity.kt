package com.codeid.pokemon.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codeid.pokemon.domain.model.Pokemon

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey val name: String,
    val url: String
)

fun PokemonEntity.toDomain(): Pokemon = Pokemon(name, url)
fun Pokemon.toEntity(): PokemonEntity = PokemonEntity(name, url)
