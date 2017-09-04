package com.qq.reader.common.web.js;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import com.qq.reader.common.db.handle.e;
import com.qq.reader.common.monitor.a.a;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.web.js.a.a.b;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.module.bookchapter.online.OnlineChapterActivity;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import org.json.JSONException;
import org.json.JSONObject;

public class JSBookDir extends b {
    private Activity a;

    public JSBookDir(Activity activity) {
        this.a = activity;
    }

    public void dir(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str.replace("\n", ""));
            long optLong = jSONObject.optLong("id");
            String valueOf = String.valueOf(optLong);
            String optString = jSONObject.optString("title");
            String optString2 = jSONObject.optString("author");
            String optString3 = jSONObject.optString("downloadurl");
            String str2 = "";
            int optInt = jSONObject.optInt(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION);
            int optInt2 = jSONObject.optInt("chapterid");
            String optString4 = jSONObject.optString("chaptertitle");
            String g = ao.g(optLong);
            String optString5 = jSONObject.optString("format");
            int optInt3 = jSONObject.optInt("drm");
            String str3 = "";
            int optInt4 = jSONObject.optInt("finished");
            if (valueOf == null || valueOf.length() == 0 || optString == null || optInt == 0) {
                throw new JSONException("no key para");
            }
            String optString6 = jSONObject.optString(s.STATPARAM_KEY);
            String optString7 = jSONObject.optString("bookfrom");
            e.a().a(String.valueOf(valueOf), optString7);
            if (optString.contains(":")) {
                optString = optString.replace(":", " ");
            }
            Parcelable onlineTag = new OnlineTag(valueOf, str2, 0);
            onlineTag.a(optString).e(optString2).f(optString3).c(optInt2).b(optString4).e(0).d(optInt).f(1).h(g).k(optString5).i(optInt3).g(str3).h(optInt4);
            com.qq.reader.common.monitor.a.b.a(new a(valueOf, optString6));
            Intent intent = new Intent();
            intent.putExtra("com.qq.reader.OnlineTag", onlineTag);
            intent.putExtra("onlineChapterActivityFromWeb", true);
            intent.setClass(this.a, OnlineChapterActivity.class);
            this.a.startActivity(intent);
            j.a(10, 2);
            i.a("event_C11", null, this.a.getApplicationContext());
            StatisticsManager.a().a("event_C11", null);
        } catch (JSONException e) {
            e.printStackTrace();
            f.a("JSBookDir", "server dir error");
        }
    }
}
