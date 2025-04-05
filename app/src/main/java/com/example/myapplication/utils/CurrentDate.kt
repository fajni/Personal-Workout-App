package com.example.myapplication.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar

class CurrentDate {

    private var time = Calendar.getInstance().time
    private var formatter = SimpleDateFormat("dd/MM/yyyy")

    private var currentDate: String = formatter.format(time)

    public fun getCurrentData() : String {
        return currentDate
    }

    public fun getCurrentDay(): String {

        val today = LocalDate.now()
        val dayOfWeek = today.dayOfWeek.toString()

        return dayOfWeek
    }

}