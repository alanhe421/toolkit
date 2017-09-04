package com.tencent.android.tpush.service.channel.protocol;

import java.io.Serializable;

/* compiled from: ProGuard */
public final class TPNS_CLIENT_REPORT_CMD implements Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = (!TPNS_CLIENT_REPORT_CMD.class.desiredAssertionStatus());
    public static final TPNS_CLIENT_REPORT_CMD CMD_REPORT_SPEED = new TPNS_CLIENT_REPORT_CMD(0, 0, "CMD_REPORT_SPEED");
    public static final int _CMD_REPORT_SPEED = 0;
    private static TPNS_CLIENT_REPORT_CMD[] __values = new TPNS_CLIENT_REPORT_CMD[1];
    private String __T = new String();
    private int __value;

    public String toString() {
        return this.__T;
    }

    private TPNS_CLIENT_REPORT_CMD(int i, int i2, String str) {
        this.__T = str;
        this.__value = i2;
        __values[i] = this;
    }
}
