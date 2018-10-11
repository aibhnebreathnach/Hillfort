package org.wit.hillfort.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfort.models.HillfortMemStore
import org.wit.hillfort.models.HillfortModel

class MainApp : Application(), AnkoLogger {

  val hillforts = HillfortMemStore()

  override fun onCreate() {
    super.onCreate()
    info("Hillfort started")
    hillforts.create(HillfortModel(0, "First", "About one..."))
    hillforts.create(HillfortModel(1, "Second", "About two..."))
    hillforts.create(HillfortModel(3, "Third", "About three..."))
  }
}