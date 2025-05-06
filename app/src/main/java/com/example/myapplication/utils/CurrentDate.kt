package com.example.myapplication.utils

import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

class CurrentDate {

    private var time = Calendar.getInstance().time
    private var formatter = SimpleDateFormat("dd/MM/yyyy")

    private var currentDate: String = formatter.format(time)

    public fun getCurrentDate() : String {
        return currentDate
    }

    public fun getCurrentDay(): String {

        val today = LocalDate.now()
        val dayOfWeek = today.dayOfWeek.toString()

        return dayOfWeek
    }

    public fun calculateTimeDuration(start: LocalTime, end: LocalTime): String {

        if(start > end) {
            //error("$start cant be bigger than $end")
            throw IllegalArgumentException("Start time can't be bigger than End time")
        }

        val duration = Duration.between(start, end)
        val hours = duration.toMinutes() / 60
        val minutes = duration.toMinutes() % 60

        return hours.toString() + "h " + minutes.toString() + "min"

    }

    public fun getCurrentTime(): String {

        val time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))

        //val now = LocalTime.now()
        //val hour = now.hour
        //val minute = now.minute

        return time.toString()
    }

}