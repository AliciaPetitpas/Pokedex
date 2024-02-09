package com.example.pokedex

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.enishop.R
import com.example.enishop.databinding.TemplatePokemonBinding
import com.example.pokedex.bo.Pokemon
import com.squareup.picasso.Picasso
import java.util.Locale
import kotlin.math.log

private const val TAG = "PokemonAdapter"
class PokemonAdapter(
    val pokemons: List<Pokemon>,
    val listener: (pokemon: Pokemon) -> Unit
): Adapter<PokemonAdapter.PokemonVH>() {

    var pokemonsList = ArrayList<Pokemon>()

    init {
        for (n in pokemons.indices) {
            pokemonsList.add(pokemons[n])
        }
    }

    class PokemonVH(val binding: TemplatePokemonBinding) : ViewHolder(binding.root) {}

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
        Picasso.get().load(pokemons[position].image)
            .into(holder.itemView.findViewById<ImageView>(R.id.ivPokemon))
        holder.itemView.setOnClickListener {
            listener.invoke(pokemons[position])
        }
    }

    fun getFilter(): Filter {
        return pokemonFilter
    }

    private val pokemonFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: ArrayList<Pokemon> = ArrayList()
            if (constraint == null || constraint.isEmpty()) {
                pokemonsList.let { filteredList.addAll(it) }
            } else {
                val query = constraint.toString().trim().lowercase()
                pokemonsList.forEach {
                    if (it.name.lowercase(Locale.ROOT).contains(query)) {
                        filteredList.add(it)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results?.values is ArrayList<*>) {
                pokemonsList.clear()
                pokemonsList.addAll(results.values as ArrayList<Pokemon>)
                notifyDataSetChanged()
            }
        }
    }
}

//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val charSearch = constraint.toString()
//
//                Log.i(TAG, "LISTE $pokemonsList")
//
//                if (charSearch.isEmpty()) {
//                    for (n in pokemons.indices) {
//                        pokemonsList.add(pokemons[n].name)
//                    }
//                } else {
//                    val resultList = ArrayList<String>()
//
//                    for (row in pokemonsList) {
//                        if (row.lowercase(Locale.ROOT)
//                                .contains(charSearch.lowercase(Locale.ROOT))
//                        ) {
//                            resultList.add(row)
//                        }
//                    }
//                    pokemonsList = resultList
//                }
//                val filterResults = FilterResults()
//                filterResults.values = pokemonsList
//                return filterResults
//            }
//
//            @Suppress("UNCHECKED_CAST")
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                pokemonsList = results?.values as ArrayList<String>
//                notifyDataSetChanged()
//            }
//        }
//    }