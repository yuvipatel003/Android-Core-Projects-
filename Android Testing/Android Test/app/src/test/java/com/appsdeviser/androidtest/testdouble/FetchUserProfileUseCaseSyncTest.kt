package com.appsdeviser.androidtest.testdouble

import com.appsdeviser.androidtest.testdouble.network.UserProfileHttpEndpointSync
import com.appsdeviser.androidtest.testdouble.user.User
import com.appsdeviser.androidtest.testdouble.user.UserCache
import org.junit.Before

import org.junit.Assert.*

class FetchUserProfileUseCaseSyncTest {


    @Before
    fun setUp() {
    }




    //--------------------------------------------------------------------------------------------------
    class FakeUseProfileHttpEndPointSyncImpl : UserProfileHttpEndpointSync {
        lateinit var mFakeUserCache : FakeUserCacheImpl
        val mIsResultSuccess = true

        override fun getUserProfile(userId: String?): UserProfileHttpEndpointSync.EndpointResult? {
            if(mIsResultSuccess){
                val user = mFakeUserCache.getUser(userId)
                return UserProfileHttpEndpointSync.EndpointResult(UserProfileHttpEndpointSync.EndpointResultStatus.SUCCESS,
                    user!!.userId,
                    user!!.fullName,
                    user!!.imageUrl)
            } else {
               return null
            }
        }
    }

    class FakeUserCacheImpl : UserCache {
        lateinit var mUser : User
        override fun cacheUser(user: User?) {
            mUser = user!!
        }

        override fun getUser(userId: String?): User? {
            return mUser
        }
    }
}