package com.example.superhero.utils

import com.example.superhero.domain.*
import com.example.superhero.requestmanager.*

fun Hero.toHeroServer() = HeroServer(
    response,
    id,
    name,
    powerStats.toPowerStatsServer(),
    biography.toBiographyServer(),
    appearance.toAppearenceServer(),
    work.toWorkServer(),
    connections.toConnectionsServer(),
    image.toImageServer()
)

fun PowerStats.toPowerStatsServer() = PowerStatsServer(
    intelligence,
    strength,
    speed,
    durability,
    power,
    combat
)

fun Biography.toBiographyServer()= BiographyServer(
    fullName,
    alterEgos,
    aliases,
    placeOfBirth,
    firstAppearance?:"",
    publisher,
    aligment?:"",
)

fun Appearance.toAppearenceServer() = AppearanceServer(
    gender,
    race,
    height,
    weight,
    eyeColor,
    hairColor
)

fun Work.toWorkServer() = WorkServer(
    occupation,
    base
)

fun Connections.toConnectionsServer()= ConnectionsServer(
    groupAffiliation,
    relatives
)

fun Image.toImageServer() = ImageServer(
    url
)