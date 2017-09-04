package com.qq.reader.module.redpacket.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.qurl.c;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RedPacketSquareUserRankView extends RelativeLayout {
    private ImageView a;
    private ImageView b;
    private ImageView c;
    private RelativeLayout d;
    private a e;

    public RedPacketSquareUserRankView(Context context) {
        super(context);
        a(context);
        a();
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.redpacket_square_user_rank_layout, this, true);
    }

    public RedPacketSquareUserRankView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
        a();
    }

    public void setEventListener(a aVar) {
        this.e = aVar;
    }

    private void a() {
        this.a = (ImageView) findViewById(R.id.redpacket_square_band_1_icon);
        this.b = (ImageView) findViewById(R.id.redpacket_square_band_2_icon);
        this.c = (ImageView) findViewById(R.id.redpacket_square_band_3_icon);
        this.d = (RelativeLayout) findViewById(R.id.redpacket_square_user_bank_rl);
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RedPacketSquareUserRankView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                try {
                    c.a(this.a.e.getFromActivity(), d.bQ(ReaderApplication.getApplicationImp().getApplicationContext()), null, null);
                    Map hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, "1");
                    i.a("event_D224", hashMap, ReaderApplication.getApplicationImp().getApplicationContext());
                } catch (Exception e) {
                }
            }
        });
    }

    public void a(ArrayList<String> arrayList) {
        if (arrayList != null && arrayList.size() >= 1) {
            int size = arrayList.size();
            if (size == 1) {
                a(this.a, (String) arrayList.get(0));
            } else if (size == 2) {
                a(this.a, (String) arrayList.get(0));
                a(this.b, (String) arrayList.get(1));
            } else if (size == 3) {
                a(this.a, (String) arrayList.get(0));
                a(this.b, (String) arrayList.get(1));
                a(this.c, (String) arrayList.get(2));
            }
        }
    }

    private void a(ImageView imageView, String str) {
        com.qq.reader.common.imageloader.c.a(getContext()).a(str, imageView, com.qq.reader.common.imageloader.a.a().a(getUserIconDisplayOption()));
    }

    private int getUserIconDisplayOption() {
        return R.drawable.redpacket_square_user_default;
    }
}
