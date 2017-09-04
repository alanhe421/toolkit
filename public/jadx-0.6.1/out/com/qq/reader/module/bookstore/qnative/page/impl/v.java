package com.qq.reader.module.bookstore.qnative.page.impl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.qq.reader.common.login.c;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.f;
import com.qq.reader.common.readertask.protocol.PostTopicTask;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubBookInfoCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubCommentDetailCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubFailedTopicCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubNoReplyTipCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubTitleCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubTopicCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookclubRouteCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookclubSpecilaCommentListCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookclubTopCommentListCard;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentOfClub;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeServerBookClubPage */
public class v extends af {
    protected int a = -1;
    private long b = 0;
    private ArrayList<a> c = new ArrayList();

    public v(Bundle bundle) {
        super(bundle);
        this.b = bundle.getLong("URL_BUILD_PERE_BOOK_ID");
        this.a = bundle.getInt("CTYPE");
    }

    public boolean u() {
        for (a aVar : this.k) {
            if (aVar instanceof BookClubTopicCard) {
                return false;
            }
        }
        return true;
    }

    protected void a(JSONObject jSONObject, JSONObject jSONObject2) {
        boolean z = true;
        super.a(jSONObject, jSONObject2);
        a aVar = null;
        try {
            String string = jSONObject.getString("type");
            String toLowerCase = string.toLowerCase();
            a bookClubCommentDetailCard;
            if ("commentbook".equalsIgnoreCase(toLowerCase)) {
                bookClubCommentDetailCard = new BookClubCommentDetailCard(this, "BookClubTopicCard", this.f, this.a);
                bookClubCommentDetailCard.fillData(jSONObject2.optJSONObject(string));
                bookClubCommentDetailCard.setEventListener(l());
                if (this.f != null && this.f.containsKey("ISMAXREWARD")) {
                    ((BookClubCommentDetailCard) bookClubCommentDetailCard).mIsMaxReward = this.f.getBoolean("ISMAXREWARD");
                }
            } else if ("maxreward".equalsIgnoreCase(toLowerCase)) {
                aVar = new BookClubTitleCard(this, "BookClubTitleCard", this.a);
                aVar.fillData(jSONObject);
                aVar.setEventListener(l());
                this.k.add(aVar);
                aVar.setIndexOnPage(this.k.size() - 1);
                this.l.put(aVar.getCardId(), aVar);
                ((BookClubTitleCard) aVar).setDividerVisible(false);
                ((BookClubTitleCard) aVar).setBackgroundColor(R.color.bookstore_tiitle_bg);
                ((BookClubTitleCard) aVar).setTitleHeight((int) l().getFromActivity().getResources().getDimension(R.dimen.title_height_48));
                ((BookClubTitleCard) aVar).setTitlePaddingTop((int) l().getFromActivity().getResources().getDimension(R.dimen.common_dp_8));
                aVar = new BookClubTopicCard(this, "BookClubTopicCard", 1003, this.a);
                ((BookClubTopicCard) aVar).setIsMaxReward(true);
                aVar.fillData(jSONObject2.optJSONObject(string));
                aVar.setCardId(aVar.getCardId() + "maxreward");
                aVar.setEventListener(l());
                this.k.add(aVar);
                this.l.put(aVar.getCardId(), aVar);
            } else {
                if ("book".equals(toLowerCase)) {
                    aVar = new BookClubBookInfoCard(this, "BookClubBookInfoCard");
                    ((BookClubBookInfoCard) aVar).setCtype(this.a);
                } else if ("routelist".equals(toLowerCase)) {
                    aVar = new BookclubRouteCard(this, "BookclubRouteCard", this.a);
                    aVar.mFromBid = this.b;
                    BookclubRouteCard bookclubRouteCard = (BookclubRouteCard) aVar;
                    if (jSONObject2.has("specialcommentlist")) {
                        z = false;
                    }
                    bookclubRouteCard.isNeedBottomLine = z;
                } else if ("specialcommentlist".equals(toLowerCase)) {
                    aVar = new BookclubSpecilaCommentListCard(this, "BookclubSpecilaCommentListCard", this.a);
                    aVar.mFromBid = this.b;
                } else if ("topcommentlist".equals(toLowerCase)) {
                    aVar = new BookclubTopCommentListCard(this, "BookclubTopCommentListCard", this.a);
                    aVar.mFromBid = this.b;
                }
                if (aVar != null) {
                    if ("slide".equals(toLowerCase) || "routelist".equals(toLowerCase) || "specialcommentlist".equals(toLowerCase) || "topcommentlist".equals(toLowerCase)) {
                        aVar.fillData(jSONObject2.optJSONArray(string));
                    } else {
                        aVar.fillData(jSONObject2.optJSONObject(string));
                    }
                    aVar.setEventListener(l());
                    this.k.add(aVar);
                    this.l.put(aVar.getCardId(), aVar);
                } else if ("commentlist".equals(toLowerCase)) {
                    String optString = jSONObject2.optString("signal");
                    int optInt = jSONObject2.optInt("commentcount");
                    if (!(jSONObject.optString("title", null) == null || optString.equals("nextpage"))) {
                        aVar = new BookClubTitleCard(this, "BookClubTitleCard", this.a);
                        ((BookClubTitleCard) aVar).setTitleExData(optInt + "条");
                        ((BookClubTitleCard) aVar).setDividerVisible(false);
                        ((BookClubTitleCard) aVar).setTitleHeight((int) l().getFromActivity().getResources().getDimension(R.dimen.title_height_48));
                        ((BookClubTitleCard) aVar).setTitlePaddingTop((int) l().getFromActivity().getResources().getDimension(R.dimen.common_dp_8));
                        aVar.fillData(jSONObject);
                        aVar.setEventListener(l());
                        ((BookClubTitleCard) aVar).setBackgroundColor(R.color.bookstore_tiitle_bg);
                        this.k.add(aVar);
                        this.l.put(aVar.getCardId(), aVar);
                    }
                    JSONArray optJSONArray = jSONObject2.optJSONArray(string);
                    if (optJSONArray == null || optJSONArray.length() == 0) {
                        bookClubCommentDetailCard = new BookClubNoReplyTipCard(this, "BookClubNoReplyTipCard");
                        bookClubCommentDetailCard.fillData(new JSONObject());
                        this.k.add(bookClubCommentDetailCard);
                        this.l.put(bookClubCommentDetailCard.getCardId(), bookClubCommentDetailCard);
                        return;
                    }
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        a bookClubTopicCard = new BookClubTopicCard(this, "BookClubTopicCard", 1001, this.a);
                        bookClubTopicCard.fillData(optJSONArray.getJSONObject(i));
                        bookClubTopicCard.setEventListener(l());
                        this.k.add(bookClubTopicCard);
                        this.l.put(bookClubTopicCard.getCardId(), bookClubTopicCard);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void d(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("comment");
        a aVar = (a) this.l.get(jSONObject.optString("signal"));
        this.l.remove(aVar);
        if (aVar != null) {
            aVar.fillData(optJSONObject);
            this.l.put(aVar.getCardId(), aVar);
        }
    }

    public void a(String str, String str2, String str3) {
        try {
            if (!this.l.containsKey(str)) {
                a aVar = (a) this.l.get("BookClubNoReplyTipCard");
                if (aVar != null && this.k.contains(aVar)) {
                    this.k.remove(aVar);
                    this.l.remove("BookClubNoReplyTipCard");
                }
                a bookClubTopicCard = new BookClubTopicCard(this, "BookClubTopicCard", 1001, this.a);
                JSONObject jSONObject = new JSONObject(str2);
                if (jSONObject != null) {
                    int i;
                    bookClubTopicCard.fillData(a(jSONObject.optString(MessageKey.MSG_CONTENT), jSONObject.optString("title"), (float) jSONObject.optDouble("score", 0.0d), str3));
                    bookClubTopicCard.setCardId(str);
                    bookClubTopicCard.setEventListener(l());
                    for (int i2 = 0; i2 < this.k.size(); i2++) {
                        aVar = (a) this.k.get(i2);
                        if (aVar != null && (aVar instanceof BookClubTopicCard)) {
                            i = i2;
                            break;
                        }
                    }
                    i = -1;
                    if (i >= 0) {
                        this.k.add(i, bookClubTopicCard);
                    } else {
                        this.k.add(bookClubTopicCard);
                    }
                    this.l.put(bookClubTopicCard.getCardId(), bookClubTopicCard);
                    aVar = (a) this.l.get("BookClubBookInfoCard");
                    if (aVar != null) {
                        ((BookClubBookInfoCard) aVar).addCommentCount();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void d(String str) {
        try {
            if (this.l.containsKey(str)) {
                a aVar = (a) this.l.remove(str);
                if (aVar != null) {
                    this.k.remove(aVar);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JSONObject a(String str, String str2, float f, String str3) {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        String str4 = "";
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("createTimeStamp", timeInMillis);
            jSONObject.put("commentid", str4);
            jSONObject.put("lastreplytime", 0);
            jSONObject.put("createtime", System.currentTimeMillis());
            jSONObject.put("disagree", 0);
            jSONObject.put(MessageKey.MSG_CONTENT, str);
            jSONObject.put("replyid", "client_fake");
            jSONObject.put("agreestatus", -1);
            jSONObject.put("status", 6);
            jSONObject.put("bid", str3);
            jSONObject.put("title", str2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("score", (double) f);
            jSONObject.put("scoreInfo", jSONObject3);
            com.qq.reader.common.login.b.a c = c.c();
            if (c.b()) {
                jSONObject2.put("uid", c.c());
                jSONObject2.put("nickname", c.a());
                jSONObject2.put(MessageKey.MSG_ICON, c.b());
                jSONObject2.put("userlevel", -1);
                jSONObject2.put("fanslevel", -1);
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

    public boolean a() {
        return false;
    }

    public void q() {
        super.q();
        t();
    }

    private void x() {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            this.k.remove(aVar);
            this.l.remove(aVar.getCardId());
        }
        this.c.clear();
    }

    public void t() {
        if ("bookclubmain".equals(this.f.getString("KEY_JUMP_PAGENAME")) || "discovery_comment_detail".equals(this.f.getString("KEY_JUMP_PAGENAME"))) {
            x();
            ArrayList arrayList = new ArrayList();
            arrayList = f.b().c(new PostTopicTask(new com.qq.reader.common.emotion.a.a(null), String.valueOf(this.b), this.a));
            com.qq.reader.common.monitor.debug.c.a("fail", "addFailedcard " + arrayList.size());
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ReaderTask readerTask = (ReaderTask) it.next();
                BookClubFailedTopicCard bookClubFailedTopicCard = new BookClubFailedTopicCard(this, "BookClubFailedTopicCard", this.a);
                com.qq.reader.common.monitor.debug.c.e("Native", "=addTempCards=" + readerTask.getTaskKey());
                if (readerTask instanceof PostTopicTask) {
                    bookClubFailedTopicCard.setErrorMsg(((PostTopicTask) readerTask).getErrorMsg());
                }
                bookClubFailedTopicCard.setCardId(readerTask.getTaskKey());
                bookClubFailedTopicCard.setTask(readerTask, this.b);
                bookClubFailedTopicCard.setEventListener(l());
                this.k.add(0, bookClubFailedTopicCard);
                this.l.put(bookClubFailedTopicCard.getCardId(), bookClubFailedTopicCard);
                this.c.add(bookClubFailedTopicCard);
            }
        }
    }

    public void a(int i, int i2, Intent intent, Handler handler) {
        if (i == Constants.ERRORCODE_UNKNOWN) {
            if (i2 == -1) {
                Bundle extras = intent.getExtras();
                String string = extras.getString("operation_comment_action");
                if (!TextUtils.isEmpty(extras.getString("operation_comment_id"))) {
                    String[] strArr = new String[]{extras.getString("operation_comment_id"), extras.getString("operation_comment_id") + "maxreward"};
                    for (Object obj : strArr) {
                        a aVar = (a) this.l.get(obj);
                        if (aVar != null && (aVar instanceof BookClubTopicCard)) {
                            int i3;
                            if (string.equalsIgnoreCase("operation_comment_action_del")) {
                                Object obj2;
                                this.k.remove(aVar);
                                this.l.remove(aVar);
                                aVar = (a) this.l.get("BookClubBookInfoCard");
                                i3 = extras.getInt("operation_commentcount_action");
                                if (aVar != null) {
                                    if (i3 == 1) {
                                        ((BookClubBookInfoCard) aVar).addCommentCount();
                                    } else if (i3 == 2) {
                                        ((BookClubBookInfoCard) aVar).minusCommentCount();
                                    }
                                }
                                for (a aVar2 : this.k) {
                                    if (aVar2 != null && (aVar2 instanceof BookClubTopicCard)) {
                                        obj2 = 1;
                                        break;
                                    }
                                }
                                obj2 = null;
                                if (obj2 == null) {
                                    aVar2 = new BookClubNoReplyTipCard(this, "BookClubNoReplyTipCard");
                                    aVar2.fillData(new JSONObject());
                                    this.k.add(aVar2);
                                    this.l.put(aVar2.getCardId(), aVar2);
                                }
                                handler.sendMessage(handler.obtainMessage(7000002));
                            } else if (string.equalsIgnoreCase("operation_comment_action_edit")) {
                                BookClubTopicCard bookClubTopicCard = (BookClubTopicCard) aVar2;
                                i3 = extras.getInt("operation_comment_action_edit_agree");
                                if (i3 > -1) {
                                    bookClubTopicCard.mAgree = i3;
                                }
                                i3 = extras.getInt("operation_comment_action_edit_agreestatus");
                                if (i3 > -10) {
                                    bookClubTopicCard.mAgreeStatus = i3;
                                }
                                i3 = extras.getInt("operation_comment_action_edit_reply");
                                if (i3 > -1) {
                                    bookClubTopicCard.mReplycount = i3;
                                }
                                handler.sendMessage(handler.obtainMessage(7000002));
                            }
                        }
                    }
                }
            }
        } else if (i == 60000) {
            handler.sendMessage(handler.obtainMessage(7000002));
        }
    }

    public Class c() {
        return NativePageFragmentOfClub.class;
    }

    public boolean b() {
        return true;
    }
}
