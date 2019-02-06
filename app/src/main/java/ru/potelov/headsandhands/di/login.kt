package ru.potelov.headsandhands.di

import ru.potelov.headsandhands.ui.login.LoginActivity
import ru.potelov.headsandhands.ui.login.LoginMvpPresenter
import ru.potelov.headsandhands.ui.login.LoginMvpView
import ru.potelov.headsandhands.ui.login.LoginPresenter

fun LoginActivity.providePresenter(): LoginMvpPresenter<LoginMvpView> {
    return LoginPresenter()
}
