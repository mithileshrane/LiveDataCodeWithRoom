package com.mith.livedataillustrator

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;

import com.mith.livedataillustrator.database.AppDatabase
import com.mith.livedataillustrator.database.GenderDao
import com.mith.livedataillustrator.models.Gender
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_room_database.*
import kotlinx.android.synthetic.main.content_room_database.*

class RoomDatabaseActivity : AppCompatActivity() {

    private var db: AppDatabase? = null
    private var genderDao: GenderDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_database)
        setSupportActionBar(toolbar)
        db = AppDatabase.getAppDataBase(context = this)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }


        Observable.fromCallable({
            db = AppDatabase.getAppDataBase(context = this)
            genderDao = db?.genderDao()

            var gender1 = Gender(name = "Male")
            var gender2 = Gender(name = "Female")

            with(genderDao){
                this?.insertGender(gender1)
                this?.insertGender(gender2)
            }
            db?.genderDao()?.getGenders()
        }).doOnNext({ list ->
            var finalString = ""
            list?.map { finalString+= it.name+" - " }
            tv_message.text = finalString

        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()

    }

}
