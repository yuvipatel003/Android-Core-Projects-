package com.yuvrajpatel.retrofitdemo.retrofit

import com.yuvrajpatel.retrofitdemo.data.Album
import com.yuvrajpatel.retrofitdemo.data.AlbumItem
import retrofit2.http.*

interface ApiInterface {

    // Get all albums
    @GET("/albums")
    suspend fun getAlbums() : retrofit2.Response<Album>

    // get single user album by adding query parameter in url
    @GET("/albums")
    suspend fun getSortedAlbums(@Query("userId") userId : Int) : retrofit2.Response<Album>

    // get single album by adding album id in path
    @GET("/albums/{id}")
    suspend fun getAlbum(@Path("id") id : Int) : retrofit2.Response<AlbumItem>

    // get single album by adding album id in path
    @POST("/albums")
    suspend fun addAlbum(@Body album : AlbumItem) : retrofit2.Response<AlbumItem>
}