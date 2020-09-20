package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)

        }
    }

    fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, SelectedYear, SelectedMonth, SelectedDayOfMonth ->
//                    Toast.makeText(this, "You have slected $year / $month /$dayOfMonth", Toast.LENGTH_SHORT).show()
                    val selectedDate = "$SelectedDayOfMonth/${SelectedMonth+1}/$SelectedYear"
                    tvSelectedDate.setText(selectedDate)

                    val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

                    val theDate = sdf.parse(selectedDate)

                    val seletedDateInMinute = theDate!!.time/60000

                    val crtDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    val crtInMinute = crtDate!!.time/60000

                    val pval = crtInMinute - seletedDateInMinute

                    tvSelectedDateInMinute.setText(pval.toString())

                }, year,
                month,
                day).show()
        }
    }
}

