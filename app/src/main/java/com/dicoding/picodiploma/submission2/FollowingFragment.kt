package com.dicoding.picodiploma.submission2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.submission2.adapter.FollowAdapter
import com.dicoding.picodiploma.submission2.databinding.FragmentUserFollowBinding

class FollowingFragment: Fragment(R.layout.fragment_user_follow) {
    private var _binding: FragmentUserFollowBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowingViewModel
    private lateinit var userAdapter: FollowAdapter
    private lateinit var login: String

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        login = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        _binding = FragmentUserFollowBinding.bind(view)

        userAdapter = FollowAdapter()
        userAdapter.notifyDataSetChanged()

        binding.apply {
            rvFollowers.setHasFixedSize(true)
            rvFollowers.layoutManager = LinearLayoutManager(activity)
            rvFollowers.adapter = userAdapter
        }

        showLoading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[FollowingViewModel::class.java]
        viewModel.setUserFollowing(login)
        viewModel.getUserFollowing().observe(viewLifecycleOwner, {
            if (it!=null){
                userAdapter.setData(it)
                showLoading(false)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}