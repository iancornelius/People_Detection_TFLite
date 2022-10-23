package iancornelius.android.peopledetection.algorithms

import android.graphics.Point
import kotlin.math.roundToInt

class MovementHandler {

    fun averageCenter(curr: Point, alpha: Double): Point {
        val x = alpha * curr.x + (1 - alpha) * previousLocation.x
        val y = alpha * curr.y + (1 - alpha) * previousLocation.y
        return Point(x.roundToInt(), y.roundToInt())
    }
    
    fun getDirection(curr: Point): Point {
        val dX = if (curr.x - previousLocation.x >= 1) {
            1
        } else if (curr.x - previousLocation.x <= -1) {
            -1
        } else {
            0
        }

        val dY = if (curr.y - previousLocation.y >= 1) {
            1
        } else if (curr.y - previousLocation.y <= -1) {
            -1
        } else {
            0
        }
        return Point(dX, dY)
    }

    companion object {
        var previousLocation = Point()
        var previousDirection = Point()
    }

}