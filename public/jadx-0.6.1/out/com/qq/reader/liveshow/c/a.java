package com.qq.reader.liveshow.c;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import com.qq.reader.liveshow.model.c;
import com.qq.reader.liveshow.utils.SxbLog;
import com.qq.reader.liveshow.utils.g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: EnterCommonAlertHelper */
public class a {
    private static final String a = a.class.getSimpleName();
    private Map<String, String> b;
    private boolean c;
    private Activity d;
    private boolean e;
    private final int f = 110;
    private com.qq.reader.liveshow.c.b.a g;
    private BroadcastReceiver h = new BroadcastReceiver(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                return;
            }
            if (g.a(context)) {
                int b = g.b(context);
                if (b == 1) {
                    if (this.a.g != null) {
                        this.a.g.f();
                    }
                } else if (this.a.g != null) {
                    this.a.g.a(b);
                }
            } else if (this.a.g != null) {
                this.a.g.e();
            }
        }
    };
    private IntentFilter i = new IntentFilter();

    public a(com.qq.reader.liveshow.c.b.a aVar, Activity activity) {
        this.g = aVar;
        this.d = activity;
        this.b = new HashMap();
        this.b.put("android.permission.WAKE_LOCK", "锁屏");
        this.b.put("android.permission.MODIFY_AUDIO_SETTINGS", "设置音频");
        this.b.put("android.permission.CAMERA", "相机");
        this.b.put("android.permission.RECORD_AUDIO", "录音");
        if (this.d != null) {
            this.i.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            this.d.registerReceiver(this.h, this.i);
        }
    }

    public void a() {
        if (this.d != null) {
            this.d.unregisterReceiver(this.h);
        }
        this.d = null;
        this.g = null;
    }

    public void a(Activity activity) {
        List arrayList = new ArrayList();
        if (this.c) {
            this.g.g();
        } else if (!this.e) {
            this.e = true;
            SxbLog.c(a, "checkPermission");
            if (VERSION.SDK_INT < 23 || this.c) {
                this.g.g();
                this.c = true;
                this.e = false;
                return;
            }
            if (android.support.v4.content.a.a((Context) activity, "android.permission.WAKE_LOCK") != 0) {
                arrayList.add("android.permission.WAKE_LOCK");
            }
            if (android.support.v4.content.a.a((Context) activity, "android.permission.MODIFY_AUDIO_SETTINGS") != 0) {
                arrayList.add("android.permission.MODIFY_AUDIO_SETTINGS");
            }
            if (c.a().h() == 1) {
                if (android.support.v4.content.a.a((Context) activity, "android.permission.CAMERA") != 0) {
                    arrayList.add("android.permission.CAMERA");
                }
                if (android.support.v4.content.a.a((Context) activity, "android.permission.RECORD_AUDIO") != 0) {
                    arrayList.add("android.permission.RECORD_AUDIO");
                }
            }
            if (arrayList.size() > 0) {
                android.support.v4.app.a.a(activity, (String[]) arrayList.toArray(new String[arrayList.size()]), 110);
                return;
            }
            this.g.g();
            this.c = true;
            this.e = false;
        }
    }

    public void a(int i, String[] strArr, int[] iArr) {
        List arrayList = new ArrayList();
        switch (i) {
            case 110:
                if (strArr.length > 0) {
                    for (int i2 = 0; i2 < iArr.length; i2++) {
                        if (iArr[i2] != 0) {
                            arrayList.add(this.b.get(strArr[i2]));
                        }
                    }
                    if (arrayList.size() > 0) {
                        this.g.a((String[]) arrayList.toArray(new String[arrayList.size()]));
                        this.c = false;
                    } else {
                        this.g.g();
                        this.c = true;
                    }
                    this.e = false;
                    return;
                }
                return;
            default:
                return;
        }
    }
}
