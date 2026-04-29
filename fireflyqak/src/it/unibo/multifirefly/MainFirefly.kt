package it.unibo.multifirefly

import it.unibo.kactor.*
import unibo.basicomm23.utils.CommUtils

fun main() {
    CommUtils.outcyan("MainFirefly | starts")

    val rows = 3
    val cols = 3

    (0 until rows).forEach { r ->
        (0 until cols).forEach { c ->
            Firefly("firefly_${r}_${c}", r, c)
        }
    }

    CommUtils.outcyan("MainFirefly | all fireflies started")
    
    // tieni vivo il processo
    while(true) {
        Thread.sleep(10000)
    }
}