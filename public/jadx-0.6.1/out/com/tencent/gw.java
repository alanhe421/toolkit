package com.tencent;

final /* synthetic */ class gw {
    static final /* synthetic */ int[] a = new int[TIMImageType.values().length];

    static {
        try {
            a[TIMImageType.Original.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[TIMImageType.Thumb.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[TIMImageType.Large.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
