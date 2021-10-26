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
    
    private val heroListAux: ArrayList<Hero> =ArrayList()

    private var currentPage = 1
    private var isLastPage = false
    private var isLoading = false

    private var minId =1
    private var maxId= 10


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
        for (i in minId..maxId){
            disposable.add(
                    getAllHeroesUseCase
                            .operate(i)
                            .doOnSubscribe { showLoading() }
                            .subscribe({ hero ->

                                /*if (hero < PAGE_SIZE) {
                                    isLastPage = true
                                }*/
                                heroListAux.add(hero)
                                //hideLoading()
                                //_events.value = Event(HeroListNavigation.ShowHeroList(heroList))
                            }, { error ->
                                isLastPage = true
                                hideLoading()
                                _events.value = Event(HeroListNavigation.ShowHeroError(error))
                            })
            )
        }

        hideLoading()
        _events.value = Event(HeroListNavigation.ShowHeroList(heroListAux))
        minId = minId.plus(10)
        maxId = maxId.plus(10)

        if (heroListAux.size < PAGE_SIZE) {
            isLastPage = true
        }
        //disposable.add(
            /*getAllHeroesUseCase
                .operate(currentPage)
                .doOnSubscribe { showLoading() }
                .subscribe({ hero ->

                    if (hero < PAGE_SIZE) {
                        isLastPage = true
                    }

                    //hideLoading()
                    //_events.value = Event(HeroListNavigation.ShowHeroList(heroList))
                }, { error ->
                    isLastPage = true
                    hideLoading()
                    _events.value = Event(HeroListNavigation.ShowHeroError(error))
                })*/
        //)
    }

    fun onGetHero(){

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

        private const val PAGE_SIZE = 10
    }


}