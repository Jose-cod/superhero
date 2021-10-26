package com.example.superhero.requestmanager

import com.example.superhero.requestmanager.APIConstants.ENDPOINT_HERO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HeroService {

    @GET(ENDPOINT_HERO)
    fun getAllHeroes(
        @Path("id") id: Int
    ): Single<HeroServer>
}