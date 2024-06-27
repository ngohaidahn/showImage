package com.example.showgallerykotlin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.showgallerykotlin.ImageItem
import com.example.showgallerykotlin.adapter.GalleryAdapter
import com.example.showgallerykotlin.R
import com.example.showgallerykotlin.SharedViewModel
//
//class ShowGalleryFragment : Fragment() {
//    private val sharedViewModel: SharedViewModel by activityViewModels()
//    lateinit var galleryNumber: TextView
//
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var galleryAdapter: GalleryAdapter
//    companion object {
//
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
////        return inflater.inflate(R.layout.fragment_show_gallery, container, false)
//        val view = inflater.inflate(R.layout.fragment_show_gallery, container, false)
//        recyclerView = view.findViewById(R.id.rcv_gallery)
//        galleryNumber= view.findViewById(R.id.gallery_name)
//        recyclerView.layoutManager = GridLayoutManager(context, 3)
////        galleryNumber.setText("photo(" + galleryAdapter.itemCount + ")")
//
//        sharedViewModel.imageItems.observe(viewLifecycleOwner) { imageItems ->
//
//            galleryAdapter = GalleryAdapter(imageItems){ imageItem ->
//                navigateToDetailImageFragment(imageItem)
//
//            }
//            galleryNumber.setText("photo(" + galleryAdapter.itemCount + ")")
//            recyclerView.adapter = galleryAdapter
//        }
//        return view
//    }
//    private fun navigateToDetailImageFragment(imageItem: ImageItem) {
//        val fragment = DetailImageFragment.newInstance(imageItem.uri.toString())
//        parentFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container, fragment)
//            .addToBackStack(null)
//            .commit()
//    }
//
//}
class ShowGalleryFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var galleryNumber: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var galleryAdapter: GalleryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_show_gallery, container, false)
        recyclerView = view.findViewById(R.id.rcv_gallery)
        galleryNumber = view.findViewById(R.id.gallery_name)
        recyclerView.layoutManager = GridLayoutManager(context, 3)

        sharedViewModel.imageItems.observe(viewLifecycleOwner) { imageItems ->
            galleryAdapter = GalleryAdapter(imageItems) { imageItem ->
                navigateToDetailImageFragment(imageItem)
            }
            galleryNumber.text = "photo(" + galleryAdapter.itemCount + ")"
            recyclerView.adapter = galleryAdapter
        }

        return view
    }

    private fun navigateToDetailImageFragment(imageItem: ImageItem) {
        val fragment = DetailImageFragment.newInstance(imageItem.uri.toString())
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
