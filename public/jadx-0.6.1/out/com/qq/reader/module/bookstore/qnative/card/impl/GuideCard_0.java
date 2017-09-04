package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.reader.module.feed.card.view.CardMoreView;
import com.tencent.feedback.proguard.R;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

public class GuideCard_0 extends a {
    private Context mContext = ReaderApplication.getApplicationImp();
    private String mExtTitle;
    private com.qq.reader.module.bookstore.qnative.item.a mLeftItem;
    private int mListSize;
    private com.qq.reader.module.bookstore.qnative.item.a mRightItem;
    private int showPicHeight = ((((com.qq.reader.common.c.a.bU - this.mContext.getResources().getDimensionPixelOffset(R.dimen.common_dp_42)) / 2) * 118) / Opcodes.REM_LONG);

    public GuideCard_0(b bVar, String str) {
        super(bVar, str);
    }

    public void attachView() {
        ((CardTitle) ap.a(getRootView(), R.id.title_layout)).setCardTitle(this.mIconIndex, this.mShowTitle, this.mExtTitle, null);
        CardMoreView cardMoreView = (CardMoreView) ap.a(getRootView(), R.id.localstore_moreaction);
        if (this.mMoreAction != null) {
            cardMoreView.setVisibility(0);
            cardMoreView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ GuideCard_0 a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.getEvnetListener() != null) {
                        if (this.a.mMoreAction.a() != null) {
                            Bundle a = this.a.mMoreAction.a().a();
                            if (a != null) {
                                a.putString("advid", a.getString("URL_BUILD_PERE_ADVS"));
                            }
                        }
                        this.a.mMoreAction.a(this.a.getEvnetListener());
                    }
                }
            });
            cardMoreView.setText(this.mMoreAction.e);
        } else {
            cardMoreView.setVisibility(8);
        }
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.left_img);
        imageView.getLayoutParams().height = this.showPicHeight;
        ImageView imageView2 = (ImageView) ap.a(getRootView(), R.id.right_img);
        imageView2.getLayoutParams().height = this.showPicHeight;
        TextView textView = (TextView) ap.a(getRootView(), R.id.left_name);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.right_name);
        TextView textView3 = (TextView) ap.a(getRootView(), R.id.left_count);
        TextView textView4 = (TextView) ap.a(getRootView(), R.id.right_count);
        View a = ap.a(getRootView(), R.id.left);
        View a2 = ap.a(getRootView(), R.id.right);
        if (getItemList().size() >= 2) {
            this.mLeftItem = (com.qq.reader.module.bookstore.qnative.item.a) getItemList().get(0);
            if (this.mLeftItem != null) {
                c.a(getEvnetListener().getFromActivity()).a(this.mLeftItem.c(), imageView, com.qq.reader.common.imageloader.a.a().j());
                textView.setText(this.mLeftItem.b());
                setItemCount(textView3, this.mLeftItem);
                a.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ GuideCard_0 a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        com.qq.reader.module.bookstore.qnative.c a = this.a.mLeftItem.a();
                        if (a != null) {
                            a.a(this.a.getEvnetListener());
                        }
                    }
                });
            }
            this.mRightItem = (com.qq.reader.module.bookstore.qnative.item.a) getItemList().get(1);
            if (this.mRightItem != null) {
                c.a(getEvnetListener().getFromActivity()).a(this.mRightItem.c(), imageView2, com.qq.reader.common.imageloader.a.a().j());
                textView2.setText(this.mRightItem.b());
                setItemCount(textView4, this.mRightItem);
                a2.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ GuideCard_0 a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        com.qq.reader.module.bookstore.qnative.c a = this.a.mRightItem.a();
                        if (a != null) {
                            a.a(this.a.getEvnetListener());
                        }
                    }
                });
            }
        }
    }

    private void setItemCount(TextView textView, com.qq.reader.module.bookstore.qnative.item.a aVar) {
        if (aVar.d().equalsIgnoreCase("topic") || aVar.d().equalsIgnoreCase("webpage")) {
            textView.setText(aVar.f() + "人读过");
        } else {
            textView.setText(aVar.e() + "人收藏");
        }
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_guide_0;
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        long optLong;
        if (jSONObject.has("updateTime")) {
            optLong = jSONObject.optLong("updateTime");
        } else {
            optLong = 0;
        }
        if (optLong > 0) {
            this.mExtTitle = new SimpleDateFormat("MM月dd日更新").format(new Date(optLong * 1000));
        }
        getItemList().clear();
        JSONArray jSONArray = jSONObject.getJSONArray(ComicStoreExclusiveItemCard.NET_AD_ATTR_ADVS);
        for (int i = 0; i < jSONArray.length(); i++) {
            s aVar = new com.qq.reader.module.bookstore.qnative.item.a();
            aVar.parseData(jSONArray.getJSONObject(i));
            addItem(aVar);
        }
        initRandomItem();
        return true;
    }

    public void refresh() {
        initRandomItem();
    }

    private void initRandomItem() {
        this.mListSize = getItemList().size();
        if (this.mListSize != 0) {
        }
    }
}
