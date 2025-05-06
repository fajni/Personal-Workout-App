package com.example.myapplication

import com.example.myapplication.utils.CurrentDate
import java.time.Duration
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar


fun main() {

    val start = LocalTime.of(10,30)
    val end = LocalTime.now()

    println(start)
    println(end)

    if(start > end) {
        throw IllegalArgumentException("Start time can't be bigger than End time")
    }

    val duration = Duration.between(start, end)

    val time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))

    //println(time)

    val hours = duration.toMinutes() / 60
    val minutes = duration.toMinutes() % 60

    println("Duration: " + hours.toString() + "h " + minutes.toString() + "min")
}