package com.example.examplenetworking.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.examplenetworking.R
import com.example.examplenetworking.models.Repository
import kotlinx.android.synthetic.main.list_item.view.*

class RepositoryAdapter(val items: List<Repository>, private val clickListener: (Repository) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // LayoutInflater: takes ID from layout defined in XML.
        // Instantiates the layout XML into corresponding View objects.
        // Use context from main app -> also supplies theme layout values!
        val inflater = LayoutInflater.from(parent.context)
        // Inflate XML. Last parameter: don't immediately attach new view to the parent view group
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Populate ViewHolder with data that corresponds to the position in the list
        // which we are told to load
        (holder as RepositoryViewHolder).bind(items[position], clickListener)
    }

    class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(repositoryData: Repository, clickListener: (Repository) -> Unit) {
            itemView.name.text = repositoryData.name
            itemView.url.text = repositoryData.url
            Glide.with(itemView.context)
                .load(repositoryData.owner.avatar_url)
                .fitCenter()
                .centerCrop()
                .into(itemView.profile_image)
            itemView.setOnClickListener { clickListener(repositoryData) }
        }
    }
}
