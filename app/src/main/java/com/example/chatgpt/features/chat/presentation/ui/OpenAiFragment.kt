package com.example.chatgpt.features.chat.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatgpt.commons.utils.*
import com.example.chatgpt.databinding.FragmentOpenAiBinding
import com.example.chatgpt.features.chat.presentation.adapter.OpenAiListAdapter
import com.example.chatgpt.features.chat.presentation.model.MessageView
import com.example.chatgpt.features.chat.presentation.model.OpenAiEvent
import com.example.chatgpt.features.chat.presentation.model.OpenAiScreenState
import com.example.chatgpt.features.chat.presentation.viewmodel.OpenAiViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OpenAiFragment : Fragment() {

    private val binding by lazy {
        FragmentOpenAiBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModel<OpenAiViewModel>()

    private lateinit var mAdapter: OpenAiListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupClickListener()
        observerFlow()
    }

    private fun setupAdapter() {
        mAdapter = OpenAiListAdapter()
        binding.recyclerView.run {
            this.adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setupClickListener() {
        binding.run {
            button.setOnClickListener {
                editText.text.toString().trim().also {
                    if (it.isNotEmpty()) {
                        textViewTapping.showView()
                        editText.run {
                            hideKeyboard()
                            text?.clear()
                        }
                        viewModel.dispatchEvent(OpenAiEvent.MakeQuestion(it))
                    }
                }
            }
        }
    }

    private fun observerFlow() {
        binding.run {
            observerFlow(viewModel.state) {
                when (it) {
                    is OpenAiScreenState.Loading -> {}
                    is OpenAiScreenState.Success -> {
                        handleSuccess(it.data, it.isLocal)
                    }
                    is OpenAiScreenState.Error -> {
                        binding.textViewTapping.hideView()
                    }
                }
            }
        }
    }

    private fun handleSuccess(data: MutableList<MessageView>, isLocal: Boolean) {
        binding.run {
            mAdapter.run {
                list = data
                notifyDataSetChanged()
            }

            if (!isLocal) textViewTapping.hideView()

            delayAction {
                nestedScrollView.fullScroll(View.FOCUS_DOWN)
            }
        }
    }


    companion object {
        fun newInstance() = OpenAiFragment()
    }
}