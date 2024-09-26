package android.azadevs.docscanner

import android.os.Environment
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_JPEG
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_PDF
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.SCANNER_MODE_FULL
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import java.io.File
import java.io.IOException

/**
 * Created by : Azamat Kalmurzayev
 * 25/09/24
 */
object DocScannerUtilities {

    private val options = GmsDocumentScannerOptions.Builder()
        .setGalleryImportAllowed(true)
        .setScannerMode(SCANNER_MODE_FULL)
        .setPageLimit(10)
        .setResultFormats(RESULT_FORMAT_PDF, RESULT_FORMAT_JPEG)
        .build()

    val scanner = GmsDocumentScanning.getClient(options)


    private fun createAppDirectoryInDownloads(): File? {
        val downloadsDirectory =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val appDirectory = File(downloadsDirectory, "DocScanner")
        if (!appDirectory.exists()) {
            val directoryCreated = appDirectory.mkdir()
            if (!directoryCreated) {
                // Failed to create the directory
                return null
            }
        }

        return appDirectory
    }

     fun createFileInAppDirectory(fileName: String): File? {
        val appDirectory = createAppDirectoryInDownloads()
        if (appDirectory != null) {
            val file = File(appDirectory, fileName)
            try {
                if (!file.exists()) {
                    val fileCreated = file.createNewFile()
                    if (!fileCreated) {
                        // Failed to create the file
                        return null
                    }
                }
                return file
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return null
    }
}