package com.internalcontentviewer.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

abstract class BaseViewModelFactory: ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val vm = createViewModel()
        if (modelClass.isAssignableFrom(vm::class.java)) return vm as T
        throw IllegalArgumentException("${vm::class.java} is not a valid ViewModel")

    }

    abstract fun createViewModel(): BaseViewModel
}