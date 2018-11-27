package org.wit.hillfort.views.hillfortlist

import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.models.UserModel
import org.wit.hillfort.views.BasePresenter
import org.wit.hillfort.views.BaseView
import org.wit.hillfort.views.VIEW

class HillfortListPresenter(view: BaseView) : BasePresenter(view) {

  var user = app.user

  fun doAddHillfort(){
    view?.navigateTo(VIEW.HILLFORT, 0)
  }

  fun doEditHillfort(hillfort : HillfortModel){
    view?.navigateTo(VIEW.HILLFORT, 0, "hillfort_edit", hillfort)
  }

  fun doSettings(){
    view?.navigateTo(VIEW.SETTINGS, 0)
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