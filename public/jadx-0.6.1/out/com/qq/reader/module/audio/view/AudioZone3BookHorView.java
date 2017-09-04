package com.qq.reader.module.audio.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.qurl.c;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AudioZone3BookHorView extends LinearLayout {
    private a a;
    private List<AudioZoneBookHorItemView> b;
    private int[] c = new int[]{R.id.book_view1, R.id.book_view2, R.id.book_view3};

    public interface a {
        void onClick();
    }

    public AudioZone3BookHorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.audio_zone_3book_hor_layout, this, true);
        a();
    }

    private void a() {
        this.b = new ArrayList();
        for (int findViewById : this.c) {
            this.b.add((AudioZoneBookHorItemView) findViewById(findViewById));
        }
    }

    public void setBookInfo(List<com.qq.reader.module.audio.b.a> list, int i) {
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            if (i2 < i) {
                ((AudioZoneBookHorItemView) this.b.get(i2)).setBookInfo((com.qq.reader.module.audio.b.a) list.get(i2), false);
                ((AudioZoneBookHorItemView) this.b.get(i2)).setVisibility(0);
            } else {
                ((AudioZoneBookHorItemView) this.b.get(i2)).setVisibility(8);
            }
        }
    }

    public void setReportOnClickListener(a aVar) {
        this.a = aVar;
    }

    private void a(com.qq.reader.module.audio.b.a aVar) {
        if (this.a != null) {
            this.a.onClick();
            return;
        }
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, aVar.getOrigin());
        hashMap.put(s.ALG, aVar.getAlg());
        i.a("event_B309", hashMap, ReaderApplication.getApplicationImp());
    }

    public void setBookOnClickListener(List<com.qq.reader.module.audio.b.a> list, final com.qq.reader.module.bookstore.qnative.c.a aVar) {
        for (int i = 0; i < list.size(); i++) {
            final com.qq.reader.module.audio.b.a aVar2 = (com.qq.reader.module.audio.b.a) list.get(i);
            ((AudioZoneBookHorItemView) this.b.get(i)).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ AudioZone3BookHorView c;

                public void onClick(View view) {
                    this.c.a(aVar2);
                    if (aVar == null) {
                        return;
                    }
                    if (TextUtils.isEmpty(aVar2.c())) {
                        aVar2.a(aVar);
                        return;
                    }
                    try {
                        c.a(aVar.getFromActivity(), aVar2.c());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
