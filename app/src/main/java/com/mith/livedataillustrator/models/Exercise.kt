package com.mith.livedataillustrator.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Exercise(
        @PrimaryKey(autoGenerate = true)
        val exerciseId: Int,
        val name: String,
        val repetitions:Int,
        @ColumnInfo(name = "machine_name")
        val machineName: String,
        val liftedWeight: Int)