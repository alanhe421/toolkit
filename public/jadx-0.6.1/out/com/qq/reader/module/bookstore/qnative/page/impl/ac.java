package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubTitleCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubTopicCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookclubRouteCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookclubSpecilaCommentListCard;
import com.qq.reader.module.bookstore.qnative.card.impl.BookclubTopCommentListCard;
import com.qq.reader.module.bookstore.qnative.card.impl.ColCard_Books;
import com.qq.reader.module.bookstore.qnative.card.impl.ColCard_Charts;
import com.qq.reader.module.bookstore.qnative.card.impl.DiscoveryCommentDetailCard;
import com.tencent.feedback.proguard.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeServerDiscoveryCommentDetailPage */
public class ac extends v {
    private long b = 0;

    public ac(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        c cVar = new c(bundle);
        return cVar.b("nativepage/comment/indexforcommonzone?ctype=" + cVar.a().getInt("CTYPE"));
    }

    public int i_() {
        return this.i.hashCode();
    }

    public void a(JSONObject jSONObject, JSONObject jSONObject2) {
        a aVar = null;
        try {
            String string = jSONObject.getString("type");
            String toLowerCase = string.toLowerCase();
            if ("normalcol".equals(toLowerCase)) {
                aVar = new ColCard_Books(this, "ColCard_Books");
            } else if ("rankcol".equals(toLowerCase) || "updatecol".equals(toLowerCase)) {
                aVar = new ColCard_Charts(this, "ColCard_Charts");
            }
            if (aVar != null) {
                aVar.build(jSONObject);
                aVar.setEventListener(l());
                this.k.add(aVar);
                this.l.put(aVar.getCardId(), aVar);
                return;
            }
            if ("bookforommonzone".equals(toLowerCase)) {
                aVar = new DiscoveryCommentDetailCard(this, "DiscoveryCommentDetailCard");
            } else if ("routelist".equals(toLowerCase)) {
                aVar = new BookclubRouteCard(this, "BookclubRouteCard", this.a);
                aVar.mFromBid = this.b;
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
                if ((aVar instanceof DiscoveryCommentDetailCard) && aVar.getItemList().size() > 0 && (aVar.getItemList().get(0) instanceof DiscoveryCommentDetailCard.a)) {
                    this.b = ((DiscoveryCommentDetailCard.a) ((DiscoveryCommentDetailCard) aVar).getItemList().get(0)).a;
                }
            } else if ("commentlist".equals(toLowerCase)) {
                try {
                    String optString = jSONObject2.optString("signal");
                    int optInt = jSONObject2.optInt("commentcount");
                    if (!(jSONObject.optString("title", null) == null || optString.equals("nextpage"))) {
                        a bookClubTitleCard = new BookClubTitleCard(this, "BookClubTitleCard", this.a);
                        ((BookClubTitleCard) bookClubTitleCard).setTitleExData(optInt + "条");
                        ((BookClubTitleCard) bookClubTitleCard).setDividerVisible(false);
                        ((BookClubTitleCard) bookClubTitleCard).setTitleHeight((int) l().getFromActivity().getResources().getDimension(R.dimen.title_height_48));
                        ((BookClubTitleCard) bookClubTitleCard).setTitlePaddingTop((int) l().getFromActivity().getResources().getDimension(R.dimen.common_dp_8));
                        bookClubTitleCard.fillData(jSONObject);
                        bookClubTitleCard.setEventListener(l());
                        this.k.add(bookClubTitleCard);
                        this.l.put(bookClubTitleCard.getCardId(), bookClubTitleCard);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                JSONArray optJSONArray = jSONObject2.optJSONArray(string);
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        a bookClubTopicCard = new BookClubTopicCard(this, "BookClubTopicCard", 1001, this.a);
                        bookClubTopicCard.fillData(optJSONArray.getJSONObject(i));
                        bookClubTopicCard.setEventListener(l());
                        this.k.add(bookClubTopicCard);
                        this.l.put(bookClubTopicCard.getCardId(), bookClubTopicCard);
                    }
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }
}
