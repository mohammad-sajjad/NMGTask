package com.internalcontentviewer.core

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import com.internalcontentviewer.R
import java.util.*


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

abstract class BaseActivity: AppCompatActivity() {
    private lateinit var mTextViewScreenTitle: TextView
    private lateinit var mImageButtonBack: ImageButton
    private lateinit var progressDialog: Dialog
    private lateinit var fakeToolbar : RelativeLayout
    private lateinit var menuMoreBtn: ImageButton
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    }

    override fun setContentView(layoutResID: Int) {
        val coordinatorLayout: CoordinatorLayout = layoutInflater.inflate(R.layout.base_activity, null) as CoordinatorLayout
        val activityContainer: FrameLayout = coordinatorLayout.findViewById(R.id.layout_container)
        mTextViewScreenTitle = coordinatorLayout.findViewById(R.id.text_screen_title) as TextView
        mImageButtonBack = coordinatorLayout.findViewById(R.id.image_back_button)
        menuMoreBtn = coordinatorLayout.findViewById(R.id.image_more_btn)
        progressDialog = Dialog(this@BaseActivity)
        toolbar = coordinatorLayout.findViewById(R.id.base_toolbar)
        layoutInflater.inflate(layoutResID, activityContainer, true)
        fakeToolbar = coordinatorLayout.findViewById(R.id.dummy_base_toolbar) as RelativeLayout
        toolbar.visibility = if(showToolbar()) View.VISIBLE else View.GONE
        super.setContentView(coordinatorLayout)
    }

    open fun showToolbar(): Boolean = true

    fun setupToolbar(toolbarConfig: ToolbarConfig) {
        mTextViewScreenTitle.text = toolbarConfig.title
        mImageButtonBack.setOnClickListener(toolbarConfig.onClick)
        mImageButtonBack.visibility = if(toolbarConfig.showBackButton) View.VISIBLE else View.GONE
        menuMoreBtn.visibility = if(toolbarConfig.showMenuButton) View.VISIBLE else View.GONE
        if(toolbarConfig.menuBtnConfig?.iconSrc != null) {
            menuMoreBtn.setImageDrawable(resources.getDrawable(toolbarConfig.menuBtnConfig?.iconSrc!!))
        }

        menuMoreBtn.setOnClickListener(toolbarConfig.menuBtnConfig?.onClick)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()


    }

    override fun onStop() {
        super.onStop()
    }

    override fun onResume() {
        super.onResume()
    }

    open fun showLoader(title:String = "Please Wait...") {

    }

    open fun dismissLoader() {

    }

    data class ToolbarMenuConfig(val iconSrc: Int,
                                 val onClick: View.OnClickListener)
    data class ToolbarConfig(val title: String = "",
                             var showBackButton: Boolean = true,
                             var onClick: View.OnClickListener? = null,
                             var showMenuButton: Boolean = false,
                             var menuBtnConfig: ToolbarMenuConfig? = null)
}
