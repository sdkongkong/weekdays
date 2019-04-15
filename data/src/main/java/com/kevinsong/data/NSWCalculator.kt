package com.kevinsong.data

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields
import java.util.*

class NSWCalculator(startDate: LocalDate, endDate: LocalDate) : BasicCalculator(startDate, endDate) {

    public override fun isCustomHoliday(startDate: LocalDate): Boolean {
        //add custom holiday check here
        return false
    }
}


