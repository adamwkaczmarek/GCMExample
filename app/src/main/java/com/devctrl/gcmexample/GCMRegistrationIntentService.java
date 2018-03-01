package com.devctrl.gcmexample;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

/**
 * Created by akaczmarek on 06.02.18.
 */

public class GCMRegistrationIntentService extends IntentService{

    public static final String REGISTRATION_SUCCESS="RegistrationSuccess";
    public static final String REGISTRATION_ERROR="RegistrationError";

    public GCMRegistrationIntentService() {
        super("");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        registerGCM();
    }

    private void registerGCM(){
        Intent registrationComplete=null;
        String token= null;

        try{
            InstanceID instanceID=InstanceID.getInstance(getApplicationContext());
            token=instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE,null);
            Log.w("GCMRegIntentService","token :"+token);
            // infotmation to UI that registration complete

            registrationComplete=new Intent(REGISTRATION_SUCCESS);
            registrationComplete.putExtra("token",token);

        }catch (Exception e){
                Log.w("GCMRegIntentService","Registration error");
        }

        //Send broadcast
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }
}
