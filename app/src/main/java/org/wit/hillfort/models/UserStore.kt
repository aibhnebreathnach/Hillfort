package org.wit.hillfort.models

interface UserStore {
  fun findAll(): List<UserModel>
  fun create(user: UserModel)
  fun delete(user: UserModel)
}