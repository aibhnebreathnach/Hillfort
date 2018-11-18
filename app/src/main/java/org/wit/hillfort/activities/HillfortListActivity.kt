package org.wit.hillfort.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_hillfort_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast
import org.wit.hillfort.R
import org.wit.hillfort.main.MainApp
import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.models.UserModel
import org.wit.hillfort.views.hillfort.HillfortView

class HillfortListActivity : AppCompatActivity(), HillfortListener {

  lateinit var app: MainApp
  lateinit var user: UserModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hillfort_list)
    app = application as MainApp

    toolbarMain.title = title
    setSupportActionBar(toolbarMain)

    val layoutManager = LinearLayoutManager(this)
    recyclerView.layoutManager = layoutManager

    if(intent.hasExtra("user_session")){
      user = intent.extras.getParcelable<UserModel>("user_session")

      // update user object on activity start
      // So that the updated user object is passed, not the old one the activity is started with
      for (saved_user in app.users.findAllUsers()) {
        if (saved_user.id == user.id){
          user = saved_user
        }
      }

      toolbarMain.title = user.username
      showHillforts(user.hillforts) // show users hillforts
    }

  }

  private fun loadHillforts() {
    showHillforts(app.users.findAllHillforts(user))
  }

  fun showHillforts (hillforts: List<HillfortModel>) {
    recyclerView.adapter = HillfortAdapter(hillforts, this)
    recyclerView.adapter?.notifyDataSetChanged()
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    loadHillforts()
    super.onActivityResult(requestCode, resultCode, data)
  }

  override fun onHillfortClick(hillfort: HillfortModel) {
    startActivityForResult(intentFor<HillfortView>()
        .putExtra("hillfort_edit", hillfort)
        .putExtra("user_session", user), 0)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.item_add -> startActivityForResult(intentFor<HillfortView>().putExtra("user_session", user), 0)
      R.id.item_settings -> startActivityForResult(intentFor<SettingsActivity>().putExtra("user_session", user), 0)
      R.id.item_logout -> {
        startActivityForResult<SigninActivity>(0)
        toast(R.string.toast_logout)
      }
    }
    return super.onOptionsItemSelected(item)
  }

}