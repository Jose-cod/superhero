package com.example.superhero.requestmanager

import com.example.superhero.domain.*

fun HeroResponseServer.toHeroDomainList(): List<Hero> = results.map {
    it.run {
        Hero(
            response,
            id,
            name,
            powerStats.toPowerStatsDomain(),
            biography.toBiographyDomain(),
            appearance.toAppearenceDomain(),
            work.toWorkDomain(),
            connections.toConnectionsDomain(),
            image.toImageDomain()
        )
    }
}

fun PowerStatsServer.toPowerStatsDomain() = PowerStats(
    intelligence,
    strength,
    speed,
    durability,
    power,
    combat
)

fun BiographyServer.toBiographyDomain()= Biography(
    fullName,
    alterEgos,
    aliases,
    placeOfBirth,
    firstAppearance,
    publisher,
    aligment,
)

fun AppearanceServer.toAppearenceDomain() = Appearance(
    gender,
    race,
    height,
    weight,
    eyeColor,
    hairColor
)

fun WorkServer.toWorkDomain() = Work(
    occupation,
    base
)

fun ConnectionsServer.toConnectionsDomain()= Connections(
    groupAffiliation,
    relatives
)

fun ImageServer.toImageDomain() = Image(
    url
)