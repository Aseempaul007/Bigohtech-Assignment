package com.example.bigohtechassignment.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.bigohtechassignment.data.UserItem
import com.example.bigohtechassignment.databinding.FragmentUsersDetailBinding
import com.example.bigohtechassignment.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersDetailFragment : Fragment() {

    private var binding: FragmentUsersDetailBinding? = null

    private var user: UserItem? = null
    private var userId: String? = null
    private var userAuthor: String? = null
    private var userWidth: String? = null
    private var userHeight: String? = null
    private var userUrl: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        user = arguments?.getSerializable(Constants.USER_OBJECT) as? UserItem

        initializeUserData()

        initializeViews()

    }

    // initializing views
    private fun initializeViews() {
        binding?.let {
            it.userIdValue.text = userId
            it.userAuthorValue.text = userAuthor
            it.userWidthValue.text = userWidth
            it.userHeightValue.text = userHeight
            it.userUrlValue.text = userUrl
            Glide.with(requireContext()).load(userUrl)
                .centerCrop()
                .into(it.imageView)
        }

    }

    // initializing data for user object
    private fun initializeUserData() {
        userId = user?.id
        userAuthor = user?.author
        userWidth = user?.width.toString().toString()
        userHeight = user?.height.toString()
        userUrl = user?.download_url
    }

}