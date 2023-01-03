package com.internalcontentviewer.core

import com.internalcontentviewer.error.StandardError


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

sealed class DataFetchState {
    object Loading: DataFetchState()
    class Success(val message: String? = null): DataFetchState()
    class Error(val error: StandardError): DataFetchState()
}