package org.wit.hillfort.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfort.models.*
import org.wit.user.models.UserJSONStore

class MainApp : Application(), AnkoLogger {

  lateinit var hillforts: HillfortStore
  lateinit var users: UserStore

  override fun onCreate() {
    super.onCreate()

//    users = UserMemStore()
//    hillforts = HillfortMemStore()

//    users.createUser(UserModel(0, "geppetto", "secret"))

    users = UserJSONStore(applicationContext)
//    hillforts = HillfortJSONStore(applicationContext)

    info("Hillfort started")
  }
}