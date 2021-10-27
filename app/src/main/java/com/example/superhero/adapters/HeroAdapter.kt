package com.example.superhero.adapters

import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.superhero.domain.Hero
import com.example.superhero.R
import com.example.superhero.databinding.ItemGridHeroBinding
import com.example.superhero.imagemanager.bindImageUrl
import com.example.superhero.ui.HeroListFragmentDirections
import com.example.superhero.utils.bindingInflate
import com.example.superhero.utils.toHeroServer
import kotlinx.android.synthetic.main.item_grid_hero.view.*

class HeroAdapter(): RecyclerView.Adapter<HeroAdapter.HeroViewHolder>() {

    private val heroList: MutableList<Hero> = mutableListOf()

    fun addData(newData: List<Hero>) {
        heroList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HeroViewHolder(
            parent.bindingInflate(R.layout.item_grid_hero, false),
            //listener
        )

    override fun getItemCount() = heroList.size

    override fun getItemId(position: Int): Long = heroList[position].id.toLong()

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(heroList[position])

    }

    class HeroViewHolder(
        private val dataBinding: ItemGridHeroBinding,
    ): RecyclerView.ViewHolder(dataBinding.root) {

        fun bind(item: Hero){
            dataBinding.hero = item

            itemView.hero_image.bindImageUrl(
                url = item.image.url,
                placeholder = R.drawable.ic_camera_alt_black,
                errorPlaceholder = R.drawable.ic_broken_image_black
            )

            itemView.setOnClickListener {


                val navDirection = HeroListFragmentDirections.toDetailHeroFragment(item.toHeroServer())

                findNavController(itemView).navigate(navDirection)
            }
        }

    }
}