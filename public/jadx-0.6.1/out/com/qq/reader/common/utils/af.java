package com.qq.reader.common.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Reminders;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.db.handle.p;
import com.qq.reader.common.monitor.debug.c;
import com.tencent.open.SocialConstants;
import java.util.TimeZone;

/* compiled from: ReminderUtils */
public class af {

    /* compiled from: ReminderUtils */
    public static class a {
        public String a;
        public String b;
        public String c;
        public String d;
        public long e;
        public long f;
    }

    public static boolean a(a aVar) {
        if (aVar == null) {
            return false;
        }
        try {
            String id = TimeZone.getDefault().getID();
            ContentResolver contentResolver = ReaderApplication.getApplicationImp().getContentResolver();
            Cursor query = contentResolver.query(Calendars.CONTENT_URI, new String[]{"_id", "calendar_displayName"}, null, null, null);
            if (query == null || query.getCount() == 0) {
                return false;
            }
            int[] iArr;
            if (query.moveToFirst()) {
                String[] strArr = new String[query.getCount()];
                iArr = new int[query.getCount()];
                for (int i = 0; i < strArr.length; i++) {
                    iArr[i] = query.getInt(0);
                    strArr[i] = query.getString(1);
                    query.moveToNext();
                }
            } else {
                iArr = null;
            }
            query.close();
            ContentValues contentValues = new ContentValues();
            contentValues.put("dtstart", Long.valueOf(aVar.e));
            contentValues.put("dtend", Long.valueOf(aVar.f));
            contentValues.put("title", aVar.c);
            contentValues.put(SocialConstants.PARAM_COMMENT, aVar.d);
            if (iArr != null && iArr.length > 0) {
                contentValues.put("calendar_id", Integer.valueOf(iArr[0]));
            }
            if (id != null) {
                contentValues.put("eventTimezone", id);
                c.e("Error", "timezone:" + id);
            }
            contentValues.put("customAppPackage", ReaderApplication.getApplicationImp().getPackageName());
            contentValues.put("customAppUri", "uniteqqreader://nativepage/discover/limittimediscountbuy");
            Object a = p.a().a(aVar.a, aVar.e);
            if (TextUtils.isEmpty(a)) {
                Uri insert = contentResolver.insert(Events.CONTENT_URI, contentValues);
                if (insert == null) {
                    return false;
                }
                long parseLong = Long.parseLong(insert.getLastPathSegment());
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("minutes", Integer.valueOf(3));
                contentValues2.put("event_id", Long.valueOf(parseLong));
                contentValues2.put("method", Integer.valueOf(1));
                if (contentResolver.insert(Reminders.CONTENT_URI, contentValues2) == null) {
                    return false;
                }
                if (!TextUtils.isEmpty(aVar.a)) {
                    return p.a().a(aVar.a, String.valueOf(parseLong), aVar.e);
                }
            }
            aVar.b = a;
            if (a(aVar, Events.CONTENT_URI, contentValues)) {
                return false;
            }
            return false;
        } catch (Exception e) {
            c.e("Error", e.getMessage());
        }
    }

    private static boolean a(a aVar, Uri uri, ContentValues contentValues) {
        if (ReaderApplication.getApplicationImp().getContentResolver().update(uri, contentValues, "_id=" + aVar.b, null) > 0) {
            return true;
        }
        return false;
    }

    public static boolean b(a aVar) {
        if (aVar == null || aVar.a == null || TextUtils.isEmpty(p.a().a(aVar.a, aVar.e))) {
            return false;
        }
        return true;
    }

    public static boolean c(a aVar) {
        if (aVar != null) {
            try {
                if (!TextUtils.isEmpty(aVar.a)) {
                    Object a = p.a().a(aVar.a, aVar.e);
                    if (!TextUtils.isEmpty(a)) {
                        long parseLong = Long.parseLong(a);
                        ContentResolver contentResolver = ReaderApplication.getApplicationImp().getContentResolver();
                        ContentValues contentValues = new ContentValues();
                        contentResolver.delete(Events.CONTENT_URI, "_id=" + parseLong, null);
                        contentResolver.delete(Reminders.CONTENT_URI, "event_id=" + parseLong, null);
                        return p.a().b(aVar.a, aVar.e);
                    }
                }
            } catch (Exception e) {
                c.e("Error", e.getMessage());
            }
        }
        return false;
    }
}
