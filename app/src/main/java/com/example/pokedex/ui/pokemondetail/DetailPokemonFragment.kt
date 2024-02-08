package com.example.pokedex.ui.pokemondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.enishop.databinding.FragmentDetailPokemonBinding

class DetailPokemonFragment: Fragment() {
    lateinit var binding: FragmentDetailPokemonBinding
    val args: DetailPokemonFragmentArgs by navArgs()
    val vm: DetailPokemonViewModel by viewModels{ DetailPokemonViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailPokemonBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentPokemon = args.pokemon

        binding.pokemon = currentPokemon
        binding.vm = vm

        binding.lifecycleOwner = this

        vm.iniCurrentPokemon(currentPokemon)
    }
}