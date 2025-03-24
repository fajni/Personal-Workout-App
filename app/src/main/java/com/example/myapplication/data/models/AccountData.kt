package com.example.myapplication.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*

    Represents a table within the database.

*/

@Entity(tableName = "account_data")
data class AccountData (

    @PrimaryKey(autoGenerate = true)
    public var id: Int?,

    @ColumnInfo(name = "name")
    public var name: String?,

    @ColumnInfo(name = "calories")
    public var calories: Int?,

    @ColumnInfo(name = "proteins")
    public var proteins: Int?,

    @ColumnInfo(name = "carbs")
    public var carbs: Int?,

    @ColumnInfo(name = "fats")
    public var fats: Int?,


    @ColumnInfo(name = "max_bench_kg")
    public var maxBenchKg: Int?,

    @ColumnInfo(name = "max_bench_reps")
    public var maxBenchReps: Int?,


    @ColumnInfo(name = "max_deadlift_kg")
    public var maxDeadliftKg: Int?,

    @ColumnInfo(name = "max_deadlift_reps")
    public var maxDeadliftReps: Int?,


    @ColumnInfo(name = "max_squad_kg")
    public var maxSquadKg: Int?,

    @ColumnInfo(name = "max_squad_reps")
    public var maxSquadReps: Int?,


    @ColumnInfo(name = "current_weight")
    public var currentWeight: Int?,


    @ColumnInfo(name = "account_date")
    public var accountDate: String?
) { }