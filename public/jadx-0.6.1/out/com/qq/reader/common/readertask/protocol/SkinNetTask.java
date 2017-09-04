package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.c;

public class SkinNetTask extends PluginNetTask {
    public SkinNetTask(c cVar, String str, String str2) {
        super(cVar, str, str2);
        this.mUrl = e.by + str.trim();
    }
}
