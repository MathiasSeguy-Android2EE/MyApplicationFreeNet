package com.android2ee.training.freenet.view.simple

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android2ee.training.freenet.repository.AndroidDatabase
import com.android2ee.training.freenet.repository.FileDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

// 
/** Created by Mathias Seguy also known as Android2ee on 11/03/2020.
 * The goal of this class is to :
 *
 */
class SimpleActivityViewModel : ViewModel(), CoroutineScope {

    companion object {
        val TAG = "SimpleActivityViewModel"
    }

    /***********************************************************
     *  Managing the incrementatin of the ticker
     *  Using Corutines
     **********************************************************/

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

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
        launch(Dispatchers.IO) {
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
        launch(Dispatchers.IO) {
            _mPictureStateInternal.postValue(FileDao.getPicture(name))
        }
    }

}