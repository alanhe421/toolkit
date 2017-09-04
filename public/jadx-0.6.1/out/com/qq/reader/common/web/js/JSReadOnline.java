package com.qq.reader.common.web.js;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.qq.reader.activity.ReaderPageActivity;
import com.qq.reader.activity.WebBrowser;
import com.qq.reader.common.db.handle.e;
import com.qq.reader.common.db.handle.r;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.monitor.a.a;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.web.js.a.a.b;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import org.json.JSONException;
import org.json.JSONObject;

public class JSReadOnline extends b {
    private Context a;

    public JSReadOnline(Context context) {
        this.a = context;
    }

    public void setuid(long j) {
    }

    public void readbook(String str) {
        try {
            String str2;
            JSONObject jSONObject = new JSONObject(str.replace("\n", ""));
            long optLong = jSONObject.optLong("id");
            String valueOf = String.valueOf(optLong);
            String optString = jSONObject.optString("title");
            String optString2 = jSONObject.optString("author");
            String optString3 = jSONObject.optString("downloadurl");
            if (optString3.equalsIgnoreCase("undefined")) {
                str2 = "";
            } else {
                str2 = optString3;
            }
            String str3 = "";
            int optInt = jSONObject.optInt(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION);
            int optInt2 = jSONObject.optInt("chapterid", -1);
            String d = v.d(jSONObject.optString("chaptertitle"));
            String g = ao.g(Long.valueOf(valueOf).longValue());
            String optString4 = jSONObject.optString("format");
            int optInt3 = jSONObject.optInt("drm");
            String str4 = "";
            int optInt4 = jSONObject.optInt("finished");
            long optLong2 = jSONObject.optLong("lastuploadtime", 0);
            String optString5 = jSONObject.optString("lastcname", "");
            String optString6 = jSONObject.optString(s.STATPARAM_KEY);
            if (valueOf == null || valueOf.length() == 0 || optString == null || optInt == 0) {
                throw new JSONException("no key para");
            }
            String optString7 = jSONObject.optString("addorigin");
            e.a().a(valueOf, jSONObject.optString("bookfrom"));
            if (optString.contains(":")) {
                optString3 = optString.replace(":", " ");
            } else {
                optString3 = optString;
            }
            OnlineTag onlineTag = new OnlineTag(valueOf, str3, 0);
            onlineTag.a(optString3).e(optString2).f(str2).c(optInt2).b(d).e(0).d(optInt).f(1).h(g).k(optString4).i(optInt3).g(str4).h(optInt4).c(optLong2).l(optString5).i(optString6);
            com.qq.reader.common.monitor.a.b.a(new a(valueOf, optString6));
            if (this.a instanceof ReaderPageActivity) {
                ((ReaderPageActivity) this.a).c(onlineTag);
            } else {
                startOnlineReader((Activity) this.a, onlineTag, optString7);
            }
            r.a().a(0, String.valueOf(optLong), optString3);
            j.a(8, 2);
            i.a("event_C9", null, this.a);
            StatisticsManager.a().a("event_C9", null);
        } catch (JSONException e) {
            e.printStackTrace();
            f.a("JSReadOnline", "server onlineinfo error : ");
            a(e);
        }
    }

    public static void startOnlineReader(Activity activity, OnlineTag onlineTag, String str) {
        Intent intent = new Intent();
        intent.setClass(activity, ReaderPageActivity.class);
        intent.setFlags(SigType.WLOGIN_QRPUSH);
        intent.putExtra("com.qq.reader.OnlineTag", onlineTag);
        intent.putExtra("com.qq.reader.OnlineTag.web.chapter", true);
        intent.putExtra("com.qq.reader.fromonline", true);
        intent.putExtra("com.qq.reader.fromonline_addfrom", str);
        Mark e = com.qq.reader.common.db.handle.i.c().e(onlineTag.k());
        if (e != null) {
            intent.putExtra("com.qq.reader.inheadpage", e.getStarPointStr());
        }
        OnlineTag a = v.b().a(onlineTag.k());
        if (!(onlineTag == null || a == null)) {
            onlineTag.c(a.x());
        }
        activity.startActivity(intent);
    }

    public void onsreach(String str) {
        if (this.a instanceof WebBrowser) {
            ((WebBrowser) this.a).b(str);
        }
    }

    private void a(JSONException jSONException) {
        if (jSONException instanceof JSONException) {
            a(this.a.getResources().getString(R.string.readonlie_getinfo_error));
        } else {
            a(this.a.getResources().getString(R.string.readonlie_getinfo_error));
        }
    }

    private void a(String str) {
        af.a(this.a, (CharSequence) str, 0).a();
    }
}
