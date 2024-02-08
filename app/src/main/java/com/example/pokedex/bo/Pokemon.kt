package com.example.pokedex.bo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Pokemon(
    @PrimaryKey
    val id: Long,
    val name: String,
    val image: String,
) : Parcelable {

}
