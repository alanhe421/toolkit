package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class AudioQuestionBuyTask extends ReaderProtocolJSONTask {
    public AudioQuestionBuyTask(String str, c cVar) {
        super(cVar);
        this.mUrl = e.cC + str;
    }
}
