package ru.potelov.headsandhands.ui.base

interface MvpPresenter<V : MvpView> {

    fun onAttach(mvpView: V)

    fun onDetach()

    fun handleError(error: Throwable)

}
