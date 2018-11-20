package org.wit.hillfort.views.hillfort

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_hillfort.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import org.wit.hillfort.R
import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.views.BaseView

class HillfortView : BaseView(), AnkoLogger {

  lateinit var presenter : HillfortPresenter
  var hillfort = HillfortModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hillfort)

    init(toolbarHillfort)

    presenter = initPresenter (HillfortPresenter(this)) as HillfortPresenter
    chooseImage.setOnClickListener { presenter.doSelectImage() }
    hillfortLocation.setOnClickListener { presenter.doSetLocation() }
    hillfortVisited.setOnClickListener { presenter.doVisitedCheckbox(hillfortVisited.isChecked) }
  }

  override fun showHillfort(hillfort: HillfortModel) {
    hillfortTitle.setText(hillfort.title)
    hillfortDescription.setText(hillfort.description)
    hillfortNotes.setText(hillfort.notes)
    hillfortVisited.setChecked(hillfort.visited)
    hillfortDate.setText(hillfort.date)

    // Quick hack for multiple images
    for (image in hillfort.images) {
      addImageToView(image)
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_hillfort_edit, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {

      R.id.item_save -> {
        hillfort.title = hillfortTitle.text.toString()
        hillfort.description = hillfortDescription.text.toString()
        hillfort.notes = hillfortNotes.text.toString()
        hillfort.visited = hillfortVisited.isChecked
        hillfort.date = hillfortDate.text.toString()

        if (hillfort.title.isEmpty()) {
          toast(R.string.toast_enterTitle)
        } else {
          presenter.doAddOrSave(hillfort.title, hillfort.description, hillfort.notes, hillfort.visited, hillfort.date, hillfort.images)
        }
        setResult(AppCompatActivity.RESULT_OK)
        finish()
      }

      R.id.item_cancel -> { presenter.doCancel() }
      R.id.item_delete -> { presenter.doDelete() }

    }
    return super.onOptionsItemSelected(item)
  }

  fun addImageToView(image: String) {
    val image_view = ImageView(this)
    image_view.setPadding(8, 10, 8, 10)

    Picasso.get().load(image)
        .placeholder(R.mipmap.ic_launcher)
        .resize(1000, 1000)
        .centerInside()
        .into(image_view)

    hillfort_layout.addView(image_view)
  }
}