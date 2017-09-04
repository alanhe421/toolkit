package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreTwoLevelActivity;
import com.qq.reader.module.bookstore.qnative.c.c;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import tencent.tls.platform.SigType;

public class AudioCategoryStackTabCard extends StackTabCard {
    private String actionId;
    private String categoryName;

    public AudioCategoryStackTabCard(b bVar, String str) {
        super(bVar, str);
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        boolean parseData = super.parseData(jSONObject);
        this.actionId = jSONObject.optString("actionId");
        this.categoryName = jSONObject.optString("categoryName");
        return parseData;
    }

    private void clickStatics() {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, this.actionId);
        i.a("event_B244", hashMap, ReaderApplication.getApplicationImp());
    }

    public void attachView() {
        super.attachView();
        ((TextView) ap.a(getRootView(), R.id.count)).setText("");
        getRootView().setOnClickListener(new c(this) {
            final /* synthetic */ AudioCategoryStackTabCard a;

            {
                this.a = r1;
            }

            public void a(View view) {
                this.a.clickStatics();
                Bundle bundle = new Bundle();
                bundle.putString("KEY_JUMP_PAGENAME", "PAGE_NAME_AUDIO_CATEGORY_LIST_PAGE");
                bundle.putString("KEY_ACTION", "audiocategory");
                bundle.putString("KEY_ACTIONID", this.a.actionId);
                bundle.putString("LOCAL_STORE_IN_TITLE", this.a.categoryName + "热播");
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(this.a.getEvnetListener().getFromActivity(), NativeBookStoreTwoLevelActivity.class);
                intent.setFlags(SigType.TLS);
                com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                this.a.getEvnetListener().getFromActivity().startActivity(intent);
            }
        });
    }
}
