package com.appsdeviser.rxjava_kotlin

import android.util.Log
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

val TAG = "Operator"
var mDisposable = CompositeDisposable()

private val mListNumber = mutableListOf("One", "Two", "Three", "Four", "Five", "Six", "Seven")
private val mListAlphabets = mutableListOf("A", "B", "C", "D", "E", "F", "G")
private val mListUser = mutableListOf(
    User(1, "Alex", 20),
    User(2, "Bella", 40),
    User(3, "Cat", 22),
    User(4, "David", 22),
    User(5, "Elly", 24),
    User(6, "Fraddy", 55),
    User(7, "Gary", 22),
    User(8, "Harry", 78),
    User(9, "Ivy", 30)
)

private val mListUserProfile = mutableListOf(
    UserProfile(1, "Alex", 20,"https://www.userprofilepic.com/1"),
    UserProfile(2, "Bella", 40,"https://www.userprofilepic.com/2"),
    UserProfile(3, "Cat", 22,"https://www.userprofilepic.com/3"),
    UserProfile(4, "David", 22,"https://www.userprofilepic.com/4"),
    UserProfile(5, "Elly", 24,"https://www.userprofilepic.com/5"),
    UserProfile(6, "Fraddy", 55,"https://www.userprofilepic.com/6"),
    UserProfile(7, "Gary", 22,"https://www.userprofilepic.com/7"),
    UserProfile(8, "Harry", 78,"https://www.userprofilepic.com/8"),
    UserProfile(9, "Ivy", 90,"https://www.userprofilepic.com/9")
)

