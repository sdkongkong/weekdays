package com.kevinsong.data

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.MonthDay
import java.time.format.DateTimeFormatter
import java.util.*
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

 fun LocalDate.isNewYearHoliday(): Boolean {
    //1st Jan is always public holiday
    if (this.monthValue == NEW_YEAE.monthValue && this.dayOfMonth == NEW_YEAE.dayOfMonth
    ) return true
    //if New Year on Sunday, 2ed Jan is public holiday
    if (this.monthValue == DAY_AFTER_NEW_YEAE.monthValue && this.dayOfMonth == DAY_AFTER_NEW_YEAE.dayOfMonth)
        return NEW_YEAE.atYear(this.year).dayOfWeek == DayOfWeek.SUNDAY

    //if New Year on Satauday, 3rd Jan is public holiday
    if (this.monthValue == TWO_DAY_AFTER_NEW_YEAR.monthValue && this.dayOfMonth == TWO_DAY_AFTER_NEW_YEAR.dayOfMonth)
        return NEW_YEAE.atYear(this.year).dayOfWeek == DayOfWeek.SATURDAY
    return false
}

 fun LocalDate.isAustraliaDayHoliday(): Boolean {
    //26th Jan is always public holiday
    if (this.monthValue == AUSTRALIA_DAY.monthValue && this.dayOfMonth == AUSTRALIA_DAY.dayOfMonth) return true
    //if Australia day on Sunday, 27th Jan is public holiday
    if (this.monthValue == DAY_AFTER_AUSTRALIA_DAY.monthValue && this.dayOfMonth == DAY_AFTER_AUSTRALIA_DAY.dayOfMonth)
        return AUSTRALIA_DAY.atYear(this.year).dayOfWeek == DayOfWeek.SUNDAY

    //if Australia day on Satauday, 28 Jan is public holiday
    if (this.monthValue == TWO_DAY_AFTER_AUSTRALIA_DAY.monthValue && this.dayOfMonth == TWO_DAY_AFTER_AUSTRALIA_DAY.dayOfMonth)
        return AUSTRALIA_DAY.atYear(this.year).dayOfWeek == DayOfWeek.SATURDAY
    return false
}

 fun LocalDate.isAnzacDay(): Boolean {
    //25th April is always public holiday
    return this.monthValue == ANZAC_DAY.monthValue && this.dayOfMonth == ANZAC_DAY.dayOfMonth
}

 fun LocalDate.isWeekend(): Boolean {
    return this.dayOfWeek == DayOfWeek.SATURDAY || this.dayOfWeek == DayOfWeek.SUNDAY
}

//second Monday in June
 fun LocalDate.isQueuesBirthday(): Boolean {
    val ca1 = Calendar.getInstance()
    ca1.set(this.year, this.monthValue, this.dayOfMonth)
    ca1.minimalDaysInFirstWeek = 1
    val weekIndexInMonth = ca1.get(Calendar.WEEK_OF_MONTH)
    return weekIndexInMonth == 2 && this.dayOfWeek == DayOfWeek.MONDAY && this.month == Month.JUNE
}

//first Monday in October
 fun LocalDate.isLaborDay(): Boolean {
    val ca1 = Calendar.getInstance()
    ca1.set(this.year, this.monthValue, this.dayOfMonth)
    ca1.minimalDaysInFirstWeek = 7
    val weekIndexInMonth = ca1.get(Calendar.WEEK_OF_MONTH)
    return weekIndexInMonth == 1 && this.dayOfWeek == DayOfWeek.MONDAY && this.month == Month.OCTOBER
}

//ChristmasHoliday
 fun LocalDate.isChirstmasHoliday(): Boolean {
    if (this.monthValue == CHIRSTMAS_DAY.monthValue && this.dayOfMonth == CHIRSTMAS_DAY.dayOfMonth) return true
    if (this.monthValue == BOXING_DAY.monthValue && this.dayOfMonth == BOXING_DAY.dayOfMonth) return true
    val dayOfWeekOfBoxing = BOXING_DAY.atYear(this.year).dayOfWeek
    //if Boxingday on Weekend, 27thDec is public holiday
    if (this.monthValue == ONE_DAY_AFTER_BOXING.monthValue && this.dayOfMonth == ONE_DAY_AFTER_BOXING.dayOfMonth)
        return dayOfWeekOfBoxing == DayOfWeek.SUNDAY || dayOfWeekOfBoxing == DayOfWeek.SATURDAY

    //if Boxingday on Sunday, 28thDec is public holiday
    if (this.monthValue == TWO_DAY_AFTER_BOXING.monthValue && this.dayOfMonth == TWO_DAY_AFTER_BOXING.dayOfMonth)
        return dayOfWeekOfBoxing == DayOfWeek.SUNDAY
    return false
}

 fun LocalDate.isEasterHoliday(): Boolean {
    val goodFriday = getEasterSunday(this.year).minusDays(2)
    val easterMonday = getEasterSunday(this.year).plusDays(1)
    return this == goodFriday || this == easterMonday
}

//easter algorithm, code from stackoverflow
//https://stackoverflow.com/questions/26022233/calculate-the-date-of-easter-sunday/26022891
private fun getEasterSunday(year: Int): LocalDate {
    val a = year % 19
    val b = year / 100
    val c = year % 100
    val d = b / 4
    val e = b % 4
    val g = (8 * b + 13) / 25
    val h = (19 * a + b - d - g + 15) % 30
    val j = c / 4
    val k = c % 4
    val m = (a + 11 * h) / 319
    val r = (2 * e + 2 * j - k - h + m + 32) % 7
    val n = (h - m + r + 90) / 25
    val p = (h - m + r + n + 19) % 32
    System.out.print("mon:" + n + "day:" + p)
    return LocalDate.of(year, n, p);
}