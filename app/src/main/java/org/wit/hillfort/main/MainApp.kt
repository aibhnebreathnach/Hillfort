package org.wit.hillfort.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfort.models.*
import org.wit.user.models.UserJSONStore

class MainApp : Application(), AnkoLogger {

  lateinit var users: UserStore
  lateinit var user: UserModel

  override fun onCreate() {
    super.onCreate()

//    users = UserMemStore()
//    users.createUser(UserModel(0, "geppetto", "secret"))
    users = UserJSONStore(applicationContext)

    info("Hillfort started")
  }
}