package com.android2ee.training.freenet.repository;
// 

import android.content.Context;

import androidx.room.Database;

import java.lang.annotation.Annotation;

/**
 * Created by Mathias Seguy also known as Android2ee on 27/03/2020.
 * The goal of this class is to fake a Database for the test project
 */
public class AndroidDatabase implements Database {

    public AndroidDatabase(Context ctx) {
        //we don't care
    }

    public final String getDescription() {
        return "A description";
    }

    @Override
    public Class<?>[] entities() {
        return new Class[0];
    }

    @Override
    public Class<?>[] views() {
        return new Class[0];
    }

    @Override
    public int version() {
        return 0;
    }

    @Override
    public boolean exportSchema() {
        return false;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
