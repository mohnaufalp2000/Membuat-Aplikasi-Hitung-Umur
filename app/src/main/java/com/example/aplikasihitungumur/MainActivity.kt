package com.example.aplikasihitungumur

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import org.joda.time.PeriodType
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        date_birth.setOnClickListener {
            val date = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                date_birth.setText("" + dayOfMonth + "/" + month + "/" + year)
            }, year, month, day)
            date.show()
        }

        date_now.setOnClickListener {
            val date = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                date_now.setText("" + dayOfMonth + "/" + month + "/" + year)
            }, year, month, day)
            date.show()
        }

        calculate.setOnClickListener {
            val sDate = date_birth.text.toString()
            val eDate = date_now.text.toString()
            val format = SimpleDateFormat("dd/MM/yyyy")

            try {
                val date1 = format.parse(sDate)
                val date2 = format.parse(eDate)

                val startDate : Long = date1.time
                val endDate : Long = date2.time

                if (startDate<=endDate){
                    val period = org.joda.time.Period(startDate, endDate, PeriodType.yearMonthDay())
                    val years = period.years
                    val months = period.months
                    val days = period.days

                    Hasil.setText("${years} Years ${months} Months ${days} Days ")
                }
                else{
                    Toast.makeText(applicationContext, "Salah Lurr", Toast.LENGTH_LONG).show()
                }

            } catch (e : ParseException){
                e.printStackTrace()
            }
        }

    }
}