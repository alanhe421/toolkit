package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.k;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubCommentDetailCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubReplyCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubTitleCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookScoreCard;
import com.qq.reader.module.bookstore.qnative.card.impl.CommentLinkCard;
import com.qq.reader.module.bookstore.qnative.card.impl.CommentSpecLinkCard;
import com.qq.reader.module.bookstore.qnative.card.impl.MyFavorEmptyCard;
import com.qq.reader.module.bookstore.qnative.card.impl.ReplyLoadMoreCard;
import com.qq.reader.module.bookstore.qnative.card.impl.SelectedCommentBookInfoCard;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentOfReply;
import com.qq.reader.module.bookstore.qnative.item.l;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfReply */
public class ao extends ah {
    public static int A = -10000;
    public static int B = 2147482647;
    public static final int C = (A + 1);
    public static final int D = (A + 2);
    public static final int E = (A + 3);
    public static final int F = (A + 4);
    public static final int G = (A + 5);
    public static final int H = (A + 6);
    public static final int I = (A + 7);
    public static final int J = (A + 8);
    public static final int K = (A + 9);
    public static final int L = (A + 100);
    public int M = 1;
    public int N = 20;
    public int O = 0;
    public int P = -1;
    public int Q = -1;
    public boolean R = false;
    public int S = 0;
    public int T = 0;
    public int U = 0;
    public int V = 0;
    public int W = 6;
    public int X = 0;
    public String Y = "";

    /* compiled from: NativeServerPageOfReply */
    public class a implements Comparator<com.qq.reader.module.bookstore.qnative.card.a> {
        final /* synthetic */ ao a;

        public a(ao aoVar) {
            this.a = aoVar;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((com.qq.reader.module.bookstore.qnative.card.a) obj, (com.qq.reader.module.bookstore.qnative.card.a) obj2);
        }

        public int a(com.qq.reader.module.bookstore.qnative.card.a aVar, com.qq.reader.module.bookstore.qnative.card.a aVar2) {
            float uILevel = aVar.getUILevel() - aVar2.getUILevel();
            if (uILevel > 0.0f) {
                return (int) Math.ceil((double) uILevel);
            }
            if (uILevel < 0.0f) {
                return (int) Math.floor((double) uILevel);
            }
            return 0;
        }
    }

    public ao(Bundle bundle) {
        super(bundle);
        this.M = bundle.getInt("floor_index", 2);
        this.N = bundle.getInt("floor_next", 20);
        this.o = -1;
        bundle.putLong("KEY_PAGEINDEX", this.o);
        this.R = bundle.getBoolean("page_replyloadpre");
        this.v = new com.qq.reader.module.bookstore.qnative.model.a.a();
    }

    public int F() {
        return this.Q;
    }

    public String a(Bundle bundle) {
        c cVar = new c(bundle);
        int i = cVar.a().getInt("CTYPE");
        int i2 = bundle.getInt("floor_index", 2);
        int i3 = bundle.getInt("floor_next", 20);
        return cVar.b("nativepage/comment/replistByIndex?ctype=" + i + "&index=" + i2 + "&next=" + i3 + "&from=" + bundle.getInt("from", 0));
    }

    public void a(b bVar) {
        if (bVar instanceof ao) {
            super.a(bVar);
            ao aoVar = (ao) bVar;
            this.M = aoVar.M;
            this.N = aoVar.N;
            this.O = aoVar.O;
            this.P = aoVar.P;
            this.Q = aoVar.Q;
            this.R = aoVar.R;
            this.S = aoVar.S;
            this.T = aoVar.T;
            this.U = aoVar.U;
            this.V = aoVar.V;
            this.W = aoVar.W;
            this.X = aoVar.X;
            this.Y = aoVar.Y;
        }
    }

