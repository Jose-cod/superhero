package com.example.superhero.requestmanager

import com.example.superhero.data.RemoteHeroDataSource
import com.example.superhero.domain.Hero
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HeroRetrofitDataSource(
    private val heroRequest: HeroRequest
): RemoteHeroDataSource {
    override fun getAllHeroes(page: Int): Single<List<Hero>> {
        return heroRequest
            .getService<HeroService>()
            .getAllHeroes(page)
            .map(HeroResponseServer::toHeroDomainList)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}