package com.qrcomic.entity;

import android.content.Context;
import android.util.Log;
import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.identityscope.IdentityScopeType;

/* compiled from: DaoMaster */
public class g extends AbstractDaoMaster {

    /* compiled from: DaoMaster */
    public static abstract class b extends DatabaseOpenHelper {
        public b(Context context, String str) {
            super(context, str, 1);
        }

        public void onCreate(Database database) {
            Log.i("greenDAO", "Creating tables for schema version 1");
            g.a(database, false);
        }
    }

    /* compiled from: DaoMaster */
    public static class a extends b {
        public a(Context context, String str) {
            super(context, str);
        }

        public void onUpgrade(Database database, int i, int i2) {
            Log.i("greenDAO", "Upgrading schema from version " + i + " to " + i2 + " by dropping all tables");
            g.b(database, true);
            onCreate(database);
        }
    }

    public /* synthetic */ AbstractDaoSession newSession() {
        return a();
    }

    public /* synthetic */ AbstractDaoSession newSession(IdentityScopeType identityScopeType) {
        return a(identityScopeType);
    }

    public static void a(Database database, boolean z) {
        DownloadHistoryDao.a(database, z);
        QRComicUpdateReadProgressFailDao.a(database, z);
        QRComicBuyInfoDao.a(database, z);
        ComicSectionDao.a(database, z);
        ComicHistoryDao.a(database, z);
        ComicDao.a(database, z);
        ComicCollectionDao.a(database, z);
        ComicReadProgressDao.a(database, z);
    }

    public static void b(Database database, boolean z) {
        DownloadHistoryDao.b(database, z);
        QRComicUpdateReadProgressFailDao.b(database, z);
        QRComicBuyInfoDao.b(database, z);
        ComicSectionDao.b(database, z);
        ComicHistoryDao.b(database, z);
        ComicDao.b(database, z);
        ComicCollectionDao.b(database, z);
        ComicReadProgressDao.b(database, z);
    }

    public g(Database database) {
        super(database, 1);
        registerDaoClass(DownloadHistoryDao.class);
        registerDaoClass(QRComicUpdateReadProgressFailDao.class);
        registerDaoClass(QRComicBuyInfoDao.class);
        registerDaoClass(ComicSectionDao.class);
        registerDaoClass(ComicHistoryDao.class);
        registerDaoClass(ComicDao.class);
        registerDaoClass(ComicCollectionDao.class);
        registerDaoClass(ComicReadProgressDao.class);
    }

    public h a() {
        return new h(this.db, IdentityScopeType.Session, this.daoConfigMap);
    }

    public h a(IdentityScopeType identityScopeType) {
        return new h(this.db, identityScopeType, this.daoConfigMap);
    }
}
