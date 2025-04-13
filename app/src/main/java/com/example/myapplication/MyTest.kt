package com.example.myapplication

import com.example.myapplication.utils.CurrentDate


fun main() {

    var ingredients: ArrayList<String> = ArrayList()

    ingredients.add("Bread")
    ingredients.add("Milk")
    ingredients.add("Water")
    ingredients.add("Cheese")

    print(ingredients.joinToString(", "))
}