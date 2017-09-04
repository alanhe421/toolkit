package com.qq.reader.module.game.card;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class GameAdvBannerCard extends a {
    List<com.qq.reader.module.game.data.a> datas;

    public GameAdvBannerCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.game_adv_double_banner;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (this.datas == null) {
            this.datas = new ArrayList();
        }
        this.datas.clear();
        JSONArray optJSONArray = jSONObject.optJSONArray("advs");
        for (int i = 0; i < optJSONArray.length(); i++) {
            this.datas.add(new com.qq.reader.module.game.data.a(optJSONArray.optJSONObject(i)));
        }
        if (this.datas.size() > 0) {
            return true;
        }
        return false;
    }

    public void attachView() {
        int size = this.datas.size();
        Map hashMap;
        if (size == 1) {
            bindAdv(0, R.id.game_banner_a, true, ((com.qq.reader.module.game.data.a) this.datas.get(0)).c() + "");
            ap.a(getRootView(), R.id.game_banner_divider).setVisibility(8);
            ap.a(getRootView(), R.id.game_banner_b_container).setVisibility(8);
            hashMap = new HashMap();
            hashMap.put(s.ORIGIN, "0");
            hashMap.put("id", ((com.qq.reader.module.game.data.a) this.datas.get(0)).c() + "");
            i.a("event_A216", hashMap, ReaderApplication.getApplicationImp());
        } else if (size > 1) {
            bindAdv(0, R.id.game_banner_a, false, ((com.qq.reader.module.game.data.a) this.datas.get(0)).c() + "");
            bindAdv(1, R.id.game_banner_b, false, ((com.qq.reader.module.game.data.a) this.datas.get(0)).c() + "");
            ap.a(getRootView(), R.id.game_banner_divider).setVisibility(0);
            ap.a(getRootView(), R.id.game_banner_b_container).setVisibility(0);
            hashMap = new HashMap();
            hashMap.put(s.ORIGIN, "1");
            hashMap.put("id", ((com.qq.reader.module.game.data.a) this.datas.get(0)).c() + "");
            i.a("event_A216", hashMap, ReaderApplication.getApplicationImp());
        }
    }

    private void bindAdv(int i, int i2, boolean z, String str) {
        c.a(getEvnetListener().getFromActivity()).a(((com.qq.reader.module.game.data.a) this.datas.get(i)).b(), (ImageView) ap.a(getRootView(), i2), com.qq.reader.common.imageloader.a.a().n());
        final boolean z2 = z;
        final int i3 = i;
        final int i4 = i2;
        final String str2 = str;
        ap.a(getRootView(), i2).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ GameAdvBannerCard e;

            public void onClick(View view) {
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, z2 ? "0" : "1");
                hashMap.put("id", ((com.qq.reader.module.game.data.a) this.e.datas.get(i3)).c() + "");
                hashMap.put("lr", i4 == R.id.game_banner_a ? "1" : "2");
                hashMap.put("id", str2);
                i.a("event_A217", hashMap, ReaderApplication.getApplicationImp());
                com.qq.reader.module.game.a.b().a(true);
                try {
                    com.qq.reader.qurl.c.a(this.e.getEvnetListener().getFromActivity(), ((com.qq.reader.module.game.data.a) this.e.datas.get(i3)).a());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
