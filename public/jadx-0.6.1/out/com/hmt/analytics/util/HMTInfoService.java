package com.hmt.analytics.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.hmt.analytics.common.CommonUtil;
import com.hmt.analytics.common.UrlBase64Coder;
import java.io.IOException;
import java.util.ArrayList;

public class HMTInfoService {
    private DataBaseOpenHelper a;
    private SQLiteDatabase b = this.a.getWritableDatabase();

    public HMTInfoService(Context context) {
        this.a = DataBaseOpenHelper.getInstance(context);
    }

    public void save(String str, String str2, String str3) {
        try {
            String compressDes = UrlBase64Coder.compressDes(str2);
            synchronized (this.a) {
                if (!this.b.isOpen()) {
                    this.b = this.a.getWritableDatabase();
                }
                this.b.beginTransaction();
                try {
                    this.b.execSQL("insert into " + str3 + "(type,info)values(?,?)", new Object[]{str, compressDes});
                    this.b.setTransactionSuccessful();
                    this.b.endTransaction();
                    this.b.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    this.b.endTransaction();
                    this.b.close();
                } catch (Throwable th) {
                    this.b.endTransaction();
                    this.b.close();
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void emptyTable(String str) {
        synchronized (this.a) {
            if (!this.b.isOpen()) {
                this.b = this.a.getWritableDatabase();
            }
            this.b.beginTransaction();
            try {
                this.b.execSQL("UPDATE sqlite_sequence SET seq = 0 WHERE name='" + str + "'");
                this.b.setTransactionSuccessful();
                this.b.endTransaction();
                this.b.close();
            } catch (Exception e) {
                e.printStackTrace();
                this.b.endTransaction();
                this.b.close();
            } catch (Throwable th) {
                this.b.endTransaction();
                this.b.close();
            }
        }
    }

    public ArrayList<HMTInfo> getScrollData(String str, int i) {
        ArrayList<HMTInfo> arrayList = new ArrayList();
        synchronized (this.a) {
            if (!this.b.isOpen()) {
                this.b = this.a.getWritableDatabase();
            }
            Cursor rawQuery = this.b.rawQuery("select * from " + str + " order by id asc limit ?", new String[]{String.valueOf(i)});
            while (rawQuery.moveToNext()) {
                try {
                    try {
                        arrayList.add(new HMTInfo(Integer.valueOf(rawQuery.getInt(0)), rawQuery.getString(1), UrlBase64Coder.uncompressDes(rawQuery.getString(2))));
                    } catch (IOException e) {
                        CommonUtil.printLog("hmt", e.toString());
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    rawQuery.close();
                } catch (Error e3) {
                    e3.printStackTrace();
                    rawQuery.close();
                } catch (Throwable th) {
                    rawQuery.close();
                }
            }
            rawQuery.close();
            this.b.close();
        }
        return arrayList;
    }

    public void deleteData(String str, int i) {
        synchronized (this.a) {
            if (!this.b.isOpen()) {
                this.b = this.a.getWritableDatabase();
            }
            this.b.beginTransaction();
            try {
                this.b.execSQL("delete from " + str + " where id<=" + i);
                this.b.setTransactionSuccessful();
                this.b.endTransaction();
                this.b.close();
            } catch (Exception e) {
                e.printStackTrace();
                this.b.endTransaction();
                this.b.close();
            } catch (Throwable th) {
                this.b.endTransaction();
                this.b.close();
            }
        }
    }
}
