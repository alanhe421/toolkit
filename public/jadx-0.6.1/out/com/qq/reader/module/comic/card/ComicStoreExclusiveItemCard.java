package com.qq.reader.module.comic.card;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.protocol.FeedDataTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONArray;
import org.json.JSONObject;

public class ComicStoreExclusiveItemCard extends a {
    public static final String NET_AD_ATTR_ACTION_URL = "actionUrl";
    public static final String NET_AD_ATTR_ADVS = "adList";
    public static final String NET_AD_ATTR_BOOKINFO = "bookInfo";
    public static final String NET_AD_ATTR_CATE = "category";
    public static final String NET_AD_ATTR_COMICID = "comicId";
    public static final String NET_AD_ATTR_COMICINFO = "comicInfo";
    public static final String NET_AD_ATTR_COUNT = "count";
    public static final String NET_AD_ATTR_DES = "descr";
    public static final String NET_AD_ATTR_EXTINFO = "extInfo";
    public static final String NET_AD_ATTR_EXT_IMAGEURL = "extImage";
    public static final String NET_AD_ATTR_ID = "id";
    public static final String NET_AD_ATTR_IMAGE_URL = "imageUrl";
    public static final String NET_AD_ATTR_JASON = "readOnline";
    public static final String NET_AD_ATTR_OUT_OF_DATE = "expireDate";
    public static final String NET_AD_ATTR_POSITION = "position";
    public static final String NET_AD_ATTR_START_OF_DATE = "startDate";
    public static final String NET_AD_ATTR_TITLE = "title";
    public static final String NET_AD_ATTR_TYPE = "type";
    public static final String NET_AD_ATTR_VALUE_TYPE = "valueType";
    public static final String NET_AD_ATTR_VERSION = "version";
    public static final String NET_AD_BOOK_INTRO = "bookIntro";
    public static final String NET_AD_BOOK_READ_TIMES = "bookReadTimes";
    public static final String NET_AD_BOOK_TITLE = "bookTitle";
    private com.qq.reader.cservice.adv.a advertisement;
    private JSONObject bookInfo;
    private String bookIntro;
    private String bookName;
    private String bookReadNum;
    private String comicId;
    private String ivBackGroundUrl;

    public ComicStoreExclusiveItemCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.comic_store_exclusive_card;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(NET_AD_ATTR_ADVS);
            String string = jSONObject.getString(NET_AD_ATTR_VERSION);
            jSONObject.getString(NET_AD_ATTR_POSITION);
            if (jSONArray == null || jSONArray.length() == 0) {
                return false;
            }
            long j = 0;
            JSONObject jSONObject2 = (JSONObject) jSONArray.get(0);
            long longValue = Long.valueOf(jSONObject2.getString("id")).longValue();
            String string2 = jSONObject2.getString("type");
            int optInt = jSONObject2.optInt(NET_AD_ATTR_VALUE_TYPE, 5);
            String string3 = jSONObject2.getString(NET_AD_ATTR_CATE);
            String string4 = jSONObject2.getString("title");
            String string5 = jSONObject2.getString(NET_AD_ATTR_DES);
            String string6 = jSONObject2.getString(NET_AD_ATTR_COUNT);
            String string7 = jSONObject2.getString(NET_AD_ATTR_ACTION_URL);
            String string8 = jSONObject2.getString(NET_AD_ATTR_JASON);
            String string9 = jSONObject2.getString("imageUrl");
            long longValue2 = Long.valueOf(jSONObject2.getString(NET_AD_ATTR_OUT_OF_DATE)).longValue();
            try {
                j = Long.valueOf(jSONObject2.getString(NET_AD_ATTR_START_OF_DATE)).longValue();
            } catch (Exception e) {
            }
            this.advertisement = new com.qq.reader.cservice.adv.a(longValue, string2);
            this.advertisement.b(optInt);
            this.advertisement.a(string3);
            this.advertisement.d(string4);
            this.advertisement.g(string5);
            this.advertisement.b(string6);
            this.advertisement.f(getAdvLinkUrlAddParam(ReaderApplication.getApplicationContext(), string7));
            this.advertisement.c(string8);
            this.advertisement.e(string9);
            this.advertisement.a(longValue2);
            this.advertisement.b(j);
            this.advertisement.i(string);
            JSONObject optJSONObject = jSONObject2.optJSONObject(NET_AD_ATTR_EXTINFO);
            this.ivBackGroundUrl = optJSONObject.optString(NET_AD_ATTR_EXT_IMAGEURL);
            this.advertisement.h(optJSONObject.toString());
            this.comicId = jSONObject2.optJSONObject(NET_AD_ATTR_COMICINFO).optString(NET_AD_ATTR_COMICID);
            this.bookInfo = jSONObject2.optJSONObject(NET_AD_ATTR_BOOKINFO);
            if (this.bookInfo != null) {
                this.bookName = this.bookInfo.optString(NET_AD_BOOK_TITLE);
                this.bookReadNum = this.bookInfo.optString(NET_AD_BOOK_READ_TIMES);
                this.bookIntro = this.bookInfo.optString(NET_AD_BOOK_INTRO);
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void attachView() {
        View rootView = getRootView();
        ImageView imageView = (ImageView) rootView.findViewById(R.id.iv_exclusive_card_adv_pic);
        ImageView imageView2 = (ImageView) rootView.findViewById(R.id.iv_exclusive_card_cover_pic);
        TextView textView = (TextView) rootView.findViewById(R.id.tv_exclusive_card_message);
        RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.rl_book_info);
        TextView textView2 = (TextView) rootView.findViewById(R.id.tv_book_name);
        TextView textView3 = (TextView) rootView.findViewById(R.id.tv_book_read_num);
        TextView textView4 = (TextView) rootView.findViewById(R.id.tv_book_desc);
        c.a(getEvnetListener().getFromActivity()).a(this.advertisement.g(), (ImageView) rootView.findViewById(R.id.iv_exclusive_card_title), com.qq.reader.common.imageloader.a.a().j());
        c.a(getEvnetListener().getFromActivity()).a(this.ivBackGroundUrl, imageView, com.qq.reader.common.imageloader.a.a().j());
        c.a(getEvnetListener().getFromActivity()).a(ao.h(Long.parseLong(this.comicId)), imageView2, com.qq.reader.common.imageloader.a.a().j());
        textView.setText(this.advertisement.i());
        if (this.bookInfo != null) {
            relativeLayout.setVisibility(0);
            textView2.setText(this.bookName);
            if (TextUtils.isEmpty(this.bookReadNum)) {
                textView3.setVisibility(8);
            } else {
                textView3.setText(this.bookReadNum + "人看过");
            }
            textView4.setText(this.bookIntro);
        } else {
            relativeLayout.setVisibility(8);
        }
        rootView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ComicStoreExclusiveItemCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (com.qq.reader.qurl.c.a(this.a.advertisement.h())) {
                    try {
                        com.qq.reader.qurl.c.a(this.a.getEvnetListener().getFromActivity(), this.a.advertisement.h());
                        i.a("event_F221", null, ReaderApplication.getApplicationImp());
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                this.a.advertisement.w().a(this.a.getEvnetListener());
            }
        });
        i.a("event_F220", null, ReaderApplication.getApplicationImp());
    }

    private String getAdvLinkUrlAddParam(Context context, String str) {
        if (str == null || str.length() <= 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        if (str.indexOf("?") != -1) {
            stringBuffer.append("&");
        } else {
            stringBuffer.append("?");
        }
        stringBuffer.append("timi=");
        stringBuffer.append(d.z(context));
        stringBuffer.append(FeedDataTask.MS_SEX);
        stringBuffer.append(d.aU(context));
        return stringBuffer.toString();
    }
}
