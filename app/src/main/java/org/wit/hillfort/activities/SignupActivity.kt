package org.wit.hillfort.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_signin.*
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.wit.hillfort.R
import org.wit.hillfort.main.MainApp
import org.wit.hillfort.models.UserModel

class SignupActivity : AppCompatActivity(), AnkoLogger {

  lateinit var app: MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_signup)
    app = application as MainApp

    toolbarSignup.title = getString(R.string.signup_title)
    setSupportActionBar(toolbarSignup)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    signup_button.setOnClickListener() {

      val username = signup_username.text.toString()
      val password = signup_password.text.toString()

      if(username == "" || password == ""){
        toast("Please enter a username and password!")
      } else {
        var newuser = UserModel()
        newuser.username = username
        newuser.password = password
        app.users.create(newuser)
        finish()
      }

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
