package com.kevinsong.data

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.MonthDay
import java.time.format.DateTimeFormatter

class Calculator(var startDate: LocalDate, val endDate: LocalDate) {

    private val sdf = DateTimeFormatter.ofPattern("MM-dd")
    private val NEW_YEAE = MonthDay.parse("01-01", sdf)
    private val DAY_AFTER_NEW_YEAE = MonthDay.parse("01-02", sdf)
    private val TWO_DAY_AFTER_NEW_YEAR = MonthDay.parse("01-03", sdf)

    private val AUSTRALIA_DAY = MonthDay.parse("01-26", sdf)
    private val DAY_AFTER_AUSTRALIA_DAY = MonthDay.parse("01-27", sdf)
    private val TWO_DAY_AFTER_AUSTRALIA_DAY = MonthDay.parse("01-28", sdf)

    private val ANZAC_DAY = MonthDay.parse("04-25", sdf)


    public fun getWeekDays(): Int {
        var numWeekDays = 0;
        while (true) {
            startDate = startDate.plusDays(1)
            if (startDate >= endDate) break
            if (isNewYearHoliday(startDate)) continue
            if (isAustraliaDayHoliday(startDate)) continue
            if (isAnzacDay(startDate)) continue
            if (isWeekend(startDate)) continue
            numWeekDays++
        }
        return numWeekDays
    }

    private fun isNewYearHoliday(curDate: LocalDate): Boolean {
        //1st Jan is always public holiday
        if (curDate.monthValue == NEW_YEAE.monthValue && curDate.dayOfMonth == NEW_YEAE.dayOfMonth
        ) return true
        //if New Year on Sunday, 2ed Jan is public holiday
        if (curDate.monthValue == DAY_AFTER_NEW_YEAE.monthValue && curDate.dayOfMonth == DAY_AFTER_NEW_YEAE.dayOfMonth)
            return NEW_YEAE.atYear(curDate.year).dayOfWeek == DayOfWeek.SUNDAY

        //if New Year on Satauday, 3rd Jan is public holiday
        if (curDate.monthValue == TWO_DAY_AFTER_NEW_YEAR.monthValue && curDate.dayOfMonth == TWO_DAY_AFTER_NEW_YEAR.dayOfMonth)
            return NEW_YEAE.atYear(curDate.year).dayOfWeek == DayOfWeek.SATURDAY
        return false
    }

    private fun isAustraliaDayHoliday(curDate: LocalDate): Boolean {
        //26th Jan is always public holiday
        if (curDate.monthValue == AUSTRALIA_DAY.monthValue && curDate.dayOfMonth == AUSTRALIA_DAY.dayOfMonth
        ) return true
        //if Australia day on Sunday, 27th Jan is public holiday
        if (curDate.monthValue == DAY_AFTER_AUSTRALIA_DAY.monthValue && curDate.dayOfMonth == DAY_AFTER_AUSTRALIA_DAY.dayOfMonth)
            return AUSTRALIA_DAY.atYear(curDate.year).dayOfWeek == DayOfWeek.SUNDAY

        //if Australia day on Satauday, 28 Jan is public holiday
        if (curDate.monthValue == TWO_DAY_AFTER_AUSTRALIA_DAY.monthValue && curDate.dayOfMonth == TWO_DAY_AFTER_AUSTRALIA_DAY.dayOfMonth)
            return AUSTRALIA_DAY.atYear(curDate.year).dayOfWeek == DayOfWeek.SATURDAY
        return false
    }

    private fun isAnzacDay(curDate: LocalDate): Boolean {
        //25th April is always public holiday
        return curDate.monthValue == ANZAC_DAY.monthValue && curDate.dayOfMonth == ANZAC_DAY.dayOfMonth
    }

    private fun isWeekend(curDate: LocalDate): Boolean {
        return curDate.dayOfWeek == DayOfWeek.SATURDAY || curDate.dayOfWeek == DayOfWeek.SUNDAY
    }
}
