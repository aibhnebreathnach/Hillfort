package org.wit.hillfort.views.signin

import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import org.wit.hillfort.models.HillfortFireStore
import org.wit.hillfort.views.BasePresenter
import org.wit.hillfort.views.BaseView
import org.wit.hillfort.views.VIEW

class SigninPresenter(view: BaseView) : BasePresenter(view), AnkoLogger {

  var auth: FirebaseAuth = FirebaseAuth.getInstance()

  fun doSignin(email: String, password: String) {
    view?.showProgress()
    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(view!!) { task ->
      if (task.isSuccessful) {
        view?.toast("Sign in as " + FirebaseAuth.getInstance().currentUser?.email.toString())
        view?.navigateTo(VIEW.LIST)
      } else {
        view?.toast("Sign In Failed: ${task.exception?.message}")
      }
      view?.hideProgress()
    }
  }

  fun doSignIn(email: String, password: String) {
    view?.showProgress()
    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(view!!) { task ->
      if (task.isSuccessful) {
        if (app.firestore != null) {
          app.firestore!!.fetchHillforts {
            view?.hideProgress()
            view?.navigateTo(VIEW.LIST)
          }
        } else {
          view?.hideProgress()
          view?.navigateTo(VIEW.LIST)
        }
      } else {
        view?.hideProgress()
        view?.toast("Sign Up Failed: ${task.exception?.message}")
      }
    }
  }


}