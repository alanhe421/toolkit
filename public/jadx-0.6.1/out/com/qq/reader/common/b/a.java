package com.qq.reader.common.b;

/* compiled from: ChannelJNI */
public class a {
    private static boolean a;

    static {
        a = false;
        try {
            System.loadLibrary("channel");
            a = true;
        } catch (Throwable th) {
            th.printStackTrace();
            a = false;
        }
    }

    public static String a() {
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{"ro.com.qq.reader.channel.path"});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (Throwable th) {
            return null;
        }
    }
}
