package com.tencent.android.tpush.service.d;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import com.qq.taf.jce.JceStruct;
import java.util.Locale;
import tencent.tls.tools.util.APNName;

/* compiled from: ProGuard */
public class a {
    public static int a = 0;
    private static final String b = a.class.getSimpleName();
    private static Uri c = Uri.parse("content://telephony/carriers/preferapn");

    public static byte a(Context context) {
        switch (c(context)) {
            case 1:
                return (byte) 2;
            case 2:
                return (byte) 3;
            case 4:
                return (byte) 1;
            case 8:
                return (byte) 4;
            case 16:
                return (byte) 5;
            case 32:
                return (byte) 6;
            case 64:
                return (byte) 7;
            case 256:
                return (byte) 8;
            case 512:
                return (byte) 9;
            case 1024:
                return (byte) 10;
            case 2048:
                return JceStruct.STRUCT_END;
            default:
                return (byte) 0;
        }
    }

    public static String b(Context context) {
        Cursor query;
        Throwable th;
        Cursor cursor = null;
        String string;
        try {
            query = context.getContentResolver().query(c, null, null, null, null);
            if (query == null) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Exception e) {
                    }
                }
                return null;
            }
            try {
                query.moveToFirst();
                if (query.isAfterLast()) {
                    if (query != null) {
                        try {
                            query.close();
                        } catch (Exception e2) {
                        }
                    }
                    return null;
                }
                string = query.getString(query.getColumnIndex("proxy"));
                if (query == null) {
                    return string;
                }
                try {
                    query.close();
                    return string;
                } catch (Exception e3) {
                    return string;
                }
            } catch (Exception e4) {
                cursor = query;
                try {
                    string = "";
                    if (cursor != null) {
                        return string;
                    }
                    try {
                        cursor.close();
                        return string;
                    } catch (Exception e5) {
                        return string;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    query = cursor;
                    if (query != null) {
                        try {
                            query.close();
                        } catch (Exception e6) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Exception e7) {
            string = "";
            if (cursor != null) {
                return string;
            }
            cursor.close();
            return string;
        } catch (Throwable th4) {
            th = th4;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    public static int c(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return 128;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return 128;
            }
            if (activeNetworkInfo.getTypeName().toUpperCase(Locale.US).equals("WIFI")) {
                return 2;
            }
            if (activeNetworkInfo.getExtraInfo() == null) {
                return 128;
            }
            String toLowerCase = activeNetworkInfo.getExtraInfo().toLowerCase(Locale.US);
            if (toLowerCase.startsWith(APNName.NAME_CMWAP)) {
                return 1;
            }
            if (toLowerCase.startsWith("cmnet") || toLowerCase.startsWith("epc.tmobile.com")) {
                return 4;
            }
            if (toLowerCase.startsWith(APNName.NAME_UNIWAP)) {
                return 16;
            }
            if (toLowerCase.startsWith("uninet")) {
                return 8;
            }
            if (toLowerCase.startsWith("wap")) {
                return 64;
            }
            if (toLowerCase.startsWith("net")) {
                return 32;
            }
            if (toLowerCase.startsWith(APNName.NAME_CTWAP)) {
                return 512;
            }
            if (toLowerCase.startsWith("ctnet")) {
                return 256;
            }
            if (toLowerCase.startsWith(APNName.NAME_3GWAP)) {
                return 1024;
            }
            if (toLowerCase.startsWith("3gnet")) {
                return 2048;
            }
            if (toLowerCase.startsWith("#777")) {
                toLowerCase = b(context);
                if (toLowerCase == null || toLowerCase.length() <= 0) {
                    return 256;
                }
                return 512;
            }
            return 128;
        } catch (Throwable e) {
            com.tencent.android.tpush.a.a.c(b, "getMProxyType>>> ", e);
        }
    }

    public static boolean d(Context context) {
        if (context == null) {
            com.tencent.android.tpush.a.a.h(b, "@@ APNUtil @@ checkNetWork >>> context is null!");
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            boolean z = connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
            return z;
        } catch (Throwable e) {
            com.tencent.android.tpush.a.a.c(b, "APNUtil -> checkNetWork", e);
            a++;
            if (a < 5) {
                return false;
            }
            a = 0;
            return true;
        }
    }
}
