package com.smarthealth.napchartandroiddev.napchart

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.text.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.smarthealth.napchartandroiddev.*
import com.smarthealth.napchartandroiddev.napchart.domain.Schedule
import java.time.Duration
import java.time.LocalTime

@Composable
@Preview(
    showBackground = true, heightDp = 750, widthDp = 550,
)
fun ScheduleComponentPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScheduleComponent(dualCore1, radius, stroke, frontColor, backColor, true)
    }
}

data class UiScheduleSegment(
    var startTime: LocalTime,
    var endTime: LocalTime,
    var centerSegmentRad: Float,
    var startEdgeRad: Float,
    var endEdgeRad: Float
)

//class ScheduleUiState(schedule: Schedule) {
//    lateinit var segments: List<UiScheduleSegment>
//
//    init {
//        schedule.segments.forEach {
//
//        }
//    }

    @Composable
    fun ScheduleComponent(
        schedule: Schedule,
        componentRadius: Dp,
        strokeWidth: Dp,
        frontColor: Color,
        backColor: Color,
        showDayBaseTimes: Boolean = false,
        componentPaddingX: Dp = 60.dp, // the padding between schedule circle and the canvas edge horizontally
        componentPaddingY: Dp = 30.dp, // the padding between schedule circle and the canvas edge vertically
        baseTextOffset: Int = 40 // offset between schedule circle and base day time text indicators
    ) {
        val textMeasurer = rememberTextMeasurer()
        val canvasWidth = componentPaddingX * 2 + componentRadius * 2 + strokeWidth
        val canvasHeight = componentPaddingY * 2 + componentRadius * 2 + strokeWidth
        var anglesInfo = remember {
            mutableStateOf<List<List<Float>>>(emptyList())
        }

        Canvas(
            modifier = Modifier
                .requiredWidth(canvasWidth)
                .requiredHeight(canvasHeight)
                .border(BorderStroke(width = 1.dp, color = Color.Black))
        ) {

            // shows base day times eg. 00, 04, 08, 12, 16, 20
            // todo how to show any hour?...
            // todo draw invisible outer circle and cycle by its invisible segments by hours using segment edge positions to draw text at any hour position
            if (showDayBaseTimes) {

                // add midnight text indicator
                val measuredMidnightText =
                    textMeasurer.measure(
                        AnnotatedString("midnight"),
                    )
                drawText(
                    textLayoutResult = measuredMidnightText,
                    topLeft = Offset(
                        size.width / 2 - measuredMidnightText.size.width / 2, // center width
                        componentPaddingY.toPx() - measuredMidnightText.size.height / 2 - baseTextOffset
                    ),
                )

                // add noon text indicator
                val measureNoonText =
                    textMeasurer.measure(
                        AnnotatedString("noon"),
                    )
                drawText(
                    topLeft = Offset(
                        size.width / 2 - measureNoonText.size.width / 2, // center width
                        size.height - measureNoonText.size.height / 2 - componentPaddingY.toPx() + baseTextOffset
                    ),
                    textLayoutResult = measureNoonText,
                )

                // most right time text indicator
                val mostLeftText =
                    textMeasurer.measure(
                        AnnotatedString("06"),
                    )
                drawText(
                    topLeft = Offset(
                        size.width - mostLeftText.size.width / 2 - componentPaddingX.toPx() + baseTextOffset,
                        size.height / 2 - mostLeftText.size.height / 2
                    ),
                    textLayoutResult = mostLeftText,
                )

                // most right time text indicator
                val mostRightText =
                    textMeasurer.measure(
                        AnnotatedString("06"),
                    )
                drawText(
                    topLeft = Offset(
                        size.width - mostLeftText.size.width / 2 - componentPaddingX.toPx() + baseTextOffset,
                        size.height / 2 - mostLeftText.size.height / 2

                    ),
                    textLayoutResult = mostLeftText,
                )
            }

            // inset to circle radius baseline to draw arcs with precision on base background circle
            inset(
                size.width / 2 - componentRadius.toPx(),
                size.height / 2 - componentRadius.toPx()
            ) {

                // background schedule circle
                drawCircle(
                    color = backColor,
                    radius = componentRadius.toPx(),
                    center = center,
                    style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
                )

                //experiment to understand coordinates system
//            drawCircle(
//                color = Color.Red,
//                center = Offset(x = 0f, y = 0f),
//                radius = 45f
//            )


                // base radius indication : experiment
//            drawCircle(
//                color = Color.Red,
//                center = center,
//                radius = componentRadius.toPx(),
//                style = Stroke(width = 1.toFloat(), cap = StrokeCap.Round)
//            )

                // schedule circle's sleep segments
                schedule.segments.forEach {
                    val minutes: Long
                    if (!it.start.isBefore(it.end)) {
                        minutes =
                            Duration.between(it.start, LocalTime.MAX)
                                .toMinutes() + Duration.between(
                                LocalTime.MIN,
                                it.end
                            ).toMinutes() + 1
                    } else {
                        val duration = Duration.between(it.start, it.end)
                        minutes = duration.toMinutes()
                    }
                    // without size parameter the arc is building upon full size circle
                    drawArc(
                        startAngle = timeToGrad(it.start),
                        sweepAngle = timeToGrad(minutes),
                        useCenter = false,
                        color = frontColor,
                        style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Butt)
                    )
                }
            }
        }
    }
//}

