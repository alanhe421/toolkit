package com.qq.reader.plugin;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.f;
import com.tencent.connect.common.Constants;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;

/* compiled from: PluginSDDatabaseHelper */
public class p extends c {
    private final String a = "SELECTION";

    public p(String str, CursorFactory cursorFactory, int i) {
        super(str, cursorFactory, i);
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("create table if not exists plugin_table_name (_id integer primary key autoincrement,plugin_id text not null,plugin_type_id text not null,plugin_name text not null,plugin_version text not null,plugin_info text not null,plugin_str_size text not null,icon_url text not null,image_url text not null,plugin_free text not null,plugin_price text not null,plugin_enable text not null,plugin_max_size long default -1,plugin_download_status long default 0,plugin_can_download long default 0,plugin_purchase_state integer default 0,plugin_latest_version text not null,plugin_all_version text not null);");
        } catch (Exception e) {
            f.a("SELECTION", "createTable : " + e.getMessage());
        }
    }

    public void c(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("update plugin_table_name set plugin_latest_version = 'android_plugin_pdf_3.0' where plugin_id= '1'");
        sQLiteDatabase.execSQL("update plugin_table_name set plugin_all_version = 'android_plugin_pdf_3.0' where plugin_id= '1'");
    }

    public void d(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        try {
            String str = "select plugin_all_version from plugin_table_name";
            rawQuery = sQLiteDatabase.rawQuery("select plugin_latest_version from plugin_table_name", null);
            try {
                cursor2 = sQLiteDatabase.rawQuery(str, null);
                if (rawQuery != null) {
                    rawQuery.close();
                }
                if (cursor2 != null) {
                    cursor2.close();
                }
            } catch (Exception e) {
                try {
                    sQLiteDatabase.execSQL("alter table plugin_table_name add plugin_latest_version text");
                    sQLiteDatabase.execSQL("alter table plugin_table_name add plugin_all_version text");
                    sQLiteDatabase.execSQL("update plugin_table_name set plugin_latest_version = plugin_version , plugin_all_version = plugin_version");
                    sQLiteDatabase.execSQL("update plugin_table_name set plugin_latest_version = 'android_plugin_pdf_2.0' where plugin_id= '1'");
                    sQLiteDatabase.execSQL("update plugin_table_name set plugin_all_version = 'android_plugin_pdf_2.0' where plugin_id= '1'");
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    cursor = rawQuery;
                    th = th3;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            }
        } catch (Exception e2) {
            rawQuery = cursor2;
            sQLiteDatabase.execSQL("alter table plugin_table_name add plugin_latest_version text");
            sQLiteDatabase.execSQL("alter table plugin_table_name add plugin_all_version text");
            sQLiteDatabase.execSQL("update plugin_table_name set plugin_latest_version = plugin_version , plugin_all_version = plugin_version");
            sQLiteDatabase.execSQL("update plugin_table_name set plugin_latest_version = 'android_plugin_pdf_2.0' where plugin_id= '1'");
            sQLiteDatabase.execSQL("update plugin_table_name set plugin_all_version = 'android_plugin_pdf_2.0' where plugin_id= '1'");
            if (rawQuery != null) {
                rawQuery.close();
            }
            if (cursor2 != null) {
                cursor2.close();
            }
        } catch (Throwable th4) {
            th = th4;
            cursor = cursor2;
            if (cursor != null) {
                cursor.close();
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    private void m(SQLiteDatabase sQLiteDatabase) {
        Cursor query;
        Throwable th;
        try {
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            query = sQLiteDatabase2.query("plugin_table_name", new String[]{"plugin_type_id"}, "plugin_type_id= '7'", null, null, null, null);
            try {
                if (query.getCount() == 0) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("plugin_id", "7");
                    contentValues.put("plugin_type_id", "");
                    contentValues.put("plugin_name", "人声朗读");
                    contentValues.put("plugin_version", "");
                    contentValues.put("plugin_info", "");
                    contentValues.put("plugin_str_size", "");
                    contentValues.put("icon_url", "http://mag.reader.3g.qq.com/plugin/7_c.png");
                    contentValues.put("image_url", "");
                    contentValues.put("plugin_free", "");
                    contentValues.put("plugin_price", "");
                    contentValues.put("plugin_enable", "1");
                    contentValues.put("plugin_max_size", Long.valueOf(0));
                    contentValues.put("plugin_download_status", Integer.valueOf(1));
                    contentValues.put("plugin_can_download", Integer.valueOf(0));
                    sQLiteDatabase.insert("plugin_table_name", null, contentValues);
                    contentValues = new ContentValues();
                    contentValues.put("plugin_id", "29");
                    contentValues.put("plugin_type_id", "7");
                    contentValues.put("plugin_name", "人声朗读");
                    contentValues.put("plugin_version", "android_plugin_tts_1.0");
                    contentValues.put("plugin_info", "人声朗读，用耳朵体验不同声音“读”书的效果。\r\n下载人声朗读安装包之后，" + ReaderApplication.getApplicationImp().getString(R.string.app_name) + "就可以使用人声朗读功能。");
                    contentValues.put("plugin_str_size", "12.3M");
                    contentValues.put("icon_url", "http://mag.reader.3g.qq.com/plugin/29_7_c.png");
                    contentValues.put("image_url", "http://mag.reader.3g.qq.com/plugin/29_7_m.png");
                    contentValues.put("plugin_free", "0");
                    contentValues.put("plugin_price", "免费");
                    contentValues.put("plugin_enable", "1");
                    contentValues.put("plugin_max_size", Long.valueOf(0));
                    contentValues.put("plugin_download_status", Integer.valueOf(1));
                    contentValues.put("plugin_can_download", Integer.valueOf(0));
                    contentValues.put("plugin_latest_version", "android_plugin_tts_1.0");
                    contentValues.put("plugin_all_version", "android_plugin_tts_1.0");
                    sQLiteDatabase.insert("plugin_table_name", null, contentValues);
                }
                if (query != null) {
                    query.close();
                }
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    private void n(SQLiteDatabase sQLiteDatabase) {
        Throwable th;
        Cursor query;
        try {
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            query = sQLiteDatabase2.query("plugin_table_name", new String[]{"plugin_type_id"}, "plugin_type_id= '8'", null, null, null, null);
            try {
                if (query.getCount() == 0) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("plugin_id", "8");
                    contentValues.put("plugin_type_id", "");
                    contentValues.put("plugin_name", "微云");
                    contentValues.put("plugin_version", "");
                    contentValues.put("plugin_info", "");
                    contentValues.put("plugin_str_size", "");
                    contentValues.put("icon_url", "http://mag.reader.3g.qq.com/plugin/8_c.png");
                    contentValues.put("image_url", "");
                    contentValues.put("plugin_free", "");
                    contentValues.put("plugin_price", "");
                    contentValues.put("plugin_enable", "1");
                    contentValues.put("plugin_max_size", Long.valueOf(0));
                    contentValues.put("plugin_download_status", Integer.valueOf(1));
                    contentValues.put("plugin_can_download", Integer.valueOf(0));
                    sQLiteDatabase.insert("plugin_table_name", null, contentValues);
                    contentValues = new ContentValues();
                    contentValues.put("plugin_id", "30");
                    contentValues.put("plugin_type_id", "8");
                    contentValues.put("plugin_name", "微云");
                    contentValues.put("plugin_version", "android_plugin_weiyun_2.0");
                    contentValues.put("plugin_info", "原QQ网盘全新升级为微云，您可以通过微云方便地同步电脑和手机中的书籍，不需要数据线导入图书。下载到微云客户端中的书籍，点击“使用其它应用打开”，选择“" + ReaderApplication.getApplicationImp().getString(R.string.app_name) + "”，这本书就会打开并自动加入到书架。");
                    contentValues.put("plugin_str_size", "8.18M");
                    contentValues.put("icon_url", "http://mag.reader.3g.qq.com/plugin/30_8_c.png");
                    contentValues.put("image_url", "http://mag.reader.3g.qq.com/plugin/30_8_m.png");
                    contentValues.put("plugin_free", "0");
                    contentValues.put("plugin_price", "免费");
                    contentValues.put("plugin_enable", "1");
                    contentValues.put("plugin_max_size", Long.valueOf(0));
                    contentValues.put("plugin_download_status", Integer.valueOf(1));
                    contentValues.put("plugin_can_download", Integer.valueOf(0));
                    contentValues.put("plugin_latest_version", "android_plugin_weiyun_2.0");
                    contentValues.put("plugin_all_version", "android_plugin_weiyun_2.0");
                    sQLiteDatabase.insert("plugin_table_name", null, contentValues);
                }
                if (query != null) {
                    query.close();
                }
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    private void o(SQLiteDatabase sQLiteDatabase) {
        Throwable th;
        Cursor query;
        try {
            Object obj;
            ContentValues contentValues;
            ArrayList arrayList;
            ArrayList arrayList2;
            ArrayList arrayList3;
            ArrayList arrayList4;
            ArrayList arrayList5;
            ArrayList arrayList6;
            ContentValues contentValues2;
            int i;
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            query = sQLiteDatabase2.query("plugin_table_name", new String[]{"plugin_id"}, "plugin_id>= '31' and plugin_id<= '37'", null, null, null, null);
            if (query != null) {
                try {
                    if (query.getCount() == 7) {
                        query.close();
                        obj = 1;
                        query = null;
                        if (obj == null) {
                            contentValues = new ContentValues();
                            arrayList = new ArrayList();
                            arrayList2 = new ArrayList();
                            arrayList3 = new ArrayList();
                            arrayList4 = new ArrayList();
                            arrayList5 = new ArrayList();
                            arrayList6 = new ArrayList();
                            arrayList.add("31");
                            arrayList.add("32");
                            arrayList.add("33");
                            arrayList.add("34");
                            arrayList.add("35");
                            arrayList.add("36");
                            arrayList.add("37");
                            arrayList5.add("http://mag.reader.3g.qq.com/plugin/31_2_c.png");
                            arrayList5.add("http://mag.reader.3g.qq.com/plugin/32_2_c.png");
                            arrayList5.add("http://mag.reader.3g.qq.com/plugin/33_2_c.png");
                            arrayList5.add("http://mag.reader.3g.qq.com/plugin/34_2_c.png");
                            arrayList5.add("http://mag.reader.3g.qq.com/plugin/35_2_c.png");
                            arrayList5.add("http://mag.reader.3g.qq.com/plugin/36_2_c.png");
                            arrayList5.add("http://mag.reader.3g.qq.com/plugin/37_2_c.png");
                            arrayList6.add("http://mag.reader.3g.qq.com/plugin/31_2_m.png");
                            arrayList6.add("http://mag.reader.3g.qq.com/plugin/32_2_m.png");
                            arrayList6.add("http://mag.reader.3g.qq.com/plugin/33_2_m.png");
                            arrayList6.add("http://mag.reader.3g.qq.com/plugin/34_2_m.png");
                            arrayList6.add("http://mag.reader.3g.qq.com/plugin/35_2_m.png");
                            arrayList6.add("http://mag.reader.3g.qq.com/plugin/36_2_m.png");
                            arrayList6.add("http://mag.reader.3g.qq.com/plugin/37_2_m.png");
                            arrayList2.add("汉仪楷体");
                            arrayList2.add("汉仪旗黑55S");
                            arrayList2.add("汉仪书宋二");
                            arrayList2.add("汉仪乐喵体");
                            arrayList2.add("汉仪细中圆");
                            arrayList2.add("汉仪细行楷");
                            arrayList2.add("汉仪小隶书");
                            arrayList3.add("HYKaiT18030F");
                            arrayList3.add("HYQiH18030F55");
                            arrayList3.add("HYShuSE18030F");
                            arrayList3.add("HYLeiMTGBKF");
                            arrayList3.add("HYXiZYJF");
                            arrayList3.add("HYXiXKJF");
                            arrayList3.add("HYXiaoLS18030F");
                            arrayList4.add("1.4M");
                            arrayList4.add("1.1M");
                            arrayList4.add("1.4M");
                            arrayList4.add("880K");
                            arrayList4.add("587k");
                            arrayList4.add("481k");
                            arrayList4.add("1.3M");
                            contentValues2 = new ContentValues();
                            for (i = 0; i < arrayList.size(); i++) {
                                contentValues2.put("plugin_id", (String) arrayList.get(i));
                                contentValues2.put("plugin_type_id", "2");
                                contentValues2.put("plugin_name", (String) arrayList2.get(i));
                                contentValues2.put("plugin_version", (String) arrayList3.get(i));
                                contentValues2.put("plugin_info", "");
                                contentValues2.put("plugin_str_size", (String) arrayList4.get(i));
                                contentValues2.put("icon_url", (String) arrayList5.get(i));
                                contentValues2.put("image_url", (String) arrayList6.get(i));
                                contentValues2.put("plugin_free", "0");
                                contentValues2.put("plugin_price", "免费");
                                contentValues2.put("plugin_enable", "1");
                                contentValues2.put("plugin_max_size", Long.valueOf(0));
                                contentValues2.put("plugin_download_status", Integer.valueOf(1));
                                contentValues2.put("plugin_can_download", Integer.valueOf(0));
                                contentValues2.put("plugin_latest_version", (String) arrayList3.get(i));
                                contentValues2.put("plugin_all_version", (String) arrayList3.get(i));
                                sQLiteDatabase.insert("plugin_table_name", null, contentValues2);
                            }
                        }
                        if (query != null) {
                            query.close();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
            obj = null;
            if (obj == null) {
                contentValues = new ContentValues();
                arrayList = new ArrayList();
                arrayList2 = new ArrayList();
                arrayList3 = new ArrayList();
                arrayList4 = new ArrayList();
                arrayList5 = new ArrayList();
                arrayList6 = new ArrayList();
                arrayList.add("31");
                arrayList.add("32");
                arrayList.add("33");
                arrayList.add("34");
                arrayList.add("35");
                arrayList.add("36");
                arrayList.add("37");
                arrayList5.add("http://mag.reader.3g.qq.com/plugin/31_2_c.png");
                arrayList5.add("http://mag.reader.3g.qq.com/plugin/32_2_c.png");
                arrayList5.add("http://mag.reader.3g.qq.com/plugin/33_2_c.png");
                arrayList5.add("http://mag.reader.3g.qq.com/plugin/34_2_c.png");
                arrayList5.add("http://mag.reader.3g.qq.com/plugin/35_2_c.png");
                arrayList5.add("http://mag.reader.3g.qq.com/plugin/36_2_c.png");
                arrayList5.add("http://mag.reader.3g.qq.com/plugin/37_2_c.png");
                arrayList6.add("http://mag.reader.3g.qq.com/plugin/31_2_m.png");
                arrayList6.add("http://mag.reader.3g.qq.com/plugin/32_2_m.png");
                arrayList6.add("http://mag.reader.3g.qq.com/plugin/33_2_m.png");
                arrayList6.add("http://mag.reader.3g.qq.com/plugin/34_2_m.png");
                arrayList6.add("http://mag.reader.3g.qq.com/plugin/35_2_m.png");
                arrayList6.add("http://mag.reader.3g.qq.com/plugin/36_2_m.png");
                arrayList6.add("http://mag.reader.3g.qq.com/plugin/37_2_m.png");
                arrayList2.add("汉仪楷体");
                arrayList2.add("汉仪旗黑55S");
                arrayList2.add("汉仪书宋二");
                arrayList2.add("汉仪乐喵体");
                arrayList2.add("汉仪细中圆");
                arrayList2.add("汉仪细行楷");
                arrayList2.add("汉仪小隶书");
                arrayList3.add("HYKaiT18030F");
                arrayList3.add("HYQiH18030F55");
                arrayList3.add("HYShuSE18030F");
                arrayList3.add("HYLeiMTGBKF");
                arrayList3.add("HYXiZYJF");
                arrayList3.add("HYXiXKJF");
                arrayList3.add("HYXiaoLS18030F");
                arrayList4.add("1.4M");
                arrayList4.add("1.1M");
                arrayList4.add("1.4M");
                arrayList4.add("880K");
                arrayList4.add("587k");
                arrayList4.add("481k");
                arrayList4.add("1.3M");
                contentValues2 = new ContentValues();
                for (i = 0; i < arrayList.size(); i++) {
                    contentValues2.put("plugin_id", (String) arrayList.get(i));
                    contentValues2.put("plugin_type_id", "2");
                    contentValues2.put("plugin_name", (String) arrayList2.get(i));
                    contentValues2.put("plugin_version", (String) arrayList3.get(i));
                    contentValues2.put("plugin_info", "");
                    contentValues2.put("plugin_str_size", (String) arrayList4.get(i));
                    contentValues2.put("icon_url", (String) arrayList5.get(i));
                    contentValues2.put("image_url", (String) arrayList6.get(i));
                    contentValues2.put("plugin_free", "0");
                    contentValues2.put("plugin_price", "免费");
                    contentValues2.put("plugin_enable", "1");
                    contentValues2.put("plugin_max_size", Long.valueOf(0));
                    contentValues2.put("plugin_download_status", Integer.valueOf(1));
                    contentValues2.put("plugin_can_download", Integer.valueOf(0));
                    contentValues2.put("plugin_latest_version", (String) arrayList3.get(i));
                    contentValues2.put("plugin_all_version", (String) arrayList3.get(i));
                    sQLiteDatabase.insert("plugin_table_name", null, contentValues2);
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.database.sqlite.SQLiteDatabase r1, int r2, int r3) {
        /*
        r0 = this;
        switch(r2) {
            case 1: goto L_0x0004;
            case 2: goto L_0x0007;
            case 3: goto L_0x000a;
            case 4: goto L_0x000d;
            case 5: goto L_0x0010;
            case 6: goto L_0x0013;
            case 7: goto L_0x0016;
            case 8: goto L_0x0019;
            case 9: goto L_0x001c;
            case 10: goto L_0x001f;
            case 11: goto L_0x0022;
            case 12: goto L_0x0003;
            case 13: goto L_0x0025;
            case 14: goto L_0x0028;
            default: goto L_0x0003;
        };
    L_0x0003:
        return;
    L_0x0004:
        r0.l(r1);
    L_0x0007:
        r0.k(r1);
    L_0x000a:
        r0.j(r1);
    L_0x000d:
        r0.i(r1);
    L_0x0010:
        r0.g(r1);
    L_0x0013:
        r0.c(r1);
    L_0x0016:
        r0.d(r1);
    L_0x0019:
        r0.m(r1);
    L_0x001c:
        r0.n(r1);
    L_0x001f:
        r0.o(r1);
    L_0x0022:
        r0.e(r1);
    L_0x0025:
        r0.f(r1);
    L_0x0028:
        r0.p(r1);
        goto L_0x0003;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.plugin.p.a(android.database.sqlite.SQLiteDatabase, int, int):void");
    }

    private void p(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("alter table plugin_table_name add plugin_purchase_state integer default 0");
        } catch (Exception e) {
        }
    }

    public void e(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("update plugin_table_name set plugin_latest_version = 'android_plugin_pdf_4.0' where plugin_id= '1'");
            sQLiteDatabase.execSQL("update plugin_table_name set plugin_all_version = 'android_plugin_pdf_4.0' where plugin_id= '1'");
        } catch (Exception e) {
        }
    }

    public void f(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("update plugin_table_name set plugin_latest_version = 'android_plugin_pdf_5.0' where plugin_id= '1'");
            sQLiteDatabase.execSQL("update plugin_table_name set plugin_all_version = 'android_plugin_pdf_5.0' where plugin_id= '1'");
        } catch (Exception e) {
        }
    }

    public void g(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select plugin_latest_version from plugin_table_name", null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.getCount() > 0) {
                        sQLiteDatabase.setVersion(6);
                        rawQuery.close();
                        if (cursor != null) {
                            cursor.close();
                            return;
                        }
                        return;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.b("DB", " update5To6 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("alter table plugin_table_name add plugin_latest_version text");
                        sQLiteDatabase.execSQL("alter table plugin_table_name add plugin_all_version text");
                        sQLiteDatabase.execSQL("update plugin_table_name set plugin_latest_version = plugin_version , plugin_all_version = plugin_version");
                        sQLiteDatabase.execSQL("update plugin_table_name set plugin_latest_version = 'android_plugin_pdf_2.0' where plugin_id= '1'");
                        sQLiteDatabase.execSQL("update plugin_table_name set plugin_all_version = 'android_plugin_pdf_2.0' where plugin_id= '1'");
                    } catch (Throwable th2) {
                        th = th2;
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        throw th;
                    }
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception e3) {
            e = e3;
            rawQuery = cursor;
            f.b("DB", " update5To6 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("alter table plugin_table_name add plugin_latest_version text");
            sQLiteDatabase.execSQL("alter table plugin_table_name add plugin_all_version text");
            sQLiteDatabase.execSQL("update plugin_table_name set plugin_latest_version = plugin_version , plugin_all_version = plugin_version");
            sQLiteDatabase.execSQL("update plugin_table_name set plugin_latest_version = 'android_plugin_pdf_2.0' where plugin_id= '1'");
            sQLiteDatabase.execSQL("update plugin_table_name set plugin_all_version = 'android_plugin_pdf_2.0' where plugin_id= '1'");
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        sQLiteDatabase.execSQL("alter table plugin_table_name add plugin_latest_version text");
        sQLiteDatabase.execSQL("alter table plugin_table_name add plugin_all_version text");
        sQLiteDatabase.execSQL("update plugin_table_name set plugin_latest_version = plugin_version , plugin_all_version = plugin_version");
        sQLiteDatabase.execSQL("update plugin_table_name set plugin_latest_version = 'android_plugin_pdf_2.0' where plugin_id= '1'");
        sQLiteDatabase.execSQL("update plugin_table_name set plugin_all_version = 'android_plugin_pdf_2.0' where plugin_id= '1'");
    }

    public void h(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("delete from plugin_table_name");
        } catch (Exception e) {
            f.a("SELECTION", "clearTable : " + e.getMessage());
        }
    }

    public void i(SQLiteDatabase sQLiteDatabase) {
    }

    public void j(SQLiteDatabase sQLiteDatabase) {
        Cursor query;
        Throwable th;
        try {
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            query = sQLiteDatabase2.query("plugin_table_name", new String[]{"plugin_type_id"}, "plugin_type_id= '6'", null, null, null, null);
            try {
                if (query.getCount() == 0) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("plugin_id", Constants.VIA_SHARE_TYPE_INFO);
                    contentValues.put("plugin_type_id", "");
                    contentValues.put("plugin_name", "Office格式支持");
                    contentValues.put("plugin_version", "");
                    contentValues.put("plugin_info", "");
                    contentValues.put("plugin_str_size", "");
                    contentValues.put("icon_url", "http://mag.reader.3g.qq.com/plugin/6_c.png");
                    contentValues.put("image_url", "");
                    contentValues.put("plugin_free", "");
                    contentValues.put("plugin_price", "");
                    contentValues.put("plugin_enable", "1");
                    contentValues.put("plugin_max_size", Long.valueOf(0));
                    contentValues.put("plugin_download_status", Integer.valueOf(1));
                    contentValues.put("plugin_can_download", Integer.valueOf(0));
                    sQLiteDatabase.insert("plugin_table_name", null, contentValues);
                    contentValues = new ContentValues();
                    contentValues.put("plugin_id", "25");
                    contentValues.put("plugin_type_id", Constants.VIA_SHARE_TYPE_INFO);
                    contentValues.put("plugin_name", "Office格式支持");
                    contentValues.put("plugin_version", "android_plugin_office_1.0");
                    contentValues.put("plugin_info", ReaderApplication.getApplicationImp().getString(R.string.app_name) + "与金山wps手机office合作推出此插件，安装后可以打开阅读 Word、Excel、PowerPoint文档，支持doc、docx、xls、xlsx、ppt、pptx格式，功能在不断优化中，如有体验问题请谅解。");
                    contentValues.put("plugin_str_size", "10M");
                    contentValues.put("icon_url", "http://mag.reader.3g.qq.com/plugin/25_6_c.png");
                    contentValues.put("image_url", "http://mag.reader.3g.qq.com/plugin/25_6_m.png");
                    contentValues.put("plugin_free", "0");
                    contentValues.put("plugin_price", "免费");
                    contentValues.put("plugin_enable", "1");
                    contentValues.put("plugin_max_size", Long.valueOf(0));
                    contentValues.put("plugin_download_status", Integer.valueOf(1));
                    contentValues.put("plugin_can_download", Integer.valueOf(0));
                    sQLiteDatabase.insert("plugin_table_name", null, contentValues);
                }
                if (query != null) {
                    query.close();
                }
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    public void k(SQLiteDatabase sQLiteDatabase) {
        Cursor query;
        Throwable th;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("plugin_id", "5");
            contentValues.put("plugin_type_id", "");
            contentValues.put("plugin_name", "Zip和Rar支持");
            contentValues.put("plugin_version", "");
            contentValues.put("plugin_info", "");
            contentValues.put("plugin_str_size", "");
            contentValues.put("icon_url", "http://mag.reader.3g.qq.com/plugin/5_c.png");
            contentValues.put("image_url", "");
            contentValues.put("plugin_free", "");
            contentValues.put("plugin_price", "");
            contentValues.put("plugin_enable", "1");
            contentValues.put("plugin_max_size", Long.valueOf(0));
            contentValues.put("plugin_download_status", Integer.valueOf(1));
            contentValues.put("plugin_can_download", Integer.valueOf(0));
            sQLiteDatabase.insert("plugin_table_name", null, contentValues);
            contentValues = new ContentValues();
            contentValues.put("plugin_id", Constants.VIA_REPORT_TYPE_MAKE_FRIEND);
            contentValues.put("plugin_type_id", "5");
            contentValues.put("plugin_name", "Zip和Rar支持");
            contentValues.put("plugin_version", "android_plugin_archive_1.0");
            contentValues.put("plugin_info", "下载Zip和Rar支持后，阅读器就可以搜索打开Zip和Rar文件。");
            contentValues.put("plugin_str_size", "");
            contentValues.put("icon_url", "http://mag.reader.3g.qq.com/plugin/14_5_c.png");
            contentValues.put("image_url", "http://mag.reader.3g.qq.com/plugin/14_5_m.png");
            contentValues.put("plugin_free", "0");
            contentValues.put("plugin_price", "免费");
            contentValues.put("plugin_enable", "1");
            contentValues.put("plugin_max_size", Long.valueOf(0));
            contentValues.put("plugin_download_status", Integer.valueOf(1));
            contentValues.put("plugin_can_download", Integer.valueOf(0));
            sQLiteDatabase.insert("plugin_table_name", null, contentValues);
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            query = sQLiteDatabase2.query("plugin_table_name", new String[]{"plugin_id", "plugin_name"}, "plugin_id= '15' and plugin_type_id!= ''", null, null, null, null);
            Object obj = null;
            if (query != null) {
                try {
                    if (query.getCount() > 0) {
                        obj = 1;
                        query.close();
                        query = null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            }
            if (obj == null) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                ArrayList arrayList4 = new ArrayList();
                ArrayList arrayList5 = new ArrayList();
                ArrayList arrayList6 = new ArrayList();
                ArrayList arrayList7 = new ArrayList();
                ArrayList arrayList8 = new ArrayList();
                ArrayList arrayList9 = new ArrayList();
                ArrayList arrayList10 = new ArrayList();
                int[] iArr = new int[10];
                iArr = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
                arrayList.add(Constants.VIA_REPORT_TYPE_WPA_STATE);
                arrayList.add(Constants.VIA_REPORT_TYPE_START_WAP);
                arrayList.add(Constants.VIA_REPORT_TYPE_START_GROUP);
                arrayList.add("18");
                arrayList.add(Constants.VIA_ACT_TYPE_NINETEEN);
                arrayList.add("20");
                arrayList.add(Constants.VIA_REPORT_TYPE_QQFAVORITES);
                arrayList.add(Constants.VIA_REPORT_TYPE_DATALINE);
                arrayList.add(Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR);
                arrayList.add("24");
                arrayList9.add("");
                arrayList9.add("");
                arrayList9.add("");
                arrayList9.add("");
                arrayList9.add("");
                arrayList9.add("");
                arrayList9.add("");
                arrayList9.add("");
                arrayList9.add("");
                arrayList9.add("");
                arrayList10.add("http://mag.reader.3g.qq.com/plugin/15_2_m.png");
                arrayList10.add("http://mag.reader.3g.qq.com/plugin/16_2_m.png");
                arrayList10.add("http://mag.reader.3g.qq.com/plugin/17_2_m.png");
                arrayList10.add("http://mag.reader.3g.qq.com/plugin/18_2_m.png");
                arrayList10.add("http://mag.reader.3g.qq.com/plugin/19_2_m.png");
                arrayList10.add("http://mag.reader.3g.qq.com/plugin/20_2_m.png");
                arrayList10.add("http://mag.reader.3g.qq.com/plugin/21_2_m.png");
                arrayList10.add("http://mag.reader.3g.qq.com/plugin/22_2_m.png");
                arrayList10.add("http://mag.reader.3g.qq.com/plugin/23_2_m.png");
                arrayList10.add("http://mag.reader.3g.qq.com/plugin/24_2_m.png");
                arrayList2.add("2");
                arrayList2.add("2");
                arrayList2.add("2");
                arrayList2.add("2");
                arrayList2.add("2");
                arrayList2.add("2");
                arrayList2.add("2");
                arrayList2.add("2");
                arrayList2.add("2");
                arrayList2.add("2");
                arrayList3.add("方正康体");
                arrayList3.add("方正隶书");
                arrayList3.add("方正喵呜体");
                arrayList3.add("方正少儿");
                arrayList3.add("方正铁筋隶书");
                arrayList3.add("方正魏碑");
                arrayList3.add("方正韵动中黑");
                arrayList3.add("方正稚艺");
                arrayList3.add("方正中倩");
                arrayList3.add("方正中雅宋");
                arrayList5.add("android_plugin_font_fzkangk_1.0");
                arrayList5.add("android_plugin_font_fzlsk_1.0");
                arrayList5.add("android_plugin_font_fzmwfont_1.0");
                arrayList5.add("android_plugin_font_fzsek_1.0");
                arrayList5.add("android_plugin_font_fztjlsk_1.0");
                arrayList5.add("android_plugin_font_fzwbk_1.0");
                arrayList5.add("android_plugin_font_fzydzhk_1.0");
                arrayList5.add("android_plugin_font_fzzhyk_1.0");
                arrayList5.add("android_plugin_font_fzzqk_1.0");
                arrayList5.add("android_plugin_font_fzzysk1_1.0");
                arrayList4.add("");
                arrayList4.add("");
                arrayList4.add("");
                arrayList4.add("");
                arrayList4.add("");
                arrayList4.add("");
                arrayList4.add("");
                arrayList4.add("");
                arrayList4.add("");
                arrayList4.add("");
                arrayList6.add("6.1M");
                arrayList6.add("13.5M");
                arrayList6.add("5.5M");
                arrayList6.add("5.1M");
                arrayList6.add("5.5M");
                arrayList6.add("16M");
                arrayList6.add("7.9M");
                arrayList6.add("4.4M");
                arrayList6.add("4.2M");
                arrayList6.add("11M");
                arrayList7.add("1");
                arrayList7.add("1");
                arrayList7.add("1");
                arrayList7.add("1");
                arrayList7.add("1");
                arrayList7.add("1");
                arrayList7.add("1");
                arrayList7.add("1");
                arrayList7.add("1");
                arrayList7.add("1");
                arrayList8.add("200书币");
                arrayList8.add("200书币");
                arrayList8.add("200书币");
                arrayList8.add("200书币");
                arrayList8.add("200书币");
                arrayList8.add("200书币");
                arrayList8.add("200书币");
                arrayList8.add("200书币");
                arrayList8.add("200书币");
                arrayList8.add("200书币");
                ContentValues contentValues2 = new ContentValues();
                for (int i = 0; i < arrayList.size(); i++) {
                    contentValues2.put("plugin_id", (String) arrayList.get(i));
                    contentValues2.put("plugin_type_id", (String) arrayList2.get(i));
                    contentValues2.put("plugin_name", (String) arrayList3.get(i));
                    contentValues2.put("plugin_version", (String) arrayList5.get(i));
                    contentValues2.put("plugin_info", (String) arrayList4.get(i));
                    contentValues2.put("plugin_str_size", (String) arrayList6.get(i));
                    contentValues2.put("icon_url", (String) arrayList9.get(i));
                    contentValues2.put("image_url", (String) arrayList10.get(i));
                    contentValues2.put("plugin_free", (String) arrayList7.get(i));
                    contentValues2.put("plugin_price", (String) arrayList8.get(i));
                    contentValues2.put("plugin_enable", "1");
                    contentValues2.put("plugin_max_size", Long.valueOf(0));
                    contentValues2.put("plugin_download_status", Integer.valueOf(1));
                    contentValues2.put("plugin_can_download", Integer.valueOf(iArr[i]));
                    sQLiteDatabase.insert("plugin_table_name", null, contentValues2);
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    public void l(SQLiteDatabase sQLiteDatabase) {
    }
}
