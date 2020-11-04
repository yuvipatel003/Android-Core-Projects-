package com.yuvrajpatel.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.yuvrajpatel.retrofitdemo.data.Album
import com.yuvrajpatel.retrofitdemo.retrofit.ApiInterface
import com.yuvrajpatel.retrofitdemo.retrofit.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val albumService = RetrofitInstance.
        getRetrofitInstance().
        create(ApiInterface::class.java)

        val albumsLiveData : LiveData<Response<Album>> = liveData {
            val liveData = albumService.getAlbums()
            emit(liveData)
        }

        albumsLiveData.observe(this, Observer {
            val albumList = it.body()?.listIterator()
            if(albumList!=null){
                while(albumList.hasNext()){
                    val albumItem = albumList.next()
                    Log.d("Album : ", albumItem.toString())
                }
            }
        })
    }
}