package com.mith.livedataillustrator.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.mith.livedataillustrator.converters.ListConverter
import java.util.*


@Entity(tableName = "traineeRoutine")
data class Routine(
        @PrimaryKey(autoGenerate = true)
        val routineId: Int,
        @ColumnInfo(name = "due_day")
        val dueDay: Date,
        @TypeConverters(ListConverter::class)
        val exercises: List<Exercise>)