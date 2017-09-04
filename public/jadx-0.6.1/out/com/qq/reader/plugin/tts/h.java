package com.qq.reader.plugin.tts;

import android.content.Context;
import com.qq.reader.common.utils.ao;
import com.qq.reader.plugin.tts.a.a;
import com.qq.reader.plugin.tts.a.e;
import com.qq.reader.plugin.tts.a.k;
import com.qq.reader.plugin.tts.model.f;
import java.util.List;

/* compiled from: ITtsPlayer */
public abstract class h implements e {
    public static final int TTS_TYPE_BAIDU = 0;
    public static final int TTS_TYPE_XUNFEI = 1;
    protected Context mContext;
    protected k mCurState = new e();
    protected boolean mDataSatisfied;
    protected boolean mEngineInited;
    protected d mListener;
    protected f mSource;

    public abstract void destory();

    public abstract int getTTSType();

    public abstract List<f> getVoices();

    public abstract void initEngine(a aVar);

    public abstract boolean isApkInstalled();

    public abstract void pause();

    public abstract void play();

    public abstract void repeat();

    public abstract void resume();

    public abstract boolean setSpeed(int i);

    public abstract boolean setVoice(String str);

    public abstract void stop();

    public h(Context context) {
        this.mContext = context;
    }

    public d getListener() {
        return this.mListener;
    }

    public f getDataSource() {
        return this.mSource;
    }

    public void setDataSource(f fVar) {
        this.mSource = fVar;
    }

    public k getCurState() {
        return this.mCurState;
    }

    public int getState() {
        return this.mCurState.b();
    }

    public void setDataProducer(c cVar) {
        this.mSource.a(cVar);
    }

    public boolean isEngineInited() {
        return this.mEngineInited;
    }

    public synchronized void changeState(int i, com.qq.reader.plugin.tts.model.e eVar) {
        this.mCurState = this.mCurState.b(this, i);
        this.mCurState.a(eVar);
        this.mCurState.a(this);
    }

    public void changeState(int i) {
        changeState(i, this.mCurState.c());
        ao.f(i);
    }

    public d getPlayerListener() {
        return this.mListener;
    }

    public void installApk() {
        this.mListener.b();
    }

    public void prepared() {
        this.mDataSatisfied = true;
        changeState(2);
    }

    public boolean isDataSatisfied() {
        return this.mDataSatisfied;
    }

    public void inputEnd() {
        this.mListener.a();
    }
}
