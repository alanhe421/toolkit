package com.qq.reader.tinker;

import android.util.Log;
import com.qq.reader.appconfig.b;
import com.tencent.tinker.lib.util.TinkerLog.TinkerLogImp;

/* compiled from: MyLogImp */
public class c implements TinkerLogImp {
    private static int a = (b.f ? 0 : 5);

    public void v(String str, String str2, Object... objArr) {
        if (a <= 0) {
            if (objArr != null) {
                str2 = String.format(str2, objArr);
            }
            Log.v(str, str2);
        }
    }

    public void i(String str, String str2, Object... objArr) {
        if (a <= 2) {
            if (objArr != null) {
                str2 = String.format(str2, objArr);
            }
            Log.i(str, str2);
        }
    }

    public void w(String str, String str2, Object... objArr) {
        if (a <= 3) {
            if (objArr != null) {
                str2 = String.format(str2, objArr);
            }
            Log.w(str, str2);
        }
    }

    public void d(String str, String str2, Object... objArr) {
        if (a <= 1) {
            if (objArr != null) {
                str2 = String.format(str2, objArr);
            }
            Log.d(str, str2);
        }
    }

    public void e(String str, String str2, Object... objArr) {
        if (a <= 4) {
            if (objArr != null) {
                str2 = String.format(str2, objArr);
            }
            Log.e(str, str2);
        }
    }

    public void printErrStackTrace(String str, Throwable th, String str2, Object... objArr) {
        String format = objArr == null ? str2 : String.format(str2, objArr);
        if (format == null) {
            format = "";
        }
        Log.e(str, format + "  " + Log.getStackTraceString(th));
    }
}
