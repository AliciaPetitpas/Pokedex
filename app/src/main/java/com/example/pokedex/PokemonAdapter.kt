package com.example.pokedex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.enishop.databinding.TemplatePokemonBinding
import com.example.pokedex.bo.Pokemon

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
        holder.itemView.setOnClickListener {
            listener.invoke(pokemons[position])
        }
    }

}