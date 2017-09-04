package com.tencent.qalsdk.service;

import com.tencent.qalsdk.sdk.MsfCommand;

/* compiled from: MsfServiceReqHandler */
/* synthetic */ class e {
    static final /* synthetic */ int[] a = new int[MsfCommand.values().length];

    static {
        try {
            a[MsfCommand.registerMsfService.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[MsfCommand.unRegisterMsfService.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[MsfCommand.registerPush.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[MsfCommand.unRegisterPush.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[MsfCommand.qal_setAppStatus.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            a[MsfCommand.registerCmdCallback.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            a[MsfCommand.resetCmdCallback.ordinal()] = 7;
        } catch (NoSuchFieldError e7) {
        }
        try {
            a[MsfCommand.getGatewayIp.ordinal()] = 8;
        } catch (NoSuchFieldError e8) {
        }
        try {
            a[MsfCommand.httpreq_remove.ordinal()] = 9;
        } catch (NoSuchFieldError e9) {
        }
        try {
            a[MsfCommand.qal_setAppEnv.ordinal()] = 10;
        } catch (NoSuchFieldError e10) {
        }
        try {
            a[MsfCommand.qal_setLogLevel.ordinal()] = 11;
        } catch (NoSuchFieldError e11) {
        }
        try {
            a[MsfCommand.qal_reportEvent.ordinal()] = 12;
        } catch (NoSuchFieldError e12) {
        }
        try {
            a[MsfCommand.qal_setServerEnv.ordinal()] = 13;
        } catch (NoSuchFieldError e13) {
        }
    }
}
