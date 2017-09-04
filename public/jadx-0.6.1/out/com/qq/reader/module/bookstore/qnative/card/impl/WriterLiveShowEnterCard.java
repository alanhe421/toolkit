package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.c.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class WriterLiveShowEnterCard extends a {
    private int roomNum;

    public WriterLiveShowEnterCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.writer_live_show_enter_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.roomNum = jSONObject.optJSONObject("manitoInfo").optInt("roomNumber");
        return true;
    }

    public void attachView() {
        ap.a(getRootView(), R.id.enter_btn).setOnClickListener(new c(this) {
            final /* synthetic */ WriterLiveShowEnterCard a;

            {
                this.a = r1;
            }

            public void a(View view) {
                i.a("event_Z33", null, ReaderApplication.getApplicationImp());
                o.c(this.a.getEvnetListener().getFromActivity(), this.a.roomNum);
            }
        });
    }
}
