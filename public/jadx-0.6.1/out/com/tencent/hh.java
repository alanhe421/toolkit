package com.tencent;

final /* synthetic */ class hh {
    static final /* synthetic */ int[] a = new int[TIMPendencyGetType.values().length];

    static {
        try {
            a[TIMPendencyGetType.TIM_PENDENCY_GET_BOTH.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[TIMPendencyGetType.TIM_PENDENCY_GET_COME_IN.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[TIMPendencyGetType.TIM_PENDENCY_GET_SEND_OUT.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
