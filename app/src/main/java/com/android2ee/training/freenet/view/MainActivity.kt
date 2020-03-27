package com.android2ee.training.freenet.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android2ee.training.freenet.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.btnLaunchSimpleActivity).setOnClickListener {
            Intent(applicationContext, SimpleActivity::class.java)
                    .apply { putExtra("name", "John Doe") }
                    .also { startActivity(it) }
        }
    }
}