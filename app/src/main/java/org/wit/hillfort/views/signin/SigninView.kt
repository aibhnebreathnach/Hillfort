package org.wit.hillfort.views.signin

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_signin.*
import org.jetbrains.anko.*
import org.wit.hillfort.R
import org.wit.hillfort.views.BaseView
import org.wit.hillfort.views.VIEW

class SigninView : BaseView(), AnkoLogger {

  lateinit var presenter : SigninPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_signin)

    presenter = initPresenter (SigninPresenter(this)) as SigninPresenter

    toolbarSignin.title = getString(R.string.signin_title)
    setSupportActionBar(toolbarSignin)

    signin_button.setOnClickListener() {

      val username = signin_username.text.toString()
      val password = signin_password.text.toString()
      presenter.doSignin(username, password)
    }

  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {

      R.id.signup -> {
        navigateTo(VIEW.SIGNUP)
      }

    }
    return super.onOptionsItemSelected(item)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_signin, menu)
    return super.onCreateOptionsMenu(menu)
  }
}
