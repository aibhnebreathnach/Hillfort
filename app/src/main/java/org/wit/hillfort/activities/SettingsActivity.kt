package org.wit.hillfort.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_settings.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.wit.hillfort.R
import org.wit.hillfort.main.MainApp
import org.wit.hillfort.models.UserModel


class SettingsActivity : AppCompatActivity() {

  lateinit var app: MainApp
  lateinit var user: UserModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_settings)
    app = application as MainApp

    setSupportActionBar(toolbarSettings)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    if(intent.hasExtra("user_session")) {
      user = intent.extras.getParcelable<UserModel>("user_session")
      settings_username.setText(user.username)
      settings_password.setText(user.password)
    }

  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {

      R.id.item_settings_save -> {
        user.username = settings_username.text.toString()
        user.password = settings_password.text.toString()

        if (user.username.isEmpty() || user.password.isEmpty()) {
          toast(R.string.toast_username_password)
        } else {
          app.users.update(user.copy())
        }
        setResult(AppCompatActivity.RESULT_OK)
        finish()
      }

    }
    return super.onOptionsItemSelected(item)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_settings, menu)
    return super.onCreateOptionsMenu(menu)
  }

}