package com.example.superhero.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.superhero.R
import com.example.superhero.adapters.HeroAdapter
import com.example.superhero.databinding.FragmentHeroListBinding
import com.example.superhero.di.HeroListComponent
import com.example.superhero.di.HeroListModule
import com.example.superhero.domain.Hero
import com.example.superhero.presentation.Event
import com.example.superhero.presentation.HeroListViewModel
import com.example.superhero.utils.app
import com.example.superhero.utils.getViewModel
import com.example.superhero.utils.showLongToast
import kotlinx.android.synthetic.main.fragment_hero_list.*


class HeroListFragment : Fragment() {

    private lateinit var heroGridAdapter: HeroAdapter
    private lateinit var heroListComponent: HeroListComponent

    private val heroListViewModel: HeroListViewModel by lazy {
        getViewModel { heroListComponent.heroListViewModel }
    }

    private val onScrollListener: RecyclerView.OnScrollListener by lazy {
        object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

                heroListViewModel.onLoadMoreItems(visibleItemCount, firstVisibleItemPosition, totalItemCount)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        heroListComponent = requireContext().app.component.inject(HeroListModule())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentHeroListBinding>(
            inflater,
            R.layout.fragment_hero_list,
            container,
            false
        ).apply {
            lifecycleOwner = this@HeroListFragment
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        heroGridAdapter = HeroAdapter()

        rvHeroList.run{
            addOnScrollListener(onScrollListener)
            adapter = heroGridAdapter
        }

        srwHeroList.setOnRefreshListener {
            heroListViewModel.onRetryGetAllHeroes(rvHeroList.adapter?.itemCount ?: 0)
        }

        heroListViewModel.events.observe(viewLifecycleOwner,
            Observer(this::validateEvents)
        )

        heroListViewModel.onGetAllHeroes()
    }

    private fun validateEvents(event: Event<HeroListViewModel.HeroListNavigation>?) {
        event?.getContentIfNotHandled()?.let { navigation ->
            when(navigation) {
                is HeroListViewModel.HeroListNavigation.ShowHeroError -> navigation.run {
                    context?.showLongToast("Error -> ${error.message}")
                }
                is HeroListViewModel.HeroListNavigation.ShowHeroList -> navigation.run {
                    heroGridAdapter.addData(heroList)
                }
                HeroListViewModel.HeroListNavigation.HideLoading -> {
                    srwHeroList.isRefreshing = false
                }
                HeroListViewModel.HeroListNavigation.ShowLoading -> {
                    srwHeroList.isRefreshing = true
                }
            }
        }
    }


}