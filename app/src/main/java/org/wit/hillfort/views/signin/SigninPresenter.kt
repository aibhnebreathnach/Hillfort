package org.wit.hillfort.views.signin

import org.jetbrains.anko.toast
import org.wit.hillfort.views.BasePresenter
import org.wit.hillfort.views.BaseView
import org.wit.hillfort.views.VIEW

class SigninPresenter(view: BaseView) : BasePresenter(view) {

 fun doSignin(username: String, password: String){

   var user = app.users.findAllUsers().find { it.username == username && it.password == password }
   if (user != null) {
     view?.toast("Sign In Successful!")
     app.user = user
     view?.navigateTo(VIEW.LIST, 0)
     view?.finish()
   } else {
     view?.toast("Incorrect username or password!")
   }

 }

}