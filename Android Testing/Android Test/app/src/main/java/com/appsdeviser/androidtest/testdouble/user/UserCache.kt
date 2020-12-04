package com.appsdeviser.androidtest.testdouble.user

import androidx.annotation.Nullable

interface UserCache {
    fun cacheUser(user: User?)

    @Nullable
    fun getUser(userId: String?): User?
}