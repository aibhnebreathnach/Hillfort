package org.wit.hillfort.views.signup

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.AnkoLogger

import org.wit.hillfort.R
import org.wit.hillfort.views.BaseView

class SignupView : BaseView(), AnkoLogger {

  lateinit var presenter: SignupPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_signup)

    toolbarSignup.title = getString(R.string.signup_title)
    setSupportActionBar(toolbarSignup)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    presenter = initPresenter (SignupPresenter(this)) as SignupPresenter

    signup_button.setOnClickListener() {

      val username = signup_username.text.toString()
      val password = signup_password.text.toString()
      presenter.doSignup(username, password)
    }

  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {

      R.id.signup_cancel -> {
        finish()
      }

    }
    return super.onOptionsItemSelected(item)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_signup, menu)
    return super.onCreateOptionsMenu(menu)
  }
}
