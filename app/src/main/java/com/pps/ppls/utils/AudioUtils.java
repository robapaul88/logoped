package com.pps.ppls.utils;

import android.content.Context;
import android.text.TextUtils;

/**
 * Created by roba on 3/19/2015.
 */
public class AudioUtils {
    public static int getRawResIdByName(Context context, String name) {
        if (!TextUtils.isEmpty(name)) {
            return context.getResources().getIdentifier(name, "raw", context.getPackageName());
        }
        return 0;
    }

}
