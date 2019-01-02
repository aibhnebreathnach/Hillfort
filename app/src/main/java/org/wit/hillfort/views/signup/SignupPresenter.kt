package org.wit.hillfort.views.signup

import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.toast
import org.wit.hillfort.views.BasePresenter
import org.wit.hillfort.views.BaseView

class SignupPresenter(view: BaseView) : BasePresenter(view){

  var auth: FirebaseAuth = FirebaseAuth.getInstance()

  fun doSignUp(email: String, password: String) {
    view?.showProgress()
    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(view!!) { task ->
      if (task.isSuccessful) {
        view?.finish()
        view?.hideProgress()
      } else {
        view?.toast("Sign Up Failed: ${task.exception?.message}")
      }
    }
  }
}