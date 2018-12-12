package org.wit.hillfort.views.settings

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_settings.*
import org.wit.hillfort.R
import org.wit.hillfort.main.MainApp
import org.wit.hillfort.views.BaseView


class SettingsView : BaseView() {

  lateinit var presenter: SettingsPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_settings)
    setSupportActionBar(toolbarSettings)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    presenter = initPresenter (SettingsPresenter(this)) as SettingsPresenter

    settings_username.setText(presenter.user.username)
    settings_password.setText(presenter.user.password)

    var hillfortCountTotal = presenter.user.hillforts.size
    var hillfortCountVisited = presenter.user.hillforts.count { hf -> hf.visited }
    var hillfortCountFavorites = presenter.user.hillforts.count {hf -> hf.favorite }

    settings_hillfort_count_total.setBackground(null)
    settings_hillfort_count_total.setKeyListener(null)
    settings_hillfort_count_visited.setBackground(null)
    settings_hillfort_count_visited.setKeyListener(null)
    settings_hillfort_count_favorite.setBackground(null)
    settings_hillfort_count_favorite.setKeyListener(null)

    val total = getResources().getString(R.string.settings_hillfort_count_total)
    val visited = getResources().getString(R.string.settings_hillfort_count_visited)
    val favorites = getResources().getString(R.string.settings_hillfort_count_favorite)

    settings_hillfort_count_total.setText(hillfortCountTotal.toString() +" "+ total)
    settings_hillfort_count_visited.setText(hillfortCountVisited.toString() +" "+ visited)
    settings_hillfort_count_favorite.setText(hillfortCountFavorites.toString() +" "+ favorites)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {

      R.id.item_settings_save -> {
        var username = settings_username.text.toString()
        var password = settings_password.text.toString()
        presenter.doSaveSettings(username, password)
      }

    }
    return super.onOptionsItemSelected(item)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_settings, menu)
    return super.onCreateOptionsMenu(menu)
  }

}