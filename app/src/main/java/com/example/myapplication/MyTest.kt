package com.example.myapplication

import com.example.myapplication.utils.CurrentDate


fun main() {

    var numbers: ArrayList<String> = ArrayList<String>()

    numbers.add("10")

    println(numbers)

    numbers.removeAt(0)

    println(numbers)

    print(CurrentDate().getCurrentData())
}