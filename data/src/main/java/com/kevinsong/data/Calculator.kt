package com.kevinsong.data

import java.util.*

class Calculator(val start:Date, val end:Date) {

    public fun getWeekDays():Int{
       val cStart = Calendar.getInstance()
        cStart.time = start
        val cEnd = Calendar.getInstance()
        cEnd.time = end
        val wStart= cStart.get(Calendar.DAY_OF_WEEK)
        cStart.add(Calendar.DAY_OF_WEEK, -wStart)
        val wEnd = cEnd.get(Calendar.DAY_OF_WEEK)
        cEnd.add(Calendar.DAY_OF_WEEK,-wEnd)
        return wStart - wEnd
    }

}
