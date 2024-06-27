package com.example.showgallerykotlin

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log

class ImageLoader {


    fun loadImageFromGallery(contentResolver: ContentResolver): List<ImageItem> {
        val imageItems = mutableListOf<ImageItem>()

        // Load Images
        val imageUri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        loadImageFromUri(contentResolver, imageUri, imageItems)

        // Load Videos
        val videoUri: Uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        loadImageFromUri(contentResolver, videoUri, imageItems)

        // Return loaded media items
        return imageItems

    }

//
//    fun loadMediaFromGallery(contentResolver: ContentResolver): List<ImageItem> {
//        val imageItems = mutableListOf<ImageItem>()
//        // Load Images
//        val imageUri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//        loadMediaFromUri(contentResolver, imageUri, imageItems)
//
//        // Load Videos
//        val videoUri: Uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
//        loadMediaFromUri(contentResolver, videoUri, imageItems)
//        return imageItems
//    }
    private fun loadImageFromUri(contentResolver: ContentResolver, uri: Uri, imageItems: MutableList<ImageItem>) {
        val projection = arrayOf(MediaStore.MediaColumns._ID)
        val cursor: Cursor? = contentResolver.query(uri, projection, null, null, null)
        cursor?.use {
            val columnIndexId = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns._ID)
            while (cursor.moveToNext()) {
                val imageId = cursor.getLong(columnIndexId)
                val imageUri = Uri.withAppendedPath(uri, imageId.toString())
                Log.d("QueryFragment", "Media URI: $imageUri")
                imageItems.add(ImageItem(imageUri))
                imageItems.reverse()
            }
        }
    }
}