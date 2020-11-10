package com.ddk.wiproassignment.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.ddk.wiproassignment.ui.main.FactsFragment
import com.ddk.wiproassignment.R

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        setupObservers()
        setupView(savedInstanceState)
    }

    protected open fun setupObservers() {

    }

    fun showMessage(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        .show()

    fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun setupView(savedInstanceState: Bundle?)

    protected fun addFragment(fragment: BaseFragment<*>) {
        supportFragmentManager.findFragmentByTag(fragment.tag) ?: supportFragmentManager
            .beginTransaction()
            .add(R.id.container, FactsFragment.newInstance(), fragment.tag)
            .commitAllowingStateLoss()
    }
}