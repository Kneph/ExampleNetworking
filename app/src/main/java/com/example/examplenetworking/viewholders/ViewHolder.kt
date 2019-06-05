package com.example.examplenetworking.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    val txtName = v.name
    val txtUrl = v.url
    val profileImage = v.profile_image
}