package com.example.superhero.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import com.example.superhero.R
import com.example.superhero.domain.Hero

class MainActivity : AppCompatActivity(),HeroListFragment.OnHeroListFragmentListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun openHeroDetail(hero: Hero) {

       /* findNavController(this.parent.findView(R.layout.fragment_hero_list)).navigate(
                HeroListFragmentDirections.toDetailHeroFragment()
        )*/
    }

}