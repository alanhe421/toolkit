package com.qq.reader.plugin.tts;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.os.Handler;
import com.iflytek.cloud.e;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.ao;
import com.qq.reader.plugin.tts.model.d;
import com.qq.reader.plugin.tts.model.f;
import com.tencent.baiduttsplugin.TTSMainBDPlayerDelegate;
import java.util.List;

/* compiled from: TtsFacade */
public class n implements d {
    static final Object a = new Object();
    static n b;
    private h c;
    private Handler d;
    private f e;
    private boolean f;
    private q g;
    private AudioManager h;
    private OnAudioFocusChangeListener i = new 1(this);

    private n() {
        try {
            e.a(ReaderApplication.getApplicationImp(), "appid=51e78860");
        } catch (Exception e) {
        }
    }

    public void a(Handler handler) {
        this.d = handler;
    }

    public void e() {
        this.d = null;
    }

    public void a(c cVar) {
        this.c.setDataProducer(cVar);
    }

    public static n f() {
        synchronized (a) {
            if (b == null) {
                b = new n();
            }
        }
        return b;
    }

    public void a(Activity activity) {
        g();
        this.h = (AudioManager) activity.getSystemService("audio");
        this.c = new p(activity, activity.getApplicationContext(), this);
        this.e = new k();
        this.c.setDataSource(this.e);
        this.e.a(this.c);
        this.g = new q();
        this.g.a();
    }

    public void a(Context context) {
        g();
        this.h = (AudioManager) context.getSystemService("audio");
        this.c = new TTSMainBDPlayerDelegate(context.getApplicationContext(), this);
        this.e = new k();
        this.c.setDataSource(this.e);
        this.e.a(this.c);
        this.g = new q();
        this.g.a();
    }

    public void g() {
        t();
        if (this.e != null) {
            this.e.a();
            this.e = null;
        }
        if (this.c != null) {
            this.c.destory();
            this.c = null;
        }
        e();
        if (this.g != null) {
            this.g.b();
            this.g = null;
        }
        if (this.h != null) {
            this.h.abandonAudioFocus(this.i);
        }
        this.f = false;
    }

    public boolean h() {
        return j() != 1;
    }

    public boolean i() {
        return j() == 2 || j() == 4;
    }

    public int j() {
        if (this.c == null) {
            return 1;
        }
        return this.c.getState();
    }

    public boolean c(int i) {
        if (this.c != null) {
            return this.c.setSpeed(i);
        }
        return false;
    }

    public boolean a(String str) {
        if (this.c != null) {
            return this.c.setVoice(str);
        }
        return false;
    }

    public List<f> k() {
        if (this.c != null) {
            return this.c.getVoices();
        }
        return null;
    }

    public int l() {
        if (this.c != null) {
            return this.c.getTTSType();
        }
        return -1;
    }

    public void m() {
        if (this.c != null) {
            this.c.repeat();
        }
    }

    public void n() {
        if (1 == this.h.requestAudioFocus(this.i, 3, 1)) {
            this.f = true;
            this.c.changeState(2);
            return;
        }
        c();
    }

    public void o() {
        if (this.c != null && j() != 3) {
            this.c.changeState(3);
        }
    }

    public void p() {
        if (this.c != null && !i()) {
            this.c.changeState(4);
        }
    }

    public boolean q() {
        if (this.c != null) {
            return this.c.isApkInstalled();
        }
        return true;
    }

    private void t() {
        if (this.c != null) {
            this.c.changeState(5);
        }
    }

    public void r() {
        o();
        b(3);
        ao.f(3);
    }

    public void s() {
        p();
        b(4);
        ao.f(4);
    }

    public void a(int i) {
        if (this.d != null) {
            this.d.obtainMessage(200001, i, 0).sendToTarget();
        }
    }

    public void a() {
        if (this.d != null) {
            this.d.sendEmptyMessage(200006);
        }
    }

    public void a(d dVar) {
        if (this.d != null) {
            this.d.obtainMessage(200003, dVar).sendToTarget();
        }
    }

    public void b(d dVar) {
        if (this.d != null) {
            this.d.obtainMessage(200004, dVar).sendToTarget();
        }
    }

    public void c(d dVar) {
        if (this.d != null) {
            this.d.obtainMessage(200007, dVar).sendToTarget();
        }
    }

    public void b() {
        if (this.d != null) {
            this.d.sendEmptyMessage(200008);
        }
    }

    public void d() {
        if (this.d != null) {
            this.d.sendEmptyMessage(200013);
        }
    }

    public void c() {
        if (this.d != null) {
            this.d.sendEmptyMessage(200009);
        }
    }

    public void b(int i) {
        if (this.d != null) {
            this.d.obtainMessage(200012, i, 0).sendToTarget();
        }
    }
}
