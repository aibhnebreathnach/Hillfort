package org.wit.hillfort.views.hillfortlist

import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.models.UserModel
import org.wit.hillfort.views.BasePresenter
import org.wit.hillfort.views.BaseView
import org.wit.hillfort.views.VIEW

class HillfortListPresenter(view: BaseView) : BasePresenter(view) {

  lateinit var user : UserModel

  fun doAddHillfort(){
    view?.navigateTo(VIEW.HILLFORT, 0, "user_session", user)
  }

  fun doEditHillfort(user : UserModel, hillfort : HillfortModel){
    view?.navigateTo(VIEW.HILLFORT, 0, "hillfort_edit", hillfort)
  }

  fun doSettings(){
    view?.navigateTo(VIEW.SETTINGS, 0, "user_session", user)
  }

  fun doLogout() {
    view?.navigateTo(VIEW.SIGNIN)
  }

  fun doShowHillfortMap() {
    view?.navigateTo(VIEW.MAPS)
  }

  fun loadHilforts() {
    view?.showHillforts(user.hillforts)
  }
}