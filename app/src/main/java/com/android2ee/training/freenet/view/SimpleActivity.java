package com.android2ee.training.freenet.view;
// 

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.android2ee.training.freenet.R;
import com.android2ee.training.freenet.repository.AndroidDatabase;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Mathias Seguy also known as Android2ee on 27/03/2020.
 * The goal of this class is to :
 */
public class SimpleActivity extends Activity {

    AndroidDatabase database;
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        this.database = new AndroidDatabase(this);

        ((TextView) findViewById(R.id.simple_text)).setText(getName(this));
        imageView = (ImageView) findViewById(R.id.simple_image);

        final TextView asynchronousTextView = (TextView) findViewById(R.id.simple_async_text);
        //Since Froyo, we said you should not use asynctask
        //not only for the memory leaks
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                return database.getDescription();
            }

            @Override
            protected void onPostExecute(String result) {
                asynchronousTextView.setText(result);
            }
        }.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        imageView.setImageBitmap(getPicture());
    }

    public String getName(Context context) {
        String contactFormat = context.getString(R.string.contact_format); // Contact: %s
        return String.format(contactFormat, getIntent().getStringExtra("name"));
    }

    public Bitmap getPicture() {
        File image = new File(this.getCacheDir(), getIntent().getStringExtra("name") + ".jpg");
        Bitmap bitmap = null;
        try {
            byte[] buf = new byte[(int) image.length()];
            FileInputStream inputStream = new FileInputStream(image);
            inputStream.read(buf);
            bitmap = BitmapFactory.decodeByteArray(buf, 0, (int) image.length());
        } catch (Exception e) {
            Log.e("picture", e.getMessage());
        }

        return bitmap;
    }
}

