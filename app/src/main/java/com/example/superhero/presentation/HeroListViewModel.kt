package com.example.superhero.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.superhero.domain.Hero
import com.example.superhero.usecases.GetAllHeroesUseCase
import io.reactivex.disposables.CompositeDisposable

class HeroListViewModel (
    private val getAllHeroesUseCase: GetAllHeroesUseCase
): ViewModel(){

    private val disposable = CompositeDisposable()

    private val _events = MutableLiveData<Event<HeroListNavigation>>()
    val events: LiveData<Event<HeroListNavigation>> get() = _events

    private var currentPage = 1
    private var isLastPage = false
    private var isLoading = false


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


    fun onLoadMoreItems(visibleItemCount: Int, firstVisibleItemPosition: Int, totalItemCount: Int) {
        if (isLoading || isLastPage || !isInFooter(visibleItemCount, firstVisibleItemPosition, totalItemCount)) {
            return
        }

        currentPage += 1
        onGetAllHeroes()
    }

    fun onRetryGetAllHeroes(itemCount: Int) {
        if (itemCount > 0) {
            _events.value = Event(HeroListNavigation.HideLoading)
            return
        }

        onGetAllHeroes()
    }

    fun onGetAllHeroes(){
        disposable.add(
            getAllHeroesUseCase
                .operate(currentPage)
                .doOnSubscribe { showLoading() }
                .subscribe({ heroList ->
                    if (heroList.size < PAGE_SIZE) {
                        isLastPage = true
                    }

                    hideLoading()
                    _events.value = Event(HeroListNavigation.ShowHeroList(heroList))
                }, { error ->
                    isLastPage = true
                    hideLoading()
                    _events.value = Event(HeroListNavigation.ShowHeroError(error))
                })
        )
    }


    private fun isInFooter(
        visibleItemCount: Int,
        firstVisibleItemPosition: Int,
        totalItemCount: Int
    ): Boolean {
        return visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= PAGE_SIZE
    }

    private fun showLoading() {
        isLoading = true
        _events.value = Event(HeroListNavigation.ShowLoading)
    }

    private fun hideLoading() {
        isLoading = false
        _events.value = Event(HeroListNavigation.HideLoading)
    }



    sealed class HeroListNavigation {
        data class ShowHeroError(val error: Throwable) : HeroListNavigation()
        data class ShowHeroList(val heroList: List<Hero>) : HeroListNavigation()
        object HideLoading : HeroListNavigation()
        object ShowLoading : HeroListNavigation()
    }



    companion object {

        private const val PAGE_SIZE = 20
    }


}