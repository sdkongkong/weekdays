package com.kevinsong.data

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.MonthDay
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields
import java.util.*

class Calculator(var startDate: LocalDate, val endDate: LocalDate) {

    private val sdf = DateTimeFormatter.ofPattern("MM-dd")
    private val NEW_YEAE = MonthDay.parse("01-01", sdf)
    private val DAY_AFTER_NEW_YEAE = MonthDay.parse("01-02", sdf)
    private val TWO_DAY_AFTER_NEW_YEAR = MonthDay.parse("01-03", sdf)

    private val AUSTRALIA_DAY = MonthDay.parse("01-26", sdf)
    private val DAY_AFTER_AUSTRALIA_DAY = MonthDay.parse("01-27", sdf)
    private val TWO_DAY_AFTER_AUSTRALIA_DAY = MonthDay.parse("01-28", sdf)

    private val CHIRSTMAS_DAY = MonthDay.parse("12-25", sdf)
    private val BOXING_DAY = MonthDay.parse("12-26", sdf)
    private val ONE_DAY_AFTER_BOXING = MonthDay.parse("12-27", sdf)
    private val TWO_DAY_AFTER_BOXING = MonthDay.parse("12-28", sdf)

    private val ANZAC_DAY = MonthDay.parse("04-25", sdf)


    public fun getWeekDays(): Int {
        var numWeekDays = 0;
        while (true) {
            startDate = startDate.plusDays(1)
            if (startDate >= endDate) break
            if (isNewYearHoliday(startDate)) continue
            if (isAustraliaDayHoliday(startDate)) continue
            if (isAnzacDay(startDate)) continue
            if (isQueuesBirthday(startDate)) continue
            if (isLaborDay(startDate)) continue
            if (isChirstmasHoliday(startDate)) continue
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
        if (curDate.monthValue == AUSTRALIA_DAY.monthValue && curDate.dayOfMonth == AUSTRALIA_DAY.dayOfMonth) return true
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

    //second Monday in June
    private fun isQueuesBirthday(curDate: LocalDate): Boolean {
        val ca1 = Calendar.getInstance()
        ca1.set(curDate.year, curDate.monthValue, curDate.dayOfMonth)
        ca1.minimalDaysInFirstWeek = 1
        val weekIndexInMonth = ca1.get(Calendar.WEEK_OF_MONTH)
        return weekIndexInMonth == 2 && curDate.dayOfWeek == DayOfWeek.MONDAY && curDate.month == Month.JUNE
    }

    //first Monday in October
    private fun isLaborDay(curDate: LocalDate): Boolean {
        val ca1 = Calendar.getInstance()
        ca1.set(curDate.year, curDate.monthValue, curDate.dayOfMonth)
        ca1.minimalDaysInFirstWeek = 7
        val weekIndexInMonth = ca1.get(Calendar.WEEK_OF_MONTH)
        return weekIndexInMonth == 1 && curDate.dayOfWeek == DayOfWeek.MONDAY && curDate.month == Month.OCTOBER
    }

    //ChristmasHoliday
    private fun isChirstmasHoliday(curDate: LocalDate): Boolean {
        if (curDate.monthValue == CHIRSTMAS_DAY.monthValue && curDate.dayOfMonth == CHIRSTMAS_DAY.dayOfMonth) return true
        if (curDate.monthValue == BOXING_DAY.monthValue && curDate.dayOfMonth == BOXING_DAY.dayOfMonth) return true
        val dayOfWeekOfBoxing = BOXING_DAY.atYear(curDate.year).dayOfWeek
        //if Boxingday on Weekend, 27thDec is public holiday
        if (curDate.monthValue == ONE_DAY_AFTER_BOXING.monthValue && curDate.dayOfMonth == ONE_DAY_AFTER_BOXING.dayOfMonth)
            return dayOfWeekOfBoxing == DayOfWeek.SUNDAY || dayOfWeekOfBoxing == DayOfWeek.SATURDAY

        //if Boxingday on Sunday, 28thDec is public holiday
        if (curDate.monthValue == TWO_DAY_AFTER_BOXING.monthValue && curDate.dayOfMonth == TWO_DAY_AFTER_BOXING.dayOfMonth)
            return dayOfWeekOfBoxing == DayOfWeek.SUNDAY
        return false
    }
}
