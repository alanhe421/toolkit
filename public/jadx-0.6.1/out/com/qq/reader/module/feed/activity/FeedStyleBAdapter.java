package com.qq.reader.module.feed.activity;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.module.feed.card.FeedNoMoreBottomCard;
import com.qq.reader.module.feed.card.FeedNoMoreTopCard;
import com.qq.reader.module.feed.card.FeedRecommendBCard;
import com.qq.reader.module.feed.card.FeedRookieEntranceCard;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.qq.reader.module.feed.data.impl.j;
import com.qq.reader.module.feed.swipe.FeedSwipeLayout;
import com.tencent.feedback.proguard.R;
import com.tencent.qalsdk.sdk.v;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FeedStyleBAdapter extends com.qq.reader.module.bookstore.qnative.a.a {
    Activity b;
    NoRepeatArrayList c = new NoRepeatArrayList();
    private Map<String, j> d = new HashMap();
    private a e;
    private FeedRecommendBCard f;

    private class NoRepeatArrayList extends ArrayList<FeedBaseCard> {
        HashSet<String> idSet;

        private NoRepeatArrayList() {
            this.idSet = new HashSet();
        }

        public boolean add(FeedBaseCard feedBaseCard) {
            if (this.idSet.add(feedBaseCard.getCardId())) {
                return super.add(feedBaseCard);
            }
            c.e("FeedPackageDate", "NoRepeatArrayList find repeatID : " + feedBaseCard.getCardId());
            return false;
        }

        public void add(int i, FeedBaseCard feedBaseCard) {
            if (this.idSet.add(feedBaseCard.getCardId())) {
                super.add(i, feedBaseCard);
            } else {
                c.e("FeedPackageDate", "NoRepeatArrayList find repeatID : " + feedBaseCard.getCardId());
            }
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

    public FeedStyleBAdapter(Activity activity) {
        super(30);
        this.b = activity;
    }

    public void a(List<FeedBaseCard> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ((FeedBaseCard) list.get(i)).setEventListener((com.qq.reader.module.bookstore.qnative.c.a) this.b);
        }
    }

    public void notifyDataSetChanged() {
        if (com.qq.reader.common.login.c.b()) {
            if (m()) {
                FeedRookieEntranceCard bW = d.bW(ReaderApplication.getApplicationImp());
                if (bW != null) {
                    List arrayList = new ArrayList();
                    arrayList.add(bW);
                    if (com.qq.reader.module.feed.loader.d.b().e() != null && com.qq.reader.module.feed.loader.d.b().e().size() == 3) {
                        this.f = new FeedRecommendBCard(null, "");
                        arrayList.add(this.f);
                    }
                    b(arrayList);
                }
            } else if (b()) {
                this.c.remove(0);
            } else if (com.qq.reader.module.feed.loader.d.b().e() != null && com.qq.reader.module.feed.loader.d.b().e().size() == 3) {
                this.f = new FeedRecommendBCard(null, "");
                a(this.f);
            }
        } else if (!d.j(ReaderApplication.getApplicationImp())) {
            if (m()) {
                List arrayList2 = new ArrayList();
                Object bV = d.bV(ReaderApplication.getApplicationImp());
                if (bV == null) {
                    FeedRookieEntranceCard feedRookieEntranceCard = new FeedRookieEntranceCard(null, "");
                    feedRookieEntranceCard.initDefaultLogin((com.qq.reader.module.bookstore.qnative.c.a) this.b);
                    bV = feedRookieEntranceCard;
                }
                arrayList2.add(bV);
                if (com.qq.reader.module.feed.loader.d.b().e() != null && com.qq.reader.module.feed.loader.d.b().e().size() == 3) {
                    this.f = new FeedRecommendBCard(null, "");
                    arrayList2.add(this.f);
                }
                b(arrayList2);
            } else {
                List arrayList3 = new ArrayList();
                arrayList3.add(new FeedNoMoreTopCard(null, FeedNoMoreTopCard.FEED_NOLOGIN));
                if (com.qq.reader.module.feed.loader.d.b().e() != null && com.qq.reader.module.feed.loader.d.b().e().size() == 3) {
                    this.f = new FeedRecommendBCard(null, "");
                    arrayList3.add(this.f);
                }
                b(arrayList3);
            }
        }
        super.notifyDataSetChanged();
        if (this.e != null) {
            this.e.onAdapterNotifidataSetChange();
        }
    }

    private boolean m() {
        int bF = d.bF(ReaderApplication.getApplicationImp());
        return bF >= 0 && bF <= 30 && d.bU(ReaderApplication.getApplicationImp());
    }

    public void a(FeedBaseCard feedBaseCard) {
        if (d()) {
            this.c.remove(0);
        }
        if (b()) {
            this.c.remove(0);
        }
        if (c()) {
            this.c.remove(0);
        }
        feedBaseCard.setEventListener((com.qq.reader.module.bookstore.qnative.c.a) this.b);
        this.c.add(0, feedBaseCard);
    }

    public void b(List<FeedBaseCard> list) {
        if (d()) {
            this.c.remove(0);
        }
        if (b()) {
            this.c.remove(0);
        }
        if (c()) {
            this.c.remove(0);
        }
        a((List) list);
        this.c.addAll(0, list);
    }

    public void b(FeedBaseCard feedBaseCard) {
        if (f()) {
            this.c.remove(this.c.size() - 1);
        }
        if (!(feedBaseCard instanceof FeedRookieEntranceCard)) {
            feedBaseCard.setEventListener((com.qq.reader.module.bookstore.qnative.c.a) this.b);
            this.c.add(feedBaseCard);
        }
    }

    public void c(List<FeedBaseCard> list) {
        if (f()) {
            this.c.remove(this.c.size() - 1);
        }
        a((List) list);
        this.c.addAll(list);
    }

    public boolean a() {
        if (this.c.size() <= 0 || !(this.c.get(0) instanceof FeedRookieEntranceCard)) {
            return false;
        }
        return ((FeedRookieEntranceCard) this.c.get(0)).isLoginCard();
    }

    public boolean b() {
        return this.c.size() > 0 && (this.c.get(0) instanceof FeedRookieEntranceCard);
    }

    public boolean c() {
        return this.c.size() > 0 && (this.c.get(0) instanceof FeedRecommendBCard);
    }

    public boolean d() {
        if (this.c.size() <= 0 || !(this.c.get(0) instanceof FeedNoMoreTopCard)) {
            return false;
        }
        return true;
    }

    public void e() {
        if (this.c.size() > 0) {
            FeedBaseCard feedBaseCard = (FeedBaseCard) this.c.get(0);
            if (feedBaseCard != null && FeedNoMoreTopCard.FEED_NOLOGIN.equals(feedBaseCard.getType())) {
                this.c.remove(0);
            } else if (a()) {
                this.c.remove(0).doRemoveCard();
                c.e("TAG", "removeLoginCard");
            }
        }
    }

    public boolean f() {
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

    public void g() {
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
            view = new FeedSwipeLayout(this.b, b.inflateView(this.b), R.layout.swipe_bottom_layout);
        }
        view.findViewById(R.id.ll_bottom_view).setVisibility(8);
        try {
            String str;
            b.attachView(view);
            String msgId = b.getMsgId();
            if (b instanceof FeedRecommendBCard) {
                str = "200000003";
            } else {
                str = msgId;
            }
            if (str != null && ((j) this.d.get(str)) == null) {
                j statItem = b.getStatItem();
                if (statItem != null) {
                    if (b instanceof FeedRecommendBCard) {
                        int aS = d.aS(ReaderApplication.getApplicationImp());
                        if (aS == 1) {
                            statItem.a("200000001");
                        } else if (aS == 2) {
                            statItem.a("200000002");
                        } else if (aS == 3) {
                            statItem.a("200000003");
                        }
                        statItem.a(1);
                        statItem.b("55.1.3");
                        statItem.c(str);
                    } else {
                        statItem.a(str);
                        statItem.a(1);
                        statItem.b(b.getStatAlg());
                        statItem.c(b.getExtInfoId());
                    }
                    this.d.put(str, statItem);
                }
            }
        } catch (Exception e) {
            f.a("FeedPackageDate", "Card attachView  ERROR:  " + b.getClass().getName());
            e.printStackTrace();
        }
        return view;
    }

    public com.qq.reader.module.feed.loader.f h() {
        com.qq.reader.module.feed.loader.f fVar = new com.qq.reader.module.feed.loader.f();
        int size = this.c.size();
        if (size > 0) {
            FeedBaseCard feedBaseCard;
            if (!f()) {
                feedBaseCard = (FeedBaseCard) this.c.get(size - 1);
                fVar.a = feedBaseCard.getShowTime();
                fVar.b = feedBaseCard.getSliceOrder();
            } else if (size > 1) {
                feedBaseCard = (FeedBaseCard) this.c.get(size - 2);
                fVar.a = feedBaseCard.getShowTime();
                fVar.b = feedBaseCard.getSliceOrder();
            }
        }
        if (TextUtils.isEmpty(fVar.a)) {
            fVar.a = n() + "98";
        }
        return fVar;
    }

    private String n() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    public com.qq.reader.module.feed.loader.f i() {
        com.qq.reader.module.feed.loader.f fVar = new com.qq.reader.module.feed.loader.f();
        int size = this.c.size();
        if (size > 0) {
            FeedBaseCard feedBaseCard;
            if (!d() && !b()) {
                feedBaseCard = (FeedBaseCard) this.c.get(0);
                fVar.a = feedBaseCard.getShowTime();
                fVar.b = feedBaseCard.getSliceOrder();
            } else if (size > 1) {
                feedBaseCard = (FeedBaseCard) this.c.get(1);
                fVar.a = feedBaseCard.getShowTime();
                fVar.b = feedBaseCard.getSliceOrder();
            }
        }
        return fVar;
    }

    public boolean j() {
        int size = this.c.size();
        int i = 0;
        while (i < size) {
            if (getItemViewType(i) != FeedBaseCard.MESSAGETYPE_NOMORE_TOP && getItemViewType(i) != FeedBaseCard.MESSAGETYPE_NOMORE_BOTTOM && getItemViewType(i) != FeedBaseCard.MESSAGETYPE_ROOKIE_CARD) {
                return false;
            }
            i++;
        }
        return true;
    }

    public String k() {
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

    public void l() {
        if (this.f != null) {
            this.f.updateColumnIndex();
            this.f.refleshColumn();
        }
    }
}
