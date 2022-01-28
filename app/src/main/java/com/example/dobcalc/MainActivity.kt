package com.example.dobcalc

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var tvAgeInMinutes : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonDatePicker : Button = findViewById(R.id.buttonDatePicker)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        buttonDatePicker.setOnClickListener{
            clickDatePicker()

        }
    }

    private fun clickDatePicker()
    {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
         DatePickerDialog.OnDateSetListener{
             view,selectedyear,selectedmonth,selecteddayOfMonth ->

             Toast.makeText(this,"Year was $selectedyear, month was ${selectedmonth+1}, day was $selecteddayOfMonth",Toast.LENGTH_LONG).show()




             val selectedDate = "$selecteddayOfMonth/${selectedmonth+1}/$selectedyear/"

             tvSelectedDate?.text = selectedDate


             val sdf = java.text.SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

             val theDate = sdf.parse(selectedDate)
             theDate?.let{
                 val selectedDateInMinutes = theDate.time / 60000

                 val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                 // 1970년 1월 1일부터 지난 시간을 ms 단위로 알수있음
                 currentDate?.let{
                     val currentDateInMinutes = currentDate.time / 60000
                     val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                     tvAgeInMinutes?.text = differenceInMinutes.toString()
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