package com.xiaomi.push.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.channel.commonutils.c.l;
import com.xiaomi.xmpush.thrift.i;
import com.xiaomi.xmpush.thrift.k;
import com.xiaomi.xmpush.thrift.m;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ar {
    private static volatile ar b;
    private static String c = "GeoFenceDao.";
    private Context a;

    private ar(Context context) {
        this.a = context;
    }

    private synchronized Cursor a(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor = null;
        synchronized (this) {
            l.a(false);
            try {
                cursor = sQLiteDatabase.rawQuery("SELECT * FROM geofence", null);
            } catch (Exception e) {
            }
        }
        return cursor;
    }

    public static ar a(Context context) {
        if (b == null) {
            synchronized (ar.class) {
                if (b == null) {
                    b = new ar(context);
                }
            }
        }
        return b;
    }

    private synchronized com.xiaomi.xmpush.thrift.l a(Cursor cursor) {
        com.xiaomi.xmpush.thrift.l lVar;
        try {
            for (com.xiaomi.xmpush.thrift.l lVar2 : com.xiaomi.xmpush.thrift.l.values()) {
                if (TextUtils.equals(cursor.getString(cursor.getColumnIndex("type")), lVar2.name())) {
                    break;
                }
            }
            lVar2 = null;
        } catch (Exception e) {
            c.d(e.toString());
            lVar2 = null;
        }
        return lVar2;
    }

    private synchronized String a(List<m> list) {
        String jSONArray;
        if (list != null) {
            if (list.size() >= 3) {
                JSONArray jSONArray2 = new JSONArray();
                try {
                    for (m mVar : list) {
                        if (mVar != null) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("point_lantitude", mVar.c());
                            jSONObject.put("point_longtitude", mVar.a());
                            jSONArray2.put(jSONObject);
                        }
                    }
                    jSONArray = jSONArray2.toString();
                } catch (JSONException e) {
                    c.d(e.toString());
                    jSONArray = null;
                }
            }
        }
        c.a(c + " points unvalidated");
        jSONArray = null;
        return jSONArray;
    }

    private synchronized m b(Cursor cursor) {
        m mVar;
        mVar = new m();
        try {
            mVar.b(Double.parseDouble(cursor.getString(cursor.getColumnIndex("center_lantitude"))));
            mVar.a(Double.parseDouble(cursor.getString(cursor.getColumnIndex("center_longtitude"))));
        } catch (Exception e) {
            c.d(e.toString());
            mVar = null;
        }
        return mVar;
    }

    private synchronized ArrayList<m> c(Cursor cursor) {
        ArrayList<m> arrayList;
        ArrayList<m> arrayList2 = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(cursor.getString(cursor.getColumnIndex("polygon_points")));
            for (int i = 0; i < jSONArray.length(); i++) {
                m mVar = new m();
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                mVar.b(jSONObject.getDouble("point_lantitude"));
                mVar.a(jSONObject.getDouble("point_longtitude"));
                arrayList2.add(mVar);
            }
            arrayList = arrayList2;
        } catch (JSONException e) {
            c.d(e.toString());
            arrayList = null;
        }
        return arrayList;
    }

    private synchronized i d(Cursor cursor) {
        i valueOf;
        try {
            valueOf = i.valueOf(cursor.getString(cursor.getColumnIndex("coordinate_provider")));
        } catch (IllegalArgumentException e) {
            c.d(e.toString());
            valueOf = null;
        }
        return valueOf;
    }

    public synchronized int a(String str, String str2) {
        int i = 0;
        synchronized (this) {
            l.a(false);
            try {
                if ("Enter".equals(str2) || "Leave".equals(str2) || "Unknown".equals(str2)) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("current_status", str2);
                    int update = as.a(this.a).a().update("geofence", contentValues, "id=?", new String[]{str});
                    as.a(this.a).b();
                    i = update;
                }
            } catch (Exception e) {
                c.d(e.toString());
            }
        }
        return i;
    }

    public synchronized long a(k kVar) {
        long insert;
        l.a(false);
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", kVar.a());
            contentValues.put("appId", Long.valueOf(kVar.e()));
            contentValues.put("name", kVar.c());
            contentValues.put("package_name", kVar.g());
            contentValues.put("create_time", Long.valueOf(kVar.i()));
            contentValues.put("type", kVar.k().name());
            contentValues.put("center_longtitude", String.valueOf(kVar.m().a()));
            contentValues.put("center_lantitude", String.valueOf(kVar.m().c()));
            contentValues.put("circle_radius", Double.valueOf(kVar.o()));
            contentValues.put("polygon_point", a(kVar.q()));
            contentValues.put("coordinate_provider", kVar.s().name());
            contentValues.put("current_status", "Unknown");
            insert = as.a(this.a).a().insert("geofence", null, contentValues);
            as.a(this.a).b();
        } catch (Exception e) {
            c.d(e.toString());
            insert = -1;
        }
        return insert;
    }

    public synchronized k a(String str) {
        k kVar;
        l.a(false);
        try {
            Iterator it = a().iterator();
            while (it.hasNext()) {
                kVar = (k) it.next();
                if (TextUtils.equals(kVar.a(), str)) {
                    break;
                }
            }
            kVar = null;
        } catch (Exception e) {
            c.d(e.toString());
            kVar = null;
        }
        return kVar;
    }

    public synchronized ArrayList<k> a() {
        ArrayList<k> arrayList;
        l.a(false);
        try {
            Cursor a = a(as.a(this.a).a());
            arrayList = new ArrayList();
            if (a != null) {
                while (a.moveToNext()) {
                    try {
                        k kVar = new k();
                        kVar.a(a.getString(a.getColumnIndex("id")));
                        kVar.b(a.getString(a.getColumnIndex("name")));
                        kVar.a((long) a.getInt(a.getColumnIndex("appId")));
                        kVar.c(a.getString(a.getColumnIndex("package_name")));
                        kVar.b((long) a.getInt(a.getColumnIndex("create_time")));
                        com.xiaomi.xmpush.thrift.l a2 = a(a);
                        if (a2 == null) {
                            c.c(c + "findAllGeoFencing: geo type null");
                        } else {
                            kVar.a(a2);
                            if (TextUtils.equals("Circle", a2.name())) {
                                kVar.a(b(a));
                                kVar.a(a.getDouble(a.getColumnIndex("circle_radius")));
                            } else if (TextUtils.equals("Polygon", a2.name())) {
                                List c = c(a);
                                if (c == null || c.size() < 3) {
                                    c.c(c + "findAllGeoFencing: geo points null or size<3");
                                } else {
                                    kVar.a(c);
                                }
                            }
                            i d = d(a);
                            if (d == null) {
                                c.c(c + "findAllGeoFencing: geo Coordinate Provider null ");
                            } else {
                                kVar.a(d);
                                arrayList.add(kVar);
                            }
                        }
                    } catch (Exception e) {
                        c.d(e.toString());
                    }
                }
                a.close();
            }
            as.a(this.a).b();
        } catch (Exception e2) {
            c.d(e2.toString());
            arrayList = null;
        }
        return arrayList;
    }

    public synchronized ArrayList<k> b(String str) {
        ArrayList<k> arrayList;
        l.a(false);
        try {
            ArrayList a = a();
            ArrayList<k> arrayList2 = new ArrayList();
            Iterator it = a.iterator();
            while (it.hasNext()) {
                k kVar = (k) it.next();
                if (TextUtils.equals(kVar.g(), str)) {
                    arrayList2.add(kVar);
                }
            }
            arrayList = arrayList2;
        } catch (Exception e) {
            c.d(e.toString());
            arrayList = null;
        }
        return arrayList;
    }

    public synchronized String c(String str) {
        String string;
        l.a(false);
        try {
            Cursor a = a(as.a(this.a).a());
            if (a != null) {
                while (a.moveToNext()) {
                    if (TextUtils.equals(a.getString(a.getColumnIndex("id")), str)) {
                        string = a.getString(a.getColumnIndex("current_status"));
                        c.c(c + "findGeoStatueByGeoId: geo current statue is " + string + " geoId:" + str);
                        a.close();
                        break;
                    }
                }
                a.close();
            }
            as.a(this.a).b();
            string = "Unknown";
        } catch (Exception e) {
            c.d(e.toString());
            string = "Unknown";
        }
        return string;
    }

    public synchronized int d(String str) {
        int delete;
        l.a(false);
        try {
            if (a(str) != null) {
                delete = as.a(this.a).a().delete("geofence", "id = ?", new String[]{str});
                as.a(this.a).b();
            } else {
                delete = 0;
            }
        } catch (Exception e) {
            c.d(e.toString());
            delete = 0;
        }
        return delete;
    }

    public synchronized int e(String str) {
        int i;
        l.a(false);
        try {
            if (TextUtils.isEmpty(str)) {
                i = 0;
            } else {
                i = as.a(this.a).a().delete("geofence", "package_name = ?", new String[]{str});
                as.a(this.a).b();
            }
        } catch (Exception e) {
            c.d(e.toString());
            i = 0;
        }
        return i;
    }
}
