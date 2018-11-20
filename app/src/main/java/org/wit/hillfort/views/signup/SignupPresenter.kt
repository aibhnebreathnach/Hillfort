package org.wit.hillfort.views.signup

import org.jetbrains.anko.toast
import org.wit.hillfort.models.UserModel
import org.wit.hillfort.views.BasePresenter
import org.wit.hillfort.views.BaseView

class SignupPresenter(view: BaseView) : BasePresenter(view){



  fun doSignup(username: String, password: String){
    if(username == "" || password == ""){
      view?.toast("Please enter a username and password!")
    } else {
      var newuser = UserModel()
      newuser.username = username
      newuser.password = password
      app.users.createUser(newuser)
      view?.finish()
    }
  }
}