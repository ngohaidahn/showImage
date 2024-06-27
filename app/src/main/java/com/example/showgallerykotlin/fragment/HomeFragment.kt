package com.example.showgallerykotlin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.showgallerykotlin.CheckPermission
import com.example.showgallerykotlin.ImageItem
import com.example.showgallerykotlin.ImageLoader
import com.example.showgallerykotlin.R
import com.example.showgallerykotlin.SharedViewModel

//
//class HomeFragment : Fragment(),
//    CheckPermission.PermissionCallback {
//    private val sharedViewModel: SharedViewModel by activityViewModels()
//    companion object {
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
//        checkPermission = CheckPermission(requireContext(),this)
//        imageLoader =  ImageLoader()
//        val button: Button = view.findViewById(R.id.btn_gallery)
//
//        button.setOnClickListener() {
////            val granted
//            if ( checkPermission.checkAndRequestPermissions()) {
//                loadMediaFromGallery()
//            }
//        }
//
//        return view
//    }
//
//    fun loadMediaFromGallery() {
//        val contentResolver = requireContext().contentResolver
//        val imageItems = imageLoader.loadImageFromGallery(contentResolver)
//
//        sharedViewModel.setImageItems(imageItems)
//        parentFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container, ShowGalleryFragment())
//            .addToBackStack(null)
//            .commit()
//    }
//
//    override fun onPermissionsGranted() {
//        loadMediaFromGallery()
//
//    }
//
//    override fun onPermissionsDenied() {
//        Toast.makeText(requireContext(), "Permissions denied", Toast.LENGTH_SHORT).show()
//
//    }
//
//
//
//}
class HomeFragment : Fragment(), CheckPermission.PermissionCallback {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var checkPermission: CheckPermission
    private lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        checkPermission = CheckPermission(requireContext(), this)
        imageLoader = ImageLoader()
        val button: Button = view.findViewById(R.id.btn_gallery)

        button.setOnClickListener {
            if (checkPermission.checkAndRequestPermissions()) {
                loadMediaFromGallery()
            }
        }

        return view
    }

    private fun loadMediaFromGallery() {
        val contentResolver = requireContext().contentResolver
        val imageItems = imageLoader.loadImageFromGallery(contentResolver)

        sharedViewModel.setImageItems(imageItems)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ShowGalleryFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onPermissionsGranted() {
        loadMediaFromGallery()
    }

    override fun onPermissionsDenied() {
        Toast.makeText(requireContext(), "Permissions denied", Toast.LENGTH_SHORT).show()
    }
}
