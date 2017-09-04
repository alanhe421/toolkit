package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.common.utils.k;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubCommentDetailCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubReplyCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubTitleCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookScoreCard;
import com.qq.reader.module.bookstore.qnative.card.impl.MyFavorEmptyCard;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentOfClub;
import com.qq.reader.module.bookstore.qnative.item.l;
import com.qq.reader.module.bookstore.qnative.model.a.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfDiscuss */
public class ah extends af {
    public int a = -1;
    public int b = -2147483647;
    public boolean c = false;
    public int d = 0;
    public long e = -1;
    public String s;
    public String t;
    public String u;
    public a v = new a();
    protected int w;

    public ah(Bundle bundle) {
        super(bundle);
        this.w = bundle.getInt("CTYPE");
    }

    public int x() {
        return new c(this.f).a().getInt("CTYPE");
    }

    public String a(Bundle bundle) {
        c cVar = new c(bundle);
        return cVar.b("nativepage/comment/detail?ctype=" + cVar.a().getInt("CTYPE"));
    }

    public void a(b bVar) {
        if (bVar instanceof ah) {
            super.a(bVar);
            ah ahVar = (ah) bVar;
            this.v.a(ahVar.y().a());
            this.v.b(ahVar.y().b());
            this.a = ahVar.a;
            this.b = ahVar.b;
            this.d = ahVar.d;
            this.e = ahVar.e;
            this.t = ahVar.t;
            this.u = ahVar.u;
            this.s = ahVar.s;
        }
    }

    public a y() {
        return this.v;
    }

    protected void a(com.qq.reader.module.bookstore.qnative.card.a aVar) {
        this.k.add(aVar);
        this.l.put(aVar.getCardId(), aVar);
    }

