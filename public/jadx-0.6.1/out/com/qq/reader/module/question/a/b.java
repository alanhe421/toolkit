package com.qq.reader.module.question.a;

import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubReplyCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubTitleCard;
import com.qq.reader.module.bookstore.qnative.card.impl.MyFavorEmptyCard;
import com.qq.reader.module.bookstore.qnative.card.impl.ReplyLoadMoreCard;
import com.qq.reader.module.bookstore.qnative.item.l;
import com.qq.reader.module.bookstore.qnative.page.impl.af;
import com.qq.reader.module.question.card.AudioQuestionAuthorCard;
import com.qq.reader.module.question.card.AudioQuestionDetailAnsweredCard;
import com.qq.reader.module.question.card.AudioQuestionStateCard;
import com.qq.reader.module.question.card.AudioQuestionWaiting4AnswerCard;
import com.qq.reader.module.question.card.AudioSingleBookCard;
import com.qq.reader.module.question.data.AudioData;
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

/* compiled from: NativeServerPageOfAudioQutionDetail */
public class b extends af {
    public static final int A = (s + 7);
    public static final int B = (s + 8);
    public static final String a = b.class.getSimpleName();
    public static int s = -100;
    public static int t = 2147482647;
    public static final int u = (s + 1);
    public static final int v = (s + 2);
    public static final int w = (s + 3);
    public static final int x = (s + 4);
    public static final int y = (s + 5);
    public static final int z = (s + 6);
    public int C = 1;
    public int D = 20;
    public int E = 0;
    public int F = -1;
    public int G = -1;
    public boolean H = false;
    public int I = 8;
    public String J;
    public int b = -1;
    public String c = null;
    public AudioData d;
    int e = 0;

    /* compiled from: NativeServerPageOfAudioQutionDetail */
    private class a implements Comparator<com.qq.reader.module.bookstore.qnative.card.a> {
        final /* synthetic */ b a;

        private a(b bVar) {
            this.a = bVar;
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

    public b(Bundle bundle) {
        super(bundle);
        this.e = bundle.getInt("audio_answered", 0);
        this.d = new AudioData();
        this.d.a(this.e);
        this.C = bundle.getInt("floor_index", 2);
        this.D = bundle.getInt("floor_next", 20);
        this.o = -1;
        bundle.putLong("KEY_PAGEINDEX", this.o);
        this.H = bundle.getBoolean("page_replyloadpre");
        this.J = bundle.getString("URL_BUILD_PERE_SIGNAL");
    }

    public String a(Bundle bundle) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(e.cx);
        stringBuilder.append("?qid=").append(bundle.getString("audio_questionid"));
        int i = bundle.getInt("floor_index", 2);
        int i2 = bundle.getInt("floor_next", 20);
        stringBuilder.append("&signal=").append(bundle.getString("URL_BUILD_PERE_SIGNAL", ""));
        stringBuilder.append("&ctype=").append(8);
        stringBuilder.append("&index=").append(i);
        stringBuilder.append("&next=").append(i2);
        return stringBuilder.toString();
    }

    public void a(com.qq.reader.module.bookstore.qnative.page.b bVar) {
        super.a(bVar);
        b bVar2 = (b) bVar;
        this.d = bVar2.d;
        this.b = bVar2.b;
        this.C = bVar2.C;
        this.D = bVar2.D;
        this.E = bVar2.E;
        this.F = bVar2.F;
        this.G = bVar2.G;
        this.H = bVar2.H;
    }

