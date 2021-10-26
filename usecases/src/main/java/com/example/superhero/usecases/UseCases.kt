package com.example.superhero.usecases

import com.example.superhero.data.HeroRepository
import com.example.superhero.domain.Hero
import io.reactivex.Single

class GetAllHeroesUseCase(
    private val heroRepository: HeroRepository
) {

    fun operate(currentPage: Int): Single<Hero> =
        heroRepository.getHero(currentPage)
}