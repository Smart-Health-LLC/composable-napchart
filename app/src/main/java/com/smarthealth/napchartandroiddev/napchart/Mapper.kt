package com.smarthealth.napchartandroiddev.napchart

import java.time.LocalTime

fun timeToGrad(time: LocalTime): Float {
    val minutes = time.hour * 60 + time.minute
    var grads = startPosInGrads + minutes * minuteInGrad
    if (grads >= 360)
        grads -= 360
    return grads.toFloat()
}

fun timeToGrad(minutes: Long): Float {
    var grads = minutes * minuteInGrad
    // kind of nonsense
    if (grads >= 360)
        grads -= 360
    return grads.toFloat()
}
