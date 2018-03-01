package com.devctrl.gcmexample;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by akaczmarek on 06.02.18.
 */

public class GCMTokenRefreshListenerService extends InstanceIDListenerService{



    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this,GCMRegistrationIntentService.class);
        startService(intent);

    }
}
