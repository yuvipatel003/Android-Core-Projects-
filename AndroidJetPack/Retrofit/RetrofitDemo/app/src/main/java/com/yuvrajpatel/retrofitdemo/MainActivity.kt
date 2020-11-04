package com.yuvrajpatel.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.yuvrajpatel.retrofitdemo.data.Album
import com.yuvrajpatel.retrofitdemo.data.AlbumItem
import com.yuvrajpatel.retrofitdemo.retrofit.ApiInterface
import com.yuvrajpatel.retrofitdemo.retrofit.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var albumService: ApiInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        albumService = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)

        getAllAlbums()
        getSingleUserAlbum()
        getSingleAlbum()
        addAlbum()
    }


    private fun getAllAlbums() {
        val albumsLiveData: LiveData<Response<Album>> = liveData {
            val liveData = albumService.getAlbums()
            emit(liveData)
        }

        albumsLiveData.observe(this, Observer {
            val albumList = it.body()?.listIterator()
            if (albumList != null) {
                while (albumList.hasNext()) {
                    val albumItem = albumList.next()
                    Log.d("Album : ", albumItem.toString())
                }
            }
        })
    }

    private fun getSingleAlbum() {
        val singleAlbum: LiveData<Response<AlbumItem>> = liveData {
            val liveData = albumService.getAlbum(2)
            emit(liveData)
        }

        singleAlbum.observe(this, Observer {
            val album = it.body()
            Log.d("Single Album : ", album.toString())
        })

    }

    private fun getSingleUserAlbum() {
        val albumsLiveData: LiveData<Response<Album>> = liveData {
            val liveData = albumService.getSortedAlbums(8)
            emit(liveData)
        }

        albumsLiveData.observe(this, Observer {
            val albumList = it.body()?.listIterator()
            if (albumList != null) {
                while (albumList.hasNext()) {
                    val albumItem = albumList.next()
                    Log.d("Album : ", albumItem.toString())
                }
            }
        })
    }

    private fun addAlbum() {

        val albumItem = AlbumItem(0,"Newly Added", 555)

        val albumsLiveData: LiveData<Response<AlbumItem>> = liveData {
            val liveData = albumService.addAlbum(albumItem)
            emit(liveData)
        }

        albumsLiveData.observe(this, Observer {
            val album = it.body()
                    Log.d("Added Album : ", album.toString())

        })
    }
}
