package com.qq.reader.cservice.booknews;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderDownloadTask;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: BookNewsHandler */
public class b {
    Handler a;
    private Context b;
    private SQLiteOpenHelper c;
    private String d;

    public void a() {
        if (b().size() <= 0) {
            try {
                List arrayList = new ArrayList();
                JSONArray jSONArray = new JSONObject(this.d).getJSONArray("bookNews");
                for (int i = 0; i < jSONArray.length(); i++) {
                    a a = a(jSONArray.getJSONObject(i));
                    arrayList.add(a);
                    String b = a.b();
                    ReaderTask readerDownloadTask = new ReaderDownloadTask(this.b.getApplicationContext(), a.k(), b);
                    readerDownloadTask.setListener(new com.qq.reader.common.readertask.ordinal.b(this) {
                        final /* synthetic */ b a;

                        {
                            this.a = r1;
                        }

                        public void a(boolean z) {
                        }
                    });
                    g.a().a(readerDownloadTask);
                }
                a(arrayList);
                d.w(this.b, ((a) arrayList.get(0)).a());
                d.e(this.b, ((a) arrayList.get(0)).i());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private a a(JSONObject jSONObject) {
        a aVar = new a();
        aVar.a(jSONObject.optInt("id"));
        aVar.a(jSONObject.optString(SocialConstants.PARAM_IMG_URL));
        aVar.b(jSONObject.optString(SocialConstants.PARAM_URL));
        aVar.c(jSONObject.optString("title"));
        aVar.d(jSONObject.optString("intro"));
        aVar.e(jSONObject.optString("type"));
        aVar.f(jSONObject.optString("tType"));
        aVar.g(jSONObject.optString(ComicStoreExclusiveItemCard.NET_AD_ATTR_COUNT));
        aVar.a(jSONObject.optLong("pTime"));
        aVar.b(jSONObject.optLong("cTime"));
        aVar.h(jSONObject.optString("typeName"));
        aVar.i(jSONObject.optString("tTypeName"));
        return aVar;
    }

    private void a(SQLiteDatabase sQLiteDatabase, a aVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("newsId", Integer.valueOf(aVar.a()));
        contentValues.put("imgUrl", aVar.b());
        contentValues.put(SocialConstants.PARAM_URL, aVar.c());
        contentValues.put("title", aVar.d());
        contentValues.put("intro", aVar.e());
        contentValues.put("type", aVar.f());
        contentValues.put("tType", aVar.g());
        contentValues.put("pTime", Long.valueOf(aVar.i()));
        contentValues.put("cTime", Long.valueOf(aVar.j()));
        contentValues.put(ComicStoreExclusiveItemCard.NET_AD_ATTR_COUNT, aVar.h());
        contentValues.put("typeName", aVar.l());
        contentValues.put("tTypeName", aVar.m());
        sQLiteDatabase.insert("booknews", null, contentValues);
    }

    public synchronized void a(List<a> list) {
        try {
            SQLiteDatabase readableDatabase = this.c.getReadableDatabase();
            for (a a : list) {
                a(readableDatabase, a);
            }
            if (readableDatabase != null) {
                this.c.close();
            }
        } catch (Exception e) {
            f.a("addBookNews error", e.toString());
            if (null != null) {
                this.c.close();
            }
        } catch (Throwable th) {
            if (null != null) {
                this.c.close();
            }
        }
    }

    public synchronized List<a> b() {
        List<a> arrayList;
        Cursor query;
        Cursor cursor;
        Exception exception;
        Throwable th;
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this) {
            arrayList = new ArrayList();
            try {
                SQLiteDatabase readableDatabase = this.c.getReadableDatabase();
                try {
                    query = readableDatabase.query("booknews", null, null, null, null, null, "_id asc");
                } catch (Exception e) {
                    Exception exception2 = e;
                    cursor = null;
                    sQLiteDatabase = readableDatabase;
                    exception = exception2;
                    try {
                        f.a("get booknews", exception.toString());
                        if (sQLiteDatabase != null) {
                            this.c.close();
                        }
                        if (cursor != null) {
                            cursor.close();
                        }
                        return arrayList;
                    } catch (Throwable th2) {
                        th = th2;
                        query = cursor;
                        if (sQLiteDatabase != null) {
                            this.c.close();
                        }
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    query = null;
                    sQLiteDatabase = readableDatabase;
                    th = th3;
                    if (sQLiteDatabase != null) {
                        this.c.close();
                    }
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
                try {
                    if (query.moveToFirst()) {
                        do {
                            a aVar = new a();
                            aVar.b(query.getLong(query.getColumnIndex("cTime")));
                            aVar.a(query.getInt(query.getColumnIndex("newsId")));
                            aVar.a(query.getString(query.getColumnIndex("imgUrl")));
                            aVar.b(query.getString(query.getColumnIndex(SocialConstants.PARAM_URL)));
                            aVar.c(query.getString(query.getColumnIndex("title")));
                            aVar.d(query.getString(query.getColumnIndex("intro")));
                            aVar.e(query.getString(query.getColumnIndex("type")));
                            aVar.f(query.getString(query.getColumnIndex("tType")));
                            aVar.a(query.getLong(query.getColumnIndex("pTime")));
                            aVar.g(query.getString(query.getColumnIndex(ComicStoreExclusiveItemCard.NET_AD_ATTR_COUNT)));
                            aVar.h(query.getString(query.getColumnIndex("typeName")));
                            aVar.i(query.getString(query.getColumnIndex("tTypeName")));
                            arrayList.add(aVar);
                        } while (query.moveToNext());
                    }
                    if (readableDatabase != null) {
                        this.c.close();
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (Exception e2) {
                    sQLiteDatabase = readableDatabase;
                    exception = e2;
                    cursor = query;
                    f.a("get booknews", exception.toString());
                    if (sQLiteDatabase != null) {
                        this.c.close();
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    return arrayList;
                } catch (Throwable th32) {
                    sQLiteDatabase = readableDatabase;
                    th = th32;
                    if (sQLiteDatabase != null) {
                        this.c.close();
                    }
                    if (query != null) {
                        query.close();
                    }
                    throw th;
                }
            } catch (Exception e3) {
                exception = e3;
                cursor = null;
                f.a("get booknews", exception.toString());
                if (sQLiteDatabase != null) {
                    this.c.close();
                }
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            } catch (Throwable th4) {
                th = th4;
                query = null;
                if (sQLiteDatabase != null) {
                    this.c.close();
                }
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        }
        return arrayList;
    }
}
