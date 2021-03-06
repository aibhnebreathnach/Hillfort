package org.wit.hillfort.models

interface UserStore {
  fun findAllUsers(): List<UserModel>
  fun createUser(user: UserModel)
  fun updateUser(user: UserModel)
  fun deleteUser(user: UserModel)

  fun findAllHillforts(user: UserModel): List<HillfortModel>
  fun findById(user: UserModel, id: Long): HillfortModel?

  fun createHillfort(user: UserModel, hillfort: HillfortModel)
  fun updateHillfort(user: UserModel, hillfort: HillfortModel)
  fun deleteHillfort(user: UserModel, hillfort: HillfortModel)
}