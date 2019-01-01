package org.wit.hillfort.views.hillfort

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_hillfort.view.*
import org.wit.hillfort.R

interface HillfortImageListener {
  fun onHillfortImageClick(image: String)
}

class HillfortImageAdapter constructor(private var images: List<String>, private val listener: HillfortImageListener) : androidx.recyclerview.widget.RecyclerView.Adapter<HillfortImageAdapter.MainHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
    return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.image_hillfort, parent, false))
  }

  override fun onBindViewHolder(holder: MainHolder, position: Int) {
    val image = images[holder.adapterPosition]
    holder.bind(image, listener)
  }

  override fun getItemCount(): Int = images.size

  class MainHolder constructor(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    fun bind(image: String, listener: HillfortImageListener) {

      Picasso.get()
          .load(image)
          .resize(1000,600)
          .into(itemView.hillfortImage)

      itemView.setOnClickListener { listener.onHillfortImageClick(image) }

    }
  }
}