package com.qlabs.wordbook.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.qlabs.wordbook.notification.Notifications;

public class WordNotificationAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Notifications.showAddWordNotification(context);
    }
}
