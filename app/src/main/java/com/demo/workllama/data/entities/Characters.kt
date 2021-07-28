package com.demo.workllama.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "charactersTable")
data class Characters(
    val name: String,
    @PrimaryKey
    val id: Int,
    val thumbnail: String,
    val phone: String,
    val email: String,
    val isStarred: Int
)