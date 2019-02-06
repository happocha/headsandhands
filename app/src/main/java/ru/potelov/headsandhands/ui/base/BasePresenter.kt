package ru.potelov.headsandhands.ui.base

import io.reactivex.disposables.CompositeDisposable
import ru.potelov.headsandhands.R

import ru.potelov.headsandhands.utils.rx.AppSchedulerProvider
import ru.potelov.headsandhands.utils.rx.SchedulerProvider
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BasePresenter<V : MvpView> : MvpPresenter<V> {

    val schedulerProvider: SchedulerProvider = AppSchedulerProvider()
    val compositeDisposable = CompositeDisposable()

    private var mvpView: V? = null

    fun getView(): V? = mvpView

    override fun onAttach(mvpView: V) {
        this.mvpView = mvpView
    }

    override fun onDetach() {
        compositeDisposable.dispose()
        mvpView = null
    }

    override fun handleError(error: Throwable) {
        if (isOffline(error)) {
            mvpView?.onError(R.string.error_no_connection)
        }
    }

    private fun isOffline(throwable: Throwable?): Boolean {
        return (throwable is UnknownHostException
                || throwable is SocketException
                || throwable is SocketTimeoutException)
    }
}