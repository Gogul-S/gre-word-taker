package com.qlabs.wordbook.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.qlabs.wordbook.cache.CacheManager;

public class FireBaseNotificationService extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d("NWTK", "onNewToken: "+s);
        CacheManager.setFirebaseId(this,s);
    }
}
