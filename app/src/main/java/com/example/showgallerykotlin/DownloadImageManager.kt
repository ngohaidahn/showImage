package com.example.showgallerykotlin
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
//
//class DownloadImageManager(private val context: Context) {
//
//    interface DownloadImageCallback {
//        fun onDownloadSuccess()
//        fun onDownloadFailed(errorMessage: String)
//    }
//
//    fun downloadImage(imageView: ImageView, callback: DownloadImageCallback) {
//        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
//        val resolver: ContentResolver = context.contentResolver
//        val imageCollection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
//        } else {
//            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//        }
//
//        val newImage = ContentValues().apply {
//            put(MediaStore.Images.Media.DISPLAY_NAME, "DownloadedImage_${System.currentTimeMillis()}.jpg")
//            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                put(MediaStore.Images.Media.IS_PENDING, 1)
//            }
//        }
//
//        val imageUri = resolver.insert(imageCollection, newImage)
//
//        imageUri?.let {
//            try {
//                resolver.openOutputStream(it)?.use { outputStream ->
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
//                }
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                    newImage.clear()
//                    newImage.put(MediaStore.Images.Media.IS_PENDING, 0)
//                    resolver.update(it, newImage, null, null)
//                }
//
//                callback.onDownloadSuccess()
//            } catch (e: Exception) {
//                callback.onDownloadFailed(e.message ?: "Unknown error")
//            }
//        } ?: run {
//            callback.onDownloadFailed("Failed to create image")
//        }
//    }
//}
class DownloadImageManager(private val context: Context) {

    interface DownloadImageCallback {
        fun onDownloadSuccess()
        fun onDownloadFailed(errorMessage: String)
    }

    fun downloadImage(imageView: ImageView, callback: DownloadImageCallback) {
        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        val resolver: ContentResolver = context.contentResolver
        val imageCollection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

        val newImage = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "DownloadedImage_${System.currentTimeMillis()}.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.Images.Media.IS_PENDING, 1)
            }
        }

        val imageUri = resolver.insert(imageCollection, newImage)

        imageUri?.let {
            try {
                resolver.openOutputStream(it)?.use { outputStream ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    newImage.clear()
                    newImage.put(MediaStore.Images.Media.IS_PENDING, 0)
                    resolver.update(it, newImage, null, null)
                }

                callback.onDownloadSuccess()
            } catch (e: Exception) {
                callback.onDownloadFailed(e.message ?: "Unknown error")
            }
        } ?: run {
            callback.onDownloadFailed("Failed to create image")
        }
    }
}
