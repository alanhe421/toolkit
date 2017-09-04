package com.qq.reader.module.feed.activity;

import android.text.TextUtils;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.module.feed.loader.d;
import com.qq.reader.module.feed.loader.d$b;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

class FeedGoogleCardsStyleBFragment$15 implements c {
    final /* synthetic */ FeedGoogleCardsStyleBFragment a;

    FeedGoogleCardsStyleBFragment$15(FeedGoogleCardsStyleBFragment feedGoogleCardsStyleBFragment) {
        this.a = feedGoogleCardsStyleBFragment;
    }

    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject != null) {
                    JSONObject optJSONObject = jSONObject.optJSONObject("cols");
                    for (int i = 0; i < 3; i++) {
                        String str2 = (String) FeedGoogleCardsStyleBFragment.access$1500(this.a).get(i);
                        if (optJSONObject.has(str2)) {
                            jSONObject = optJSONObject.getJSONObject(str2);
                            if (jSONObject != null) {
                                String optString = jSONObject.optString("title");
                                JSONArray optJSONArray = jSONObject.optJSONArray("bookList");
                                if (optJSONArray != null && optJSONArray.length() > 0) {
                                    ArrayList arrayList = new ArrayList();
                                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                        d b = d.b();
                                        b.getClass();
                                        d$b com_qq_reader_module_feed_loader_d_b = new d$b(b);
                                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                                        if (optJSONObject2 != null) {
                                            com_qq_reader_module_feed_loader_d_b.c = optString;
                                            com_qq_reader_module_feed_loader_d_b.a = optJSONObject2.optLong("bid");
                                            com_qq_reader_module_feed_loader_d_b.b = optJSONObject2.optString("intro");
                                            com_qq_reader_module_feed_loader_d_b.d = optJSONObject2.optString("title");
                                        }
                                        arrayList.add(com_qq_reader_module_feed_loader_d_b);
                                    }
                                    FeedGoogleCardsStyleBFragment.access$1600(this.a).add(arrayList);
                                }
                            }
                        }
                    }
                }
                if (FeedGoogleCardsStyleBFragment.access$1600(this.a) != null && FeedGoogleCardsStyleBFragment.access$1600(this.a).size() > 0) {
                    d.b().a(FeedGoogleCardsStyleBFragment.access$1600(this.a));
                    FeedGoogleCardsStyleBFragment.access$2100(this.a).sendEmptyMessage(10000006);
                }
            } catch (Exception e) {
            }
        }
    }

    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
    }
}
