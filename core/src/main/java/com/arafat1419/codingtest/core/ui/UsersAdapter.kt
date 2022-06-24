package com.arafat1419.codingtest.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arafat1419.codingtest.core.databinding.ItemUsersBinding
import com.arafat1419.codingtest.core.domain.domain.UserDomain
import com.arafat1419.codingtest.core.utils.setAvatarGenerator
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    // Variable to save list of user
    private val listUsers = ArrayList<UserDomain>()

    // Trigger function
    var onItemClicked: ((UserDomain) -> Unit)? = null

    // Set users
    fun setUsers(newUsers: List<UserDomain>?) {
        if (newUsers == null) return

        // Clear current users and add new users
        listUsers.clear()
        listUsers.addAll(newUsers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.ViewHolder {
        return ViewHolder(
            ItemUsersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UsersAdapter.ViewHolder, position: Int) {
        holder.bind(listUsers[position])
    }

    override fun getItemCount(): Int = listUsers.size

    inner class ViewHolder(private val binding: ItemUsersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserDomain) {
            with(binding) {
                val avatar = user.name?.let { setAvatarGenerator(itemView.context, it) }

                Glide.with(itemView.context)
                    .load("")
                    .apply(
                        RequestOptions.placeholderOf(avatar)
                            .error(avatar)
                    )
                    .into(imgUser)

                txtName.text = user.name
                txtUsername.text = user.username

                // Invoke onItemClicked when data is clicked and set userDomain as parameter
                itemView.setOnClickListener {
                    onItemClicked?.invoke(user)
                }
            }
        }
    }
}