package com.tencent.assistant.link.sdk.c;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ProGuard */
public class a implements c {
    private Context a = null;

    public a(Context context) {
        this.a = context;
    }

    public synchronized List<com.tencent.assistant.link.sdk.b.a> a(String str, String str2) {
        List<com.tencent.assistant.link.sdk.b.a> arrayList;
        arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = c().d().a("select * from applink_action_task_table where packageName = ?", new String[]{str});
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    byte[] blob = cursor.getBlob(cursor.getColumnIndexOrThrow("dataItem"));
                    if (blob != null && blob.length > 0) {
                        com.tencent.assistant.link.sdk.b.a a = com.tencent.assistant.link.sdk.b.a.a(blob);
                        if (a != null) {
                            arrayList.add(a);
                        }
                    }
                } while (cursor.moveToNext());
                if (cursor != null) {
                    cursor.close();
                }
            } else if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
        return arrayList;
    }

    public boolean b(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (c().c().a("applink_action_task_table", "packageName=?", new String[]{str}) > 0) {
            return true;
        }
        return false;
    }

    public String a() {
        return "applink_action_task_table";
    }

    public String b() {
        return "CREATE TABLE if not exists applink_action_task_table( _id INTEGER PRIMARY KEY AUTOINCREMENT, packageName TEXT , version TEXT,dataItem BLOB,state INTEGER)";
    }

    public String[] a(int i, int i2) {
        return null;
    }

    public e c() {
        return b.a(this.a, "");
    }

    public void a(int i, int i2, SQLiteDatabase sQLiteDatabase) {
    }

    public void b(int i, int i2, SQLiteDatabase sQLiteDatabase) {
    }
}
