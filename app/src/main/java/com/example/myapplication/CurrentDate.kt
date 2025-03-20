package com.example.myapplication

import java.text.SimpleDateFormat
import java.util.Calendar

class CurrentDate {

    private var time = Calendar.getInstance().time
    private var formatter = SimpleDateFormat("dd/MM/yyyy")

    private var currentDate: String = formatter.format(time)

    public fun getCurrentData() : String {
        return currentDate
    }
}