package org.wit.hillfort.models

import org.jetbrains.anko.AnkoLogger
import java.util.*

private var lastId = 0L

private fun getId(): Long {
  return Random().nextLong()
}

class UserMemStore : UserStore, AnkoLogger {

  val users = ArrayList<UserModel>()

  override fun findAll(): List<UserModel> {
    return users
  }

  override fun create(user: UserModel) {
    user.id = getId()
    users.add(user)
  }

  override fun delete(user: UserModel) {
    users.remove(user)
  }
}
