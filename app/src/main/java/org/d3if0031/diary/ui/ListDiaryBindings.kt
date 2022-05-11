package org.d3if0031.diary.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:load")
fun imageViewLoadUrl(imageView: ImageView, drawable: Int?) {
    if (drawable == null) return
    imageView.setImageResource(drawable)
}