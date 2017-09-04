package com.qrcomic.entity;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class ComicSectionDao extends AbstractDao<f, Long> {
    public static final String TABLENAME = "COMIC_SECTION";

    public static class Properties {
        public static final Property a = new Property(0, Long.class, "_id", true, "_id");
        public static final Property b = new Property(1, String.class, ComicStoreExclusiveItemCard.NET_AD_ATTR_COMICID, false, "COMIC_ID");
        public static final Property c = new Property(2, String.class, "sectionId", false, "SECTION_ID");
        public static final Property d = new Property(3, String.class, "name", false, "NAME");
        public static final Property e = new Property(4, Long.TYPE, "size", false, "SIZE");
        public static final Property f = new Property(5, Integer.TYPE, "pageCount", false, "PAGE_COUNT");
        public static final Property g = new Property(6, Integer.TYPE, "sectionIndex", false, "SECTION_INDEX");
        public static final Property h = new Property(7, String.class, "coverUrl", false, "COVER_URL");
        public static final Property i = new Property(8, Long.TYPE, "updateTime", false, "UPDATE_TIME");
        public static final Property j = new Property(9, byte[].class, "pageUrls", false, "PAGE_URLS");
        public static final Property k = new Property(10, Integer.TYPE, "shareStatus", false, "SHARE_STATUS");
        public static final Property l = new Property(11, String.class, "definition", false, "DEFINITION");
        public static final Property m = new Property(12, Integer.TYPE, "payType", false, "PAY_TYPE");
        public static final Property n = new Property(13, Integer.TYPE, "specialFree", false, "SPECIAL_FREE");
        public static final Property o = new Property(14, Long.TYPE, "specialFreeStart", false, "SPECIAL_FREE_START");
        public static final Property p = new Property(15, Long.TYPE, "specialFreeEnd", false, "SPECIAL_FREE_END");
    }

    protected /* synthetic */ void bindValues(SQLiteStatement sQLiteStatement, Object obj) {
        a(sQLiteStatement, (f) obj);
    }

    protected /* synthetic */ void bindValues(DatabaseStatement databaseStatement, Object obj) {
        a(databaseStatement, (f) obj);
    }

    public /* synthetic */ Object getKey(Object obj) {
        return a((f) obj);
    }

    public /* synthetic */ boolean hasKey(Object obj) {
        return b((f) obj);
    }

    public /* synthetic */ Object readEntity(Cursor cursor, int i) {
        return b(cursor, i);
    }

    public /* synthetic */ void readEntity(Cursor cursor, Object obj, int i) {
        a(cursor, (f) obj, i);
    }

    public /* synthetic */ Object readKey(Cursor cursor, int i) {
        return a(cursor, i);
    }

    protected /* synthetic */ Object updateKeyAfterInsert(Object obj, long j) {
        return a((f) obj, j);
    }

    public ComicSectionDao(DaoConfig daoConfig, h hVar) {
        super(daoConfig, hVar);
    }

    public static void a(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"COMIC_SECTION\" (" + "\"_id\" INTEGER PRIMARY KEY ," + "\"COMIC_ID\" TEXT," + "\"SECTION_ID\" TEXT," + "\"NAME\" TEXT," + "\"SIZE\" INTEGER NOT NULL ," + "\"PAGE_COUNT\" INTEGER NOT NULL ," + "\"SECTION_INDEX\" INTEGER NOT NULL ," + "\"COVER_URL\" TEXT," + "\"UPDATE_TIME\" INTEGER NOT NULL ," + "\"PAGE_URLS\" BLOB," + "\"SHARE_STATUS\" INTEGER NOT NULL ," + "\"DEFINITION\" TEXT," + "\"PAY_TYPE\" INTEGER NOT NULL ," + "\"SPECIAL_FREE\" INTEGER NOT NULL ," + "\"SPECIAL_FREE_START\" INTEGER NOT NULL ," + "\"SPECIAL_FREE_END\" INTEGER NOT NULL );");
    }

    public static void b(Database database, boolean z) {
        database.execSQL("DROP TABLE " + (z ? "IF EXISTS " : "") + "\"COMIC_SECTION\"");
    }

    protected final void a(DatabaseStatement databaseStatement, f fVar) {
        databaseStatement.clearBindings();
        Long n = fVar.n();
        if (n != null) {
            databaseStatement.bindLong(1, n.longValue());
        }
        String c = fVar.c();
        if (c != null) {
            databaseStatement.bindString(2, c);
        }
        c = fVar.d();
        if (c != null) {
            databaseStatement.bindString(3, c);
        }
        c = fVar.e();
        if (c != null) {
            databaseStatement.bindString(4, c);
        }
        databaseStatement.bindLong(5, fVar.f());
        databaseStatement.bindLong(6, (long) fVar.g());
        databaseStatement.bindLong(7, (long) fVar.h());
        c = fVar.i();
        if (c != null) {
            databaseStatement.bindString(8, c);
        }
        databaseStatement.bindLong(9, fVar.j());
        byte[] k = fVar.k();
        if (k != null) {
            databaseStatement.bindBlob(10, k);
        }
        databaseStatement.bindLong(11, (long) fVar.l());
        c = fVar.m();
        if (c != null) {
            databaseStatement.bindString(12, c);
        }
        databaseStatement.bindLong(13, (long) fVar.o());
        databaseStatement.bindLong(14, (long) fVar.r());
        databaseStatement.bindLong(15, fVar.q());
        databaseStatement.bindLong(16, fVar.p());
    }

    protected final void a(SQLiteStatement sQLiteStatement, f fVar) {
        sQLiteStatement.clearBindings();
        Long n = fVar.n();
        if (n != null) {
            sQLiteStatement.bindLong(1, n.longValue());
        }
        String c = fVar.c();
        if (c != null) {
            sQLiteStatement.bindString(2, c);
        }
        c = fVar.d();
        if (c != null) {
            sQLiteStatement.bindString(3, c);
        }
        c = fVar.e();
        if (c != null) {
            sQLiteStatement.bindString(4, c);
        }
        sQLiteStatement.bindLong(5, fVar.f());
        sQLiteStatement.bindLong(6, (long) fVar.g());
        sQLiteStatement.bindLong(7, (long) fVar.h());
        c = fVar.i();
        if (c != null) {
            sQLiteStatement.bindString(8, c);
        }
        sQLiteStatement.bindLong(9, fVar.j());
        byte[] k = fVar.k();
        if (k != null) {
            sQLiteStatement.bindBlob(10, k);
        }
        sQLiteStatement.bindLong(11, (long) fVar.l());
        c = fVar.m();
        if (c != null) {
            sQLiteStatement.bindString(12, c);
        }
        sQLiteStatement.bindLong(13, (long) fVar.o());
        sQLiteStatement.bindLong(14, (long) fVar.r());
        sQLiteStatement.bindLong(15, fVar.q());
        sQLiteStatement.bindLong(16, fVar.p());
    }

    public Long a(Cursor cursor, int i) {
        return cursor.isNull(i + 0) ? null : Long.valueOf(cursor.getLong(i + 0));
    }

    public f b(Cursor cursor, int i) {
        Long l;
        String str;
        String str2;
        String str3;
        String str4;
        byte[] bArr;
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
        long j = cursor.getLong(i + 4);
        int i2 = cursor.getInt(i + 5);
        int i3 = cursor.getInt(i + 6);
        if (cursor.isNull(i + 7)) {
            str4 = null;
        } else {
            str4 = cursor.getString(i + 7);
        }
        long j2 = cursor.getLong(i + 8);
        if (cursor.isNull(i + 9)) {
            bArr = null;
        } else {
            bArr = cursor.getBlob(i + 9);
        }
        int i4 = cursor.getInt(i + 10);
        if (cursor.isNull(i + 11)) {
            str5 = null;
        } else {
            str5 = cursor.getString(i + 11);
        }
        return new f(l, str, str2, str3, j, i2, i3, str4, j2, bArr, i4, str5, cursor.getInt(i + 12), cursor.getInt(i + 13), cursor.getLong(i + 14), cursor.getLong(i + 15));
    }

    public void a(Cursor cursor, f fVar, int i) {
        String str = null;
        fVar.a(cursor.isNull(i + 0) ? null : Long.valueOf(cursor.getLong(i + 0)));
        fVar.a(cursor.isNull(i + 1) ? null : cursor.getString(i + 1));
        fVar.b(cursor.isNull(i + 2) ? null : cursor.getString(i + 2));
        fVar.c(cursor.isNull(i + 3) ? null : cursor.getString(i + 3));
        fVar.a(cursor.getLong(i + 4));
        fVar.a(cursor.getInt(i + 5));
        fVar.b(cursor.getInt(i + 6));
        fVar.d(cursor.isNull(i + 7) ? null : cursor.getString(i + 7));
        fVar.b(cursor.getLong(i + 8));
        fVar.a(cursor.isNull(i + 9) ? null : cursor.getBlob(i + 9));
        fVar.c(cursor.getInt(i + 10));
        if (!cursor.isNull(i + 11)) {
            str = cursor.getString(i + 11);
        }
        fVar.e(str);
        fVar.d(cursor.getInt(i + 12));
        fVar.e(cursor.getInt(i + 13));
        fVar.d(cursor.getLong(i + 14));
        fVar.c(cursor.getLong(i + 15));
    }

    protected final Long a(f fVar, long j) {
        fVar.a(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long a(f fVar) {
        if (fVar != null) {
            return fVar.n();
        }
        return null;
    }

    public boolean b(f fVar) {
        return fVar.n() != null;
    }

    protected final boolean isEntityUpdateable() {
        return true;
    }
}
