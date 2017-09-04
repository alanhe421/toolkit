package com.qrcomic.entity;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class ComicHistoryDao extends AbstractDao<c, Long> {
    public static final String TABLENAME = "COMIC_HISTORY";

    public static class Properties {
        public static final Property a = new Property(0, Long.class, "_id", true, "_id");
        public static final Property b = new Property(1, String.class, "_key", false, "_KEY");
        public static final Property c = new Property(2, String.class, "uin", false, "UIN");
        public static final Property d = new Property(3, String.class, ComicStoreExclusiveItemCard.NET_AD_ATTR_COMICID, false, "COMIC_ID");
        public static final Property e = new Property(4, String.class, "comicName", false, "COMIC_NAME");
        public static final Property f = new Property(5, String.class, "comicCoverUrl", false, "COMIC_COVER_URL");
        public static final Property g = new Property(6, String.class, "updateSectionId", false, "UPDATE_SECTION_ID");
        public static final Property h = new Property(7, Long.TYPE, "collectTs", false, "COLLECT_TS");
        public static final Property i = new Property(8, Long.TYPE, "maxTs", false, "MAX_TS");
        public static final Property j = new Property(9, Integer.TYPE, "status", false, "STATUS");
        public static final Property k = new Property(10, Boolean.TYPE, "isSaveServer", false, "IS_SAVE_SERVER");
        public static final Property l = new Property(11, Boolean.TYPE, "isTsUpdate", false, "IS_TS_UPDATE");
        public static final Property m = new Property(12, Integer.TYPE, "opt", false, "OPT");
        public static final Property n = new Property(13, Integer.TYPE, "attentionUpdateOpt", false, "ATTENTION_UPDATE_OPT");
        public static final Property o = new Property(14, Integer.TYPE, "iegComicStatus", false, "IEG_COMIC_STATUS");
        public static final Property p = new Property(15, Integer.TYPE, "type", false, "TYPE");
        public static final Property q = new Property(16, Long.TYPE, "updateTs", false, "UPDATE_TS");
        public static final Property r = new Property(17, String.class, "sectionName", false, "SECTION_NAME");
        public static final Property s = new Property(18, Integer.TYPE, "picSeq", false, "PIC_SEQ");
    }

    protected /* synthetic */ void bindValues(SQLiteStatement sQLiteStatement, Object obj) {
        a(sQLiteStatement, (c) obj);
    }

    protected /* synthetic */ void bindValues(DatabaseStatement databaseStatement, Object obj) {
        a(databaseStatement, (c) obj);
    }

    public /* synthetic */ Object getKey(Object obj) {
        return a((c) obj);
    }

    public /* synthetic */ boolean hasKey(Object obj) {
        return b((c) obj);
    }

    public /* synthetic */ Object readEntity(Cursor cursor, int i) {
        return b(cursor, i);
    }

    public /* synthetic */ void readEntity(Cursor cursor, Object obj, int i) {
        a(cursor, (c) obj, i);
    }

    public /* synthetic */ Object readKey(Cursor cursor, int i) {
        return a(cursor, i);
    }

    protected /* synthetic */ Object updateKeyAfterInsert(Object obj, long j) {
        return a((c) obj, j);
    }

    public ComicHistoryDao(DaoConfig daoConfig, h hVar) {
        super(daoConfig, hVar);
    }

    public static void a(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"COMIC_HISTORY\" (" + "\"_id\" INTEGER PRIMARY KEY ," + "\"_KEY\" TEXT," + "\"UIN\" TEXT," + "\"COMIC_ID\" TEXT," + "\"COMIC_NAME\" TEXT," + "\"COMIC_COVER_URL\" TEXT," + "\"UPDATE_SECTION_ID\" TEXT," + "\"COLLECT_TS\" INTEGER NOT NULL ," + "\"MAX_TS\" INTEGER NOT NULL ," + "\"STATUS\" INTEGER NOT NULL ," + "\"IS_SAVE_SERVER\" INTEGER NOT NULL ," + "\"IS_TS_UPDATE\" INTEGER NOT NULL ," + "\"OPT\" INTEGER NOT NULL ," + "\"ATTENTION_UPDATE_OPT\" INTEGER NOT NULL ," + "\"IEG_COMIC_STATUS\" INTEGER NOT NULL ," + "\"TYPE\" INTEGER NOT NULL ," + "\"UPDATE_TS\" INTEGER NOT NULL ," + "\"SECTION_NAME\" TEXT," + "\"PIC_SEQ\" INTEGER NOT NULL );");
        database.execSQL("CREATE UNIQUE INDEX " + str + "IDX_COMIC_HISTORY__KEY ON COMIC_HISTORY" + " (\"_KEY\" ASC);");
    }

    public static void b(Database database, boolean z) {
        database.execSQL("DROP TABLE " + (z ? "IF EXISTS " : "") + "\"COMIC_HISTORY\"");
    }

    protected final void a(DatabaseStatement databaseStatement, c cVar) {
        long j = 1;
        databaseStatement.clearBindings();
        Long r = cVar.r();
        if (r != null) {
            databaseStatement.bindLong(1, r.longValue());
        }
        String s = cVar.s();
        if (s != null) {
            databaseStatement.bindString(2, s);
        }
        s = cVar.a();
        if (s != null) {
            databaseStatement.bindString(3, s);
        }
        s = cVar.b();
        if (s != null) {
            databaseStatement.bindString(4, s);
        }
        s = cVar.c();
        if (s != null) {
            databaseStatement.bindString(5, s);
        }
        s = cVar.d();
        if (s != null) {
            databaseStatement.bindString(6, s);
        }
        s = cVar.e();
        if (s != null) {
            databaseStatement.bindString(7, s);
        }
        databaseStatement.bindLong(8, cVar.f());
        databaseStatement.bindLong(9, cVar.g());
        databaseStatement.bindLong(10, (long) cVar.h());
        databaseStatement.bindLong(11, cVar.i() ? 1 : 0);
        if (!cVar.j()) {
            j = 0;
        }
        databaseStatement.bindLong(12, j);
        databaseStatement.bindLong(13, (long) cVar.k());
        databaseStatement.bindLong(14, (long) cVar.l());
        databaseStatement.bindLong(15, (long) cVar.m());
        databaseStatement.bindLong(16, (long) cVar.n());
        databaseStatement.bindLong(17, cVar.o());
        s = cVar.p();
        if (s != null) {
            databaseStatement.bindString(18, s);
        }
        databaseStatement.bindLong(19, (long) cVar.q());
    }

    protected final void a(SQLiteStatement sQLiteStatement, c cVar) {
        long j = 1;
        sQLiteStatement.clearBindings();
        Long r = cVar.r();
        if (r != null) {
            sQLiteStatement.bindLong(1, r.longValue());
        }
        String s = cVar.s();
        if (s != null) {
            sQLiteStatement.bindString(2, s);
        }
        s = cVar.a();
        if (s != null) {
            sQLiteStatement.bindString(3, s);
        }
        s = cVar.b();
        if (s != null) {
            sQLiteStatement.bindString(4, s);
        }
        s = cVar.c();
        if (s != null) {
            sQLiteStatement.bindString(5, s);
        }
        s = cVar.d();
        if (s != null) {
            sQLiteStatement.bindString(6, s);
        }
        s = cVar.e();
        if (s != null) {
            sQLiteStatement.bindString(7, s);
        }
        sQLiteStatement.bindLong(8, cVar.f());
        sQLiteStatement.bindLong(9, cVar.g());
        sQLiteStatement.bindLong(10, (long) cVar.h());
        sQLiteStatement.bindLong(11, cVar.i() ? 1 : 0);
        if (!cVar.j()) {
            j = 0;
        }
        sQLiteStatement.bindLong(12, j);
        sQLiteStatement.bindLong(13, (long) cVar.k());
        sQLiteStatement.bindLong(14, (long) cVar.l());
        sQLiteStatement.bindLong(15, (long) cVar.m());
        sQLiteStatement.bindLong(16, (long) cVar.n());
        sQLiteStatement.bindLong(17, cVar.o());
        s = cVar.p();
        if (s != null) {
            sQLiteStatement.bindString(18, s);
        }
        sQLiteStatement.bindLong(19, (long) cVar.q());
    }

    public Long a(Cursor cursor, int i) {
        return cursor.isNull(i + 0) ? null : Long.valueOf(cursor.getLong(i + 0));
    }

    public c b(Cursor cursor, int i) {
        Long l;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        boolean z;
        boolean z2;
        String str7;
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
        long j = cursor.getLong(i + 7);
        long j2 = cursor.getLong(i + 8);
        int i2 = cursor.getInt(i + 9);
        if (cursor.getShort(i + 10) != (short) 0) {
            z = true;
        } else {
            z = false;
        }
        if (cursor.getShort(i + 11) != (short) 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        int i3 = cursor.getInt(i + 12);
        int i4 = cursor.getInt(i + 13);
        int i5 = cursor.getInt(i + 14);
        int i6 = cursor.getInt(i + 15);
        long j3 = cursor.getLong(i + 16);
        if (cursor.isNull(i + 17)) {
            str7 = null;
        } else {
            str7 = cursor.getString(i + 17);
        }
        return new c(l, str, str2, str3, str4, str5, str6, j, j2, i2, z, z2, i3, i4, i5, i6, j3, str7, cursor.getInt(i + 18));
    }

    public void a(Cursor cursor, c cVar, int i) {
        boolean z;
        boolean z2 = true;
        String str = null;
        cVar.a(cursor.isNull(i + 0) ? null : Long.valueOf(cursor.getLong(i + 0)));
        cVar.g(cursor.isNull(i + 1) ? null : cursor.getString(i + 1));
        cVar.a(cursor.isNull(i + 2) ? null : cursor.getString(i + 2));
        cVar.b(cursor.isNull(i + 3) ? null : cursor.getString(i + 3));
        cVar.c(cursor.isNull(i + 4) ? null : cursor.getString(i + 4));
        cVar.d(cursor.isNull(i + 5) ? null : cursor.getString(i + 5));
        cVar.e(cursor.isNull(i + 6) ? null : cursor.getString(i + 6));
        cVar.a(cursor.getLong(i + 7));
        cVar.b(cursor.getLong(i + 8));
        cVar.b(cursor.getInt(i + 9));
        if (cursor.getShort(i + 10) != (short) 0) {
            z = true;
        } else {
            z = false;
        }
        cVar.a(z);
        if (cursor.getShort(i + 11) == (short) 0) {
            z2 = false;
        }
        cVar.b(z2);
        cVar.c(cursor.getInt(i + 12));
        cVar.d(cursor.getInt(i + 13));
        cVar.e(cursor.getInt(i + 14));
        cVar.f(cursor.getInt(i + 15));
        cVar.c(cursor.getLong(i + 16));
        if (!cursor.isNull(i + 17)) {
            str = cursor.getString(i + 17);
        }
        cVar.f(str);
        cVar.g(cursor.getInt(i + 18));
    }

    protected final Long a(c cVar, long j) {
        cVar.a(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long a(c cVar) {
        if (cVar != null) {
            return cVar.r();
        }
        return null;
    }

    public boolean b(c cVar) {
        return cVar.r() != null;
    }

    protected final boolean isEntityUpdateable() {
        return true;
    }
}