    protected void a(JSONObject jSONObject, JSONObject jSONObject2) {
        String str = null;
        super.a(jSONObject, jSONObject2);
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
            this.u = jSONObject2.optString("commentuid");
            String string = jSONObject.getString("type");
            String toLowerCase = string.toLowerCase();
            com.qq.reader.module.bookstore.qnative.card.a bookClubCommentDetailCard;
            if ("comment".equalsIgnoreCase(toLowerCase)) {
                bookClubCommentDetailCard = new BookClubCommentDetailCard(this, "BookClubTopicCard", this.f, this.w);
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
                this.s = ((BookClubCommentDetailCard) bookClubCommentDetailCard).mContent;
                JSONObject optJSONObject = jSONObject2.optJSONObject("comment");
                if (optJSONObject != null) {
                    this.b = optJSONObject.getInt("agreestatus");
                    this.a = optJSONObject.getInt("agree");
                }
                this.d = jSONObject2.getInt("replycount");
            } else if ("topreply".equalsIgnoreCase(toLowerCase)) {
                r2 = new BookClubTitleCard(this, "BookClubTitleCard", this.w);
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
                ((BookClubReplyCard) bookClubCommentDetailCard).setTopRrply(true);
                bookClubCommentDetailCard.fillData(jSONObject2.optJSONObject(string));
                bookClubCommentDetailCard.setEventListener(l());
                this.k.add(bookClubCommentDetailCard);
                this.l.put(bookClubCommentDetailCard.getCardId(), bookClubCommentDetailCard);
                ((BookClubReplyCard) bookClubCommentDetailCard).mCommentUid = toLowerCase;
            } else if ("score".equals(toLowerCase)) {
                r2 = new BookScoreCard(this, "BookScoreCard", this.w);
                r2.fillData(jSONObject2);
                r2.setEventListener(l());
                this.k.add(r2);
            } else if ("replylist".equals(toLowerCase)) {
                JSONObject optJSONObject2 = jSONObject2.optJSONObject("comment");
                if (optJSONObject2 != null) {
                    str = optJSONObject2.optJSONObject("user").optString("uid");
                }
                this.d = jSONObject2.getInt("replycount");
                JSONArray optJSONArray = jSONObject2.optJSONArray(string);
                string = jSONObject2.optString("signal");
                if (!(jSONObject.optString("title", null) == null || string.equals("nextpage"))) {
                    BookClubTitleCard bookClubTitleCard = new BookClubTitleCard(this, "BookClubTitleCard", this.w);
                    bookClubTitleCard.setPageType(E());
                    bookClubTitleCard.setReplyCount(this.d);
                    bookClubTitleCard.fillData(jSONObject);
                    bookClubTitleCard.setEventListener(l());
                    this.k.add(bookClubTitleCard);
                    this.l.put(bookClubTitleCard.getCardId(), bookClubTitleCard);
                    if (optJSONArray != null && optJSONArray.length() == 0) {
                        bookClubTitleCard.setTipVisible(true);
                    }
                }
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        BookClubReplyCard bookClubReplyCard = new BookClubReplyCard(this, "BookClubReplyCard", this.w);
                        bookClubReplyCard.setTopRrply(false);
                        bookClubReplyCard.fillData(optJSONArray.getJSONObject(i));
                        bookClubReplyCard.setEventListener(l());
                        bookClubReplyCard.setPageType(E());
                        this.k.add(bookClubReplyCard);
                        this.l.put(bookClubReplyCard.getCardId(), bookClubReplyCard);
                        bookClubReplyCard.mCommentUid = str;
                    }
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public boolean u() {
        return D() == 0;
    }

    public synchronized void z() {
        this.d++;
        b(this.d);
    }

    public synchronized void A() {
        this.d--;
        b(this.d);
    }

    private void b(int i) {
        for (com.qq.reader.module.bookstore.qnative.card.a aVar : this.k) {
            if (aVar instanceof BookClubTitleCard) {
                ((BookClubTitleCard) aVar).setReplyCount(i);
            }
        }
    }

    public void c(Bundle bundle) {
        d(d(bundle));
    }

    public synchronized boolean d(JSONObject jSONObject) {
        boolean z;
        int size;
        BookClubReplyCard bookClubReplyCard = new BookClubReplyCard(this, "BookClubReplyCard", this.w);
        bookClubReplyCard.fillData(jSONObject);
        bookClubReplyCard.setPageType(E());
        bookClubReplyCard.setEventListener(l());
        if (this.k.size() > 0) {
            size = this.k.size();
        } else {
            size = 0;
        }
        for (com.qq.reader.module.bookstore.qnative.card.a aVar : this.k) {
            if ((aVar instanceof BookClubReplyCard) && aVar.getCardId().equals(bookClubReplyCard.getCardId())) {
                z = false;
                break;
            }
        }
        this.k.add(size, bookClubReplyCard);
        this.l.put(bookClubReplyCard.getCardId(), bookClubReplyCard);
        bookClubReplyCard.mCommentUid = jSONObject.optString("PARA_TYPE_COMMENT_UID");
        z();
        z = true;
        return z;
    }

    public void B() {
        List arrayList = new ArrayList();
        for (int size = this.k.size() - 1; size > 0; size--) {
            com.qq.reader.module.bookstore.qnative.card.a aVar = (com.qq.reader.module.bookstore.qnative.card.a) this.k.get(size);
            for (int i = size - 1; i >= 0; i--) {
                if (aVar.getCardId().equals(((com.qq.reader.module.bookstore.qnative.card.a) this.k.get(i)).getCardId())) {
                    arrayList.add(aVar);
                }
            }
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            this.k.remove(arrayList.get(i2));
        }
    }

    public void C() {
        BookClubTitleCard bookClubTitleCard;
        boolean z = false;
        int i = 0;
        for (com.qq.reader.module.bookstore.qnative.card.a aVar : this.k) {
            int i2;
            if (aVar == null || !(aVar instanceof BookClubReplyCard)) {
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

    public int D() {
        return this.d;
    }

    public int d(String str) {
        return this.a;
    }

    public int e(String str) {
        return this.b;
    }

    public synchronized void f(String str) {
        Object obj = null;
        synchronized (this) {
            Object obj2;
            List m = m();
            com.qq.reader.module.bookstore.qnative.card.a aVar = (com.qq.reader.module.bookstore.qnative.card.a) this.l.get(str);
            if (aVar == null || !(aVar instanceof BookClubReplyCard)) {
                obj2 = null;
            } else {
                com.qq.reader.module.bookstore.qnative.card.a aVar2;
                BookClubReplyCard bookClubReplyCard = (BookClubReplyCard) aVar;
                if (bookClubReplyCard.isBestReply()) {
                    com.qq.reader.module.bookstore.qnative.card.a aVar3 = (com.qq.reader.module.bookstore.qnative.card.a) this.l.get("topreply");
                    com.qq.reader.module.bookstore.qnative.card.a aVar4 = (com.qq.reader.module.bookstore.qnative.card.a) this.l.get(str + BookClubReplyCard.IS_TOPREPLY);
                    aVar2 = aVar3;
                    obj = aVar4;
                } else {
                    aVar2 = null;
                }
                m.remove(bookClubReplyCard);
                obj2 = obj;
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
                    com.qq.reader.common.login.c.c();
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
                bookClubReplyCard.setEventListener(l());
                bookClubReplyCard.mCommentUid = bookClubReplyCard2.mCommentUid;
                this.k.add(2, bookClubReplyCard);
                this.l.put(bookClubReplyCard.getCardId(), bookClubReplyCard);
            }
        }
        super.b(bundle);
    }

    public boolean a() {
        return false;
    }

    protected JSONObject d(Bundle bundle) {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        long j = bundle.getLong("fakereplyid");
        bundle.getString("PARA_TYPE_COMMENT_UID");
        String string = bundle.getString("COMMENT_ID");
        String string2 = bundle.getString(BookClubReplyCard.REPLY_UID);
        String string3 = bundle.getString("CONTENT");
        String string4 = bundle.getString(BookClubReplyCard.REPLY_ID);
        int i = bundle.getInt("REPLY_TYPE");
        String string5 = bundle.getString(BookClubReplyCard.BID);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("createTimeStamp", timeInMillis);
            jSONObject.put("commentid", string);
            jSONObject.put("lastreplytime", 0);
            jSONObject.put("createtime", System.currentTimeMillis());
            jSONObject.put("disagree", 0);
            jSONObject.put("replyuid", string2);
            jSONObject.put(MessageKey.MSG_CONTENT, string3);
            jSONObject.put("replyid", b(j));
            jSONObject.put("replyreplyid", string4);
            jSONObject.put("replytype", i);
            jSONObject.put("status", 6);
            jSONObject.put("agreestatus", -1);
            jSONObject.put("bid", string5);
            if (com.qq.reader.common.login.c.b()) {
                com.qq.reader.common.login.b.a c = com.qq.reader.common.login.c.c();
                jSONObject2.put("uid", c.c());
                jSONObject2.put("nickname", c.a());
                jSONObject2.put(MessageKey.MSG_ICON, c.b());
                jSONObject2.put("fanslevel", -1);
                jSONObject2.put("userlevel", -1);
                jSONObject2.put("isauthor", 0);
            }
            jSONObject.put("user", jSONObject2);
            jSONObject.put("agree", 0);
            jSONObject.put("top", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static String b(long j) {
        return j + "client_fake";
    }

    public Class c() {
        return NativePageFragmentOfClub.class;
    }

    public boolean b() {
        return true;
    }

    protected String E() {
        return this.f.getString("KEY_JUMP_PAGENAME");
    }
}
