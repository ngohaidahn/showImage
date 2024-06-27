package com.example.showgallerykotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.showgallerykotlin.ImageItem
import com.example.showgallerykotlin.R

class GalleryAdapter(
    private val imageItems: List<ImageItem>,
    private val onItemClick: (ImageItem) -> Unit

): RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView: ImageView= itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageItem = imageItems[position]

        Glide.with(holder.itemView.context)
            .load(imageItem.uri)
//            .placeholder(R.drawable.loading)
            .into(holder.imageView)
        holder.imageView.setOnClickListener(){
            onItemClick(imageItem)
        }
    }
}