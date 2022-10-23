package iancornelius.android.peopledetection.ui

import android.graphics.RectF
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import org.tensorflow.lite.task.vision.detector.Detection

private const val TAG = "ObjectDetector"

class ViewModel : ViewModel() {

    var detectedObjects = mutableStateListOf<HashMap<Int, RectF>>()


    fun setObject(objects: MutableList<Detection>) {
        detectedObjects.clear()
        val tmpDetections: HashMap<Int, RectF> = HashMap()
        for (obj in objects) {
            for (lbl in obj.categories) {
                Log.d(TAG, "Label: ${lbl.index} Rect: ${obj.boundingBox}")
                obj.let { tmpDetections.put(1, obj.boundingBox)}
            }
        }
        detectedObjects.add(tmpDetections)
    }
}