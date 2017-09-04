package com.tencent.assistant.link.sdk;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.tencent.assistant.link.sdk.a.a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ProGuard */
public class AppLinkHelper {
    public static final String AUTHORITY = "com.tencent.android.qqdownloader.applink.provider";
    public static final String READ_PERMISSION = "com.tencent.applink.sdk.permission.APPLINK_READ_PERMISSION";
    public static final String WRITE_PERMISSION = "com.tencent.applink.sdk.permission.APPLINK_WRITE_PERMISSION";

    public static String getSDCardActionTask(Context context) {
        List list = null;
        if (context == null) {
            return null;
        }
        String packageName = context.getPackageName();
        int a = a(context);
        if (!TextUtils.isEmpty(packageName)) {
            try {
                if (context.checkCallingOrSelfPermission(READ_PERMISSION) == 0) {
                    list = a(context, packageName);
                } else if (a.b()) {
                    list = new com.tencent.assistant.link.sdk.c.a(context).a(packageName, String.valueOf(a));
                }
                if (list != null && list.size() > 0) {
                    com.tencent.assistant.link.sdk.b.a aVar = (com.tencent.assistant.link.sdk.b.a) list.get(0);
                    if (aVar != null) {
                        return aVar.c;
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return "";
    }

    public static void deteleActionTask(Context context) {
        if (context != null) {
            try {
                String packageName = context.getPackageName();
                int a = a(context);
                if (context.checkCallingOrSelfPermission(WRITE_PERMISSION) == 0) {
                    ContentResolver contentResolver = context.getContentResolver();
                    if (contentResolver != null) {
                        contentResolver.delete(Uri.parse("content://com.tencent.android.qqdownloader.applink.provider/applink_action_task_table"), "packageName=?", new String[]{packageName});
                    }
                } else if (a.b()) {
                    new com.tencent.assistant.link.sdk.c.a(context).b(packageName, String.valueOf(a));
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private static int a(Context context) {
        int i = 0;
        if (context == null) {
            return i;
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return i;
        }
    }

    private static List<com.tencent.assistant.link.sdk.b.a> a(Context context, String str) {
        Cursor query;
        Throwable th;
        if (TextUtils.isEmpty(str) || context == null) {
            return null;
        }
        ContentResolver contentResolver = context.getContentResolver();
        if (contentResolver == null) {
            return null;
        }
        try {
            query = contentResolver.query(Uri.parse("content://com.tencent.android.qqdownloader.applink.provider/applink_action_task_table"), null, "packageName=?", new String[]{str}, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        List list = null;
                        do {
                            byte[] blob = query.getBlob(query.getColumnIndexOrThrow("dataItem"));
                            if (blob != null && blob.length > 0) {
                                com.tencent.assistant.link.sdk.b.a a = com.tencent.assistant.link.sdk.b.a.a(blob);
                                if (a != null) {
                                    if (list == null) {
                                        list = new ArrayList();
                                    }
                                    list.add(a);
                                }
                            }
                        } while (query.moveToNext());
                        if (query != null) {
                            query.close();
                        }
                        return r0;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        th.printStackTrace();
                        if (query != null) {
                            return null;
                        }
                        query.close();
                        return null;
                    } catch (Throwable th3) {
                        th = th3;
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                }
            }
            List<com.tencent.assistant.link.sdk.b.a> list2 = null;
            if (query != null) {
                query.close();
            }
            return list2;
        } catch (Throwable th4) {
            th = th4;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }
}
