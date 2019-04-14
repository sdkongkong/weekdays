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
    fun testWeekendSameDay() {
        val startDate = LocalDate.parse("2019-04-11")
        val endDate = LocalDate.parse("2019-04-11")
        val calc = Calculator(startDate, endDate)
        Assert.assertEquals(0, calc.getWeekDays())
    }

    //start from weekend
    @Test
    fun testWeekendStartWeekend() {
        val startDate = LocalDate.parse("2019-04-06")
        val endDate = LocalDate.parse("2019-04-12")
        val calc = Calculator(startDate, endDate)
        Assert.assertEquals(4, calc.getWeekDays())
    }

    //new year on weekdays
    @Test
    fun testNewYear(){
        val startDate = LocalDate.parse("2018-12-31")
        val endDate = LocalDate.parse("2019-01-03")
        val calc = Calculator(startDate, endDate)
        Assert.assertEquals(1, calc.getWeekDays())
    }
    //new year on Sunday
    @Test
    fun testNewYearOnSunday(){
        val startDate = LocalDate.parse("2016-12-31")
        val endDate = LocalDate.parse("2017-01-03")
        val calc = Calculator(startDate, endDate)
        Assert.assertEquals(0, calc.getWeekDays())
    }

    //new year on Sunday
    @Test
    fun testNewYearOnSaturday(){
        val startDate = LocalDate.parse("2010-12-31")
        val endDate = LocalDate.parse("2011-01-03")
        val calc = Calculator(startDate, endDate)
        Assert.assertEquals(0, calc.getWeekDays())
    }


}