package com.tencent.av.Message;

import com.tencent.av.Message.AvMsg.Type;

final /* synthetic */ class ac {
    static final /* synthetic */ int[] a = new int[Type.values().length];

    static {
        try {
            a[Type.MutiAvInvitation.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[Type.MutiAvCancel.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[Type.MutiAvAccept.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[Type.MutiAvReject.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
    }
}
