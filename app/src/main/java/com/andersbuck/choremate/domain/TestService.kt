package com.andersbuck.choremate.domain

import com.andersbuck.choremate.data.UserDao
import javax.inject.Inject

class TestService @Inject constructor(private val userDao: UserDao) {

}