package com.shohiebsense.timeutils

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TimePicker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val timeListener = object : TimePickerDialog.OnTimeSetListener {
        override fun onTimeSet(p0: TimePicker?, hourOfDay: Int, minute: Int) {
            val hourOfDayStr = Utils.Time.getStandardDigitsInTime(hourOfDay)
            var minutesStr = Utils.Time.getStandardDigitsInTime(minute)
            edit_time.setText("$hourOfDayStr:$minutesStr")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edit_time.showSoftInputOnFocus = false
        edit_time.setOnClickListener {
            showTimePicker()
        }
    }

    fun showTimePicker(){
        val calendar = Utils.Time.getCalendar()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = CustomTimePickerDialog(this, timeListener, hour, minute, true)
        timePickerDialog.show()
    }





}
