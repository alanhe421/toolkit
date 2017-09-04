package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.module.bookstore.qnative.activity.NativeSearchToolMainActivity;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.widget.cloudtag.CloudSurfaceView;
import com.tencent.feedback.proguard.R;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class SearchToolCloudCard extends a {
    private String mBookNum;
    private ArrayList<String> mTagList = new ArrayList();

    public SearchToolCloudCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.search_tool_cloud_ui;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        this.mBookNum = jSONObject.optString("bookCount");
        try {
            this.mBookNum = new DecimalFormat("###,###").format(new BigDecimal(this.mBookNum));
        } catch (Exception e) {
            c.e("SearchToolCloudCard", e.getMessage());
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("keywords");
        if (optJSONArray != null) {
            this.mTagList.clear();
            String[] split = optJSONArray.toString().replace("\"", "").replace("[", "").replace("]", "").split(",");
            while (split != null && i < split.length) {
                CharSequence trim = split[i].trim();
                if (!TextUtils.isEmpty(trim)) {
                    this.mTagList.add(trim);
                }
                i++;
            }
        } else {
            this.mTagList.clear();
            String[] split2 = ReaderApplication.getApplicationImp().getResources().getString(R.string.cloud_keywords_default).replace("\"", "").split(",");
            int i2 = 0;
            while (split2 != null && i2 < split2.length) {
                CharSequence trim2 = split2[i2].trim();
                if (!TextUtils.isEmpty(trim2)) {
                    this.mTagList.add(trim2);
                }
                i2++;
            }
        }
        return true;
    }

    public void attachView() {
        View rootView = getRootView();
        TextView textView = (TextView) rootView.findViewById(R.id.tv_book_count);
        CharSequence spannableString = new SpannableString(this.mBookNum + " 册好书 想读就读");
        spannableString.setSpan(new AbsoluteSizeSpan(ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.search_tool_main_bookcount_textsize)), 0, this.mBookNum.length(), 33);
        textView.setText(spannableString);
        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.ll_cloud_container);
        linearLayout.removeAllViews();
        if (this.mTagList != null && this.mTagList.size() > 0) {
            View cloudSurfaceView = new CloudSurfaceView(rootView.getContext());
            cloudSurfaceView.setBookNames(this.mTagList);
            cloudSurfaceView.setLayoutParams(new LayoutParams(-1, -1));
            cloudSurfaceView.requestFocus();
            linearLayout.addView(cloudSurfaceView);
            NativeSearchToolMainActivity.k = cloudSurfaceView;
        }
    }
}
