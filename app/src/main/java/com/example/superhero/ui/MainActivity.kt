package com.example.superhero.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.superhero.R
import com.example.superhero.domain.Hero

class MainActivity : AppCompatActivity(),HeroListFragment.OnHeroListFragmentListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun openHeroDetail(hero: Hero) {

    }
}