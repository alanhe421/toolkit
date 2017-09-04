package com.tencent.baiduttsplugin;

import android.content.Context;

public interface BDTTSAdapter {
    boolean init(Context context, String str, int i, String str2);

    void pause();

    void play();

    void release();

    void resume();

    void setListener(BDTTSCallback bDTTSCallback);

    void setSpeed(int i);

    void setVoice(String str);

    int speak(String str);

    void stop();
}
