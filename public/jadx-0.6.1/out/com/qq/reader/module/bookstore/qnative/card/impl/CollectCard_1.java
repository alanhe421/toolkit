package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.json.JSONArray;
import org.json.JSONObject;

public class CollectCard_1 extends com.qq.reader.module.bookstore.qnative.card.a {
    protected static final String JSON_KEY_BOOKLIST = "bookList";
    protected static final String JSON_KEY_JZCOUNT = "jzcount";
    protected static final String JSON_KEY_PROMOTION_NAME = "pushName";
    protected static final String JSON_KEY_TITLE = "title";
    int fisrt = 0;
    private LinearLayout mContainerView;
    private ArrayList<View> mItemList = new ArrayList();
    private LayoutInflater mLayoutInflater;
    int second = 0;

    public static class PraiseInfoTask extends ReaderProtocolJSONTask {
        public PraiseInfoTask(c cVar) {
            super(cVar);
            this.mUrl = e.j + "queryWeekFaverInfo?day=" + new SimpleDateFormat("yyyyMMdd").format(Long.valueOf(System.currentTimeMillis()));
        }
    }

    public static class PraiseNetTask extends ReaderProtocolJSONTask {
        public PraiseNetTask(c cVar, long j) {
            super(cVar);
            this.mUrl = e.j + "addBookFaver?bid=" + j;
        }
    }

    private class a {
        int a;
        int b;
        final /* synthetic */ CollectCard_1 c;

        private a(CollectCard_1 collectCard_1) {
            this.c = collectCard_1;
        }
    }

    public CollectCard_1(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localcollectcard_1;
    }

    public void attachView() {
        showStatics();
        this.mLayoutInflater = (LayoutInflater) ReaderApplication.getApplicationImp().getSystemService("layout_inflater");
        this.mContainerView = (LinearLayout) ap.a(getRootView(), R.id.book_topraise_list);
        ((CardTitle) ap.a(getRootView(), R.id.title_layout)).setCardTitle(37, this.mShowTitle, this.mPromotionName, null);
        if (this.mContainerView.getChildCount() >= 0) {
            this.mContainerView.removeAllViews();
        }
        if (this.mContainerView.getChildCount() <= 0) {
            this.mItemList.clear();
            int i = 0;
            while (i < getItemList().size()) {
                boolean z;
                if (this.fisrt == i || this.second == i) {
                    z = true;
                } else {
                    z = false;
                }
                addPariseView(i, z);
                i++;
            }
        }
    }

