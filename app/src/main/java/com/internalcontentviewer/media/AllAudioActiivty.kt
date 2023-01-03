package com.internalcontentviewer.media

import android.content.ContentValues
import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.internalcontentviewer.R
import com.internalcontentviewer.core.BaseActivity
import com.internalcontentviewer.core.BaseViewModelFactory
import com.internalcontentviewer.core.DataFetchState
import com.internalcontentviewer.core.StateMachine
import com.internalcontentviewer.error.StandardError
import com.internalcontentviewer.media.view.AllAudioItemViewHolder
import kotlinx.android.synthetic.main.activity_all_audio_actiivty.*
import kotlinx.android.synthetic.main.activity_all_images.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllAudioActiivty : BaseActivity(), AllAudioItemViewHolder.AudioItemActionListener {
    private val factory = object : BaseViewModelFactory() {
        override fun createViewModel() = AllMediaViewModel()
    }
    private val viewModel by viewModels<AllMediaViewModel> { factory }
    private val adapter by lazy { AllAudioAdapter(this) }
    private val stateMachine = StateMachine()
    private var player: MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_audio_actiivty)
        setupToolbar(ToolbarConfig("All images", true, {
            onBackPressed()
        }))

        init()
    }

    private fun init() {
        Log.e(ContentValues.TAG, "init: In")
        all_audio_item_recyclerView.layoutManager = LinearLayoutManager(this)
        all_audio_item_recyclerView.adapter = adapter

        stateMachine.observe(this) {
            when (it) {
                is DataFetchState.Loading -> loading()
                is DataFetchState.Success -> success()
                is DataFetchState.Error -> error(it.error)
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getAllAudio(stateMachine)
        }

    }


    private fun loading() {
        audioPd.visibility = View.VISIBLE
        Log.e(ContentValues.TAG, "loading: ")
    }

    private fun success() {
        setAdapter()
        audioPd.visibility = View.GONE

        Log.e(ContentValues.TAG, "success: ")
    }

    private fun error(standardError: StandardError) {
        audioPd.visibility = View.GONE
        Toast.makeText(this, standardError.displayError, Toast.LENGTH_SHORT).show()
        Log.e(ContentValues.TAG, "error: ${standardError.displayError}")
    }

    private fun setAdapter() {
        adapter.updateData(viewModel.audioItems)
    }


    override fun onAudioPlay(uri: Uri) {
        playAudio(uri)
    }

    override fun stopAudio() {
        if (player != null) {
            player?.stop()
            player?.release()

        }
    }

    private fun playAudio(uri: Uri) {
        if (player == null) {
            player = MediaPlayer()
            try {
                val file: AssetFileDescriptor = getContentResolver().openAssetFileDescriptor(uri, "r")!!
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    player?.setDataSource(file)
                } else {
                    player?.setDataSource(uri.path)
                }
                player?.isLooping = false
                player?.prepare()
                player?.start()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            player?.release()
            player = MediaPlayer()
            try {
                val file: AssetFileDescriptor =
                    getContentResolver().openAssetFileDescriptor(uri, "r")!!
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    player?.setDataSource(file)
                } else {
                    player?.setDataSource(uri.path)
                }
                player?.setLooping(false)
                player?.prepare()
                player?.start()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}