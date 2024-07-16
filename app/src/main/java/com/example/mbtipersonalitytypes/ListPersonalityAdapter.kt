package com.example.mbtipersonalitytypes

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListPersonalityAdapter(private val listPersonality: ArrayList<Personality>) : RecyclerView.Adapter<ListPersonalityAdapter.ListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_personality, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val personality = listPersonality[position]
        holder.tvName.text = personality.name
        holder.tvType.text = personality.type
        holder.tvDescription.text = personality.description
        holder.ivPhoto.setImageResource(personality.photo)
        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_personality", listPersonality[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int = listPersonality.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvType: TextView = itemView.findViewById(R.id.tv_item_type)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }
}
