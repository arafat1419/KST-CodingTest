package com.arafat1419.codingtest.core.utils

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import androidx.recyclerview.widget.RecyclerView
import com.avatarfirst.avatargenlib.AvatarGenerator

// Set recycler view
fun setRecyclerView(
    recyclerView: RecyclerView,
    layoutManager: RecyclerView.LayoutManager,
    adapter: RecyclerView.Adapter<*>
) {
    recyclerView.layoutManager = layoutManager
    recyclerView.adapter = adapter
}

// Set first letter from name when image empty
fun setAvatarGenerator(context: Context, label: String): BitmapDrawable =
    AvatarGenerator.AvatarBuilder(context)
        .setLabel(label)
        .setAvatarSize(100)
        .setTextSize(20)
        .setBackgroundColor(com.arafat1419.codingtest.assets.R.color.grey)
        .toCircle()
        .build()