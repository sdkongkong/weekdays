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

    //new year on Staturday
    @Test
    fun testNewYearOnSaturday(){
        val startDate = LocalDate.parse("2010-12-31")
        val endDate = LocalDate.parse("2011-01-03")
        val calc = Calculator(startDate, endDate)
        Assert.assertEquals(0, calc.getWeekDays())
    }

    //Australia day on weekdays
    @Test
    fun testAustraliaDay(){
        val startDate = LocalDate.parse("2018-01-24")
        val endDate = LocalDate.parse("2018-01-30")
        val calc = Calculator(startDate, endDate)
        Assert.assertEquals(2, calc.getWeekDays())
    }

    //Australia day on Sunday
    @Test
    fun testAustraliaDayOnSunday(){
        val startDate = LocalDate.parse("2014-01-25")
        val endDate = LocalDate.parse("2014-01-28")
        val calc = Calculator(startDate, endDate)
        Assert.assertEquals(0, calc.getWeekDays())
    }

    //Australia day on Staturday
    @Test
    fun testAustraliaDayOnSaturday(){
        val startDate = LocalDate.parse("2019-01-25")
        val endDate = LocalDate.parse("2019-01-30")
        val calc = Calculator(startDate, endDate)
        Assert.assertEquals(1, calc.getWeekDays())
    }


    //AnzacDay on weekdays
    @Test
    fun testAnzacDay(){
        val startDate = LocalDate.parse("2019-04-23")
        val endDate = LocalDate.parse("2019-04-26")
        val calc = Calculator(startDate, endDate)
        Assert.assertEquals(1, calc.getWeekDays())
    }

    //AnzacDay on weekend, no make up for Anzac day even in weekend
    @Test
    fun testAnzacDayOnWeekend(){
        val startDate = LocalDate.parse("2015-04-24")
        val endDate = LocalDate.parse("2015-04-28")
        val calc = Calculator(startDate, endDate)
        Assert.assertEquals(1, calc.getWeekDays())
    }

    //queen's birthday
    @Test
   fun testQueensBirthday(){
        val startDate = LocalDate.parse("2019-06-06")
        val endDate = LocalDate.parse("2019-06-11")
        val calc = Calculator(startDate, endDate)
        Assert.assertEquals(1, calc.getWeekDays())
    }


    //labour day
    @Test
    fun testLabourDay(){
        val startDate = LocalDate.parse("2019-10-04")
        val endDate = LocalDate.parse("2019-10-09")
        val calc = Calculator(startDate, endDate)
        Assert.assertEquals(1, calc.getWeekDays())
    }


    //chirstmax
    @Test
    fun testChirstmaxHoliday(){
        val startDate = LocalDate.parse("2019-12-23")
        val endDate = LocalDate.parse("2019-12-27")
        val calc = Calculator(startDate, endDate)
        Assert.assertEquals(1, calc.getWeekDays())
    }

    //easter
    @Test
    fun testEasterHoliday(){
        val startDate = LocalDate.parse("2019-04-18")
        val endDate = LocalDate.parse("2019-04-29")
        val calc = Calculator(startDate, endDate)
        //only ask 3 days annual leave, you get 10 days off
        Assert.assertEquals(3, calc.getWeekDays())
    }

}