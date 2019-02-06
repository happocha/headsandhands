package ru.potelov.headsandhands.di

import ru.potelov.headsandhands.ui.main.MainActivity
import ru.potelov.headsandhands.ui.main.MainMvpPresenter
import ru.potelov.headsandhands.ui.main.MainMvpView
import ru.potelov.headsandhands.ui.main.MainPresenter
import ru.potelov.headsandhands.utils.extensions.app

fun MainActivity.providePresenter(): MainMvpPresenter<MainMvpView> {
    return MainPresenter(app.repository)
}