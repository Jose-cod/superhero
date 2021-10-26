package com.example.superhero.di

import android.app.Application
import com.example.superhero.data.di.RepositoryModule
import com.example.superhero.requestmanager.di.APIModule
import com.example.superhero.usecases.di.UseCaseModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    APIModule::class,
    RepositoryModule::class,
    UseCaseModule::class
])
interface SuperHeroAppComponent {

    fun inject(module: HeroListModule): HeroListComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): SuperHeroAppComponent
    }
}