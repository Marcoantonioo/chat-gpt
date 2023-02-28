package com.example.chatgpt.features.chat.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatgpt.commons.utils.formatDateToHourFormat
import com.example.chatgpt.commons.utils.formatDateToMonthAndDayFormat
import com.example.chatgpt.databinding.MessageReceivedItemBinding
import com.example.chatgpt.databinding.MessageSendItemBinding
import com.example.chatgpt.features.chat.presentation.model.MessageView

class OpenAiListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: MutableList<MessageView> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == FROM_ME) {
            val view =
                MessageSendItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            MessageFromMeViewHolder(view)
        } else {
            val view =
                MessageReceivedItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            MessageReceivedViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == FROM_ME) {
            (holder as MessageFromMeViewHolder).bind(list[position])
        } else {
            (holder as MessageReceivedViewHolder).bind(list[position])
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (list[position].isFromMe) FROM_ME else NOT_FROM_ME
    }

    override fun getItemCount() = list.size

    inner class MessageFromMeViewHolder(private val binding: MessageSendItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MessageView) {
            item.run {
                binding.run {
                    tvMessage.text = message.first()
                    tvHour.text = createdAt.formatDateToHourFormat()
                    tvDate.text = createdAt.formatDateToMonthAndDayFormat()
                }
            }
        }

    }

    inner class MessageReceivedViewHolder(private val binding: MessageReceivedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MessageView) {
            binding.run {
                item.run {
                    tvMessage.text = message.first()
                    tvHour.text = createdAt.formatDateToHourFormat()
                    tvDate.text = createdAt.formatDateToMonthAndDayFormat()
                    tvName.text = NAME
                }
            }
        }

    }

    companion object {
        const val FROM_ME = 1
        const val NOT_FROM_ME = 0
        const val NAME = "Chat GPT"
    }
}
