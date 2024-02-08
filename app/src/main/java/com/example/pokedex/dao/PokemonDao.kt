package com.example.pokedex.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.pokedex.bo.Pokemon

@Dao
interface PokemonDao {
    @Query("SELECT * FROM Pokemon WHERE id = :id")
    fun selectById(id: Long): Pokemon
}