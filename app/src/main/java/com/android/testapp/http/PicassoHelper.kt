package com.android.testapp.http

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso

object PicassoHelper {

    /**
     * Object implements simplest case of Picasso lib usage
     */
    fun draw(
        context: Context?,
        view: ImageView?,
        url: String?
    ) {
        Picasso.get()
            .load(url)
            .into(view)
    }
}