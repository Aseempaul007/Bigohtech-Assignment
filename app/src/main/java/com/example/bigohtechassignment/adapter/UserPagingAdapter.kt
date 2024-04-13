package com.example.bigohtechassignment.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bigohtechassignment.R
import com.example.bigohtechassignment.data.UserItem
import com.example.bigohtechassignment.databinding.UserItemBinding
import com.example.bigohtechassignment.utils.Constants

// paging adapter for showing user
class UserPagingAdapter(
    private val context: Context,
    private val navigationController: NavController
) : PagingDataAdapter<UserItem, UserPagingAdapter.UserViewHolder>(comparator) {

    inner class UserViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val comparator = object : DiffUtil.ItemCallback<UserItem>() {
            override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val author = getItem(position)?.author
        val downloadUrl = getItem(position)?.download_url
        val height = getItem(position)?.height
        val id = getItem(position)?.id
        val url = getItem(position)?.url
        val width = getItem(position)?.width

        holder.binding.textViewAuthor.text = author
        Glide.with(context).load(downloadUrl)
            .centerCrop()
            .into(holder.binding.imageViewAuthor)

        holder.binding.cardHolder.setOnClickListener {
            val userObject = UserItem(author!!, downloadUrl!!, height!!, id!!, url!!, width!!)
            val bundle = Bundle()
            bundle.putSerializable(Constants.USER_OBJECT, userObject)
            navigationController?.navigate(
                R.id.action_usersListFragment_to_usersDetailFragment,
                bundle
            )
        }
    }
}