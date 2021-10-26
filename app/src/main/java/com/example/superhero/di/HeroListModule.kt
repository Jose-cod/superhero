package com.example.superhero.di

import com.example.superhero.presentation.HeroListViewModel
import com.example.superhero.usecases.GetAllHeroesUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class HeroListModule {

    @Provides
    fun provideHeroListViewModel(
        getAllHeroesUseCase: GetAllHeroesUseCase
    ) = HeroListViewModel(
        getAllHeroesUseCase
    )
}

@Subcomponent(modules = [(HeroListModule::class)])
interface HeroListComponent {
    val heroListViewModel: HeroListViewModel
}