package com.example.myapplication.models

data class MealData (

    public var number: Int,

    public var calories: Int? = 0,
    public var proteins: Int? = 0,
    public var carbs: Int? = 0,
    public var fats: Int? = 0

)