    protected void a(JSONObject jSONObject, JSONObject jSONObject2) {
        String str = null;
        if (jSONObject2.optInt("code") == -1) {
            MyFavorEmptyCard myFavorEmptyCard = new MyFavorEmptyCard(this, "MyFavourEmpty");
            MyFavorEmptyCard myFavorEmptyCard2 = myFavorEmptyCard;
            myFavorEmptyCard2.setText("内容不存在或已删除");
            myFavorEmptyCard2.setImage(R.drawable.empty08);
            if (this.k.size() == 0) {
                this.k.add(myFavorEmptyCard);
                return;
            }
            return;
        }
        try {
            this.S = jSONObject2.optInt("cmr");
            this.T = jSONObject2.optInt("toplimit");
            this.u = jSONObject2.optString("commentuid");
            String string = jSONObject.getString("type");
            String toLowerCase = string.toLowerCase();
            com.qq.reader.module.bookstore.qnative.card.a bookClubCommentDetailCard;
            if ("comment".equalsIgnoreCase(toLowerCase)) {
                bookClubCommentDetailCard = new BookClubCommentDetailCard(this, "BookClubTopicCard", this.f, this.w);
                bookClubCommentDetailCard.setUILevel((float) C);
                bookClubCommentDetailCard.fillData(jSONObject2.optJSONObject(string));
                bookClubCommentDetailCard.setEventListener(l());
                if (this.f != null && this.f.containsKey("ISMAXREWARD")) {
                    ((BookClubCommentDetailCard) bookClubCommentDetailCard).mIsMaxReward = this.f.getBoolean("ISMAXREWARD");
                }
                a(bookClubCommentDetailCard);
                this.v.a(((BookClubCommentDetailCard) bookClubCommentDetailCard).getCommentId());
                this.v.b(((BookClubCommentDetailCard) bookClubCommentDetailCard).getCommentUid());
                this.e = ((BookClubCommentDetailCard) bookClubCommentDetailCard).mBookid;
                this.t = ((BookClubCommentDetailCard) bookClubCommentDetailCard).mBookName;
                this.s = ((BookClubCommentDetailCard) bookClubCommentDetailCard).mSharedStringBuilder.toString();
                JSONObject optJSONObject = jSONObject2.optJSONObject("comment");
                if (optJSONObject != null) {
                    this.b = optJSONObject.getInt("agreestatus");
                    this.a = optJSONObject.getInt("agree");
                }
                this.d = jSONObject2.getInt("replycount");
            } else if ("book".equalsIgnoreCase(toLowerCase)) {
                bookClubCommentDetailCard = new SelectedCommentBookInfoCard(this, "SelectedCommentBookInfoCard", this.f, this.w);
                bookClubCommentDetailCard.fillData(jSONObject2.optJSONObject(string));
                bookClubCommentDetailCard.setUILevel((float) D);
                bookClubCommentDetailCard.setEventListener(l());
                this.e = ((SelectedCommentBookInfoCard) bookClubCommentDetailCard).getBookId();
                this.k.add(bookClubCommentDetailCard);
                i.a("event_C277", null, ReaderApplication.getApplicationImp());
                this.l.put(bookClubCommentDetailCard.getCardId(), bookClubCommentDetailCard);
            } else if ("gotodetail".equalsIgnoreCase(toLowerCase)) {
                r2 = new CommentLinkCard(this, "CommentLinkCard", this.w);
                r2.fillData(jSONObject2.optJSONObject(string));
                r2.setUILevel((float) E);
                r2.setEventListener(l());
                this.k.add(r2);
                this.l.put(r2.getCardId(), r2);
                CommentLinkCard commentLinkCard = (CommentLinkCard) r2;
                commentLinkCard.mCommentid = this.v.a();
                commentLinkCard.uid = this.v.b();
                commentLinkCard.bid = this.e;
            } else if ("gotodetailSpec".equalsIgnoreCase(toLowerCase)) {
                r2 = new CommentSpecLinkCard(this, "CommentLinkCard", this.w);
                r2.fillData(jSONObject2.optJSONObject(string));
                r2.setUILevel((float) E);
                r2.setEventListener(l());
                this.k.add(r2);
                this.l.put(r2.getCardId(), r2);
                i.a("event_C254", null, ReaderApplication.getApplicationImp());
            } else if ("topreply".equalsIgnoreCase(toLowerCase)) {
                r2 = new BookClubTitleCard(this, "BookClubTitleCard", this.w);
                r2.setUILevel((float) F);
                r2.fillData(jSONObject);
                r2.setEventListener(l());
                this.k.add(r2);
                this.l.put(r2.getCardId(), r2);
                try {
                    toLowerCase = jSONObject2.optJSONObject("comment").optJSONObject("user").optString("uid");
                } catch (Exception e) {
                    toLowerCase = str;
                }
                bookClubCommentDetailCard = new BookClubReplyCard(this, "BookClubReplyCard", this.w);
                bookClubCommentDetailCard.setUILevel((float) G);
                ((BookClubReplyCard) bookClubCommentDetailCard).setTopRrply(true);
                bookClubCommentDetailCard.fillData(jSONObject2.optJSONObject(string));
                bookClubCommentDetailCard.setEventListener(l());
                this.k.add(bookClubCommentDetailCard);
                this.l.put(bookClubCommentDetailCard.getCardId(), bookClubCommentDetailCard);
                ((BookClubReplyCard) bookClubCommentDetailCard).mCommentUid = toLowerCase;
            } else if ("score".equals(toLowerCase)) {
                r2 = new BookScoreCard(this, "BookScoreCard", this.w);
                r2.setUILevel((float) H);
                r2.fillData(jSONObject2);
                r2.setEventListener(l());
                this.k.add(r2);
            } else if ("replylist".equals(toLowerCase)) {
                JSONObject optJSONObject2 = jSONObject2.optJSONObject("comment");
                if (optJSONObject2 != null) {
                    JSONObject optJSONObject3 = optJSONObject2.optJSONObject("user");
                    str = optJSONObject3.optString("uid");
                    this.Y = optJSONObject3.optString("uid");
                    this.X = optJSONObject3.optInt(BookClubReplyCard.REPLY_USER_BLACK);
                    this.V = optJSONObject2.optInt("top");
                    this.U = optJSONObject2.optInt("better");
                    this.W = optJSONObject2.optInt("status");
                }
                this.d = jSONObject2.getInt("replycount");
                JSONArray optJSONArray = jSONObject2.optJSONArray(string);
                string = jSONObject2.optString("signal");
                if (!(jSONObject.optString("title", null) == null || string.equals("nextpage"))) {
                    BookClubTitleCard bookClubTitleCard = new BookClubTitleCard(this, "BookClubTitleCard", this.w);
                    bookClubTitleCard.setUILevel((float) I);
                    bookClubTitleCard.fillData(jSONObject);
                    bookClubTitleCard.setEventListener(l());
                    this.k.add(bookClubTitleCard);
                    this.l.put(bookClubTitleCard.getCardId(), bookClubTitleCard);
                    if (optJSONArray != null && optJSONArray.length() == 0) {
                        bookClubTitleCard.setTipVisible(true);
                    }
                }
                if (optJSONArray != null) {
                    List arrayList = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        BookClubReplyCard bookClubReplyCard = new BookClubReplyCard(this, "BookClubReplyCard", this.w);
                        bookClubReplyCard.setTopRrply(false);
                        bookClubReplyCard.fillData(optJSONArray.getJSONObject(i));
                        bookClubReplyCard.setEventListener(l());
                        arrayList.add(bookClubReplyCard);
                        this.l.put(bookClubReplyCard.getCardId(), bookClubReplyCard);
                        bookClubReplyCard.mCommentUid = str;
                    }
                    this.O = optJSONArray.length();
                    b(arrayList);
                    this.k.addAll(arrayList);
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    protected void b(List<com.qq.reader.module.bookstore.qnative.card.a> list) {
        Exception e;
        if (list.size() != 0) {
            int a;
            if (this.N > 0) {
                this.P = this.M;
                this.Q = a((BookClubReplyCard) list.get(list.size() - 1));
            } else {
                int i;
                a = a((BookClubReplyCard) list.get(0));
                if (this.M + this.N <= 2) {
                    i = 2;
                } else {
                    i = this.M + this.N;
                }
                this.P = i;
                this.P = Math.min(this.P, a);
                this.Q = this.M;
            }
            Object obj = null;
            a = this.P;
            while (a < this.Q) {
                Object obj2;
                if (a(a, (List) list)) {
                    obj2 = obj;
                } else {
                    BookClubReplyCard bookClubReplyCard = new BookClubReplyCard(this, "BookClubReplyCard", this.w);
                    bookClubReplyCard.setTopRrply(false);
                    try {
                        obj2 = new JSONObject();
                        try {
                            obj2.put("index", a);
                            obj2.put("replyid", a);
                            obj2.put("placeholder", true);
                        } catch (Exception e2) {
                            e = e2;
                            com.qq.reader.common.monitor.debug.c.e("NativeServerPageOfReply", e.getMessage());
                            bookClubReplyCard.fillData(obj2);
                            list.add(bookClubReplyCard);
                            a++;
                            obj = obj2;
                        }
                    } catch (Exception e3) {
                        Exception exception = e3;
                        obj2 = obj;
                        e = exception;
                        com.qq.reader.common.monitor.debug.c.e("NativeServerPageOfReply", e.getMessage());
                        bookClubReplyCard.fillData(obj2);
                        list.add(bookClubReplyCard);
                        a++;
                        obj = obj2;
                    }
                    bookClubReplyCard.fillData(obj2);
                    list.add(bookClubReplyCard);
                }
                a++;
                obj = obj2;
            }
        }
    }

    protected JSONObject d(Bundle bundle) {
        JSONObject d = super.d(bundle);
        try {
            d.put("index", B);
        } catch (JSONException e) {
            com.qq.reader.common.monitor.debug.c.e("NativeServerPageOfReply.getFakeAddReply", e.getMessage());
        }
        return d;
    }

    public synchronized void f(String str) {
        Object obj;
        com.qq.reader.module.bookstore.qnative.card.a aVar;
        com.qq.reader.module.bookstore.qnative.card.a aVar2;
        Exception e;
        Object obj2 = null;
        synchronized (this) {
            List m = m();
            com.qq.reader.module.bookstore.qnative.card.a aVar3 = (com.qq.reader.module.bookstore.qnative.card.a) this.l.get(str);
            if (aVar3 == null || !(aVar3 instanceof BookClubReplyCard)) {
                obj = null;
            } else {
                BookClubReplyCard bookClubReplyCard = (BookClubReplyCard) aVar3;
                if (bookClubReplyCard.isBestReply()) {
                    com.qq.reader.module.bookstore.qnative.card.a aVar4 = (com.qq.reader.module.bookstore.qnative.card.a) this.l.get("topreply");
                    aVar = (com.qq.reader.module.bookstore.qnative.card.a) this.l.get(str + BookClubReplyCard.IS_TOPREPLY);
                    aVar2 = aVar4;
                } else {
                    aVar = null;
                    aVar2 = null;
                }
                if (!bookClubReplyCard.isFakeCard()) {
                    Object jSONObject;
                    BookClubReplyCard bookClubReplyCard2 = new BookClubReplyCard(this, "BookClubReplyCard", this.w);
                    bookClubReplyCard2.setTopRrply(false);
                    try {
                        jSONObject = new JSONObject();
                        try {
                            jSONObject.put("index", a(bookClubReplyCard));
                            jSONObject.put("replyid", a(bookClubReplyCard));
                            jSONObject.put("placeholder", true);
                        } catch (Exception e2) {
                            e = e2;
                            com.qq.reader.common.monitor.debug.c.e("NativeServerPageOfReply", e.getMessage());
                            bookClubReplyCard2.fillData(jSONObject);
                            this.k.add(bookClubReplyCard2);
                            m.remove(bookClubReplyCard);
                            obj2 = aVar;
                            obj = aVar2;
                            if (obj != null) {
                                m.remove(obj);
                            }
                            if (obj2 != null) {
                                m.remove(obj2);
                            }
                            A();
                        }
                    } catch (Exception e3) {
                        e = e3;
                        jSONObject = null;
                        com.qq.reader.common.monitor.debug.c.e("NativeServerPageOfReply", e.getMessage());
                        bookClubReplyCard2.fillData(jSONObject);
                        this.k.add(bookClubReplyCard2);
                        m.remove(bookClubReplyCard);
                        obj2 = aVar;
                        obj = aVar2;
                        if (obj != null) {
                            m.remove(obj);
                        }
                        if (obj2 != null) {
                            m.remove(obj2);
                        }
                        A();
                    }
                    bookClubReplyCard2.fillData(jSONObject);
                    this.k.add(bookClubReplyCard2);
                }
                m.remove(bookClubReplyCard);
                obj2 = aVar;
                obj = aVar2;
            }
            if (obj != null) {
                m.remove(obj);
            }
            if (obj2 != null) {
                m.remove(obj2);
            }
            A();
        }
    }

    private boolean a(int i, List<com.qq.reader.module.bookstore.qnative.card.a> list) {
        for (com.qq.reader.module.bookstore.qnative.card.a aVar : list) {
            if (i == a((BookClubReplyCard) aVar)) {
                return true;
            }
        }
        return false;
    }

    private int a(BookClubReplyCard bookClubReplyCard) {
        return ((l) bookClubReplyCard.getItemList().get(0)).h();
    }

    public void b(Bundle bundle) {
        com.qq.reader.module.bookstore.qnative.card.a aVar;
        BookClubReplyCard bookClubReplyCard;
        String string = bundle.getString(BookClubReplyCard.REPLY_ID);
        int i = bundle.getInt("TOP", 1);
        List<com.qq.reader.module.bookstore.qnative.card.a> m = m();
        Object obj = null;
        BookClubReplyCard bookClubReplyCard2 = null;
        for (com.qq.reader.module.bookstore.qnative.card.a aVar2 : m) {
            if (aVar2 instanceof BookClubReplyCard) {
                bookClubReplyCard = (BookClubReplyCard) aVar2;
                if (bookClubReplyCard.isTopReply()) {
                    obj = bookClubReplyCard;
                    break;
                }
            }
        }
        for (com.qq.reader.module.bookstore.qnative.card.a aVar22 : m) {
            if (aVar22 instanceof BookClubReplyCard) {
                bookClubReplyCard = (BookClubReplyCard) aVar22;
                if (bookClubReplyCard.isBestReply()) {
                    bookClubReplyCard.setBestReply(0);
                    bookClubReplyCard = bookClubReplyCard2;
                } else if (string.equals(bookClubReplyCard.getReplyid())) {
                    bookClubReplyCard.setBestReply(1);
                }
                bookClubReplyCard2 = bookClubReplyCard;
            }
            bookClubReplyCard = bookClubReplyCard2;
            bookClubReplyCard2 = bookClubReplyCard;
        }
        if (i == 0) {
            if (this.k.indexOf(obj) > -1) {
                aVar22 = (com.qq.reader.module.bookstore.qnative.card.a) this.l.get("topreply");
                this.k.remove(aVar22);
                this.l.remove(aVar22);
                this.k.remove(obj);
                this.l.remove(obj);
            }
        } else if (i == 1) {
            int indexOf = this.k.indexOf(obj);
            if (bookClubReplyCard2 != null) {
                if (indexOf < 0) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("title", "神回复");
                        jSONObject.put("type", "topreply");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    aVar22 = new BookClubTitleCard(this, "BookClubTitleCard", this.w);
                    aVar22.fillData(jSONObject);
                    aVar22.setUILevel((float) D);
                    aVar22.setEventListener(l());
                    this.k.add(1, aVar22);
                    this.l.put(aVar22.getCardId(), aVar22);
                } else {
                    this.k.remove(obj);
                    this.l.remove(obj);
                }
                l lVar = (l) bookClubReplyCard2.getItemList().get(0);
                long timeInMillis = Calendar.getInstance().getTimeInMillis();
                string = lVar.g;
                String str = lVar.f;
                String str2 = lVar.b;
                long j = lVar.i;
                k.c(lVar.d);
                JSONObject jSONObject2 = new JSONObject();
                JSONObject jSONObject3 = new JSONObject();
                try {
                    jSONObject2.put("createTimeStamp", timeInMillis);
                    jSONObject2.put("commentid", string);
                    jSONObject2.put("lastreplytime", lVar.e);
                    jSONObject2.put("createtime", lVar.d);
                    jSONObject2.put("disagree", 0);
                    jSONObject2.put(MessageKey.MSG_CONTENT, str2);
                    jSONObject2.put("replyid", str);
                    jSONObject2.put("status", 6);
                    jSONObject2.put("bid", j);
                    if (com.qq.reader.common.login.c.b()) {
                        jSONObject3.put("uid", lVar.a.g);
                        jSONObject3.put("userlevel", lVar.a.c);
                        jSONObject3.put("nickname", lVar.a.a);
                        jSONObject3.put(MessageKey.MSG_ICON, lVar.a.b);
                        jSONObject3.put("fanslevel", lVar.a.f);
                        jSONObject3.put("isauthor", lVar.a.h);
                    }
                    jSONObject2.put("user", jSONObject3);
                    jSONObject2.put("agree", 0);
                    jSONObject2.put("top", 1);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                bookClubReplyCard = new BookClubReplyCard(this, "BookClubReplyCard", this.w);
                bookClubReplyCard.setTopRrply(true);
                bookClubReplyCard.fillData(jSONObject2);
                bookClubReplyCard.setUILevel((float) E);
                bookClubReplyCard.setEventListener(l());
                bookClubReplyCard.mCommentUid = bookClubReplyCard2.mCommentUid;
                this.k.add(2, bookClubReplyCard);
                this.l.put(bookClubReplyCard.getCardId(), bookClubReplyCard);
            }
        }
    }

    public Class c() {
        return NativePageFragmentOfReply.class;
    }

    public boolean s() {
        return Math.abs(this.N) <= this.O;
    }

    public boolean u() {
        return D() == 0;
    }

    public boolean addMore(com.qq.reader.module.bookstore.qnative.a aVar) {
        ao aoVar = (ao) aVar;
        Collection m = aoVar.m();
        if (m.size() <= 0) {
            return false;
        }
        this.k.addAll(m);
        this.l.putAll(aoVar.l);
        this.o = aoVar.o;
        int i = ((ao) aVar).Q;
        int i2 = ((ao) aVar).P;
        if (i > 0 && i > this.Q) {
            this.Q = i;
        }
        if (i2 > 0 && i2 < this.P) {
            this.P = i2;
        }
        this.M = this.P;
        this.N = 20;
        return true;
    }

    public void G() {
        Collections.sort(this.k, new a(this));
        if (com.qq.reader.appconfig.b.f) {
            com.qq.reader.common.monitor.debug.c.e("NativeServerPageOfReply", MessageKey.MSG_ACCEPT_TIME_START);
            for (com.qq.reader.module.bookstore.qnative.card.a aVar : this.k) {
                com.qq.reader.common.monitor.debug.c.e("NativeServerPageOfReply", aVar.getClass().getSimpleName() + "UI level : " + aVar.getUILevel());
            }
            com.qq.reader.common.monitor.debug.c.e("NativeServerPageOfReply", MessageKey.MSG_ACCEPT_TIME_END);
        }
    }

    public void H() {
        Object obj;
        I();
        if (this.P > 2) {
            ReplyLoadMoreCard replyLoadMoreCard = new ReplyLoadMoreCard(this, ReplyLoadMoreCard.LOADPRE, this.P, -20, this.w);
            replyLoadMoreCard.setUILevel((float) (L + 1));
            replyLoadMoreCard.setEventListener(l());
            this.k.add(replyLoadMoreCard);
            obj = 1;
        } else {
            obj = null;
        }
        int i = 1;
        while (i < this.k.size()) {
            if (((com.qq.reader.module.bookstore.qnative.card.a) this.k.get(i)).getUILevel() > 0.0f) {
                break;
            }
            i++;
        }
        i = 1;
        int i2 = i + 1;
        Object obj2 = obj;
        while (i2 < this.k.size()) {
            com.qq.reader.module.bookstore.qnative.card.a aVar = (com.qq.reader.module.bookstore.qnative.card.a) this.k.get(i2);
            com.qq.reader.module.bookstore.qnative.card.a aVar2 = (com.qq.reader.module.bookstore.qnative.card.a) this.k.get(i2 - 1);
            if ((aVar instanceof BookClubReplyCard) && (aVar2 instanceof BookClubReplyCard)) {
                if (((BookClubReplyCard) aVar).isFakeCard() || ((BookClubReplyCard) aVar2).isFakeCard()) {
                    obj = 1;
                    i2++;
                    obj2 = obj;
                } else {
                    int a = a((BookClubReplyCard) aVar);
                    int a2 = a((BookClubReplyCard) aVar2);
                    if (a - a2 > 1) {
                        replyLoadMoreCard = new ReplyLoadMoreCard(this, ReplyLoadMoreCard.LOADMIDDLE, a2 + 1, 20, this.w);
                        replyLoadMoreCard.setUILevel(((float) a2) + 0.1f);
                        replyLoadMoreCard.setEventListener(l());
                        this.k.add(i2, replyLoadMoreCard);
                        i2++;
                        int i3 = 1;
                        i2++;
                        obj2 = obj;
                    }
                }
            }
            obj = obj2;
            i2++;
            obj2 = obj;
        }
        if (obj2 != null) {
            G();
        }
    }

    private void I() {
        Iterator it = this.k.iterator();
        while (it.hasNext()) {
            if (((com.qq.reader.module.bookstore.qnative.card.a) it.next()) instanceof ReplyLoadMoreCard) {
                it.remove();
            }
        }
    }

    public void C() {
        BookClubTitleCard bookClubTitleCard;
        boolean z = false;
        int i = 0;
        for (com.qq.reader.module.bookstore.qnative.card.a aVar : this.k) {
            int i2;
            if (aVar == null || !(aVar instanceof BookClubReplyCard) || ((BookClubReplyCard) aVar).isPlaceholder()) {
                i2 = i;
            } else {
                i2 = i + 1;
            }
            i = i2;
        }
        com.qq.reader.module.bookstore.qnative.card.a aVar2 = (com.qq.reader.module.bookstore.qnative.card.a) this.l.get("replylist");
        if (aVar2 instanceof BookClubTitleCard) {
            bookClubTitleCard = (BookClubTitleCard) aVar2;
        } else {
            bookClubTitleCard = null;
        }
        if (bookClubTitleCard != null) {
            if (i == 0) {
                z = true;
            }
            bookClubTitleCard.setTipVisible(z);
        }
    }
}
