package com.smarthealth.napchartandroiddev.napchart.domain

import java.time.LocalTime

data class Schedule(
    val name: String,
    val tst: LocalTime,
    val segments: List<Segment>
)
