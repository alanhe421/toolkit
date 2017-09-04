package com.qq.reader.liveshow.utils;

import com.etrump.jni.ETConverter;
import com.qq.reader.liveshow.b.n;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;

/* compiled from: Constants */
public class e {
    public static boolean a = false;
    public static boolean b = false;
    public static int c;
    public static volatile String d = "/sdcard/123.yuv";
    public static volatile int e = ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE;
    public static volatile int f = ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK;
    public static volatile int g = 0;
    public static volatile String h = "/sdcard/123";
    public static volatile String i = "1.pcm";
    public static volatile String j = "1.pcm";
    public static volatile int k = 16000;
    public static volatile int l = 1;
    public static volatile long m = 0;

    public static void a() {
        a = n.a().e().a();
        b = n.a().e().b();
        l.a();
        b();
        SxbLog.a(n.a().e().c());
        SxbLog.a(n.a().e().b());
    }

    private static void b() {
        if (a) {
            c = 1400017300;
        } else {
            c = 1400019438;
        }
    }
}
