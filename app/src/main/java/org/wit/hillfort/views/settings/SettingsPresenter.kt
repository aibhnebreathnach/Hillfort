package org.wit.hillfort.views.settings

import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.toast
import org.wit.hillfort.R
import org.wit.hillfort.models.UserModel
import org.wit.hillfort.views.BasePresenter
import org.wit.hillfort.views.BaseView

class SettingsPresenter(view: BaseView) : BasePresenter(view){

  lateinit var user : UserModel

  init
  {
    if (view.intent.hasExtra("user_session")) {
      user = view.intent.extras.getParcelable<UserModel>("user_session")
    }
  }

  fun doSaveSettings(settingsUsername : String, settingsPassword : String){

    if (settingsUsername.isEmpty() || settingsPassword.isEmpty()){
      view?.toast(R.string.toast_username_password)
    } else {
      user.username = settingsUsername
      user.password = settingsPassword
      app.users.updateUser(user.copy())
    }

    view?.setResult(AppCompatActivity.RESULT_OK)
    view?.finish()
  }

}