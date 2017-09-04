package com.tencent.mid.api;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.tencent.mid.b.g;
import com.tencent.mid.util.Util;

public class MidProvider extends ContentProvider {
    public static final int CMD_GET_PRIVATE_MID = 1;
    public static final int CMD_GET_PRIVATE_MID_ENTITY = 2;
    public static final int CMD_GET_PRIVATE_NEW_VERSION_MID_ENTITY = 3;
    public static final int CMD_INSERT_NEW_VERSION_MID_ENTITY = 10;
    public static final int CMD_INSERT_NEW_VERSION_MID_OLD_ENTITY = 11;

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public String getType(Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        Context applicationContext = getContext().getApplicationContext();
        Log.i("MID", applicationContext.getPackageName() + ":MidProvider receive cmd:" + lastPathSegment);
        if (Util.isEmpty(lastPathSegment)) {
            return WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
        }
        try {
            MidEntity h;
            switch (Integer.parseInt(lastPathSegment)) {
                case 1:
                    h = g.a(applicationContext).h();
                    return h != null ? h.getMid() : null;
                case 2:
                    h = g.a(applicationContext).h();
                    if (h == null) {
                        return null;
                    }
                    h.setImei("");
                    h.setImsi("");
                    h.setMac("");
                    return h.toString();
                case 3:
                    h = g.a(applicationContext).c();
                    if (h == null) {
                        return null;
                    }
                    h.setImei("");
                    h.setImsi("");
                    h.setMac("");
                    return h.toString();
                default:
                    return "";
            }
        } catch (Throwable th) {
            th.printStackTrace();
            return "-2";
        }
        th.printStackTrace();
        return "-2";
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        String lastPathSegment = uri.getLastPathSegment();
        Context applicationContext = getContext().getApplicationContext();
        if (applicationContext != null) {
            String packageName = applicationContext.getPackageName();
            Log.i("MID", packageName + ":MidProvider receive cmd:" + lastPathSegment);
            if (!Util.isEmpty(lastPathSegment)) {
                try {
                    switch (Integer.parseInt(lastPathSegment)) {
                        case 10:
                            try {
                                lastPathSegment = contentValues.getAsString("mid");
                                Log.i("MID", packageName + ":MidProvider insert old receive cmd:" + MidService.getLocalMidOnly(getContext().getApplicationContext()));
                                if (!Util.isMidValid(MidService.getLocalMidOnly(getContext().getApplicationContext()))) {
                                    Log.i("MID", packageName + ":MidProvider old insert receive cmd:" + lastPathSegment);
                                    g.a(applicationContext).b(MidEntity.parse(lastPathSegment), false);
                                    break;
                                }
                            } catch (Throwable th) {
                                break;
                            }
                            break;
                        case 11:
                            try {
                                lastPathSegment = contentValues.getAsString("mid");
                                Log.i("MID", packageName + ":MidProvider insert receive cmd:" + MidService.getLocalMidOnly(getContext().getApplicationContext()));
                                if (!Util.isMidValid(MidService.getLocalMidOnly(getContext().getApplicationContext()))) {
                                    Log.i("MID", packageName + ":MidProvider insert receive cmd:" + lastPathSegment);
                                    g.a(applicationContext).a(MidEntity.parse(lastPathSegment), false);
                                    break;
                                }
                            } catch (Throwable th2) {
                                break;
                            }
                            break;
                        default:
                            break;
                    }
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
            }
        }
        return null;
    }

    public boolean onCreate() {
        return false;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
