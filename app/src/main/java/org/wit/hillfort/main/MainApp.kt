package org.wit.hillfort.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfort.models.HillfortJSONStore
import org.wit.hillfort.models.HillfortStore
import org.wit.hillfort.models.UserMemStore
import org.wit.hillfort.models.UserModel
import org.wit.hillfort.models.UserStore

class MainApp : Application(), AnkoLogger {

  lateinit var hillforts: HillfortStore
  lateinit var users: UserStore

  override fun onCreate() {
    super.onCreate()

    users = UserMemStore()

    users.create(UserModel(0, "pinocchio", "secret"))
    users.create(UserModel(0, "geppetto", "secret"))
    users.create(UserModel(0, "jiminy", "secret"))

//  hillforts = HillfortMemStore()
    hillforts = HillfortJSONStore(applicationContext)
    info("Hillfort started")
    info("User log: users created :" + users.findAll().toString())
  }
}