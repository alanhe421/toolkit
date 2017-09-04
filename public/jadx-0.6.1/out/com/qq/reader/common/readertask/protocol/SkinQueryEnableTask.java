package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import java.util.List;

public class SkinQueryEnableTask extends ReaderProtocolJSONTask {
    public SkinQueryEnableTask(c cVar, List<String> list) {
        super(cVar);
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : list) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(str);
        }
        this.mUrl = e.bz + stringBuilder.toString();
    }
}
