package com.example.chatgpt.commons.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

inline fun <reified T> Fragment.observerFlow(
    flow: Flow<T>,
    noinline block: (T) -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.onEach {
                block.invoke(it)
            }.launchIn(this)
        }
    }
}
fun FragmentActivity.setFragment(fragment: Fragment, frameId: Int) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.add(frameId, fragment)
    transaction.commit()
}