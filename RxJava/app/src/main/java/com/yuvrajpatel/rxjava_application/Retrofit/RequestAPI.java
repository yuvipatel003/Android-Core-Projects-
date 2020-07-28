package com.yuvrajpatel.rxjava_application.Retrofit;

import com.yuvrajpatel.rxjava_application.Model.Comment;
import com.yuvrajpatel.rxjava_application.Model.Post;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RequestAPI {

    @GET("posts")
    Observable<List<Post>> getPosts();

    @GET("posts/{id}/comments")
    Observable<List<Comment>> getComments(
            @Path("id") int id
    );
}
