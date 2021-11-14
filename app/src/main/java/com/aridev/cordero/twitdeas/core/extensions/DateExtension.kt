package com.aridev.cordero.twitdeas.core.credentials

import java.text.SimpleDateFormat
import java.util.*

fun getDaysAgo(daysAgo: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
    return calendar.time
}

fun getToday() : Date {
    val calendar = Calendar.getInstance()
    return calendar.time
}

fun dateToStringFormat(date : Date):String{
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
    return sdf.format(date)
}

fun stringToDateStringShort(dateString : String) : String {
    val format = SimpleDateFormat("dd-MM-yyyy", Locale.US)
    val newFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
    val date = format.parse(dateString)
    return newFormat.format(date)
}