package com.example.workload;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.onesignal.OSNotification;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

public class MyApp extends Application {
    MyBroadcastReceiver receiver;
    @Override
    public void onCreate() {
        super.onCreate();

        receiver=new MyBroadcastReceiver();
        IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver,intentFilter);


        // FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        // Logging set to help debug issues, remove before releasing your app.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        //OneSignal Initialization

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationReceivedHandler(new OneSignal.NotificationReceivedHandler() {
                    @Override
                    public void notificationReceived(OSNotification notification) {
                        //on notification recivied
                    }
                })
                .setNotificationOpenedHandler(new OneSignal.NotificationOpenedHandler() {
                    @Override
                    public void notificationOpened(OSNotificationOpenResult result) {
                        //act when we click on noifcation
                    }
                })
                .init();
    }
}
