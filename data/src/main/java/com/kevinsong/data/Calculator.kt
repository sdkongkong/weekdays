package com.kevinsong.data

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields
import java.util.*

class Calculator(var startDate: LocalDate, val endDate: LocalDate) {

    public fun getWeekDays(): Int {
        var numWeekDays = 0;
        while (true) {
            startDate = startDate.plusDays(1)
            if (startDate >= endDate) break
            if (startDate.isWeekend()) continue
            if (startDate.isNewYearHoliday()) continue
            if (startDate.isAustraliaDayHoliday()) continue
            if (startDate.isEasterHoliday()) continue
            if (startDate.isAnzacDay()) continue
            if (startDate.isQueuesBirthday()) continue
            if (startDate.isLaborDay()) continue
            if (startDate.isChirstmasHoliday()) continue
            numWeekDays++
        }
        return numWeekDays
    }
}
