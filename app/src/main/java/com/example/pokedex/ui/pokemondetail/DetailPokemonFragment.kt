package com.example.pokedex.ui.pokemondetail

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.enishop.R
import com.example.enishop.databinding.FragmentDetailPokemonBinding
import com.example.pokedex.utils.DataConverter
import com.example.pokedex.utils.JsonConverters
import com.squareup.picasso.Picasso

private const val TAG = "DetailPokemonFragment"
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

        // Affiche les images du Pokémon
        Picasso.get().load(currentPokemon.image).into(binding.ivPkmn)
        Picasso.get().load(currentPokemon.sprite).into(binding.ivSprite)

        val stats = currentPokemon.stats
        val types = currentPokemon.types

        // Affiche le.s type.s du Pokémon
        if (types.size == 1) {
            view.findViewById<TextView>(R.id.tvType).text = "${currentPokemon.name} est de type ${currentPokemon.types[0].name}"
        }
        if (types.size == 2) {
            view.findViewById<TextView>(R.id.tvType).text = "${currentPokemon.name} est de type ${currentPokemon.types[0].name} et ${currentPokemon.types[1].name}"
        }

        // Affiche les images des types
        for (n in types.indices) {
            val imgType1 = view.findViewById<ImageView>(R.id.ivType1)
            val imgType2 = view.findViewById<ImageView>(R.id.ivType2)

            if (n == 0) {
                Picasso.get()
                    .load(types[n].image)
                    .into(imgType1)
            }
            if (n == 1) {
                Picasso.get()
                    .load(types[n].image)
                    .into(imgType2)
            }
        }

        // Affiche les statistiques
        view.findViewById<TextView>(R.id.tvAttack).text = stats.attack.toString()
        view.findViewById<TextView>(R.id.tvDefense).text = stats.defense.toString()
        view.findViewById<TextView>(R.id.tvHp).text = stats.hp.toString()
        view.findViewById<TextView>(R.id.tvSpeAtt).text = stats.speAttack.toString()
        view.findViewById<TextView>(R.id.tvSpeDef).text = stats.speDefense.toString()
        view.findViewById<TextView>(R.id.tvSpeed).text = stats.speed.toString()

        // Rédirige vers au page google au clique de l'image
        binding.ivPkmn.setOnClickListener {
            val pokemon = currentPokemon.name

            Intent(Intent.ACTION_WEB_SEARCH).also {
                it.putExtra(SearchManager.QUERY, pokemon)
                startActivity(it)
            }
        }

        binding.lifecycleOwner = this

        vm.iniCurrentPokemon(currentPokemon)
    }
}