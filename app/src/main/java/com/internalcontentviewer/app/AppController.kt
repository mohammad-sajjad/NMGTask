package com.internalcontentviewer.app

import android.content.Context
import com.internalcontentviewer.error.AppContextGoneException
import java.lang.ref.WeakReference


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

open class AppController: AppControllerContract {

    companion object {
        private var instance: AppController? = null
        private var context: WeakReference<Context>? = null

        fun init (context: WeakReference<Context>): AppController {
            Companion.context = context
            instance = AppController()
            return instance as AppController


        }
        fun get(): AppController = instance ?: throw AppContextGoneException()


    }

    override fun getProperContext(): Context {
        val localContext = context?.get()
        return localContext ?: throw AppContextGoneException()
    }

}