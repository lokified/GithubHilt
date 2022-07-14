package com.loki.githubhilt.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.loki.githubhilt.data.model.Users
import com.loki.githubhilt.databinding.RepoItemLayoutBinding

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var userList = mutableListOf<Users>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RepoItemLayoutBinding.inflate(inflater, parent, false)

        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])

        holder.binding.tvGoTo.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(userList[position].html_url)
            holder.itemView.context.startActivity(intent)
        }
    }

    fun setRepoList(users: List<Users>) {

        this.userList = users.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = userList.size

    inner class UserViewHolder(val binding: RepoItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(users: Users) {
            binding.users = users
            Glide.with(binding.thumbImg).load(users.avatar_url).into(binding.thumbImg)
        }
    }
}