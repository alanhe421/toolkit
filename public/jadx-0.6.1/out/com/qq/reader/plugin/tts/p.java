package com.qq.reader.plugin.tts;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.c;
import com.iflytek.cloud.f;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.plugin.k;
import com.qq.reader.plugin.l;
import com.qq.reader.plugin.m;
import com.qq.reader.plugin.tts.a.a;
import com.qq.reader.plugin.tts.model.b;
import com.qq.reader.plugin.tts.model.e;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: TtsMainPlayer */
public class p extends h {
    b a = null;
    private String b = p.class.getName();
    private c c;
    private volatile boolean d = false;
    private Handler e = new Handler(this) {
        final /* synthetic */ p a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 200010:
                    switch (this.a.mCurState.b()) {
                        case 2:
                        case 4:
                            this.a.play();
                            return;
                        default:
                            return;
                    }
                case 200011:
                    if (this.a.mListener != null) {
                        this.a.mListener.a(message.what);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private f f = new f(this) {
        final /* synthetic */ p a;

        {
            this.a = r1;
        }

        public void a(int i, int i2, int i3, String str) {
        }

        public void a(int i, int i2, int i3, Bundle bundle) {
        }

        public void a(SpeechError speechError) {
            if (speechError != null) {
                this.a.a = null;
                this.a.mListener.a(speechError.getErrorCode());
            } else if (this.a.a == null || this.a.a.a() == null) {
                this.a.e.sendEmptyMessage(200010);
            } else {
                switch (this.a.a.a().a()) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        if (this.a.mListener != null) {
                            this.a.mListener.c();
                            return;
                        } else {
                            com.qq.reader.common.monitor.f.b(this.a.b, "onCompleted mListener==null");
                            return;
                        }
                    default:
                        this.a.mListener.b(this.a.a.a());
                        this.a.a = null;
                        this.a.e.sendEmptyMessage(200010);
                        return;
                }
            }
        }

        public void a() {
        }

        public void b() {
            com.qq.reader.common.monitor.f.b(this.a.b, "onSpeakPaused");
            e eVar = new e();
            eVar.a(2);
            this.a.changeState(3, eVar);
            if (this.a.mListener != null) {
                this.a.mListener.b(3);
            }
        }

        public void a(int i, int i2, int i3) {
            try {
                if (this.a.a != null && this.a.a.c() && i >= this.a.a.d() && this.a.mListener != null) {
                    this.a.mListener.c(this.a.a.a());
                }
            } catch (Exception e) {
                com.qq.reader.common.monitor.f.b(this.a.b, "onSpeakProgress=" + e.getMessage());
            }
        }

        public void c() {
            e eVar = new e();
            eVar.a(2);
            this.a.changeState(4, eVar);
            if (this.a.mListener != null) {
                this.a.mListener.b(4);
            }
        }
    };

    public p(Activity activity, Context context, d dVar) {
        super(context);
        this.mListener = dVar;
    }

    public void initEngine(final a aVar) {
        this.d = false;
        this.c = c.a(this.mContext, new com.iflytek.cloud.a(this) {
            final /* synthetic */ p b;

            public void a(int i) {
                if (!this.b.d) {
                    this.b.d = true;
                    if (i == 0) {
                        this.b.mEngineInited = true;
                        try {
                            this.b.c.a("engine_type", "local");
                            this.b.setSpeed(d.ax(this.b.mContext));
                            this.b.setVoice(d.ay(this.b.mContext));
                            this.b.c.a("pitch", "50");
                        } catch (Exception e) {
                            com.qq.reader.common.monitor.f.a(this.b.b, "onInit error=" + e.getMessage());
                        }
                        this.b.changeState(2);
                        aVar.a();
                        return;
                    }
                    this.b.mEngineInited = false;
                    if (this.b.mListener != null) {
                        this.b.mListener.a(i);
                    }
                }
            }
        });
        new Timer().schedule(new TimerTask(this) {
            final /* synthetic */ p a;

            {
                this.a = r1;
            }

            public void run() {
                if (!this.a.d) {
                    this.a.d = true;
                    this.a.mListener.d();
                }
            }
        }, 1500);
    }

    public boolean isApkInstalled() {
        com.qq.reader.plugin.d dVar = null;
        l b = k.b().b("29");
        if (b != null) {
            dVar = (com.qq.reader.plugin.d) m.c().b(this.mContext, b);
        }
        if (dVar == null || !dVar.i() || dVar.o()) {
            return false;
        }
        return true;
    }

    public void pause() {
        e c = this.mCurState.c();
        if (this.c == null) {
            return;
        }
        if (c == null || c.a() != 2) {
            try {
                if (this.c.e()) {
                    this.c.b();
                    return;
                }
                return;
            } catch (Exception e) {
                com.qq.reader.common.monitor.f.a(this.b, "pause error=" + e.getMessage());
                return;
            }
        }
        this.mCurState.a(null);
    }

    public void stop() {
        try {
            if (this.c != null) {
                this.c.d();
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.f.a(this.b, "stop error=" + e.getMessage());
        }
    }

    public void resume() {
        e c = this.mCurState.c();
        if (c != null) {
            try {
                if (c.a() == 1) {
                    this.c.d();
                    play();
                } else if (c.a() == 2) {
                    this.mCurState.a(null);
                }
            } catch (Exception e) {
                com.qq.reader.common.monitor.f.a(this.b, "resume error=" + e.getMessage());
            }
        } else if (this.c.e()) {
            this.c.c();
        } else {
            this.mCurState.a(null);
            play();
        }
    }

    public void destory() {
        try {
            if (this.c != null) {
                this.c.f();
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.f.a(this.b, "destory error=" + e.getMessage());
        }
        this.mDataSatisfied = false;
    }

    public void repeat() {
        e eVar = null;
        if (this.a != null) {
            eVar = new e();
            eVar.a(1);
            eVar.a(this.a);
        }
        changeState(4, eVar);
    }

    public void play() {
        int i = -1;
        if (com.qq.reader.plugin.audiobook.core.l.a != null) {
            try {
                com.qq.reader.plugin.audiobook.core.l.a.c();
            } catch (RemoteException e) {
            }
        }
        e c = this.mCurState.c();
        if (c == null || c.a() != 1) {
            this.a = this.mSource.b();
            if (this.a == null) {
            }
        } else {
            this.a = (b) c.b();
            if (this.a == null) {
                this.mCurState.a(null);
            } else {
                this.mCurState.a(null);
            }
        }
        switch (this.a.b()) {
            case 1:
                this.mDataSatisfied = false;
                changeState(7);
                return;
            case 2:
                com.qq.reader.plugin.tts.model.d a = this.a.a();
                switch (a.a()) {
                    case 0:
                        this.mSource.b(a);
                        if (a.f().trim().length() == 0) {
                            this.mListener.b(a);
                            this.e.sendEmptyMessage(200010);
                            return;
                        }
                        this.mListener.a(a);
                        try {
                            i = this.c.a(a.f(), this.f);
                        } catch (Exception e2) {
                            com.qq.reader.common.monitor.f.a(this.b, "startSpeaking error=" + e2.getMessage());
                        }
                        if (i != 0) {
                            this.mListener.a(i);
                            return;
                        }
                        return;
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        int a2;
                        try {
                            a2 = this.c.a(com.qq.reader.plugin.tts.model.d.a[a.a()], this.f);
                        } catch (Exception e22) {
                            com.qq.reader.common.monitor.f.a(this.b, "startSpeaking special error=" + e22.getMessage());
                            a2 = i;
                        }
                        if (a2 != 0) {
                            this.mListener.a(a2);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            default:
                return;
        }
    }

    public List<com.qq.reader.plugin.tts.model.f> getVoices() {
        String a;
        Exception e;
        try {
            a = this.c.a("local_speakers");
            try {
                d.B(this.mContext, a);
            } catch (Exception e2) {
                e = e2;
                com.qq.reader.common.monitor.f.a(this.b, "getVoices error=" + e.getMessage());
                com.qq.reader.common.monitor.f.a(this.b, a);
                return a(a);
            }
        } catch (Exception e3) {
            Exception exception = e3;
            a = null;
            e = exception;
            com.qq.reader.common.monitor.f.a(this.b, "getVoices error=" + e.getMessage());
            com.qq.reader.common.monitor.f.a(this.b, a);
            return a(a);
        }
        com.qq.reader.common.monitor.f.a(this.b, a);
        return a(a);
    }

    public int getTTSType() {
        return 1;
    }

    private List<com.qq.reader.plugin.tts.model.f> a(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        List<com.qq.reader.plugin.tts.model.f> arrayList = new ArrayList();
        try {
            for (String split : str.split(VoiceWakeuperAidl.PARAMS_SEPARATE)) {
                String[] split2 = split.split(":");
                if (split2.length >= 2) {
                    com.qq.reader.plugin.tts.model.f fVar = new com.qq.reader.plugin.tts.model.f();
                    fVar.a = split2[0];
                    fVar.b = split2[1];
                    fVar.c = 0;
                    arrayList.add(fVar);
                }
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.f.a(this.b, "input voice=" + str + " parseVoices error=" + e.getMessage());
        }
        File file = new File(com.qq.reader.common.c.a.aX + "libs/");
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(com.qq.reader.common.c.a.aX + "bdttsplugin.zip");
        if (file2.exists() && file.list().length < 3) {
            try {
                ao.f(file2.getPath(), com.qq.reader.common.c.a.aX + "libs/");
            } catch (Exception e2) {
            }
        }
        if (new File(com.qq.reader.common.c.a.aX + "libs/bd_etts_speech_female.dat").exists()) {
            com.qq.reader.plugin.tts.model.f fVar2 = new com.qq.reader.plugin.tts.model.f();
            fVar2.a = "baidu_female";
            fVar2.b = "甜美女";
            fVar2.c = 1;
            arrayList.add(fVar2);
        }
        if (!new File(com.qq.reader.common.c.a.aX + "libs/bd_etts_speech_male.dat").exists()) {
            return arrayList;
        }
        fVar2 = new com.qq.reader.plugin.tts.model.f();
        fVar2.a = "baidu_male";
        fVar2.b = "情感男";
        fVar2.c = 1;
        arrayList.add(fVar2);
        return arrayList;
    }

    public boolean setSpeed(int i) {
        try {
            if (this.c != null && true == this.c.a("speed", i + "")) {
                return true;
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.f.a(this.b, "setSpeed error=" + e.getMessage());
        }
        return false;
    }

    public boolean setVoice(String str) {
        if (this.c != null) {
            List<com.qq.reader.plugin.tts.model.f> voices = getVoices();
            if (voices != null && voices.size() > 0) {
                int b;
                Object obj;
                for (com.qq.reader.plugin.tts.model.f fVar : voices) {
                    if (fVar.a.equalsIgnoreCase(str)) {
                        b = b(fVar.b);
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                b = -1;
                if (obj != null) {
                    try {
                        Map hashMap = new HashMap();
                        if (b != -1) {
                            hashMap.put(s.ORIGIN, String.valueOf(b));
                            i.a("event_D28", hashMap, ReaderApplication.getApplicationImp());
                        }
                        return true == this.c.a("voice_name", str);
                    } catch (Exception e) {
                        com.qq.reader.common.monitor.f.a(this.b, "setVoice error=" + e.getMessage());
                    }
                }
            }
        }
        return false;
    }

    private int b(String str) {
        if ("郭嘉".equals(str)) {
            return 3;
        }
        if ("风清扬".equals(str)) {
            return 4;
        }
        if ("小鲜肉".equals(str)) {
            return 5;
        }
        if ("东方教主".equals(str)) {
            return 6;
        }
        if ("王老师".equals(str)) {
            return 7;
        }
        if ("小萝莉".equals(str)) {
            return 8;
        }
        if ("男主播".equals(str)) {
            return 9;
        }
        if ("邻家姐姐".equals(str)) {
            return 10;
        }
        if ("女汉子".equals(str)) {
            return 11;
        }
        if ("傻根".equals(str)) {
            return 12;
        }
        if ("涵涵".equals(str)) {
            return 13;
        }
        if ("辣妹子".equals(str)) {
            return 14;
        }
        if ("佳宜".equals(str)) {
            return 15;
        }
        if ("港姐".equals(str)) {
            return 16;
        }
        if ("希拉里".equals(str)) {
            return 17;
        }
        if ("嘉嘉老师".equals(str)) {
            return 18;
        }
        if ("方茴".equals(str)) {
            return 19;
        }
        return -1;
    }
}
