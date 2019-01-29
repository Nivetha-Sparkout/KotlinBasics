package com.kotlinproject.app

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatTextView
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.DatePicker
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    var cal = Calendar.getInstance()


    //another way to create findviewbyid
    var button: AppCompatButton? = null

    var date: AppCompatTextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button = this.findViewById(R.id.button)
        date = this.findViewById(R.id.date)


//        val button = findViewById<AppCompatButton>(R.id.button) //generate Id this way

        // create an OnDateSetListener
        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            callCalendaerView()
        }

        button!!.setOnClickListener {

            Toast.makeText(this@MainActivity, "Clciked Button", Toast.LENGTH_SHORT).show()
            DatePickerDialog(this@MainActivity,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        date!!.setOnClickListener {

            val move = Intent(this, NavigationActivity::class.java)
            move.putExtra("PASS", "1")
            startActivity(move)

        }


    }


    public fun Context.callCalendaerView() {


        //move one screen to another screen
        /*val move=Intent(this,MainActivity::class.java)
        startActivity(move)*/

        Log.e("Nive", "callCalendaerView")

        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)

        val meesage: String = sdf.format(cal.getTime())

        Toast.makeText(this, meesage, Toast.LENGTH_SHORT).show()

        //setText for TextView in kotlin
        date!!.text = "Click This Date Move to Next Page" + meesage


        //normally display calender and select date to display toast

        /* val calendarView = findViewById<CalendarView>(R.id.calendarView)
         calendarView?.setOnDateChangeListener { view, year, month, dayOfMonth ->
             // Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
             val msg = "Selected date is " + dayOfMonth + "/" + (month + 1) + "/" + year
             Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()*/

    }


}








