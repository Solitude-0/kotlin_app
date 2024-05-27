package com.example.myapplication.ui.fragment

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentMyBinding
import com.squareup.picasso.Picasso


/**
 * A simple [Fragment] subclass.
 * Use the [MyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyFragment : Fragment() {
    private var userAvatar: ImageView? = null
    private var _binding: FragmentMyBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyBinding.inflate(inflater, container, false)
        userAvatar = _binding!!.userAvatar;
        loadUserData()
        return binding.root
    }


    private fun loadUserData() {
        val imageUrl = "https://img.wmtp.net/wp-content/uploads/2024/04/z2FKnQBQ-200x185.jpg"
        Picasso.get()
            .load(imageUrl)
            .into(userAvatar)
    }

}