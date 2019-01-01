package org.wit.hillfort.views.settings

import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.toast
import org.wit.hillfort.R
import org.wit.hillfort.models.UserModel
import org.wit.hillfort.views.BasePresenter
import org.wit.hillfort.views.BaseView

class SettingsPresenter(view: BaseView) : BasePresenter(view){

  var user = app.user

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