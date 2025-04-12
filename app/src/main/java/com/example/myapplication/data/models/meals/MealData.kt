package com.example.myapplication.data.models.meals

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*

    Represents a table within the database.

*/

@Entity(tableName = "meal_data")
data class MealData(

    @PrimaryKey(autoGenerate = true)
    public var id: Int,

    @ColumnInfo(name = "name")
    public var name: String,

    @ColumnInfo(name = "desc")
    public var description: String?,

) {

    override fun toString(): String {
        return "Id: " + id + "\n" +
                "Name: " + name + "\n" +
                "Description: " + description + "\n"
    }
}