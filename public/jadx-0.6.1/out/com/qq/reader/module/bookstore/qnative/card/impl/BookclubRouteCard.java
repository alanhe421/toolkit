package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.BaseCommentCard;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class BookclubRouteCard extends BaseCommentCard {
    private static final int ICON_1 = 36;
    private static final int ICON_2 = 37;
    private static final int ICON_3 = 38;
    private static final int ICON_4 = 39;
    public boolean isNeedBottomLine = false;
    private LinearLayout mRootView;

    class a extends s {
        int a;
        int b;
        String c;
        String d;
        int e;
        String f;
        int g;
        final /* synthetic */ BookclubRouteCard h;

        a(BookclubRouteCard bookclubRouteCard) {
            this.h = bookclubRouteCard;
        }

        public void parseData(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.a = jSONObject.optInt("type");
                this.b = jSONObject.optInt(MessageKey.MSG_ICON);
                this.c = jSONObject.optString("title");
                this.d = jSONObject.optString("nickname");
                this.e = jSONObject.optInt("reward");
                this.g = jSONObject.optInt("commentcount");
                this.f = jSONObject.optString("firstcommentusericon");
            }
        }
    }

    public BookclubRouteCard(b bVar, String str, int i) {
        super(bVar, str, i);
        setCardId(str);
    }

    public int getResLayoutId() {
        return R.layout.bookclubroutelayout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        getItemList().clear();
        int i = 0;
        while (optJSONArray != null && i < optJSONArray.length()) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            a aVar = new a(this);
            aVar.parseData(optJSONObject);
            getItemList().add(aVar);
            i++;
        }
        return true;
    }

    public void attachView() {
        this.mRootView = (LinearLayout) ap.a(getRootView(), R.id.container);
        this.mRootView.removeAllViews();
        int i = 0;
        for (s sVar : getItemList()) {
            int i2;
            a aVar = (a) sVar;
            if (aVar != null) {
                View addRouteItem = addRouteItem(aVar);
                View findViewById = addRouteItem.findViewById(R.id.bottomline);
                i2 = (this.isNeedBottomLine && i == getItemList().size() - 1) ? 0 : 8;
                findViewById.setVisibility(i2);
                this.mRootView.addView(addRouteItem);
                i2 = i + 1;
            } else {
                i2 = i;
            }
            i = i2;
        }
    }

    private View addRouteItem(final a aVar) {
        View inflate = LayoutInflater.from(ReaderApplication.getApplicationImp()).inflate(R.layout.bookclub_routelist_item, null);
        if (inflate != null) {
            ImageView imageView = (ImageView) inflate.findViewById(R.id.routeicon);
            TextView textView = (TextView) inflate.findViewById(R.id.routetitle);
            TextView textView2 = (TextView) inflate.findViewById(R.id.routecontent);
            ImageView imageView2 = (ImageView) inflate.findViewById(R.id.avator_img);
            TextView textView3 = (TextView) inflate.findViewById(R.id.count);
            if (aVar.g > 0) {
                textView3.setText("(" + aVar.g + ")");
            }
            switch (aVar.b) {
                case 36:
                    imageView.setImageResource(R.drawable.bookclub_reward_tag);
                    textView2.setVisibility(0);
                    imageView2.setVisibility(8);
                    break;
                case 37:
                    imageView.setImageResource(R.drawable.bookclub_hotcoment_tag);
                    textView2.setVisibility(8);
                    imageView2.setVisibility(0);
                    c.a(getEvnetListener().getFromActivity()).a(aVar.f, imageView2, com.qq.reader.common.imageloader.a.a().q());
                    break;
                case 39:
                    imageView.setImageResource(R.drawable.bookclub_top_tag);
                    break;
            }
            if (!TextUtils.isEmpty(aVar.c)) {
                textView.setText(aVar.c);
            }
            if (!(aVar.a != 1 || TextUtils.isEmpty(aVar.d) || aVar.e == 0)) {
                int dip2px = ReaderApplication.getApplicationImp().getResources().getDisplayMetrics().widthPixels - dip2px(66.0f);
                String str = aVar.d + "打赏" + aVar.e + "书币";
                int length = str.length() - ao.a(textView2.getPaint(), (float) dip2px, str);
                String str2 = aVar.d;
                if (length > 0 && str2.length() > length) {
                    str2 = str2.substring(0, str2.length() - length);
                }
                textView2.setText(str2 + "打赏" + aVar.e + "书币");
            }
            inflate.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ BookclubRouteCard b;

                public void onClick(View view) {
                    long j = this.b.mFromBid;
                    Bundle bundle = new Bundle();
                    com.qq.reader.module.bookstore.qnative.c cVar = new com.qq.reader.module.bookstore.qnative.c(bundle);
                    Map hashMap;
                    if (aVar.a == 1) {
                        bundle.putLong("URL_BUILD_PERE_BOOK_ID", j);
                        bundle.putString("KEY_JUMP_PAGENAME", "bookclubreward");
                        bundle.putString("LOCAL_STORE_IN_TITLE", ReaderApplication.getApplicationImp().getResources().getString(R.string.bookclubreward));
                        if (j < 4) {
                            bundle.putInt("CTYPE", 4);
                        } else {
                            bundle.putInt("CTYPE", this.b.getCtype());
                        }
                        hashMap = new HashMap();
                        if (j == 570698) {
                            hashMap.put(s.ORIGIN, "1");
                            i.a("event_E5", hashMap, ReaderApplication.getApplicationImp());
                            StatisticsManager.a().a("event_E5", hashMap);
                        } else if (j == 614782) {
                            hashMap.put(s.ORIGIN, "2");
                            i.a("event_E5", hashMap, ReaderApplication.getApplicationImp());
                            StatisticsManager.a().a("event_E5", hashMap);
                        } else if (j == 500680) {
                            hashMap.put(s.ORIGIN, "3");
                            i.a("event_E5", hashMap, ReaderApplication.getApplicationImp());
                            StatisticsManager.a().a("event_E5", hashMap);
                        } else if (j == 612464) {
                            hashMap.put(s.ORIGIN, "4");
                            i.a("event_E5", hashMap, ReaderApplication.getApplicationImp());
                            StatisticsManager.a().a("event_E5", hashMap);
                        }
                        i.a("event_C175", null, ReaderApplication.getApplicationImp());
                        cVar.a(this.b.getEvnetListener());
                    } else if (aVar.a == 2 && aVar.g > 0) {
                        bundle.putLong("URL_BUILD_PERE_BOOK_ID", j);
                        bundle.putString("KEY_JUMP_PAGENAME", "bookclubhot");
                        bundle.putString("LOCAL_STORE_IN_TITLE", "热评区");
                        if (j < 4) {
                            bundle.putInt("CTYPE", 4);
                        }
                        hashMap = new HashMap();
                        if (j == 570698) {
                            hashMap.put(s.ORIGIN, "1");
                            i.a("event_E2", hashMap, ReaderApplication.getApplicationImp());
                            StatisticsManager.a().a("event_E2", hashMap);
                        } else if (j == 614782) {
                            hashMap.put(s.ORIGIN, "2");
                            i.a("event_E2", hashMap, ReaderApplication.getApplicationImp());
                            StatisticsManager.a().a("event_E2", hashMap);
                        } else if (j == 500680) {
                            hashMap.put(s.ORIGIN, "3");
                            i.a("event_E2", hashMap, ReaderApplication.getApplicationImp());
                            StatisticsManager.a().a("event_E2", hashMap);
                        } else if (j == 612464) {
                            hashMap.put(s.ORIGIN, "4");
                            i.a("event_E2", hashMap, ReaderApplication.getApplicationImp());
                            StatisticsManager.a().a("event_E2", hashMap);
                        }
                        i.a("event_C58", null, ReaderApplication.getApplicationImp());
                        cVar.a(this.b.getEvnetListener());
                    }
                }
            });
        }
        return inflate;
    }

    private static int dip2px(float f) {
        return (int) ((ReaderApplication.getApplicationImp().getResources().getDisplayMetrics().density * f) + 0.5f);
    }
}
