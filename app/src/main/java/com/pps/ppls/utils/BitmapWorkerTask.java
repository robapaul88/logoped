package com.pps.ppls.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by paul on 20/02/15.
 */
public class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
    private final WeakReference<ImageView> imageViewReference;

    public BitmapWorkerTask(ImageView imageView) {
        imageViewReference = new WeakReference<>(imageView);
    }

    @Override
    protected Bitmap doInBackground(Integer... params) {
        return BitmapFactory.decodeResource(imageViewReference.get().getContext().getResources(), params[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap != null) {
            final ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}