package iancornelius.android.peopledetection.ui

import android.graphics.Rect
import android.graphics.RectF
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.objects.DetectedObject
import org.tensorflow.lite.task.vision.detector.Detection

private const val TAG = "ObjectDetector"

class ViewModel : ViewModel() {

    var detectedObjects = mutableStateListOf<HashMap<Int, RectF>>()


    fun setObject(objects: MutableList<Detection>) {
        detectedObjects.clear()
        val tmpDetections: HashMap<Int, RectF> = HashMap()
        for (obj in objects) {
            val tmpData: HashMap<Int, RectF> = HashMap()
            for (lbl in obj.categories) {
                Log.d(TAG, "Label: ${lbl.index} Rect: ${obj.boundingBox}")
//                tmpData[lbl.index] = obj.boundingBox
                obj.let { tmpDetections.put(1, obj.boundingBox)}
            }
//            obj.trackingId?.let { tmpDetections.put(it, obj.boundingBox) }
//            Log.d(TAG, "Tracking ID: ${obj.trackingId}")
//            objectBoundingBox.add(obj.boundingBox)
        }
        detectedObjects.add(tmpDetections)
    }
}