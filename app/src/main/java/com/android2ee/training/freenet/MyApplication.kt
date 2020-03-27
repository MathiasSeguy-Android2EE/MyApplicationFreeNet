package com.android2ee.training.freenet

import android.app.Application

//
/**
 * Created by Mathias Seguy also known as Android2ee on 27/03/2020.
 * The goal of this class is to :
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: MyApplication
    }
}