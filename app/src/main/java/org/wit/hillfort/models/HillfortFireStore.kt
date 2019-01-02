package org.wit.hillfort.models

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.jetbrains.anko.AnkoLogger
import org.wit.hillfort.models.HillfortModel

class HillfortFireStore(val context: Context) : AnkoLogger {

  val hillforts = ArrayList<HillfortModel>()
  lateinit var userId: String
  lateinit var db: DatabaseReference

  fun findAll(): List<HillfortModel> {
    return hillforts
  }

  fun findById(id: Long): HillfortModel? {
    val foundHillfort: HillfortModel? = hillforts.find { p -> p.id == id }
    return foundHillfort
  }

  fun create(hillfort: HillfortModel) {
    val key = db.child("users").child(userId).child("hillforts").push().key
    hillfort.fbId = key!!
    hillforts.add(hillfort)
    db.child("users").child(userId).child("hillforts").child(key).setValue(hillfort)
  }

  fun update(hillfort: HillfortModel) {
    var foundHillfort: HillfortModel? = hillforts.find { p -> p.fbId == hillfort.fbId }
    if (foundHillfort != null) {
      foundHillfort.title = hillfort.title
      foundHillfort.description = hillfort.description
      foundHillfort.images = hillfort.images
      foundHillfort.location = hillfort.location
    }

    db.child("users").child(userId).child("hillforts").child(hillfort.fbId).setValue(hillfort)
  }

  fun delete(hillfort: HillfortModel) {
    db.child("users").child(userId).child("hillforts").child(hillfort.fbId).removeValue()
    hillforts.remove(hillfort)
  }

  fun clear() {
    hillforts.clear()
  }

  fun fetchHillforts(hillfortsReady: () -> Unit) {
    val valueEventListener = object : ValueEventListener {
      override fun onCancelled(error: DatabaseError) {
      }
      override fun onDataChange(dataSnapshot: DataSnapshot) {
        dataSnapshot.children.mapNotNullTo(hillforts) { it.getValue<HillfortModel>(HillfortModel::class.java) }
        hillfortsReady()
      }
    }
    userId = FirebaseAuth.getInstance().currentUser!!.uid
    db = FirebaseDatabase.getInstance().reference
    hillforts.clear()
    db.child("users").child(userId).child("hillforts").addListenerForSingleValueEvent(valueEventListener)
  }
}