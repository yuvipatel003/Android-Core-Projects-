package com.appsdeviser.androidtest.testdouble.network

import android.accounts.NetworkErrorException


interface UserProfileHttpEndpointSync {
    /**
     * Get user's profile from the server
     * @return the aggregated result
     * @throws NetworkErrorException if operation failed due to network error
     */
    @Throws(NetworkErrorException::class)
    fun getUserProfile(userId: String?): EndpointResult?
    enum class EndpointResultStatus {
        SUCCESS, AUTH_ERROR, SERVER_ERROR, GENERAL_ERROR
    }

    class EndpointResult(
        val status: EndpointResultStatus,
        val userId: String,
        val fullName: String,
        val imageUrl: String
    )
}