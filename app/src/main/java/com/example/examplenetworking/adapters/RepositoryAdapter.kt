package com.example.examplenetworking.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.examplenetworking.R
import com.example.examplenetworking.models.Repository
import com.example.examplenetworking.viewholders.ViewHolder

class RepositoryAdapter (val items: List<Repository>, val context: Context?):
    RecyclerView.Adapter<ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName.text = items[position].name
        holder.txtUrl.text = items[position].url
        if (context != null) {
            Glide.with(context)
                .load(items[position].owner.avatar_url)
                .fitCenter()
                .centerCrop()
                .into(holder.profileImage)
        }
    }
}