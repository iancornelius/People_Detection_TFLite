package iancornelius.android.peopledetection.ui

import android.graphics.RectF
import android.graphics.Typeface
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas

private const val TAG = "Boundaries"

class Boundaries {

    @Composable
    fun displayBounds(
        objects: SnapshotStateList<HashMap<Int, RectF>>,
        labels: List<String>
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            for (obj in objects) {
                for (item in obj) {
                    Log.d(TAG, "Key: ${labels[item.key]} \n Value: ${item.value}")

                    drawContext.canvas.nativeCanvas.apply {
                        drawText(
                            "Object ID: ${labels[item.key]}",
                            item.value.centerX(),
                            item.value.centerY(),
                            textPaint
                        )
                    }
                    drawRect(
                        color = Color.Red,
                        size = Size(
                            item.value.height(),
                            item.value.width()
                        ),
                        topLeft = Offset(item.value.top, item.value.left),
                        style = Stroke(10f)
                    )
                    drawCircle(
                        color = Color.Red,
                        radius = 10F,
                        center = Offset(item.value.centerX(), item.value.centerY()),
                        style = Stroke(3f)
                    )
                }
            }
        }
    }

    companion object {
        private val textPaint = Paint().asFrameworkPaint().apply {
            isAntiAlias = true
            textSize = 32F
            color = android.graphics.Color.RED
            typeface = Typeface.create(Typeface.MONOSPACE, Typeface.BOLD)
        }
    }

}