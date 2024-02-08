package com.example.pokedex.utils

import androidx.room.TypeConverter
import com.example.pokedex.bo.Stats
import com.example.pokedex.bo.Types
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class JsonConverters {
    @TypeConverter
    fun toStats(stats: String): Stats {
        val type = object: TypeToken<Stats>(){}.type
        return Gson().fromJson(stats, type)
    }
    @TypeConverter
    fun toStatsJson(stats: Stats): String {
        val type = object: TypeToken<Stats>(){}.type
        return Gson().toJson(stats, type)
    }

    @TypeConverter
    fun toTypes(types: String): List<Types> {
        val type = object: TypeToken<Stats>(){}.type
        return Gson().fromJson(types, type)
    }
    @TypeConverter
    fun toTypesJson(types: List<Types>): String {
        val type = object: TypeToken<Stats>(){}.type
        return Gson().toJson(types, type)
    }
}