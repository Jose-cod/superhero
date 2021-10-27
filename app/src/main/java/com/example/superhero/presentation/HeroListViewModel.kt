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

        minId = minId.plus(10)
        maxId = maxId.plus(10)
        onGetAllHeroes()
    }

    fun onRetryGetAllHeroes(itemCount: Int) {
        if (itemCount > 0) {
            _events.value = Event(HeroListNavigation.HideLoading)
            return
        }
        //minId = minId.plus(10)
        //maxId = maxId.plus(10)
        onGetAllHeroes()
    }

    fun onGetAllHeroes(){
        if (maxId==10){
            for (i in minId..maxId){
                fillListHero(i)
            }
        }else{
            fillListHero(maxId)
        }
    }

    private fun fillListHero(id:Int){
        disposable.add(
                getAllHeroesUseCase
                        .operate(id)
                        .doOnSubscribe { showLoading() }
                        .subscribe({ hero ->
                            heroListAux.add(hero)
                            if (heroListAux.size>=PAGE_SIZE){
                                if (heroListAux.size<= TOTAL_HERO){
                                    maxId+=1
                                    hideLoading()
                                    _events.value = Event(HeroListNavigation.ShowHeroList(heroListAux))
                                }else{
                                    isLastPage = true
                                    hideLoading()
                                }
                            }
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

        private const val PAGE_SIZE = 10
        private const val TOTAL_HERO = 731
    }


}