package com.example.superhero.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.superhero.R
import com.example.superhero.imagemanager.bindCircularImageUrl
import kotlinx.android.synthetic.main.fragment_detail_hero.*


class DetailHeroFragment : Fragment() {

    private val args: DetailHeroFragmentArgs? by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_hero, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args?.hero?.let {
            val name = "Nombre: ${it.name}"
            tv_name.text = name
            iv_photo.bindCircularImageUrl(
                    it.image.url,R.drawable.ic_camera_alt_black,R.drawable.ic_broken_image_black
            )
            //PowerStats
            tv_intelligence.text= "Inteligencia: ${it.powerStats.intelligence}"
            tv_strength.text ="Fuerza: ${it.powerStats.strength}"
            tv_speed.text ="Velocidad: ${it.powerStats.speed}"
            tv_durability.text ="Resistencia: ${it.powerStats.durability}"
            tv_power.text = "Poder: ${it.powerStats.power}"
            tv_combat.text = "Combate: ${it.powerStats.combat}"
            //Biography
            tv_fullName.text = "Nombre completo: ${it.biography.fullName?:"Nombre no disponible"}"
            tv_alterEgos.text = "Alter egos: ${it.biography.alterEgos}"
            tv_place_of_birth.text = "Lugar de nacimiento: ${it.biography.placeOfBirth}"
            tv_first_appearence.text = "Primera aparición: ${it.biography.firstAppearance}"
            tv_publisher.text= "Editora: ${it.biography.publisher}"
            tv_aligment.text = "Alineación: ${it.biography.aligment}"

            var alias: String
            alias = "Alias:\n"
            it.biography.aliases.map {
                alias= alias + it +"\n"
            }
            tv_aliases.text = alias
            //Appearence
            tv_gender.text = "Género: ${it.appearance?.gender}"
            tv_race.text = "Raza: ${it.appearance?.race}"

            var heigth: String="Estatura: "
            it.appearance?.height?.map {
                heigth += "$it "
            }
            tv_height.text = heigth

            var weight: String="Peso: "
            it.appearance?.weight?.map {
                weight += "$it "
            }
            tv_weight.text = weight

            tv_eye_color.text= "Color de ojos: ${it.appearance?.eyeColor}"
            tv_hair_color.text = "Color de cabello: ${it.appearance?.hairColor}"
            //Work
            tv_occupation.text = "Ocupación: ${it.work.occupation}"
            tv_base.text = "Base: ${it.work.base}"
            //Connections
            tv_group_affiliation.text ="Grupo afiliado: ${it.connections.groupAffiliation.replace(";","\n")}"
            tv_relatives.text = "Parientes: ${it.connections.relatives.replace(";","\n")}"

        }
    }

}