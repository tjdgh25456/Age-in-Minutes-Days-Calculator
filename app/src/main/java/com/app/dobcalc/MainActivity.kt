package com.app.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvAgeInMinutes: TextView? = null
    private var tvAgeDay: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        tvAgeDay = findViewById(R.id.tvAgeDay)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }

    }

    private fun clickDatePicker() {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd =
            DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDayOfMonth ->

                    Toast.makeText(
                        this,
                        "Year was $selectedYear, month was ${selectedMonth + 1}, day of month was " +
                                "$selectedDayOfMonth", Toast.LENGTH_LONG
                    ).show()

                    val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"

                    tvSelectedDate?.text = selectedDate

                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                    val theDate = sdf.parse(selectedDate)
                    theDate?.let {
                        val selectedDateInMinutes = theDate.time / 60000
                        val selectedDateInDays = theDate.time / 86400000

                        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                        currentDate?.let {

                            val currentDateInMinutes = currentDate.time / 60000
                            val currentDateInDays = currentDate.time / 86400000

                            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                            val differenceInDays= currentDateInDays - selectedDateInDays


                            tvAgeInMinutes?.text = differenceInMinutes.toString()
                            tvAgeDay?.text = differenceInDays.toString()

                        }
                    }

                },
                year,
                month,
                day
            )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()


    }
}
















