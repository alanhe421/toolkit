package com.qq.reader.module.game.card;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import org.json.JSONArray;
import org.json.JSONObject;

public class GameEnterCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private a enter1 = new a();
    private a enter2 = new a();

    private class a {
        final /* synthetic */ GameEnterCard a;
        private String b;
        private String c;
        private String d;
        private int e;

        private a(GameEnterCard gameEnterCard) {
            this.a = gameEnterCard;
        }
    }

    public GameEnterCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.game_category_double_banner;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        JSONArray optJSONArray = jSONObject.optJSONArray("enter");
        parseEnter(this.enter1, optJSONArray.optJSONObject(0));
        parseEnter(this.enter2, optJSONArray.optJSONObject(1));
        return true;
    }

    private void parseEnter(a aVar, JSONObject jSONObject) {
        aVar.b = jSONObject.optString(SocialConstants.PARAM_SEND_MSG);
        aVar.c = jSONObject.optString(SocialConstants.PARAM_IMG_URL);
        aVar.d = jSONObject.optString("qurl");
        aVar.e = jSONObject.optInt("newRedActivity");
    }

    public void attachView() {
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.game_banner_img_a);
        TextView textView = (TextView) ap.a(getRootView(), R.id.game_banner_a);
        if (!TextUtils.isEmpty(this.enter1.c)) {
            c.a(getEvnetListener().getFromActivity()).a(this.enter1.c, imageView, com.qq.reader.common.imageloader.a.a().j());
        }
        if (!TextUtils.isEmpty(this.enter1.b)) {
            textView.setText(this.enter1.b);
        }
        ap.a(getRootView(), R.id.game_banner_a_container).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ GameEnterCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                com.qq.reader.module.game.a.b().a(true);
                try {
                    com.qq.reader.qurl.c.a(this.a.getEvnetListener().getFromActivity(), this.a.enter1.d);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                i.a("event_A251", null, ReaderApplication.getApplicationImp());
            }
        });
        imageView = (ImageView) ap.a(getRootView(), R.id.game_banner_img_b);
        textView = (TextView) ap.a(getRootView(), R.id.game_banner_b);
        if (!TextUtils.isEmpty(this.enter2.c)) {
            c.a(getEvnetListener().getFromActivity()).a(this.enter2.c, imageView, com.qq.reader.common.imageloader.a.a().j());
        }
        if (!TextUtils.isEmpty(this.enter2.b)) {
            textView.setText(this.enter2.b);
        }
        final View a = ap.a(getRootView(), R.id.adv_bubble);
        int d = this.enter2.e;
        int ci = d.ci(ReaderApplication.getApplicationImp());
        if (a != null) {
            if (d > ci) {
                a.setVisibility(0);
            } else {
                a.setVisibility(4);
            }
        }
        ap.a(getRootView(), R.id.game_banner_b_container).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ GameEnterCard b;

            public void onClick(View view) {
                com.qq.reader.module.game.a.b().a(true);
                try {
                    com.qq.reader.qurl.c.a(this.b.getEvnetListener().getFromActivity(), this.b.enter2.d);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                i.a("event_A236", null, ReaderApplication.getApplicationImp());
                if (a != null) {
                    a.setVisibility(4);
                    d.Q(ReaderApplication.getApplicationImp(), this.b.enter2.e);
                }
            }
        });
    }
}
