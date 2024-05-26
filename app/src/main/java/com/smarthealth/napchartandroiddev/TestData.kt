package com.smarthealth.napchartandroiddev

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.smarthealth.napchartandroiddev.napchart.domain.Schedule
import com.smarthealth.napchartandroiddev.napchart.domain.Segment
import java.time.LocalTime

val dualCore1 = Schedule(
    name = "Dual Core 1",
    tst = LocalTime.of(5, 20),
    segments = listOf(
        Segment(
            LocalTime.of(21, 30),
            LocalTime.of(0, 50)
        ),
        Segment(
            LocalTime.of(5, 50),
            LocalTime.of(7, 30)
        ),

        Segment(
            LocalTime.of(14, 0),
            LocalTime.of(14, 20)
        )
    )
)

val radius = 150.dp
val stroke = 75.dp

val backColor = Color(0xff91C3FF)
val frontColor = Color(0xff3775D2)

val previewDarkBackgroundColor = Color(0xff1c1e1c)

