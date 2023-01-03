package com.internalcontentviewer.error

import java.io.IOException


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

class NoConnectivityException : IOException() {
    // You can send any message whatever you want from here.
    override val message: String
        get() = "No Internet Connection"
    // You can send any message whatever you want from here.
}