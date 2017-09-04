package com.iflytek.cloud;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.speech.SpeechSynthesizerAidl;
import com.iflytek.speech.SynthesizerListener;

public class c extends com.iflytek.cloud.a.a.a {
    private static c c = null;
    a a = null;
    private SpeechSynthesizerAidl d = null;
    private a e = null;
    private Handler f = new i(this, Looper.getMainLooper());

    private final class a implements f {
        final /* synthetic */ c a;
        private f b = null;
        private SynthesizerListener c = null;
        private Handler d = new k(this, Looper.getMainLooper());

        public a(c cVar, f fVar) {
            this.a = cVar;
            this.b = fVar;
            this.c = new j(this, cVar);
        }

        public void a() {
            if (this.b != null) {
                Message.obtain(this.d, 1).sendToTarget();
            }
        }

        public void a(int i, int i2, int i3) {
            if (this.b != null) {
                Message.obtain(this.d, 5, i, i2, Integer.valueOf(i3)).sendToTarget();
            }
        }

        public void a(int i, int i2, int i3, Bundle bundle) {
            if (this.b != null) {
                Message obtain = Message.obtain();
                obtain.what = i;
                obtain.arg1 = 0;
                obtain.arg2 = 0;
                obtain.obj = bundle;
                Message.obtain(this.d, 7, 0, 0, obtain).sendToTarget();
            }
        }

        public void a(int i, int i2, int i3, String str) {
            if (this.b != null) {
                Bundle bundle = new Bundle();
                bundle.putInt("percent", i);
                bundle.putInt("begpos", i2);
                bundle.putInt("endpos", i3);
                bundle.putString("spellinfo", str);
                if (this.b != null) {
                    Message.obtain(this.d, 2, bundle).sendToTarget();
                }
            }
        }

        public void a(SpeechError speechError) {
            if (this.b != null) {
                Message.obtain(this.d, 6, speechError).sendToTarget();
            }
        }

        public void b() {
            if (this.b != null) {
                Message.obtain(this.d, 3).sendToTarget();
            }
        }

        public void c() {
            if (this.b != null) {
                Message.obtain(this.d, 4).sendToTarget();
            }
        }
    }

    protected c(Context context, a aVar) {
        this.a = aVar;
        e a = e.a();
        if (a == null || !a.b() || a.d() == com.iflytek.cloud.a.a.a.a.MSC) {
            Message.obtain(this.f, 0, 0, 0, null).sendToTarget();
        } else {
            this.d = new SpeechSynthesizerAidl(context.getApplicationContext(), aVar);
        }
    }

    public static c a() {
        return c;
    }

    public static c a(Context context, a aVar) {
        if (c == null) {
            c = new c(context, aVar);
        }
        return c;
    }

    public int a(String str, f fVar) {
        if (this.d == null) {
            return 21001;
        }
        this.d.setParameter("params", null);
        this.d.setParameter("params", this.b.toString());
        this.b.c("next_text");
        this.e = new a(this, fVar);
        return this.d.startSpeaking(str, this.e.c);
    }

    public String a(String str) {
        return (!"local_speakers".equals(str) || this.d == null) ? ("tts_play_state".equals(str) && a("tts", this.d) == com.iflytek.cloud.a.a.a.a.PLUS && this.d != null) ? this.d.getParameter(str) : super.a(str) : this.d.getParameter(str);
    }

    protected void a(Context context) {
        e a = e.a();
        if (a != null && a.b() && a.d() != com.iflytek.cloud.a.a.a.a.MSC) {
            if (!(this.d == null || this.d.isAvailable())) {
                this.d.destory();
                this.d = null;
            }
            this.d = new SpeechSynthesizerAidl(context.getApplicationContext(), this.a);
        } else if (this.a != null && this.d != null) {
            this.d.destory();
            this.d = null;
        }
    }

    public boolean a(String str, String str2) {
        return super.a(str, str2);
    }

    public void b() {
        if (this.d != null && this.d.isSpeaking() && this.e != null) {
            this.d.pauseSpeaking(this.e.c);
        }
    }

    public void c() {
        if (this.d != null && this.d.isSpeaking() && this.e != null) {
            this.d.resumeSpeaking(this.e.c);
        }
    }

    public void d() {
        if (this.d != null && this.d.isSpeaking() && this.e != null) {
            this.d.stopSpeaking(this.e.c);
        }
    }

    public boolean e() {
        return this.d != null && this.d.isSpeaking();
    }

    public boolean f() {
        if (this.d != null) {
            this.d.destory();
        }
        c = null;
        return true;
    }
}
