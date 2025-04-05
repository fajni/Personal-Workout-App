package com.example.myapplication.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*

    Represents a table within the database.

*/

@Entity(tableName = "workout_data")
data class WorkoutData (

    @PrimaryKey(autoGenerate = true)
    public var number: Int,

    @ColumnInfo(name = "day")
    public var day: String,

    @ColumnInfo(name = "muscle")
    public var muscle: String,

    @ColumnInfo(name = "workout_title")
    public var workoutTitle: String,

    @ColumnInfo(defaultValue = "('Created at' || CURRENT_TIMESTAMP)")
    public var createdAt: String?,

    @ColumnInfo(name = "note", defaultValue = "/")
    public var note: String?
)
{

    override fun toString(): String {
        return "\nNumber: " + number +
                "\nDay: " + day +
                "\nMuscle Part: " + muscle +
                "\nWorkout title: " + workoutTitle +
                "\nWorkout Note: " + note +
                "\nCreated at: " + createdAt
    }

}