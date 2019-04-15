package com.kevinsong.weekdays

import android.app.DatePickerDialog
import android.icu.util.LocaleData
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import com.kevinsong.data.Calculator
import com.kevinsong.data.NSWCalculator
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.format.DateTimeParseException
import java.util.*

class MainActivity : AppCompatActivity() {
    var startDate = LocalDate.now()
    var endDate = LocalDate.now()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setClickListener()
    }

    private fun setClickListener() {
        val cal = Calendar.getInstance();
        val mYear = cal.get(Calendar.YEAR);
        val mMonth = cal.get(Calendar.MONTH);
        val mDay = cal.get(Calendar.DAY_OF_MONTH);
        btn_select_start.setOnClickListener {
            // Launch Date Picker Dialog
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener() { datePicker: DatePicker, year: Int, monthIndex: Int, day: Int ->
                    val month = monthIndex + 1
                    et_start_date.setText("$year-$month-$day")
                    startDate = LocalDate.of(year, month, day)

                },
                mYear,
                mMonth,
                mDay
            )
            dpd.show()

        }
        btn_select_end.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener() { datePicker: DatePicker, year: Int, monthIndex: Int, day: Int ->
                    val month = monthIndex + 1;
                    et_end_date.setText("$year-$month-$day")
                    endDate = LocalDate.of(year, month, day)
                    calcWeekDays()
                },
                mYear,
                mMonth,
                mDay
            )
            dpd.show()
        }
    }

    private fun calcWeekDays() {
        val calc = NSWCalculator(startDate, endDate)
        tv_res.text = "the week days is ${calc.getWeekdays()}"


    }
}
