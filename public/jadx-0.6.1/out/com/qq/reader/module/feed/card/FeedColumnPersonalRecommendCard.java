package com.qq.reader.module.feed.card;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.j;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.feed.data.impl.b;
import com.qq.reader.module.feed.mypreference.MyFeedPreferenceActivity;
import com.qq.reader.view.RoundImageView;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class FeedColumnPersonalRecommendCard extends FeedMultiClickBaseCard implements b {
    private ArrayList<a> columnRecommendModels = new ArrayList();
    private boolean mAttached = false;
    private int mBookIndex = 0;
    private ImageView mGeneIv;
    private TextView mLoginTextTv;
    private View mNoLoginDivider;
    private View mRecommendLayout1;
    private View mRecommendLayout2;
    private View mRecommendLayout3;
    private View mRecommendLayout4;
    private View mRecommendViewContainer;
    private RoundImageView mUserIcon;
    private View mYesLoginDivider;
    private View noLoginView;
    private View yesLoginView;

    public static class a {
        public String a;
        public int b;
        public String c;
        public ArrayList<String> d;
    }

    public FeedColumnPersonalRecommendCard(com.qq.reader.module.bookstore.qnative.page.b bVar, String str) {
        super(bVar, str);
    }

    public void attachView() {
        this.mAttached = true;
        this.yesLoginView = ap.a(getRootView(), R.id.yes_login_layout);
        this.noLoginView = ap.a(getRootView(), R.id.no_login_layout);
        this.mLoginTextTv = (TextView) ap.a(getRootView(), R.id.login_text);
        this.mUserIcon = (RoundImageView) ap.a(getRootView(), R.id.user_icon);
        this.mYesLoginDivider = ap.a(getRootView(), R.id.yes_login_divider);
        this.mNoLoginDivider = ap.a(getRootView(), R.id.no_login_divider);
        this.mRecommendViewContainer = ap.a(getRootView(), R.id.view_container);
        this.mRecommendLayout1 = ap.a(getRootView(), R.id.recommend_layout_1);
        this.mRecommendLayout2 = ap.a(getRootView(), R.id.recommend_layout_2);
        this.mRecommendLayout3 = ap.a(getRootView(), R.id.recommend_layout_3);
        this.mRecommendLayout4 = ap.a(getRootView(), R.id.recommend_layout_4);
        this.mRecommendLayout1.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedColumnPersonalRecommendCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_F25", null, ReaderApplication.getApplicationImp().getApplicationContext());
                this.a.goToQurl(((a) this.a.columnRecommendModels.get(0)).c, 0);
                this.a.mRecommendLayout1.setSelected(true);
                this.a.mRecommendLayout1.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.mRecommendLayout1.setSelected(false);
                    }
                }, 100);
            }
        });
        this.mRecommendLayout2.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedColumnPersonalRecommendCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_F27", null, ReaderApplication.getApplicationImp().getApplicationContext());
                this.a.goToQurl(((a) this.a.columnRecommendModels.get(1)).c, 1);
                this.a.mRecommendLayout2.setSelected(true);
                this.a.mRecommendLayout2.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.mRecommendLayout2.setSelected(false);
                    }
                }, 100);
            }
        });
        this.mRecommendLayout3.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedColumnPersonalRecommendCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_F29", null, ReaderApplication.getApplicationImp().getApplicationContext());
                this.a.goToQurl(((a) this.a.columnRecommendModels.get(2)).c, 2);
                this.a.mRecommendLayout3.setSelected(true);
                this.a.mRecommendLayout3.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.mRecommendLayout3.setSelected(false);
                    }
                }, 100);
            }
        });
        this.mRecommendLayout4.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedColumnPersonalRecommendCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_F31", null, ReaderApplication.getApplicationImp().getApplicationContext());
                this.a.goToQurl(((a) this.a.columnRecommendModels.get(3)).c, 3);
                this.a.mRecommendLayout4.setSelected(true);
                this.a.mRecommendLayout4.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass4 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.mRecommendLayout4.setSelected(false);
                    }
                }, 100);
            }
        });
        if (c.b()) {
            showContentView();
        } else {
            showLoginView();
        }
        if (this.columnRecommendModels == null || this.columnRecommendModels.size() < 4) {
            if (c.b()) {
                this.mYesLoginDivider.setVisibility(8);
            } else {
                this.mNoLoginDivider.setVisibility(8);
            }
            this.mRecommendViewContainer.setVisibility(8);
            return;
        }
        if (c.b()) {
            this.mYesLoginDivider.setVisibility(0);
        } else {
            this.mNoLoginDivider.setVisibility(0);
        }
        this.mRecommendViewContainer.setVisibility(0);
        i.a("event_F24", null, ReaderApplication.getApplicationImp().getApplicationContext());
        TextView textView = (TextView) ap.a(this.mRecommendLayout1, R.id.column_des);
        ImageView imageView = (ImageView) ap.a(this.mRecommendLayout1, R.id.column_cover);
        a aVar = (a) this.columnRecommendModels.get(0);
        ((TextView) ap.a(this.mRecommendLayout1, R.id.column_name)).setText(aVar.a);
        textView.setText("共" + j.d((long) aVar.b) + "册");
        setImage(imageView, ao.g(Long.valueOf((String) aVar.d.get(this.mBookIndex)).longValue()), null);
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, aVar.a);
        i.a("event_F131", hashMap, ReaderApplication.getApplicationImp().getApplicationContext());
        textView = (TextView) ap.a(this.mRecommendLayout2, R.id.column_des);
        imageView = (ImageView) ap.a(this.mRecommendLayout2, R.id.column_cover);
        aVar = (a) this.columnRecommendModels.get(1);
        ((TextView) ap.a(this.mRecommendLayout2, R.id.column_name)).setText(aVar.a);
        textView.setText("共" + j.d((long) aVar.b) + "册");
        setImage(imageView, ao.g(Long.valueOf((String) aVar.d.get(this.mBookIndex)).longValue()), null);
        Map hashMap2 = new HashMap();
        hashMap.put(s.ORIGIN, aVar.a);
        i.a("event_F131", hashMap2, ReaderApplication.getApplicationImp().getApplicationContext());
        textView = (TextView) ap.a(this.mRecommendLayout3, R.id.column_des);
        imageView = (ImageView) ap.a(this.mRecommendLayout3, R.id.column_cover);
        aVar = (a) this.columnRecommendModels.get(2);
        ((TextView) ap.a(this.mRecommendLayout3, R.id.column_name)).setText(aVar.a);
        textView.setText("共" + j.d((long) aVar.b) + "册");
        setImage(imageView, ao.g(Long.valueOf((String) aVar.d.get(this.mBookIndex)).longValue()), null);
        hashMap2 = new HashMap();
        hashMap.put(s.ORIGIN, aVar.a);
        i.a("event_F131", hashMap2, ReaderApplication.getApplicationImp().getApplicationContext());
        textView = (TextView) ap.a(this.mRecommendLayout4, R.id.column_des);
        imageView = (ImageView) ap.a(this.mRecommendLayout4, R.id.column_cover);
        aVar = (a) this.columnRecommendModels.get(3);
        ((TextView) ap.a(this.mRecommendLayout4, R.id.column_name)).setText(aVar.a);
        textView.setText("共" + j.d((long) aVar.b) + "册");
        setImage(imageView, ao.g(Long.valueOf((String) aVar.d.get(this.mBookIndex)).longValue()), null);
        hashMap2 = new HashMap();
        hashMap.put(s.ORIGIN, aVar.a);
        i.a("event_F131", hashMap2, ReaderApplication.getApplicationImp().getApplicationContext());
    }

    public int getResLayoutId() {
        return R.layout.feed_column_personal_recommend_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject == null || jSONObject.optInt("style") != 7) {
            return false;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray(MessageKey.MSG_CONTENT);
        if (optJSONArray != null && optJSONArray.length() > 0) {
            this.columnRecommendModels = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                a aVar = new a();
                aVar.a = optJSONObject.optString("catename");
                aVar.b = optJSONObject.optInt("bookNum");
                aVar.c = optJSONObject.optString("qurl");
                ArrayList arrayList = new ArrayList();
                JSONArray optJSONArray2 = optJSONObject.optJSONArray("bids");
                if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        arrayList.add(optJSONArray2.getString(i2));
                    }
                }
                aVar.d = arrayList;
                this.columnRecommendModels.add(aVar);
            }
        }
        return true;
    }

    public void showLoginView() {
        i.a("event_F22", null, ReaderApplication.getApplicationImp().getApplicationContext());
        if (this.noLoginView != null) {
            this.noLoginView.setVisibility(0);
            this.noLoginView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ FeedColumnPersonalRecommendCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    i.a("event_F23", null, ReaderApplication.getApplicationImp().getApplicationContext());
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("goLgoin", true);
                    bundle.putBoolean("fromFeedAction", true);
                    this.a.getEvnetListener().doFunction(bundle);
                }
            });
        }
        if (this.yesLoginView != null) {
            this.yesLoginView.setVisibility(8);
        }
    }

    public void showContentView() {
        i.a("event_F129", null, ReaderApplication.getApplicationImp().getApplicationContext());
        if (this.noLoginView != null) {
            this.noLoginView.setVisibility(8);
        }
        if (this.yesLoginView != null) {
            this.yesLoginView.setVisibility(0);
            this.yesLoginView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ FeedColumnPersonalRecommendCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.goToGene();
                    i.a("event_F130", null, ReaderApplication.getApplicationImp().getApplicationContext());
                }
            });
        }
        if (this.mLoginTextTv != null) {
            this.mLoginTextTv.setText(c.c().a() + "的私人定制");
        }
        if (this.mUserIcon != null) {
            String str = "";
            if (c.b()) {
                try {
                    str = c.c().b();
                } catch (Exception e) {
                }
                com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(str, this.mUserIcon, com.qq.reader.common.imageloader.a.a().b());
            }
        }
    }

    protected ArrayList<View> getViews() {
        ArrayList<View> arrayList = new ArrayList();
        arrayList.add(this.mRecommendLayout1);
        arrayList.add(this.mRecommendLayout2);
        arrayList.add(this.mRecommendLayout3);
        arrayList.add(this.mRecommendLayout4);
        return arrayList;
    }

    protected void doClick(final View view) {
        if (view != null) {
            view.setSelected(true);
            view.postDelayed(new Runnable(this) {
                final /* synthetic */ FeedColumnPersonalRecommendCard b;

                public void run() {
                    view.setSelected(false);
                }
            }, 100);
        }
    }

    public boolean swipeEnable() {
        return false;
    }

    public void change() {
        if (this.columnRecommendModels != null && this.columnRecommendModels.size() >= 1) {
            a aVar = (a) this.columnRecommendModels.get(0);
            if (aVar.d != null && aVar.d.size() >= 1) {
                this.mBookIndex++;
                if (this.mBookIndex == aVar.d.size()) {
                    this.mBookIndex = 0;
                }
                if (!this.mAttached) {
                }
            }
        }
    }

    private void goToQurl(String str, int i) {
        try {
            if (this.columnRecommendModels == null || i >= this.columnRecommendModels.size()) {
                com.qq.reader.qurl.c.a(getEvnetListener().getFromActivity(), str, null);
                return;
            }
            a aVar = (a) this.columnRecommendModels.get(i);
            ArrayList arrayList = aVar.d;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str).append("&bids=").append((String) arrayList.get(this.mBookIndex));
            com.qq.reader.qurl.c.a(getEvnetListener().getFromActivity(), stringBuilder.toString(), null);
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, aVar.a);
            i.a("event_F132", hashMap, ReaderApplication.getApplicationImp().getApplicationContext());
        } catch (Exception e) {
        }
    }

    private void goToGene() {
        com.qq.reader.module.bookstore.qnative.c.a evnetListener = getEvnetListener();
        if (evnetListener != null) {
            Context fromActivity = evnetListener.getFromActivity();
            if (fromActivity != null) {
                Intent intent = new Intent();
                intent.setClass(fromActivity, MyFeedPreferenceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("fromFeedAction", true);
                intent.putExtras(bundle);
                com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                fromActivity.startActivity(intent);
            }
        }
    }
}
