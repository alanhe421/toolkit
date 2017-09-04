package com.qq.reader.module.redpacket.singlebookpacket.card;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class SingleBookInValidCard extends RedPacketSingleBookCard {
    public SingleBookInValidCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.redpacket_singlebook_invalid_card;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.single_book_invalid_title);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.single_book_invalid_status);
        View a = ap.a(getRootView(), R.id.red_packet_single_book_card_divider);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getItem().l() + "的");
        switch (getItem().j()) {
            case 0:
                stringBuilder.append("普通红包");
                break;
            case 1:
                stringBuilder.append("月票红包");
                break;
            case 2:
                stringBuilder.append("推荐票红包");
                break;
        }
        if (getItem().c() == 3) {
            textView2.setText(processEmptyTimeFromCreate(getItem().a()) + "被抢光");
        } else {
            textView2.setText("已过期");
        }
        textView.setText(stringBuilder.toString());
        if (isShowDivider()) {
            a.setVisibility(0);
        } else {
            a.setVisibility(8);
        }
        getRootView().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SingleBookInValidCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                o.a(this.a.getEvnetListener().getFromActivity(), this.a.getItem().d(), this.a.getItem().j());
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, String.valueOf(this.a.getItem().j()));
                i.a("event_D214", hashMap, ReaderApplication.getApplicationImp());
            }
        });
    }

    private String processEmptyTimeFromCreate(long j) {
        if (j <= 0) {
            return "1秒";
        }
        long j2 = j / 1000;
        if (j2 == 0) {
            return "1秒";
        }
        long j3 = j2 / 60;
        if (j3 == 0) {
            return j2 + "秒";
        }
        j2 = j3 / 60;
        if (j2 == 0) {
            return j3 + "分钟";
        }
        return j2 + "小时";
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        return false;
    }
}
