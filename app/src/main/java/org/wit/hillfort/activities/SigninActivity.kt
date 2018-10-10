package org.wit.hillfort.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_signin.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.toolbar
import org.wit.hillfort.R
import org.wit.hillfort.main.MainApp

class SigninActivity : AppCompatActivity(), AnkoLogger {

  lateinit var app : MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_signin)
    app = application as MainApp

    toolbarSignin.title = getString(R.string.signin_title)
    setSupportActionBar(toolbarSignin)

    signin_button.setOnClickListener() {

      val username = signin_username.text.toString()
      val password = signin_password.text.toString()

      var res = app.users.findAll().find { it.username == username && it.password == password }
      if (res != null) {
        toast("Sign In Successful!")
        startActivity<HillfortListActivity>()
      } else {
        toast("Incorrect username or password!")
      }
    }

  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {

      R.id.signup -> {
        startActivity<SignupActivity>()
      }

    }
    return super.onOptionsItemSelected(item)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_signin, menu)
    return super.onCreateOptionsMenu(menu)
  }
}