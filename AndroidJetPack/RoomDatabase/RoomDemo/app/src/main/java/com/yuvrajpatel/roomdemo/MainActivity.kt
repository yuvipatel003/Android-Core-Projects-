package com.yuvrajpatel.roomdemo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuvrajpatel.roomdemo.adapter.UserListRecyclerViewAdapter
import com.yuvrajpatel.roomdemo.databinding.ActivityMainBinding
import com.yuvrajpatel.roomdemo.db.User
import com.yuvrajpatel.roomdemo.db.UserDatabase
import com.yuvrajpatel.roomdemo.db.UserRepository

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.simpleName
    private lateinit var mModel : UserViewModel
    private lateinit var mBinding : ActivityMainBinding
    private lateinit var mAdapter : UserListRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao = UserDatabase.getInstance(application).userDao

        val repository = UserRepository(dao)
        val factory = UserViewModelFactory(repository)

        mModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
        mBinding.myUserViewModel = mModel
        mBinding.lifecycleOwner = this
        initRecyclerView()
        setMessageBox()
    }


    private fun getUserList(){
        mModel.users.observe(this, Observer {
            Log.d(TAG, it.toString())
            mAdapter.setList(it)
            mAdapter.notifyDataSetChanged()
        })
    }

    private fun initRecyclerView(){
        mBinding.recyclerViewUsersList.layoutManager = LinearLayoutManager(this)
        mAdapter = UserListRecyclerViewAdapter({selectedItem: User ->selectItemListener(selectedItem)})
        mBinding.recyclerViewUsersList.adapter = mAdapter
        getUserList()
    }

    private fun setMessageBox(){
       mModel.message.observe(this, Observer {
           it.getContentIfNotHandledOrReturnNull()?.let {
               Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
           }
       })
    }

    private fun selectItemListener(user: User){
        Log.d(TAG,"Selected : ${user.name}")
        mModel.initUpdateAndDelete(user)
    }
}