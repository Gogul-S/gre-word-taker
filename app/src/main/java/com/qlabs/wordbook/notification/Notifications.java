package com.qlabs.wordbook.notification;

import android.app.NotificationManager;
import android.content.Context;

import androidx.core.app.NotificationCompat;

import com.qlabs.wordbook.R;

public class Notifications {

    public static void showAddWordNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NotificationChannels.WORD_NOTIFICATION_CHANNEL);
        builder.setSmallIcon(R.drawable.app_icon);
        builder.setContentTitle("Word Book");
        builder.setContentText("Add new Word");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }
}
