package com.Softpig;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.RemoteInput;


public class DirectReplyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);

        if (remoteInput != null) {
            CharSequence replyText = remoteInput.getCharSequence("key_text_reply");
            FirebaseService answer = new FirebaseService(replyText, null);
            NotificationActivity.MESSAGES.add(answer);

            NotificationActivity.sendChannel1Notification(context);
        }
    }
}
