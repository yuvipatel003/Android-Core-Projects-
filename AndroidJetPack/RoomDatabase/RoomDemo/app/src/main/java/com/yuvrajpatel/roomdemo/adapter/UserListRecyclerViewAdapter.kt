package com.yuvrajpatel.roomdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yuvrajpatel.roomdemo.R
import com.yuvrajpatel.roomdemo.databinding.UserListItemBinding
import com.yuvrajpatel.roomdemo.db.User
import com.yuvrajpatel.roomdemo.generated.callback.OnClickListener

class UserListRecyclerViewAdapter(private val clickListener :(User) -> Unit) : RecyclerView.Adapter<UserListView>() {

    private val userList = ArrayList<User>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListView {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : UserListItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.user_list_item,parent,false)
        return UserListView(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserListView, position: Int) {
        holder.bind(userList.get(position), clickListener)
    }

    fun setList(users : List<User>){
        userList.clear()
        userList.addAll(users)
    }
}

class UserListView(private val userListBinding : UserListItemBinding) : RecyclerView.ViewHolder(userListBinding.root){

        fun bind(user : User, clickListener :(User) -> Unit){
            userListBinding.textViewUserListUserName.text = user.name
            userListBinding.textViewUserListUserEmail.text = user.email
            userListBinding.layoutUserListItemContainer.setOnClickListener {
                clickListener.invoke(user)
            }
        }
}