package com.example.pokedex

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.enishop.R
import com.example.enishop.databinding.TemplatePokemonBinding
import com.example.pokedex.bo.Pokemon
import com.squareup.picasso.Picasso

class PokemonAdapter(
    val pokemons: List<Pokemon>,
    val listener: (pokemon: Pokemon) -> Unit
): Adapter<PokemonAdapter.PokemonVH>() {

    class PokemonVH(val binding: TemplatePokemonBinding): ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonVH {
        val binding = TemplatePokemonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PokemonVH(binding)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: PokemonVH, position: Int) {
        holder.binding.pokemon = pokemons[position]
        // Affiche l'image dans le template
        Picasso.get().load(pokemons[position].image).into(holder.itemView.findViewById<ImageView>(R.id.ivPokemon))
        holder.itemView.setOnClickListener {
            listener.invoke(pokemons[position])
        }
    }

}