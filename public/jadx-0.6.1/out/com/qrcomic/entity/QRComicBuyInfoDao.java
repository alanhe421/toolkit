package com.qrcomic.entity;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class QRComicBuyInfoDao extends AbstractDao<l, Long> {
    public static final String TABLENAME = "QRCOMIC_BUY_INFO";

    public static class Properties {
        public static final Property a = new Property(0, Long.class, "_id", true, "_id");
        public static final Property b = new Property(1, String.class, "uid", false, "UID");
        public static final Property c = new Property(2, String.class, ComicStoreExclusiveItemCard.NET_AD_ATTR_COMICID, false, "COMIC_ID");
        public static final Property d = new Property(3, Integer.TYPE, "isAutoBuy", false, "IS_AUTO_BUY");
        public static final Property e = new Property(4, Integer.TYPE, "buyTheBook", false, "BUY_THE_BOOK");
        public static final Property f = new Property(5, String.class, "buyJson", false, "BUY_JSON");
    }

    protected /* synthetic */ void bindValues(SQLiteStatement sQLiteStatement, Object obj) {
        a(sQLiteStatement, (l) obj);
    }

    protected /* synthetic */ void bindValues(DatabaseStatement databaseStatement, Object obj) {
        a(databaseStatement, (l) obj);
    }

    public /* synthetic */ Object getKey(Object obj) {
        return a((l) obj);
    }

    public /* synthetic */ boolean hasKey(Object obj) {
        return b((l) obj);
    }

    public /* synthetic */ Object readEntity(Cursor cursor, int i) {
        return b(cursor, i);
    }

    public /* synthetic */ void readEntity(Cursor cursor, Object obj, int i) {
        a(cursor, (l) obj, i);
    }

    public /* synthetic */ Object readKey(Cursor cursor, int i) {
        return a(cursor, i);
    }

    protected /* synthetic */ Object updateKeyAfterInsert(Object obj, long j) {
        return a((l) obj, j);
    }

    public QRComicBuyInfoDao(DaoConfig daoConfig, h hVar) {
        super(daoConfig, hVar);
    }

    public static void a(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"QRCOMIC_BUY_INFO\" (" + "\"_id\" INTEGER PRIMARY KEY ," + "\"UID\" TEXT," + "\"COMIC_ID\" TEXT," + "\"IS_AUTO_BUY\" INTEGER NOT NULL ," + "\"BUY_THE_BOOK\" INTEGER NOT NULL ," + "\"BUY_JSON\" TEXT);");
        database.execSQL("CREATE UNIQUE INDEX " + str + "IDX_QRCOMIC_BUY_INFO__id ON QRCOMIC_BUY_INFO" + " (\"_id\" ASC);");
    }

    public static void b(Database database, boolean z) {
        database.execSQL("DROP TABLE " + (z ? "IF EXISTS " : "") + "\"QRCOMIC_BUY_INFO\"");
    }

    protected final void a(DatabaseStatement databaseStatement, l lVar) {
        databaseStatement.clearBindings();
        Long d = lVar.d();
        if (d != null) {
            databaseStatement.bindLong(1, d.longValue());
        }
        String f = lVar.f();
        if (f != null) {
            databaseStatement.bindString(2, f);
        }
        f = lVar.a();
        if (f != null) {
            databaseStatement.bindString(3, f);
        }
        databaseStatement.bindLong(4, (long) lVar.b());
        databaseStatement.bindLong(5, (long) lVar.g());
        f = lVar.e();
        if (f != null) {
            databaseStatement.bindString(6, f);
        }
    }

    protected final void a(SQLiteStatement sQLiteStatement, l lVar) {
        sQLiteStatement.clearBindings();
        Long d = lVar.d();
        if (d != null) {
            sQLiteStatement.bindLong(1, d.longValue());
        }
        String f = lVar.f();
        if (f != null) {
            sQLiteStatement.bindString(2, f);
        }
        f = lVar.a();
        if (f != null) {
            sQLiteStatement.bindString(3, f);
        }
        sQLiteStatement.bindLong(4, (long) lVar.b());
        sQLiteStatement.bindLong(5, (long) lVar.g());
        f = lVar.e();
        if (f != null) {
            sQLiteStatement.bindString(6, f);
        }
    }

    public Long a(Cursor cursor, int i) {
        return cursor.isNull(i + 0) ? null : Long.valueOf(cursor.getLong(i + 0));
    }

    public l b(Cursor cursor, int i) {
        String str = null;
        Long valueOf = cursor.isNull(i + 0) ? null : Long.valueOf(cursor.getLong(i + 0));
        String string = cursor.isNull(i + 1) ? null : cursor.getString(i + 1);
        String string2 = cursor.isNull(i + 2) ? null : cursor.getString(i + 2);
        int i2 = cursor.getInt(i + 3);
        int i3 = cursor.getInt(i + 4);
        if (!cursor.isNull(i + 5)) {
            str = cursor.getString(i + 5);
        }
        return new l(valueOf, string, string2, i2, i3, str);
    }

    public void a(Cursor cursor, l lVar, int i) {
        String str = null;
        lVar.a(cursor.isNull(i + 0) ? null : Long.valueOf(cursor.getLong(i + 0)));
        lVar.c(cursor.isNull(i + 1) ? null : cursor.getString(i + 1));
        lVar.a(cursor.isNull(i + 2) ? null : cursor.getString(i + 2));
        lVar.a(cursor.getInt(i + 3));
        lVar.b(cursor.getInt(i + 4));
        if (!cursor.isNull(i + 5)) {
            str = cursor.getString(i + 5);
        }
        lVar.b(str);
    }

    protected final Long a(l lVar, long j) {
        lVar.a(j);
        return Long.valueOf(j);
    }

    public Long a(l lVar) {
        if (lVar != null) {
            return lVar.d();
        }
        return null;
    }

    public boolean b(l lVar) {
        return lVar.d() != null;
    }

    protected final boolean isEntityUpdateable() {
        return true;
    }
}
