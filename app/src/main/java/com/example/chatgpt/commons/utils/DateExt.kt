package com.example.chatgpt.commons.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.formatDateToHourFormat() =
    SimpleDateFormat("MMMM dd", Locale.getDefault()).format(Date(this))

fun Long.formatDateToMonthAndDayFormat() =
    SimpleDateFormat("MMMM dd", Locale.getDefault()).format(Date(this))