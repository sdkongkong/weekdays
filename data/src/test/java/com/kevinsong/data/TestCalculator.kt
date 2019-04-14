package com.kevinsong.data

import org.junit.Assert
import org.junit.Test
import java.time.LocalDate


class TestCalculator {

    //normal case for weekend
    @Test
    fun testWeekend() {
        val startDate = LocalDate.parse("2019-04-11")
        val endDate = LocalDate.parse("2019-04-16")
        val calc = Calculator(startDate, endDate)
        Assert.assertEquals(2, calc.getWeekDays())
    }

    //same day for start & end
    @Test
    fun testWeenendSameDay() {
        val startDate = LocalDate.parse("2019-04-11")
        val endDate = LocalDate.parse("2019-04-11")
        val calc = Calculator(startDate, endDate)
        Assert.assertEquals(0, calc.getWeekDays())
    }

    //start from weekend
    @Test
    fun testWeenendStartWeekend() {
        val startDate = LocalDate.parse("2019-04-06")
        val endDate = LocalDate.parse("2019-04-12")
        val calc = Calculator(startDate, endDate)
        Assert.assertEquals(4, calc.getWeekDays())
    }

}