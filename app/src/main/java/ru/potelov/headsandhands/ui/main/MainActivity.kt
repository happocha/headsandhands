package ru.potelov.headsandhands.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import ru.potelov.headsandhands.R
import ru.potelov.headsandhands.di.providePresenter

import ru.potelov.headsandhands.ui.base.BaseActivity
import ru.potelov.headsandhands.ui.base.MvpView

interface MainMvpView : MvpView

class MainActivity : BaseActivity(), MainMvpView {

    private val presenter by lazy { providePresenter() }

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onAttach(this)
        setUp()
    }

    private fun setUp() {
        presenter.init()
        iv_back.setOnClickListener { onBackPressed() }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}