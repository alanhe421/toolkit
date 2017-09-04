package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.m;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.text.SimpleDateFormat;
import org.json.JSONObject;

public class DetailCopyrightCard extends a {
    public DetailCopyrightCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_detail_copyright;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject == null) {
            return false;
        }
        s mVar = new m();
        mVar.parseData(jSONObject);
        getItemList().clear();
        addItem(mVar);
        return true;
    }

    public void attachView() {
        if (getItemList().size() > 0) {
            View a = ap.a(getRootView(), R.id.detail_common_title);
            if (a != null) {
                ((TextView) a.findViewById(R.id.title_name)).setText("更多图书信息");
            }
            m mVar = (m) getItemList().get(0);
            if (mVar.a() == 1) {
                ((LinearLayout) ap.a(getRootView(), R.id.ll_publish_sec)).setVisibility(0);
                fillInfoIntoTextview(R.id.tv_translator, "译者：", mVar.f());
                fillInfoIntoTextview(R.id.tv_publish_press, "出版社：", mVar.c());
                fillInfoIntoTextview(R.id.tv_publish_time, "上架时间：", formatTimeString(mVar.b()));
                if (((float) mVar.d()) / 100.0f > 0.0f) {
                    fillInfoIntoTextview(R.id.tv_paperbook_price, "纸质书价格：", String.valueOf(((float) mVar.d()) / 100.0f) + "元");
                } else {
                    fillInfoIntoTextview(R.id.tv_paperbook_price, "纸质书价格：", "");
                }
                fillInfoIntoTextview(R.id.tv_copyright_publish, "", ReaderApplication.getApplicationImp().getString(R.string.default_publish_copyright_info, new Object[]{ReaderApplication.getApplicationImp().getString(R.string.app_name)}));
            } else if (mVar.a() == 0) {
                ((LinearLayout) ap.a(getRootView(), R.id.ll_origin_sec)).setVisibility(0);
                fillInfoIntoTextview(R.id.tv_copyright_origin, "", ReaderApplication.getApplicationImp().getString(R.string.default_publish_copyright_info, new Object[]{ReaderApplication.getApplicationImp().getString(R.string.app_name)}));
                fillInfoIntoTextview(R.id.tv_origin_copyright, "版权：", mVar.e());
            }
        }
    }

    private void fillInfoIntoTextview(int i, String str, String str2) {
        CharSequence charSequence = str + str2;
        TextView textView = (TextView) ap.a(getRootView(), i);
        if (TextUtils.isEmpty(str2)) {
            textView.setVisibility(8);
        } else {
            textView.setText(charSequence);
        }
    }

    private String formatTimeString(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            str = new SimpleDateFormat("yyyy-MM-dd").format(simpleDateFormat.parse(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
