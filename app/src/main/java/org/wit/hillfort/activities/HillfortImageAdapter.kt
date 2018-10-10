package org.wit.hillfort.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import org.wit.hillfort.R

class HillfortImageAdapter(private val context: Context,
                           private val images: ArrayList<String>) : BaseAdapter() {

  private val inflater: LayoutInflater
      = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

  override fun getCount(): Int {
    return images.size
  }

  override fun getItem(position: Int): Any {
    return images[position]
  }

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

  override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
    val rowView = inflater.inflate(R.layout.hillfort_image, parent, false)
    val image = rowView.findViewById(R.id.hillfort_image) as ImageView
    Picasso.get().load(images[position]).placeholder(R.mipmap.ic_launcher).into(image)
    return rowView
  }
}