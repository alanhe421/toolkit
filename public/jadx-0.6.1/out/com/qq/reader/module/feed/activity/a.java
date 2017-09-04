package com.qq.reader.module.feed.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.qq.reader.activity.MainActivity;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.common.utils.c;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.feed.data.impl.d;
import com.qq.reader.module.feed.mypreference.MyReadingGeneActivity;
import com.tencent.feedback.proguard.R;

/* compiled from: FeedAction */
public class a {
    public static void a(d dVar, com.qq.reader.module.bookstore.qnative.c.a aVar) {
        if (aVar != null) {
            a(dVar, aVar.getFromActivity());
        }
    }

    private static void a(d dVar, Activity activity) {
        int i = 0;
        if (activity != null) {
            Intent intent = new Intent();
            String a = dVar.a();
            if (a == null) {
                a = "";
            }
            if (a.equalsIgnoreCase("goReadPage")) {
                Object b = dVar.b();
                if (!TextUtils.isEmpty(b)) {
                    o.a(activity, b, 0, 0, null);
                }
            } else if (a.equalsIgnoreCase("goBookDetail")) {
                if (dVar.c() != null) {
                    a = dVar.c().toString();
                } else {
                    a = null;
                }
                o.a(activity, dVar.b(), a, null, null);
            } else if (a.equalsIgnoreCase("goTopic")) {
                a = dVar.b();
                if (!TextUtils.isEmpty(dVar.d())) {
                    a = a + "&" + dVar.d();
                }
                o.c(activity, a, null);
            } else {
                int i2;
                if (a.equalsIgnoreCase("weblink")) {
                    intent.setClass(activity, WebBrowserForContents.class);
                    intent.putExtra("com.qq.reader.WebContent", dVar.b());
                    i2 = 0;
                } else if (a.equalsIgnoreCase("goExplore")) {
                    if (activity instanceof MainActivity) {
                        ((MainActivity) activity).a("bookweb_classify_tab");
                        return;
                    }
                    return;
                } else if (a.equalsIgnoreCase("goClassify")) {
                    if (activity instanceof MainActivity) {
                        ((MainActivity) activity).a("stacks_tab");
                        return;
                    }
                    return;
                } else if (a.equalsIgnoreCase("goRank")) {
                    o.b(activity, null, null, null);
                    return;
                } else if (a.equalsIgnoreCase("goPaymonth")) {
                    o.l(activity, null);
                    return;
                } else if (a.equalsIgnoreCase("goComment")) {
                    o.a(activity, dVar.b(), "0", null, null, null);
                    return;
                } else if (a.equalsIgnoreCase("goLgoin")) {
                    if (activity instanceof MainActivity) {
                        r0 = new Bundle();
                        r0.putBoolean("goLgoin", true);
                        r0.putBoolean("fromFeedAction", true);
                        ((com.qq.reader.module.bookstore.qnative.c.a) activity).doFunction(r0);
                        return;
                    }
                    o.a(activity, null);
                    return;
                } else if (a.equalsIgnoreCase("goGuide")) {
                    intent.setClass(activity, MyReadingGeneActivity.class);
                    i2 = 30001;
                    i = 1;
                } else if (a.equalsIgnoreCase("goClassicTopic")) {
                    o.d(activity, null, "1", null);
                    return;
                } else if (a.equalsIgnoreCase("goFame")) {
                    o.b(activity, null, null);
                    return;
                } else if (a.equalsIgnoreCase("goHallOfFame")) {
                    o.a(activity, null, 0, null);
                    return;
                } else if (!a.equalsIgnoreCase("goRookieLogin")) {
                    return;
                } else {
                    if (activity instanceof MainActivity) {
                        r0 = new Bundle();
                        r0.putBoolean("fromFeedAction", true);
                        r0.putBoolean("goRookieLogin", true);
                        r0.putString("getReward", dVar.b());
                        ((com.qq.reader.module.bookstore.qnative.c.a) activity).doFunction(r0);
                        return;
                    }
                    o.a(activity, null);
                    return;
                }
                c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                if (i != 0) {
                    activity.startActivityForResult(intent, i2);
                } else {
                    activity.startActivity(intent);
                }
            }
        }
    }
}
