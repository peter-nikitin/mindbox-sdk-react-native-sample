package com.testmbsdk;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;

import cloud.mindbox.mobile_sdk.Mindbox;

public class MindboxMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String token) {

        // Передайте в Mindbox SDK полученный токен
        Mindbox.INSTANCE.updateFmsToken(getApplicationContext(), token);

        super.onNewToken(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.i("Message", remoteMessage.toString() );
        // Обработайте полученные пуш
//        Mindbox.INSTANCE.handleRemoteMessage(
//                getApplicationContext(),
//                remoteMessage,
//                "notification_channel_id",
//                "notification_channel_name",
//                R.mipmap.ic_launcher, // икнока приложения для push уведомлений
//                MainActivity.class,
//                "notification_channel_description",
//                new HashMap<>()
//        );

        super.onMessageReceived(remoteMessage);
    }
}