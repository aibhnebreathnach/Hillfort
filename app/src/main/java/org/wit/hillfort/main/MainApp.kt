package org.wit.hillfort.main

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfort.models.*
import org.wit.user.models.UserJSONStore

class MainApp : Application(), AnkoLogger {

//  lateinit var users: UserStore
//  lateinit var user: UserModel
  lateinit var hillforts: ArrayList<HillfortModel>
  lateinit var firestore: HillfortFireStore

  override fun onCreate() {
    super.onCreate()
//    users = UserMemStore()
//    users.createUser(UserModel(0, "geppetto", "secret"))
//    users = UserJSONStore(applicationContext)
    firestore = HillfortFireStore(applicationContext)
    hillforts = firestore.hillforts


    info("Hillfort started")
  }
}