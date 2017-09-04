package com.qq.reader.module.game.card.view;

import android.app.Activity;
import android.view.View;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.feed.head.a;
import com.qq.reader.qurl.c;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: GameHeadAdv */
public class b extends a {
    public b(Activity activity) {
        super(activity);
    }

    public void onClick(View view) {
        com.qq.reader.module.game.a.b().a(true);
        Object tag = view.getTag();
        if (tag instanceof com.qq.reader.cservice.adv.a) {
            String h = ((com.qq.reader.cservice.adv.a) tag).h();
            if (c.a(h)) {
                try {
                    c.a(this.c, h);
                    Map hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, view.getTag(R.string.obj_tag) + "");
                    hashMap.put("aId", ((com.qq.reader.cservice.adv.a) tag).d() + "");
                    i.a("event_A210", hashMap, ReaderApplication.getApplicationImp());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void a() {
    }

    public void onPageSelected(int i) {
        super.onPageSelected(i);
        List f = this.b.f();
        if (f.size() > i && i >= 0 && this.c != null && !this.c.isFinishing()) {
            a((com.qq.reader.cservice.adv.a) f.get(i), i);
        }
    }

    public static void a(com.qq.reader.cservice.adv.a aVar, int i) {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, i + "");
        hashMap.put("aId", aVar.d() + "");
        i.a("event_A209", hashMap, ReaderApplication.getApplicationImp());
    }

    public void onPageScrolled(int i, float f, int i2) {
        super.onPageScrolled(i, f, i2);
    }

    public void onPageScrollStateChanged(int i) {
        super.onPageScrollStateChanged(i);
        switch (i) {
            case 1:
                com.qq.reader.module.game.a.b().a(true);
                return;
            default:
                return;
        }
    }

    protected int h() {
        return R.color.game_header_default_color;
    }
}
