package com.example.superhero.requestmanager


import android.os.Parcelable
import com.example.superhero.requestmanager.APIConstants.ALIASES_KEY
import com.example.superhero.requestmanager.APIConstants.ALIGMENT_KEY
import com.example.superhero.requestmanager.APIConstants.ALTER_EGO_KEY
import com.example.superhero.requestmanager.APIConstants.APPEARENCE_KEYS
import com.example.superhero.requestmanager.APIConstants.BASE_KEY
import com.example.superhero.requestmanager.APIConstants.BIOGRAPHY_KEY
import com.example.superhero.requestmanager.APIConstants.COMBAT_KEY
import com.example.superhero.requestmanager.APIConstants.CONNECTION_KEY
import com.example.superhero.requestmanager.APIConstants.DURABILITY_KEY
import com.example.superhero.requestmanager.APIConstants.EYE_COLOR_KEY
import com.example.superhero.requestmanager.APIConstants.FIRST_APPEARENCE_KEY
import com.example.superhero.requestmanager.APIConstants.FULLNAME_KEY
import com.example.superhero.requestmanager.APIConstants.GENDER_KEY
import com.example.superhero.requestmanager.APIConstants.GROUP_AFFILIATION_KEY
import com.example.superhero.requestmanager.APIConstants.HAIR_COLOR_KEY
import com.example.superhero.requestmanager.APIConstants.HEIGHT_KEY
import com.example.superhero.requestmanager.APIConstants.ID_KEY
import com.example.superhero.requestmanager.APIConstants.IMAGE_KEY
import com.example.superhero.requestmanager.APIConstants.INTELLIGENCE_KEY
import com.example.superhero.requestmanager.APIConstants.NAME_KEY
import com.example.superhero.requestmanager.APIConstants.OCCUPATION_KEY
import com.example.superhero.requestmanager.APIConstants.PLACE_BIRTH_KEY
import com.example.superhero.requestmanager.APIConstants.POWER_KEY
import com.example.superhero.requestmanager.APIConstants.POWER_STATS_KEY
import com.example.superhero.requestmanager.APIConstants.PUBLISHER_KEY
import com.example.superhero.requestmanager.APIConstants.RACE_KEY
import com.example.superhero.requestmanager.APIConstants.RELATIVES_KEY
import com.example.superhero.requestmanager.APIConstants.RESPONSE_KEY
import com.example.superhero.requestmanager.APIConstants.SPEED_KEY
import com.example.superhero.requestmanager.APIConstants.STRENGTH_KEY
import com.example.superhero.requestmanager.APIConstants.URL_IMAGE_KEY
import com.example.superhero.requestmanager.APIConstants.WEIGHT_KEY
import com.example.superhero.requestmanager.APIConstants.WORK_KEY
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/*data class HeroResponseServer(
    @SerializedName(RESULTS_KEY) val results: List<HeroServer>
)*/

@Parcelize
data class HeroServer(
    @SerializedName(RESPONSE_KEY) val response: String?,
    @SerializedName(ID_KEY) val id: Int,
    @SerializedName(NAME_KEY) val name: String,
    @SerializedName(POWER_STATS_KEY) val powerStats: PowerStatsServer,
    @SerializedName(BIOGRAPHY_KEY) val biography: BiographyServer,
    @SerializedName(APPEARENCE_KEYS) val appearance: AppearanceServer?,
    @SerializedName(WORK_KEY) val work: WorkServer,
    @SerializedName(CONNECTION_KEY) val connections: ConnectionsServer,
    @SerializedName(IMAGE_KEY) val image: ImageServer
): Parcelable

@Parcelize
data class PowerStatsServer(
    @SerializedName(INTELLIGENCE_KEY) val intelligence: String?,
    @SerializedName(STRENGTH_KEY) val strength: String?,
    @SerializedName(SPEED_KEY) val speed: String?,
    @SerializedName(DURABILITY_KEY) val durability: String?,
    @SerializedName(POWER_KEY) val power: String?,
    @SerializedName(COMBAT_KEY) val combat: String?
): Parcelable

@Parcelize
data class BiographyServer(
    @SerializedName(FULLNAME_KEY) val fullName: String,
    @SerializedName(ALTER_EGO_KEY) val alterEgos: String,
    @SerializedName(ALIASES_KEY) val aliases: List<String>,
    @SerializedName(PLACE_BIRTH_KEY) val placeOfBirth: String,
    @SerializedName(FIRST_APPEARENCE_KEY) val firstAppearance: String?,
    @SerializedName(PUBLISHER_KEY) val publisher: String,
    @SerializedName(ALIGMENT_KEY) val aligment: String?
): Parcelable

@Parcelize
data class AppearanceServer(
    @SerializedName(GENDER_KEY) val gender: String?,
    @SerializedName(RACE_KEY) val race: String?,
    @SerializedName(HEIGHT_KEY) val height: List<String>?,
    @SerializedName(WEIGHT_KEY) val weight: List<String>?,
    @SerializedName(EYE_COLOR_KEY) val eyeColor: String?,
    @SerializedName(HAIR_COLOR_KEY) val hairColor: String?
): Parcelable

@Parcelize
data class WorkServer(
    @SerializedName(OCCUPATION_KEY) val occupation: String,
    @SerializedName(BASE_KEY) val base: String
): Parcelable

@Parcelize
data class ConnectionsServer(
    @SerializedName(GROUP_AFFILIATION_KEY) val groupAffiliation: String,
    @SerializedName(RELATIVES_KEY) val relatives: String
): Parcelable

@Parcelize
data class ImageServer(
    @SerializedName(URL_IMAGE_KEY) val url: String
): Parcelable