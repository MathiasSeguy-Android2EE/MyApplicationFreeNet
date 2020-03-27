package com.android2ee.training.freenet.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.android2ee.training.freenet.MyApplication
import java.io.File
import java.io.FileInputStream

// 
/** Created by Mathias Seguy also known as Android2ee on 27/03/2020.
 * The goal of this class is to :
 *
 */
object FileDao {
    val TAG = "FileDao"
    fun getPicture(name: String): Bitmap? {
        val image = File(MyApplication.instance.getCacheDir(), "$name.jpg")
        var bitmap: Bitmap? = null
        try {
            val buf = ByteArray(image.length().toInt())
            val inputStream = FileInputStream(image)
            inputStream.read(buf)
            bitmap = BitmapFactory.decodeByteArray(buf, 0, image.length().toInt())
        } catch (e: Exception) {
            Log.e(TAG, "picture", e)
        }
        return bitmap
    }
}