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
    if (timestamp <= 0) return "0 ч 0 мин"
    val totalMinutes = timestamp / (1000 * 60)
    val hours = totalMinutes / 60
    val minutes = totalMinutes % 60
    return "$hours ч $minutes мин"
}

fun convertTimeStampToLocalDateTime(timestamp: Long) = (timestamp % 86_400_000).toFloat()
