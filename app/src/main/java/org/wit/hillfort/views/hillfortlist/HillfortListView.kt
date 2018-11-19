package org.wit.hillfort.views.hillfortlist

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_hillfort_list.*
import org.jetbrains.anko.intentFor
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

    if(intent.hasExtra("user_session")){
      presenter.user = intent.extras.getParcelable<UserModel>("user_session")

      // update user object on activity start
      // So that the updated user object is passed, not the old one the activity is started with
//      for (saved_user in presenter.users.findAllUsers()) {
//        if (saved_user.id == user.id){
//          user = saved_user
//        }
//      }

      toolbarMain.title = presenter.user.username
    }

    val layoutManager = LinearLayoutManager(this)
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
    startActivityForResult(intentFor<HillfortView>()
        .putExtra("hillfort_edit", hillfort)
        .putExtra("user_session", presenter.user), 0)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.item_add -> { presenter.doAddHillfort() }
      R.id.item_settings -> { presenter.doSettings() }
      R.id.item_logout -> { presenter.doLogout() }
    }
    return super.onOptionsItemSelected(item)
  }

}