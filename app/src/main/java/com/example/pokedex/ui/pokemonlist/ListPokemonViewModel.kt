package com.example.pokedex.ui.pokemonlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.pokedex.bo.Pokemon
import com.example.pokedex.dao.PokemonDao
import com.example.pokedex.dao.PokemonService
import com.example.pokedex.db.PokemonDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListPokemonViewModel(
    private val pokemonDao: PokemonDao
): ViewModel() {
    var pokemons = MutableLiveData<List<Pokemon>>(emptyList())

    fun getPokemonList() {
        viewModelScope.launch(Dispatchers.IO) {
            pokemons.postValue(PokemonService.PokemonApi.retrofitService.getAllPokemons())
        }
    }

    companion object {
        val Factory : ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val context = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])

                return ListPokemonViewModel(
                    PokemonDatabase.getInstance(context).getPokemonDao()
                ) as T
            }
        }
    }
}