package ru.potelov.headsandhands.ui.main

import ru.potelov.headsandhands.data.Repository
import ru.potelov.headsandhands.ui.base.BasePresenter
import ru.potelov.headsandhands.ui.base.MvpPresenter

interface MainMvpPresenter<V : MainMvpView> : MvpPresenter<V> {
    fun init()
}

class MainPresenter<V : MainMvpView>(
    private val repository: Repository)
    : BasePresenter<V>(), MainMvpPresenter<V> {

    override fun init() {
        getView()?.showLoading()
        compositeDisposable.add(
            repository
                .getWeather("moscow")
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    getView()?.hideLoading()
                    val result = "temp = %.2f description = %s".format(
                        it.weather.temp,
                        it.weather.description)
                    getView()?.onError(result)
                }, { throwable ->
                    getView()?.hideLoading()
                    handleError(throwable)
                })
        )
    }
}