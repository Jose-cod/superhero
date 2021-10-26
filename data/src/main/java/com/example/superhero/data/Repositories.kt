package com.example.superhero.data

import com.example.superhero.domain.Hero
import io.reactivex.Single

class HeroRepository(
    private val remoteHeroDataSource: RemoteHeroDataSource
){
    fun getAllHeroes(page: Int): Single<List<Hero>> =
        remoteHeroDataSource.getAllHeroes(page)

}