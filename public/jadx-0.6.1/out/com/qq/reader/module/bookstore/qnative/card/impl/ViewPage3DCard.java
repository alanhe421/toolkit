package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.item.b;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.view.FlowIndicator;
import com.qq.reader.view.scrollcover.FancyCoverFlow;
import com.qq.reader.view.scrollcover.FancyCoverFlow.LayoutParams;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ViewPage3DCard extends com.qq.reader.module.bookstore.qnative.card.a {
    public static final int EXTERNAL_ADD_ITEMS = 4;
    protected static final String JSON_KEY_ADVLIST = "adList";
    protected static final String JSON_KEY_PROMOTION_NAME = "pushName";
    private static final int MIN_NUMS = 3;
    c mAction;

    private class a extends com.qq.reader.view.scrollcover.a {
        final /* synthetic */ ViewPage3DCard a;
        private ArrayList<s> b = new ArrayList();

        public a(ViewPage3DCard viewPage3DCard, List<s> list) {
            this.a = viewPage3DCard;
            for (int i = 0; i < list.size(); i++) {
                this.b.add(list.get(i));
            }
            if (list != null && list.size() >= 2) {
                s sVar = (s) this.b.get(1);
                s sVar2 = (s) list.get(this.b.size() - 1);
                s sVar3 = (s) list.get(this.b.size() - 2);
                this.b.add((s) this.b.get(0));
                this.b.add(sVar);
                this.b.add(0, sVar2);
                this.b.add(0, sVar3);
            }
        }

        public View a(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(ReaderApplication.getApplicationImp(), R.layout.fancy_gallery_3ditem_layout, null);
            }
            b bVar = (b) this.b.get(i);
            if (bVar != null) {
                view.setLayoutParams(new LayoutParams(ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.viewpage3d_width), ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.viewpage3d_height)));
                ImageView imageView = (ImageView) view.findViewById(R.id.img_bookcover);
                TextView textView = (TextView) view.findViewById(R.id.info);
                ((TextView) view.findViewById(R.id.title)).setText(bVar.c());
                textView.setText(bVar.d());
                com.qq.reader.common.imageloader.c.a(this.a.getEvnetListener().getFromActivity()).a(bVar.f(), imageView, com.qq.reader.common.imageloader.a.a().j());
            }
            return view;
        }

        public int getCount() {
            if (this.b != null) {
                return this.b.size();
            }
            return 0;
        }

        public Object getItem(int i) {
            if (this.b == null || i >= this.b.size()) {
                return null;
            }
            return this.b.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public int a() {
            return R.id.img_mask;
        }
    }

    public ViewPage3DCard(com.qq.reader.module.bookstore.qnative.page.b bVar, String str) {
        super(bVar, str);
    }

    public void build(JSONObject jSONObject) {
        super.build(jSONObject);
        String optString = jSONObject.optString("jumpPageName");
        String optString2 = jSONObject.optString("controllerTitle");
        if (optString.length() > 0) {
            this.mAction = new c(null);
            Bundle a = this.mAction.a();
            a.putString("LOCAL_STORE_IN_TITLE", optString2);
            a.putString("KEY_JUMP_PAGENAME", optString);
        }
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_viewpage3dcard;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        getItemList().clear();
        this.mPromotionName = jSONObject.optString(JSON_KEY_PROMOTION_NAME);
        JSONArray optJSONArray = jSONObject.optJSONArray("adList");
        if (optJSONArray == null) {
            return false;
        }
        int length = optJSONArray.length();
        if (length <= 0 || length < 3) {
            return false;
        }
        while (i < length) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            s bVar = new b();
            bVar.parseData(jSONObject2);
            addItem(bVar);
            i++;
        }
        return true;
    }

    public void attachView() {
        View rootView = getRootView();
        Button button = (Button) ap.a(rootView, R.id.btn_more);
        final TextView textView = (TextView) ap.a(rootView, R.id.info1);
        final TextView textView2 = (TextView) ap.a(rootView, R.id.info2);
        final List itemList = getItemList();
        if (itemList.size() >= 3) {
            if (itemList == null || itemList.size() <= this.mDispaly) {
                button.setVisibility(8);
            } else {
                button.setVisibility(0);
                button.setText(this.mMoreAction.e);
                button.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ViewPage3DCard a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        if (this.a.mAction != null && this.a.getEvnetListener() != null) {
                            this.a.mAction.a(this.a.getEvnetListener());
                        }
                    }
                });
            }
            if (itemList != null && itemList.size() > 0) {
                CardTitle cardTitle = (CardTitle) ap.a(getRootView(), R.id.card_title);
                cardTitle.setCardTitle(0, this.mShowTitle, this.mPromotionName, null);
                if (TextUtils.isEmpty(this.mShowTitle)) {
                    cardTitle.setVisibility(8);
                }
                final FancyCoverFlow fancyCoverFlow = (FancyCoverFlow) ap.a(getRootView(), R.id.fancyCoverFlow);
                fancyCoverFlow.setSelectedScale((((float) ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.author_page_gallery_item_scale_cover_height)) * 1.0f) / ((float) ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.author_page_gallery_item_cover_height)));
                fancyCoverFlow.setUnselectedScale(1.0f);
                fancyCoverFlow.setUnselectedSaturation(1.0f);
                fancyCoverFlow.setAdapter(new a(this, itemList));
                final FlowIndicator flowIndicator = (FlowIndicator) ap.a(getRootView(), R.id.flowindicator);
                flowIndicator.setSelectedColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c301));
                flowIndicator.setUnSelectedColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c801));
                flowIndicator.setRadius(ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.common_dp_3));
                flowIndicator.setSpace(ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.common_dp_4));
                flowIndicator.setSize(itemList.size());
                fancyCoverFlow.setOnitemSelectListener(new com.qq.reader.view.scrollcover.FancyCoverFlow.a(this) {
                    final /* synthetic */ ViewPage3DCard e;

                    public void a(int i) {
                        int i2 = i - 2;
                        if (i2 >= 0 && i2 < itemList.size()) {
                            b bVar = (b) itemList.get(i2);
                            textView.setText(bVar.c());
                            textView2.setText(bVar.d());
                            flowIndicator.setCurrent(i2);
                        }
                    }
                });
                fancyCoverFlow.setSelection(3);
                textView.setText(((b) itemList.get(1)).c());
                textView2.setText(((b) itemList.get(1)).d());
                ap.a(rootView, R.id.info_area).setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ViewPage3DCard b;

                    public void onClick(View view) {
                        this.b.handdleClick(fancyCoverFlow.getSelectedItemPosition());
                    }
                });
                fancyCoverFlow.setOnItemClickListener(new OnItemClickListener(this) {
                    final /* synthetic */ ViewPage3DCard a;

                    {
                        this.a = r1;
                    }

                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        this.a.handdleClick(i);
                    }
                });
            }
        }
        showStatics();
    }

    private void handdleClick(int i) {
        int i2 = i - 2;
        List itemList = getItemList();
        if (itemList != null && i2 >= 0 && i2 < itemList.size()) {
            b bVar = (b) itemList.get(i2);
            if (bVar != null && !TextUtils.isEmpty(bVar.e())) {
                try {
                    com.qq.reader.qurl.c.a(getEvnetListener().getFromActivity(), bVar.e(), null);
                    clickStatics();
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
                }
            }
        }
    }

    private void showStatics() {
        i.a("event_shown_" + getCardId(), null, ReaderApplication.getApplicationImp());
    }

    private void clickStatics() {
        i.a("event_clicked_" + getCardId(), null, ReaderApplication.getApplicationImp());
    }
}
