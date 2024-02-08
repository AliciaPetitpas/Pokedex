package com.example.pokedex.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pokedex.bo.Pokemon
import com.example.pokedex.dao.PokemonDao

@Database(entities = [Pokemon::class], version = 1)
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun getPokemonDao(): PokemonDao

    companion object {
        private var INSTANCE: PokemonDatabase? = null

        fun getInstance(context: Context): PokemonDatabase {
            var instance = INSTANCE

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    PokemonDatabase::class.java,
                    "Pokedex"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
            }

            return instance
        }
    }
}