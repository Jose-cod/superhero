package com.example.superhero.requestmanager

import com.example.superhero.requestmanager.APIConstants.ENDPOINT_HEROES
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroService {

    @GET(ENDPOINT_HEROES)
    fun getAllHeroes(
        @Query("page") page: Int
    ): Single<HeroResponseServer>
}