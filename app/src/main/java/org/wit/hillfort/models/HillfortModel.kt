package org.wit.hillfort.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HillfortModel(var id: Long = 0,
                         var fbId : String = "",
                         var title: String = "",
                         var description: String = "",
                         var notes: String = "",
                         var images: ArrayList<String> = ArrayList(),
                         var visited: Boolean = false,
                         var favorite: Boolean = false,
                         var rating: Float = 0.0f,
                         var date: String = "",
                         var location: Location = Location(0.0, 0.0, 0f)) : Parcelable

@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable