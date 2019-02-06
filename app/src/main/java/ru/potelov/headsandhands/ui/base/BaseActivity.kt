package ru.potelov.headsandhands.ui.base

import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import ru.potelov.headsandhands.R

abstract class BaseActivity : AppCompatActivity(), MvpView {

    override fun showLoading() {
        findViewById<View>(R.id.progress_bar).visibility = View.VISIBLE
    }

    override fun hideLoading() {
        findViewById<View>(R.id.progress_bar).visibility = View.GONE
    }

    override fun onError(message: String) {
        if (message != null) {
            showSnackBar(message)
        } else {
            showSnackBar(getString(R.string.error_something))
        }
    }

    private fun showSnackBar(message: String) {
        val snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
        val sbView = snackbar.view
        val textView = sbView.findViewById<TextView>(R.id.snackbar_text)
        textView.setTextColor(ContextCompat.getColor(this, R.color.white))
        snackbar.show()
    }

    override fun onError(@StringRes resId: Int) {
        onError(getString(resId))
    }
}
