package com.example.bigohtechassignment.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bigohtechassignment.adapter.UserPagingAdapter
import com.example.bigohtechassignment.databinding.FragmentUsersListBinding
import com.example.bigohtechassignment.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersListFragment : Fragment() {

    private var viewModel: UsersViewModel? = null
    private var adapter: UserPagingAdapter? = null
    private var binding: FragmentUsersListBinding? = null
    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initializeViews()

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        viewModel?.getUsers?.observe(requireActivity()) {
            adapter?.submitData(lifecycle, it)
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        adapter?.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading
            )
                binding?.progressBar?.visibility = View.VISIBLE
            else {
                binding?.progressBar?.visibility = View.INVISIBLE
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(requireActivity(), it.error.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun initializeViews() {
        viewModel = ViewModelProvider(this)[UsersViewModel::class.java]
        navController = findNavController()
        if (navController != null) {
            adapter = UserPagingAdapter(requireContext(), navController!!)
        }
        binding?.let {
            it.recyclerview.adapter = adapter
            it.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        }
    }

}