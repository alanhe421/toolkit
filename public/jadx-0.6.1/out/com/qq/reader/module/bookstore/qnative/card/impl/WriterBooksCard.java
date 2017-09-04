package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Intent;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.WebBrowserForContents;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigDetailActivity;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.view.scrollcover.FancyCoverFlow;
import com.qq.reader.view.scrollcover.FancyCoverFlow.LayoutParams;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import tencent.tls.platform.SigType;

public class WriterBooksCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private int active;
    private String authorId;
    private ArrayList<b> bookInfoItemList;
    private int isOwn;
    private int qaCount;
    private b selectedItem;

    private class a extends com.qq.reader.view.scrollcover.a {
        final /* synthetic */ WriterBooksCard a;
        private ArrayList<b> b = new ArrayList();

        public a(WriterBooksCard writerBooksCard, ArrayList<b> arrayList) {
            this.a = writerBooksCard;
            this.b = arrayList;
        }

        public View a(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(ReaderApplication.getApplicationImp(), R.layout.fancy_gallery_item_layout, null);
            }
            b bVar = (b) this.b.get(i);
            if (bVar != null) {
                view.setLayoutParams(new LayoutParams(ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.author_page_gallery_item_cover_width), ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.author_page_gallery_item_cover_height)));
                ImageView imageView = (ImageView) view.findViewById(R.id.img_bookcover);
                TextView textView = (TextView) view.findViewById(R.id.tv_score);
                textView.getPaint().setAntiAlias(true);
                if (this.a.selectedItem != null && bVar.m() != this.a.selectedItem.m()) {
                    textView.setVisibility(8);
                } else if (TextUtils.isEmpty(bVar.b)) {
                    textView.setVisibility(8);
                } else {
                    textView.setVisibility(0);
                    textView.setText(bVar.b);
                }
                c.a(this.a.getEvnetListener().getFromActivity()).a(ao.f(bVar.m()), imageView, com.qq.reader.common.imageloader.a.a().j());
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

    private class b extends g {
        public ArrayList<a> a;
        public String b;
        public boolean c;
        final /* synthetic */ WriterBooksCard d;

        private class a {
            public String a;
            final /* synthetic */ b b;

            private a(b bVar) {
                this.b = bVar;
            }
        }

        private b(WriterBooksCard writerBooksCard) {
            this.d = writerBooksCard;
            this.a = new ArrayList();
        }

        public void parseData(JSONObject jSONObject) {
            int i = 0;
            super.parseData(jSONObject);
            if (jSONObject != null) {
                JSONObject optJSONObject = jSONObject.optJSONObject("ext");
                this.a = new ArrayList();
                if (optJSONObject != null) {
                    this.c = optJSONObject.optBoolean("isNew", false);
                    JSONArray optJSONArray = optJSONObject.optJSONArray("fans");
                    while (optJSONArray != null && i < optJSONArray.length()) {
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                        if (optJSONObject2 != null) {
                            a aVar = new a();
                            aVar.a = optJSONObject2.optString(MessageKey.MSG_ICON);
                            this.a.add(aVar);
                        }
                        i++;
                    }
                    JSONObject optJSONObject3 = optJSONObject.optJSONObject("score");
                    if (optJSONObject3 != null) {
                        this.b = optJSONObject3.optString("scoretext");
                    }
                }
            }
        }
    }

    public WriterBooksCard(com.qq.reader.module.bookstore.qnative.page.b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.author_page_writer_books_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.isOwn = jSONObject.optInt("owner");
        JSONObject optJSONObject = jSONObject.optJSONObject("manitoInfo");
        this.active = optJSONObject.optInt("active");
        this.qaCount = optJSONObject.optInt("qaCount");
        this.bookInfoItemList = new ArrayList();
        if (optJSONObject != null) {
            this.authorId = optJSONObject.optJSONObject("info").optString("authorId");
            JSONArray optJSONArray = optJSONObject.optJSONArray("books");
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                return false;
            }
            parseBookInfoList(optJSONArray);
        }
        return true;
    }

    public void attachView() {
        Button button = (Button) ap.a(getRootView(), R.id.btn_more);
        if (this.bookInfoItemList == null || this.bookInfoItemList.size() <= 3) {
            button.setVisibility(8);
        } else {
            button.setVisibility(0);
            Map hashMap = new HashMap();
            hashMap.put("isOwn", String.valueOf(this.isOwn));
            i.a("event_D146", hashMap, ReaderApplication.getApplicationImp());
            button.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ WriterBooksCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.mMoreAction != null) {
                        this.a.mMoreAction.a().a().putString("KEY_ACTIONID", this.a.authorId);
                        this.a.mMoreAction.a(this.a.getEvnetListener());
                        Map hashMap = new HashMap();
                        hashMap.put("isOwn", String.valueOf(this.a.isOwn));
                        i.a("event_D147", hashMap, ReaderApplication.getApplicationImp());
                    }
                }
            });
        }
        if (this.bookInfoItemList != null && this.bookInfoItemList.size() > 0) {
            ((CardTitle) ap.a(getRootView(), R.id.card_title)).setCardTitle(37, "全部作品", this.bookInfoItemList.size() + "本", null);
            FancyCoverFlow fancyCoverFlow = (FancyCoverFlow) ap.a(getRootView(), R.id.fancyCoverFlow);
            fancyCoverFlow.setSelectedScale((((float) ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.author_page_gallery_item_scale_cover_height)) * 1.0f) / ((float) ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.author_page_gallery_item_cover_height)));
            fancyCoverFlow.setUnselectedScale(1.0f);
            fancyCoverFlow.setUnselectedSaturation(1.0f);
            fancyCoverFlow.setAdapter(new a(this, this.bookInfoItemList));
            if (this.bookInfoItemList.size() >= 3) {
                fancyCoverFlow.setSelection(1);
            } else {
                fancyCoverFlow.setSelection(0);
            }
            fancyCoverFlow.setOnItemClickListener(new OnItemClickListener(this) {
                final /* synthetic */ WriterBooksCard a;

                {
                    this.a = r1;
                }

                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    b bVar = (b) adapterView.getItemAtPosition(i);
                    if (bVar.m() == this.a.selectedItem.m()) {
                        this.a.goBookDetail(bVar.m());
                        Map hashMap = new HashMap();
                        hashMap.put("isOwn", String.valueOf(this.a.isOwn));
                        hashMap.put(s.ORIGIN, "11009");
                        i.a("event_D145", hashMap, ReaderApplication.getApplicationImp());
                        StatisticsManager.a().a("event_D145", hashMap);
                    }
                }
            });
            fancyCoverFlow.setOnItemSelectedListener(new OnItemSelectedListener(this) {
                final /* synthetic */ WriterBooksCard a;

                {
                    this.a = r1;
                }

                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                    try {
                        b bVar = (b) adapterView.getItemAtPosition(i);
                        this.a.updateBookInfo(bVar);
                        this.a.selectedItem = bVar;
                        this.a.notifyWrapperAdapter(adapterView.getAdapter());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }
        if (this.isOwn == 1) {
            ap.a(getRootView(), R.id.localstore_adv_divider).setVisibility(0);
        } else if (this.qaCount > 0 || this.active == 1) {
            ap.a(getRootView(), R.id.localstore_adv_divider).setVisibility(0);
        } else {
            ap.a(getRootView(), R.id.localstore_adv_divider).setVisibility(8);
        }
    }

    private CharSequence spellNumberAndUnit(String str, String str2) {
        CharSequence spannableString = new SpannableString(str + str2);
        if (!TextUtils.isEmpty(str)) {
            spannableString.setSpan(new AbsoluteSizeSpan(ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.text_size_class_5)), 0, str.length(), 34);
        }
        if (!TextUtils.isEmpty(str2)) {
            spannableString.setSpan(new AbsoluteSizeSpan(ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.text_size_class_2)), str.length(), str.length() + str2.length(), 34);
        }
        return spannableString;
    }

    private int getAuthorLevelResId(int i) {
        switch (i) {
            case 4:
                return R.drawable.card_platinum;
            case 5:
                return R.drawable.card_god;
            case 6:
                return R.drawable.card_star;
            case 7:
                return R.drawable.card_auther;
            default:
                return 0;
        }
    }

    private void notifyWrapperAdapter(Adapter adapter) {
        ((a) adapter).notifyDataSetChanged();
    }

    private void updateBookInfo(final b bVar) {
        if (bVar != null) {
            CharSequence charSequence;
            View rootView = getRootView();
            ap.a(rootView, R.id.ll_book_info).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ WriterBooksCard b;

                public void onClick(View view) {
                    this.b.goBookDetail(bVar.m());
                    i.a("event_D145", null, ReaderApplication.getApplicationImp());
                }
            });
            TextView textView = (TextView) ap.a(rootView, R.id.tv_bookname);
            textView.setText(bVar.n());
            TextView textView2 = (TextView) ap.a(rootView, R.id.tv_type_chapterinfo);
            String str = bVar.r() + " | ";
            if (bVar.i() > 0) {
                charSequence = str + "完结";
                if (bVar.e() > 0) {
                    charSequence = charSequence + "共" + bVar.e() + "章";
                }
            } else {
                charSequence = str + "连载";
                if (bVar.e() > 0) {
                    charSequence = charSequence + "至" + bVar.e() + "章";
                }
            }
            textView2.setText(charSequence);
            ((TextView) ap.a(rootView, R.id.tv_book_intro)).setText(bVar.s());
            initFansLayout();
            showFansLayout(bVar.a, bVar.m());
            if (bVar.c) {
                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.author_page_new_book_tag), null);
            } else {
                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            }
        }
    }

    private void initFansLayout() {
        int i = 0;
        ap.a(getRootView(), R.id.ll_no_fan).setVisibility(0);
        int[] iArr = new int[]{R.id.ll_fan_1, R.id.ll_fan_2, R.id.ll_fan_3};
        while (i < iArr.length) {
            ap.a(getRootView(), iArr[i]).setVisibility(8);
            i++;
        }
        ap.a(getRootView(), R.id.rl_fans).setOnClickListener(null);
    }

    private void showFansLayout(ArrayList<a> arrayList, final long j) {
        TextView textView = (TextView) ap.a(getRootView(), R.id.tv_nofan_tip);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.tv_fans_title);
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.img_fans_arrow);
        View a = ap.a(getRootView(), R.id.rl_fans);
        Map hashMap = new HashMap();
        hashMap.put("isOwn", String.valueOf(this.isOwn));
        i.a("event_D200", hashMap, ReaderApplication.getApplicationImp());
        if (arrayList == null || arrayList.size() <= 0) {
            textView.setVisibility(0);
            textView2.setVisibility(8);
            imageView.setVisibility(8);
            a.setEnabled(false);
            return;
        }
        textView.setVisibility(8);
        textView2.setVisibility(0);
        imageView.setVisibility(0);
        ap.a(getRootView(), R.id.ll_no_fan).setVisibility(8);
        a.setEnabled(true);
        a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WriterBooksCard b;

            public void onClick(View view) {
                Map hashMap = new HashMap();
                hashMap.put("isOwn", String.valueOf(this.b.isOwn));
                i.a("event_D149", hashMap, ReaderApplication.getApplicationImp());
                Intent intent = new Intent();
                intent.setClass(this.b.getEvnetListener().getFromActivity(), WebBrowserForContents.class);
                intent.putExtra("com.qq.reader.WebContent", e.e(this.b.getEvnetListener().getFromActivity(), j));
                com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
                this.b.getEvnetListener().getFromActivity().startActivity(intent);
            }
        });
        int[] iArr = new int[]{R.id.ll_fan_1, R.id.ll_fan_2, R.id.ll_fan_3};
        int[] iArr2 = new int[]{R.id.img_fan_icon_1, R.id.img_fan_icon_2, R.id.img_fan_icon_3};
        int i = 0;
        while (arrayList != null && i < arrayList.size() && i < iArr.length && i < iArr2.length) {
            a aVar = (a) arrayList.get(i);
            if (aVar != null) {
                ap.a(getRootView(), iArr[i]).setVisibility(0);
                c.a(getEvnetListener().getFromActivity()).a(aVar.a, (ImageView) ap.a(getRootView(), iArr2[i]), com.qq.reader.common.imageloader.a.a().b());
            }
            i++;
        }
    }

    private void goBookDetail(long j) {
        Intent intent = new Intent();
        intent.setFlags(SigType.TLS);
        intent.setClass(getEvnetListener().getFromActivity(), NativeBookStoreConfigDetailActivity.class);
        intent.putExtra("KEY_JUMP_PAGENAME", "DetailPage");
        intent.putExtra("URL_BUILD_PERE_BOOK_ID", j);
        com.qq.reader.common.utils.c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        getEvnetListener().getFromActivity().startActivity(intent);
    }

    private void parseBookInfoList(JSONArray jSONArray) {
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    b bVar = new b();
                    bVar.parseData(optJSONObject);
                    this.bookInfoItemList.add(bVar);
                }
            }
        }
    }
}
