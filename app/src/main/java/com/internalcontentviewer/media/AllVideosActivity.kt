package com.internalcontentviewer.media

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.internalcontentviewer.R
import com.internalcontentviewer.adapter.AllImageAdapter
import com.internalcontentviewer.core.BaseActivity
import com.internalcontentviewer.core.BaseViewModelFactory
import com.internalcontentviewer.core.DataFetchState
import com.internalcontentviewer.core.StateMachine
import com.internalcontentviewer.error.StandardError
import kotlinx.android.synthetic.main.activity_all_images.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllVideosActivity : BaseActivity() {
    private val factory = object : BaseViewModelFactory() {
        override fun createViewModel() = AllMediaViewModel()
    }
    private val viewModel by viewModels<AllMediaViewModel> { factory }
    private val adapter by lazy { MediaPlayerAdapter() }
    private val stateMachine = StateMachine()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_images)
        setupToolbar(ToolbarConfig("All images", true, {
            onBackPressed()
        }))

        init()
    }


    private fun init() {
        Log.e(ContentValues.TAG, "init: In")
        all_media_item_recyclerView.layoutManager = GridLayoutManager(this, calculateNoOfColumns(this, 90))
        all_media_item_recyclerView.adapter = adapter

        stateMachine.observe(this) {
            when (it) {
                is DataFetchState.Loading -> loading()
                is DataFetchState.Success -> success()
                is DataFetchState.Error -> error(it.error)
            }
        }
        CoroutineScope(Dispatchers.Default).launch {
            viewModel.getAllVideos(stateMachine)
        }

    }

    private fun loading() {
        pd.visibility = View.VISIBLE
        Log.e(ContentValues.TAG, "loading: " )
    }

    private fun success(){
        setAdapter()
        pd.visibility = View.GONE

        Log.e(ContentValues.TAG, "success: " )
    }

    private fun error(standardError: StandardError) {
        pd.visibility = View.GONE
        Toast.makeText(this, standardError.displayError, Toast.LENGTH_SHORT).show()
        Log.e(ContentValues.TAG, "error: ${standardError.displayError}" )
    }

    private fun setAdapter() {
        adapter.updateData(viewModel.videoItems)
    }

    private fun calculateNoOfColumns(
        context: Context,
        columnWidthDp: Int
    ): Int { // For example columnWidthdp=180
        val displayMetrics = context.resources.displayMetrics
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        return (screenWidthDp / columnWidthDp + 0.5).toInt()
    }
}