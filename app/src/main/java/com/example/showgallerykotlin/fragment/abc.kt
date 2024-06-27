//package com.example.showgallerykotlin
//
//import android.Manifest
//
//import android.os.Build
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import androidx.fragment.app.activityViewModels
//
//
//class abc : Fragment() {
//    private val sharedViewModel: SharedViewModel by activityViewModels()
////    private val requestPermissions =
//////        RequestMultiplePermissions cho phép yêu cầu nhiều quyền cùng lúc.
////        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
////            // Handle permission requests results
//////            results chứa kết quả của các yêu cầu quyền,
////            //         với key là tên quyền và value là kết quả (true nếu được cấp quyền).
////            if (results[Manifest.permission.READ_EXTERNAL_STORAGE] == true) {
////                // Permission granted, load images
////                loadMediaFromGallery()
////            } else if (results[Manifest.permission.READ_MEDIA_IMAGES] == true) {
////                loadMediaFromGallery()
////            } else if (results[Manifest.permission.READ_MEDIA_VIDEO] == true) {
////                loadMediaFromGallery()
////            }
////
////            else if (results[Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED] == true) {
////                loadMediaFromGallery()
////            } else {
////                // Permission denied, handle accordingly
////                Log.d("GalleryFragment", "Media permission denied")
////            }
////        }
//
////    private val requestPermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
////        if (results[Manifest.permission.READ_MEDIA_IMAGES] == true || results[Manifest.permission.READ_MEDIA_VIDEO] == true) {
////            loadMediaFromGallery()
////        } else {
////            Log.d("GalleryFragment", "Media permission denied")
////        }
////    }
//
//    companion object {
//
//    }
//    private lateinit var checkPermission: CheckPermission
//    private lateinit var imageLoader: ImageLoader
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_home, container, false)
//
//        val button: Button = view.findViewById(R.id.btn_gallery)
//
//        button.setOnClickListener() {
////            checkStoragePermission()
//            val permissionsToRequest = getPermissionsToRequest()
//            checkPermission.checkAndRequestPermissions(permissionsToRequest)
//
//        }
//        return view
//    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        checkPermission = CheckPermission(this)
//        imageLoader = ImageLoader()
//    }
//
//    private fun getPermissionsToRequest(): Array<String> {
//        return when {
//            Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE -> arrayOf(
//                Manifest.permission.READ_MEDIA_IMAGES,
//                Manifest.permission.READ_MEDIA_VIDEO,
//                Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
//            )
//            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> arrayOf(
//                Manifest.permission.READ_MEDIA_IMAGES,
//                Manifest.permission.READ_MEDIA_VIDEO
//            )
//            else -> arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
//        }
//    }
//
//
//    fun loadMediaFromGallery() {
//        val contentResolver = requireContext().contentResolver
//        val mediaItems = imageLoader.loadMediaFromGallery(contentResolver)
//        sharedViewModel.setMediaItems(mediaItems)
//
//        parentFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container, ShowGalleryFragment())
//            .addToBackStack(null)
//            .commit()
//    }
//
////    private fun checkStoragePermission() {
////        val permissionsToRequest = when {
////            Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE -> arrayOf(
////                Manifest.permission.READ_MEDIA_IMAGES,
////                Manifest.permission.READ_MEDIA_VIDEO,
////                Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
////            )
////            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> arrayOf(
////                Manifest.permission.READ_MEDIA_IMAGES,
////                Manifest.permission.READ_MEDIA_VIDEO
////            )
////            else -> arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
////        }
////
////        if (permissionsToRequest.any {
////                ContextCompat.checkSelfPermission(requireContext(), it) != PackageManager.PERMISSION_GRANTED
////            }) {
////            requestPermissions.launch(permissionsToRequest)
////        } else {
////            loadMediaFromGallery()
////        }
////    }
////    private fun loadMediaFromGallery() {
////        val contentResolver: ContentResolver = requireContext().contentResolver
////        val mediaItems = mutableListOf<MediaItem>()
////        // Load Images
////        val imageUri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
////        loadMediaFromUri(contentResolver, imageUri, mediaItems)
////
////        // Load Videos
////        val videoUri: Uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
////        loadMediaFromUri(contentResolver, videoUri, mediaItems)
////
////        sharedViewModel.setMediaItems(mediaItems)
////        // Navigate to DisplayFragment
////        parentFragmentManager.beginTransaction()
////            .replace(R.id.fragment_container, ShowGalleryFragment())
////            .addToBackStack(null)
////            .commit()
////    }
////    private fun loadMediaFromUri(contentResolver: ContentResolver, uri: Uri, mediaItems: MutableList<MediaItem>) {
////        val projection = arrayOf(MediaStore.MediaColumns._ID)
////        val cursor: Cursor? = contentResolver.query(uri, projection, null, null, null)
////        cursor?.use {
////            val columnIndexId = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns._ID)
////            while (cursor.moveToNext()) {
////                val mediaId = cursor.getLong(columnIndexId)
////                val mediaUri = Uri.withAppendedPath(uri, mediaId.toString())
////                Log.d("QueryFragment", "Media URI: $mediaUri")
////                mediaItems.add(MediaItem(mediaUri, ))
////                mediaItems.reverse()
////            }
////        }
////    }
//
//}