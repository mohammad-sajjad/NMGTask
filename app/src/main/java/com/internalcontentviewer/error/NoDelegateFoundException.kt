package com.internalcontentviewer.error


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

class NoDelegateFoundException(private val item: Int, private val errorClass: String) : Exception() {
    override val message: String
        get() = "No delegate found for the view type : $item in $errorClass"
}