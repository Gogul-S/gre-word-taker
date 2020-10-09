package com.qlabs.wordbook.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.common.util.SharedPreferencesUtils;

public class CacheManager {
    public static void setFirebaseId(Context context, String firebaseInstanceId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("appPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("firebaseInstanceId", firebaseInstanceId);
        editor.commit();
    }

    public static String getFirebaseId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("appPreferences", Context.MODE_PRIVATE);
        return sharedPreferences.getString("firebaseInstanceId",null);
    }
}
