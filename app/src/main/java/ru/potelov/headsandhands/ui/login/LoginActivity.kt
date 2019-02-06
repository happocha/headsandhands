package ru.potelov.headsandhands.ui.login

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import ru.potelov.headsandhands.R
import ru.potelov.headsandhands.di.providePresenter
import ru.potelov.headsandhands.ui.base.BaseActivity
import ru.potelov.headsandhands.ui.base.MvpView
import ru.potelov.headsandhands.ui.main.MainActivity

interface LoginMvpView : MvpView {
    fun openMainActivity()
}

class LoginActivity : BaseActivity(), LoginMvpView {

    private val presenter by lazy { providePresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.onAttach(this)
        setUp()
    }

    private fun setUp() {
        btn_login.setOnClickListener {
            presenter.onLoginClick(
                et_email.text.toString().trim(),
                et_password.text.toString().trim()
            )
        }
        iv_back.setOnClickListener {
            onBackPressed()
        }
        tv_create.setOnClickListener {
            presenter.onCreateClick()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }

    override fun openMainActivity() {
        startActivity(MainActivity.getStartIntent(this))
        finish()
    }
}