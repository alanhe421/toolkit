package com.qrcomic.entity;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class ComicDao extends AbstractDao<a, Long> {
    public static final String TABLENAME = "COMIC";

    public static class Properties {
        public static final Property A = new Property(26, Long.TYPE, "specialFreeEnd", false, "SPECIAL_FREE_END");
        public static final Property B = new Property(27, Long.TYPE, "commentCount", false, "COMMENT_COUNT");
        public static final Property a = new Property(0, Long.class, "_id", true, "_id");
        public static final Property b = new Property(1, String.class, ComicStoreExclusiveItemCard.NET_AD_ATTR_COMICID, false, "COMIC_ID");
        public static final Property c = new Property(2, String.class, "comicName", false, "COMIC_NAME");
        public static final Property d = new Property(3, Integer.TYPE, "chapterCount", false, "CHAPTER_COUNT");
        public static final Property e = new Property(4, Long.TYPE, "comicSize", false, "COMIC_SIZE");
        public static final Property f = new Property(5, Integer.TYPE, "comicStatus", false, "COMIC_STATUS");
        public static final Property g = new Property(6, Integer.TYPE, "payType", false, "PAY_TYPE");
        public static final Property h = new Property(7, Integer.TYPE, "buyType", false, "BUY_TYPE");
        public static final Property i = new Property(8, byte[].class, "chapterInfo", false, "CHAPTER_INFO");
        public static final Property j = new Property(9, Integer.TYPE, "sectionCount", false, "SECTION_COUNT");
        public static final Property k = new Property(10, Boolean.TYPE, "isChapterSplit", false, "IS_CHAPTER_SPLIT");
        public static final Property l = new Property(11, Long.TYPE, "updateTime", false, "UPDATE_TIME");
        public static final Property m = new Property(12, String.class, "comicCoverUrl", false, "COMIC_COVER_URL");
        public static final Property n = new Property(13, Integer.TYPE, "type", false, "TYPE");
        public static final Property o = new Property(14, String.class, "author", false, "AUTHOR");
        public static final Property p = new Property(15, Integer.TYPE, "discount", false, "DISCOUNT");
        public static final Property q = new Property(16, Long.TYPE, "discountStart", false, "DISCOUNT_START");
        public static final Property r = new Property(17, Long.TYPE, "discountEnd", false, "DISCOUNT_END");
        public static final Property s = new Property(18, Integer.TYPE, "isMonthly", false, "IS_MONTHLY");
        public static final Property t = new Property(19, Long.TYPE, "monthlyStart", false, "MONTHLY_START");
        public static final Property u = new Property(20, Long.TYPE, "monthlyEnd", false, "MONTHLY_END");
        public static final Property v = new Property(21, String.class, "errorMsg", false, "ERROR_MSG");
        public static final Property w = new Property(22, Integer.TYPE, "errorCode", false, "ERROR_CODE");
        public static final Property x = new Property(23, Integer.TYPE, "checkLevel", false, "CHECK_LEVEL");
        public static final Property y = new Property(24, Integer.TYPE, "specialFree", false, "SPECIAL_FREE");
        public static final Property z = new Property(25, Long.TYPE, "specialFreeStart", false, "SPECIAL_FREE_START");
    }

    protected /* synthetic */ void bindValues(SQLiteStatement sQLiteStatement, Object obj) {
        a(sQLiteStatement, (a) obj);
    }

    protected /* synthetic */ void bindValues(DatabaseStatement databaseStatement, Object obj) {
        a(databaseStatement, (a) obj);
    }

    public /* synthetic */ Object getKey(Object obj) {
        return a((a) obj);
    }

    public /* synthetic */ boolean hasKey(Object obj) {
        return b((a) obj);
    }

    public /* synthetic */ Object readEntity(Cursor cursor, int i) {
        return b(cursor, i);
    }

    public /* synthetic */ void readEntity(Cursor cursor, Object obj, int i) {
        a(cursor, (a) obj, i);
    }

    public /* synthetic */ Object readKey(Cursor cursor, int i) {
        return a(cursor, i);
    }

    protected /* synthetic */ Object updateKeyAfterInsert(Object obj, long j) {
        return a((a) obj, j);
    }

    public ComicDao(DaoConfig daoConfig, h hVar) {
        super(daoConfig, hVar);
    }

    public static void a(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"COMIC\" (" + "\"_id\" INTEGER PRIMARY KEY ," + "\"COMIC_ID\" TEXT," + "\"COMIC_NAME\" TEXT," + "\"CHAPTER_COUNT\" INTEGER NOT NULL ," + "\"COMIC_SIZE\" INTEGER NOT NULL ," + "\"COMIC_STATUS\" INTEGER NOT NULL ," + "\"PAY_TYPE\" INTEGER NOT NULL ," + "\"BUY_TYPE\" INTEGER NOT NULL ," + "\"CHAPTER_INFO\" BLOB," + "\"SECTION_COUNT\" INTEGER NOT NULL ," + "\"IS_CHAPTER_SPLIT\" INTEGER NOT NULL ," + "\"UPDATE_TIME\" INTEGER NOT NULL ," + "\"COMIC_COVER_URL\" TEXT," + "\"TYPE\" INTEGER NOT NULL ," + "\"AUTHOR\" TEXT," + "\"DISCOUNT\" INTEGER NOT NULL ," + "\"DISCOUNT_START\" INTEGER NOT NULL ," + "\"DISCOUNT_END\" INTEGER NOT NULL ," + "\"IS_MONTHLY\" INTEGER NOT NULL ," + "\"MONTHLY_START\" INTEGER NOT NULL ," + "\"MONTHLY_END\" INTEGER NOT NULL ," + "\"ERROR_MSG\" TEXT," + "\"ERROR_CODE\" INTEGER NOT NULL ," + "\"CHECK_LEVEL\" INTEGER NOT NULL ," + "\"SPECIAL_FREE\" INTEGER NOT NULL ," + "\"SPECIAL_FREE_START\" INTEGER NOT NULL ," + "\"SPECIAL_FREE_END\" INTEGER NOT NULL ," + "\"COMMENT_COUNT\" INTEGER NOT NULL );");
    }

    public static void b(Database database, boolean z) {
        database.execSQL("DROP TABLE " + (z ? "IF EXISTS " : "") + "\"COMIC\"");
    }

    protected final void a(DatabaseStatement databaseStatement, a aVar) {
        databaseStatement.clearBindings();
        Long p = aVar.p();
        if (p != null) {
            databaseStatement.bindLong(1, p.longValue());
        }
        String e = aVar.e();
        if (e != null) {
            databaseStatement.bindString(2, e);
        }
        e = aVar.f();
        if (e != null) {
            databaseStatement.bindString(3, e);
        }
        databaseStatement.bindLong(4, (long) aVar.g());
        databaseStatement.bindLong(5, aVar.h());
        databaseStatement.bindLong(6, (long) aVar.i());
        databaseStatement.bindLong(7, (long) aVar.q());
        databaseStatement.bindLong(8, (long) aVar.r());
        byte[] j = aVar.j();
        if (j != null) {
            databaseStatement.bindBlob(9, j);
        }
        databaseStatement.bindLong(10, (long) aVar.k());
        databaseStatement.bindLong(11, aVar.l() ? 1 : 0);
        databaseStatement.bindLong(12, aVar.m());
        e = aVar.n();
        if (e != null) {
            databaseStatement.bindString(13, e);
        }
        databaseStatement.bindLong(14, (long) aVar.o());
        e = aVar.s();
        if (e != null) {
            databaseStatement.bindString(15, e);
        }
        databaseStatement.bindLong(16, (long) aVar.v());
        databaseStatement.bindLong(17, aVar.u());
        databaseStatement.bindLong(18, aVar.t());
        databaseStatement.bindLong(19, (long) aVar.A());
        databaseStatement.bindLong(20, aVar.z());
        databaseStatement.bindLong(21, aVar.y());
        e = aVar.x();
        if (e != null) {
            databaseStatement.bindString(22, e);
        }
        databaseStatement.bindLong(23, (long) aVar.w());
        databaseStatement.bindLong(24, (long) aVar.B());
        databaseStatement.bindLong(25, (long) aVar.C());
        databaseStatement.bindLong(26, aVar.E());
        databaseStatement.bindLong(27, aVar.D());
        databaseStatement.bindLong(28, aVar.F());
    }

    protected final void a(SQLiteStatement sQLiteStatement, a aVar) {
        sQLiteStatement.clearBindings();
        Long p = aVar.p();
        if (p != null) {
            sQLiteStatement.bindLong(1, p.longValue());
        }
        String e = aVar.e();
        if (e != null) {
            sQLiteStatement.bindString(2, e);
        }
        e = aVar.f();
        if (e != null) {
            sQLiteStatement.bindString(3, e);
        }
        sQLiteStatement.bindLong(4, (long) aVar.g());
        sQLiteStatement.bindLong(5, aVar.h());
        sQLiteStatement.bindLong(6, (long) aVar.i());
        sQLiteStatement.bindLong(7, (long) aVar.q());
        sQLiteStatement.bindLong(8, (long) aVar.r());
        byte[] j = aVar.j();
        if (j != null) {
            sQLiteStatement.bindBlob(9, j);
        }
        sQLiteStatement.bindLong(10, (long) aVar.k());
        sQLiteStatement.bindLong(11, aVar.l() ? 1 : 0);
        sQLiteStatement.bindLong(12, aVar.m());
        e = aVar.n();
        if (e != null) {
            sQLiteStatement.bindString(13, e);
        }
        sQLiteStatement.bindLong(14, (long) aVar.o());
        e = aVar.s();
        if (e != null) {
            sQLiteStatement.bindString(15, e);
        }
        sQLiteStatement.bindLong(16, (long) aVar.v());
        sQLiteStatement.bindLong(17, aVar.u());
        sQLiteStatement.bindLong(18, aVar.t());
        sQLiteStatement.bindLong(19, (long) aVar.A());
        sQLiteStatement.bindLong(20, aVar.z());
        sQLiteStatement.bindLong(21, aVar.y());
        e = aVar.x();
        if (e != null) {
            sQLiteStatement.bindString(22, e);
        }
        sQLiteStatement.bindLong(23, (long) aVar.w());
        sQLiteStatement.bindLong(24, (long) aVar.B());
        sQLiteStatement.bindLong(25, (long) aVar.C());
        sQLiteStatement.bindLong(26, aVar.E());
        sQLiteStatement.bindLong(27, aVar.D());
        sQLiteStatement.bindLong(28, aVar.F());
    }

    public Long a(Cursor cursor, int i) {
        return cursor.isNull(i + 0) ? null : Long.valueOf(cursor.getLong(i + 0));
    }

    public a b(Cursor cursor, int i) {
        Long l;
        String str;
        String str2;
        byte[] bArr;
        boolean z;
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
        int i2 = cursor.getInt(i + 3);
        long j = cursor.getLong(i + 4);
        int i3 = cursor.getInt(i + 5);
        int i4 = cursor.getInt(i + 6);
        int i5 = cursor.getInt(i + 7);
        if (cursor.isNull(i + 8)) {
            bArr = null;
        } else {
            bArr = cursor.getBlob(i + 8);
        }
        int i6 = cursor.getInt(i + 9);
        if (cursor.getShort(i + 10) != (short) 0) {
            z = true;
        } else {
            z = false;
        }
        long j2 = cursor.getLong(i + 11);
        if (cursor.isNull(i + 12)) {
            str3 = null;
        } else {
            str3 = cursor.getString(i + 12);
        }
        int i7 = cursor.getInt(i + 13);
        if (cursor.isNull(i + 14)) {
            str4 = null;
        } else {
            str4 = cursor.getString(i + 14);
        }
        int i8 = cursor.getInt(i + 15);
        long j3 = cursor.getLong(i + 16);
        long j4 = cursor.getLong(i + 17);
        int i9 = cursor.getInt(i + 18);
        long j5 = cursor.getLong(i + 19);
        long j6 = cursor.getLong(i + 20);
        if (cursor.isNull(i + 21)) {
            str5 = null;
        } else {
            str5 = cursor.getString(i + 21);
        }
        return new a(l, str, str2, i2, j, i3, i4, i5, bArr, i6, z, j2, str3, i7, str4, i8, j3, j4, i9, j5, j6, str5, cursor.getInt(i + 22), cursor.getInt(i + 23), cursor.getInt(i + 24), cursor.getLong(i + 25), cursor.getLong(i + 26), cursor.getLong(i + 27));
    }

    public void a(Cursor cursor, a aVar, int i) {
        String str = null;
        aVar.a(cursor.isNull(i + 0) ? null : Long.valueOf(cursor.getLong(i + 0)));
        aVar.a(cursor.isNull(i + 1) ? null : cursor.getString(i + 1));
        aVar.b(cursor.isNull(i + 2) ? null : cursor.getString(i + 2));
        aVar.a(cursor.getInt(i + 3));
        aVar.a(cursor.getLong(i + 4));
        aVar.b(cursor.getInt(i + 5));
        aVar.e(cursor.getInt(i + 6));
        aVar.f(cursor.getInt(i + 7));
        aVar.a(cursor.isNull(i + 8) ? null : cursor.getBlob(i + 8));
        aVar.c(cursor.getInt(i + 9));
        aVar.a(cursor.getShort(i + 10) != (short) 0);
        aVar.b(cursor.getLong(i + 11));
        aVar.c(cursor.isNull(i + 12) ? null : cursor.getString(i + 12));
        aVar.d(cursor.getInt(i + 13));
        aVar.d(cursor.isNull(i + 14) ? null : cursor.getString(i + 14));
        aVar.g(cursor.getInt(i + 15));
        aVar.d(cursor.getLong(i + 16));
        aVar.c(cursor.getLong(i + 17));
        aVar.i(cursor.getInt(i + 18));
        aVar.f(cursor.getLong(i + 19));
        aVar.e(cursor.getLong(i + 20));
        if (!cursor.isNull(i + 21)) {
            str = cursor.getString(i + 21);
        }
        aVar.e(str);
        aVar.h(cursor.getInt(i + 22));
        aVar.j(cursor.getInt(i + 23));
        aVar.k(cursor.getInt(i + 24));
        aVar.h(cursor.getLong(i + 25));
        aVar.g(cursor.getLong(i + 26));
        aVar.i(cursor.getLong(i + 27));
    }

    protected final Long a(a aVar, long j) {
        aVar.a(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long a(a aVar) {
        if (aVar != null) {
            return aVar.p();
        }
        return null;
    }

    public boolean b(a aVar) {
        return aVar.p() != null;
    }

    protected final boolean isEntityUpdateable() {
        return true;
    }
}
