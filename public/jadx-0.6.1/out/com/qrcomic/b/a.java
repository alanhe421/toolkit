package com.qrcomic.b;

import android.content.Context;
import android.util.Log;
import com.qrcomic.entity.g;
import org.greenrobot.greendao.database.Database;

/* compiled from: QRComicDevOpenHelper */
public class a extends com.qrcomic.entity.g.a {
    public a(Context context, String str) {
        super(context, str);
    }

    public void onUpgrade(Database database, int i, int i2) {
        Log.i("greenDAO", "Upgrading schema from version " + i + " to " + i2 + " by dropping all tables");
        g.b(database, true);
        onCreate(database);
    }
}
