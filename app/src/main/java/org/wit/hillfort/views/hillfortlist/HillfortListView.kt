package org.wit.hillfort.views.hillfortlist

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_hillfort_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.wit.hillfort.R
import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.models.UserModel
import org.wit.hillfort.views.BaseView
import org.wit.hillfort.views.hillfort.HillfortView

class HillfortListView : BaseView(), HillfortListener {

  lateinit var presenter: HillfortListPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hillfort_list)
    setSupportActionBar(toolbarMain)

    presenter = initPresenter(HillfortListPresenter(this)) as HillfortListPresenter

    hillfortSearch.addTextChangedListener(object: TextWatcher{

      override fun afterTextChanged(s: Editable?) {
      }

      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
      }

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        presenter.filterHillforts(s.toString())
      }

    })

    var layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

    // if orientation is landscape, make hillfort recycler view scroll horizontally
    var orientation = getResources().getConfiguration().orientation
    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
      layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this, androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL, false)
    }

    recyclerView.layoutManager = layoutManager
    presenter.loadHilforts()

  }

  override fun showHillforts (hillforts: List<HillfortModel>) {
    recyclerView.adapter = HillfortAdapter(hillforts, this)
    recyclerView.adapter?.notifyDataSetChanged()
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    presenter.loadHilforts()
    super.onActivityResult(requestCode, resultCode, data)
  }

  override fun onHillfortClick(hillfort: HillfortModel) {
    presenter.doEditHillfort(hillfort)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.item_add -> { presenter.doAddHillfort() }
      R.id.item_filter_favorite -> {
        if (!presenter.favorite) {
          presenter.favorite = true
        } else {
          presenter.favorite = false
        }
        presenter.loadHilforts()
      }
      R.id.item_map -> { presenter.doShowHillfortMap() }
      R.id.item_settings -> { presenter.doSettings() }
      R.id.item_logout -> { presenter.doLogout() }
    }
    return super.onOptionsItemSelected(item)
  }

}