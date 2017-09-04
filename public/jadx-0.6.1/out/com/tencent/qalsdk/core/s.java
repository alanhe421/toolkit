package com.tencent.qalsdk.core;

import com.tencent.qalsdk.sdk.MsfCommand;

/* compiled from: SsoRespHandler */
/* synthetic */ class s {
    static final /* synthetic */ int[] a = new int[MsfCommand.values().length];

    static {
        try {
            a[MsfCommand._msf_RegPush.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[MsfCommand._msf_UnRegPush.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[MsfCommand._msf_queryPush.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[MsfCommand.getServerTime.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[MsfCommand.qal_reportEvent.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
    }
}
