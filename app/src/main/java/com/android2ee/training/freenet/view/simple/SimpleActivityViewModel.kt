package com.android2ee.training.freenet.view.simple

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android2ee.training.freenet.MyApplication
import com.android2ee.training.freenet.repository.AndroidDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream

// 
/** Created by Mathias Seguy also known as Android2ee on 11/03/2020.
 * The goal of this class is to :
 *
 */
class SimpleActivityViewModel : ViewModel() {

    companion object {
        val TAG = "SimpleActivityViewModel"
    }

    /***********************************************************
     *  Attributes
     **********************************************************/

    var database: AndroidDatabase = AndroidDatabase()

    /***********************************************************
     * Constructors and exposed data to the view pattern
     ********************************************************* **/
    private var _mDescriptionStateInternal = MutableLiveData<String>()

    /**Exposed*/
    val mDescriptionState: LiveData<String>
        get() = _mDescriptionStateInternal

    private var _mPictureStateInternal = MutableLiveData<Bitmap>()

    /**Exposed*/
    val mPictureState: LiveData<Bitmap>
        get() = _mPictureStateInternal

    /**
     * Initialize your data
     */
    init {
        //load your data (there is no param, so go for it here)
        GlobalScope.launch(Dispatchers.IO) {
            _mDescriptionStateInternal.postValue(database.description)
        }
    }

    /***********************************************************
     *  Business Method
     **********************************************************/

    /**
     * Get your picture
     */
    fun getPictureAsync(name: String) {
        //create a non blocking coroutine in IO threads
        GlobalScope.launch(Dispatchers.IO) {
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
            _mPictureStateInternal.postValue(bitmap)
        }
    }

}