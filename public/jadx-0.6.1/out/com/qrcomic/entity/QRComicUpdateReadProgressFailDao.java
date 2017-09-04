package com.qrcomic.entity;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class QRComicUpdateReadProgressFailDao extends AbstractDao<m, Long> {
    public static final String TABLENAME = "QRCOMIC_UPDATE_READ_PROGRESS_FAIL";

    public static class Properties {
        public static final Property a = new Property(0, Long.class, "_id", true, "_id");
        public static final Property b = new Property(1, String.class, "uin", false, "UIN");
        public static final Property c = new Property(2, String.class, ComicStoreExclusiveItemCard.NET_AD_ATTR_COMICID, false, "COMIC_ID");
        public static final Property d = new Property(3, String.class, "sectionId", false, "SECTION_ID");
        public static final Property e = new Property(4, String.class, "picId", false, "PIC_ID");
        public static final Property f = new Property(5, Integer.TYPE, "offsetY", false, "OFFSET_Y");
        public static final Property g = new Property(6, Long.TYPE, "readTs", false, "READ_TS");
        public static final Property h = new Property(7, String.class, "errorMsg", false, "ERROR_MSG");
        public static final Property i = new Property(8, Integer.TYPE, "type", false, "TYPE");
    }

    protected /* synthetic */ void bindValues(SQLiteStatement sQLiteStatement, Object obj) {
        a(sQLiteStatement, (m) obj);
    }

    protected /* synthetic */ void bindValues(DatabaseStatement databaseStatement, Object obj) {
        a(databaseStatement, (m) obj);
    }

    public /* synthetic */ Object getKey(Object obj) {
        return a((m) obj);
    }

    public /* synthetic */ boolean hasKey(Object obj) {
        return b((m) obj);
    }

    public /* synthetic */ Object readEntity(Cursor cursor, int i) {
        return b(cursor, i);
    }

    public /* synthetic */ void readEntity(Cursor cursor, Object obj, int i) {
        a(cursor, (m) obj, i);
    }

    public /* synthetic */ Object readKey(Cursor cursor, int i) {
        return a(cursor, i);
    }

    protected /* synthetic */ Object updateKeyAfterInsert(Object obj, long j) {
        return a((m) obj, j);
    }

    public QRComicUpdateReadProgressFailDao(DaoConfig daoConfig, h hVar) {
        super(daoConfig, hVar);
    }

    public static void a(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"QRCOMIC_UPDATE_READ_PROGRESS_FAIL\" (" + "\"_id\" INTEGER PRIMARY KEY ," + "\"UIN\" TEXT," + "\"COMIC_ID\" TEXT," + "\"SECTION_ID\" TEXT," + "\"PIC_ID\" TEXT," + "\"OFFSET_Y\" INTEGER NOT NULL ," + "\"READ_TS\" INTEGER NOT NULL ," + "\"ERROR_MSG\" TEXT," + "\"TYPE\" INTEGER NOT NULL );");
    }

    public static void b(Database database, boolean z) {
        database.execSQL("DROP TABLE " + (z ? "IF EXISTS " : "") + "\"QRCOMIC_UPDATE_READ_PROGRESS_FAIL\"");
    }

    protected final void a(DatabaseStatement databaseStatement, m mVar) {
        databaseStatement.clearBindings();
        Long i = mVar.i();
        if (i != null) {
            databaseStatement.bindLong(1, i.longValue());
        }
        String a = mVar.a();
        if (a != null) {
            databaseStatement.bindString(2, a);
        }
        a = mVar.b();
        if (a != null) {
            databaseStatement.bindString(3, a);
        }
        a = mVar.c();
        if (a != null) {
            databaseStatement.bindString(4, a);
        }
        a = mVar.d();
        if (a != null) {
            databaseStatement.bindString(5, a);
        }
        databaseStatement.bindLong(6, (long) mVar.e());
        databaseStatement.bindLong(7, mVar.f());
        a = mVar.g();
        if (a != null) {
            databaseStatement.bindString(8, a);
        }
        databaseStatement.bindLong(9, (long) mVar.h());
    }

    protected final void a(SQLiteStatement sQLiteStatement, m mVar) {
        sQLiteStatement.clearBindings();
        Long i = mVar.i();
        if (i != null) {
            sQLiteStatement.bindLong(1, i.longValue());
        }
        String a = mVar.a();
        if (a != null) {
            sQLiteStatement.bindString(2, a);
        }
        a = mVar.b();
        if (a != null) {
            sQLiteStatement.bindString(3, a);
        }
        a = mVar.c();
        if (a != null) {
            sQLiteStatement.bindString(4, a);
        }
        a = mVar.d();
        if (a != null) {
            sQLiteStatement.bindString(5, a);
        }
        sQLiteStatement.bindLong(6, (long) mVar.e());
        sQLiteStatement.bindLong(7, mVar.f());
        a = mVar.g();
        if (a != null) {
            sQLiteStatement.bindString(8, a);
        }
        sQLiteStatement.bindLong(9, (long) mVar.h());
    }

    public Long a(Cursor cursor, int i) {
        return cursor.isNull(i + 0) ? null : Long.valueOf(cursor.getLong(i + 0));
    }

    public m b(Cursor cursor, int i) {
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
        if (cursor.isNull(i + 4)) {
            str4 = null;
        } else {
            str4 = cursor.getString(i + 4);
        }
        int i2 = cursor.getInt(i + 5);
        long j = cursor.getLong(i + 6);
        if (cursor.isNull(i + 7)) {
            str5 = null;
        } else {
            str5 = cursor.getString(i + 7);
        }
        return new m(l, str, str2, str3, str4, i2, j, str5, cursor.getInt(i + 8));
    }

    public void a(Cursor cursor, m mVar, int i) {
        String str = null;
        mVar.a(cursor.isNull(i + 0) ? null : Long.valueOf(cursor.getLong(i + 0)));
        mVar.a(cursor.isNull(i + 1) ? null : cursor.getString(i + 1));
        mVar.b(cursor.isNull(i + 2) ? null : cursor.getString(i + 2));
        mVar.c(cursor.isNull(i + 3) ? null : cursor.getString(i + 3));
        mVar.d(cursor.isNull(i + 4) ? null : cursor.getString(i + 4));
        mVar.a(cursor.getInt(i + 5));
        mVar.a(cursor.getLong(i + 6));
        if (!cursor.isNull(i + 7)) {
            str = cursor.getString(i + 7);
        }
        mVar.e(str);
        mVar.b(cursor.getInt(i + 8));
    }

    protected final Long a(m mVar, long j) {
        mVar.a(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long a(m mVar) {
        if (mVar != null) {
            return mVar.i();
        }
        return null;
    }

    public boolean b(m mVar) {
        return mVar.i() != null;
    }

    protected final boolean isEntityUpdateable() {
        return true;
    }
}
