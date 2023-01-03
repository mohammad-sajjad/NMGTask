package com.internalcontentviewer.app

import android.app.Application
import com.internalcontentviewer.app.AppController
import java.lang.ref.WeakReference


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AppController.init(WeakReference(this))
    }
}