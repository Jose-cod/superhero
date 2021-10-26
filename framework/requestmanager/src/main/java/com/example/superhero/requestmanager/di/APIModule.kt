package com.example.superhero.requestmanager.di

import com.example.superhero.data.RemoteHeroDataSource
import com.example.superhero.requestmanager.APIConstants.BASE_API_URL
import com.example.superhero.requestmanager.HeroRequest
import com.example.superhero.requestmanager.HeroRetrofitDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class APIModule {

    @Provides
    fun provideHeroRequest(
        @Named("baseUrl") baseUrl: String
    ) = HeroRequest(baseUrl)

    @Provides
    @Singleton
    @Named("baseUrl")
    fun baseUrlProvider(): String = BASE_API_URL

    @Provides
    fun provideRemoteHeroDataSource(
        heroRequest: HeroRequest
    ): RemoteHeroDataSource = HeroRetrofitDataSource(heroRequest)
}