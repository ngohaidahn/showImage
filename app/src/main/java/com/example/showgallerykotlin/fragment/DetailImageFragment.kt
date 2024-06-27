package com.example.showgallerykotlin.fragment


import android.Manifest
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.showgallerykotlin.DownloadImageManager
import com.example.showgallerykotlin.R

//
//class DetailImageFragment : Fragment() {
//    private lateinit var imageView: ImageView
//    private lateinit var downloadButton: Button
//    private lateinit var imageUri: Uri
//    private lateinit var downloadImageManager: DownloadImageManager
//
//    companion object {
//        private const val ARG_IMAGE_URI = "image_uri"
//        private const val REQUEST_WRITE_PERMISSION = 1
//
//        fun newInstance(imageUri: String): DetailImageFragment {
//            val fragment = DetailImageFragment()
//            val args = Bundle()
//            args.putString(ARG_IMAGE_URI, imageUri)
//            fragment.arguments = args
//            return fragment
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_detail_image, container, false)
//        imageView = view.findViewById(R.id.image_detail)
//        downloadButton = view.findViewById(R.id.download_button)
//
//        arguments?.getString(ARG_IMAGE_URI)?.let {
//            imageUri = Uri.parse(it)
//            Glide.with(this).load(imageUri).into(imageView)
//        }
//        downloadImageManager = DownloadImageManager(requireContext())
//
//        downloadButton.setOnClickListener {
//            if (hasWritePermission(requireContext())) {
//                downloadImage()
//            } else {
//                requestWritePermission()
//            }
//        }
//
//        return view
//    }
//    // Kiểm tra quyền WRITE_EXTERNAL_STORAGE
// //Kiểm tra quyền ghi.
//    private fun hasWritePermission(context: Context): Boolean {
//        return ContextCompat.checkSelfPermission(
//            context,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//        ) == PackageManager.PERMISSION_GRANTED
//    }
//    // Yêu cầu quyền WRITE_EXTERNAL_STORAGE
////Yêu cầu quyền ghi nếu chưa được cấp.
//    private fun requestWritePermission() {
//        requestPermissions(
//            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
//            REQUEST_WRITE_PERMISSION
//        )
//    }
//
//
//
//
//    private fun downloadImage() {
//        downloadImageManager.downloadImage(imageView, object :
//            DownloadImageManager.DownloadImageCallback {
//            override fun onDownloadSuccess() {
//                Toast.makeText(context, "Tai thanh cong", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onDownloadFailed(errorMessage: String) {
//                Toast.makeText(context, "Tai that bai: $errorMessage", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            REQUEST_WRITE_PERMISSION -> {
//                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    downloadImage()
//                } else {
//                    Toast.makeText(
//                        context,
//                        "Permission denied to write storage",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }
//    }
//
//
//}



class DetailImageFragment : Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var downloadButton: Button
    private lateinit var imageUri: String
    private lateinit var downloadImageManager: DownloadImageManager

    companion object {
        private const val ARG_IMAGE_URI = "image_uri"
        private const val REQUEST_WRITE_EXTERNAL_STORAGE = 1

        fun newInstance(imageUri: String): DetailImageFragment {
            val fragment = DetailImageFragment()
            val args = Bundle()
            args.putString(ARG_IMAGE_URI, imageUri)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_image, container, false)

        imageView = view.findViewById(R.id.image_detail)
        downloadButton = view.findViewById(R.id.download_button)

        arguments?.getString(ARG_IMAGE_URI)?.let {
            imageUri = it
            Glide.with(this).load(imageUri).into(imageView)
        }

        downloadImageManager = DownloadImageManager(requireContext())

        downloadButton.setOnClickListener {
            if (hasWritePermission()) {
                downloadImage()
            } else {
                showPermissionRationaleDialog()
            }
        }

        return view
    }

    private fun hasWritePermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun showPermissionRationaleDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Yêu cầu quyền ghi")
            .setMessage("Ứng dụng cần quyền ghi để tải ảnh về thiết bị của bạn.")
            .setPositiveButton("Đồng ý") { _, _ ->
                requestWritePermission()
            }
            .setNegativeButton("Hủy bỏ") { dialog, _ ->
                dialog.dismiss()
                Toast.makeText(requireContext(), "Quyền bị từ chối", Toast.LENGTH_SHORT).show()
            }
            .create()
            .show()
    }

    private fun requestWritePermission() {
        requestPermissions(
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            REQUEST_WRITE_EXTERNAL_STORAGE
        )
    }

    private fun downloadImage() {
        downloadImageManager.downloadImage(imageView, object : DownloadImageManager.DownloadImageCallback {
            override fun onDownloadSuccess() {
                Toast.makeText(context, "Tải thành công", Toast.LENGTH_SHORT).show()
            }

            override fun onDownloadFailed(errorMessage: String) {
                Toast.makeText(context, "Tải thất bại: $errorMessage", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_WRITE_EXTERNAL_STORAGE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    downloadImage()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Quyền bị từ chối để ghi vào bộ nhớ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}