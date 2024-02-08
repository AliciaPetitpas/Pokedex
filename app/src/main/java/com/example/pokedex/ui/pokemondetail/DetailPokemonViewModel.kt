package com.example.pokedex.ui.pokemondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.pokedex.bo.Pokemon
import com.example.pokedex.dao.PokemonDao
import com.example.pokedex.db.PokemonDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailPokemonViewModel(
    private val pokemonDao: PokemonDao
): ViewModel() {

    fun iniCurrentPokemon(pokemon: Pokemon) {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonDao.selectById(pokemon.id)
        }
    }

    companion object {
        val Factory : ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val context = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])

                return DetailPokemonViewModel(
                    PokemonDatabase.getInstance(context).getPokemonDao()
                ) as T
            }
        }
    }
}