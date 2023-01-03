package com.internalcontentviewer.provider

import android.content.ContentValues.TAG
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.internalcontentviewer.app.AppControllerContract
import com.internalcontentviewer.media.model.AllAudioListItem
import com.internalcontentviewer.media.model.MediaListItems
import com.internaldatapicker.media.model.MediaPlayItems
import kotlin.math.log

object MediaProvider {

    var Selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"

    private val Projections = arrayOf(
        MediaStore.Images.Media.DATA,
        MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.SIZE,
        MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
        MediaStore.Images.Media.BUCKET_ID,
        MediaStore.Images.Media._ID,
        MediaStore.Images.Media.DATE_TAKEN
    )

    private val AudioProjections = arrayOf(
        MediaStore.Audio.Media.TITLE,
        MediaStore.Audio.Media.DISPLAY_NAME,
        MediaStore.Audio.Media.ARTIST,
        MediaStore.Audio.Media.COMPOSER,
        MediaStore.Audio.Media.SIZE,
        MediaStore.Audio.Media._ID,
        MediaStore.Audio.Media.DATA,
        MediaStore.Audio.Media.DURATION,
    )


    var videoProjections = arrayOf(
        MediaStore.Video.Media.DATA,
        MediaStore.Video.Media.DISPLAY_NAME,
        MediaStore.Video.Media.DURATION,
        MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
        MediaStore.Video.Media.BUCKET_ID,
        MediaStore.Video.Media.SIZE,
        MediaStore.Video.Media._ID,
        MediaStore.Video.Media.ALBUM,
        MediaStore.Video.Media.DATE_TAKEN,
        MediaStore.Video.Media.ARTIST
    )

    fun getAllPictureContents(contentLocation: Uri?): ArrayList<MediaListItems> {
        val images: ArrayList<MediaListItems> = ArrayList<MediaListItems>()
        val cursor = AppControllerContract.get().getProperContext().contentResolver.query(
            contentLocation!!, Projections, null, null,
            "LOWER (" + MediaStore.Images.Media.DATE_TAKEN + ") DESC"
        )

        if (cursor == null) {
            Log.e(TAG, "getAllPictureContents: no data" )
            return images
        }

        try {
            cursor!!.moveToFirst()
            do {
                val name = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                        MediaStore.Images.Media.DISPLAY_NAME))
//                val path = cursor.getString(
//                    cursor.getColumnIndexOrThrow(
//                        MediaStore.Images.Media.DATA))
//
                val id = cursor.getInt(
                    cursor.getColumnIndexOrThrow(
                        MediaStore.Images.Media._ID))
                val contentUri = Uri.withAppendedPath(contentLocation, id.toString())

                images.add(MediaListItems(id.toString(), name, contentUri))
                Log.e(TAG, "getAllPictureContents: $images")
            } while (cursor.moveToNext())
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return images
    }

    fun getAllAudioContent(contentLocation: Uri?): MutableList<AllAudioListItem> {
        val allAudioContent: ArrayList<AllAudioListItem> = ArrayList<AllAudioListItem>()
        val cursor = AppControllerContract.get().getProperContext().contentResolver.query(
            contentLocation!!, AudioProjections, Selection,
            null,
            "LOWER (" + MediaStore.Audio.Media.TITLE + ") ASC"
        ) //"LOWER ("+MediaStore.Audio.Media.TITLE + ") ASC"
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val name =  cursor.getString(
                        cursor.getColumnIndexOrThrow(
                            MediaStore.Audio.Media.DISPLAY_NAME
                        )
                    )
                    val title: String =
                        cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                MediaStore.Audio.Media.TITLE
                            )
                        )
                    val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID))
                    val contentUri = Uri.withAppendedPath(contentLocation, id.toString())
                    val path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))
                    val size = cursor.getLong(
                        cursor.getColumnIndexOrThrow(
                            MediaStore.Audio.Media.SIZE
                        )
                    )
                    val duration: String = cursor.getLong(
                        cursor.getColumnIndexOrThrow(
                            MediaStore.Audio.Media.DURATION
                        )
                    ).toString()

                    val album_id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID))
                    val sArtworkUri = Uri.parse("content://media/external/audio/albumart")
                    val imageUri = Uri.withAppendedPath(sArtworkUri, album_id.toString())

                    allAudioContent.add(
                        AllAudioListItem(id.toString(), title, size.toString(), path, contentUri, duration)
                    )
                } while (cursor.moveToNext())
            }
            cursor.close()
            Log.e(TAG, "getAllAudioContent: $allAudioContent" )
            Log.e(TAG, "getAllAudioContent: ${allAudioContent.size}" )
        }
        return allAudioContent
    }

    fun getAllVideoContent(contentLocation: Uri): MutableList<MediaPlayItems> {
        val allVideo = mutableListOf<MediaPlayItems>()
        val cursor = AppControllerContract.get().getProperContext().contentResolver.query(
            contentLocation,
            videoProjections,
            null,
            null,
            "LOWER (" + MediaStore.Video.Media.DATE_TAKEN + ") DESC"
        ) //DESC ASC
       if (cursor != null) {
           try {
               cursor.moveToFirst()

               do {
                   cursor.run {
                       val name = this.getString(this.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME))
                       val path =  this.getLong(this.getColumnIndexOrThrow(MediaStore.Video.Media.DATA))
                       val duration =  this.getLong(this.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION))
                       val size =  this.getLong(this.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE))
                       val id =  this.getLong(this.getColumnIndexOrThrow(MediaStore.Video.Media._ID))
                       val contentUri = Uri.withAppendedPath(contentLocation, id.toString())

                       allVideo.add(MediaPlayItems(id.toString(), name, size.toString(), path.toString(), contentUri, duration.toString()))
                       Log.e(TAG, "getAllVideoContent: $allVideo", )
                   }

               } while (cursor.moveToNext())
               cursor.close()
           } catch (e: java.lang.Exception) {
               e.printStackTrace()
           }
       } else {
           Log.e(TAG, "getAllVideoContent: No data found", )
       }

        return allVideo
    }


}