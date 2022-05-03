package com.dicoding.picodiploma.submission2.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import androidx.recyclerview.widget.RecyclerView
import coil.transform.CircleCropTransformation
import com.dicoding.picodiploma.submission2.data.model.Item
import com.dicoding.picodiploma.submission2.databinding.ItemUserBinding

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    private val list = ArrayList<Item>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: ArrayList<Item>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val v: ItemUserBinding): RecyclerView.ViewHolder(v.root){
        fun bind(item: Item){
            v.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(item)
            }
            v.imgItemPhoto.load(item.avatar_url){
                transformations(CircleCropTransformation())
            }
            v.tvUserName.text = item.login
            v.tvID.text = item.id.toString()
            v.tvType.text = item.type
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback{
        fun onItemClicked(data: Item)
    }

}


