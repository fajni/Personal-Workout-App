package com.example.myapplication.data.models.meals

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*

    Represents a table within the database.

*/

@Entity(tableName = "ingredient_data")
data class IngredientData(

    @PrimaryKey(autoGenerate = true)
    public var id: Int,

    @ColumnInfo(name = "title")
    public var title: String,


) {

    override fun toString(): String {
        return "Id: " + id + "\n" +
                "Title: " + title + "\n"
    }
}
