package com.internalcontentviewer.error


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

class AppContextGoneException: Exception("App context is null, try calling init function of the implementing class") {
}