package com.example.myapplication.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

/*

    Represents a table within the database.

*/

@Entity(tableName = "meal_data") // ingredients: List<String>
@TypeConverters(Converters::class)
data class MealData(

    @PrimaryKey(autoGenerate = true)
    public var id: Int,

    @ColumnInfo(name = "name")
    public var name: String,

    @ColumnInfo(name = "texture")
    public var texture: String?, // is it drink or food

    @ColumnInfo(name = "ingredients")
    // Room will use Converters class because can't work with List
    public var ingredients: List<String>,

    @ColumnInfo(name = "desc")
    public var description: String?,

    @ColumnInfo(name = "calories")
    public var calories: Int? = 0,
    @ColumnInfo(name = "proteins")
    public var proteins: Int? = 0,
    @ColumnInfo(name = "carbs")
    public var carbs: Int? = 0,
    @ColumnInfo(name = "fats")
    public var fats: Int? = 0,

) {

    override fun toString(): String {
        return "Id: " + id + "\n" +
                "Name: " + name + "\n" +
                "Texture: " + texture + "\n" +
                "Ingredients: " + ingredients.joinToString(", ") + "\n" +
                "Description: " + description + "\n"
    }
}

class Converters {

    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return value.joinToString(separator = ",")
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return value.split(",").map { it.trim() }
    }
}