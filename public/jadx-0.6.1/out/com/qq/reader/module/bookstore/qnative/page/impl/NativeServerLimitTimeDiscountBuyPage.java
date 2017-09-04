package com.qq.reader.module.bookstore.qnative.page.impl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.an;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.LimitTimeDiscountBuyCard;
import com.qq.reader.module.bookstore.qnative.card.impl.LimitTimeDiscountBuyCountDownCard;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.d;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class NativeServerLimitTimeDiscountBuyPage extends af {
    public String a;
    public String b;
    public String c;
    public ArrayList<EventTimeItem> d;
    private d e;
    private int s;

    public static class EventTimeItem implements Parcelable {
        public static final Creator<EventTimeItem> CREATOR = new Creator<EventTimeItem>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public EventTimeItem a(Parcel parcel) {
                return new EventTimeItem(parcel);
            }

            public EventTimeItem[] a(int i) {
                return new EventTimeItem[i];
            }
        };
        public long a;
        public long b;

        protected EventTimeItem(Parcel parcel) {
            this.a = parcel.readLong();
            this.b = parcel.readLong();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.a);
            parcel.writeLong(this.b);
        }
    }

    public NativeServerLimitTimeDiscountBuyPage(Bundle bundle) {
        super(bundle);
    }

    public void a(b bVar) {
        super.a(bVar);
        this.a = ((NativeServerLimitTimeDiscountBuyPage) bVar).a;
        this.b = ((NativeServerLimitTimeDiscountBuyPage) bVar).b;
        this.c = ((NativeServerLimitTimeDiscountBuyPage) bVar).c;
        this.d = ((NativeServerLimitTimeDiscountBuyPage) bVar).d;
    }

    public String a(Bundle bundle) {
        String string = bundle.getString("KEY_ACTIONTAG", "0");
        this.s = Integer.parseInt(string);
        Object string2 = bundle.getString("bids");
        string = e.de + "?seq=" + string;
        if (TextUtils.isEmpty(string2)) {
            return string;
        }
        return string + "&bids=" + string2;
    }

    public boolean b() {
        return true;
    }

    public void b(JSONObject jSONObject) {
        int i = 0;
        super.b(jSONObject);
        if (this.k != null) {
            for (int i2 = 0; i2 < this.k.size(); i2++) {
                ((a) this.k.get(i2)).onCardShouldDestroy();
            }
        }
        this.k.clear();
        this.l.clear();
        this.d = new ArrayList();
        Object string = this.f.getString("BUNDLE_PARAM_LIMIT_TIME_DISCOUNT_BUY_START_TIME");
        JSONObject optJSONObject = jSONObject.optJSONObject("seckill");
        if (optJSONObject != null) {
            JSONArray optJSONArray = optJSONObject.optJSONArray("activities");
            int i3 = 0;
            while (optJSONArray != null && i3 < optJSONArray.length()) {
                EventTimeItem eventTimeItem = new EventTimeItem();
                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i3);
                if (optJSONObject2 != null) {
                    eventTimeItem.a = optJSONObject2.optLong("startTime");
                    eventTimeItem.b = optJSONObject2.optLong("endTime");
                    this.d.add(eventTimeItem);
                    if (!TextUtils.isEmpty(string) && string.equals(String.valueOf(eventTimeItem.a))) {
                        Intent intent = new Intent("BROADCAST_ACTION_SELECT_CUR_ITEM");
                        intent.putExtra("BUNDLE_PARAM_LIMIT_TIME_DISCOUNT_BUY_SEQ", i3);
                        ReaderApplication.getApplicationImp().sendBroadcast(intent);
                    }
                }
                i3++;
            }
            y();
            a limitTimeDiscountBuyCountDownCard = new LimitTimeDiscountBuyCountDownCard(this, "LimitTimeDiscountBuyCountDownCard");
            limitTimeDiscountBuyCountDownCard.setEventListener(l());
            limitTimeDiscountBuyCountDownCard.fillData(optJSONObject);
            this.k.add(limitTimeDiscountBuyCountDownCard);
            this.l.put(limitTimeDiscountBuyCountDownCard.getCardId(), limitTimeDiscountBuyCountDownCard);
            JSONArray optJSONArray2 = optJSONObject.optJSONArray("books");
            while (optJSONArray2 != null && i < optJSONArray2.length()) {
                JSONObject optJSONObject3 = optJSONArray2.optJSONObject(i);
                if (optJSONObject3 != null) {
                    long optLong = optJSONObject.optLong("startTime");
                    long optLong2 = optJSONObject.optLong("endTime");
                    int optInt = optJSONObject.optInt("freeLimit");
                    LimitTimeDiscountBuyCard limitTimeDiscountBuyCard = new LimitTimeDiscountBuyCard(this, "LimitTimeDiscountBuy");
                    limitTimeDiscountBuyCard.setPageIndex(this.s);
                    limitTimeDiscountBuyCard.setStartTime(optLong);
                    limitTimeDiscountBuyCard.setEndTime(optLong2);
                    limitTimeDiscountBuyCard.setCheckLimit(optInt);
                    limitTimeDiscountBuyCard.setEventListener(l());
                    limitTimeDiscountBuyCard.fillData(optJSONObject3);
                    this.k.add(limitTimeDiscountBuyCard);
                    this.l.put(limitTimeDiscountBuyCard.getCardId(), limitTimeDiscountBuyCard);
                }
                i++;
            }
        }
    }

    public boolean a() {
        return false;
    }

    private void y() {
        try {
            if (this.e == null) {
                JSONObject jSONObject = new JSONObject(an.a().a(4));
                this.e = new d();
                this.e.a(jSONObject);
            }
        } catch (Exception e) {
            c.e("Error", e.getMessage());
        }
        if (this.f != null) {
            String string = this.f.getString("KEY_ACTIONTAG");
            List g = this.e.g();
            int i = 0;
            while (g != null && this.d != null && i < g.size() && i < this.d.size()) {
                d.b bVar = (d.b) g.get(i);
                bVar.a = new SimpleDateFormat("HH:mm").format(new Date(((EventTimeItem) this.d.get(i)).a));
                bVar.c = false;
                if (bVar != null && bVar.b.equals(string)) {
                    bVar.c = true;
                }
                i++;
            }
        }
        this.n = this.e;
    }

    public void x() {
        if (this.k != null && this.k.size() > 0) {
            for (a aVar : this.k) {
                if (aVar instanceof LimitTimeDiscountBuyCard) {
                    ((LimitTimeDiscountBuyCard) aVar).statExposure();
                }
            }
        }
    }
}
