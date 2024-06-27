package com.example.showgallerykotlin

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.showgallerykotlin.fragment.HomeFragment


class CheckPermission(private val context: Context, private val callback: PermissionCallback) {
    interface PermissionCallback {
        fun onPermissionsGranted()
        fun onPermissionsDenied()
    }
    private val requestPermissionLauncher = (context as ComponentActivity).registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        handlePermissionResults(permissions)
    }



//    private val requestPermissionLauncher = (context as Fragment).registerForActivityResult(
//        ActivityResultContracts.RequestMultiplePermissions()
//    ) { permissions ->
//        handlePermissionResults(permissions)
//    }

        private fun handlePermissionResults(permissions: Map<String, Boolean>) {
        if (permissions[Manifest.permission.READ_EXTERNAL_STORAGE] == true ||
            permissions[Manifest.permission.READ_MEDIA_IMAGES] == true ||
            permissions[Manifest.permission.READ_MEDIA_VIDEO] == true ||
            permissions[Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED] == true
        ) {
            callback.onPermissionsGranted()

            //
        } else {
            // Handle permission denied
            // Log or show error message
            callback.onPermissionsDenied()

            //
        }
    }
//    private fun handlePermissionResults(permissions: Map<String, Boolean>) {
//        val allGranted = permissions.values.all { it }
//        if (allGranted) {
//            permissionCallback?.onPermissionGranted()
//        } else {
//            permissionCallback?.onPermissionDenied()
//        }
//    }

    private fun getPermissionsToRequest(): Array<String> {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE -> arrayOf(
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO,
                Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
            )

            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> arrayOf(
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO
            )

            else -> arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    //kiểm tra xem các quyền có được cấp hay chưa và yêu cầu nếu cần thiết.
    fun checkAndRequestPermissions(): Boolean {
        val permissionsToRequest = getPermissionsToRequest()
        return if (permissionsToRequest.any {
                ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED
            }) {
            requestPermissionLauncher.launch(permissionsToRequest)
            false // Permissions need to be requested
        } else {
            callback.onPermissionsGranted()
            true // Permissions already granted
        }

    }



}

//
//class CheckPermission(private val context: Context, private val callback: PermissionCallback) {
//    interface PermissionCallback {
//        fun onPermissionsGranted()
//        fun onPermissionsDenied()
//    }
//
//    private val requestPermissionLauncher = (context as ComponentActivity).registerForActivityResult(
//        ActivityResultContracts.RequestMultiplePermissions()
//    ) { permissions ->
//        handlePermissionResults(permissions)
//    }
//
//    private fun handlePermissionResults(permissions: Map<String, Boolean>) {
//        if (permissions[Manifest.permission.READ_EXTERNAL_STORAGE] == true) {
//            callback.onPermissionsGranted()
//        } else {
//            callback.onPermissionsDenied()
//        }
//    }
//
//    fun checkAndRequestPermissions(): Boolean {
//        val permissionsToRequest = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
//        return if (permissionsToRequest.any {
//                ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED
//            }) {
//            requestPermissionLauncher.launch(permissionsToRequest)
//            false // Permissions need to be requested
//        } else {
//            callback.onPermissionsGranted()
//            true // Permissions already granted
//        }
//    }
//}
