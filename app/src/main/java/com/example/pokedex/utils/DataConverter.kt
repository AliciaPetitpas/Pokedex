package com.example.pokedex.utils

import com.example.pokedex.bo.Stats
import com.example.pokedex.bo.Types
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DataConverter {
    fun toStats(stats: String): Stats {
        val type = object: TypeToken<Stats>(){}.type
        return Gson().fromJson(stats, type)
    }

    fun toStatsJson(stats: Stats): String {
        val type = object: TypeToken<Stats>(){}.type
        return Gson().toJson(stats, type)
    }

    fun toTypes(types: String): Array<Types> {
        val type = object: TypeToken<Array<Types>>(){}.type
        return Gson().fromJson(types, type)
    }

    fun toTypesJson(types: Array<Types>): String {
        val type = object: TypeToken<Array<Types>>(){}.type
        return Gson().toJson(types, type)
    }
}