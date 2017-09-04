package com.qrcomic.entity;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class DownloadHistoryDao extends AbstractDao<i, Long> {
    public static final String TABLENAME = "DOWNLOAD_HISTORY";

    public static class Properties {
        public static final Property a = new Property(0, Long.class, "_id", true, "_id");
        public static final Property b = new Property(1, String.class, "uin", false, "UIN");
        public static final Property c = new Property(2, String.class, "historyComicId", false, "HISTORY_COMIC_ID");
        public static final Property d = new Property(3, String.class, "historySectionId", false, "HISTORY_SECTION_ID");
        public static final Property e = new Property(4, Integer.TYPE, "status", false, "STATUS");
        public static final Property f = new Property(5, String.class, "errorMsg", false, "ERROR_MSG");
        public static final Property g = new Property(6, Integer.TYPE, "errorCode", false, "ERROR_CODE");
        public static final Property h = new Property(7, Long.TYPE, "downloadSize", false, "DOWNLOAD_SIZE");
        public static final Property i = new Property(8, Integer.TYPE, "successNum", false, "SUCCESS_NUM");
        public static final Property j = new Property(9, Integer.TYPE, "percentage", false, "PERCENTAGE");
        public static final Property k = new Property(10, Long.TYPE, "historyUpdatetime", false, "HISTORY_UPDATETIME");
        public static final Property l = new Property(11, Long.TYPE, "size", false, "SIZE");
        public static final Property m = new Property(12, Integer.TYPE, "sectionIndex", false, "SECTION_INDEX");
        public static final Property n = new Property(13, String.class, "videoType", false, "VIDEO_TYPE");
    }

    protected /* synthetic */ void bindValues(SQLiteStatement sQLiteStatement, Object obj) {
        a(sQLiteStatement, (i) obj);
    }

    protected /* synthetic */ void bindValues(DatabaseStatement databaseStatement, Object obj) {
        a(databaseStatement, (i) obj);
    }

    public /* synthetic */ Object getKey(Object obj) {
        return a((i) obj);
    }

    public /* synthetic */ boolean hasKey(Object obj) {
        return b((i) obj);
    }

    public /* synthetic */ Object readEntity(Cursor cursor, int i) {
        return b(cursor, i);
    }

    public /* synthetic */ void readEntity(Cursor cursor, Object obj, int i) {
        a(cursor, (i) obj, i);
    }

    public /* synthetic */ Object readKey(Cursor cursor, int i) {
        return a(cursor, i);
    }

    protected /* synthetic */ Object updateKeyAfterInsert(Object obj, long j) {
        return a((i) obj, j);
    }

    public DownloadHistoryDao(DaoConfig daoConfig, h hVar) {
        super(daoConfig, hVar);
    }

    public static void a(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"DOWNLOAD_HISTORY\" (" + "\"_id\" INTEGER PRIMARY KEY ," + "\"UIN\" TEXT," + "\"HISTORY_COMIC_ID\" TEXT," + "\"HISTORY_SECTION_ID\" TEXT," + "\"STATUS\" INTEGER NOT NULL ," + "\"ERROR_MSG\" TEXT," + "\"ERROR_CODE\" INTEGER NOT NULL ," + "\"DOWNLOAD_SIZE\" INTEGER NOT NULL ," + "\"SUCCESS_NUM\" INTEGER NOT NULL ," + "\"PERCENTAGE\" INTEGER NOT NULL ," + "\"HISTORY_UPDATETIME\" INTEGER NOT NULL ," + "\"SIZE\" INTEGER NOT NULL ," + "\"SECTION_INDEX\" INTEGER NOT NULL ," + "\"VIDEO_TYPE\" TEXT);");
    }

    public static void b(Database database, boolean z) {
        database.execSQL("DROP TABLE " + (z ? "IF EXISTS " : "") + "\"DOWNLOAD_HISTORY\"");
    }

    protected final void a(DatabaseStatement databaseStatement, i iVar) {
        databaseStatement.clearBindings();
        Long n = iVar.n();
        if (n != null) {
            databaseStatement.bindLong(1, n.longValue());
        }
        String a = iVar.a();
        if (a != null) {
            databaseStatement.bindString(2, a);
        }
        a = iVar.b();
        if (a != null) {
            databaseStatement.bindString(3, a);
        }
        a = iVar.c();
        if (a != null) {
            databaseStatement.bindString(4, a);
        }
        databaseStatement.bindLong(5, (long) iVar.d());
        a = iVar.e();
        if (a != null) {
            databaseStatement.bindString(6, a);
        }
        databaseStatement.bindLong(7, (long) iVar.f());
        databaseStatement.bindLong(8, iVar.g());
        databaseStatement.bindLong(9, (long) iVar.h());
        databaseStatement.bindLong(10, (long) iVar.i());
        databaseStatement.bindLong(11, iVar.j());
        databaseStatement.bindLong(12, iVar.k());
        databaseStatement.bindLong(13, (long) iVar.l());
        a = iVar.m();
        if (a != null) {
            databaseStatement.bindString(14, a);
        }
    }

    protected final void a(SQLiteStatement sQLiteStatement, i iVar) {
        sQLiteStatement.clearBindings();
        Long n = iVar.n();
        if (n != null) {
            sQLiteStatement.bindLong(1, n.longValue());
        }
        String a = iVar.a();
        if (a != null) {
            sQLiteStatement.bindString(2, a);
        }
        a = iVar.b();
        if (a != null) {
            sQLiteStatement.bindString(3, a);
        }
        a = iVar.c();
        if (a != null) {
            sQLiteStatement.bindString(4, a);
        }
        sQLiteStatement.bindLong(5, (long) iVar.d());
        a = iVar.e();
        if (a != null) {
            sQLiteStatement.bindString(6, a);
        }
        sQLiteStatement.bindLong(7, (long) iVar.f());
        sQLiteStatement.bindLong(8, iVar.g());
        sQLiteStatement.bindLong(9, (long) iVar.h());
        sQLiteStatement.bindLong(10, (long) iVar.i());
        sQLiteStatement.bindLong(11, iVar.j());
        sQLiteStatement.bindLong(12, iVar.k());
        sQLiteStatement.bindLong(13, (long) iVar.l());
        a = iVar.m();
        if (a != null) {
            sQLiteStatement.bindString(14, a);
        }
    }

    public Long a(Cursor cursor, int i) {
        return cursor.isNull(i + 0) ? null : Long.valueOf(cursor.getLong(i + 0));
    }

    public i b(Cursor cursor, int i) {
        Long l;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        if (cursor.isNull(i + 0)) {
            l = null;
        } else {
            l = Long.valueOf(cursor.getLong(i + 0));
        }
        if (cursor.isNull(i + 1)) {
            str = null;
        } else {
            str = cursor.getString(i + 1);
        }
        if (cursor.isNull(i + 2)) {
            str2 = null;
        } else {
            str2 = cursor.getString(i + 2);
        }
        if (cursor.isNull(i + 3)) {
            str3 = null;
        } else {
            str3 = cursor.getString(i + 3);
        }
        int i2 = cursor.getInt(i + 4);
        if (cursor.isNull(i + 5)) {
            str4 = null;
        } else {
            str4 = cursor.getString(i + 5);
        }
        int i3 = cursor.getInt(i + 6);
        long j = cursor.getLong(i + 7);
        int i4 = cursor.getInt(i + 8);
        int i5 = cursor.getInt(i + 9);
        long j2 = cursor.getLong(i + 10);
        long j3 = cursor.getLong(i + 11);
        int i6 = cursor.getInt(i + 12);
        if (cursor.isNull(i + 13)) {
            str5 = null;
        } else {
            str5 = cursor.getString(i + 13);
        }
        return new i(l, str, str2, str3, i2, str4, i3, j, i4, i5, j2, j3, i6, str5);
    }

    public void a(Cursor cursor, i iVar, int i) {
        String str = null;
        iVar.a(cursor.isNull(i + 0) ? null : Long.valueOf(cursor.getLong(i + 0)));
        iVar.a(cursor.isNull(i + 1) ? null : cursor.getString(i + 1));
        iVar.b(cursor.isNull(i + 2) ? null : cursor.getString(i + 2));
        iVar.c(cursor.isNull(i + 3) ? null : cursor.getString(i + 3));
        iVar.a(cursor.getInt(i + 4));
        iVar.d(cursor.isNull(i + 5) ? null : cursor.getString(i + 5));
        iVar.b(cursor.getInt(i + 6));
        iVar.a(cursor.getLong(i + 7));
        iVar.c(cursor.getInt(i + 8));
        iVar.d(cursor.getInt(i + 9));
        iVar.b(cursor.getLong(i + 10));
        iVar.c(cursor.getLong(i + 11));
        iVar.e(cursor.getInt(i + 12));
        if (!cursor.isNull(i + 13)) {
            str = cursor.getString(i + 13);
        }
        iVar.e(str);
    }

    protected final Long a(i iVar, long j) {
        iVar.a(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long a(i iVar) {
        if (iVar != null) {
            return iVar.n();
        }
        return null;
    }

    public boolean b(i iVar) {
        return iVar.n() != null;
    }

    protected final boolean isEntityUpdateable() {
        return true;
    }
}
