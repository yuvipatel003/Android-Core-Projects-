package com.appsdeviser.rxjava_kotlin

import android.util.Log
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception
import java.util.*

val TAG_OBSERVABLE = "Observable"


/**
 * Observable Typers
 *
 * 1. Observable - emits more than one value
 *      Observer has 4 function - onSubscribe(), onNext(), onError(), onComplete()
 *
 * 2. SingleObservable - emit only one value (Network call, Database operation)
 *      SingleObserver has 3 function - onSubscribe(), onError(), onSuccess()
 *
 * 3. MaybeObservable - emit value or no value (Not recommanded for android application)
 *      MaybeObserver has 4 function - onSubscribe(), onError(), onSuccess(), onComplete()
 *
 * 4. CompletableObservable - used when observer has to do some task without emitting any value
 *      CompletableObserver has 3 function - onSubscribe(), onError(), onComplete()
 *
 * 5. Flowable - is similar to observable but used when emitting huge number of values, which is not consumed/received by observer
 *              - need to skip some values on strategy. strategy called as BackPressureStrategy
 *              - Types of Strategy : Drop, Buffer, Latest, Missing, Error
 *      Observer is same as Observable observer has 4 function - onSubscribe(), onError(), onNext(), onComplete()
 */




/**
 * Observable emits more than one value
 */
fun createObservable() : Observable<Int> {
    return Observable.create<Int> {emitter ->
        try {
            if (!emitter.isDisposed) {
                for (i in 0..50) {
                    emitter.onNext(i)
                }
                emitter.onComplete()
            }
        } catch (e: Exception){
            emitter.onError(e)
        }
    }
}

fun observer(): Observer<Int> {
     return object : Observer<Int>{
         override fun onComplete() {
             Log.d(TAG_OBSERVABLE,"onComplete()")
         }

         override fun onSubscribe(d: Disposable?) {
             Log.d(TAG_OBSERVABLE,"onSubscribe()")
         }

         override fun onNext(t: Int?) {
             Log.d(TAG_OBSERVABLE,"onNext() $t")
         }

         override fun onError(e: Throwable?) {
             Log.d(TAG_OBSERVABLE,"onError()")
         }

     }
}

/**
 * Observable emits more than one value
 */
fun flowable() : Flowable<Int> {
    return Flowable.range(1,100)
}

fun flowableObservable(){
    flowable()
        .onBackpressureBuffer() // Get all in buffer
        .onBackpressureDrop() // Get first 10 only and drop other
        .onBackpressureLatest() // get first 10 and latest emit 100 in this case - total 11
        .observeOn(Schedulers.io(),false,10)
        .subscribe({
            Log.d(TAG,"flowable onNext() $it")
        },{
            Log.d(TAG,"flowable onError()")
        },{
            Log.d(TAG,"flowable onComplete()")
        })
}