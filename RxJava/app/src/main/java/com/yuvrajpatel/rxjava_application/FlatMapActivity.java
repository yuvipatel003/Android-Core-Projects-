package com.yuvrajpatel.rxjava_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

import android.os.Bundle;
import android.util.Log;

import com.yuvrajpatel.rxjava_application.Model.Comment;
import com.yuvrajpatel.rxjava_application.Model.Post;
import com.yuvrajpatel.rxjava_application.Retrofit.ApiClient;

import java.util.List;
import java.util.Random;

public class FlatMapActivity extends AppCompatActivity {

    private static String TAG = "FlatMapActivity";
    private RecyclerView mRecyclerView;
    private CompositeDisposable mDisposable = new CompositeDisposable();
    private RecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_map);

        mRecyclerView = findViewById(R.id.recyclerView);

        initRecyclerView();

        getPostsObservable().subscribeOn(Schedulers.io())
                .flatMap(new Function<Post, ObservableSource<Post>>() {
                    @Override
                    public ObservableSource<Post> apply(Post post) {
                        return getCommentsObservable(post);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull Post post) {
                        updatePost(post);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private Observable<Post> getCommentsObservable(final Post post){
        return ApiClient.getRequestApi().getComments(post.getId())
                .map(new Function<List<Comment>, Post>() {
                    @Override
                    public Post apply(List<Comment> comments) throws Throwable {

                        int delay = ((new Random()).nextInt() + 1) * 1000;
                        Thread.sleep(delay);
                        Log.d(TAG,"apply : sleeping thread " + Thread.currentThread().getName() + " for " +
                                String.valueOf(delay) + " ms");
                        post.setComments(comments);
                        return post;
                    }
                })
                .observeOn(Schedulers.io());
    }



    private @NonNull Observable<Post> getPostsObservable(){
       return ApiClient.getRequestApi()
               .getPosts()
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .flatMap(new Function<List<Post>, ObservableSource<Post>>() {
                   @Override
                   public ObservableSource<Post> apply(List<Post> posts) throws Throwable {
                       mRecyclerViewAdapter.setPosts(posts);
                       return Observable.fromIterable(posts)
                               .subscribeOn(Schedulers.io());
                   }
               });
    }

    private void initRecyclerView() {
        mRecyclerViewAdapter = new RecyclerViewAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    private void updatePost(Post post){
        mRecyclerViewAdapter.updatePost(post);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposable.clear();
    }

}
