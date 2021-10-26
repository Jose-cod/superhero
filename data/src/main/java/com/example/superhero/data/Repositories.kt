package com.example.superhero.data

import com.example.superhero.domain.Hero
import io.reactivex.Single

class HeroRepository(
    private val remoteHeroDataSource: RemoteHeroDataSource
){
    fun getHero(page: Int): Single<Hero> =
        remoteHeroDataSource.getHero(page)

}