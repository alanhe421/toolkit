package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubCommentDetailCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubReplyCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubTitleCard;
import com.qq.reader.module.bookstore.qnative.card.impl.CommentLinkCard;
import com.qq.reader.module.bookstore.qnative.card.impl.CommentSpecLinkCard;
import com.qq.reader.module.bookstore.qnative.card.impl.MyFavorEmptyCard;
import com.qq.reader.module.bookstore.qnative.card.impl.RecommendWordsCard;
import com.qq.reader.module.bookstore.qnative.card.impl.SelectedCommentBookInfoCard;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeServerSelectedCommentDetailPage */
public class ba extends ao {
    public String x = null;
    public String y = null;
    public String z = null;

    public ba(Bundle bundle) {
        super(bundle);
        this.x = bundle.getString("TOPIC_ID");
    }

    public String a(Bundle bundle) {
        c cVar = new c(bundle);
        int i = cVar.a().getInt("CTYPE");
        int i2 = bundle.getInt("floor_index", 2);
        return cVar.b("nativepage/topic/detail?ctype=" + i + "&index=" + i2 + "&next=" + bundle.getInt("floor_next", 20));
    }

    public int i_() {
        return this.i.hashCode();
    }

    public void a(JSONObject jSONObject, JSONObject jSONObject2) {
        String str = null;
        this.f.putString("COMMENT_ID", this.v.a());
        this.f.putString("PARA_TYPE_COMMENT_UID", this.v.b());
        this.f.putLong("URL_BUILD_PERE_BOOK_ID", this.e);
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
            this.z = jSONObject2.getString("topictitle");
            this.u = jSONObject2.optString("commentuid");
            String string = jSONObject.getString("type");
            String toLowerCase = string.toLowerCase();
            a recommendWordsCard;
            if ("intro".equalsIgnoreCase(toLowerCase)) {
                recommendWordsCard = new RecommendWordsCard(this, "RecommendWordsCard");
                recommendWordsCard.fillData(jSONObject2.optJSONObject(string));
                recommendWordsCard.setUILevel((float) C);
                recommendWordsCard.setEventListener(l());
                this.y = ((RecommendWordsCard) recommendWordsCard).mContent;
                this.k.add(recommendWordsCard);
                this.l.put(recommendWordsCard.getCardId(), recommendWordsCard);
            } else if ("comment".equalsIgnoreCase(toLowerCase)) {
                recommendWordsCard = new BookClubCommentDetailCard(this, "BookClubTopicCard", this.f, this.w);
                recommendWordsCard.setUILevel((float) D);
                recommendWordsCard.fillData(jSONObject2.optJSONObject(string));
                recommendWordsCard.setEventListener(l());
                if (this.f != null && this.f.containsKey("ISMAXREWARD")) {
                    ((BookClubCommentDetailCard) recommendWordsCard).mIsMaxReward = this.f.getBoolean("ISMAXREWARD");
                }
                a(recommendWordsCard);
                this.v.a(((BookClubCommentDetailCard) recommendWordsCard).getCommentId());
                this.v.b(((BookClubCommentDetailCard) recommendWordsCard).getCommentUid());
                this.e = ((BookClubCommentDetailCard) recommendWordsCard).mBookid;
                this.t = ((BookClubCommentDetailCard) recommendWordsCard).mBookName;
                this.s = ((BookClubCommentDetailCard) recommendWordsCard).mContent;
                JSONObject optJSONObject = jSONObject2.optJSONObject("comment");
                if (optJSONObject != null) {
                    this.b = optJSONObject.getInt("agreestatus");
                    this.a = optJSONObject.getInt("agree");
                }
                this.d = jSONObject2.getInt("replycount");
            } else if ("book".equalsIgnoreCase(toLowerCase)) {
                recommendWordsCard = new SelectedCommentBookInfoCard(this, "SelectedCommentBookInfoCard", this.f, this.w);
                recommendWordsCard.fillData(jSONObject2.optJSONObject(string));
                recommendWordsCard.setUILevel((float) E);
                recommendWordsCard.setEventListener(l());
                this.e = ((SelectedCommentBookInfoCard) recommendWordsCard).getBookId();
                this.k.add(recommendWordsCard);
                this.l.put(recommendWordsCard.getCardId(), recommendWordsCard);
            } else if ("gotodetail".equalsIgnoreCase(toLowerCase)) {
                r2 = new CommentLinkCard(this, "CommentLinkCard", this.w);
                r2.fillData(jSONObject2.optJSONObject(string));
                r2.setUILevel((float) F);
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
                r2.setUILevel((float) G);
                r2.setEventListener(l());
                this.k.add(r2);
                this.l.put(r2.getCardId(), r2);
            } else if ("topreply".equalsIgnoreCase(toLowerCase)) {
                r2 = new BookClubTitleCard(this, "BookClubTitleCard", this.w);
                r2.setUILevel((float) H);
                r2.fillData(jSONObject);
                r2.setEventListener(l());
                this.k.add(r2);
                this.l.put(r2.getCardId(), r2);
                try {
                    toLowerCase = jSONObject2.optJSONObject("comment").optJSONObject("user").optString("uid");
                } catch (Exception e) {
                    toLowerCase = str;
                }
                recommendWordsCard = new BookClubReplyCard(this, "BookClubReplyCard", this.w);
                recommendWordsCard.setUILevel((float) I);
                ((BookClubReplyCard) recommendWordsCard).setTopRrply(true);
                recommendWordsCard.fillData(jSONObject2.optJSONObject(string));
                recommendWordsCard.setEventListener(l());
                this.k.add(recommendWordsCard);
                this.l.put(recommendWordsCard.getCardId(), recommendWordsCard);
                ((BookClubReplyCard) recommendWordsCard).mCommentUid = toLowerCase;
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
                    bookClubTitleCard.setUILevel((float) J);
                    bookClubTitleCard.fillData(jSONObject);
                    bookClubTitleCard.setEventListener(l());
                    this.k.add(bookClubTitleCard);
                    this.l.put(bookClubTitleCard.getCardId(), bookClubTitleCard);
                    if (optJSONArray != null && optJSONArray.length() == 0) {
                        bookClubTitleCard.setTipVisible(true);
                    }
                }
                if (optJSONArray != null) {
                    Collection arrayList = new ArrayList();
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
                    b((List) arrayList);
                    this.k.addAll(arrayList);
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void a(b bVar) {
        if (bVar instanceof ba) {
            super.a(bVar);
            ba baVar = (ba) bVar;
            this.y = baVar.y;
            this.z = baVar.z;
            this.x = baVar.x;
        }
    }
}
