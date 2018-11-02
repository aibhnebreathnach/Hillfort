package org.wit.hillfort.activities


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_hillfort.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.wit.hillfort.R
import org.wit.hillfort.helpers.readImage
import org.wit.hillfort.helpers.showImagePicker
import org.wit.hillfort.main.MainApp
import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.models.Location


class HillfortActivity : AppCompatActivity(), AnkoLogger {

  var hillfort = HillfortModel()
  lateinit var app : MainApp
  val IMAGE_REQUEST = 1;
  val LOCATION_REQUEST = 2
  var location = Location(52.245696, -7.139102, 15f)
  var edit = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hillfort)
    app = application as MainApp

    setSupportActionBar(toolbarHillfort)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    chooseImage.setOnClickListener {
      showImagePicker(this, IMAGE_REQUEST)
    }

    hillfortLocation.setOnClickListener {
      val location = Location(52.245696, -7.139102, 15f)
      if (hillfort.zoom != 0f) {
        location.lat =  hillfort.lat
        location.lng = hillfort.lng
        location.zoom = hillfort.zoom
      }
      startActivityForResult(intentFor<MapsActivity>().putExtra("location", location), LOCATION_REQUEST)
    }

    if (intent.hasExtra("hillfort_edit")) {
      edit = true
      hillfort = intent.extras.getParcelable<HillfortModel>("hillfort_edit")
      hillfortTitle.setText(hillfort.title)
      hillfortDescription.setText(hillfort.description)
      checkbox_visited.setChecked(hillfort.visited)

      hillfort_images_list_view.adapter = HillfortImageAdapter(this, hillfort.images)
    }
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_hillfort_edit, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {

      R.id.item_save -> {
        hillfort.title = hillfortTitle.text.toString()
        hillfort.description = hillfortDescription.text.toString()
        hillfort.visited = checkbox_visited.isChecked

        if (hillfort.title.isEmpty()) {
          toast(R.string.toast_enterTitle)
        } else {
          if (edit) {
            app.hillforts.update(hillfort.copy())
          } else {
            app.hillforts.create(hillfort.copy())
          }
        }
        setResult(AppCompatActivity.RESULT_OK)
        finish()
      }

      R.id.item_cancel -> {
        finish()
      }

      R.id.item_delete -> {
        app.hillforts.delete(hillfort)
        finish()
        toast("Hillfort Deleted!")
      }
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    when (requestCode) {
      IMAGE_REQUEST -> {
        if (data != null) {

          hillfort.images.add(data.data.toString())
          hillfort_images_list_view.adapter = HillfortImageAdapter(this, hillfort.images)

        }
      }

      LOCATION_REQUEST -> {
        if (data != null) {
          val location = data.extras.getParcelable<Location>("location")
          hillfort.lat = location.lat
          hillfort.lng = location.lng
          hillfort.zoom = location.zoom
        }
      }
    }
  }

}
