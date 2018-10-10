package org.wit.hillfort.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfort.models.HillfortJSONStore
import org.wit.hillfort.models.HillfortStore
import org.wit.hillfort.models.UserStore
import org.wit.user.models.UserJSONStore

class MainApp : Application(), AnkoLogger {

  lateinit var hillforts: HillfortStore
  lateinit var users: UserStore

  override fun onCreate() {
    super.onCreate()


//    users.create(UserModel(0, "pinocchio", "secret"))
//    users.create(UserModel(0, "geppetto", "secret"))
//    users.create(UserModel(0, "jiminy", "secret"))

//    users = UserMemStore()
//    hillforts = HillfortMemStore()

    users = UserJSONStore(applicationContext)
    hillforts = HillfortJSONStore(applicationContext)

    info("Hillfort started")
  }
}