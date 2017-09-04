package com.qrcomic.entity;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class ComicCollectionDao extends AbstractDao<b, Long> {
    public static final String TABLENAME = "COMIC_COLLECTION";

    public static class Properties {
        public static final Property a = new Property(0, Long.class, "_id", true, "_id");
        public static final Property b = new Property(1, String.class, "uin", false, "UIN");
        public static final Property c = new Property(2, String.class, ComicStoreExclusiveItemCard.NET_AD_ATTR_COMICID, false, "COMIC_ID");
        public static final Property d = new Property(3, String.class, "comicName", false, "COMIC_NAME");
        public static final Property e = new Property(4, String.class, "comicCoverUrl", false, "COMIC_COVER_URL");
        public static final Property f = new Property(5, String.class, "updateSectionId", false, "UPDATE_SECTION_ID");
        public static final Property g = new Property(6, Long.TYPE, "collectTs", false, "COLLECT_TS");
        public static final Property h = new Property(7, Long.TYPE, "maxTs", false, "MAX_TS");
        public static final Property i = new Property(8, Integer.TYPE, "status", false, "STATUS");
        public static final Property j = new Property(9, Boolean.TYPE, "isSaveServer", false, "IS_SAVE_SERVER");
        public static final Property k = new Property(10, Boolean.TYPE, "isTsUpdate", false, "IS_TS_UPDATE");
        public static final Property l = new Property(11, Integer.TYPE, "opt", false, "OPT");
        public static final Property m = new Property(12, Integer.TYPE, "attentionUpdateOpt", false, "ATTENTION_UPDATE_OPT");
        public static final Property n = new Property(13, Integer.TYPE, "iegComicStatus", false, "IEG_COMIC_STATUS");
        public static final Property o = new Property(14, Integer.TYPE, "type", false, "TYPE");
        public static final Property p = new Property(15, Long.TYPE, "updateTs", false, "UPDATE_TS");
        public static final Property q = new Property(16, String.class, "sectionName", false, "SECTION_NAME");
        public static final Property r = new Property(17, Integer.TYPE, "picSeq", false, "PIC_SEQ");
    }

    protected /* synthetic */ void bindValues(SQLiteStatement sQLiteStatement, Object obj) {
        a(sQLiteStatement, (b) obj);
    }

    protected /* synthetic */ void bindValues(DatabaseStatement databaseStatement, Object obj) {
        a(databaseStatement, (b) obj);
    }

    public /* synthetic */ Object getKey(Object obj) {
        return a((b) obj);
    }

    public /* synthetic */ boolean hasKey(Object obj) {
        return b((b) obj);
    }

    public /* synthetic */ Object readEntity(Cursor cursor, int i) {
        return b(cursor, i);
    }

    public /* synthetic */ void readEntity(Cursor cursor, Object obj, int i) {
        a(cursor, (b) obj, i);
    }

    public /* synthetic */ Object readKey(Cursor cursor, int i) {
        return a(cursor, i);
    }

    protected /* synthetic */ Object updateKeyAfterInsert(Object obj, long j) {
        return a((b) obj, j);
    }

    public ComicCollectionDao(DaoConfig daoConfig, h hVar) {
        super(daoConfig, hVar);
    }

    public static void a(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"COMIC_COLLECTION\" (" + "\"_id\" INTEGER PRIMARY KEY ," + "\"UIN\" TEXT," + "\"COMIC_ID\" TEXT," + "\"COMIC_NAME\" TEXT," + "\"COMIC_COVER_URL\" TEXT," + "\"UPDATE_SECTION_ID\" TEXT," + "\"COLLECT_TS\" INTEGER NOT NULL ," + "\"MAX_TS\" INTEGER NOT NULL ," + "\"STATUS\" INTEGER NOT NULL ," + "\"IS_SAVE_SERVER\" INTEGER NOT NULL ," + "\"IS_TS_UPDATE\" INTEGER NOT NULL ," + "\"OPT\" INTEGER NOT NULL ," + "\"ATTENTION_UPDATE_OPT\" INTEGER NOT NULL ," + "\"IEG_COMIC_STATUS\" INTEGER NOT NULL ," + "\"TYPE\" INTEGER NOT NULL ," + "\"UPDATE_TS\" INTEGER NOT NULL ," + "\"SECTION_NAME\" TEXT," + "\"PIC_SEQ\" INTEGER NOT NULL );");
    }

    public static void b(Database database, boolean z) {
        database.execSQL("DROP TABLE " + (z ? "IF EXISTS " : "") + "\"COMIC_COLLECTION\"");
    }

    protected final void a(DatabaseStatement databaseStatement, b bVar) {
        long j = 1;
        databaseStatement.clearBindings();
        Long r = bVar.r();
        if (r != null) {
            databaseStatement.bindLong(1, r.longValue());
        }
        String a = bVar.a();
        if (a != null) {
            databaseStatement.bindString(2, a);
        }
        a = bVar.b();
        if (a != null) {
            databaseStatement.bindString(3, a);
        }
        a = bVar.c();
        if (a != null) {
            databaseStatement.bindString(4, a);
        }
        a = bVar.d();
        if (a != null) {
            databaseStatement.bindString(5, a);
        }
        a = bVar.e();
        if (a != null) {
            databaseStatement.bindString(6, a);
        }
        databaseStatement.bindLong(7, bVar.f());
        databaseStatement.bindLong(8, bVar.g());
        databaseStatement.bindLong(9, (long) bVar.h());
        databaseStatement.bindLong(10, bVar.i() ? 1 : 0);
        if (!bVar.j()) {
            j = 0;
        }
        databaseStatement.bindLong(11, j);
        databaseStatement.bindLong(12, (long) bVar.k());
        databaseStatement.bindLong(13, (long) bVar.l());
        databaseStatement.bindLong(14, (long) bVar.m());
        databaseStatement.bindLong(15, (long) bVar.n());
        databaseStatement.bindLong(16, bVar.o());
        a = bVar.p();
        if (a != null) {
            databaseStatement.bindString(17, a);
        }
        databaseStatement.bindLong(18, (long) bVar.q());
    }

    protected final void a(SQLiteStatement sQLiteStatement, b bVar) {
        long j = 1;
        sQLiteStatement.clearBindings();
        Long r = bVar.r();
        if (r != null) {
            sQLiteStatement.bindLong(1, r.longValue());
        }
        String a = bVar.a();
        if (a != null) {
            sQLiteStatement.bindString(2, a);
        }
        a = bVar.b();
        if (a != null) {
            sQLiteStatement.bindString(3, a);
        }
        a = bVar.c();
        if (a != null) {
            sQLiteStatement.bindString(4, a);
        }
        a = bVar.d();
        if (a != null) {
            sQLiteStatement.bindString(5, a);
        }
        a = bVar.e();
        if (a != null) {
            sQLiteStatement.bindString(6, a);
        }
        sQLiteStatement.bindLong(7, bVar.f());
        sQLiteStatement.bindLong(8, bVar.g());
        sQLiteStatement.bindLong(9, (long) bVar.h());
        sQLiteStatement.bindLong(10, bVar.i() ? 1 : 0);
        if (!bVar.j()) {
            j = 0;
        }
        sQLiteStatement.bindLong(11, j);
        sQLiteStatement.bindLong(12, (long) bVar.k());
        sQLiteStatement.bindLong(13, (long) bVar.l());
        sQLiteStatement.bindLong(14, (long) bVar.m());
        sQLiteStatement.bindLong(15, (long) bVar.n());
        sQLiteStatement.bindLong(16, bVar.o());
        a = bVar.p();
        if (a != null) {
            sQLiteStatement.bindString(17, a);
        }
        sQLiteStatement.bindLong(18, (long) bVar.q());
    }

    public Long a(Cursor cursor, int i) {
        return cursor.isNull(i + 0) ? null : Long.valueOf(cursor.getLong(i + 0));
    }

    public b b(Cursor cursor, int i) {
        Long l;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        boolean z;
        boolean z2;
        String str6;
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
        long j = cursor.getLong(i + 6);
        long j2 = cursor.getLong(i + 7);
        int i2 = cursor.getInt(i + 8);
        if (cursor.getShort(i + 9) != (short) 0) {
            z = true;
        } else {
            z = false;
        }
        if (cursor.getShort(i + 10) != (short) 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        int i3 = cursor.getInt(i + 11);
        int i4 = cursor.getInt(i + 12);
        int i5 = cursor.getInt(i + 13);
        int i6 = cursor.getInt(i + 14);
        long j3 = cursor.getLong(i + 15);
        if (cursor.isNull(i + 16)) {
            str6 = null;
        } else {
            str6 = cursor.getString(i + 16);
        }
        return new b(l, str, str2, str3, str4, str5, j, j2, i2, z, z2, i3, i4, i5, i6, j3, str6, cursor.getInt(i + 17));
    }

    public void a(Cursor cursor, b bVar, int i) {
        boolean z;
        boolean z2 = true;
        String str = null;
        bVar.a(cursor.isNull(i + 0) ? null : Long.valueOf(cursor.getLong(i + 0)));
        bVar.a(cursor.isNull(i + 1) ? null : cursor.getString(i + 1));
        bVar.b(cursor.isNull(i + 2) ? null : cursor.getString(i + 2));
        bVar.c(cursor.isNull(i + 3) ? null : cursor.getString(i + 3));
        bVar.d(cursor.isNull(i + 4) ? null : cursor.getString(i + 4));
        bVar.e(cursor.isNull(i + 5) ? null : cursor.getString(i + 5));
        bVar.a(cursor.getLong(i + 6));
        bVar.b(cursor.getLong(i + 7));
        bVar.b(cursor.getInt(i + 8));
        if (cursor.getShort(i + 9) != (short) 0) {
            z = true;
        } else {
            z = false;
        }
        bVar.a(z);
        if (cursor.getShort(i + 10) == (short) 0) {
            z2 = false;
        }
        bVar.b(z2);
        bVar.c(cursor.getInt(i + 11));
        bVar.d(cursor.getInt(i + 12));
        bVar.e(cursor.getInt(i + 13));
        bVar.f(cursor.getInt(i + 14));
        bVar.c(cursor.getLong(i + 15));
        if (!cursor.isNull(i + 16)) {
            str = cursor.getString(i + 16);
        }
        bVar.f(str);
        bVar.g(cursor.getInt(i + 17));
    }

    protected final Long a(b bVar, long j) {
        bVar.a(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long a(b bVar) {
        if (bVar != null) {
            return bVar.r();
        }
        return null;
    }

    public boolean b(b bVar) {
        return bVar.r() != null;
    }

    protected final boolean isEntityUpdateable() {
        return true;
    }
}
