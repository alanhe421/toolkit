package com.qq.reader.common.web.js;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.db.handle.e;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.db.handle.j;
import com.qq.reader.common.db.handle.l;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.QueryBookIntroTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.web.js.a.a.b;
import com.qq.reader.cservice.cloud.g;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.reader.module.comic.entity.ComicShelfInfo;
import com.qq.reader.qplugin.local.TingBookMark;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSAddToBookShelf extends b {
    private Context a;

    public interface a {
        void a();

        void b();
    }

    public JSAddToBookShelf(Context context) {
        this.a = context;
    }

    public void audioBookAdd(String str) {
        try {
            int i;
            Object obj;
            JSONObject jSONObject = new JSONObject(str);
            long optLong = jSONObject.optLong("adid");
            String optString = jSONObject.optString("audioName");
            String optString2 = jSONObject.optString("anchorName");
            String optString3 = jSONObject.optString("downloadurl");
            String a = ao.a(optLong, false, 180);
            int optInt = jSONObject.optInt("drm");
            String optString4 = jSONObject.optString("format");
            int optInt2 = jSONObject.optInt("finished");
            int optInt3 = jSONObject.optInt("downloadtype");
            int optInt4 = jSONObject.optInt("payed");
            if (optInt3 == 0 || (optInt3 == 1 && optInt4 == 1)) {
                i = 1;
            } else {
                i = 0;
            }
            if (jSONObject.optInt("needtoast", 1) != 0) {
                obj = 1;
            } else {
                obj = null;
            }
            int optInt5 = jSONObject.optInt("allAudioChapters");
            long optLong2 = jSONObject.optLong("chapterid");
            String optString5 = jSONObject.optString("chaptertitle");
            String optString6 = jSONObject.optString("discountendtime");
            int optInt6 = jSONObject.optInt("discount");
            String optString7 = jSONObject.optString("bookfrom");
            e.a().a(String.valueOf(optLong), optString7);
            long optLong3 = jSONObject.optLong("lastUpdateTime");
            String optString8 = jSONObject.optString("lastChapterName");
            optString7 = jSONObject.optString(s.ORIGIN);
            if (TextUtils.isEmpty(optString7)) {
                optString7 = jSONObject.optString(s.STATPARAM_KEY);
            }
            if (i.c().e(String.valueOf(optLong)) == null) {
                OnlineTag onlineTag = new OnlineTag(String.valueOf(optLong), "", 0);
                onlineTag.a(optString).e(optString2).f(optString3).c(1).b(optString5).e(0).d(optInt5).f(0).h(a).k(optString4).i(optInt).h(optInt2);
                onlineTag.a(0);
                onlineTag.b(System.currentTimeMillis());
                v.b().b(onlineTag);
                Mark tingBookMark = new TingBookMark(optLong, optString);
                tingBookMark.setPercentStr("0.0%").setAuthor(optString2).setDescriptionStr("");
                tingBookMark.setStarPointStr(Mark.HEADPAGE_FLAG);
                tingBookMark.setHasNewContent(false);
                tingBookMark.setId(onlineTag.k());
                tingBookMark.setFinished(optInt2);
                long currentTimeMillis = System.currentTimeMillis();
                tingBookMark.setAuthor(onlineTag.o());
                tingBookMark.setBookName(optString);
                tingBookMark.setTotalChapterCount(optInt5);
                tingBookMark.setReadTime(0);
                tingBookMark.setOperateTime(currentTimeMillis);
                tingBookMark.setBookId(optLong);
                tingBookMark.setCoverUrl(a);
                if (optInt2 == 0) {
                    tingBookMark.setLastUpdateTime(optLong3);
                    tingBookMark.setLastUpdateChapter(optString8);
                }
                tingBookMark.setLimitFreeEndTime(optString6);
                tingBookMark.setDiscount(optInt6);
                tingBookMark.setBookId(optLong);
                tingBookMark.setCoverUrl(a);
                i.c().a(tingBookMark, true);
                j.a().a(new com.qq.reader.common.monitor.a.a("" + optLong, optString7));
                com.qq.reader.common.monitor.a.b.a(new com.qq.reader.common.monitor.a.a("" + optLong, optString7));
                d.h(ReaderApplication.getApplicationImp(), String.valueOf(tingBookMark.getBookId()));
                if (obj != null) {
                    af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getString(R.string.bookshelf_add_success), 0).a();
                }
                getIconFromUrl(tingBookMark);
                if (c.b()) {
                    g gVar = new g(optLong, 0, 2);
                    gVar.a(optString, optString4);
                    gVar.d(optString);
                    gVar.h(optString4);
                    gVar.e(optString2);
                    gVar.g(a);
                    gVar.d(optLong2);
                    gVar.a(0);
                    gVar.d(optInt5);
                    gVar.i(optString5);
                    gVar.f(optInt2);
                    gVar.c(i);
                    gVar.b(optInt);
                    gVar.e(0);
                    l.b().a(gVar);
                    com.qq.reader.cservice.cloud.b.a(ReaderApplication.getApplicationContext()).a(new com.qq.reader.cservice.cloud.a.b(optLong, 1, 0, 0, 2), false, null);
                }
            } else if (obj != null) {
                af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getString(R.string.bookshelf_had_book), 0).a();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        com.qq.reader.common.monitor.j.a(7, 2);
        com.qq.reader.common.monitor.i.a("event_C8", null, ReaderApplication.getApplicationImp());
        StatisticsManager.a().a("event_C8", null);
    }

    @Deprecated
    public void add(String str) {
        try {
            int i;
            Object obj;
            JSONObject jSONObject = new JSONObject(str);
            long optLong = jSONObject.optLong("id");
            String optString = jSONObject.optString("title");
            String optString2 = jSONObject.optString("author");
            String optString3 = jSONObject.optString("downloadurl");
            String g = ao.g(optLong);
            int optInt = jSONObject.optInt("drm");
            String optString4 = jSONObject.optString("format");
            int optInt2 = jSONObject.optInt("finished");
            int optInt3 = jSONObject.optInt("downloadtype");
            int optInt4 = jSONObject.optInt("payed");
            if (optInt3 == 0 || (optInt3 == 1 && optInt4 == 1)) {
                i = 1;
            } else {
                i = 0;
            }
            if (jSONObject.optInt("needtoast", 1) != 0) {
                obj = 1;
            } else {
                obj = null;
            }
            int optInt5 = jSONObject.optInt(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION);
            long optLong2 = jSONObject.optLong("chapterid");
            String optString5 = jSONObject.optString("chaptertitle");
            String optString6 = jSONObject.optString("discountendtime");
            int optInt6 = jSONObject.optInt("discount");
            e.a().a(String.valueOf(optLong), jSONObject.optString("bookfrom"));
            long optLong3 = jSONObject.optLong("lastuploadtime");
            String optString7 = jSONObject.optString("lastcname");
            String optString8 = jSONObject.optString(s.ORIGIN);
            if (TextUtils.isEmpty(optString8)) {
                optString8 = jSONObject.optString(s.STATPARAM_KEY);
            }
            String optString9 = jSONObject.optString("downloadinfo");
            if (i.c().e(String.valueOf(optLong)) == null) {
                Mark a;
                com.qq.reader.module.bookstore.qnative.b.b bVar = new com.qq.reader.module.bookstore.qnative.b.b();
                bVar.a(optString9);
                if (bVar.a()) {
                    a = com.qq.reader.framework.mark.a.a(optLong, optString, optString2, optString9);
                } else {
                    OnlineTag onlineTag = new OnlineTag(String.valueOf(optLong), "", 0);
                    onlineTag.a(optString).e(optString2).f(optString3).c(1).b(optString5).e(0).d(optInt5).f(0).h(g).k(optString4).i(optInt).h(optInt2);
                    onlineTag.a(0);
                    onlineTag.b(System.currentTimeMillis());
                    v.b().b(onlineTag);
                    a = new LocalMark(optString, onlineTag.f(), 0, 4, false);
                    a.setPercentStr("0.0%").setAuthor(optString2).setDescriptionStr("");
                    a.setStarPointStr(Mark.HEADPAGE_FLAG);
                    a.setHasNewContent(false);
                    a.setId(onlineTag.k());
                    a.setFinished(optInt2);
                    if (optInt2 == 0) {
                        a.setLastUpdateTime(optLong3);
                        a.setLastUpdateChapter(optString7);
                    }
                    a.setDownloadInfo(optString9);
                }
                a.setLimitFreeEndTime(optString6);
                a.setDiscount(optInt6);
                a.setBookId(optLong);
                a.setCoverUrl(g);
                i.c().a(a, true);
                j.a().a(new com.qq.reader.common.monitor.a.a("" + optLong, optString8));
                com.qq.reader.common.monitor.a.b.a(new com.qq.reader.common.monitor.a.a("" + optLong, optString8));
                d.h(ReaderApplication.getApplicationImp(), String.valueOf(a.getBookId()));
                if (obj != null) {
                    af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getString(R.string.bookshelf_add_success), 0).a();
                }
                getIconFromUrl(a);
                if (c.b()) {
                    com.qq.reader.cservice.cloud.b.a(ReaderApplication.getApplicationImp()).a(new com.qq.reader.cservice.cloud.a.b(optLong, 1, 0, 0, 1), false, null);
                    g gVar = new g(optLong, 0, 1);
                    gVar.a(optString, optString4);
                    gVar.d(optString);
                    gVar.h(optString4);
                    gVar.e(optString2);
                    gVar.g(g);
                    gVar.d(optLong2);
                    gVar.a(0);
                    gVar.d(optInt5);
                    gVar.i(optString5);
                    gVar.f(optInt2);
                    gVar.c(i);
                    gVar.b(optInt);
                    gVar.e(0);
                    l.b().a(gVar);
                }
                com.qq.reader.common.monitor.j.a(7, 2);
                com.qq.reader.common.monitor.i.a("event_C8", null, ReaderApplication.getApplicationImp());
                StatisticsManager.a().a("event_C8", null);
            }
            if (obj != null) {
                af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getString(R.string.bookshelf_had_book), 0).a();
            }
            com.qq.reader.common.monitor.j.a(7, 2);
            com.qq.reader.common.monitor.i.a("event_C8", null, ReaderApplication.getApplicationImp());
            StatisticsManager.a().a("event_C8", null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public void add2(String str, String str2) {
        try {
            int i;
            JSONObject jSONObject = new JSONObject(str);
            long optLong = jSONObject.optLong("id");
            String optString = jSONObject.optString("title");
            String optString2 = jSONObject.optString("author");
            String optString3 = jSONObject.optString("downloadUrl");
            String g = ao.g(optLong);
            int optInt = jSONObject.optInt("drm");
            String optString4 = jSONObject.optString("fileformat");
            int optInt2 = jSONObject.optInt("isfinished");
            int optInt3 = jSONObject.optInt("downloadType");
            boolean optBoolean = jSONObject.optBoolean("payinfo");
            if (optInt3 == 0 || (optInt3 == 1 && optBoolean)) {
                i = 1;
            } else {
                i = 0;
            }
            int optInt4 = jSONObject.optInt("totalChapters");
            long optLong2 = jSONObject.optLong("chapterid");
            String optString5 = jSONObject.optString("chaptertitle");
            e.a().a(String.valueOf(optLong), jSONObject.optString("copyright"));
            long optLong3 = jSONObject.optLong("lastuploadtime");
            String optString6 = jSONObject.optString("lastcname");
            String optString7 = jSONObject.optString(s.ORIGIN);
            if (TextUtils.isEmpty(optString7)) {
                optString7 = jSONObject.optString(s.STATPARAM_KEY);
            }
            if (i.c().e(String.valueOf(optLong)) == null) {
                OnlineTag onlineTag = new OnlineTag(String.valueOf(optLong), "", 0);
                onlineTag.a(optString).e(optString2).f(optString3).c(1).b(optString5).e(0).d(optInt4).f(0).h(g).k(optString4).i(optInt).h(optInt2);
                onlineTag.a(0);
                onlineTag.b(System.currentTimeMillis());
                v.b().b(onlineTag);
                Mark localMark = new LocalMark(optString, onlineTag.f(), 0, 4, false);
                localMark.setPercentStr("0.0%").setAuthor(optString2).setDescriptionStr("");
                localMark.setStarPointStr(Mark.HEADPAGE_FLAG);
                localMark.setHasNewContent(false);
                localMark.setId(onlineTag.k());
                localMark.setFinished(optInt2);
                if (optInt2 == 0) {
                    localMark.setLastUpdateTime(optLong3);
                    localMark.setLastUpdateChapter(optString6);
                }
                localMark.setBookId(Long.valueOf(onlineTag.k()).longValue());
                localMark.setCoverUrl(g);
                i.c().a(localMark, true);
                j.a().a(new com.qq.reader.common.monitor.a.a(onlineTag.k(), optString7));
                com.qq.reader.common.monitor.a.b.a(new com.qq.reader.common.monitor.a.a(onlineTag.k(), optString7));
                d.h(ReaderApplication.getApplicationImp(), String.valueOf(localMark.getBookId()));
                if ("true".equals(str2)) {
                    af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getString(R.string.bookshelf_add_success), 0).a();
                }
                getIconFromUrl(localMark);
                if (c.b()) {
                    com.qq.reader.cservice.cloud.b.a(ReaderApplication.getApplicationImp()).a(new com.qq.reader.cservice.cloud.a.b(optLong, 1, 0, 0, 1), false, null);
                    g gVar = new g(optLong, 0, 1);
                    gVar.a(optString, optString4);
                    gVar.d(optString);
                    gVar.h(optString4);
                    gVar.e(optString2);
                    gVar.g(g);
                    gVar.d(optLong2);
                    gVar.a(0);
                    gVar.d(optInt4);
                    gVar.i(optString5);
                    gVar.f(optInt2);
                    gVar.c(i);
                    gVar.b(optInt);
                    gVar.e(0);
                    l.b().a(gVar);
                }
            } else if ("true".equals(str2)) {
                af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getString(R.string.bookshelf_had_book), 0).a();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public void getIconFromUrl(Mark mark) {
        com.qq.reader.common.imageloader.c.a(ReaderApplication.getApplicationImp()).c(mark.getImageURI());
    }

    @Deprecated
    public void addFromAdv(String str) {
        try {
            int i;
            JSONObject jSONObject = new JSONObject(str);
            long optLong = jSONObject.optLong("id");
            String optString = jSONObject.optString("title");
            String optString2 = jSONObject.optString("author");
            String optString3 = jSONObject.optString("downloadurl");
            String g = ao.g(optLong);
            int optInt = jSONObject.optInt("drm");
            String optString4 = jSONObject.optString("fileformat");
            int optInt2 = jSONObject.optInt("isfinished");
            int optInt3 = jSONObject.optInt("downloadType");
            boolean optBoolean = jSONObject.optBoolean("payinfo");
            if (optInt3 == 0 || (optInt3 == 1 && optBoolean)) {
                i = 1;
            } else {
                i = 0;
            }
            int optInt4 = jSONObject.optInt("totalChapters");
            long optLong2 = jSONObject.optLong("lastupdatechapterid");
            String optString5 = jSONObject.optString("lastupdatechaptername");
            e.a().a(String.valueOf(optLong), jSONObject.optString("bookfrom"));
            long optLong3 = jSONObject.optLong("lastuploadtime");
            String optString6 = jSONObject.optString("lastcname");
            String optString7 = jSONObject.optString(s.ORIGIN);
            if (TextUtils.isEmpty(optString7)) {
                optString7 = jSONObject.optString(s.STATPARAM_KEY);
            }
            if (i.c().e(String.valueOf(optLong)) == null) {
                OnlineTag onlineTag = new OnlineTag(String.valueOf(optLong), "", 0);
                onlineTag.a(optString).e(optString2).f(optString3).c(1).b(optString5).e(0).d(optInt4).f(0).h(g).k(optString4).i(optInt).h(optInt2);
                onlineTag.a(0);
                onlineTag.b(System.currentTimeMillis());
                v.b().b(onlineTag);
                Mark localMark = new LocalMark(optString, onlineTag.f(), 0, 4, false);
                localMark.setPercentStr("0.0%").setAuthor(optString2).setDescriptionStr("");
                localMark.setStarPointStr(Mark.HEADPAGE_FLAG);
                localMark.setHasNewContent(false);
                localMark.setId(onlineTag.k());
                localMark.setFinished(optInt2);
                if (optInt2 == 0) {
                    localMark.setLastUpdateTime(optLong3);
                    localMark.setLastUpdateChapter(optString6);
                }
                localMark.setBookId(Long.valueOf(onlineTag.k()).longValue());
                localMark.setCoverUrl(g);
                i.c().a(localMark, true);
                j.a().a(new com.qq.reader.common.monitor.a.a(onlineTag.k(), optString7));
                com.qq.reader.common.monitor.a.b.a(new com.qq.reader.common.monitor.a.a(onlineTag.k(), optString7));
                d.h(ReaderApplication.getApplicationImp(), String.valueOf(localMark.getBookId()));
                af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getString(R.string.bookshelf_add_success), 0).a();
                getIconFromUrl(localMark);
                if (c.b()) {
                    com.qq.reader.cservice.cloud.b.a(ReaderApplication.getApplicationImp()).a(new com.qq.reader.cservice.cloud.a.b(optLong, 1, 0, 0, 1), false, null);
                    g gVar = new g(optLong, 0, 1);
                    gVar.a(optString, optString4);
                    gVar.d(optString);
                    gVar.h(optString4);
                    gVar.e(optString2);
                    gVar.g(g);
                    gVar.d(optLong2);
                    gVar.a(0);
                    gVar.d(optInt4);
                    gVar.i(optString5);
                    gVar.f(optInt2);
                    gVar.c(i);
                    gVar.b(optInt);
                    gVar.e(0);
                    f.b("JSAddToBookShelf", "sourceType :" + 0);
                    l.b().a(gVar);
                }
            } else {
                af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getString(R.string.bookshelf_had_book), 0).a();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        com.qq.reader.common.monitor.j.a(7, 2);
        com.qq.reader.common.monitor.i.a("event_C8", null, ReaderApplication.getApplicationImp());
        StatisticsManager.a().a("event_C8", null);
    }

    public void addBooks(String str) {
        try {
            if (c.b()) {
                JSONArray jSONArray = new JSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (jSONObject != null) {
                        String optString;
                        long j = (long) jSONObject.getInt("bid");
                        String string = jSONObject.getString("title");
                        String string2 = jSONObject.getString("author");
                        if (TextUtils.isEmpty(jSONObject.optString("downloadinfo"))) {
                            optString = jSONObject.optString("downloadInfo");
                        } else {
                            optString = jSONObject.optString("downloadinfo");
                        }
                        Mark a = com.qq.reader.framework.mark.a.a(j, string, string2, optString);
                        i.c().a(a, false);
                        com.qq.reader.cservice.cloud.b.a(ReaderApplication.getApplicationImp().getApplicationContext()).a(new com.qq.reader.cservice.cloud.a.b(a.getBookId(), 1, 0, 0, 1), false, null);
                    }
                }
                af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getString(R.string.bookshelf_add_success), 0).a();
                return;
            }
            ((ReaderBaseActivity) this.a).startLogin();
        } catch (Exception e) {
        }
    }

    @Deprecated
    public void addBook(String str) {
        try {
            String optString;
            JSONObject jSONObject = new JSONObject(str);
            long j = (long) jSONObject.getInt("bid");
            String string = jSONObject.getString("title");
            String string2 = jSONObject.getString("author");
            if (TextUtils.isEmpty(jSONObject.optString("downloadinfo"))) {
                optString = jSONObject.optString("downloadInfo");
            } else {
                optString = jSONObject.optString("downloadinfo");
            }
            Mark a = com.qq.reader.framework.mark.a.a(j, string, string2, optString);
            i.c().a(a, false);
            com.qq.reader.cservice.cloud.b.a(ReaderApplication.getApplicationImp()).a(new com.qq.reader.cservice.cloud.a.b(a.getBookId(), 1, 0, 0, 1), false, null);
        } catch (Exception e) {
        }
    }

    public void addById(String str, final String str2) {
        if (str != null && str.trim().length() > 0) {
            com.qq.reader.common.readertask.g.a().a(new QueryBookIntroTask(new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ JSAddToBookShelf b;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, final String str, long j) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    try {
                        handler.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void run() {
                                this.b.b.add2(str, str2);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                        if ("true".equals(str2)) {
                            handler.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getString(R.string.not_available), 0).a();
                                }
                            });
                        }
                    }
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    if ("true".equals(str2)) {
                        try {
                            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    af.a(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getString(R.string.net_not_available), 0).a();
                                }
                            });
                        } catch (Exception e) {
                        }
                    }
                }
            }, str));
        }
    }

    public void addById(String str) {
        addByIdWithCallBack(str, "true", null);
    }

    public void addByIdWithCallBack(String str, final String str2, final a aVar) {
        if (str != null && str.trim().length() > 0) {
            com.qq.reader.common.readertask.g.a().a(new QueryBookIntroTask(new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ JSAddToBookShelf c;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, final String str, long j) {
                    try {
                        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 b;

                            public void run() {
                                this.b.c.add2(str, str2);
                                if (aVar != null) {
                                    aVar.a();
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    if (aVar != null) {
                        aVar.b();
                    }
                }
            }, str));
        }
    }

    public void addComic(String str) {
        addComic(str, "1");
    }

    public void addComic(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            long optLong = jSONObject.optLong("bid");
            String optString = jSONObject.optString("title");
            String optString2 = jSONObject.optString("author");
            int optInt = jSONObject.optInt("sectionCount");
            int optInt2 = jSONObject.optInt("finished");
            ComicShelfInfo comicShelfInfo = new ComicShelfInfo();
            comicShelfInfo.a(optLong + "");
            comicShelfInfo.b(optString);
            comicShelfInfo.c(optString2);
            comicShelfInfo.a(optInt);
            comicShelfInfo.b(optInt2);
            com.qq.reader.module.comic.e.e.a(this.a, comicShelfInfo, "1".equals(str2));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
