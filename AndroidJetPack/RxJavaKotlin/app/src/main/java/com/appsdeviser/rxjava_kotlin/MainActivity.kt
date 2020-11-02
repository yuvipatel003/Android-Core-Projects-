package com.appsdeviser.rxjava_kotlin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.simpleName + " Operators"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        // just operator of RxJava
//        justOperator()
//
//        // fromArray operator of RxJava
//        fromArrayOperator()
//
//        // fromIterable operator of RxJava
//        fromIterableOperator()
//
//        // range operator return observable
//        rangeOperatorFunction()
//
//        // repeat operator return observable on repeat mode
//        // repeat it once executed all
//        // returned observable subscribed using lambda expression
//        repeatOperatorFunction()
//
//        // emit value after regular interval
//        intervalOperatorFunction()
//
//        // execute after given timer delay
//        timerOperatorFunction()
//
//        // emit customized observable
//        // single string object from list
//        createOperatorFunction()
//
//        // filter observable and emit which fulfill required condition
//        filterOperatorFunction()
//
//        // last observable and emit last or default if last is null or empty
//        lastOperatorFunction()
//
//        // return only unique value from User list according to age
//        distinctOperatorFunction()
//
//        // skip initial count and emit other values
//        // skipLast - skip last count
//        skipOperatorFunction()
//
//        // buffer minimum count and emit bundle of value
//        bufferOperatorFunction()
//
//        // perform some operation and emit new value
//        mapOperatorFunction()
//
//        // transfer emitted item to another observable
//        flatMapOperatorFunction()
//
//        // apply multiple flatMap
//        multipleFlatMapOperatorFunction()
//
//        // create group from emitted items regarding to applied values(filter or condition)
//        groupByOperatorFunction()
//       groupByOperatorFunctionCreateList()
//
//        // merge two observable and may their emission interleave each other
//        mergeOperatorFunction()
//
//        // complete start with observable emission then move to another observable
//        startWithOperatorFunction()
//
//        // Combine two observable using function and emit combined value as observable
//        zipOperatorSimpleFunction()
//        zipOperatorComplexFunction()
//
//        createObservable().subscribe(observer())
        flowableObservable()

    }


    private fun bufferOperatorFunction() {
        bufferOperator()
            .buffer(3)
            .subscribe(
                {
                    Log.d(TAG, "buffer() : onNext() : ${it.toString()}")
                }, {
                    Log.d(TAG, "buffer() : onError() : ${it.toString()}")
                }, {
                    Log.d(TAG, "buffer() : onComplete()")
                }
            )

    }

    private fun skipOperatorFunction() {
        // skip initial count and emit other values
        // skipLast - skip last count
        skipOperator()
            .skip(2)
            .subscribe(
                {
                    Log.d(TAG, "skip() : onNext() : ${it.toString()}")
                }, {
                    Log.d(TAG, "skip() : onError() : ${it.toString()}")
                }, {
                    Log.d(TAG, "skip() : onComplete()")
                }
            )

    }

    private fun distinctOperatorFunction() {
        distinctOperator()
            // .distinct() - refer whole user object
            .distinct {
                it.name
            }
            .subscribe(
                {
                    Log.d(TAG, "distinct() : onNext() : ${it.toString()}")
                }, {
                    Log.d(TAG, "distinct() : onError() : ${it.toString()}")
                }, {
                    Log.d(TAG, "distinct() : onComplete()")
                }
            )
    }

    private fun lastOperatorFunction() {
        // another  : lastElement(), lastOrError()
        lastOperator()
            .last(User(100, "Default", 0))
            .subscribe(
                {
                    Log.d(TAG, "last() : onNext() : ${it.toString()}")
                }, {
                    Log.d(TAG, "last() : onError() : ${it.toString()}")
                })

    }

    private fun filterOperatorFunction() {
        // filter observable and emit which fulfill required condition
        filterOperator()
            .filter {
                it.age > 36
            }
            .subscribe(
                {
                    Log.d(TAG, "filter() : onNext() : ${it.toString()}")
                }, {
                    Log.d(TAG, "filter() : onError() : ${it.toString()}")
                }, {
                    Log.d(TAG, "filter() : onComplete()")
                })

    }

    private fun createOperatorFunction() {
        // emit customized observable
        // single string object from list
        createOperator().subscribe({
            Log.d(TAG, "create() : onNext() : $it")
        }, {
            Log.d(TAG, "create() : onError() : ${it.toString()}")
        }, {
            Log.d(TAG, "create() : onComplete()")
        })

    }

    private fun timerOperatorFunction() {
        // execute after given timer delay
        timerOperator().subscribe({
            Log.d(TAG, "timer() : onNext() : $it")
        }, {
            Log.d(TAG, "timer() : onError() : ${it.toString()}")
        }, {
            Log.d(TAG, "timer() : onComplete()")
        })

    }

    private fun intervalOperatorFunction() {
        // emit value after regular interval
        intervalOperator().subscribe({
            Log.d(TAG, "interval() : onNext() : $it")
            // get location on every execution of interval
            getLocation()
        }, {
            Log.d(TAG, "interval() : onError() : ${it.toString()}")
        }, {
            Log.d(TAG, "interval() : onComplete()")
        })

    }

    private fun repeatOperatorFunction() {
        // repeat operator return observable on repeat mode
        // repeat it once executed all
        // returned observable subscribed using lambda expression
        repeatOperator()?.subscribe({
            Log.d(TAG, "repeat() : onNext() : $it")
        }, {
            Log.d(TAG, "repeat() : onError() : ${it.toString()}")
        }, {
            Log.d(TAG, "repeat() : onComplete()")
        })

    }

    private fun rangeOperatorFunction() {
        rangeOperator()?.subscribe({
            Log.d(TAG, "range() : onNext() : $it")
        }, {
            Log.d(TAG, "range() : onError() : ${it.toString()}")
        }, {
            Log.d(TAG, "range() : onComplete()")
        })
    }

    /**
     * perform some operation on emitted value and return that value onNext().
     * if last statement is it.name + "Don" -> it will add Don at end of name and return it in onNext()
     * if last statement id it.age * 10 -> It will multiply age by 10 and return it in onNext()
     * if last statement is new object of userprofile -> It will return it in onNext()
     */
    private fun mapOperatorFunction() {
        mapOperator()
            .map {
                it.name + " Don"
                it.age * 10
                UserProfile(it.id, it.name, it.age, "https://www.userprofilepic.com/${it.id}")
            }
            .subscribe(
                {
                    Log.d(TAG, "map() : onNext() : ${it.toString()}")
                }, {
                    Log.d(TAG, "map() : onError() : ${it.toString()}")
                }, {
                    Log.d(TAG, "map() : onComplete()")
                }
            )

    }

    /**
     *  transfer emitted item to another observable
     *  flatMap always return observable item where Map is return any
     */
    private fun flatMapOperatorFunction() {
        flatMapOperator()
            .flatMap {
                getUserProfile(it.id)
            }
//            .map {
//                it.image
//            }
            .subscribe(
                {
                    Log.d(TAG, "flatMap() : onNext() : ${it.toString()}")
                }, {
                    Log.d(TAG, "flatMap() : onError() : ${it.toString()}")
                }, {
                    Log.d(TAG, "flatMap() : onComplete()")
                }
            )

    }


    /**
     *  getting list of user from api,
     *  convert single user into userprofile
     *  get purticulat image field from userprofile
     */
    private fun multipleFlatMapOperatorFunction() {
        getUserList()
            .flatMap {
                Observable.fromIterable(it)
            }
            .flatMap {
                getUserProfile(it.id)
            }
//            .map {
//                it.image
//            }
            .subscribe(
                {
                    Log.d(TAG, "multipleFlatMap() : onNext() : ${it.toString()}")
                }, {
                    Log.d(TAG, "multipleFlatMap() : onError() : ${it.toString()}")
                }, {
                    Log.d(TAG, "multipleFlatMap() : onComplete()")
                }
            )

    }


    /**
     * create a group from emitted value by apply age as group
     * if it.age > 30 applied than 2 group will created ,
     * 1 - age above 30 with group key value true,
     * 2 - age below 30 with group key value false
     */
    private fun groupByOperatorFunction() {

        // Group by key
        groupByOperator()
            .groupBy {
                it.age
            }
            .subscribe(
                { group ->
                    group.subscribe({
                        Log.d(TAG, "groupBy() ${group.key} - onNext() : ${it.toString()}")
                    },{
                        Log.d(TAG, "groupBy() : onError() : ${it.toString()}")
                    })

                }, {
                    Log.d(TAG, "groupBy() : onError() : ${it.toString()}")
                }, {
                    Log.d(TAG, "groupBy() : onComplete()")
                }
            )
    }


    /**
     * create a group from emitted value by apply age as group
     * if it.age > 30 applied than 2 group list created,
     * 1 - age above 30 with group key value true,
     * 2 - age below 30 with group key value false
     */
    private fun groupByOperatorFunctionCreateList() {

        // group by key and make new list of same key
        groupByOperator()
            .groupBy {
//                it.age > 30 && it.name.contains("a")
//                it.age > 30
                it.name.contains("a")
            }
            .flatMapSingle { group ->
                group.toList()
            }
            .subscribe(
                {
                    Log.d(TAG, "groupBy() CreateList - onNext() : ${it.toString()}")
                }, {
                    Log.d(TAG, "groupBy() CreateList : onError() : ${it.toString()}")
                }, {
                    Log.d(TAG, "groupBy() CreateList : onComplete()")
                }
            )
    }

    /**
     * Merge two emission in one and perform
     * It will may interleave each other
     * For complete protection from interleave use concat - concat will execute first emission till onComplete()
     *                                                      after execution onComplete() it will move to next Observable emission.
     */
    private fun mergeOperatorFunction() {
        Observable.merge(getUsers(), getUsersProfile())
            .subscribe( {
                Log.d(TAG, "merge() : onNext() : ${it.toString()}")
            }, {
                Log.d(TAG, "merge() : onError() : ${it.toString()}")
            }, {
                Log.d(TAG, "merge() : onComplete()")
            })
    }

    /**
     * Used to get default value for any observable or first returned value of observable
     * Execute startWith observable first later execute second observable
     */
    private fun startWithOperatorFunction() {
        startWithOperator().subscribe({
            Log.d(TAG, "startWith() : onNext() : ${it.toString()}")
        }, {
            Log.d(TAG, "startWith() : onError() : ${it.toString()}")
        }, {
            Log.d(TAG, "startWith() : onComplete()")
        })
    }

    /**
     * Combine emission of two observables using function and return combined value as observable
     */
    private fun zipOperatorSimpleFunction() {
        zipOperatorSimple().subscribe({
            Log.d(TAG, "zipOperatorSimple() : onNext() : ${it.toString()}")
        }, {
            Log.d(TAG, "zipOperatorSimple() : onError() : ${it.toString()}")
        }, {
            Log.d(TAG, "zipOperatorSimple() : onComplete()")
        })
    }
    /**
     *
     */
    private fun zipOperatorComplexFunction() {
        zipOperatorComplex().subscribe({
            it.forEach {
                Log.d(TAG, "zipOperatorComplex() : onNext() : ${it.toString()}")
            }
        }, {
            Log.d(TAG, "zipOperatorComplex() : onError() : ${it.toString()}")
        }, {
            Log.d(TAG, "zipOperatorComplex() : onComplete()")
        })
    }

    /**private fun bufferOperatorFunction() {
        bufferOperator()
            .buffer(3)
            .subscribe(
                {
                    Log.d(TAG, "buffer() : onNext() : ${it.toString()}")
                }, {
                    Log.d(TAG, "buffer() : onError() : ${it.toString()}")
                }, {
                    Log.d(TAG, "buffer() : onComplete()")
                }
            )

    }
 */
    /**
     * return current location
     */
    private fun getLocation() {
        Log.d(TAG, "Lat : 11.2222 , Long : 22.1111")
    }

    /**
     * Clear disposable
     */
    override fun onDestroy() {
        clearDisposable()
        super.onDestroy()
    }
}