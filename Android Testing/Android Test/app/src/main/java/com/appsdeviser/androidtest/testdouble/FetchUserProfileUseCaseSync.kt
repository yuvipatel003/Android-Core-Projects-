package com.appsdeviser.androidtest.testdouble

import android.accounts.NetworkErrorException
import com.appsdeviser.androidtest.testdouble.network.UserProfileHttpEndpointSync
import com.appsdeviser.androidtest.testdouble.user.User
import com.appsdeviser.androidtest.testdouble.user.UserCache


class FetchUserProfileUseCaseSync(
    userProfileHttpEndpointSync: UserProfileHttpEndpointSync,
    userCache: UserCache
) {
    enum class UseCaseResult {
        SUCCESS, FAILURE, NETWORK_ERROR
    }

    private val mUserProfileHttpEndpointSync: UserProfileHttpEndpointSync =
        userProfileHttpEndpointSync
    private val mUserCache: UserCache

    fun fetchUserProfileSync(userId: String?): UseCaseResult {
        val endpointResult: UserProfileHttpEndpointSync.EndpointResult
        try {
            // the bug here is that userId is not passed to endpoint
            endpointResult = mUserProfileHttpEndpointSync.getUserProfile("")!!
            // the bug here is that I don't check for successful result and it's also a duplication
            // of the call later in this method
            mUserCache.cacheUser(
                userId?.let {
                    User(
                        it,
                        endpointResult.fullName,
                        endpointResult.imageUrl
                    )
                }
            )
        } catch (e: NetworkErrorException) {
            return UseCaseResult.NETWORK_ERROR
        }
        if (isSuccessfulEndpointResult(endpointResult)) {
            mUserCache.cacheUser(
                userId?.let {
                    User(
                        it,
                        endpointResult.fullName,
                        endpointResult.imageUrl
                    )
                }
            )
        }

        // the bug here is that I return wrong result in case of an unsuccessful server response
        return UseCaseResult.SUCCESS
    }

    private fun isSuccessfulEndpointResult(endpointResult: UserProfileHttpEndpointSync.EndpointResult): Boolean {
        return endpointResult.status == UserProfileHttpEndpointSync.EndpointResultStatus.SUCCESS
    }

    init {
        mUserCache = userCache
    }
}