package com.qq.reader.module.question.card;

import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.question.data.AudioData;

public class AudioComCardOfMyListen extends AudioComBaseCardDisablePlay {
    public AudioComCardOfMyListen(b bVar, String str) {
        super(bVar, str);
    }

    protected void onCardShowUpLog() {
        super.onCardShowUpLog();
    }

    protected void onToDetailPageUpLog(boolean z) {
        super.onToDetailPageUpLog(z);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public boolean equals(Object obj) {
        try {
            AudioData audioData = ((AudioComCardOfMyListen) obj).getAudioData();
            if (audioData != null && audioData.a().g().equals(this.data.a().g())) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
}