private val mListBlog = mutableListOf(
    Blog(1, 1, "Blog1","Content 1"),
    Blog(2, 3, "Blog2","Content 2"),
    Blog(3, 10, "Blog3","Content 3"),
    Blog(4, 1, "Blog4","Content 4"),
    Blog(5, 5, "Blog5","Content 5"),
    Blog(6, 5, "Blog6","Content 6"),
    Blog(7, 8, "Blog7","Content 7"),
    Blog(8, 9, "Blog8","Content 8"),
    Blog(9, 2, "Blog9","Content 9")
)

    /**
     * clear disposable
     */
    fun clearDisposable() {
        mDisposable.clear()
    }

    /**
     * Just can only pass limited values to observable
     * max limit is 10
     * If we pass mList it will consider it as a one list object
     * instead of 7 String items
     *
     * just emit single value rather it is list or String or any
     */
    fun justOperator() {

        val observable = Observable.just("One", "Two", "Three", "Four", "Five", "Six", "Seven")
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<String> {
                override fun onComplete() {
                    Log.d(TAG, "just() : onComplete()")
                }

                override fun onSubscribe(d: Disposable?) {
                    mDisposable.add(d)
                    Log.d(TAG, "just() : onSubscribe()")
                }

                override fun onNext(t: String) {
                    Log.d(TAG, "just() : onNext() : $t")
                }

                override fun onError(e: Throwable?) {
                    Log.d(TAG, "just() : onError() : " + e.toString())
                }

            })
    }


    /**
     * fromArray emit whole array in onNext()
     * we can pass multiple arrays in fromArray() operator
     */
    fun fromArrayOperator() {
        val observable = Observable.fromArray(mListNumber, mListAlphabets, mListAlphabets, mListNumber)
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<MutableList<String>> {
                override fun onComplete() {
                    Log.d(TAG, "fromArray() : onComplete()")
                }

                override fun onSubscribe(d: Disposable?) {
                    mDisposable.add(d)
                    Log.d(TAG, "fromArray() : onSubscribe()")
                }

                override fun onNext(t: MutableList<String>?) {
                    Log.d(TAG, "fromArray() : onNext() : ${t.toString()}")
                }

                override fun onError(e: Throwable?) {
                    Log.d(TAG, "fromArray() : onError() : ${e.toString()}")
                }


            })
    }


    /**
     * fromIterable emits single item in sequence
     * observe single item in list
     */
    fun fromIterableOperator() {
        val observable = Observable.fromIterable(mListNumber)
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<String> {
                override fun onComplete() {
                    Log.d(TAG, "fromIterable() : onComplete()")
                }

                override fun onSubscribe(d: Disposable?) {
                    mDisposable.add(d)
                    Log.d(TAG, "fromIterable() : onSubscribe()")
                }

                override fun onNext(t: String?) {
                    Log.d(TAG, "fromIterable() : onNext() : ${t.toString()}")
                }

                override fun onError(e: Throwable?) {
                    Log.d(TAG, "fromIterable() : onError() : ${e.toString()}")
                }

            })
    }


    /**
     * emits a sequence of Observable<Int> within a specified range
     */
    fun rangeOperator(): @NonNull Observable<Int>? {
        return Observable.range(1, 5)
    }


    /**
     * emits a sequence of Observable<Int> within a specified range and
     * repeat it as provided times
     */
    fun repeatOperator(): @NonNull Observable<Int>? {
        return Observable.range(1, 5).repeat(3)
    }


    /**
     * emit value after regular interval until app is closed or condition match
     * in this case, emit 1, 2, 3....
     * e.g. get device location continiously after every two second
     * eliminate code of service or extra thread which is used to get location
     */
    fun intervalOperator(): Observable<Long> {
        return Observable.interval(5000, 2000, TimeUnit.MILLISECONDS).takeWhile { value ->
            value < 10
        }
    }


    /**
     * emit observable after given timer delay
     * It is only one time execution
     * Useful to complete some execution(onetime execution),
     * after app start with some specified delay
     */
    fun timerOperator(): Observable<Long> {
        return Observable.timer(5000, TimeUnit.MILLISECONDS)

    }


    /**
     * emit observable
     * It provides customization option to developer
     * Customize emitted value to String where onNext will execute from mListNumber
     * emit single string object from list
     */
    fun createOperator(): Observable<String> {
        return Observable.create(ObservableOnSubscribe<String> {
            try {
                for (i in mListNumber) {
                    it.onNext(i)
                }
                it.onComplete()
            } catch (e: Exception) {
                it.onError(e)
            }
        })
    }


    /**
     * filter observable
     */
    fun filterOperator(): Observable<User> {
        return Observable.fromIterable(mListUser)
    }


    /**
     * last observable
     */
    fun lastOperator(): Observable<User> {
        // return default value from last operator as list is empty
        // return Observable.fromIterable(emptyList<User>())
        return Observable.fromIterable(mListUser)
    }


    /**
     * emit only unique value from list
     */
    fun distinctOperator(): Observable<User> {
        return Observable.fromIterable(mListUser)
    }


    /**
     * skip initial count and emit other values
     * skipLast - skip last count
     */
    fun skipOperator(): Observable<User> {
        return Observable.fromIterable(mListUser)
    }


    /**
     * emit bundle
     * emit bunch of value rather emitting single item
     * e.g. buffer 3 items than emit
     */
    fun bufferOperator(): Observable<User> {
        return Observable.fromIterable(mListUser)
    }


    /**
     * transfer emitted item by applying some function or operation
     * perform function or operations on emitted value
     */
    fun mapOperator(): Observable<User> {
        return Observable.fromIterable(mListUser)
    }


    /**
     * transfer emitted item in observable
     */
    fun flatMapOperator(): Observable<User> {
        return Observable.fromIterable(mListUser)
    }


    /**
     * group emitted item by applying condition or parameter
     */
    fun groupByOperator(): Observable<User> {
        return Observable.fromIterable(mListUser)
    }


    /**
     * first execute timer operator than interval
     * start with timer observable and once timer observable complete, it will proceed to interval operator
     */
    fun startWithOperator() : Observable<Long> {
        return intervalOperator().startWith(timerOperator())
    }


    /**
     * Combine emission of two observables using function and return combined value as observable
     */
    fun zipOperatorSimple() : Observable<String> {
        val num = Observable.just(1,2,3,4,5,6)
        val char = Observable.just("A","B","C","D","E","F","G")
        return Observable.zip(char, num, BiFunction { t1, t2 ->
            "$t1$t2"
        })
    }


    /**
     * Combine Blog and User object to BlogDetail object and emit List of BlogDetail with blog and relevant user info
     * If usedid from blog doesn't match with id from userlist then object will be ignored
     */
    fun zipOperatorComplex() : Observable<List<BlogDetail>> {
        return Observable.zip(getBlogs(), getUserList(), BiFunction { t1, t2 ->
            blogDetail(t1,t2)
          })
    }


    /**
     * Get observable from User Profile
     */
    fun getUserProfile(id: Long) : Observable<UserProfile>{
        return Observable.fromIterable(mListUserProfile)
            .filter {
                it.id == id
            }
    }


    /**
     *  Get user list as Observable<List<User>>
     */
    fun getUserList(): Observable<List<User>>{
        return Observable.just(mListUser)
    }


    /**
     * Get users as Observable<User> from userList
     */
    fun getUsers(): Observable<User>{
        return Observable.fromIterable(mListUser)
    }


    /**
     * Get userprofile as Observable<User> from userprofileList
     */
    fun getUsersProfile(): Observable<UserProfile>{
        return Observable.fromIterable(mListUserProfile)
    }


    /**
     * Get blogs as Observable<Blog> from blogList
     */
    fun getBlogs(): Observable<List<Blog>>{
        return Observable.just(mListBlog)
    }


    /**
     * zip opertor function to combine user and blog to blogdetail object
     */
    fun blogDetail(blogList: List<Blog>, userList: List<User>): List<BlogDetail>{
        var listBlogDetail : MutableList<BlogDetail> = emptyList<BlogDetail>().toMutableList()
        blogList.forEach { blog->
            userList.forEach {user ->
                if(blog.userId == user.id){
                    listBlogDetail.add(BlogDetail(blog.id, blog.title, blog.content, user))
                }
            }
        }
        return listBlogDetail
    }



