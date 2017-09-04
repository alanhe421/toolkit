package com.tencent;

final /* synthetic */ class bm {
    static final /* synthetic */ int[] a = new int[TIMElemType.values().length];

    static {
        try {
            a[TIMElemType.Text.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[TIMElemType.Image.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[TIMElemType.Sound.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
