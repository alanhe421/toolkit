package com.hmt.analytics.dao;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;

public class OpenUDID_service_hmt extends Service {
    public IBinder onBind(Intent intent) {
        return new Binder(this) {
            final /* synthetic */ OpenUDID_service_hmt a;

            {
                this.a = r1;
            }

            public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
                SharedPreferences sharedPreferences = this.a.getSharedPreferences("openudid_prefs", 0);
                parcel2.writeInt(parcel.readInt());
                parcel2.writeString(sharedPreferences.getString("openudid", null));
                return true;
            }
        };
    }
}
