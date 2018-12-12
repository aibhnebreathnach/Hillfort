package org.wit.hillfort.views.hillfort

import android.content.Intent
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.models.Location
import org.wit.hillfort.models.UserModel
import org.wit.hillfort.helpers.showImagePicker
import org.wit.hillfort.views.*
import java.util.*

class HillfortPresenter(view: BaseView) : BasePresenter(view) {

  var map: GoogleMap? = null
  var hillfort = HillfortModel()
  var user = app.user
  var defaultLocation = Location(52.245696, -7.139102, 15f)
  var edit = false

  init
  {
    if (view.intent.hasExtra("hillfort_edit")) {
      edit = true
      hillfort = view.intent.extras.getParcelable<HillfortModel>("hillfort_edit")
      view.showHillfort(hillfort)
    }

  }

  fun doAddOrSave(title: String, description: String, notes: String, visited: Boolean, favorite: Boolean, date: String, rating: Float) {
    hillfort.title = title
    hillfort.description = description
    hillfort.notes = notes
    hillfort.visited = visited
    hillfort.favorite = favorite
    hillfort.date = date
    hillfort.rating = rating

    if (edit) {
      app.users.updateHillfort(user, hillfort)
    } else {
      app.users.createHillfort(user, hillfort)
    }
    view?.finish()
  }

  // Temporarily save the field values to the Presenter Hillfort object
  fun doTempSave(title: String, description: String, notes: String, visited: Boolean, favorite: Boolean, date: String, rating: Float){
    hillfort.title = title
    hillfort.description = description
    hillfort.notes = notes
    hillfort.visited = visited
    hillfort.favorite = favorite
    hillfort.date = date
    hillfort.rating = rating
  }

  fun doCancel() {
    view?.finish()
  }

  fun doDelete() {
    app.users.deleteHillfort(user, hillfort)
    view?.finish()
  }

  fun doSelectImage() {
    view?.let{
      showImagePicker(view!!, IMAGE_REQUEST)
    }
  }

  fun loadHillfortImages(){
    view?.showHillfortImages(hillfort.images)
  }

  fun doSetLocation() {
    if (edit == false) {
      view?.navigateTo(VIEW.LOCATION, LOCATION_REQUEST, "location", defaultLocation)
    } else {
      view?.navigateTo(VIEW.LOCATION, LOCATION_REQUEST, "location", Location(hillfort.location.lat, hillfort.location.lng, hillfort.location.zoom))
    }
  }

  fun doVisitedCheckbox(visited: Boolean) {
    hillfort.visited = visited

    if(hillfort.visited){
      hillfort.date = Date().toString()
    } else {
      hillfort.date = ""
    }

    view?.showHillfort(hillfort)
  }

  fun doFavoriteCheckbox(favorite: Boolean) {
    hillfort.favorite = favorite
    view?.showHillfort(hillfort)
  }

  fun doConfigureMap(m: GoogleMap) {
    map = m
    locationUpdate(hillfort.location.lat, hillfort.location.lng)
  }

  fun locationUpdate(lat: Double, lng: Double) {
    hillfort.location.lat = lat
    hillfort.location.lng = lng
    hillfort.location.zoom = 15f
    map?.clear()
    map?.uiSettings?.setZoomControlsEnabled(true)
    val options = MarkerOptions().title(hillfort.title).position(LatLng(hillfort.location.lat, hillfort.location.lng))
    map?.addMarker(options)
    map?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(hillfort.location.lat, hillfort.location.lng), hillfort.location.zoom))
    view?.showHillfort(hillfort)
  }

  override fun doActivityResult(requestCode: Int, resultCode: Int, data: Intent) {

    when (requestCode)
    {
      IMAGE_REQUEST -> {
        if (data != null) {
          val image = data.data.toString()
          hillfort.images.add(image)
          view?.showHillfort(hillfort)
        }
      }

      LOCATION_REQUEST -> {
        if (data != null) {
          val location = data.extras.getParcelable<Location>("location")
          hillfort.location.lat = location.lat
          hillfort.location.lng = location.lng
          hillfort.location.zoom = location.zoom
          locationUpdate(hillfort.location.lat, hillfort.location.lng)
        }
      }

    }

  }
}