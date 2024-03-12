package com.example.roomer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.roomer.databinding.ItemsRowBinding

class UsersAdapter(
    private val users: List<User>,
    private val updater: (id: Int) -> Unit,
    private val deleter: (id: Int) -> Unit

) :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    inner class UserViewHolder(binding: ItemsRowBinding) : RecyclerView.ViewHolder(binding.root) {


        val llMain = binding.llMain
        val tvName = binding.tvName
        val tvEmail = binding.tvEmail
        val ivEdit = binding.ivEdit
        val ivDelete = binding.ivDelete

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {


        return UserViewHolder(
            ItemsRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val user = users[position]
        val context = holder.itemView.context

        if (position % 2 == 0) {
            holder.llMain.setBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.lightGrey
                )
            )
        } else {
            holder.llMain.setBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.white
                )
            )
        }
        holder.tvName.text = user.name
        holder.tvEmail.text = user.email

        holder.ivDelete.setOnClickListener {
            deleter.invoke(user.id)
        }
        holder.ivEdit.setOnClickListener {
            updater.invoke(user.id)
        }
    }
}