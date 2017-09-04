package com.qq.reader.module.comic.card;

import android.app.Activity;
import android.view.View;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.audio.activity.NativeAudioZoneActivity;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.comic.activity.NativeBookStoreComicMainPageActivity;
import com.qq.reader.module.comic.activity.NativeComicFreeAreaActivity;
import com.qq.reader.module.comic.activity.NativeComicMonthlyAreaActivity;
import com.qq.reader.qurl.c;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ComicHeadAdv */
public class a extends com.qq.reader.module.feed.head.a {
    public a(Activity activity) {
        super(activity);
    }

    public void onClick(View view) {
        if (view.getTag() != null && (view.getTag() instanceof com.qq.reader.cservice.adv.a)) {
            com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) view.getTag();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(s.ORIGIN, aVar.f());
                aVar.w().a().putString(s.STATPARAM_KEY, jSONObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, String.valueOf(aVar.d()));
            a(hashMap);
            if (c.a(aVar.h())) {
                String h = aVar.h();
                if (this.a.a.get(h) != null) {
                    h = h + "&posId=" + this.a.a.get(h);
                }
                com.qq.reader.common.monitor.debug.c.e("adv", h);
                try {
                    c.a(this.c, h);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private void a(Map<String, String> map) {
        if (getFromActivity() instanceof NativeBookStoreComicMainPageActivity) {
            i.a("event_F216", map, ReaderApplication.getApplicationImp());
        } else if (getFromActivity() instanceof NativeComicFreeAreaActivity) {
            i.a("event_F342", map, ReaderApplication.getApplicationImp());
        } else if (getFromActivity() instanceof NativeComicMonthlyAreaActivity) {
            i.a("event_F344", map, ReaderApplication.getApplicationImp());
        } else if (getFromActivity() instanceof NativeAudioZoneActivity) {
            i.a("event_B304", map, ReaderApplication.getApplicationImp());
        }
    }

    protected void a() {
    }

    public boolean a(List<com.qq.reader.cservice.adv.a> list) {
        if (!(list == null || list.isEmpty())) {
            a((com.qq.reader.cservice.adv.a) list.get(0), 0);
        }
        return super.a(list);
    }

    public void onPageSelected(int i) {
        super.onPageSelected(i);
        List f = this.b.f();
        if (f.size() > i && i >= 0 && this.c != null && !this.c.isFinishing()) {
            com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) f.get(i);
            if (b() != null && b().isShown()) {
                a(aVar, i);
            }
        }
    }

    public void a(com.qq.reader.cservice.adv.a aVar, int i) {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, i + "");
        hashMap.put("aId", aVar.d() + "");
        if (getFromActivity() instanceof NativeBookStoreComicMainPageActivity) {
            i.a("event_F215", hashMap, ReaderApplication.getApplicationImp());
        } else if (getFromActivity() instanceof NativeComicFreeAreaActivity) {
            i.a("event_F341", hashMap, ReaderApplication.getApplicationImp());
        } else if (getFromActivity() instanceof NativeComicMonthlyAreaActivity) {
            i.a("event_F343", hashMap, ReaderApplication.getApplicationImp());
        } else if (getFromActivity() instanceof NativeAudioZoneActivity) {
            i.a("event_B300", hashMap, ReaderApplication.getApplicationImp());
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        super.onPageScrolled(i, f, i2);
    }
}
