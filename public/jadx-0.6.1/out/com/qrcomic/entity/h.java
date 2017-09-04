package com.qrcomic.entity;

import java.util.Map;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

/* compiled from: DaoSession */
public class h extends AbstractDaoSession {
    private final DaoConfig a;
    private final DaoConfig b;
    private final DaoConfig c;
    private final DaoConfig d;
    private final DaoConfig e;
    private final DaoConfig f;
    private final DaoConfig g;
    private final DaoConfig h;
    private final DownloadHistoryDao i = new DownloadHistoryDao(this.a, this);
    private final QRComicUpdateReadProgressFailDao j = new QRComicUpdateReadProgressFailDao(this.b, this);
    private final QRComicBuyInfoDao k = new QRComicBuyInfoDao(this.c, this);
    private final ComicSectionDao l = new ComicSectionDao(this.d, this);
    private final ComicHistoryDao m = new ComicHistoryDao(this.e, this);
    private final ComicDao n = new ComicDao(this.f, this);
    private final ComicCollectionDao o = new ComicCollectionDao(this.g, this);
    private final ComicReadProgressDao p = new ComicReadProgressDao(this.h, this);

    public h(Database database, IdentityScopeType identityScopeType, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> map) {
        super(database);
        this.a = ((DaoConfig) map.get(DownloadHistoryDao.class)).clone();
        this.a.initIdentityScope(identityScopeType);
        this.b = ((DaoConfig) map.get(QRComicUpdateReadProgressFailDao.class)).clone();
        this.b.initIdentityScope(identityScopeType);
        this.c = ((DaoConfig) map.get(QRComicBuyInfoDao.class)).clone();
        this.c.initIdentityScope(identityScopeType);
        this.d = ((DaoConfig) map.get(ComicSectionDao.class)).clone();
        this.d.initIdentityScope(identityScopeType);
        this.e = ((DaoConfig) map.get(ComicHistoryDao.class)).clone();
        this.e.initIdentityScope(identityScopeType);
        this.f = ((DaoConfig) map.get(ComicDao.class)).clone();
        this.f.initIdentityScope(identityScopeType);
        this.g = ((DaoConfig) map.get(ComicCollectionDao.class)).clone();
        this.g.initIdentityScope(identityScopeType);
        this.h = ((DaoConfig) map.get(ComicReadProgressDao.class)).clone();
        this.h.initIdentityScope(identityScopeType);
        registerDao(i.class, this.i);
        registerDao(m.class, this.j);
        registerDao(l.class, this.k);
        registerDao(f.class, this.l);
        registerDao(c.class, this.m);
        registerDao(a.class, this.n);
        registerDao(b.class, this.o);
        registerDao(e.class, this.p);
    }

    public DownloadHistoryDao a() {
        return this.i;
    }

    public QRComicUpdateReadProgressFailDao b() {
        return this.j;
    }

    public QRComicBuyInfoDao c() {
        return this.k;
    }

    public ComicSectionDao d() {
        return this.l;
    }

    public ComicHistoryDao e() {
        return this.m;
    }

    public ComicDao f() {
        return this.n;
    }

    public ComicCollectionDao g() {
        return this.o;
    }

    public ComicReadProgressDao h() {
        return this.p;
    }
}
