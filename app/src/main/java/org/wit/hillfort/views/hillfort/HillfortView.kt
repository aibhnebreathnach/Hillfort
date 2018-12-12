package org.wit.hillfort.views.hillfort

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.maps.GoogleMap
import kotlinx.android.synthetic.main.activity_hillfort.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import org.wit.hillfort.R
import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.views.BaseView

class HillfortView : BaseView(), AnkoLogger, HillfortImageListener {

  lateinit var presenter : HillfortPresenter
  lateinit var map: GoogleMap

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hillfort)

    init(toolbarHillfort)

    presenter = initPresenter(HillfortPresenter(this)) as HillfortPresenter

    chooseImage.setOnClickListener {
      tempSave()
      presenter.doSelectImage()
    }

    mapView.onCreate(savedInstanceState)
    mapView.getMapAsync {
      map = it
      presenter.doConfigureMap(map)
      it.setOnMapClickListener {
        tempSave()
        presenter.doSetLocation()
      }
    }

    hillfortVisited.setOnClickListener {
      tempSave()
      presenter.doVisitedCheckbox(hillfortVisited.isChecked)
    }

    val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    hillfortImageRecycler.layoutManager = layoutManager
    presenter.loadHillfortImages()
  }

  override fun showHillfort(hillfort: HillfortModel) {
    hillfortTitle.setText(hillfort.title)
    hillfortDescription.setText(hillfort.description)
    hillfortNotes.setText(hillfort.notes)
    hillfortVisited.setChecked(hillfort.visited)
    hillfortDate.setText(hillfort.date)
    hillfortRating.setRating(hillfort.rating)
    display_lat.setText("%.6f".format(hillfort.lat))
    display_lng.setText("%.6f".format(hillfort.lng))
    showHillfortImages(hillfort.images)
  }

  override fun showHillfortImages (images: List<String>) {
    hillfortImageRecycler.adapter = HillfortImageAdapter(images, this)
    hillfortImageRecycler.adapter?.notifyDataSetChanged()
  }

  override fun onHillfortImageClick(image: String) {
    toast("Image Clicked!")
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_hillfort_edit, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {

      R.id.item_save -> {
        var title = hillfortTitle.text.toString()
        var description = hillfortDescription.text.toString()
        var notes = hillfortNotes.text.toString()
        var visited = hillfortVisited.isChecked
        var date = hillfortDate.text.toString()
        var rating = hillfortRating.rating

        if (title.isEmpty()) {
          toast(R.string.toast_enterTitle)
        } else {
          presenter.doAddOrSave(title, description, notes, visited, date, rating)
        }
        setResult(AppCompatActivity.RESULT_OK)
        finish()
      }

      R.id.item_cancel -> { presenter.doCancel() }
      R.id.item_delete -> { presenter.doDelete() }

    }
    return super.onOptionsItemSelected(item)
  }

  // Temporarily save hillfort fields in presenter hillfort object
  fun tempSave(){
    var title = hillfortTitle.text.toString()
    var description = hillfortDescription.text.toString()
    var notes = hillfortNotes.text.toString()
    var visited = hillfortVisited.isChecked
    var date = hillfortDate.text.toString()
    var rating = hillfortRating.rating
    presenter.doTempSave(title, description, notes, visited, date, rating)
  }

}