package com.dicoding.picodiploma.submission2.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import androidx.recyclerview.widget.RecyclerView
import coil.transform.CircleCropTransformation
import com.dicoding.picodiploma.submission2.data.model.Item
import com.dicoding.picodiploma.submission2.databinding.ItemFollowBinding

class FollowAdapter: RecyclerView.Adapter<FollowAdapter.UserViewHolder>(){

    private val list = ArrayList<Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: ArrayList<Item>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val v: ItemFollowBinding): RecyclerView.ViewHolder(v.root){
        fun bind(item: Item){
            v.imgItemPhoto.load(item.avatar_url){
                transformations(CircleCropTransformation())
            }
            v.tvUserName.text = item.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(ItemFollowBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

}


