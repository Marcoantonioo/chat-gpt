package com.example.chatgpt.commons.sharedpreferences

import android.content.SharedPreferences
import com.google.gson.Gson

class SharedPreferencesManagerImpl(
    private val sharedPreferences: SharedPreferences
) : ISharedPreferencesManager {

    override fun <T> save(key: String, value: T) = sharedPreferences.edit().run {
        when (value) {
            is String -> putString(key, value)
            is Int -> putString(key, value.toString())
            is Long -> putString(key, value.toString())
            is Boolean -> putString(key, value.toString())
            is Float -> putString(key, value.toString())
        }
        apply()
    }

    override fun <T> get(key: String, clazz: Class<T>?, defaultValue: T?): T? {
        sharedPreferences.run {
            val result: Any? = when (clazz) {
                String::class.java -> getString(key, defaultValue as? String?)
                Int::class.java -> getString(key, defaultValue as? String?)?.toInt()
                Long::class.java -> getString(key, defaultValue as? String?)?.toLong()
                Boolean::class.java -> getString(key, defaultValue as? String?).toBoolean()
                Float::class.java -> getString(key, defaultValue as? String?)?.toFloat()
                else -> {
                    Gson().fromJson(getString(key, null), clazz)
                }
            }
            return result as? T?
        }
    }

    override fun delete(key: String) = sharedPreferences.edit().remove(key).apply()

    override fun clear() = sharedPreferences.edit().clear().apply()

    override fun hasKey(key: String) = sharedPreferences.contains(key)
}