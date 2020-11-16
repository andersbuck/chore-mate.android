package com.andersbuck.choremate.shared

object Session {

    private val sessionMap: MutableMap<String, String> = HashMap()

    fun setUsername(username: String) {
        sessionMap["username"] = username
    }

    fun getUsername(): String? {
        return sessionMap["username"]
    }

}