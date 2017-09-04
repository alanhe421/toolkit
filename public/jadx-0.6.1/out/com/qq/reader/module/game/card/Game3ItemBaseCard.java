package com.qq.reader.module.game.card;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.CardTitle;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.card.view.CardMoreView;
import com.qq.reader.module.game.card.view.d;
import com.qq.reader.module.game.data.c;
import com.qq.reader.module.game.data.e;
import com.qq.reader.module.game.data.f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Game3ItemBaseCard extends a {
    protected String columnId;
    protected List<c> gameDatas;
    protected e moreData;
    protected List<d> presenters;
    protected List<b> providers;
    protected f titleData;

    public abstract CardMoreView getMoreView();

    public abstract CardTitle getTitleView();

    public abstract void installProvider();

    public abstract d newGamePresenter();

    public Game3ItemBaseCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return 0;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        List arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("games");
        setTitleData(new f(jSONObject.optString("columnName")));
        setMoreData(new e(jSONObject.optString("more")));
        this.columnId = jSONObject.optString("columnId");
        for (int i = 0; i < optJSONArray.length(); i++) {
            c a = c.a(optJSONArray.optJSONObject(i));
            com.qq.reader.module.game.a.c(a);
            arrayList.add(a);
        }
        setGameDatas(arrayList);
        return true;
    }

    public void attachView() {
        if (this.providers == null || this.providers.size() == 0) {
            installProvider();
        }
        for (b visiable : this.providers) {
            visiable.setVisiable(8);
        }
        c cVar;
        if (this.presenters == null && this.gameDatas != null) {
            this.presenters = new ArrayList();
            int i = 0;
            for (c cVar2 : this.gameDatas) {
                d newGamePresenter = newGamePresenter();
                if (newGamePresenter != null) {
                    this.presenters.add(newGamePresenter);
                    newGamePresenter.a((b) this.providers.get(i), cVar2);
                }
                i++;
            }
        } else if (!(this.presenters == null || this.gameDatas == null)) {
            int i2 = 0;
            while (i2 < this.presenters.size() && i2 < this.gameDatas.size()) {
                cVar2 = (c) this.gameDatas.get(i2);
                if (cVar2 != null) {
                    d dVar = (d) this.presenters.get(i2);
                    if (dVar != null) {
                        dVar.a((b) this.providers.get(i2), cVar2);
                    }
                }
                i2++;
            }
        }
        getTitleView().setCardTitle(37, this.titleData.a(), "", "");
        if (this.gameDatas == null || this.gameDatas.size() == 0) {
            getRootView().setVisibility(8);
        } else {
            getRootView().setVisibility(0);
        }
        CardMoreView moreView = getMoreView();
        final Object a = this.moreData.a();
        moreView.setText("查看更多");
        if (TextUtils.isEmpty(a)) {
            moreView.setVisibility(8);
        } else {
            moreView.setVisibility(0);
        }
        moreView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ Game3ItemBaseCard b;

            public void onClick(View view) {
                this.b.doMoreClick(a);
            }
        });
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, this.columnId);
        i.a("event_A226", hashMap, ReaderApplication.getApplicationImp());
    }

    private void doTitleClick() {
    }

    private void doMoreClick(String str) {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, this.columnId);
        i.a("event_A225", hashMap, ReaderApplication.getApplicationImp());
        com.qq.reader.module.game.a.b().a(true);
        if (!TextUtils.isEmpty(str)) {
            try {
                com.qq.reader.qurl.c.a(getEvnetListener().getFromActivity(), str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setGameDatas(List<c> list) {
        this.gameDatas = list;
    }

    public void setTitleData(f fVar) {
        this.titleData = fVar;
    }

    public void setMoreData(e eVar) {
        this.moreData = eVar;
    }

    public void onCardShouldDestroy() {
        super.onCardShouldDestroy();
        if (this.presenters != null && this.providers.size() > 0) {
            for (d b : this.presenters) {
                b.b();
            }
        }
    }
}
