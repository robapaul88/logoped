package com.pps.ppls.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by paul on 14/02/15.
 */
public class ImageUtils {
    private static final String TAG = ImageUtils.class.getSimpleName();
    private static final int IMAGE_EXTENSION_LENGTH = 4;

    public static Bitmap getBitmapFromAsset(Context ctxt, String strName) {
        AssetManager assetManager = ctxt.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(strName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(istr);
    }

    public static int getImageResIdByName(Context context, String name) {
        Log.d(TAG, "Searching for: " + name);
        if (name != null && name.length() > IMAGE_EXTENSION_LENGTH) {
            return context.getResources().getIdentifier(name.substring(0, name.length() - IMAGE_EXTENSION_LENGTH), "drawable", context.getPackageName());
        }
        return 0;
    }

    private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Context context, String name,
                                                         int reqWidth, int reqHeight) {
        Resources res = context.getResources();
        int resId = res.getIdentifier(name.substring(0, name.length() - IMAGE_EXTENSION_LENGTH), "drawable", context.getPackageName());

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
