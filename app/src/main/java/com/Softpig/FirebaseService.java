package com.Softpig;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import com.Softpig.View.MainMenuActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;
import static android.graphics.Color.rgb;

public class FirebaseService extends FirebaseMessagingService {

    private String TAG = "FirebaseService";
    private CharSequence text;
    private  long timestamp;
    private CharSequence sender;

    public FirebaseService() {
    }

    public FirebaseService(CharSequence text, CharSequence sender){
        this.text = text;
        this.sender = sender;
        timestamp = System.currentTimeMillis();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        if (remoteMessage.getData().isEmpty())
            showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        else showNotification(remoteMessage.getData());

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                //scheduleJob();
            } else {
                // Handle message within 10 seconds
                // handleNow();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    public void showNotification(Map<String, String> data) {
        String title = data.get("title").toString();
        String body = data.get("body").toString();
        String NOTIFICATION_CHANNEL_ID = getString(R.string.default_notification_channel_id);
        //
        Intent intent = new Intent(this, MainMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        /*NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        notificationBuilder
                .setSmallIcon(R.drawable.nav_pig)
                .setColor(rgb(254, 220, 220))
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setVibrate(new long[]{0, 1000, 500, 1000})
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)

                .setContentInfo("info");

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "Notification",
                    NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.setDescription("Descripcion");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableLights(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        notificationManager.notify(0, notificationBuilder.build());*/
    }

    private void showNotification(String title, String body) {
            Intent intent = new Intent(this, MainMenuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            //
            String NOTIFICATION_CHANNEL_ID = getString(R.string.default_notification_channel_id);

        Notification notificationBuilder = new androidx.core.app.NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.nav_pig)
                .setColor(rgb(254, 220, 220))
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setVibrate(new long[]{0, 1000, 500, 1000})
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "Notification",
                    NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.setDescription("Descripcion");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableLights(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

           /* NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
            notificationBuilder
                    .setSmallIcon(R.drawable.nav_pig)
                    .setColor(rgb(254, 220, 220))
                    .setContentTitle(title)
                    .setContentText(body)
                    .setAutoCancel(true)
                    .setVibrate(new long[]{0, 1000, 500, 1000})
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent)

                    .setContentInfo("info");

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            // Since android Oreo notification channel is needed.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                        "Notification",
                        NotificationManager.IMPORTANCE_DEFAULT);

                notificationChannel.setDescription("Descripcion");
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.BLUE);
                notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
                notificationChannel.enableLights(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }

            notificationManager.notify(0, notificationBuilder.build());*/
        }

    public CharSequence getText() {
        return text;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public CharSequence getSender() {
        return sender;
    }


}
