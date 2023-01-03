package com.internalcontentviewer.core

import com.internalcontentviewer.error.StandardError


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

interface ApiResponseCallBack<T> {
    fun onSuccess(response: T)
    fun onFailure(standardError: StandardError)
    fun unAuthorised()
}