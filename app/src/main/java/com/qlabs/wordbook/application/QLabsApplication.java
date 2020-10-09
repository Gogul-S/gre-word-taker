package com.qlabs.wordbook.application;

import android.app.Application;

import com.qlabs.wordbook.alarm.Alarms;
import com.qlabs.wordbook.notification.NotificationChannels;

public class QLabsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationChannels.createWordsNotificationChannel(this);
        Alarms.setWordAlarm(this);
    }
}
