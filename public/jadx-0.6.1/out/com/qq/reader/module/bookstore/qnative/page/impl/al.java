package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubChapterEndCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubChapterEndTitleCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubReplyCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookScoreCard;
import com.qq.reader.module.bookstore.qnative.card.impl.MyFavorEmptyCard;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentOfChapterEnd;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfLastChapter */
public class al extends ao {
    public static final int x = (A + 50);
    public static final int y = (A + 51);
    public List<a> z = new ArrayList();

    public al(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        c cVar = new c(bundle);
        int i = bundle.getInt("floor_index", 2);
        int i2 = bundle.getInt("floor_next", 20);
        return cVar.b("nativepage/comment/chapter?index=" + i + "&next=" + i2 + "&ctype=" + bundle.getInt("CTYPE"));
    }

    protected void a(JSONObject jSONObject, JSONObject jSONObject2) {
        String str = null;
        int i = 0;
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
            a bookScoreCard;
            if ("score".equals(toLowerCase)) {
                bookScoreCard = new BookScoreCard(this, "BookScoreCard", this.w);
                bookScoreCard.setUILevel((float) H);
                bookScoreCard.fillData(jSONObject2);
                bookScoreCard.setEventListener(l());
                this.k.add(bookScoreCard);
            } else if ("hotreplylist".equals(toLowerCase)) {
                this.z.clear();
                r3 = jSONObject2.optJSONObject("comment");
                if (r3 != null) {
                    r4 = r3.optJSONObject("user");
                    str = r4.optString("uid");
                    this.Y = r4.optString("uid");
                    this.X = r4.optInt(BookClubReplyCard.REPLY_USER_BLACK);
                    this.V = r3.optInt("top");
                    this.U = r3.optInt("better");
                    this.W = r3.optInt("status");
                }
                r2 = jSONObject2.optJSONArray(string);
                jSONObject2.optString("signal");
                if (jSONObject.optString("title", null) != null) {
                    r3 = new BookClubChapterEndTitleCard(this, "BookClubTitleCard", this.w);
                    r3.setUILevel((float) x);
                    r3.fillData(jSONObject);
                    r3.setEventListener(l());
                    this.z.add(r3);
                    if (r2 != null && r2.length() == 0) {
                        r3.setTipVisible(true);
                    }
                }
                if (r2 != null) {
                    r3 = new ArrayList();
                    while (i < r2.length()) {
                        r4 = new BookClubChapterEndCard(this, "BookClubReplyCard", this.w);
                        r4.setTopRrply(false);
                        r4.setUILevel((float) (y + i));
                        r4.fillData(r2.getJSONObject(i));
                        r4.setEventListener(l());
                        r3.add(r4);
                        r4.mCommentUid = str;
                        i++;
                    }
                    this.z.addAll(r3);
                }
            } else if ("replylist".equals(toLowerCase)) {
                r3 = jSONObject2.optJSONObject("comment");
                if (r3 != null) {
                    r4 = r3.optJSONObject("user");
                    str = r4.optString("uid");
                    this.Y = r4.optString("uid");
                    this.X = r4.optInt(BookClubReplyCard.REPLY_USER_BLACK);
                    this.V = r3.optInt("top");
                    this.U = r3.optInt("better");
                    this.W = r3.optInt("status");
                }
                this.d = jSONObject2.getInt("replycount");
                r2 = jSONObject2.optJSONArray(string);
                toLowerCase = jSONObject2.optString("signal");
                if (!(jSONObject.optString("title", null) == null || toLowerCase.equals("nextpage"))) {
                    r3 = new BookClubChapterEndTitleCard(this, "BookClubTitleCard", this.w);
                    r3.setUILevel((float) L);
                    r3.fillData(jSONObject);
                    r3.setEventListener(l());
                    r3.setReplyCount(this.d);
                    this.k.add(r3);
                    this.l.put(r3.getCardId(), r3);
                    if (r2 != null && r2.length() == 0) {
                        r3.setTipVisible(true);
                    }
                }
                if (r2 != null) {
                    r3 = new ArrayList();
                    while (i < r2.length()) {
                        r4 = new BookClubChapterEndCard(this, "BookClubReplyCard", this.w);
                        r4.setTopRrply(false);
                        r4.fillData(r2.getJSONObject(i));
                        r4.setEventListener(l());
                        r3.add(r4);
                        this.l.put(r4.getCardId(), r4);
                        r4.mCommentUid = str;
                        i++;
                    }
                    this.O = r2.length();
                    b((List) r3);
                    if (this.P <= 2) {
                        for (a bookScoreCard2 : this.z) {
                            if (bookScoreCard2 != null) {
                                this.k.add(bookScoreCard2);
                                this.l.put(bookScoreCard2.getCardId(), bookScoreCard2);
                            }
                        }
                    }
                    this.k.addAll(r3);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void B() {
        List arrayList = new ArrayList();
        for (int size = this.k.size() - 1; size > 0; size--) {
            a aVar = (a) this.k.get(size);
            for (int i = size - 1; i >= 0; i--) {
                if (aVar.equals((a) this.k.get(i))) {
                    arrayList.add(aVar);
                }
            }
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            this.k.remove(arrayList.get(i2));
        }
    }

    public synchronized boolean d(JSONObject jSONObject) {
        boolean z;
        int size;
        BookClubChapterEndCard bookClubChapterEndCard = new BookClubChapterEndCard(this, "BookClubReplyCard", this.w);
        bookClubChapterEndCard.fillData(jSONObject);
        bookClubChapterEndCard.setPageType(E());
        bookClubChapterEndCard.setEventListener(l());
        if (this.k.size() > 0) {
            size = this.k.size();
        } else {
            size = 0;
        }
        for (a aVar : this.k) {
            if ((aVar instanceof BookClubReplyCard) && aVar.getCardId().equals(bookClubChapterEndCard.getCardId())) {
                z = false;
                break;
            }
        }
        this.k.add(size, bookClubChapterEndCard);
        this.l.put(bookClubChapterEndCard.getCardId(), bookClubChapterEndCard);
        bookClubChapterEndCard.mCommentUid = jSONObject.optString("PARA_TYPE_COMMENT_UID");
        z();
        z = true;
        return z;
    }

    public Class c() {
        return NativePageFragmentOfChapterEnd.class;
    }

    protected void a(a aVar) {
    }
}
