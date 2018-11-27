package org.wit.user.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfort.models.UserModel
import org.wit.hillfort.models.UserStore
import org.wit.hillfort.helpers.*
import org.wit.hillfort.models.HillfortModel
import java.util.*

private val JSON_FILE = "users.json"
private val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
private val userlistType = object : TypeToken<java.util.ArrayList<UserModel>>() {}.type

private fun generateRandomId(): Long{
  return Random().nextLong()
}

class UserJSONStore : UserStore, AnkoLogger {

  val context: Context
  var users = mutableListOf<UserModel>()

  constructor(context: Context){
    this.context = context
    if(exists(context, JSON_FILE)){
      deserialize()
    }
  }

  override fun findAllUsers(): MutableList<UserModel> {
    return users
  }

  override fun createUser(user: UserModel) {
    user.id = generateRandomId()
    users.add(user)
    serialize()
  }

  override fun updateUser(user: UserModel) {
    var foundUser: UserModel? = users.find { p -> p.id == user.id }
    if (foundUser != null) {
      foundUser.username = user.username
      foundUser.password = user.password
      foundUser.hillforts = user.hillforts
      serialize()
    }
  }

  override fun deleteUser(user: UserModel) {
    users.remove(user)
    serialize()
  }

  override fun findAllHillforts(user: UserModel): List<HillfortModel> {
    var foundUser: UserModel? = users.find { p -> p.id == user.id }
    return foundUser!!.hillforts
  }

  override fun findById(user : UserModel, id: Long) : HillfortModel? {
    val foundHillfort: HillfortModel? = user.hillforts.find { it.id == id }
    return foundHillfort
  }

  override fun createHillfort(user: UserModel, hillfort: HillfortModel) {
    hillfort.id = generateRandomId()
    var foundUser: UserModel? = users.find { p -> p.id == user.id }
    if (foundUser != null) {
      foundUser.hillforts.add(hillfort)
      serialize()
    }
  }

  override fun updateHillfort(user: UserModel, hillfort: HillfortModel) {
    var foundUser: UserModel? = users.find { p -> p.id == user.id }
    if (foundUser != null) {
      var foundHillfort: HillfortModel? = foundUser.hillforts.find { p -> p.id == hillfort.id }
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
        serialize()
      }
    }
  }

  override fun deleteHillfort(user: UserModel, hillfort: HillfortModel) {
    var foundUser: UserModel? = users.find { p -> p.id == user.id }
    if (foundUser != null) {
      foundUser.hillforts.remove(hillfort)
      serialize()
    }
  }

  private fun serialize() {
    val userJson = gsonBuilder.toJson(users)
    write(context, JSON_FILE, userJson)
  }

  private fun deserialize() {
    val jsonString = read(context, JSON_FILE)
    users = Gson().fromJson(jsonString, userlistType)
  }

}