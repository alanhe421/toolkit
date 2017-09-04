package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.BaseAdvCard;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.feedback.proguard.R;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class Horizontal2ImgAdvCard extends BaseAdvCard {
    private int[] imgIds = new int[]{R.id.img_left, R.id.img_right};
    private int[] imgMaskIds = new int[]{R.id.img_left_mask, R.id.img_right_mask};

    public Horizontal2ImgAdvCard(b bVar, String str) {
        super(bVar, str);
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        JSONArray optJSONArray = jSONObject.optJSONArray(ComicStoreExclusiveItemCard.NET_AD_ATTR_ADVS);
        if (optJSONArray == null || optJSONArray.length() >= 2) {
            return super.parseData(jSONObject);
        }
        return false;
    }

    public int getResLayoutId() {
        return R.layout.vertical_2_img_adv_layout;
    }

    public void attachView() {
        List itemList = getItemList();
        int i = 0;
        while (itemList != null && i < itemList.size() && i < this.imgIds.length && i < this.imgMaskIds.length) {
            ImageView imageView = (ImageView) ap.a(getRootView(), this.imgIds[i]);
            ImageView imageView2 = (ImageView) ap.a(getRootView(), this.imgMaskIds[i]);
            final com.qq.reader.module.bookstore.qnative.item.b bVar = (com.qq.reader.module.bookstore.qnative.item.b) itemList.get(i);
            if (bVar != null) {
                c.a(getEvnetListener().getFromActivity()).a(bVar.f(), imageView, a.a().j());
                imageView2.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ Horizontal2ImgAdvCard b;

                    public void onClick(View view) {
                        try {
                            com.qq.reader.qurl.c.a(this.b.getEvnetListener().getFromActivity(), bVar.e(), null);
                            i.a("event_clicked_" + this.b.getCardId(), null, ReaderApplication.getApplicationImp());
                        } catch (Exception e) {
                            com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
                        }
                    }
                });
            }
            i++;
        }
        i.a("event_shown_" + getCardId(), null, getEvnetListener().getFromActivity());
    }
}
