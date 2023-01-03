package com.internalcontentviewer.media

import android.provider.MediaStore
import androidx.lifecycle.viewModelScope
import com.internalcontentviewer.core.BaseViewModel
import com.internalcontentviewer.core.DataFetchState
import com.internalcontentviewer.core.RecyclerViewListItem
import com.internalcontentviewer.core.StateMachine
import com.internalcontentviewer.error.StandardError
import com.internalcontentviewer.provider.MediaProvider
import kotlinx.coroutines.launch


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

class AllMediaViewModel: BaseViewModel() {
    val externalContentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    val externalVideoContentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
    val externalAudioContentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
    val items = mutableListOf<RecyclerViewListItem>()
    val audioItems = mutableListOf<RecyclerViewListItem>()
    val videoItems = mutableListOf<RecyclerViewListItem>()


    fun getAllImages(stateMachine: StateMachine) {
            stateMachine.postValue(DataFetchState.Loading)
            val data = MediaProvider.getAllPictureContents(externalContentUri)
            if (data.isNotEmpty() ) {
                for (i in data) {
                    items.add(i)
                }
                stateMachine.postValue(DataFetchState.Success())
            } else {
                stateMachine.postValue(DataFetchState.Error(StandardError("No data found")))
            }
    }

    fun getAllAudio(stateMachine: StateMachine) {
            stateMachine.postValue(DataFetchState.Loading)
            val data = MediaProvider.getAllAudioContent(externalAudioContentUri)
            if (data.isNotEmpty() ) {
                data.map { audioItems.add(it) }
                stateMachine.postValue(DataFetchState.Success())
            } else {
                stateMachine.postValue(DataFetchState.Error(StandardError("No data found")))
            }
    }
    fun getAllVideos(stateMachine: StateMachine) {
        viewModelScope.launch {
            stateMachine.postValue(DataFetchState.Loading)
            val data = MediaProvider.getAllVideoContent(externalVideoContentUri)
            if (data.isNotEmpty() ) {
                data.map { videoItems.add(it) }
                stateMachine.postValue(DataFetchState.Success())
            } else {
                stateMachine.postValue(DataFetchState.Error(StandardError("No data found")))
            }
        }
    }



}