package com.tencent.baiduttsplugin;

public interface BDTTSCallback {
    void callback();

    void onError(String str, int i);

    void onSpeechFinish(String str);

    void onSpeechProgressChanged(String str, int i);
}
