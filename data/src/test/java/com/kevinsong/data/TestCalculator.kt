package com.kevinsong.data

import org.junit.Assert
import org.junit.Test
import sun.util.calendar.BaseCalendar
import java.text.SimpleDateFormat
import java.util.*


class TestCalculator {

    @Test
    fun testWithOutWeekend() {
        val startStr = "11/04/2019"
        val endStr = "16/04/2019"

        val curFormater = SimpleDateFormat("dd/MM/yyyy")
        val startDate = curFormater.parse(startStr)
        val endDate = curFormater.parse(endStr);
        val calc = Calculator(startDate, endDate)
        Assert.assertEquals(calc.getWeekDays(), 2)
    }
}