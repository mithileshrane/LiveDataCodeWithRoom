package com.mith.livedataillustrator.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.mith.livedataillustrator.converters.DateTypeConverter
import com.mith.livedataillustrator.converters.ListConverter
import com.mith.livedataillustrator.models.Exercise
import com.mith.livedataillustrator.models.Gender
import com.mith.livedataillustrator.models.Routine
import com.mith.livedataillustrator.models.Trainee

@Database(entities = [Exercise::class, Gender::class, Routine::class, Trainee::class], version = 1)
@TypeConverters(DateTypeConverter::class, ListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun genderDao(): GenderDao
    abstract fun routineDao(): RoutineDao
    abstract fun traineeDao(): TraineeDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "myDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}