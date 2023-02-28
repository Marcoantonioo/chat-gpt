package com.example.chatgpt.commons.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.formatDateToHourFormat(): String =
    SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(this))

fun Long.formatDateToMonthAndDayFormat(): String =
    SimpleDateFormat("MMMM dd", Locale.getDefault()).format(Date(this))