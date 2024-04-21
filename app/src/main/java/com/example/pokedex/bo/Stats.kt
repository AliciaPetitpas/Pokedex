package com.example.pokedex.bo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
open class Stats(
    @PrimaryKey
    @Json(name = "HP")
    val hp: Int = 0,
    val attack: Int = 0,
    val defense: Int = 0,
    @Json(name = "special_attack")
    val speAttack: Int = 0,
    @Json(name = "special_defense")
    val speDefense: Int = 0,
    val speed: Int = 0,
): Parcelable {
}