    private int[] getBiggestTwo() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getItemList().size(); i++) {
            a aVar = new a();
            aVar.a = i;
            aVar.b = ((g) getItemList().get(i)).t();
            arrayList.add(aVar);
        }
        Collections.sort(arrayList, new Comparator<a>(this) {
            final /* synthetic */ CollectCard_1 a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((a) obj, (a) obj2);
            }

            public int a(a aVar, a aVar2) {
                if (aVar.b > aVar2.b) {
                    return -1;
                }
                if (aVar.b < aVar2.b) {
                    return 1;
                }
                return 0;
            }
        });
        if (arrayList.size() > 2) {
            return new int[]{((a) arrayList.get(0)).a, ((a) arrayList.get(1)).a};
        } else if (arrayList.size() != 1) {
            return null;
        } else {
            return new int[]{((a) arrayList.get(0)).a};
        }
    }

    private void refrehAllView() {
        int i;
        int[] biggestTwo = getBiggestTwo();
        if (biggestTwo != null) {
            for (i = 0; i < biggestTwo.length; i++) {
                if (i == 0) {
                    this.fisrt = biggestTwo[i];
                } else if (i == 1) {
                    this.second = biggestTwo[i];
                }
            }
        }
        i = 0;
        while (i < getItemList().size()) {
            boolean z;
            if (this.fisrt == i || this.second == i) {
                z = true;
            } else {
                z = false;
            }
            refreshView(i, z);
            i++;
        }
    }

    private void refreshView(int i, boolean z) {
        if (this.mLayoutInflater != null) {
            View view = (View) this.mItemList.get(i);
            if (view != null) {
                final g gVar = (g) getItemList().get(i);
                TextView textView = (TextView) view.findViewById(R.id.book_name);
                ImageView imageView = (ImageView) view.findViewById(R.id.book_level);
                final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.book_parise);
                final TextView textView2 = (TextView) view.findViewById(R.id.book_parise_txt);
                final ImageView imageView2 = (ImageView) view.findViewById(R.id.book_parise_img);
                if (!z || gVar.t() <= 0) {
                    imageView.setVisibility(8);
                    textView.setMaxWidth(ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.free_item_text_max_width) + ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.free_item_icon_width));
                } else {
                    imageView.setVisibility(0);
                    textView.setMaxWidth(ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.free_item_text_max_width));
                }
                if (gVar.t() > 0) {
                    textView2.setText(gVar.u() + "");
                } else {
                    textView2.setText("集赞");
                }
                textView.setText(gVar.n());
                textView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ CollectCard_1 b;

                    public void onClick(View view) {
                        gVar.a(this.b.getEvnetListener());
                    }
                });
                linearLayout.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ CollectCard_1 e;

                    public void onClick(View view) {
                        if (this.e.isLogin()) {
                            this.e.sendParise(imageView2, textView2, linearLayout, gVar.m());
                        } else {
                            gVar.c(this.e.getEvnetListener());
                        }
                    }
                });
                if (gVar.j()) {
                    imageView2.setImageResource(R.drawable.collect_parise_press);
                    linearLayout.setBackgroundResource(R.drawable.localstore_common_btn_press);
                    textView2.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.textcolor_white));
                    return;
                }
                imageView2.setImageResource(R.drawable.collect_parise_normal);
                linearLayout.setBackgroundResource(R.drawable.localstore_common_btn_nor);
                textView2.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.skin_set_common_textcolor));
            }
        }
    }

    private void addPariseView(int i, boolean z) {
        if (this.mLayoutInflater != null) {
            View inflate = this.mLayoutInflater.inflate(R.layout.localcollectcard_0_item, null);
            this.mContainerView.addView(inflate);
            this.mItemList.add(inflate);
            final g gVar = (g) getItemList().get(i);
            TextView textView = (TextView) inflate.findViewById(R.id.book_name);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.book_level);
            final LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.book_parise);
            final TextView textView2 = (TextView) inflate.findViewById(R.id.book_parise_txt);
            final ImageView imageView2 = (ImageView) inflate.findViewById(R.id.book_parise_img);
            if (!z || gVar.t() <= 0) {
                imageView.setVisibility(8);
                textView.setMaxWidth(ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.free_item_text_max_width) + ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.free_item_icon_width));
            } else {
                imageView.setVisibility(0);
                textView.setMaxWidth(ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.free_item_text_max_width));
            }
            if (gVar.t() > 0) {
                textView2.setText(gVar.u() + "");
            } else {
                textView2.setText("集赞");
            }
            textView.setText(gVar.n());
            textView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ CollectCard_1 b;

                public void onClick(View view) {
                    gVar.a(this.b.getEvnetListener());
                    this.b.clickStatics();
                }
            });
            linearLayout.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ CollectCard_1 e;

                public void onClick(View view) {
                    this.e.clickStatics();
                    if (this.e.isLogin()) {
                        this.e.sendParise(imageView2, textView2, linearLayout, gVar.m());
                    } else {
                        gVar.c(this.e.getEvnetListener());
                    }
                }
            });
            if (gVar.j()) {
                imageView2.setImageResource(R.drawable.collect_parise_press);
                linearLayout.setBackgroundResource(R.drawable.localstore_common_btn_press);
                textView2.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.textcolor_white));
                return;
            }
            imageView2.setImageResource(R.drawable.collect_parise_normal);
            linearLayout.setBackgroundResource(R.drawable.localstore_common_btn_nor);
            textView2.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c301));
        }
    }

    private void sendParise(final ImageView imageView, final TextView textView, final LinearLayout linearLayout, long j) {
        com.qq.reader.common.readertask.g.a().a(new PraiseNetTask(new c(this) {
            final /* synthetic */ CollectCard_1 d;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    final String optString = jSONObject.optString(SocialConstants.PARAM_SEND_MSG);
                    final int optInt = jSONObject.optInt("bookcount");
                    int optInt2 = jSONObject.optInt("code");
                    if (optInt2 == -2) {
                        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass6 b;

                            public void run() {
                                af.a(ReaderApplication.getApplicationImp(), optString, 0).a();
                                com.qq.reader.module.bookstore.qnative.c cVar = new com.qq.reader.module.bookstore.qnative.c(null);
                                cVar.a().putInt("function_type", 3);
                                cVar.a(this.b.d.getEvnetListener());
                            }
                        });
                    } else if (optInt2 != -1) {
                        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass6 c;

                            public void run() {
                                af.a(ReaderApplication.getApplicationImp(), optString, 0).a();
                                imageView.setImageResource(R.drawable.collect_parise_press);
                                linearLayout.setBackgroundResource(R.drawable.localstore_common_btn_press);
                                textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.textcolor_white));
                                textView.setText("" + optInt);
                            }
                        });
                        this.d.getJzCount();
                    } else {
                        new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass6 b;

                            public void run() {
                                af.a(ReaderApplication.getApplicationImp(), optString, 0).a();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                exception.printStackTrace();
            }
        }, j));
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        if (getItemList() != null) {
            getItemList().clear();
        }
        this.mServerTitle = jSONObject.optString("title");
        this.mPromotionName = jSONObject.optString(JSON_KEY_PROMOTION_NAME);
        JSONArray optJSONArray = jSONObject.optJSONArray(JSON_KEY_BOOKLIST);
        if (optJSONArray == null) {
            return false;
        }
        int length = optJSONArray.length();
        if (length <= 0) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
            s gVar = new g();
            gVar.parseData(jSONObject2);
            addItem(gVar);
        }
        int[] biggestTwo = getBiggestTwo();
        if (biggestTwo != null) {
            while (i < biggestTwo.length) {
                if (i == 0) {
                    this.fisrt = biggestTwo[i];
                } else if (i == 1) {
                    this.second = biggestTwo[i];
                }
                i++;
            }
        }
        return true;
    }

    public boolean selfPrepareData() {
        getJzCount();
        return false;
    }

    private void getJzCount() {
        com.qq.reader.common.readertask.g.a().a(new PraiseInfoTask(new c(this) {
            final /* synthetic */ CollectCard_1 a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    JSONObject optJSONObject = jSONObject.optJSONObject("bookinfos");
                    JSONObject optJSONObject2 = jSONObject.optJSONObject("userinfo");
                    for (s sVar : this.a.getItemList()) {
                        g gVar = (g) sVar;
                        if (!(optJSONObject == null || optJSONObject.isNull("" + gVar.m()))) {
                            gVar.a(optJSONObject.optInt("" + gVar.m()));
                        }
                        if (optJSONObject2 == null || optJSONObject2.isNull("" + gVar.m())) {
                            gVar.a(false);
                        } else {
                            boolean z;
                            if (optJSONObject2.optInt("" + gVar.m()) > 0) {
                                z = true;
                            } else {
                                z = false;
                            }
                            gVar.a(z);
                        }
                    }
                    new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass7 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.refrehAllView();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                f.d("collect", "onConnectionError " + exception);
            }
        }));
    }

    private void showStatics() {
        i.a("event_shown_" + getCardId(), null, ReaderApplication.getApplicationImp());
    }

    private void clickStatics() {
        i.a("event_clicked_" + getCardId(), null, ReaderApplication.getApplicationImp());
    }
}
