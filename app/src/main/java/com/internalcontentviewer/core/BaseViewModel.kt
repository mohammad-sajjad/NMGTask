package com.internalcontentviewer.core

import androidx.lifecycle.ViewModel
import com.internalcontentviewer.app.AppControllerContract


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

abstract class BaseViewModel(val appControllerContract: AppControllerContract = AppControllerContract.get()): ViewModel()
