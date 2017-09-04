package com.qq.reader.cservice.cloud.big;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class CloudListUpdateTaskBig extends ReaderProtocolJSONTask {
    private boolean isFirstPackage = false;
    private boolean isLastPackage = false;
    private final int mDefaultPn = 1;
    private final int mDefaultPs = 100;
    private int mParamPn = 1;
    private int mParamPs = 100;

    public CloudListUpdateTaskBig(c cVar, int i, int i2) {
        super(cVar);
        if (i == -1) {
            i = 1;
        }
        this.mParamPn = i;
        if (i2 == -1) {
            i2 = 100;
        }
        this.mParamPs = i2;
        this.mUrl = e.aW + 0 + "&pn=" + this.mParamPn + "&ps=" + this.mParamPs;
        if (this.mParamPn == 1) {
            this.isFirstPackage = true;
        }
    }

    public int getParamPn() {
        return this.mParamPn;
    }

    public int getParamPs() {
        return this.mParamPs;
    }

    public void setIsLastPackage(boolean z) {
        this.isLastPackage = z;
    }

    public boolean getIsLastPackage() {
        return this.isLastPackage;
    }

    public boolean getIsFirstPackage() {
        return this.isFirstPackage;
    }
}
