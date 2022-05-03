package com.dicoding.picodiploma.submission2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.CircleCropTransformation
import com.dicoding.picodiploma.submission2.adapter.PagerAdapter
import com.dicoding.picodiploma.submission2.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_USERNAME = "extra_username"
    }

    private lateinit var viewModel: DetailUserViewModel
    private lateinit var binding: ActivityDetailUserBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.title = "User Profile"

        val login = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, login)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailUserViewModel::class.java]

        viewModel.isLoading.observe(this, {
            showLoading(it)
        })

        if (login != null) {
            viewModel.setUserDetail(login)
        }
        viewModel.getUserDetail().observe(this, {
            if(it != null){
                binding.apply {
                    tvName.text = it.name
                    tvUserName.text = it.login
                    tvFollowers.text = it.followers.toString()
                    tvFollowing.text = it.following.toString()
                    imageView.load(it.avatar_url) {
                        transformations(CircleCropTransformation())
                    }
                }
            }
        })
        val pagerAdapter = PagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            userPager.adapter = pagerAdapter
            userTab.setupWithViewPager(userPager)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}