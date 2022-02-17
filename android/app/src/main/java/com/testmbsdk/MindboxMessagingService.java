package com.testmbsdk;

import android.app.Activity;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.testmbsdk.MainActivity;

import java.util.HashMap;

import cloud.mindbox.mobile_sdk.Mindbox;

public class MindboxMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String token) {
        Mindbox.INSTANCE.updateFmsToken(getApplicationContext(), token);

        super.onNewToken(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Mindbox.INSTANCE.handleRemoteMessage(
                getApplicationContext(),
                remoteMessage,
                "mindbox_c",
                "Mindbox channel",
                android.R.drawable.ic_dialog_info,
                MainActivity.class,
                "Descr",
                new HashMap<>()
        );

        super.onMessageReceived(remoteMessage);
    }
}
