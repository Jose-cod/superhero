package com.example.superhero.data

import com.example.superhero.domain.Hero
import io.reactivex.Single

interface RemoteHeroDataSource {
    fun getHero(page: Int): Single<Hero>
}