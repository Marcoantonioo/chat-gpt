package com.example.chatgpt.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatgpt.databinding.MessageReceivedItemBinding
import com.example.chatgpt.databinding.MessageSendItemBinding
import com.example.chatgpt.presentation.model.MessageView
import java.text.SimpleDateFormat
import java.util.*

class OpenAiListAdapter(
    val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
            binding.run {
                textGchatMessageMe.text = item.message.first()

                val formatter = SimpleDateFormat("MMMM dd", Locale.getDefault())
                val formatter1 = SimpleDateFormat("hh/mm", Locale.getDefault())

                val timeString = formatter1.format(Date(item.createdAt))
                val dateString = formatter.format(Date(item.createdAt))

                textGchatTimestampMe.text = timeString

                textGchatDateMe.text = dateString
            }
        }

    }

    inner class MessageReceivedViewHolder(private val binding: MessageReceivedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MessageView) {
            binding.run {
                textGchatMessageOther.text = item.message.first()

                val formatter = SimpleDateFormat("MMMM dd", Locale.getDefault())
                val formatter1 = SimpleDateFormat("hh/mm", Locale.getDefault())

                val timeString = formatter1.format(Date(item.createdAt))
                val dateString = formatter.format(Date(item.createdAt))

                textGchatTimestampOther.text = timeString

                textGchatDateOther.text = dateString

                textGchatUserOther.text = "Chat GPT"
            }
        }

    }

    companion object {
        const val FROM_ME = 1
        const val NOT_FROM_ME = 0
    }
}
