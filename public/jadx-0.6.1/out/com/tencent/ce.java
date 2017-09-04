package com.tencent;

final /* synthetic */ class ce {
    static final /* synthetic */ int[] a = new int[TIMConversationType.values().length];

    static {
        try {
            a[TIMConversationType.Invalid.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[TIMConversationType.C2C.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[TIMConversationType.Group.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[TIMConversationType.System.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
    }
}
