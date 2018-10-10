package org.wit.hillfort.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_signin.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.wit.hillfort.R
import org.wit.hillfort.main.MainApp
import org.wit.hillfort.models.UserModel

class SigninActivity : AppCompatActivity(), AnkoLogger {

  lateinit var app : MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_signin)
    app = application as MainApp


    signin_button.setOnClickListener(){

      val username = signin_username.text.toString()
      val password = signin_password.text.toString()

      for (user in app.users.findAll()){

        if (user.username == username && user.password == password) {
          toast("Sign In Successful!")
          startActivity<HillfortListActivity>()
        } else {
          toast("Incorrect username or password!")
        }
      }

    }
  }
}