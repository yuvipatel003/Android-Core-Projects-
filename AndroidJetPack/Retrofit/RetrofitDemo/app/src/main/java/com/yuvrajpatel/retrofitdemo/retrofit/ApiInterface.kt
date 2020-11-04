package com.yuvrajpatel.retrofitdemo.retrofit

import com.yuvrajpatel.retrofitdemo.data.Album
import retrofit2.http.GET

interface ApiInterface {

    @GET("/albums")
    suspend fun getAlbums() : retrofit2.Response<Album>
}