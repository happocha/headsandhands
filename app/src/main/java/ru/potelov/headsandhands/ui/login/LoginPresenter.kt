package ru.potelov.headsandhands.ui.login

import ru.potelov.headsandhands.R
import ru.potelov.headsandhands.ui.base.BasePresenter
import ru.potelov.headsandhands.ui.base.MvpPresenter
import java.util.regex.Matcher
import java.util.regex.Pattern

private const val PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$"
private const val EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

interface LoginMvpPresenter<V : LoginMvpView> : MvpPresenter<V> {
    fun onLoginClick(email: String, password: String)
    fun onCreateClick()
}

class LoginPresenter<V : LoginMvpView> : BasePresenter<V>(), LoginMvpPresenter<V> {

    override fun onLoginClick(email: String, password: String) {
        when {
            !validate(email, EMAIL_PATTERN) -> getView()?.onError(R.string.error_email)
            !validate(password, PASSWORD_PATTERN) -> getView()?.onError(R.string.error_password)
            else -> {
               getView()?.openMainActivity()
            }
        }
    }

    override fun onCreateClick() {
        getView()?.onError(R.string.login_create_button)
    }

    private fun validate(password: String, template: String) : Boolean {
        val pattern: Pattern = Pattern.compile(template)
        val matcher: Matcher = pattern.matcher(password)
        return matcher.matches()
    }
}