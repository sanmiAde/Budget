package com.sanmiaderibigbe.budget.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sanmiaderibigbe.budget.data.model.User
import com.sanmiaderibigbe.budget.data.model.UserWithTransactions
import com.sanmiaderibigbe.budget.databinding.UserListItemBinding

class UserAdapter(context: Context, val onUserItemClickHandler: OnUserItemClickHandler) : RecyclerView.Adapter<UserAdapter.ViewHolder>()  {
    private var userList: List<UserWithTransactions>? = null

    interface OnUserItemClickHandler{
        fun onClick(user: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val binding = UserListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    fun setUserList(songs: List<UserWithTransactions>?) {
        userList = songs
        notifyDataSetChanged()
    }

    fun clear() {
        userList = null
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int =  userList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        val user: UserWithTransactions? = userList?.get(p1)
        holder.userListBinding.user = user
    }

    inner class ViewHolder(val userListBinding: UserListItemBinding) : RecyclerView.ViewHolder(userListBinding.root), View.OnClickListener {

        init {
            userListBinding.root.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val user = userList?.get(adapterPosition)
            onUserItemClickHandler.onClick(user?.user!!)
        }
    }
}