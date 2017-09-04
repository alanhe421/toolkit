package com.tencent.baiduttsplugin;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.plugin.audiobook.core.l;
import com.qq.reader.plugin.tts.d;
import com.qq.reader.plugin.tts.h;
import com.qq.reader.plugin.tts.model.b;
import com.qq.reader.plugin.tts.model.e;
import com.qq.reader.plugin.tts.model.f;
import com.tencent.qalsdk.im_open.http;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TTSMainBDPlayerDelegate extends h implements BDTTSCallback {
    static final String CLASS_PDFDOCUMENT = "com.tencent.baiduttsplugin.TestAgent";
    private static int SPLIT_LENGTH = http.Internal_Server_Error;
    private static Class agentcla;
    private static DexClassLoader loader;
    private volatile boolean SPLIT_FLAG = false;
    private String TAG = TTSMainBDPlayerDelegate.class.getName();
    String apkurl = (a.aX + "/libs/bdttsplugin.apk");
    b mCurResult = null;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 200010:
                    switch (TTSMainBDPlayerDelegate.this.mCurState.b()) {
                        case 2:
                        case 4:
                            TTSMainBDPlayerDelegate.this.play();
                            return;
                        default:
                            return;
                    }
                case 200011:
                    if (TTSMainBDPlayerDelegate.this.mListener != null) {
                        TTSMainBDPlayerDelegate.this.mListener.a(message.what);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private String mSampleDirPath;
    private String mSplitString;
    private BDTTSAdapter mTts;
    String outPath = "/sdcard/testdir";

    public TTSMainBDPlayerDelegate(Context context, d dVar) {
        super(context);
        this.mListener = dVar;
        initialEnv();
    }

    private void initialEnv() {
        if (this.mSampleDirPath == null) {
            this.mSampleDirPath = a.aX;
        }
        makeDir(this.mSampleDirPath);
        this.outPath = this.mContext.getDir(ShareConstants.DEX_PATH, 0).getPath();
        try {
            if (loader == null) {
                loader = new DexClassLoader(this.apkurl, this.outPath, this.mSampleDirPath + "libs", getClass().getClassLoader());
            }
            if (agentcla == null) {
                agentcla = loader.loadClass(CLASS_PDFDOCUMENT);
            }
            this.mTts = (BDTTSAdapter) agentcla.getConstructor(new Class[0]).newInstance(new Object[0]);
            this.mTts.setListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initEngine(com.qq.reader.plugin.tts.a.a aVar) {
        if (this.mTts.init(this.mContext, com.qq.reader.appconfig.a.d.ay(this.mContext), com.qq.reader.appconfig.a.d.ax(this.mContext), this.mSampleDirPath + "/libs")) {
            changeState(2);
            aVar.a();
        }
    }

    public boolean setSpeed(int i) {
        if (i >= 100) {
            i = 99;
        }
        this.mTts.setSpeed(i);
        return false;
    }

    public boolean setVoice(String str) {
        Map hashMap = new HashMap();
        if (str == "baidu_female") {
            this.mTts.setVoice(str);
            hashMap.put(s.ORIGIN, "1");
        } else {
            this.mTts.setVoice(str);
            hashMap.put(s.ORIGIN, "2");
        }
        i.a("event_D28", hashMap, ReaderApplication.getApplicationImp());
        return true;
    }

    public List<f> getVoices() {
        String str = null;
        try {
            str = com.qq.reader.appconfig.a.d.bK(this.mContext);
        } catch (Exception e) {
            com.qq.reader.common.monitor.f.a(this.TAG, "getVoices error=" + e.getMessage());
        }
        com.qq.reader.common.monitor.f.a(this.TAG, str);
        return parseVoices(str);
    }

    private List<f> parseVoices(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        List<f> arrayList = new ArrayList();
        try {
            for (String split : str.split(VoiceWakeuperAidl.PARAMS_SEPARATE)) {
                String[] split2 = split.split(":");
                if (split2.length >= 2) {
                    f fVar = new f();
                    fVar.a = split2[0];
                    fVar.b = split2[1];
                    fVar.c = 0;
                    arrayList.add(fVar);
                }
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.f.a(this.TAG, "input voice=" + str + " parseVoices error=" + e.getMessage());
        }
        File file = new File(a.aX + "libs/");
        File file2 = new File(a.aX + "bdttsplugin.zip");
        if (!file.exists()) {
            file.mkdir();
        }
        if (file2.exists() && file.list().length < 3) {
            try {
                ao.f(file2.getPath(), a.aX + "libs/");
            } catch (Exception e2) {
            }
        }
        if (new File(a.aX + "libs/bd_etts_speech_female.dat").exists()) {
            f fVar2 = new f();
            fVar2.a = "baidu_female";
            fVar2.b = "甜美女";
            fVar2.c = 1;
            arrayList.add(fVar2);
        }
        if (!new File(a.aX + "libs/bd_etts_speech_male.dat").exists()) {
            return arrayList;
        }
        fVar2 = new f();
        fVar2.a = "baidu_male";
        fVar2.b = "情感男";
        fVar2.c = 1;
        arrayList.add(fVar2);
        return arrayList;
    }

    public int getTTSType() {
        return 0;
    }

    public boolean isApkInstalled() {
        return true;
    }

    public void pause() {
        e c = this.mCurState.c();
        if (this.mTts == null) {
            return;
        }
        if (c == null || c.a() != 2) {
            try {
                this.mTts.pause();
                return;
            } catch (Exception e) {
                com.qq.reader.common.monitor.f.a(this.TAG, "pause error=" + e.getMessage());
                return;
            }
        }
        this.mCurState.a(null);
    }

    public void stop() {
        try {
            if (this.mTts != null) {
                this.mTts.stop();
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.f.a(this.TAG, "stop error=" + e.getMessage());
        }
    }

    public void resume() {
        e c = this.mCurState.c();
        if (c != null) {
            try {
                if (c.a() == 1) {
                    this.mTts.stop();
                    play();
                    return;
                } else if (c.a() == 2) {
                    this.mCurState.a(null);
                    return;
                } else {
                    return;
                }
            } catch (Exception e) {
                com.qq.reader.common.monitor.f.a(this.TAG, "resume error=" + e.getMessage());
                return;
            }
        }
        this.mTts.resume();
    }

    public void destory() {
        try {
            if (this.mTts != null) {
                this.mTts.release();
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.f.a(this.TAG, "destory error=" + e.getMessage());
        }
        this.mDataSatisfied = false;
    }

    public void play() {
        if (l.a != null) {
            try {
                l.a.c();
            } catch (RemoteException e) {
            }
        }
        e c = this.mCurState.c();
        if (c == null || c.a() != 1) {
            this.mCurResult = this.mSource.b();
            if (this.mCurResult == null) {
            }
        } else {
            this.mCurResult = (b) c.b();
            if (this.mCurResult == null) {
                this.mCurState.a(null);
            } else {
                this.mCurState.a(null);
            }
        }
        switch (this.mCurResult.b()) {
            case 1:
                this.mDataSatisfied = false;
                changeState(7);
                return;
            case 2:
                com.qq.reader.plugin.tts.model.d a = this.mCurResult.a();
                int i;
                switch (a.a()) {
                    case 0:
                        this.mSource.b(a);
                        if (a.f().trim().length() == 0) {
                            this.mListener.b(a);
                            this.mHandler.sendEmptyMessage(200010);
                            return;
                        }
                        this.mListener.a(a);
                        try {
                            if (a.f().length() > SPLIT_LENGTH) {
                                this.SPLIT_FLAG = true;
                                this.mSplitString = a.f();
                                this.mTts.speak(this.mSplitString.substring(0, SPLIT_LENGTH));
                                this.mSplitString = this.mSplitString.substring(SPLIT_LENGTH);
                                i = 0;
                            } else {
                                this.SPLIT_FLAG = false;
                                i = this.mTts.speak(a.f());
                            }
                        } catch (Exception e2) {
                            com.qq.reader.common.monitor.f.a(this.TAG, "startSpeaking error=" + e2.getMessage());
                            i = -1;
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
                        try {
                            i = this.mTts.speak(com.qq.reader.plugin.tts.model.d.a[a.a()]);
                        } catch (Exception e22) {
                            com.qq.reader.common.monitor.f.a(this.TAG, "startSpeaking special error=" + e22.getMessage());
                            i = -1;
                        }
                        if (i != 0) {
                            this.mListener.a(i);
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

    public void repeat() {
        e eVar = null;
        if (this.mCurResult != null) {
            eVar = new e();
            eVar.a(1);
            eVar.a(this.mCurResult);
        }
        changeState(4, eVar);
    }

    private void makeDir(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private void copyFromAssetsToSdcard(boolean z, String str, String str2) {
        InputStream open;
        FileOutputStream fileOutputStream;
        FileNotFoundException e;
        InputStream inputStream;
        IOException e2;
        Throwable th;
        FileOutputStream fileOutputStream2 = null;
        File file = new File(str2);
        if (z || !(z || file.exists())) {
            try {
                open = this.mContext.getResources().getAssets().open(str);
                try {
                    fileOutputStream = new FileOutputStream(str2);
                } catch (FileNotFoundException e3) {
                    e = e3;
                    fileOutputStream = null;
                    inputStream = open;
                    try {
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        if (inputStream == null) {
                            try {
                                inputStream.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                                return;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        open = inputStream;
                        fileOutputStream2 = fileOutputStream;
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        if (open != null) {
                            try {
                                open.close();
                            } catch (IOException e42) {
                                e42.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e5) {
                    e222 = e5;
                    try {
                        e222.printStackTrace();
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (IOException e2222) {
                                e2222.printStackTrace();
                            }
                        }
                        if (open == null) {
                            try {
                                open.close();
                            } catch (IOException e22222) {
                                e22222.printStackTrace();
                                return;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        if (open != null) {
                            open.close();
                        }
                        throw th;
                    }
                }
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = open.read(bArr, 0, 1024);
                        if (read < 0) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e222222) {
                            e222222.printStackTrace();
                        }
                    }
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e2222222) {
                            e2222222.printStackTrace();
                        }
                    }
                } catch (FileNotFoundException e6) {
                    e = e6;
                    inputStream = open;
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (inputStream == null) {
                        inputStream.close();
                    }
                } catch (IOException e7) {
                    e2222222 = e7;
                    fileOutputStream2 = fileOutputStream;
                    e2222222.printStackTrace();
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    if (open == null) {
                        open.close();
                    }
                } catch (Throwable th4) {
                    th = th4;
                    fileOutputStream2 = fileOutputStream;
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    if (open != null) {
                        open.close();
                    }
                    throw th;
                }
            } catch (FileNotFoundException e8) {
                e = e8;
                fileOutputStream = null;
                e.printStackTrace();
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (inputStream == null) {
                    inputStream.close();
                }
            } catch (IOException e9) {
                e2222222 = e9;
                open = null;
                e2222222.printStackTrace();
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                if (open == null) {
                    open.close();
                }
            } catch (Throwable th5) {
                th = th5;
                open = null;
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                if (open != null) {
                    open.close();
                }
                throw th;
            }
        }
    }

    public void callback() {
        com.qq.reader.common.monitor.f.a(this.TAG, "CALLBACK");
    }

    public void onSpeechProgressChanged(String str, int i) {
        try {
            if (this.mCurResult != null && this.mCurResult.c() && i >= this.mCurResult.d() && this.mListener != null) {
                this.mListener.c(this.mCurResult.a());
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.f.b(this.TAG, "onSpeakProgress=" + e.getMessage());
        }
    }

    public void onSpeechFinish(String str) {
        if (this.SPLIT_FLAG) {
            if (this.mSplitString.length() > SPLIT_LENGTH) {
                this.mTts.speak(this.mSplitString.substring(0, SPLIT_LENGTH));
                this.mSplitString = this.mSplitString.substring(SPLIT_LENGTH);
            } else if (this.mSplitString.length() > 0) {
                this.mTts.speak(this.mSplitString);
                this.SPLIT_FLAG = false;
            } else {
                this.SPLIT_FLAG = false;
            }
        } else if (this.mCurResult == null || this.mCurResult.a() == null) {
            this.mHandler.sendEmptyMessage(200010);
        } else {
            switch (this.mCurResult.a().a()) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    if (this.mListener != null) {
                        this.mListener.c();
                        return;
                    } else {
                        com.qq.reader.common.monitor.f.b(this.TAG, "onCompleted mListener==null");
                        return;
                    }
                default:
                    this.mListener.b(this.mCurResult.a());
                    this.mCurResult = null;
                    this.mHandler.sendEmptyMessage(200010);
                    return;
            }
        }
    }

    public void onError(String str, int i) {
        this.mCurResult = null;
        this.mListener.a(i);
    }
}
