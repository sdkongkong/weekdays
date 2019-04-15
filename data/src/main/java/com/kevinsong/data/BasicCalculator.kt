package com.kevinsong.data

import java.time.LocalDate

open class BasicCalculator(var startDate: LocalDate, var endDate: LocalDate) : Claculator {
    override fun getWeekdays(): Int {
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
            if (startDate.isChirstmasHoliday()) continue
                numWeekDays++
        }
        return numWeekDays
    }

    open fun isCustomHoliday(startDate: LocalDate): Boolean {
        return false
    }
}