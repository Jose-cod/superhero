package com.example.superhero

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.superhero.di.DaggerSuperHeroAppComponent
import com.example.superhero.di.SuperHeroAppComponent

class SuperHeroApp: Application() {


    lateinit var component: SuperHeroAppComponent
        private set



    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        component = initAppComponent()
    }

    //endregion

    //region Private Methods

    private fun initAppComponent() = DaggerSuperHeroAppComponent.factory().create(this)

    //endregion
}