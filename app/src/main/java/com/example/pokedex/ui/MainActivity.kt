package com.example.pokedex.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.enishop.R
import com.example.enishop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}