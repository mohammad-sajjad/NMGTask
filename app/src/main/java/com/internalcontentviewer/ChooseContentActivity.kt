package com.internalcontentviewer

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.internalcontentviewer.core.BaseActivity
import com.internalcontentviewer.media.AllAudioActiivty
import com.internalcontentviewer.media.AllImagesActivity
import com.internalcontentviewer.media.AllVideosActivity
import com.internalcontentviewer.provider.PermissionManager
import com.internalcontentviewer.provider.Permissions
import kotlinx.android.synthetic.main.activity_choose_content.*
import java.security.Permission

class ChooseContentActivity : BaseActivity() {
    var TAG = "MainActivity"
    private val permissionManager = PermissionManager.from(this)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_content)

        init()
        listner()
    }

    private fun init() {

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun listner() {
        ImagesBtn.setOnClickListener {

            permissionManager.request(Permissions.Storage)
                .rationale("We require some permission to show data")
                .checkPermission {
                    if (it) {
                        startActivity(Intent(this@ChooseContentActivity, AllImagesActivity::class.java))
                    } else {
                        Toast.makeText(this, "Please grant Permission", Toast.LENGTH_SHORT).show()
                    }
                }

        }
        VideoBtn.setOnClickListener {
            permissionManager.request(Permissions.Storage)
                .rationale("We require some permission to show data")
                .checkPermission {
                    if (it) {
                        startActivity(Intent(this@ChooseContentActivity, AllVideosActivity::class.java))
                    } else {
                        Toast.makeText(this, "Please grant Permission", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        AudioBtn.setOnClickListener {
            permissionManager.request(Permissions.Storage)
                .rationale("We require some permission to show data")
                .checkPermission {
                    if (it) {
                        startActivity(Intent(this@ChooseContentActivity, AllAudioActiivty::class.java))
                    } else {
                        Toast.makeText(this, "Please grant Permission", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }


}