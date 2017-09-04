package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.BaseCommentCard;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class ReplyLoadMoreCard extends BaseCommentCard {
    public static final String LOADMIDDLE = "loadmiddle";
    public static final String LOADPRE = "loadpre";
    private int mFloorIndex;
    private int mNextCount;
    private String strLoadText;

    public ReplyLoadMoreCard(b bVar, String str, int i, int i2, int i3) {
        super(bVar, str, i3);
        this.mFloorIndex = i;
        this.mNextCount = i2;
    }

    public int getResLayoutId() {
        return R.layout.replyloadmore;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        return true;
    }

    public boolean isDataReady() {
        return true;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.reply_more);
        View a = ap.a(getRootView(), R.id.reply_top_divider);
        if (LOADPRE.equals(this.mType)) {
            this.strLoadText = "查看更早的楼层";
            a.setVisibility(0);
        } else {
            this.strLoadText = "查看中间的楼层";
            a.setVisibility(8);
        }
        textView.setText(this.strLoadText);
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ReplyLoadMoreCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                ((TextView) view).setText("加载中...");
                Bundle bundle = new Bundle();
                bundle.putInt("function_type", 6);
                bundle.putString("KEY_JUMP_PAGENAME", "bookclubreply");
                bundle.putInt("floor_index", this.a.mFloorIndex);
                bundle.putInt("floor_next", this.a.mNextCount);
                if (this.a.getEvnetListener() != null) {
                    this.a.getEvnetListener().doFunction(bundle);
                }
            }
        });
    }

    public int getNextCount() {
        return this.mNextCount;
    }

    public int getFloorIndex() {
        return this.mFloorIndex;
    }

    public void setNextCount(int i) {
        this.mNextCount = i;
    }

    public void setFloorIndex(int i) {
        this.mFloorIndex = i;
    }
}
