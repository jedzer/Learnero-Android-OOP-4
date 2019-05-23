package com.jedzer.file;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.jedzer.learnero.SettingsActivity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.ContentValues.TAG;

public class FileImage {

    public static Bitmap getBitmapFromURL(String imageUrl) {
        try {
            return Picasso.get().load(imageUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void writeBitmapToSD(String aFileName, Bitmap aBitmap) {
        aFileName = "/Learnero/" + aFileName.replace('/', '_');
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return;
        }

        File sdPath = Environment.getExternalStorageDirectory();
        File sdFile = new File(sdPath, aFileName);

        if (sdFile.exists()) {
            sdFile.delete();
        }

        try {
            FileOutputStream out = new FileOutputStream(sdFile);
            aBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Bitmap loadImageFromSD(String aFileName) {
        aFileName = "/Learnero/" + aFileName.replace('/', '_');
        Bitmap result = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                FileInputStream fis = new FileInputStream(new File(Environment.getExternalStorageDirectory(), aFileName));
                result = BitmapFactory.decodeStream(fis);
                fis.close();
            } catch (FileNotFoundException e) {
                Log.d(TAG, "loadImageFromSD: " + e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
