package com.codeid.pokemon.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codeid.pokemon.domain.model.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val email: String,
    val username: String,
    val password: String
)

