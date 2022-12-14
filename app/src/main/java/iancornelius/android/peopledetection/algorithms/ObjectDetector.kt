package iancornelius.android.peopledetection.algorithms

import android.annotation.SuppressLint
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import iancornelius.android.peopledetection.MainActivity
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.vision.detector.Detection
import org.tensorflow.lite.task.vision.detector.ObjectDetector

class ObjectDetector(private val onObjectDetected: (MutableList<Detection>) -> Unit) : ImageAnalysis.Analyzer {

    val localModel = "lite-model_efficientdet_lite2_detection_metadata_1.tflite"

    val baseOptions = BaseOptions.builder().useGpu().build()

    var options: ObjectDetector.ObjectDetectorOptions? =
        ObjectDetector.ObjectDetectorOptions.builder()
            .setBaseOptions(baseOptions)
            .setScoreThreshold(0.6f)
            .build()


    var detector: ObjectDetector =
        ObjectDetector.createFromFileAndOptions(MainActivity.appContext, localModel, options)

    private var running = false

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(image: ImageProxy) {
        if (running) {
            image.close()
            return
        }

        image.image?.let {
            running = true
            val mediaImage = image.image
            if(mediaImage != null) {
                val inputImage = InputImage.fromMediaImage(mediaImage, image.imageInfo.rotationDegrees)
                val tensorImage = TensorImage(DataType.UINT8)
                tensorImage.load(inputImage.mediaImage)
                val results = detector.detect(tensorImage)
                onObjectDetected.invoke(results)
                running = false
                image.close()
            }
        }
    }

}