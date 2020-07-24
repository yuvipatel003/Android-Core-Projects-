package com.yuvrajpatel.rxjava_application;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.yuvrajpatel.rxjava_application.DataSource.DataSource;
import com.yuvrajpatel.rxjava_application.Model.Task;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    private TextView mTextView;

    // Disposable
    CompositeDisposable disposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView);

        Observable<Task> taskObservable = Observable
                .fromIterable(DataSource.createTask())
                .subscribeOn(Schedulers.io())
                // Use filter to avoid freezing of screen on main thread
                .filter(new Predicate<Task>() {
                    @Override
                    public boolean test(Task task) {
                        Log.d(TAG, "test : "+ Thread.currentThread().getName());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // Only observe those tasks which mIsCompleted is true
                        return task.getIsCompleted();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());


        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG,"onSubscribe()");
                disposable.add(d);
            }

            @Override
            public void onNext(@NonNull Task task) {
                Log.d(TAG,"onNext() : " + Thread.currentThread().getName());
                Log.d(TAG,"Task :" + task.getDescription());
                mTextView.setText(task.getDescription() + " is performing");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG,"Error: "+ e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"onComplete()");
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
