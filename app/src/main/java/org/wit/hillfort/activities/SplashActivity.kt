package org.wit.hillfort.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import org.wit.hillfort.R
import org.wit.hillfort.views.signin.SigninView

class SplashActivity : AppCompatActivity() {

  val wait:Long = 3000 // 3 sec wait

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.requestFeature(Window.FEATURE_NO_TITLE) // hiding title bar of this activity
    window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN) // making this activity full screen
    setContentView(R.layout.activity_splash)

    // Start this activity and wait to start Main Activity
    Handler().postDelayed({
      startActivity(Intent(this, SigninView::class.java)) // start main activity
      finish() // finish this activity
    }, wait)

  }
}