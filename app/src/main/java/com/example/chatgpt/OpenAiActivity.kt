package com.example.chatgpt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chatgpt.commons.utils.setFragment
import com.example.chatgpt.databinding.ActivityOpenAiBinding
import com.example.chatgpt.features.chat.presentation.ui.OpenAiFragment

class OpenAiActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityOpenAiBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFragment(OpenAiFragment.newInstance(), binding.frameLayout.id)
    }
}