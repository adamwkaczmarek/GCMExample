package com.devctrl.gcmexample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by akaczmarek on 06.02.18.
 */

public class GCMPushReceiverService extends GcmListenerService{

    @Override
    public void onMessageReceived(String s, Bundle bundle) {
            //String message = bundle.getString("message");
        Bundle notification = bundle.getBundle("notification");
        String title = notification.getString("title");
        String body = notification.getString("body");

        String guid = bundle.getString("guid");
        String linkAction = bundle.getString("linkAction");

        sendNotification(body,title,guid,linkAction);
    }

    private void sendNotification(String body,String title,String guid,String linkAction){
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        int requestCode=0;
        PendingIntent pendingIntent = PendingIntent.getActivity(this,requestCode,intent,PendingIntent.FLAG_ONE_SHOT);
        //
        Uri sound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder noBuilder=new NotificationCompat.Builder(this,null)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("My GCM message :X:X")
                .setContentText(body + "External data - GUID : " + guid +" LinkAction : "+ linkAction)
                .setContentTitle(title)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =  (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,noBuilder.build());
    }
}
