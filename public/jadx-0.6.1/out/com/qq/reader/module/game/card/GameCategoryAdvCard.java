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
import com.tencent.open.SocialConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class GameCategoryAdvCard extends a {
    private String adImg;
    private String adUrl;
    String categoryId;
    int userLocation;

    public GameCategoryAdvCard(b bVar, String str) {
        super(bVar, str);
    }

    public GameCategoryAdvCard(b bVar, String str, int i, String str2) {
        super(bVar, str);
        this.userLocation = i;
        this.categoryId = str2;
    }

    public int getResLayoutId() {
        return R.layout.game_adv_double_banner;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.adImg = jSONObject.optString(SocialConstants.PARAM_IMG_URL);
        this.adUrl = jSONObject.optString("qurl");
        return true;
    }

    public void attachView() {
        c.a(getEvnetListener().getFromActivity()).a(this.adImg, (ImageView) ap.a(getRootView(), R.id.game_banner_a), com.qq.reader.common.imageloader.a.a().n());
        ap.a(getRootView(), R.id.game_banner_a).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ GameCategoryAdvCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                try {
                    com.qq.reader.qurl.c.a(this.a.getEvnetListener().getFromActivity(), this.a.adUrl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, this.a.categoryId);
                if (this.a.userLocation == 2) {
                    i.a("event_A231", hashMap, ReaderApplication.getApplicationImp());
                } else if (this.a.userLocation == 1) {
                    i.a("event_A212", hashMap, ReaderApplication.getApplicationImp());
                }
            }
        });
        ap.a(getRootView(), R.id.game_banner_divider).setVisibility(8);
        ap.a(getRootView(), R.id.game_banner_b_container).setVisibility(8);
        ap.a(getRootView(), R.id.localstore_card_divider).setVisibility(8);
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, this.categoryId);
        if (this.userLocation == 2) {
            i.a("event_A230", hashMap, ReaderApplication.getApplicationImp());
        } else if (this.userLocation == 1) {
            i.a("event_A211", hashMap, ReaderApplication.getApplicationImp());
        }
    }
}
