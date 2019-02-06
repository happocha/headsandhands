package ru.potelov.headsandhands.ui.base

import androidx.annotation.StringRes

interface MvpView {

    fun onError(@StringRes resId: Int)

    fun onError(message: String)

    fun showLoading()

    fun hideLoading()

}
