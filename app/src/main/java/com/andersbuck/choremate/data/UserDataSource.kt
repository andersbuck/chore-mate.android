package com.andersbuck.choremate.data

import android.os.Handler
import android.os.Looper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataSource @Inject constructor(private val userDao: UserDao) {

    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)
    private val mainThreadHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    fun fetchUsers(callback: (List<User>) -> Unit) {
        executorService.execute {
            val users = userDao.fetchUsers()
            mainThreadHandler.post { callback(users) }
        }
    }

    fun insert(user: User) {
        executorService.execute {
            userDao.insert(user)
        }
    }
}