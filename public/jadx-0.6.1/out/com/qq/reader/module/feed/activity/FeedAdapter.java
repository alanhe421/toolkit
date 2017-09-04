package com.qq.reader.module.feed.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.module.feed.card.FeedNoMoreBottomCard;
import com.qq.reader.module.feed.card.FeedNoMoreTopCard;
import com.qq.reader.module.feed.card.FeedRookieEntranceCard;
import com.qq.reader.module.feed.card.FeedTodayFlashSaleCard;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.qq.reader.module.feed.data.impl.j;
import com.qq.reader.module.feed.swipe.FeedSwipeCompatibleLayout;
import com.qq.reader.module.feed.swipe.FeedSwipeLayout;
import com.qq.reader.module.question.data.AudioData;
import com.tencent.feedback.proguard.R;
import com.tencent.qalsdk.sdk.v;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FeedAdapter extends com.qq.reader.module.bookstore.qnative.a.a {
    Activity b;
    NoRepeatArrayList c = new NoRepeatArrayList();
    private Map<String, j> d = new HashMap();
    private a e;

    private class NoRepeatArrayList extends ArrayList<FeedBaseCard> {
        HashSet<String> idSet;

        private NoRepeatArrayList() {
            this.idSet = new HashSet();
        }

        public boolean add(FeedBaseCard feedBaseCard) {
            if (this.idSet.add(feedBaseCard.getCardId())) {
                FeedAdapter.this.a.a(feedBaseCard);
                return super.add(feedBaseCard);
            }
            c.e("FeedPackageDate", "NoRepeatArrayList find repeatID : " + feedBaseCard.getCardId());
            return false;
        }

        public void add(int i, FeedBaseCard feedBaseCard) {
            if (this.idSet.add(feedBaseCard.getCardId())) {
                FeedAdapter.this.a.a(feedBaseCard);
                super.add(i, feedBaseCard);
                return;
            }
            c.e("FeedPackageDate", "NoRepeatArrayList find repeatID : " + feedBaseCard.getCardId());
        }

        public FeedBaseCard remove(int i) {
            FeedBaseCard feedBaseCard = (FeedBaseCard) super.remove(i);
            this.idSet.remove(feedBaseCard.getCardId());
            return feedBaseCard;
        }

        public boolean addAll(int i, Collection<? extends FeedBaseCard> collection) {
            if (collection != null) {
                try {
                    Iterator it = ((ArrayList) collection).iterator();
                    while (it.hasNext()) {
                        FeedBaseCard feedBaseCard = (FeedBaseCard) it.next();
                        if (this.idSet.contains(feedBaseCard.getCardId())) {
                            it.remove();
                            c.e("FeedPackageDate", "NoRepeatArrayList find repeatID : " + feedBaseCard.getCardId());
                        } else {
                            this.idSet.add(feedBaseCard.getCardId());
                        }
                    }
                } catch (Exception e) {
                    c.e("FeedPackageDate", "Please use ArrayList to addAll in FeedAdapter");
                }
            }
            FeedAdapter.this.a.a(collection);
            return super.addAll(i, collection);
        }

        public boolean addAll(Collection<? extends FeedBaseCard> collection) {
            if (collection != null) {
                try {
                    Iterator it = ((ArrayList) collection).iterator();
                    while (it.hasNext()) {
                        FeedBaseCard feedBaseCard = (FeedBaseCard) it.next();
                        if (this.idSet.contains(feedBaseCard.getCardId())) {
                            it.remove();
                            c.e("FeedPackageDate", "NoRepeatArrayList find repeatID : " + feedBaseCard.getCardId());
                        } else {
                            this.idSet.add(feedBaseCard.getCardId());
                        }
                    }
                } catch (Exception e) {
                    c.e("FeedPackageDate", "Please use ArrayList to addAll in FeedAdapter");
                }
            }
            FeedAdapter.this.a.a(collection);
            return super.addAll(collection);
        }

        public void clear() {
            this.idSet.clear();
            super.clear();
        }
    }

    public interface a {
        void onAdapterNotifidataSetChange();
    }

    public /* synthetic */ Object getItem(int i) {
        return b(i);
    }

    public FeedAdapter(Activity activity) {
        super(30);
        this.b = activity;
    }

    public void a(List<FeedBaseCard> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i) != null) {
                ((FeedBaseCard) list.get(i)).setEventListener((com.qq.reader.module.bookstore.qnative.c.a) this.b);
            }
        }
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (this.e != null) {
            this.e.onAdapterNotifidataSetChange();
        }
    }

    public void b(List<FeedBaseCard> list) {
        if (b()) {
            this.c.remove(0);
        }
        if (a()) {
            this.c.remove(0);
        }
        a((List) list);
        this.c.addAll(0, list);
    }

    public void a(FeedBaseCard feedBaseCard) {
        if (d()) {
            this.c.remove(this.c.size() - 1);
        }
        if (!(feedBaseCard instanceof FeedRookieEntranceCard)) {
            feedBaseCard.setEventListener((com.qq.reader.module.bookstore.qnative.c.a) this.b);
            this.c.add(feedBaseCard);
        }
    }

    public void c(List<FeedBaseCard> list) {
        if (d()) {
            this.c.remove(this.c.size() - 1);
        }
        a((List) list);
        this.c.addAll(list);
    }

    public boolean a() {
        return this.c.size() > 0 && (this.c.get(0) instanceof FeedRookieEntranceCard);
    }

    public boolean b() {
        if (this.c.size() <= 0 || !(this.c.get(0) instanceof FeedNoMoreTopCard)) {
            return false;
        }
        return true;
    }

    public void c() {
        if (this.c.size() > 0) {
            FeedBaseCard feedBaseCard = (FeedBaseCard) this.c.get(0);
        }
    }

    public boolean d() {
        int size = this.c.size();
        if (size > 0) {
            if (this.c.get(size - 1) instanceof FeedNoMoreBottomCard) {
                return true;
            }
        }
        return false;
    }

    public void a(int i) {
        if (i < this.c.size() && i >= 0) {
            ((FeedBaseCard) this.c.get(i)).doRemoveCard();
            this.c.remove(i);
        }
    }

    public void e() {
        this.c.clear();
        this.d.clear();
    }

    public int getCount() {
        return this.c.size();
    }

    public FeedBaseCard b(int i) {
        return (FeedBaseCard) this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        FeedBaseCard b = b(i);
        b.setPosition(i);
        if (view == null) {
            View inflateView = b.inflateView(this.b);
            if (VERSION.SDK_INT < 14) {
                view = new FeedSwipeCompatibleLayout(this.b, inflateView, R.layout.swipe_bottom_layout);
            } else {
                view = new FeedSwipeLayout(this.b, inflateView, R.layout.swipe_bottom_layout);
            }
        }
        view.findViewById(R.id.ll_bottom_view).setVisibility(8);
        try {
            b.attachView(view);
            String msgId = b.getMsgId();
            if (msgId != null && ((j) this.d.get(msgId)) == null) {
                j statItem = b.getStatItem();
                if (statItem != null) {
                    statItem.a(msgId);
                    statItem.a(1);
                    statItem.b(b.getStatAlg());
                    statItem.c(b.getExtInfoId());
                    this.d.put(msgId, statItem);
                }
            }
        } catch (Exception e) {
            f.a("FeedPackageDate", "Card attachView  ERROR:  " + b.getClass().getName());
            e.printStackTrace();
        }
        return view;
    }

    public com.qq.reader.module.feed.loader.f f() {
        com.qq.reader.module.feed.loader.f fVar = new com.qq.reader.module.feed.loader.f();
        int size = this.c.size();
        if (size > 0) {
            FeedBaseCard feedBaseCard;
            if (!d()) {
                feedBaseCard = (FeedBaseCard) this.c.get(size - 1);
                fVar.a = feedBaseCard.getShowTime();
                fVar.b = feedBaseCard.getSliceOrder();
            } else if (size > 1) {
                feedBaseCard = (FeedBaseCard) this.c.get(size - 2);
                fVar.a = feedBaseCard.getShowTime();
                fVar.b = feedBaseCard.getSliceOrder();
            }
        }
        return fVar;
    }

    public boolean g() {
        int size = this.c.size();
        for (int i = 0; i < size; i++) {
            FeedBaseCard feedBaseCard = (FeedBaseCard) this.c.get(i);
            if (!(feedBaseCard instanceof FeedNoMoreTopCard) && !(feedBaseCard instanceof FeedNoMoreBottomCard) && !(feedBaseCard instanceof FeedRookieEntranceCard)) {
                return false;
            }
        }
        return true;
    }

    public String h() {
        if (this.d.size() == 0) {
            return null;
        }
        try {
            StringBuilder stringBuilder = new StringBuilder();
            Iterator it = this.d.keySet().iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                j jVar = (j) this.d.get(str);
                if (str.indexOf(",") != -1) {
                    int i;
                    int a = jVar.a();
                    String c = jVar.c();
                    String b = jVar.b();
                    String[] split = str.split(",");
                    String[] split2 = b.split(",");
                    String[] split3 = c.split(",");
                    int length = split3.length;
                    for (i = 0; i < length; i++) {
                        if (split3[i].equals(v.n)) {
                            split3[i] = "";
                        }
                    }
                    for (i = 0; i < length; i++) {
                        if (i == length - 1) {
                            stringBuilder.append(split[i]).append(":").append(a).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(split2[i]).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(split3[i]);
                        } else {
                            stringBuilder.append(split[i]).append(":").append(a).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(split2[i]).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(split3[i]).append(",");
                        }
                    }
                } else {
                    stringBuilder.append(jVar.toString());
                }
                if (it.hasNext()) {
                    stringBuilder.append(",");
                }
            }
            this.d.clear();
            return stringBuilder.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public void a(a aVar) {
        this.e = aVar;
    }

    public void a(int i, int i2, Intent intent, Handler handler) {
        if (i == 1006 && intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                AudioData audioData = (AudioData) extras.getParcelable("AUDIO_DATA");
                Iterator it = this.c.iterator();
                while (it.hasNext()) {
                    com.qq.reader.module.bookstore.qnative.card.a aVar = (com.qq.reader.module.bookstore.qnative.card.a) it.next();
                    if ("normalList".equalsIgnoreCase(aVar.getType().toLowerCase())) {
                        try {
                            if (((com.qq.reader.module.question.card.a) aVar).isDataChanged(audioData)) {
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public FeedBaseCard i() {
        if (this.c == null || this.c.size() < 1) {
            return null;
        }
        return (FeedBaseCard) this.c.get(this.c.size() - 1);
    }

    public void j() {
        if (this.c != null && this.c.size() >= 1) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                FeedBaseCard feedBaseCard = (FeedBaseCard) it.next();
                if (feedBaseCard instanceof FeedTodayFlashSaleCard) {
                    ((FeedTodayFlashSaleCard) feedBaseCard).removeHandler();
                    return;
                }
            }
        }
    }
}
