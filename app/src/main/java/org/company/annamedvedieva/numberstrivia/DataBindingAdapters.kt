package org.company.annamedvedieva.numberstrivia

import android.widget.ImageView
import androidx.databinding.BindingAdapter


    @BindingAdapter("app:source")
    fun setImageResource(imageView: ImageView, resource: Int) {
        imageView.setImageResource(resource)
    }

