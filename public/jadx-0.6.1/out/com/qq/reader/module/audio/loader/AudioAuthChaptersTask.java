package com.qq.reader.module.audio.loader;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;

public class AudioAuthChaptersTask extends ReaderProtocolJSONTask {
    public AudioAuthChaptersTask(String str, String str2) {
        this.mUrl = e.P + "adid=" + str + "&acids=" + str2;
    }
}
