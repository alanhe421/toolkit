package com.qrcomic.activity.reader;

import android.os.Bundle;
import com.qrcomic.a.h;
import com.qrcomic.activity.QRTitleBarActivity;
import com.qrcomic.manager.b;

public class QRComicBaseActivity extends QRTitleBarActivity {
    public h j;
    public boolean k = true;
    protected float l;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.j = b.a().b();
        this.l = getResources().getDisplayMetrics().density;
    }
}
