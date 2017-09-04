package com.qq.reader.module.comic.card;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.comic.entity.r;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class ComicStoreEntranceCard extends a {
    public static final int BOOKSTORE_ID = 70151170;
    public static final int FREEINFO_ID = 70151166;
    public static final int MONTHLY_ID = 70151165;
    public static final int RANKINFO_ID = 70151167;
    private r mEntranceInfo;

    public ComicStoreEntranceCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.comic_bookstore_head_entrance_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mEntranceInfo = (r) new Gson().fromJson(jSONObject.toString(), r.class);
        return this.mEntranceInfo != null && this.mEntranceInfo.b() >= 4;
    }

    public void attachView() {
        LinearLayout linearLayout = (LinearLayout) getRootView();
        if (this.mEntranceInfo != null && linearLayout != null) {
            linearLayout = (LinearLayout) ap.a(linearLayout, R.id.entrance);
            if (linearLayout.getChildCount() != 0) {
                linearLayout.removeAllViews();
            }
            if (this.mEntranceInfo.b() >= 4) {
                for (int i = 0; i < this.mEntranceInfo.b(); i++) {
                    View addEntranceItemView = addEntranceItemView(linearLayout, (r.a) this.mEntranceInfo.a().get(i));
                    LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2, 1.0f);
                    layoutParams.weight = 1.0f;
                    linearLayout.addView(addEntranceItemView, layoutParams);
                }
            }
        }
    }

    public View addEntranceItemView(LinearLayout linearLayout, final r.a aVar) {
        Context context = linearLayout.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.comic_bookstore_head_entrance_item_view, null);
        ImageView imageView = (ImageView) ap.a(inflate, R.id.entrance_icon);
        TextView textView = (TextView) ap.a(inflate, R.id.entrance_text);
        if (!TextUtils.isEmpty(aVar.d())) {
            c.a(context).a(aVar.d(), imageView, com.qq.reader.common.imageloader.a.a().j());
        }
        if (!TextUtils.isEmpty(aVar.b())) {
            textView.setText(aVar.b());
        }
        inflate.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ComicStoreEntranceCard b;

            public void onClick(View view) {
                this.b.onItemClick(aVar);
            }
        });
        return inflate;
    }

    public void onItemClick(r.a aVar) {
        if (aVar != null && !TextUtils.isEmpty(aVar.c())) {
            try {
                Object obj = "";
                switch (aVar.a()) {
                    case MONTHLY_ID /*70151165*/:
                        obj = "event_F291";
                        break;
                    case FREEINFO_ID /*70151166*/:
                        obj = "event_F290";
                        break;
                    case RANKINFO_ID /*70151167*/:
                        obj = "event_F289";
                        break;
                    case BOOKSTORE_ID /*70151170*/:
                        obj = "event_F288";
                        break;
                }
                if (!TextUtils.isEmpty(obj)) {
                    Map hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, "" + aVar.a());
                    i.a(obj, hashMap, ReaderApplication.getApplicationContext());
                }
                com.qq.reader.qurl.c.a(getEvnetListener().getFromActivity(), aVar.c());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
