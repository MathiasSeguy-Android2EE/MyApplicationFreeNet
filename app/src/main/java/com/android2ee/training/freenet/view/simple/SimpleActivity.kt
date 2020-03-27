package com.android2ee.training.freenet.view.simple

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android2ee.training.freenet.R

//
/**
 * Created by Mathias Seguy also known as Android2ee on 27/03/2020.
 * The goal of this class is to :
 */
class SimpleActivity : AppCompatActivity() {
    /***********************************************************
     *  Attributes
     **********************************************************/
    /**     * Model       */
    lateinit var model: SimpleActivityViewModel

    /** Lazy load you widget */
    private val imvPicture: AppCompatImageView by lazy {
        findViewById<AppCompatImageView>(R.id.simple_image)
    }
    private val txvSimpleText: AppCompatTextView by lazy {
        findViewById<AppCompatTextView>(R.id.simple_text)
    }
    private val txvSimpleTextAsync: AppCompatTextView by lazy {
        findViewById<AppCompatTextView>(R.id.simple_async_text)
    }

    /** The name */
    val name: String by lazy {
        intent.getStringExtra("name")
    }

    /***********************************************************
     *  Managing LifeCycle
     **********************************************************/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //load the layout
        setContentView(R.layout.activity_simple)
        //load your model
        model = ViewModelProvider(this).get(SimpleActivityViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        //register as observer of the data you are interested in
        model.mDescriptionState.observe(this, Observer { desc -> txvSimpleTextAsync.text = desc })
        model.mPictureState.observe(this, Observer { bitmap ->
            if (bitmap != null) {
                imvPicture.setImageBitmap(bitmap)
            } else {
                imvPicture.setImageResource(R.drawable.ic_rowing)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        //Load your picture
        model.getPictureAsync(name)
        txvSimpleText.text = name
    }
}