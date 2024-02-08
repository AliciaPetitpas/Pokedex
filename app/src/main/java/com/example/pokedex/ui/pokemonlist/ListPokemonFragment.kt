package com.example.pokedex.ui.pokemonlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.enishop.databinding.FragmentListPokemonBinding
import com.example.pokedex.PokemonAdapter

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

        val recycler = binding.rvPokemons

        vm.pokemons.observe(viewLifecycleOwner) { pokemons ->
            recycler.adapter = PokemonAdapter(pokemons) {
                val direction = ListPokemonFragmentDirections.actionListToDetail(it)
                Navigation.findNavController(view).navigate(direction)
            }
            recycler.layoutManager = LinearLayoutManager(view.context)
        }
    }
}