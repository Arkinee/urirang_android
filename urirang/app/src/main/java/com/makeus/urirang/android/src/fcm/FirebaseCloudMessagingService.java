package com.makeus.urirang.android.src.fcm;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.makeus.urirang.android.R;
import com.makeus.urirang.android.src.splash.SplashActivity;
import com.makeus.urirang.android.src.splash.interfaces.FcmView;

import java.util.HashMap;

public class FirebaseCloudMessagingService extends FirebaseMessagingService implements FcmView {

    public FirebaseCloudMessagingService() {

    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);

        HashMap<String, Object> params = new HashMap<>();
        params.put("fcmToken", s);
//        final SplashService fcmService = new SplashService(this, this, params);
//        fcmService.tryPatchFcmToken();

    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getData().size() > 0) {
            sendNotification(remoteMessage);
            return;
        }

        if (remoteMessage.getNotification() != null) {
            sendNotification(remoteMessage);
        }
    }

    boolean isRunning() {
        boolean tf;
        ActivityManager.RunningAppProcessInfo myProcess = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(myProcess);
        tf = myProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
        return tf;
    }

    private void sendNotification(RemoteMessage remoteMessage) {
        // foreground
        String title = remoteMessage.getData().get("title");
        String message = remoteMessage.getData().get("message");

        Intent notificationIntent;
        PendingIntent pendingIntent;

        notificationIntent = new Intent(getApplicationContext(), SplashActivity.class);

        pendingIntent = PendingIntent.getActivity(getApplicationContext(), (int) (System.currentTimeMillis() / 1000), notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        /**
         * 오레오 버전부터는 Notification Channel이 없으면 푸시가 생성되지 않는 현상이 있습니다.
         * **/

        String channel = "OMO";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String channel_nm = "event_notification";

            NotificationManager noticeChannel = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channelMessage = new NotificationChannel(channel, channel_nm,
                    NotificationManager.IMPORTANCE_DEFAULT);
            channelMessage.setDescription("OMO 앱 이벤트 푸시알림 채널");
            channelMessage.enableLights(true);
            channelMessage.enableVibration(true);
            channelMessage.setShowBadge(false);
            channelMessage.setVibrationPattern(new long[]{100, 200, 100, 200});
            noticeChannel.createNotificationChannel(channelMessage);

            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(this, channel)
                            .setSmallIcon(R.drawable.ic_splash_logo)
                            .setContentTitle(title)
                            .setContentText(message)
                            .setChannelId(channel)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                            .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify((int) (System.currentTimeMillis() / 1000), notificationBuilder.build());

        } else {
            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(this, channel)
                            .setSmallIcon(R.drawable.ic_splash_logo)
                            .setContentTitle(title)
                            .setContentText(message)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify((int) (System.currentTimeMillis() / 1000), notificationBuilder.build());

        }

//        Log.d("revely", "title: " + title + " message: " + message);
    }

    @Override
    public void tryPatchFcmSuccess() {

    }

    @Override
    public void tryPatchFcmFailure(String message) {

    }
}

