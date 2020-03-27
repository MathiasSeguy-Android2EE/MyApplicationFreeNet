package com.android2ee.training.freenet.model
//
/**
 * Created by Mathias Seguy also known as Android2ee on 27/03/2020.
 * The goal of this class is to :
 */
class Circle(radius: Float) {
    /***********************************************************
     *  Object
     **********************************************************/
    companion object {
        val PI = 3.141592653589793238F
    }

    /***********************************************************
     *  Attributes
     **********************************************************/
    val circumference: Float
    val area: Float

    /***********************************************************
     *  Managing LifeCycle
     **********************************************************/
    init {
        circumference = (radius + radius) * PI
        area = radius * radius * PI
    }

}