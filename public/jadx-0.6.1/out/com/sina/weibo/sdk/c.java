package com.sina.weibo.sdk;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.sina.weibo.sdk.b.d;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.json.JSONObject;

/* compiled from: WeiboAppManager */
public class c {
    private static final String a = c.class.getName();
    private static final Uri b = Uri.parse("content://com.sina.weibo.sdkProvider/query/package");
    private static final Uri c = Uri.parse("content://com.sina.weibo.sdkProvider/query/package");
    private static c d;
    private Context e;
    private com.sina.weibo.sdk.auth.c f;

    private c(Context context) {
        this.e = context.getApplicationContext();
    }

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (c.class) {
            if (d == null) {
                d = new c(context);
            }
            cVar = d;
        }
        return cVar;
    }

    public synchronized com.sina.weibo.sdk.auth.c a() {
        if (this.f == null) {
            this.f = b(this.e);
        }
        return this.f;
    }

    private com.sina.weibo.sdk.auth.c b(Context context) {
        Object obj = 1;
        com.sina.weibo.sdk.auth.c c = c(context);
        com.sina.weibo.sdk.auth.c d = d(context);
        Object obj2 = c != null ? 1 : null;
        if (d == null) {
            obj = null;
        }
        if (obj2 == null || obj == null) {
            if (obj2 != null) {
                return c;
            }
            if (obj != null) {
                return d;
            }
            return null;
        } else if (c.c() >= d.c()) {
            return c;
        } else {
            return d;
        }
    }

    private com.sina.weibo.sdk.auth.c c(Context context) {
        Cursor query;
        Exception e;
        Throwable th;
        ContentResolver contentResolver = context.getContentResolver();
        try {
            Cursor query2 = contentResolver.query(b, null, null, null, null);
            if (query2 == null) {
                try {
                    query = contentResolver.query(c, null, null, null, null);
                    if (query == null) {
                        if (query != null) {
                            query.close();
                        }
                        return null;
                    }
                } catch (Exception e2) {
                    e = e2;
                    query = query2;
                    try {
                        d.c(a, e.getMessage());
                        if (query != null) {
                            query.close();
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    query = query2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
            query = query2;
            try {
                int columnIndex = query.getColumnIndex("support_api");
                int columnIndex2 = query.getColumnIndex("package");
                int columnIndex3 = query.getColumnIndex("sso_activity");
                if (query.moveToFirst()) {
                    int parseInt;
                    Object string;
                    int i = -1;
                    try {
                        parseInt = Integer.parseInt(query.getString(columnIndex));
                    } catch (NumberFormatException e3) {
                        e3.printStackTrace();
                        parseInt = i;
                    }
                    String string2 = query.getString(columnIndex2);
                    if (columnIndex3 > 0) {
                        string = query.getString(columnIndex3);
                    } else {
                        string = null;
                    }
                    if (!TextUtils.isEmpty(string2) && a.a(context, string2)) {
                        com.sina.weibo.sdk.auth.c cVar = new com.sina.weibo.sdk.auth.c();
                        cVar.a(string2);
                        cVar.a(parseInt);
                        if (!TextUtils.isEmpty(string)) {
                            cVar.b(string);
                        }
                        if (query == null) {
                            return cVar;
                        }
                        query.close();
                        return cVar;
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (Exception e4) {
                e = e4;
                d.c(a, e.getMessage());
                if (query != null) {
                    query.close();
                }
                return null;
            }
        } catch (Exception e5) {
            e = e5;
            query = null;
            d.c(a, e.getMessage());
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
        return null;
    }

    private com.sina.weibo.sdk.auth.c d(Context context) {
        com.sina.weibo.sdk.auth.c cVar = null;
        Intent intent = new Intent("com.sina.weibo.action.sdkidentity");
        intent.addCategory("android.intent.category.DEFAULT");
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (!(queryIntentServices == null || queryIntentServices.isEmpty())) {
            for (ResolveInfo resolveInfo : queryIntentServices) {
                if (!(resolveInfo.serviceInfo == null || resolveInfo.serviceInfo.applicationInfo == null || TextUtils.isEmpty(resolveInfo.serviceInfo.packageName))) {
                    com.sina.weibo.sdk.auth.c a = a(resolveInfo.serviceInfo.packageName);
                    if (a == null) {
                        a = cVar;
                    }
                    cVar = a;
                }
            }
        }
        return cVar;
    }

    public com.sina.weibo.sdk.auth.c a(String str) {
        InputStream open;
        Exception e;
        Throwable th;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            byte[] bArr = new byte[4096];
            open = this.e.createPackageContext(str, 2).getAssets().open("weibo_for_sdk.json");
            try {
                int read;
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    read = open.read(bArr, 0, 4096);
                    if (read == -1) {
                        break;
                    }
                    stringBuilder.append(new String(bArr, 0, read));
                }
                if (!TextUtils.isEmpty(stringBuilder.toString()) && a.a(this.e, str)) {
                    JSONObject jSONObject = new JSONObject(stringBuilder.toString());
                    read = jSONObject.optInt("support_api", -1);
                    com.sina.weibo.sdk.auth.c cVar = new com.sina.weibo.sdk.auth.c();
                    cVar.a(str);
                    cVar.a(read);
                    cVar.b(jSONObject.optString("authActivityName", "com.sina.weibo.SSOActivity"));
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e2) {
                        }
                    }
                    return cVar;
                } else if (open == null) {
                    return null;
                } else {
                    try {
                        open.close();
                        return null;
                    } catch (IOException e3) {
                        return null;
                    }
                }
            } catch (Exception e4) {
                e = e4;
                try {
                    d.c(a, e.getMessage());
                    if (open != null) {
                        return null;
                    }
                    try {
                        open.close();
                        return null;
                    } catch (IOException e5) {
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e6) {
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e7) {
            e = e7;
            open = null;
            d.c(a, e.getMessage());
            if (open != null) {
                return null;
            }
            open.close();
            return null;
        } catch (Throwable th3) {
            open = null;
            th = th3;
            if (open != null) {
                open.close();
            }
            throw th;
        }
    }
}
