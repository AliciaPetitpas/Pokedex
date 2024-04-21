package com.example.pokedex.bo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
open class Pokemon(
    @PrimaryKey
    val id: Long = 0,
    val pokedexId: Int = 0,
    val name: String = "",
    val image: String = "",
    val sprite: String = "",
    val stats: Stats,
    @Json(name = "apiTypes")
    val types: Array<Types>,
    @Json(name = "apiGeneration")
    val generation: Int = 0,
) : Parcelable {
}
