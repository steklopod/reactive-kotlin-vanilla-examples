package de.steklopod.sec09.helper

import java.time.LocalDateTime

data class RevenueReport(
    val revenue: Map<String, Double>,
    val localDateTime: LocalDateTime = LocalDateTime.now()
)
