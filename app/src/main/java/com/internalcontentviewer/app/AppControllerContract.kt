package com.internalcontentviewer.app

import android.content.Context
import com.internalcontentviewer.app.AppController


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

interface AppControllerContract {


    companion object {
        fun get() = AppController()
    }


    fun getProperContext(): Context
}