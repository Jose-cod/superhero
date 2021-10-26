package com.example.superhero.data.di

import com.example.superhero.data.HeroRepository
import com.example.superhero.data.RemoteHeroDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideHeroRepository(remoteHeroDataSource: RemoteHeroDataSource)=
        HeroRepository(remoteHeroDataSource)
}