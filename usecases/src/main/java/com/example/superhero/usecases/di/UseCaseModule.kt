package com.example.superhero.usecases.di

import com.example.superhero.data.HeroRepository
import com.example.superhero.usecases.GetAllHeroesUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideGetAllCharacterUseCase(heroRepository: HeroRepository) =
        GetAllHeroesUseCase(heroRepository)
}