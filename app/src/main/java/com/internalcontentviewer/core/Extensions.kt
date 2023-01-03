package com.internalcontentviewer.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

object Extensions {

    fun ViewGroup.inflate(resId: Int): View {
        return LayoutInflater.from(context).inflate(resId, this, false)
    }

}