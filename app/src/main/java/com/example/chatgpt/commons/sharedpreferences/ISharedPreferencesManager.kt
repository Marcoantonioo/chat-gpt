package com.example.chatgpt.commons.sharedpreferences

interface ISharedPreferencesManager {
    fun <T> save(key: String, value: T)
    fun <T> get(key: String, clazz: Class<T>?, defaultValue: T? = null): T?
    fun delete(key: String)
    fun clear()
    fun hasKey(key: String): Boolean
}