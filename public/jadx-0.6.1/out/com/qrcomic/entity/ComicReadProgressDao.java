package com.qrcomic.entity;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class ComicReadProgressDao extends AbstractDao<e, Long> {
    public static final String TABLENAME = "COMIC_READ_PROGRESS";

    public static class Properties {
        public static final Property a = new Property(0, String.class, "sectionId", false, "SECTION_ID");
        public static final Property b = new Property(1, String.class, "uin", false, "UIN");
        public static final Property c = new Property(2, String.class, ComicStoreExclusiveItemCard.NET_AD_ATTR_COMICID, false, "COMIC_ID");
        public static final Property d = new Property(3, Long.class, "_id", true, "_id");
        public static final Property e = new Property(4, String.class, "comicName", false, "COMIC_NAME");
        public static final Property f = new Property(5, String.class, "sectionName", false, "SECTION_NAME");
        public static final Property g = new Property(6, String.class, "picId", false, "PIC_ID");
        public static final Property h = new Property(7, Integer.TYPE, "picSeq", false, "PIC_SEQ");
        public static final Property i = new Property(8, Integer.TYPE, "offsetY", false, "OFFSET_Y");
        public static final Property j = new Property(9, Integer.TYPE, "type", false, "TYPE");
        public static final Property k = new Property(10, Boolean.TYPE, "isSaveServer", false, "IS_SAVE_SERVER");
        public static final Property l = new Property(11, byte[].class, "sectionReadeds", false, "SECTION_READEDS");
        public static final Property m = new Property(12, Long.TYPE, "readTs", false, "READ_TS");
        public static final Property n = new Property(13, Integer.TYPE, "mSectionIndex", false, "M_SECTION_INDEX");
    }

    protected /* synthetic */ void bindValues(SQLiteStatement sQLiteStatement, Object obj) {
        a(sQLiteStatement, (e) obj);
    }

    protected /* synthetic */ void bindValues(DatabaseStatement databaseStatement, Object obj) {
        a(databaseStatement, (e) obj);
    }

    public /* synthetic */ Object getKey(Object obj) {
        return a((e) obj);
    }

    public /* synthetic */ boolean hasKey(Object obj) {
        return b((e) obj);
    }

    public /* synthetic */ Object readEntity(Cursor cursor, int i) {
        return b(cursor, i);
    }

    public /* synthetic */ void readEntity(Cursor cursor, Object obj, int i) {
        a(cursor, (e) obj, i);
    }

    public /* synthetic */ Object readKey(Cursor cursor, int i) {
        return a(cursor, i);
    }

    protected /* synthetic */ Object updateKeyAfterInsert(Object obj, long j) {
        return a((e) obj, j);
    }

    public ComicReadProgressDao(DaoConfig daoConfig, h hVar) {
        super(daoConfig, hVar);
    }

    public static void a(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"COMIC_READ_PROGRESS\" (" + "\"SECTION_ID\" TEXT," + "\"UIN\" TEXT," + "\"COMIC_ID\" TEXT," + "\"_id\" INTEGER PRIMARY KEY ," + "\"COMIC_NAME\" TEXT," + "\"SECTION_NAME\" TEXT," + "\"PIC_ID\" TEXT," + "\"PIC_SEQ\" INTEGER NOT NULL ," + "\"OFFSET_Y\" INTEGER NOT NULL ," + "\"TYPE\" INTEGER NOT NULL ," + "\"IS_SAVE_SERVER\" INTEGER NOT NULL ," + "\"SECTION_READEDS\" BLOB," + "\"READ_TS\" INTEGER NOT NULL ," + "\"M_SECTION_INDEX\" INTEGER NOT NULL );");
    }

    public static void b(Database database, boolean z) {
        database.execSQL("DROP TABLE " + (z ? "IF EXISTS " : "") + "\"COMIC_READ_PROGRESS\"");
    }

    protected final void a(DatabaseStatement databaseStatement, e eVar) {
        databaseStatement.clearBindings();
        String d = eVar.d();
        if (d != null) {
            databaseStatement.bindString(1, d);
        }
        d = eVar.a();
        if (d != null) {
            databaseStatement.bindString(2, d);
        }
        d = eVar.b();
        if (d != null) {
            databaseStatement.bindString(3, d);
        }
        Long n = eVar.n();
        if (n != null) {
            databaseStatement.bindLong(4, n.longValue());
        }
        d = eVar.c();
        if (d != null) {
            databaseStatement.bindString(5, d);
        }
        d = eVar.e();
        if (d != null) {
            databaseStatement.bindString(6, d);
        }
        d = eVar.f();
        if (d != null) {
            databaseStatement.bindString(7, d);
        }
        databaseStatement.bindLong(8, (long) eVar.g());
        databaseStatement.bindLong(9, (long) eVar.h());
        databaseStatement.bindLong(10, (long) eVar.i());
        databaseStatement.bindLong(11, eVar.j() ? 1 : 0);
        byte[] k = eVar.k();
        if (k != null) {
            databaseStatement.bindBlob(12, k);
        }
        databaseStatement.bindLong(13, eVar.l());
        databaseStatement.bindLong(14, (long) eVar.m());
    }

    protected final void a(SQLiteStatement sQLiteStatement, e eVar) {
        sQLiteStatement.clearBindings();
        String d = eVar.d();
        if (d != null) {
            sQLiteStatement.bindString(1, d);
        }
        d = eVar.a();
        if (d != null) {
            sQLiteStatement.bindString(2, d);
        }
        d = eVar.b();
        if (d != null) {
            sQLiteStatement.bindString(3, d);
        }
        Long n = eVar.n();
        if (n != null) {
            sQLiteStatement.bindLong(4, n.longValue());
        }
        d = eVar.c();
        if (d != null) {
            sQLiteStatement.bindString(5, d);
        }
        d = eVar.e();
        if (d != null) {
            sQLiteStatement.bindString(6, d);
        }
        d = eVar.f();
        if (d != null) {
            sQLiteStatement.bindString(7, d);
        }
        sQLiteStatement.bindLong(8, (long) eVar.g());
        sQLiteStatement.bindLong(9, (long) eVar.h());
        sQLiteStatement.bindLong(10, (long) eVar.i());
        sQLiteStatement.bindLong(11, eVar.j() ? 1 : 0);
        byte[] k = eVar.k();
        if (k != null) {
            sQLiteStatement.bindBlob(12, k);
        }
        sQLiteStatement.bindLong(13, eVar.l());
        sQLiteStatement.bindLong(14, (long) eVar.m());
    }

    public Long a(Cursor cursor, int i) {
        return cursor.isNull(i + 3) ? null : Long.valueOf(cursor.getLong(i + 3));
    }

    public e b(Cursor cursor, int i) {
        String str;
        String str2;
        String str3;
        Long l;
        String str4;
        String str5;
        String str6;
        boolean z;
        byte[] bArr;
        if (cursor.isNull(i + 0)) {
            str = null;
        } else {
            str = cursor.getString(i + 0);
        }
        if (cursor.isNull(i + 1)) {
            str2 = null;
        } else {
            str2 = cursor.getString(i + 1);
        }
        if (cursor.isNull(i + 2)) {
            str3 = null;
        } else {
            str3 = cursor.getString(i + 2);
        }
        if (cursor.isNull(i + 3)) {
            l = null;
        } else {
            l = Long.valueOf(cursor.getLong(i + 3));
        }
        if (cursor.isNull(i + 4)) {
            str4 = null;
        } else {
            str4 = cursor.getString(i + 4);
        }
        if (cursor.isNull(i + 5)) {
            str5 = null;
        } else {
            str5 = cursor.getString(i + 5);
        }
        if (cursor.isNull(i + 6)) {
            str6 = null;
        } else {
            str6 = cursor.getString(i + 6);
        }
        int i2 = cursor.getInt(i + 7);
        int i3 = cursor.getInt(i + 8);
        int i4 = cursor.getInt(i + 9);
        if (cursor.getShort(i + 10) != (short) 0) {
            z = true;
        } else {
            z = false;
        }
        if (cursor.isNull(i + 11)) {
            bArr = null;
        } else {
            bArr = cursor.getBlob(i + 11);
        }
        return new e(str, str2, str3, l, str4, str5, str6, i2, i3, i4, z, bArr, cursor.getLong(i + 12), cursor.getInt(i + 13));
    }

    public void a(Cursor cursor, e eVar, int i) {
        byte[] bArr = null;
        eVar.d(cursor.isNull(i + 0) ? null : cursor.getString(i + 0));
        eVar.a(cursor.isNull(i + 1) ? null : cursor.getString(i + 1));
        eVar.b(cursor.isNull(i + 2) ? null : cursor.getString(i + 2));
        eVar.a(cursor.isNull(i + 3) ? null : Long.valueOf(cursor.getLong(i + 3)));
        eVar.c(cursor.isNull(i + 4) ? null : cursor.getString(i + 4));
        eVar.e(cursor.isNull(i + 5) ? null : cursor.getString(i + 5));
        eVar.f(cursor.isNull(i + 6) ? null : cursor.getString(i + 6));
        eVar.a(cursor.getInt(i + 7));
        eVar.b(cursor.getInt(i + 8));
        eVar.c(cursor.getInt(i + 9));
        eVar.a(cursor.getShort(i + 10) != (short) 0);
        if (!cursor.isNull(i + 11)) {
            bArr = cursor.getBlob(i + 11);
        }
        eVar.a(bArr);
        eVar.a(cursor.getLong(i + 12));
        eVar.d(cursor.getInt(i + 13));
    }

    protected final Long a(e eVar, long j) {
        eVar.a(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long a(e eVar) {
        if (eVar != null) {
            return eVar.n();
        }
        return null;
    }

    public boolean b(e eVar) {
        return eVar.n() != null;
    }

    protected final boolean isEntityUpdateable() {
        return true;
    }
}
