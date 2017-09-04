package com.qq.reader.common.utils.networkUtil;

import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class CheckServerContctionTask extends ReaderProtocolJSONTask {
    public CheckServerContctionTask(String str, c cVar) {
        super(cVar);
        this.mUrl = str;
    }
}
