package com.example.pokedex.ui.pokemonlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.enishop.R
import com.example.enishop.databinding.FragmentListPokemonBinding
import com.example.pokedex.PokemonAdapter
import com.example.pokedex.bo.Pokemon

class ListPokemonFragment: Fragment() {
    lateinit var binding: FragmentListPokemonBinding
    val vm: ListPokemonViewModel by viewModels{ ListPokemonViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListPokemonBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.getPokemonList()

        val pokemonList = ArrayList<Pokemon>()

        val recycler = binding.rvPokemons

        vm.pokemons.observe(viewLifecycleOwner) { pokemons ->
            recycler.adapter = PokemonAdapter(pokemons) {pokemon ->
                pokemonList.add(pokemon)

                val direction = ListPokemonFragmentDirections.actionListToDetail(pokemon)
                Navigation.findNavController(view).navigate(direction)
            }
            recycler.layoutManager = LinearLayoutManager(view.context)
        }

        var searchPokemon = view.findViewById<SearchView>(R.id.search_pokemon)

        searchPokemon.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                PokemonAdapter(pokemonList){}.getFilter().filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                PokemonAdapter(pokemonList){}.getFilter().filter(newText)
                return true
            }
        })
    }
}