    public void b(JSONObject jSONObject) {
        JSONObject jSONObject2 = null;
        try {
            jSONObject2 = jSONObject.optJSONObject("qanode");
            this.b = jSONObject2.optJSONObject("question").optInt("status", -1);
            Object obj = jSONObject2;
        } catch (Exception e) {
            c.e(a, e.getMessage());
            JSONObject jSONObject3 = jSONObject2;
        }
        try {
            if (this.b == -1) {
                MyFavorEmptyCard myFavorEmptyCard = new MyFavorEmptyCard(this, "MyFavourEmpty");
                MyFavorEmptyCard myFavorEmptyCard2 = myFavorEmptyCard;
                myFavorEmptyCard2.setText("内容不存在或已删除");
                myFavorEmptyCard2.setImage(R.drawable.empty08);
                if (this.k.size() == 0) {
                    this.k.add(myFavorEmptyCard);
                }
            } else if (this.b == 1) {
                JSONArray optJSONArray;
                int i;
                String optString = jSONObject.optString("signal");
                if (!"nextpage".equals(optString)) {
                    if (obj != null) {
                        r3 = new AudioQuestionDetailAnsweredCard(this, "", jSONObject.optInt("owner"));
                        r3.fillData(obj);
                        r3.setEventListener(l());
                        r3.setUILevel((float) u);
                        this.k.add(r3);
                        this.l.put(r3.getCardId(), r3);
                        ((AudioQuestionDetailAnsweredCard) r3).setAutoPlay(this.f.getInt("audio_auto_play"));
                        this.f.remove("audio_auto_play");
                        this.d = ((AudioQuestionDetailAnsweredCard) r3).getAudioData();
                    }
                    JSONObject optJSONObject = jSONObject.optJSONObject("authornode");
                    if (optJSONObject != null) {
                        r3 = new AudioQuestionAuthorCard(this, "");
                        r3.fillData(optJSONObject);
                        r3.setEventListener(l());
                        r3.setUILevel((float) v);
                        this.k.add(r3);
                        this.l.put(r3.getCardId(), r3);
                    }
                    optJSONArray = jSONObject.optJSONArray("booklist");
                    if (optJSONArray != null) {
                        int length = optJSONArray.length();
                        for (i = 0; i < length; i++) {
                            com.qq.reader.module.bookstore.qnative.card.a audioSingleBookCard = new AudioSingleBookCard(this, "");
                            audioSingleBookCard.fillData(optJSONArray.get(i));
                            audioSingleBookCard.setEventListener(l());
                            audioSingleBookCard.setUILevel((float) w);
                            this.k.add(audioSingleBookCard);
                            this.l.put(audioSingleBookCard.getCardId(), audioSingleBookCard);
                        }
                    }
                }
                optJSONArray = jSONObject.optJSONArray("replylist");
                if (!"nextpage".equals(optString)) {
                    BookClubTitleCard bookClubTitleCard = new BookClubTitleCard(this, "BookClubTitleCard", this.I);
                    bookClubTitleCard.setTitle("全部回复");
                    bookClubTitleCard.setDataReady();
                    bookClubTitleCard.setEventListener(l());
                    bookClubTitleCard.setUILevel((float) x);
                    this.k.add(bookClubTitleCard);
                    this.l.put(bookClubTitleCard.getCardId(), bookClubTitleCard);
                    if (optJSONArray != null && optJSONArray.length() == 0) {
                        bookClubTitleCard.setTipVisible(true);
                    }
                }
                if (optJSONArray != null) {
                    List arrayList = new ArrayList();
                    for (i = 0; i < optJSONArray.length(); i++) {
                        BookClubReplyCard bookClubReplyCard = new BookClubReplyCard(this, "BookClubReplyCard", this.I);
                        bookClubReplyCard.setTopRrply(false);
                        bookClubReplyCard.fillData(optJSONArray.getJSONObject(i));
                        bookClubReplyCard.setEventListener(l());
                        arrayList.add(bookClubReplyCard);
                        this.l.put(bookClubReplyCard.getCardId(), bookClubReplyCard);
                    }
                    this.E = optJSONArray.length();
                    b(arrayList);
                    this.k.addAll(arrayList);
                }
            } else if (obj != null) {
                r3 = new AudioQuestionWaiting4AnswerCard(this);
                ((AudioQuestionWaiting4AnswerCard) r3).setEavesDroppingMsg(jSONObject.optString("eavesDroppingMsg"));
                r3.fillData(obj);
                r3.setEventListener(l());
                this.k.add(r3);
                this.l.put(r3.getCardId(), r3);
                this.d = ((AudioQuestionWaiting4AnswerCard) r3).getAudioData();
                this.c = this.d.a().k();
                long l = com.qq.reader.common.login.c.c().l(ReaderApplication.getApplicationImp());
                if (this.b != 0 || (this.d.b() != null && l != this.d.b().f())) {
                    com.qq.reader.module.bookstore.qnative.card.a audioQuestionStateCard = new AudioQuestionStateCard(this, this.b, this.c, this.d.b().g());
                    audioQuestionStateCard.setEventListener(l());
                    this.k.add(audioQuestionStateCard);
                    this.l.put(audioQuestionStateCard.getCardId(), audioQuestionStateCard);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public boolean s() {
        return Math.abs(this.D) <= this.E;
    }

    public boolean addMore(com.qq.reader.module.bookstore.qnative.a aVar) {
        b bVar = (b) aVar;
        Collection m = bVar.m();
        if (m.size() > 0) {
            this.k.addAll(m);
            this.l.putAll(bVar.l);
            this.o = bVar.o;
            int i = ((b) aVar).G;
            int i2 = ((b) aVar).F;
            if (i > 0 && i > this.G) {
                this.G = i;
            }
            if (i2 > 0 && i2 < this.F) {
                this.F = i2;
            }
            this.C = this.F;
            this.D = 20;
        }
        return true;
    }

    public boolean a() {
        return false;
    }

    protected void b(List<com.qq.reader.module.bookstore.qnative.card.a> list) {
        int a;
        Exception e;
        if (list.size() != 0) {
            if (this.D > 0) {
                this.F = this.C;
                this.G = a((BookClubReplyCard) list.get(list.size() - 1));
            } else {
                int i;
                a = a((BookClubReplyCard) list.get(0));
                if (this.C + this.D <= 2) {
                    i = 2;
                } else {
                    i = this.C + this.D;
                }
                this.F = i;
                this.F = Math.min(this.F, a);
                this.G = this.C;
            }
            Object obj = null;
            a = this.F;
            while (a < this.G) {
                Object obj2;
                if (a(a, list)) {
                    obj2 = obj;
                } else {
                    BookClubReplyCard bookClubReplyCard = new BookClubReplyCard(this, "BookClubReplyCard", this.I);
                    bookClubReplyCard.setTopRrply(false);
                    try {
                        obj2 = new JSONObject();
                        try {
                            obj2.put("index", a);
                            obj2.put("replyid", a);
                            obj2.put("placeholder", true);
                        } catch (Exception e2) {
                            e = e2;
                            c.e(a, e.getMessage());
                            bookClubReplyCard.fillData(obj2);
                            list.add(bookClubReplyCard);
                            a++;
                            obj = obj2;
                        }
                    } catch (Exception e3) {
                        Exception exception = e3;
                        obj2 = obj;
                        e = exception;
                        c.e(a, e.getMessage());
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

    public void x() {
        Collections.sort(this.k, new a());
    }

    public void y() {
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

    public void z() {
        Object obj = null;
        C();
        if (this.F > 2) {
            ReplyLoadMoreCard replyLoadMoreCard = new ReplyLoadMoreCard(this, ReplyLoadMoreCard.LOADPRE, this.F, -20, this.I);
            replyLoadMoreCard.setUILevel(-1.0f);
            replyLoadMoreCard.setEventListener(l());
            this.k.add(replyLoadMoreCard);
            obj = 1;
        }
        int i = 1;
        Object obj2 = obj;
        while (i < this.k.size()) {
            Object obj3;
            com.qq.reader.module.bookstore.qnative.card.a aVar = (com.qq.reader.module.bookstore.qnative.card.a) this.k.get(i);
            com.qq.reader.module.bookstore.qnative.card.a aVar2 = (com.qq.reader.module.bookstore.qnative.card.a) this.k.get(i - 1);
            if ((aVar instanceof BookClubReplyCard) && (aVar2 instanceof BookClubReplyCard)) {
                if (((BookClubReplyCard) aVar).isFakeCard() || ((BookClubReplyCard) aVar2).isFakeCard()) {
                    obj3 = 1;
                    i++;
                    obj2 = obj3;
                } else {
                    int a = a((BookClubReplyCard) aVar);
                    int a2 = a((BookClubReplyCard) aVar2);
                    if (a - a2 > 1) {
                        replyLoadMoreCard = new ReplyLoadMoreCard(this, ReplyLoadMoreCard.LOADMIDDLE, a2 + 1, 20, this.I);
                        replyLoadMoreCard.setUILevel(((float) a2) + 0.1f);
                        replyLoadMoreCard.setEventListener(l());
                        this.k.add(i, replyLoadMoreCard);
                        i++;
                        int i2 = 1;
                        i++;
                        obj2 = obj3;
                    }
                }
            }
            obj3 = obj2;
            i++;
            obj2 = obj3;
        }
        if (obj2 != null) {
            x();
        }
    }

    private void C() {
        Iterator it = this.k.iterator();
        while (it.hasNext()) {
            if (((com.qq.reader.module.bookstore.qnative.card.a) it.next()) instanceof ReplyLoadMoreCard) {
                it.remove();
            }
        }
    }

    public int A() {
        return this.G;
    }

    public synchronized void d(String str) {
        Object jSONObject;
        Exception e;
        List m = m();
        com.qq.reader.module.bookstore.qnative.card.a aVar = (com.qq.reader.module.bookstore.qnative.card.a) this.l.get(str);
        if (aVar != null && (aVar instanceof BookClubReplyCard)) {
            BookClubReplyCard bookClubReplyCard = (BookClubReplyCard) aVar;
            if (!bookClubReplyCard.isFakeCard()) {
                BookClubReplyCard bookClubReplyCard2 = new BookClubReplyCard(this, "BookClubReplyCard", this.I);
                bookClubReplyCard2.setTopRrply(false);
                try {
                    jSONObject = new JSONObject();
                    try {
                        jSONObject.put("index", a(bookClubReplyCard));
                        jSONObject.put("replyid", a(bookClubReplyCard));
                        jSONObject.put("placeholder", true);
                    } catch (Exception e2) {
                        e = e2;
                        c.e("NativeServerPageOfReply", e.getMessage());
                        bookClubReplyCard2.fillData(jSONObject);
                        this.k.add(bookClubReplyCard2);
                        m.remove(bookClubReplyCard);
                    }
                } catch (Exception e3) {
                    e = e3;
                    jSONObject = null;
                    c.e("NativeServerPageOfReply", e.getMessage());
                    bookClubReplyCard2.fillData(jSONObject);
                    this.k.add(bookClubReplyCard2);
                    m.remove(bookClubReplyCard);
                }
                bookClubReplyCard2.fillData(jSONObject);
                this.k.add(bookClubReplyCard2);
            }
            m.remove(bookClubReplyCard);
        }
    }

    public boolean u() {
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
        if (i == 0) {
            return true;
        }
        return false;
    }

    public void B() {
        com.qq.reader.module.bookstore.qnative.card.a aVar = (com.qq.reader.module.bookstore.qnative.card.a) this.l.get("BookClubTitleCard");
        if (aVar != null && (aVar instanceof BookClubTitleCard)) {
            ((BookClubTitleCard) aVar).setTipVisible(u());
        }
    }

    public synchronized boolean d(JSONObject jSONObject) {
        boolean z;
        int size;
        BookClubReplyCard bookClubReplyCard = new BookClubReplyCard(this, "BookClubReplyCard", this.I);
        bookClubReplyCard.fillData(jSONObject);
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
        z = true;
        return z;
    }

    public static String b(long j) {
        return j + "client_fake";
    }

    public void c(Bundle bundle) {
        d(d(bundle));
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
            c.e(a, e.getMessage());
        }
        try {
            jSONObject.put("index", t);
        } catch (JSONException e2) {
            c.e(a, e2.getMessage());
        }
        return jSONObject;
    }
}
