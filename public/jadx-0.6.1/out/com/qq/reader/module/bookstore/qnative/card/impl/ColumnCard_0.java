package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.item.ColumnWebItem;
import com.qq.reader.module.bookstore.qweb.channel.ColumnActivity;
import com.qq.reader.module.bookstore.qweb.channel.ColumnWebEntity;
import com.qq.reader.module.bookstore.qweb.channel.OtherGridView;
import com.tencent.feedback.proguard.R;
import com.tencent.util.WeakReferenceHandler;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import tencent.tls.platform.SigType;

public class ColumnCard_0 extends com.qq.reader.module.bookstore.qnative.card.a implements Callback, OnItemClickListener {
    private static final int FIRSTLINE_COLUMN_MAX_COUNT = 4;
    private static final int MESSAGE_FIRST = 1;
    private static final int MESSAGE_OTHER = 2;
    private Context mContext = ReaderApplication.getApplicationImp();
    private a mFirstLineAdapter = new a(this);
    private List<ColumnWebItem> mFirstLineList = new ArrayList();
    WeakReferenceHandler mHandler = new WeakReferenceHandler(this);
    private b mOtherLineAdapter = new b(this);
    private List<ColumnWebItem> mOtherLineList = new ArrayList();
    private List<ColumnWebItem> mTabList = new ArrayList();
    private int sexType;

    class a extends BaseAdapter {
        final /* synthetic */ ColumnCard_0 a;

        a(ColumnCard_0 columnCard_0) {
            this.a = columnCard_0;
        }

        public int getCount() {
            if (this.a.mFirstLineList.size() > 0) {
                return this.a.mFirstLineList.size() + 1;
            }
            return this.a.mFirstLineList.size();
        }

