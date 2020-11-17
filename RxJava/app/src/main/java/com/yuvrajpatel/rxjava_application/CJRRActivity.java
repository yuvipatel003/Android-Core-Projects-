package com.yuvrajpatel.rxjava_application;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

import android.os.Bundle;
import android.util.Log;

import com.yuvrajpatel.rxjava_application.DataSource.DataSource;
import com.yuvrajpatel.rxjava_application.Model.Task;
import com.yuvrajpatel.rxjava_application.R;

public class CJRRActivity extends AppCompatActivity {

    private static String TAG = "CJRRActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cjrr);

        createOperator();

        justOperator();

        rangeOperator();

        repeatOperator();

        fromOperator();
    }

    /**
     *
     */
    private void fromOperator() {
        Observable observable = Observable.fromArray()
    }

    /**
     *
     */
    private void repeatOperator() {
        Observable<Task> taskObservable = Observable
                .range(1,3)
                .map(new Function<Integer, Task>() {
                    @Override
                    public Task apply(Integer integer) throws Throwable {
                        return new Task("Newly created repeat task", false, integer);
                    }
                })
                .repeat(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Task task) {
                Log.d(TAG,"Repeat onNext: Task : "+ task.getDescription() + " priprity : " + task.getPriority());
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }

    /**
     *
     */
    private void rangeOperator() {

        Observable<Task> taskObservable = Observable
                .range(1,9)
                .map(new Function<Integer, Task>() {
                    @Override
                    public Task apply(Integer integer) throws Throwable {
                        return new Task("Newly created task", false, integer);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

                taskObservable.subscribe(new Observer<Task>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Task task) {
                        Log.d(TAG,"Range onNext: Task : "+ task.getDescription() + " priprity : " + task.getPriority());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    /**
     *
     */
    private void createOperator() {
        final Task task = new Task("Do Laundry", false, 2);

        Observable<Task> taskObservable = Observable
                .create(new ObservableOnSubscribe<Task>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Task> emitter) throws Throwable {

                        if (!emitter.isDisposed()) {
                            emitter.onNext(task);
                            emitter.onComplete();
                        }
//                        For list of tasks
//                        for(Task task : DataSource.createTask()){
//                            if(!emitter.isDisposed()){
//                              emitter.onNext(task);
//                              }
//                        }
//                        if(!emitter.isDisposed()){
//                            emitter.onComplete();
//                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Task task) {
                Log.d(TAG ," Create OnNext : " + task.getDescription());
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }

    /**
     * Just has limit of 10 items to pass
     */
    private void justOperator() {

        Observable.just("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.d(TAG, "Just OnNext : " + s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void divide(){
        Log.d(TAG, "-------------------------------------------------------------------------------");
    }


}
