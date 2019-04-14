package com.kevinsong.data

import java.time.DayOfWeek
import java.time.LocalDate
import java.util.*

class Calculator(var startDate: LocalDate, val endDate: LocalDate) {

    public fun getWeekDays(): Int {
        var numWeekDays = 0;
        while (true) {
            startDate = startDate.plusDays(1)
            if (startDate >= endDate) break
            if (isNewYearHoliday(startDate)) continue
            if (isWeekend(startDate)) continue
            numWeekDays++
        }
        return numWeekDays
    }

    private fun isNewYearHoliday(curDate: LocalDate): Boolean {
        if (curDate.dayOfYear == LocalDate.parse("2019-01-01").dayOfYear) return true
        return false
    }

    private fun isWeekend(curDate: LocalDate): Boolean {
        return curDate.dayOfWeek == DayOfWeek.SATURDAY || curDate.dayOfWeek == DayOfWeek.SUNDAY
    }


}
