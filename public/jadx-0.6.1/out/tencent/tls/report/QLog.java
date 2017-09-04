package tencent.tls.report;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import tencent.tls.tools.util;

public class QLog {
    private static final int DEBUG = 4;
    private static final int ERROR = 1;
    private static final int INFO = 3;
    public static final String TAG = "tls_sdk";
    private static final int WARN = 2;
    private static Method info;
    private static Method log;
    private static Object tim;

    public static void init() {
        try {
            tim = Class.forName("com.tencent.TIMManager").getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            log = tim.getClass().getMethod("log", new Class[]{Integer.TYPE, String.class, String.class});
        } catch (Throwable e) {
            e(e);
        }
    }

    public static void initQAL() {
        if (info == null) {
            try {
                info = Class.forName("com.tencent.qalsdk.util.QLog").getMethod("i", new Class[]{String.class, Integer.TYPE, String.class});
            } catch (Exception e) {
                try {
                    init();
                } catch (Throwable e2) {
                    e(e2);
                }
            }
        }
    }

    private static void doit(int i, String str) {
        if (i == 1 || util.LOGCAT_OUT) {
            try {
                if (log == null) {
                    info.invoke(null, new Object[]{TAG, Integer.valueOf(1), str});
                    return;
                }
                log.invoke(tim, new Object[]{Integer.valueOf(i), TAG, str});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void d(String str) {
        _d(wrapMsg(str, 0));
    }

    public static void d(String str, long j) {
        _d(wrapMsg(str, j));
    }

    private static void _d(String str) {
        doit(4, str);
    }

    public static void i(String str) {
        _i(wrapMsg(str, 0));
    }

    public static void i(String str, long j) {
        _i(wrapMsg(str, j));
    }

    private static void _i(String str) {
        doit(3, str);
    }

    public static void w(String str) {
        _w(wrapMsg(str, 0));
    }

    private static void _w(String str) {
        doit(2, str);
    }

    public static void e(Throwable th) {
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter, true);
        th.printStackTrace(printWriter);
        printWriter.flush();
        stringWriter.flush();
        doit(1, stringWriter.toString());
    }

    private static String wrapMsg(String str, long j) {
        String str2 = "";
        if (j != 0) {
            str2 = "[" + Long.toHexString(j) + "]";
        }
        return util.getLineInfo(3) + str2 + str;
    }
}
