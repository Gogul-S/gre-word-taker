package com.qlabs.wordbook.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import com.qlabs.wordbook.R;

public class NotificationChannels {

    public static final String WORD_NOTIFICATION_CHANNEL = "1";

    public static void createWordsNotificationChannel(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.word_notifications);
            String description = context.getString(R.string.used_for_displaying_notifications_related_to_words);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(WORD_NOTIFICATION_CHANNEL, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}

