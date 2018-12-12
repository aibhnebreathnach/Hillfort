package org.wit.hillfort.views.hillfortlist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_hillfort.view.*
import org.wit.hillfort.R
import org.wit.hillfort.models.HillfortModel

interface HillfortListener {
  fun onHillfortClick(hillfort: HillfortModel)
}

class HillfortAdapter constructor(private var hillforts: List<HillfortModel>,
                                   private val listener: HillfortListener) : RecyclerView.Adapter<HillfortAdapter.MainHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
    return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_hillfort, parent, false))
  }

  override fun onBindViewHolder(holder: MainHolder, position: Int) {
    val hillfort = hillforts[holder.adapterPosition]
    holder.bind(hillfort, listener)
  }

  override fun getItemCount(): Int = hillforts.size

  class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(hillfort: HillfortModel,  listener : HillfortListener) {
      itemView.hillfortCardTitle.text = hillfort.title
      itemView.description.text = hillfort.description
      itemView.hillfort_visited.setChecked(hillfort.visited)
      itemView.hillfort_favorited.setChecked(hillfort.favorite)

      if (hillfort.images.isEmpty()) {
        Picasso.get()
            .load(R.mipmap.ic_launcher_round)
            .resize(1000, 1000)
            .centerInside()
            .into(itemView.hillfortCardImage)
      } else {
        Picasso.get()
            .load(hillfort.images[0]) // only load first image of list
            .resize(750, 750)
            .centerInside()
            .into(itemView.hillfortCardImage)
      }

      itemView.setOnClickListener { listener.onHillfortClick(hillfort) }
    }
  }
}