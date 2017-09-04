package com.qq.reader.common.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;

/* compiled from: ShortCutUtil */
public class ak {
    public static void a(Context context, String str, Intent intent, ShortcutIconResource shortcutIconResource, boolean z) {
        Intent intent2 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        intent2.putExtra("duplicate", z);
        intent2.putExtra("android.intent.extra.shortcut.NAME", str);
        intent2.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", shortcutIconResource);
        intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
        context.sendBroadcast(intent2);
    }

    public static void a(Context context, String str, Intent intent, Bitmap bitmap, boolean z) {
        Intent intent2 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        intent2.putExtra("duplicate", z);
        intent2.putExtra("android.intent.extra.shortcut.NAME", str);
        intent2.putExtra("android.intent.extra.shortcut.ICON", bitmap);
        intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
        context.sendBroadcast(intent2);
    }

    public static boolean a(Context context, String str) {
        try {
            boolean z;
            ContentResolver contentResolver = context.getContentResolver();
            StringBuilder stringBuilder = new StringBuilder();
            Object b = p.b(context);
            if (b == null || b.trim().equals("")) {
                b = p.a(context, p.a(context) + ".permission.READ_SETTINGS");
            }
            stringBuilder.append("content://");
            if (TextUtils.isEmpty(b)) {
                int i = VERSION.SDK_INT;
                if (i < 8) {
                    stringBuilder.append("com.android.launcher.settings");
                } else if (i < 19) {
                    stringBuilder.append("com.android.launcher2.settings");
                } else {
                    stringBuilder.append("com.android.launcher3.settings");
                }
            } else {
                stringBuilder.append(b);
            }
            stringBuilder.append("/favorites?notify=true");
            Cursor query = contentResolver.query(Uri.parse(stringBuilder.toString()), new String[]{"title"}, "title=? ", new String[]{str}, null);
            if (query == null || query.getCount() <= 0) {
                z = false;
            } else {
                z = true;
            }
            if (query == null || query.isClosed()) {
                return z;
            }
            query.close();
            return z;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
