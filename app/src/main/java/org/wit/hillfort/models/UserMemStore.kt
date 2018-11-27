package org.wit.hillfort.models

import org.jetbrains.anko.AnkoLogger
import java.util.*

private var lastId = 0L

private fun generateRandomId(): Long{
  return Random().nextLong()
}


class UserMemStore : UserStore, AnkoLogger {

  val users = ArrayList<UserModel>()

  override fun findAllUsers(): MutableList<UserModel> {
    return users
  }

  override fun createUser(user: UserModel) {
    user.id = generateRandomId()
    users.add(user)
  }

  override fun updateUser(user: UserModel) {
    var foundUser: UserModel? = users.find { p -> p.id == user.id }
    if (foundUser != null) {
      foundUser.username = user.username
      foundUser.password = user.password
      foundUser.hillforts = user.hillforts
    }
  }

  override fun deleteUser(user: UserModel) {
    users.remove(user)
  }


  override fun findAllHillforts(user: UserModel): List<HillfortModel> {
    return user.hillforts
  }

  override fun findById(user: UserModel, id: Long) : HillfortModel? {
    val foundHillfort: HillfortModel? = user.hillforts.find { it.id == id }
    return foundHillfort
  }

  override fun createHillfort(user: UserModel, hillfort: HillfortModel) {
    hillfort.id = generateRandomId()
    user.hillforts.add(hillfort)
  }

  override fun updateHillfort(user: UserModel, hillfort: HillfortModel) {
    var foundHillfort: HillfortModel? = user.hillforts.find {p -> p.id == hillfort.id}
    if (foundHillfort != null){
      foundHillfort.title = hillfort.title
      foundHillfort.description = hillfort.description
      foundHillfort.notes = hillfort.notes
      foundHillfort.date = hillfort.date
      foundHillfort.visited = hillfort.visited
      foundHillfort.images = hillfort.images;
      foundHillfort.lat = hillfort.lat;
      foundHillfort.lng = hillfort.lng;
      foundHillfort.zoom = hillfort.zoom;
    }
  }

  override fun deleteHillfort(user: UserModel, hillfort: HillfortModel) {
    user.hillforts.remove(hillfort)
  }
}