        public Object getItem(int i) {
            if (i < this.a.mFirstLineList.size()) {
                return this.a.mFirstLineList.get(i);
            }
            return null;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(this.a.mContext).inflate(R.layout.localstore_card_column_0_firstline_item, null);
            }
            TextView textView = (TextView) view.findViewById(R.id.text);
            ImageView imageView = (ImageView) view.findViewById(R.id.img);
            if (i < this.a.mFirstLineList.size()) {
                textView.setText(((ColumnWebItem) this.a.mTabList.get(i)).getTitleName());
                if (((ColumnWebItem) this.a.mFirstLineList.get(i)).getTitleName().equalsIgnoreCase("包月")) {
                    imageView.setImageResource(R.drawable.localstore_month_channel);
                }
                if (((ColumnWebItem) this.a.mFirstLineList.get(i)).getTitleName().equalsIgnoreCase("免费")) {
                    imageView.setImageResource(R.drawable.localstore_free_channel);
                }
                if (i == 2) {
                    imageView.setImageResource(R.drawable.localstore_one_channel);
                }
                if (i == 3) {
                    imageView.setImageResource(R.drawable.localstore_two_channel);
                }
            } else if (this.a.mTabList.size() <= 4) {
                textView.setText("关注");
                imageView.setImageResource(R.drawable.channel_focus);
                view.setTag("add");
            } else {
                textView.setText("更多");
                if (this.a.getHideLayoutDivider().getVisibility() == 0) {
                    imageView.setImageResource(R.drawable.channel_close);
                } else {
                    imageView.setImageResource(R.drawable.channel_open);
                }
                view.setTag("more");
            }
            return view;
        }
    }

    class b extends BaseAdapter {
        final /* synthetic */ ColumnCard_0 a;

        b(ColumnCard_0 columnCard_0) {
            this.a = columnCard_0;
        }

        public int getCount() {
            return this.a.mOtherLineList.size() + 1;
        }

        public Object getItem(int i) {
            if (i < this.a.mOtherLineList.size()) {
                return this.a.mOtherLineList.get(i);
            }
            return null;
        }

        public int getItemViewType(int i) {
            if (i == getCount() - 1) {
                return 1;
            }
            return 0;
        }

        public int getViewTypeCount() {
            return 2;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            switch (getItemViewType(i)) {
                case 0:
                    if (view == null) {
                        view = LayoutInflater.from(this.a.mContext).inflate(R.layout.localstore_card_column_0_otherline_item, null);
                    }
                    TextView textView = (TextView) view.findViewById(R.id.text);
                    if (i >= this.a.mOtherLineList.size()) {
                        return view;
                    }
                    textView.setTextColor(this.a.mContext.getResources().getColor(R.color.localstore_textcolor_black));
                    textView.setText(((ColumnWebItem) this.a.mOtherLineList.get(i)).getTitleName());
                    return view;
                case 1:
                    if (view == null) {
                        return LayoutInflater.from(this.a.mContext).inflate(R.layout.localstore_card_column_0_otherline_item_last, null);
                    }
                    return view;
                default:
                    return view;
            }
        }
    }

    public ColumnCard_0(com.qq.reader.module.bookstore.qnative.page.b bVar, String str) {
        super(bVar, str);
    }

    public void build(JSONObject jSONObject) {
        super.build(jSONObject);
        this.sexType = jSONObject.optInt("sex");
    }

    public void attachView() {
        OtherGridView otherGridView = (OtherGridView) ap.a(getRootView(), R.id.otherGridView_1);
        OtherGridView otherGridView2 = (OtherGridView) ap.a(getRootView(), R.id.otherGridView_2);
        otherGridView.setAdapter(this.mFirstLineAdapter);
        otherGridView2.setAdapter(this.mOtherLineAdapter);
        otherGridView.setOnItemClickListener(this);
        otherGridView2.setOnItemClickListener(this);
        refresh();
    }

    private View getHideLayoutDivider() {
        return ap.a(getRootView(), R.id.hideLayout);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_column_0;
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        int i;
        int i2;
        int i3 = 0;
        ArrayList readLocalData = readLocalData();
        j.q = readLocalData.size();
        if (this.mTabList == null) {
            boolean z = true;
        } else if (this.mTabList.size() == readLocalData.size()) {
            i = 0;
            for (i2 = 0; i2 < this.mTabList.size(); i2++) {
                if (((ColumnWebItem) this.mTabList.get(i2)).getTitleid() != ((ColumnWebEntity) readLocalData.get(i2)).getTitleid()) {
                    i = true;
                }
            }
        } else {
            i = true;
        }
        if (i != 0) {
            this.mFirstLineList.clear();
            this.mOtherLineList.clear();
            this.mTabList.clear();
            for (i2 = 0; i2 < readLocalData.size(); i2++) {
                ColumnWebItem columnWebItem = new ColumnWebItem((ColumnWebEntity) readLocalData.get(i2));
                columnWebItem.parseData("-1,-1,6");
                this.mTabList.add(columnWebItem);
            }
            if (readLocalData.size() > 4) {
                while (i3 < 4) {
                    this.mFirstLineList.add(this.mTabList.get(i3));
                    i3++;
                }
                for (int i4 = 4; i4 < readLocalData.size(); i4++) {
                    this.mOtherLineList.add(this.mTabList.get(i4));
                }
            } else {
                while (i3 < this.mTabList.size()) {
                    this.mFirstLineList.add(this.mTabList.get(i3));
                    i3++;
                }
            }
        }
        return true;
    }

    private void refreshAdapter() {
        if (this.mFirstLineAdapter != null && this.mOtherLineAdapter != null) {
            this.mHandler.sendEmptyMessage(1);
            this.mHandler.sendEmptyMessage(2);
        }
    }

    private ArrayList<ColumnWebEntity> readLocalData() {
        if (d.aS(this.mContext) == 0) {
            return com.qq.reader.module.bookstore.qweb.channel.a.a(this.mContext, this.mSex);
        }
        return com.qq.reader.module.bookstore.qweb.channel.d.a().a(Boolean.valueOf(true));
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.mFirstLineAdapter.notifyDataSetChanged();
                break;
            case 2:
                this.mOtherLineAdapter.notifyDataSetChanged();
                if (this.mOtherLineList.size() == 0 && getHideLayoutDivider() != null) {
                    getHideLayoutDivider().setVisibility(8);
                    break;
                }
        }
        return true;
    }

    public void refresh() {
        try {
            parseData(null);
            refreshAdapter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        c cVar = new c(null);
        Bundle a = cVar.a();
        ColumnWebItem columnWebItem;
        Intent intent;
        switch (adapterView.getId()) {
            case R.id.otherGridView_2:
                if (i < this.mOtherLineList.size()) {
                    columnWebItem = (ColumnWebItem) this.mOtherLineList.get(i);
                    columnWebItem.getBindAction().a(getEvnetListener());
                    updateServerLog(columnWebItem.getTitleid(), columnWebItem.getWebid());
                    return;
                }
                i.a("event_C18", null, this.mContext);
                StatisticsManager.a().a("event_C18", null);
                j.a(17, 2);
                intent = new Intent();
                intent.setFlags(SigType.TLS);
                intent.setClass(this.mContext, ColumnActivity.class);
                this.mContext.startActivity(intent);
                return;
            case R.id.otherGridView_1:
                if (i < this.mFirstLineList.size()) {
                    columnWebItem = (ColumnWebItem) this.mFirstLineList.get(i);
                    updateServerLog(columnWebItem.getTitleid(), columnWebItem.getWebid());
                    String str;
                    switch (i) {
                        case 0:
                            str = "PayMonth_Boy";
                            switch (this.sexType) {
                                case 1:
                                    str = "PayMonth_Boy";
                                    break;
                                case 2:
                                    str = "PayMonth_Girl";
                                    break;
                                case 3:
                                    str = "PayMonth_Publish";
                                    break;
                            }
                            a.putString("KEY_JUMP_PAGENAME", str);
                            a.putString("LOCAL_STORE_IN_TITLE", columnWebItem.getTitleName());
                            cVar.a(getEvnetListener());
                            return;
                        case 1:
                            str = "FreePage_Boy";
                            switch (this.sexType) {
                                case 1:
                                    str = "FreePage_Boy";
                                    break;
                                case 2:
                                    str = "FreePage_Girl";
                                    break;
                                case 3:
                                    str = "FreePage_Publish";
                                    break;
                            }
                            a.putString("KEY_JUMP_PAGENAME", str);
                            a.putString("LOCAL_STORE_IN_TITLE", columnWebItem.getTitleName());
                            cVar.a(getEvnetListener());
                            return;
                        default:
                            columnWebItem.getBindAction().a(getEvnetListener());
                            return;
                    }
                }
                ImageView imageView = (ImageView) view.findViewById(R.id.img);
                if (((String) view.getTag()).equals("more")) {
                    i.a("event_C45", null, this.mContext);
                    StatisticsManager.a().a("event_C45", null);
                    j.a(44, 2);
                    if (getHideLayoutDivider().getVisibility() == 8) {
                        getHideLayoutDivider().setVisibility(0);
                        imageView.setImageResource(R.drawable.channel_close);
                        return;
                    }
                    getHideLayoutDivider().setVisibility(8);
                    imageView.setImageResource(R.drawable.channel_open);
                    return;
                }
                i.a("event_C18", null, this.mContext);
                StatisticsManager.a().a("event_C18", null);
                j.a(17, 2);
                intent = new Intent();
                intent.setFlags(SigType.TLS);
                intent.setClass(this.mContext, ColumnActivity.class);
                this.mContext.startActivity(intent);
                return;
            default:
                return;
        }
    }

    public boolean selfPrepareData() {
        try {
            fillData(new JSONObject());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isExpired() {
        return false;
    }

    private void updateServerLog(int i, int i2) {
        if (i2 == 0) {
            switch (i) {
                case 10003:
                    i.a("event_C25", null, this.mContext);
                    StatisticsManager.a().a("event_C25", null);
                    return;
                case 10004:
                    i.a("event_C26", null, this.mContext);
                    StatisticsManager.a().a("event_C26", null);
                    return;
                default:
                    return;
            }
        }
        switch (i2) {
            case 10006:
                i.a("event_C42", null, this.mContext);
                StatisticsManager.a().a("event_C42", null);
                return;
            case 10011:
                i.a("event_C41", null, this.mContext);
                StatisticsManager.a().a("event_C41", null);
                return;
            case 10018:
                i.a("event_C39", null, this.mContext);
                StatisticsManager.a().a("event_C39", null);
                return;
            case 10070:
                i.a("event_C40", null, this.mContext);
                StatisticsManager.a().a("event_C40", null);
                return;
            case 10076:
                i.a("event_C43", null, this.mContext);
                StatisticsManager.a().a("event_C43", null);
                return;
            case 20001:
                i.a("event_C28", null, this.mContext);
                StatisticsManager.a().a("event_C28", null);
                return;
            case 20005:
                i.a("event_C48", null, this.mContext);
                StatisticsManager.a().a("event_C48", null);
                return;
            case 20010:
                i.a("event_C49", null, this.mContext);
                StatisticsManager.a().a("event_C49", null);
                return;
            case 20014:
                i.a("event_C29", null, this.mContext);
                StatisticsManager.a().a("event_C29", null);
                return;
            case 20019:
                i.a("event_C33", null, this.mContext);
                StatisticsManager.a().a("event_C33", null);
                return;
            case 20028:
                i.a("event_C31", null, this.mContext);
                StatisticsManager.a().a("event_C31", null);
                return;
            case 20032:
                i.a("event_C53", null, this.mContext);
                StatisticsManager.a().a("event_C53", null);
                return;
            case 20037:
                i.a("event_C32", null, this.mContext);
                StatisticsManager.a().a("event_C32", null);
                return;
            case 20042:
                i.a("event_C52", null, this.mContext);
                StatisticsManager.a().a("event_C52", null);
                return;
            case 20050:
                i.a("event_C30", null, this.mContext);
                StatisticsManager.a().a("event_C30", null);
                return;
            case 20054:
                i.a("event_C51", null, this.mContext);
                StatisticsManager.a().a("event_C51", null);
                return;
            case 20065:
                i.a("event_C50", null, this.mContext);
                StatisticsManager.a().a("event_C50", null);
                return;
            case 30001:
                i.a("event_C36", null, this.mContext);
                StatisticsManager.a().a("event_C36", null);
                return;
            case 30008:
                i.a("event_C37", null, this.mContext);
                StatisticsManager.a().a("event_C37", null);
                return;
            case 30013:
                i.a("event_C34", null, this.mContext);
                StatisticsManager.a().a("event_C34", null);
                return;
            case 30020:
                i.a("event_C35", null, this.mContext);
                StatisticsManager.a().a("event_C35", null);
                return;
            case 30031:
                i.a("event_C38", null, this.mContext);
                StatisticsManager.a().a("event_C38", null);
                return;
            default:
                return;
        }
    }
}
