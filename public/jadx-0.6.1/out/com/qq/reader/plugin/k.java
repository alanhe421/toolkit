package com.qq.reader.plugin;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.qq.reader.appconfig.a.b;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: PlugInDatebaseHandle */
public class k extends b implements h {
    private static k b = null;
    private static p c;
    String a = "PlugInListHandle";

    public static synchronized k b() {
        k kVar;
        synchronized (k.class) {
            if (b == null) {
                b = new k();
            }
            if (c == null) {
                c = new p(a.p + "plugin.db", null, 15);
            }
            kVar = b;
        }
        return kVar;
    }

    public synchronized void a(Context context) {
        TypedArray obtainTypedArray = context.getResources().obtainTypedArray(R.array.prePluginDB);
        ao.a(context, obtainTypedArray, a.p, "");
        obtainTypedArray.recycle();
    }

    public synchronized boolean c() {
        boolean z;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = c.a();
            c.h(sQLiteDatabase);
            z = true;
            if (sQLiteDatabase != null) {
                c.c();
            }
        } catch (Exception e) {
            f.a("PlugInDB", "clearPluginDatebase exception : " + e.getMessage());
            if (sQLiteDatabase != null) {
                c.c();
            }
            z = false;
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                c.c();
            }
        }
        return z;
    }

    public synchronized boolean a(ArrayList<l> arrayList) {
        boolean z;
        try {
            SQLiteDatabase a = c.a();
            ContentValues contentValues = new ContentValues();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                l lVar = (l) it.next();
                contentValues.put("plugin_id", lVar.i());
                contentValues.put("plugin_type_id", lVar.k());
                contentValues.put("plugin_name", lVar.l());
                contentValues.put("plugin_version", lVar.m());
                contentValues.put("plugin_info", lVar.n());
                contentValues.put("plugin_str_size", lVar.o());
                contentValues.put("icon_url", lVar.g());
                contentValues.put("image_url", lVar.h());
                contentValues.put("plugin_free", lVar.t());
                contentValues.put("plugin_price", lVar.v());
                contentValues.put("plugin_enable", lVar.p());
                contentValues.put("plugin_max_size", Long.valueOf(lVar.f()));
                contentValues.put("plugin_download_status", Integer.valueOf(lVar.d()));
                contentValues.put("plugin_can_download", Integer.valueOf(lVar.e()));
                contentValues.put("plugin_latest_version", lVar.b());
                contentValues.put("plugin_all_version", lVar.c());
                contentValues.put("plugin_purchase_state", Integer.valueOf(lVar.a()));
                a.insert("plugin_table_name", null, contentValues);
            }
            z = true;
            if (a != null) {
                c.c();
            }
        } catch (Exception e) {
            f.a("PlugInDB", "getAll with exception : " + e.getMessage());
            if (null != null) {
                c.c();
            }
            z = false;
        } catch (Throwable th) {
            if (null != null) {
                c.c();
            }
        }
        return z;
    }

    public synchronized ArrayList<l> d() {
        ArrayList<l> arrayList;
        SQLiteDatabase sQLiteDatabase;
        Exception exception;
        Cursor cursor;
        Throwable th;
        arrayList = new ArrayList();
        Cursor query;
        try {
            SQLiteDatabase a = c.a();
            try {
                query = a.query("plugin_table_name", new String[]{"_id", "plugin_id", "plugin_type_id", "plugin_version", "plugin_name", "plugin_info", "plugin_str_size", "icon_url", "image_url", "plugin_free", "plugin_price", "plugin_enable"}, "plugin_type_id= '' order by _id desc", null, null, null, null);
                try {
                    if (query.moveToFirst()) {
                        String str = "";
                        str = "";
                        str = "";
                        str = "";
                        str = "";
                        str = "";
                        str = "";
                        str = "";
                        str = "";
                        str = "";
                        str = "";
                        do {
                            query.getInt(0);
                            ArrayList<l> arrayList2 = arrayList;
                            arrayList2.add(new l(query.getString(1), query.getString(2), query.getString(4), query.getString(3), query.getString(5), query.getString(6), query.getString(7), query.getString(8), query.getString(9), query.getString(10), query.getString(11), "", ""));
                        } while (query.moveToNext());
                    }
                    if (a != null) {
                        if (query != null) {
                            query.close();
                        }
                        c.c();
                    }
                } catch (Exception e) {
                    sQLiteDatabase = a;
                    exception = e;
                    cursor = query;
                    try {
                        f.a("PlugInDB", "getPluginType with exception : " + exception.getMessage());
                        if (sQLiteDatabase != null) {
                            if (cursor != null) {
                                cursor.close();
                            }
                            c.c();
                        }
                        return arrayList;
                    } catch (Throwable th2) {
                        th = th2;
                        query = cursor;
                        if (sQLiteDatabase != null) {
                            if (query != null) {
                                query.close();
                            }
                            c.c();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    sQLiteDatabase = a;
                    th = th3;
                    if (sQLiteDatabase != null) {
                        if (query != null) {
                            query.close();
                        }
                        c.c();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                sQLiteDatabase = a;
                exception = e2;
                cursor = null;
                f.a("PlugInDB", "getPluginType with exception : " + exception.getMessage());
                if (sQLiteDatabase != null) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    c.c();
                }
                return arrayList;
            } catch (Throwable th32) {
                query = null;
                sQLiteDatabase = a;
                th = th32;
                if (sQLiteDatabase != null) {
                    if (query != null) {
                        query.close();
                    }
                    c.c();
                }
                throw th;
            }
        } catch (Exception e3) {
            exception = e3;
            sQLiteDatabase = null;
            cursor = null;
            f.a("PlugInDB", "getPluginType with exception : " + exception.getMessage());
            if (sQLiteDatabase != null) {
                if (cursor != null) {
                    cursor.close();
                }
                c.c();
            }
            return arrayList;
        } catch (Throwable th4) {
            th = th4;
            query = null;
            sQLiteDatabase = null;
            if (sQLiteDatabase != null) {
                if (query != null) {
                    query.close();
                }
                c.c();
            }
            throw th;
        }
        return arrayList;
    }

    public synchronized ArrayList<l> e() {
        ArrayList<l> arrayList;
        SQLiteDatabase sQLiteDatabase;
        Exception exception;
        Cursor cursor;
        Throwable th;
        arrayList = new ArrayList();
        Cursor cursor2 = null;
        try {
            SQLiteDatabase a = c.a();
            try {
                Cursor query = a.query("plugin_table_name", new String[]{"plugin_id", "plugin_type_id", "plugin_version", "plugin_name", "plugin_info", "plugin_str_size", "icon_url", "image_url", "plugin_free", "plugin_price", "plugin_enable", "plugin_max_size", "plugin_download_status", "plugin_can_download", "plugin_latest_version", "plugin_all_version"}, "plugin_type_id!= ''", null, null, null, null);
                try {
                    if (query.moveToFirst()) {
                        String str = "";
                        str = "";
                        str = "";
                        str = "";
                        str = "";
                        str = "";
                        str = "";
                        str = "";
                        str = "";
                        str = "";
                        str = "";
                        str = "";
                        str = "";
                        do {
                            String string = query.getString(0);
                            String string2 = query.getString(1);
                            String string3 = query.getString(2);
                            String string4 = query.getString(3);
                            String string5 = query.getString(4);
                            String string6 = query.getString(5);
                            String string7 = query.getString(6);
                            String string8 = query.getString(7);
                            String string9 = query.getString(8);
                            String string10 = query.getString(9);
                            String string11 = query.getString(10);
                            long j = query.getLong(11);
                            int i = query.getInt(12);
                            arrayList.add(new l(string, string2, string4, string3, string5, string6, string7, string8, string9, string10, string11, query.getString(14), query.getString(15)).a(j).b(i).c(query.getInt(13)));
                        } while (query.moveToNext());
                    }
                    if (a != null) {
                        if (query != null) {
                            query.close();
                        }
                        c.c();
                    }
                } catch (Exception e) {
                    sQLiteDatabase = a;
                    exception = e;
                    cursor = query;
                    try {
                        f.a("PlugInDB", "getPluginAsType with exception : " + exception.getMessage());
                        if (sQLiteDatabase != null) {
                            if (cursor != null) {
                                cursor.close();
                            }
                            c.c();
                        }
                        return arrayList;
                    } catch (Throwable th2) {
                        th = th2;
                        cursor2 = cursor;
                        if (sQLiteDatabase != null) {
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            c.c();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    cursor2 = query;
                    sQLiteDatabase = a;
                    th = th3;
                    if (sQLiteDatabase != null) {
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        c.c();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                sQLiteDatabase = a;
                exception = e2;
                cursor = null;
                f.a("PlugInDB", "getPluginAsType with exception : " + exception.getMessage());
                if (sQLiteDatabase != null) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    c.c();
                }
                return arrayList;
            } catch (Throwable th32) {
                sQLiteDatabase = a;
                th = th32;
                if (sQLiteDatabase != null) {
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    c.c();
                }
                throw th;
            }
        } catch (Exception e3) {
            exception = e3;
            sQLiteDatabase = null;
            cursor = null;
            f.a("PlugInDB", "getPluginAsType with exception : " + exception.getMessage());
            if (sQLiteDatabase != null) {
                if (cursor != null) {
                    cursor.close();
                }
                c.c();
            }
            return arrayList;
        } catch (Throwable th4) {
            th = th4;
            sQLiteDatabase = null;
            if (sQLiteDatabase != null) {
                if (cursor2 != null) {
                    cursor2.close();
                }
                c.c();
            }
            throw th;
        }
        return arrayList;
    }

    public synchronized ArrayList<l> a(String str) {
        ArrayList<l> arrayList;
        SQLiteDatabase sQLiteDatabase;
        Exception exception;
        Cursor cursor;
        Throwable th;
        arrayList = new ArrayList();
        Cursor cursor2 = null;
        try {
            SQLiteDatabase a = c.a();
            try {
                Cursor query = a.query("plugin_table_name", new String[]{"plugin_id", "plugin_type_id", "plugin_version", "plugin_name", "plugin_info", "plugin_str_size", "icon_url", "image_url", "plugin_free", "plugin_price", "plugin_enable", "plugin_max_size", "plugin_download_status", "plugin_can_download", "plugin_latest_version", "plugin_all_version", "plugin_purchase_state"}, "plugin_type_id= '" + str + "'", null, null, null, null);
                try {
                    if (query.moveToFirst()) {
                        String str2 = "";
                        str2 = "";
                        str2 = "";
                        str2 = "";
                        str2 = "";
                        str2 = "";
                        str2 = "";
                        str2 = "";
                        str2 = "";
                        str2 = "";
                        str2 = "";
                        str2 = "";
                        str2 = "";
                        do {
                            String string = query.getString(0);
                            String string2 = query.getString(1);
                            String string3 = query.getString(2);
                            String string4 = query.getString(3);
                            String string5 = query.getString(4);
                            String string6 = query.getString(5);
                            String string7 = query.getString(6);
                            String string8 = query.getString(7);
                            String string9 = query.getString(8);
                            String string10 = query.getString(9);
                            String string11 = query.getString(10);
                            long j = query.getLong(11);
                            int i = query.getInt(12);
                            int i2 = query.getInt(13);
                            String string12 = query.getString(14);
                            String string13 = query.getString(15);
                            int i3 = query.getInt(16);
                            l c = new l(string, string2, string4, string3, string5, string6, string7, string8, string9, string10, string11, string12, string13).a(j).b(i).c(i2);
                            c.a(i3);
                            arrayList.add(c);
                        } while (query.moveToNext());
                    }
                    if (a != null) {
                        if (query != null) {
                            query.close();
                        }
                        c.c();
                    }
                } catch (Exception e) {
                    sQLiteDatabase = a;
                    exception = e;
                    cursor = query;
                    try {
                        f.a("PlugInDB", "getPluginAsType with exception : " + exception.getMessage());
                        if (sQLiteDatabase != null) {
                            if (cursor != null) {
                                cursor.close();
                            }
                            c.c();
                        }
                        return arrayList;
                    } catch (Throwable th2) {
                        th = th2;
                        cursor2 = cursor;
                        if (sQLiteDatabase != null) {
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            c.c();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    cursor2 = query;
                    sQLiteDatabase = a;
                    th = th3;
                    if (sQLiteDatabase != null) {
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        c.c();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                sQLiteDatabase = a;
                exception = e2;
                cursor = null;
                f.a("PlugInDB", "getPluginAsType with exception : " + exception.getMessage());
                if (sQLiteDatabase != null) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    c.c();
                }
                return arrayList;
            } catch (Throwable th32) {
                sQLiteDatabase = a;
                th = th32;
                if (sQLiteDatabase != null) {
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    c.c();
                }
                throw th;
            }
        } catch (Exception e3) {
            exception = e3;
            sQLiteDatabase = null;
            cursor = null;
            f.a("PlugInDB", "getPluginAsType with exception : " + exception.getMessage());
            if (sQLiteDatabase != null) {
                if (cursor != null) {
                    cursor.close();
                }
                c.c();
            }
            return arrayList;
        } catch (Throwable th4) {
            th = th4;
            sQLiteDatabase = null;
            if (sQLiteDatabase != null) {
                if (cursor2 != null) {
                    cursor2.close();
                }
                c.c();
            }
            throw th;
        }
        return arrayList;
    }

    public synchronized boolean a(String str, int i) {
        boolean z = false;
        synchronized (this) {
            if (!(str == null || str.length() == 0)) {
                try {
                    SQLiteDatabase a = c.a();
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("update plugin_table_name set ");
                    stringBuffer.append("plugin_purchase_state = " + i);
                    stringBuffer.append(" where plugin_id= '" + str + "' and " + "plugin_type_id" + "!= '" + "" + "'");
                    a.execSQL(stringBuffer.toString());
                    z = true;
                } catch (Exception e) {
                    f.a("PlugInDB", "error in  : " + e.toString());
                } finally {
                    c.c();
                }
            }
        }
        return z;
    }

    public synchronized boolean a(String str, String str2, String str3) {
        boolean z = false;
        synchronized (this) {
            if (!(str == null || str.length() == 0 || str2 == null || str2.length() == 0 || str3 == null || str3.length() == 0)) {
                try {
                    SQLiteDatabase a = c.a();
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("update plugin_table_name set ");
                    stringBuffer.append("plugin_free = " + str2);
                    stringBuffer.append(",");
                    stringBuffer.append("plugin_price = " + str3);
                    stringBuffer.append(" where plugin_id= '" + str + "' and " + "plugin_type_id" + "!= '" + "" + "'");
                    a.execSQL(stringBuffer.toString());
                    z = true;
                } catch (Exception e) {
                    f.a("PlugInDB", "error in  : " + e.toString());
                } finally {
                    c.c();
                }
            }
        }
        return z;
    }

    public synchronized boolean a(String str, String str2, String str3, String str4) {
        boolean z = false;
        synchronized (this) {
            if (!(str == null || str.length() == 0)) {
                try {
                    SQLiteDatabase a = c.a();
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("update plugin_table_name set ");
                    if (str2 != null) {
                        stringBuffer.append("plugin_version = '" + str2 + "'");
                    }
                    if (str3 != null) {
                        stringBuffer.append(" , ");
                        stringBuffer.append("plugin_latest_version = '" + str3 + "'");
                    }
                    if (str4 != null) {
                        stringBuffer.append(" , ");
                        stringBuffer.append("plugin_all_version = '" + str4 + "'");
                    }
                    stringBuffer.append(" where plugin_id= '" + str + "' and " + "plugin_type_id" + "!= '" + "" + "'");
                    a.execSQL(stringBuffer.toString());
                    z = true;
                } catch (Exception e) {
                    f.a("PlugInDB", "error in updatePluginVersions : " + e.toString());
                } finally {
                    c.c();
                }
            }
        }
        return z;
    }

    public synchronized boolean a(String str, long j, int i, String str2, int i2) {
        boolean z = true;
        p pVar = null;
        synchronized (this) {
            if (str == null || str.length() == 0 || j < 0) {
                z = false;
            } else {
                try {
                    SQLiteDatabase a = c.a();
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("update plugin_table_name set ");
                    if ((i2 & 4) == 4) {
                        stringBuffer.append("plugin_max_size = " + j);
                    }
                    if ((i2 & 2) == 2) {
                        if ((i2 & 4) == 4) {
                            stringBuffer.append(",");
                        }
                        stringBuffer.append("plugin_download_status = " + i);
                    }
                    if (str2 != null && str2.length() > 0 && (i2 & 1) == 1) {
                        if ((i2 & 2) == 2) {
                            stringBuffer.append(",");
                        }
                        stringBuffer.append("plugin_free = " + str2);
                    }
                    stringBuffer.append(" where plugin_id= '" + str + "' and " + "plugin_type_id" + "!= '" + "" + "'");
                    a.execSQL(stringBuffer.toString());
                    if (c != null) {
                        c.c();
                    }
                } catch (Exception e) {
                    f.a("PlugInDB", "error in updatePlugins : " + e.toString());
                    z = pVar;
                } finally {
                    pVar = c;
                    if (pVar != null) {
                        pVar = c;
                        pVar.c();
                    }
                }
            }
        }
        return z;
    }

    public synchronized l b(String str) {
        SQLiteDatabase sQLiteDatabase;
        Exception exception;
        Cursor cursor;
        l lVar;
        Throwable th;
        if (str != null) {
            if (str.length() != 0) {
                Cursor cursor2 = null;
                try {
                    Cursor query;
                    SQLiteDatabase a = c.a();
                    try {
                        query = a.query("plugin_table_name", new String[]{"plugin_id", "plugin_type_id", "plugin_version", "plugin_name", "plugin_info", "plugin_str_size", "icon_url", "image_url", "plugin_free", "plugin_price", "plugin_enable", "plugin_max_size", "plugin_download_status", "plugin_can_download", "plugin_latest_version", "plugin_all_version"}, "plugin_id= '" + str + "' and " + "plugin_type_id" + "!= '" + "" + "'", null, null, null, null);
                    } catch (Exception e) {
                        sQLiteDatabase = a;
                        exception = e;
                        cursor = null;
                        try {
                            f.a("PlugInDB", "getPluginAsType with exception : " + exception.getMessage());
                            if (sQLiteDatabase != null) {
                                if (cursor != null) {
                                    cursor.close();
                                }
                                if (c != null) {
                                    c.c();
                                }
                            }
                            lVar = null;
                            return lVar;
                        } catch (Throwable th2) {
                            th = th2;
                            cursor2 = cursor;
                            if (sQLiteDatabase != null) {
                                if (cursor2 != null) {
                                    cursor2.close();
                                }
                                if (c != null) {
                                    c.c();
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        sQLiteDatabase = a;
                        th = th3;
                        if (sQLiteDatabase != null) {
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            if (c != null) {
                                c.c();
                            }
                        }
                        throw th;
                    }
                    try {
                        if (query.moveToFirst()) {
                            String string = query.getString(0);
                            String string2 = query.getString(1);
                            String string3 = query.getString(2);
                            String string4 = query.getString(3);
                            String string5 = query.getString(4);
                            String string6 = query.getString(5);
                            String string7 = query.getString(6);
                            String string8 = query.getString(7);
                            String string9 = query.getString(8);
                            String string10 = query.getString(9);
                            String string11 = query.getString(10);
                            long j = query.getLong(11);
                            int i = query.getInt(12);
                            l c = new l(string, string2, string4, string3, string5, string6, string7, string8, string9, string10, string11, query.getString(14), query.getString(15)).a(j).b(i).c(query.getInt(13));
                            if (a != null) {
                                if (query != null) {
                                    query.close();
                                }
                                if (c != null) {
                                    c.c();
                                }
                            }
                            lVar = c;
                        } else {
                            if (a != null) {
                                if (query != null) {
                                    query.close();
                                }
                                if (c != null) {
                                    c.c();
                                }
                            }
                            lVar = null;
                        }
                    } catch (Exception e2) {
                        sQLiteDatabase = a;
                        exception = e2;
                        cursor = query;
                        f.a("PlugInDB", "getPluginAsType with exception : " + exception.getMessage());
                        if (sQLiteDatabase != null) {
                            if (cursor != null) {
                                cursor.close();
                            }
                            if (c != null) {
                                c.c();
                            }
                        }
                        lVar = null;
                        return lVar;
                    } catch (Throwable th32) {
                        cursor2 = query;
                        sQLiteDatabase = a;
                        th = th32;
                        if (sQLiteDatabase != null) {
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            if (c != null) {
                                c.c();
                            }
                        }
                        throw th;
                    }
                } catch (Exception e3) {
                    exception = e3;
                    sQLiteDatabase = null;
                    cursor = null;
                    f.a("PlugInDB", "getPluginAsType with exception : " + exception.getMessage());
                    if (sQLiteDatabase != null) {
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (c != null) {
                            c.c();
                        }
                    }
                    lVar = null;
                    return lVar;
                } catch (Throwable th4) {
                    th = th4;
                    sQLiteDatabase = null;
                    if (sQLiteDatabase != null) {
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        if (c != null) {
                            c.c();
                        }
                    }
                    throw th;
                }
            }
        }
        lVar = null;
        return lVar;
    }

    public synchronized String c(String str) {
        Cursor query;
        String str2;
        Cursor cursor;
        Object obj;
        Exception exception;
        Throwable th;
        Cursor cursor2 = null;
        synchronized (this) {
            if (str != null) {
                if (str.length() != 0) {
                    try {
                        SQLiteDatabase a = c.a();
                        try {
                            query = a.query("plugin_table_name", new String[]{"plugin_id", "plugin_name"}, "plugin_id= '" + str + "' and " + "plugin_type_id" + "!= '" + "" + "'", null, null, null, null);
                            try {
                                if (query.moveToFirst()) {
                                    query.getString(0);
                                    String string = query.getString(1);
                                    if (a != null) {
                                        if (query != null) {
                                            query.close();
                                        }
                                        if (c != null) {
                                            c.c();
                                        }
                                    }
                                    str2 = string;
                                } else {
                                    if (a != null) {
                                        if (query != null) {
                                            query.close();
                                        }
                                        if (c != null) {
                                            c.c();
                                        }
                                    }
                                    str2 = null;
                                }
                            } catch (Exception e) {
                                Exception exception2 = e;
                                cursor = query;
                                obj = a;
                                exception = exception2;
                                try {
                                    f.a("PlugInDB", "getPluginAsType with exception : " + exception.getMessage());
                                    if (query != null) {
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        if (c != null) {
                                            c.c();
                                        }
                                    }
                                    str2 = null;
                                    return str2;
                                } catch (Throwable th2) {
                                    th = th2;
                                    cursor2 = cursor;
                                    if (query != null) {
                                        if (cursor2 != null) {
                                            cursor2.close();
                                        }
                                        if (c != null) {
                                            c.c();
                                        }
                                    }
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                cursor2 = query;
                                obj = a;
                                th = th3;
                                if (query != null) {
                                    if (cursor2 != null) {
                                        cursor2.close();
                                    }
                                    if (c != null) {
                                        c.c();
                                    }
                                }
                                throw th;
                            }
                        } catch (Exception e2) {
                            obj = a;
                            exception = e2;
                            cursor = null;
                            f.a("PlugInDB", "getPluginAsType with exception : " + exception.getMessage());
                            if (query != null) {
                                if (cursor != null) {
                                    cursor.close();
                                }
                                if (c != null) {
                                    c.c();
                                }
                            }
                            str2 = null;
                            return str2;
                        } catch (Throwable th32) {
                            obj = a;
                            th = th32;
                            if (query != null) {
                                if (cursor2 != null) {
                                    cursor2.close();
                                }
                                if (c != null) {
                                    c.c();
                                }
                            }
                            throw th;
                        }
                    } catch (Exception e3) {
                        exception = e3;
                        cursor = null;
                        query = null;
                        f.a("PlugInDB", "getPluginAsType with exception : " + exception.getMessage());
                        if (query != null) {
                            if (cursor != null) {
                                cursor.close();
                            }
                            if (c != null) {
                                c.c();
                            }
                        }
                        str2 = null;
                        return str2;
                    } catch (Throwable th4) {
                        th = th4;
                        query = null;
                        if (query != null) {
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            if (c != null) {
                                c.c();
                            }
                        }
                        throw th;
                    }
                }
            }
            str2 = null;
        }
        return str2;
    }

    public void a() {
        synchronized (k.class) {
            b = null;
            c = null;
        }
    }
}
