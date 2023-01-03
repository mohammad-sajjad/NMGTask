package com.internalcontentviewer.core

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import com.internalcontentviewer.R


/**
 * Created by Mohammad Sajjad on 30-12-2022.
 * Email mohammadsajjad679@gmail.com
 */

abstract class BaseActivity: AppCompatActivity() {

    private lateinit var mTextViewScreenTitle: TextView
    private lateinit var mImageBack: ImageView
    private lateinit var fakeToolbar : RelativeLayout
    private lateinit var menuMoreBtn: ImageView
    private lateinit var toolbar: Toolbar
//    private val progressDialog = CustomProgressDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // FullScreen
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView)

        windowInsetsController?.isAppearanceLightNavigationBars = true



    }

    override fun setContentView(layoutResID: Int) {
        val coordinatorLayout: CoordinatorLayout = layoutInflater.inflate(R.layout.base_activity, null) as CoordinatorLayout
        val activityContainer: FrameLayout = coordinatorLayout.findViewById(R.id.layout_container)
        mTextViewScreenTitle = coordinatorLayout.findViewById(R.id.text_screen_title) as TextView
        mImageBack = coordinatorLayout.findViewById(R.id.image_back_button)
        menuMoreBtn = coordinatorLayout.findViewById(R.id.image_more_btn)


        toolbar = coordinatorLayout.findViewById(R.id.base_toolbar)
        layoutInflater.inflate(layoutResID, activityContainer, true)
        fakeToolbar = coordinatorLayout.findViewById(R.id.dummy_base_toolbar) as RelativeLayout
        toolbar.visibility = if(showToolbar()) View.VISIBLE else View.GONE
        super.setContentView(coordinatorLayout)
    }

    open fun showToolbar(): Boolean = true

    fun setupToolbar(toolbarConfig: ToolbarConfig) {
        mTextViewScreenTitle.text = toolbarConfig.title
        mImageBack.setOnClickListener(toolbarConfig.onClick)
        mImageBack.visibility = if(toolbarConfig.showBackButton) View.VISIBLE else View.GONE
        menuMoreBtn.visibility = if(toolbarConfig.showMenuButton) View.VISIBLE else View.GONE
        if(toolbarConfig.menuBtnConfig?.iconSrc != null) {
            menuMoreBtn.setImageResource(toolbarConfig.menuBtnConfig?.iconSrc!!)
        }

        menuMoreBtn.setOnClickListener(toolbarConfig.menuBtnConfig?.onClick)
    }

    open fun showLoader(title: String? = null) {
//        progressDialog.show(this, title )
//        progressDialog.dialog?.setCancelable(false)
    }

    open fun dismissLoader() {
//        if (progressDialog.dialog != null) {
//            progressDialog.dialog!!.dismiss()
//        }

    }


    override fun onPause() {
        super.onPause()
//        if (progressDialog.dialog != null) {
//            progressDialog.dialog!!.dismiss()
//        }

    }

    /**
     * Showing Alert Dialog with Settings option
     * Navigates user to app settings
     * NOTE: Keep proper title and message depending on your app
     */
    fun showSettingsDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.dialog_permission_title))
        builder.setMessage(getString(R.string.dialog_permission_message))
        builder.setPositiveButton(getString(R.string.go_to_settings)) { dialog, which ->
            dialog.cancel()
            openSettings()
        }
        builder.setNegativeButton(getString(android.R.string.cancel)) { dialog, which -> dialog.cancel() }
        builder.show()
    }

    fun checkIsPermissionGranted(permission: String):  Boolean {
        val result = ContextCompat.checkSelfPermission(this, permission)
        return result == PackageManager.PERMISSION_GRANTED
    }

    fun requestRequiredPermission(permission: String) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            openSettings()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                101
            )
        }
    }

    // navigating user to app settings
    fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", this.packageName, null)
        intent.data = uri
        startActivityForResult(intent, 101)
    }

    data class ToolbarMenuConfig(val iconSrc: Int,
                                 val onClick: View.OnClickListener)
    data class ToolbarConfig(val title: String = "",
                             var showBackButton: Boolean = true,
                             var onClick: View.OnClickListener? = null,
                             var showMenuButton: Boolean = false,
                             var menuBtnConfig: ToolbarMenuConfig? = null)
}