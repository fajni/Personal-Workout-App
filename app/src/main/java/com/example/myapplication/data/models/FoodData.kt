package com.example.myapplication.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*

    Represents a table within the database.

*/

@Entity(tableName = "food_data")
data class FoodData(

    @PrimaryKey(autoGenerate = true)
    public var number: Int,

    @ColumnInfo(name = "title")
    public var title: String? = "",

    @ColumnInfo(name = "calories")
    public var calories: Int? = 0,
    @ColumnInfo(name = "proteins")
    public var proteins: Int? = 0,
    @ColumnInfo(name = "carbs")
    public var carbs: Int? = 0,
    @ColumnInfo(name = "fats")
    public var fats: Int? = 0,

    @ColumnInfo(name = "date")
    public var date: String? = ""

) {
    override fun toString(): String {
        return "Title: " + title + "\n" +
                "Calories: " + calories + "\n" +
                "Proteins: " + proteins + "\n" +
                "Carbs: " + carbs + "\n" +
                "Fats: " + fats + "\n" +
                "Date: " + date
    }
}