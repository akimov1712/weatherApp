package ru.topbun.weather.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun convertTimeStampToDate(timestamp: Long): String {
    val date = Date(timestamp)
    val sdf = SimpleDateFormat("E, d MMM, HH:mm", Locale.getDefault())
    return sdf.format(date)
}

fun convertTimeStampToTime(timestamp: Long): String {
    val date = Date(timestamp)
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    return sdf.format(date)
}

fun convertTimeStampToCountTime(timestamp: Long): String {
    val date = Date(timestamp)
    val sdfHours = SimpleDateFormat("hh", Locale.getDefault()).format(date) + " ч "
    val sdfMin = SimpleDateFormat("mm", Locale.getDefault()).format(date) + " мин "
    return sdfHours + sdfMin
}

fun convertTimeStampToLocalDateTime(timestamp: Long) = (timestamp % 86_400_000).toFloat()
