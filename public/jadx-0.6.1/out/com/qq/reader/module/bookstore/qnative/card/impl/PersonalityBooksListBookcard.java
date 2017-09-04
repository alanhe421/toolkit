package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.protocol.H5GameChargeTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.k;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreTwoLevelActivity;
import com.qq.reader.module.bookstore.qnative.card.BaseListCard;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentForPersonality;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.item.v;
import com.qq.reader.module.readpage.voteview.net.GetVoteUserIconsTask;
import com.qq.reader.qurl.c;
import com.qq.reader.view.ap;
import com.qq.reader.view.pullupdownlist.XListView;
import com.tencent.feedback.proguard.R;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PersonalityBooksListBookcard extends ListCardCommon {
    private boolean mHasClicked = false;

    public interface a {
        void a(String str, boolean z);
    }

    public class b extends v {
        final /* synthetic */ PersonalityBooksListBookcard a;
        private int b = -1;
        private boolean c = false;
        private String d;
        private String m;
        private HashMap<String, String> n = new HashMap();
        private int o;
        private String p;
        private long q;
        private boolean r = false;
        private List<String> s = new ArrayList();
        private PersonalityBooksListBookcard t;

        private class a implements ListAdapter {
            final /* synthetic */ b a;
            private HashMap<String, String> b;
            private a c;

            private class a extends TextView {
                final /* synthetic */ a a;
                private boolean b = false;

                a(a aVar, Context context) {
                    this.a = aVar;
                    super(context);
                }

                public boolean a() {
                    return this.b;
                }

                public void a(boolean z) {
                    this.b = z;
                    if (z) {
                        setTextColor(getContext().getResources().getColor(R.color.text_color_c301));
                        setBackgroundResource(R.drawable.personality_no_interesting_item_sel_selector);
                        return;
                    }
                    setTextColor(getContext().getResources().getColor(R.color.text_color_c103));
                    setBackgroundResource(R.drawable.personality_no_interesting_item_selector);
                }
            }

            a(b bVar, HashMap<String, String> hashMap) {
                this.a = bVar;
                this.b = hashMap;
            }

            public void a(a aVar) {
                this.c = aVar;
            }

            public boolean areAllItemsEnabled() {
                return false;
            }

            public boolean isEnabled(int i) {
                return false;
            }

            public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            }

            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            }

            public int getCount() {
                return this.b.size();
            }

            public Object getItem(int i) {
                return this.b.get(Integer.valueOf(i));
            }

            public long getItemId(int i) {
                return (long) i;
            }

            public boolean hasStableIds() {
                return false;
            }

            public View getView(final int i, View view, ViewGroup viewGroup) {
                final View aVar = new a(this, this.a.t.getEvnetListener().getFromActivity());
                Resources resources = this.a.t.getEvnetListener().getFromActivity().getResources();
                aVar.setWidth((int) (((((float) com.qq.reader.common.c.a.bU) - (resources.getDimension(R.dimen.common_dp_8) * 2.0f)) - (resources.getDimension(R.dimen.common_left_right_margin) * 3.0f)) / 2.0f));
                aVar.setHeight((int) resources.getDimension(R.dimen.personality_btn_height));
                ColorStateList colorStateList = resources.getColorStateList(R.color.personality_no_interesting_item_textcolor);
                if (colorStateList != null) {
                    aVar.setTextColor(colorStateList);
                }
                aVar.setBackgroundResource(R.drawable.personality_no_interesting_item_selector);
                aVar.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ a c;

                    public void onClick(View view) {
                        int i = 0;
                        aVar.a(!aVar.a());
                        for (String str : this.c.a.n.keySet()) {
                            if (i == i) {
                                aVar.setText((CharSequence) this.c.a.n.get(str));
                                break;
                            }
                            i++;
                        }
                        String str2 = null;
                        this.c.c.a(str2, aVar.a());
                    }
                });
                aVar.setGravity(17);
                int i2 = 0;
                for (String str : this.a.n.keySet()) {
                    if (i2 == i) {
                        aVar.setText((CharSequence) this.a.n.get(str));
                        break;
                    }
                    i2++;
                }
                return aVar;
            }

            public int getItemViewType(int i) {
                return 1;
            }

            public int getViewTypeCount() {
                return 1;
            }

            public boolean isEmpty() {
                return false;
            }
        }

        public b(PersonalityBooksListBookcard personalityBooksListBookcard, PersonalityBooksListBookcard personalityBooksListBookcard2) {
            this.a = personalityBooksListBookcard;
            this.t = personalityBooksListBookcard2;
        }

        public void a(PersonalityBooksListBookcard personalityBooksListBookcard) {
            this.t = personalityBooksListBookcard;
        }

        public void a(com.qq.reader.module.bookstore.qnative.c.a aVar) {
            if (!this.t.hasClicked()) {
                this.t.setClicked();
                d.a(ReaderApplication.getApplicationImp(), false);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("uniteqqreader://nativepage/book/detail?").append("bid=").append(e()).append("&alg=").append(getAlg()).append(H5GameChargeTask.ITEMID).append(e());
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ext_info_id", e());
                jSONObject.put("itemid", e());
                jSONObject.put(s.ALG, getAlg());
                stringBuilder.append("&statInfo=").append(URLEncoder.encode(jSONObject.toString(), "utf-8"));
                c.a(this.t.getEvnetListener().getFromActivity(), stringBuilder.toString(), null);
            } catch (Exception e) {
            }
            i.a("event_F81", null, aVar.getFromActivity());
            if (this.t.getItemList().size() >= 3) {
                ArrayList arrayList = new ArrayList();
                arrayList.add((b) this.t.getItemList().get(0));
                arrayList.add((b) this.t.getItemList().get(1));
                arrayList.add((b) this.t.getItemList().get(2));
                if (arrayList.contains(this)) {
                    i.a("event_F82", null, aVar.getFromActivity());
                }
            }
        }

        private String u() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(e()).append(":").append(1).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(getAlg()).append(DLConstants.DEPENDENCY_PACKAGE_DIV);
            return stringBuilder.toString();
        }

        public void a(final View view, final int i, boolean z) {
            com.qq.reader.common.utils.v vVar;
            if (view.getTag() == null) {
                com.qq.reader.common.utils.v vVar2 = new com.qq.reader.common.utils.v(view, this.t.getEvnetListener().getFromActivity());
                view.setTag(vVar2);
                vVar = vVar2;
            } else {
                vVar = (com.qq.reader.common.utils.v) view.getTag();
            }
            a(vVar, i, z);
            final ImageView imageView = (ImageView) vVar.a((int) R.id.close_btn);
            if (this.n.size() > 0) {
                imageView.setVisibility(0);
                ArrayList arrayList = new ArrayList();
                imageView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ b d;

                    public void onClick(View view) {
                        this.d.a(view, imageView, this.d.n, this.d.e(), i);
                        i.a("event_F194", null, ReaderApplication.getApplicationImp());
                    }
                });
            } else {
                imageView.setVisibility(8);
            }
            imageView = (ImageView) vVar.a((int) R.id.concept_cover_tag2);
            if (this.o == 0 && this.b == 1) {
                imageView.setVisibility(0);
                imageView.setImageResource(R.drawable.feed_corner_mark_finish);
            } else if (this.o == 1 && this.c) {
                imageView.setVisibility(0);
                imageView.setImageResource(R.drawable.feed_corner_mark_hard_cover);
            } else {
                imageView.setVisibility(8);
            }
            TextView textView = (TextView) vVar.a((int) R.id.concept_order);
            if (TextUtils.isEmpty(this.m)) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
                textView.setText(this.m);
            }
            textView = (TextView) vVar.a((int) R.id.concept_category);
            if (TextUtils.isEmpty(this.d)) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
                textView.setText(this.d);
            }
            textView = (TextView) vVar.a((int) R.id.concept_tag_3);
            TextView textView2 = (TextView) vVar.a((int) R.id.concept_tag_4);
            textView.setVisibility(8);
            textView2.setVisibility(8);
            if (this.o == 1) {
                textView.setVisibility(8);
                if (TextUtils.isEmpty(this.p)) {
                    textView2.setVisibility(8);
                } else {
                    float parseFloat;
                    try {
                        parseFloat = Float.parseFloat(this.p);
                    } catch (Exception e) {
                        e.printStackTrace();
                        parseFloat = 0.0f;
                    }
                    if (parseFloat > 5.0f) {
                        textView2.setVisibility(0);
                        textView2.setText(this.p + "分");
                    } else {
                        textView2.setVisibility(8);
                    }
                }
            } else if (this.o == 0) {
                textView2.setVisibility(8);
                if (this.q <= 0) {
                    textView.setVisibility(8);
                } else {
                    textView.setVisibility(0);
                    textView.setText(s.countTransform(this.q) + "字");
                }
            }
            if (!(this.r || TextUtils.isEmpty(u()))) {
                Map hashMap = new HashMap();
                hashMap.put("event_feed_exposure", u());
                StatisticsManager.a().a("event_feed_exposure", hashMap);
                this.r = true;
            }
            if (i >= 10 && !this.t.hasClicked() && d.b(ReaderApplication.getApplicationImp())) {
                com.qq.reader.view.b.b a = ap.a(9, this.t.getEvnetListener().getFromActivity());
                a.c((int) (((float) com.qq.reader.common.c.a.ca) + this.a.getEvnetListener().getFromActivity().getResources().getDimension(R.dimen.bookstore_titlerbar_height)));
                a.b(0);
                a.a();
                d.a(ReaderApplication.getApplicationImp(), false);
            }
        }

        private void a(com.qq.reader.common.utils.v vVar, int i, boolean z) {
            CharSequence f;
            TextView textView = (TextView) vVar.a((int) R.id.concept_title);
            TextView textView2 = (TextView) vVar.a((int) R.id.concept_author);
            TextView textView3 = (TextView) vVar.a((int) R.id.concept_content);
            TextView textView4 = (TextView) vVar.a((int) R.id.concept_order);
            TextView textView5 = (TextView) vVar.a((int) R.id.concept_category);
            TextView textView6 = (TextView) vVar.a((int) R.id.concept_special);
            ImageView imageView = (ImageView) vVar.a((int) R.id.concept_tingbook_tag);
            com.qq.reader.common.imageloader.c.a(this.a.getEvnetListener().getFromActivity()).a(d(), (ImageView) vVar.a((int) R.id.concept_cover_img), com.qq.reader.common.imageloader.a.a().j());
            if (z) {
                textView.setText((i + 1) + "." + f());
                ImageView imageView2 = (ImageView) vVar.a((int) R.id.rank_list_bg);
                if (i < 0 || i >= 3) {
                    imageView2.setVisibility(8);
                } else {
                    imageView2.setVisibility(0);
                    imageView2.setImageResource(ao.d(i));
                }
            } else {
                f = f();
                if (f == null) {
                    f = "";
                }
                textView.setText(f);
            }
            View a = vVar.a((int) R.id.top_adv_divider);
            if (i == 0) {
                a.setVisibility(8);
            } else {
                a.setVisibility(0);
            }
            if (l() > 0) {
                imageView.setVisibility(0);
                textView2.setCompoundDrawablesWithIntrinsicBounds(ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.mic_icon), null, null, null);
                textView2.setText(k());
            } else {
                imageView.setVisibility(8);
                textView2.setText(g());
            }
            textView3.setText(j());
            if ("19200".equals(o())) {
                textView6.setVisibility(8);
                textView5.setVisibility(8);
                textView4.setVisibility(8);
                return;
            }
            textView6.setVisibility(0);
            textView5.setVisibility(8);
            textView4.setVisibility(8);
            if (!TextUtils.isEmpty(p())) {
                textView6.setText(p());
                textView6.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.book_store_card_suggest_color));
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, String.valueOf(e()));
                i.a("event_C80", hashMap, ReaderApplication.getApplicationImp());
            } else if (!TextUtils.isEmpty(q())) {
                textView6.setText(q());
                textView6.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.book_store_card_price_color));
            } else if (!TextUtils.isEmpty(s())) {
                String str = t() + " " + s();
                f = new SpannableString(str);
                f.setSpan(new StrikethroughSpan(), 0, t().length(), 33);
                f.setSpan(new ForegroundColorSpan(ReaderApplication.getApplicationImp().getResources().getColor(R.color.book_store_card_discount_color)), 0, t().length(), 33);
                f.setSpan(new ForegroundColorSpan(ReaderApplication.getApplicationImp().getResources().getColor(R.color.book_store_card_price_color)), str.length() - s().length(), str.length(), 33);
                textView6.setText(f);
            } else if (TextUtils.isEmpty(r())) {
                textView6.setVisibility(8);
                textView5.setVisibility(0);
                textView4.setVisibility(0);
                if (TextUtils.isEmpty(h())) {
                    textView5.setVisibility(8);
                } else {
                    textView5.setText(h());
                }
                if (TextUtils.isEmpty(c()) || !TextUtils.isDigitsOnly(b()) || Integer.parseInt(b()) <= 0) {
                    try {
                        if (TextUtils.isEmpty(m())) {
                            textView4.setVisibility(8);
                            return;
                        }
                        textView4.setText(m() + "字");
                        textView4.setVisibility(0);
                        textView4.setBackgroundResource(R.drawable.concept_bookitem_tag_level3);
                        textView4.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.common_textcolor_secondary));
                        return;
                    } catch (Exception e) {
                        com.qq.reader.common.monitor.debug.c.e("Err", e.getMessage());
                        textView4.setVisibility(8);
                        return;
                    }
                }
                textView4.setVisibility(0);
                if (!"time".equals(c())) {
                    textView4.setText(a() + "" + c());
                } else if (TextUtils.isDigitsOnly(b())) {
                    textView4.setText(k.a(Long.parseLong(b()) * 1000));
                }
            } else {
                textView6.setText(r());
                textView6.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.book_store_card_rent_color));
            }
        }

        public void parseData(JSONObject jSONObject) {
            boolean z = true;
            super.parseData(jSONObject);
            this.b = jSONObject.optInt("finished");
            if (jSONObject.optInt("exquisite", 0) != 1) {
                z = false;
            }
            this.c = z;
            this.d = jSONObject.optString("catel2name");
            this.m = jSONObject.optString("catel3name");
            this.o = jSONObject.optInt("form");
            this.p = jSONObject.optString("bookScore");
            this.q = jSONObject.optLong("totalWords");
            JSONArray optJSONArray = jSONObject.optJSONArray("reasons");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    String optString = optJSONArray.optString(i);
                    if (!TextUtils.isEmpty(optString) && optString.length() > 2) {
                        int indexOf = optString.indexOf("#");
                        if (indexOf != -1) {
                            this.n.put(optString.subSequence(0, indexOf).toString(), optString.substring(indexOf + 1));
                        }
                    }
                }
            }
        }

        private void a(View view, View view2, HashMap<String, String> hashMap, long j, int i) {
            View inflate = LayoutInflater.from(this.t.getEvnetListener().getFromActivity()).inflate(R.layout.personality_booklist_popwindow_layout, null);
            int[] iArr = new int[2];
            view2.getLocationOnScreen(iArr);
            View findViewById = inflate.findViewById(R.id.content_view);
            View findViewById2 = inflate.findViewById(R.id.top_area);
            if (iArr[1] > com.qq.reader.common.c.a.bT / 2) {
                findViewById.setBackgroundResource(R.drawable.personality_pop_up_bg);
                findViewById2.setPadding(0, (int) this.t.getEvnetListener().getFromActivity().getResources().getDimension(R.dimen.common_left_right_margin), 0, 0);
            } else {
                findViewById.setBackgroundResource(R.drawable.personality_pop_down_bg);
                findViewById2.setPadding(0, (int) this.t.getEvnetListener().getFromActivity().getResources().getDimension(R.dimen.personality_pop_box_down_top), 0, 0);
            }
            GridView gridView = (GridView) inflate.findViewById(R.id.grid_view);
            final TextView textView = (TextView) inflate.findViewById(R.id.tip_text);
            final TextView textView2 = (TextView) inflate.findViewById(R.id.no_interesting);
            ListAdapter aVar = new a(this, hashMap);
            aVar.a(new a(this) {
                final /* synthetic */ b c;

                public void a(String str, boolean z) {
                    if (z) {
                        this.c.s.add(str);
                    } else {
                        this.c.s.remove(str);
                    }
                    CharSequence spannableStringBuilder = new SpannableStringBuilder(String.format(this.c.t.getEvnetListener().getFromActivity().getResources().getString(R.string.selected_no_interesting_amounts), new Object[]{Integer.valueOf(this.c.s.size())}));
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(this.c.a.getEvnetListener().getFromActivity().getResources().getColor(R.color.text_color_c301)), 2, 3, 33);
                    textView.setText(spannableStringBuilder);
                    if (this.c.s.size() > 0) {
                        textView2.setText("确认");
                    } else {
                        textView2.setText("不感兴趣");
                    }
                }
            });
            gridView.setAdapter(aVar);
            int a = a((HashMap) hashMap);
            final PopupWindow popupWindow = new PopupWindow(inflate, com.qq.reader.common.c.a.bU, a);
            LayoutParams attributes = this.t.getEvnetListener().getFromActivity().getWindow().getAttributes();
            attributes.alpha = 0.4f;
            this.t.getEvnetListener().getFromActivity().getWindow().addFlags(2);
            this.t.getEvnetListener().getFromActivity().getWindow().setAttributes(attributes);
            popupWindow.setTouchable(true);
            popupWindow.setFocusable(true);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setOutsideTouchable(true);
            popupWindow.update();
            popupWindow.setOnDismissListener(new OnDismissListener(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void onDismiss() {
                    LayoutParams attributes = this.a.t.getEvnetListener().getFromActivity().getWindow().getAttributes();
                    attributes.alpha = 1.0f;
                    this.a.t.getEvnetListener().getFromActivity().getWindow().addFlags(2);
                    this.a.t.getEvnetListener().getFromActivity().getWindow().setAttributes(attributes);
                    this.a.s.clear();
                }
            });
            Point a2 = a(view2, iArr[1], a);
            popupWindow.showAtLocation(view2, 0, a2.x, a2.y);
            final long j2 = j;
            final View view3 = view;
            final int i2 = i;
            inflate.findViewById(R.id.no_interesting).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b e;

                public void onClick(View view) {
                    this.e.b(j2);
                    this.e.a(view3, i2);
                    Map hashMap = new HashMap();
                    if (this.e.s.size() >= 1) {
                        hashMap.put(s.ORIGIN, "1");
                    } else {
                        hashMap.put(s.ORIGIN, "0");
                    }
                    i.a("event_F195", hashMap, ReaderApplication.getApplicationImp());
                    popupWindow.dismiss();
                }
            });
        }

        private Point a(View view, int i, int i2) {
            int i3;
            view.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
            if (i > com.qq.reader.common.c.a.bT / 2) {
                i3 = i - i2;
            } else {
                i3 = view.getMeasuredHeight() + i;
            }
            return new Point(0, i3);
        }

        private void a(View view, int i) {
            view.setVisibility(8);
            a(((b) this.t.getItemList().get(i)).e());
            this.t.getItemList().remove(i);
            ((com.qq.reader.common.utils.v) view.getTag()).a(true);
            this.t.notifyDataSetChanged();
            if (this.t.getItemList().size() == 0) {
                NativeBookStoreTwoLevelActivity nativeBookStoreTwoLevelActivity = (NativeBookStoreTwoLevelActivity) this.a.getEvnetListener().getFromActivity();
                if (nativeBookStoreTwoLevelActivity != null && nativeBookStoreTwoLevelActivity.d() != null) {
                    ((NativePageFragmentForPersonality) nativeBookStoreTwoLevelActivity.d()).loadDataByDelete();
                }
            }
        }

        private void a(long j) {
            try {
                JSONArray jSONArray = (JSONArray) this.a.getOrginCardJsonOjb();
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                    if (j == jSONObject.optLong("bid")) {
                        jSONObject.put("delete", true);
                        this.a.doReSave();
                        return;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private void b(long j) {
            ReaderTask readerProtocolJSONTask = new ReaderProtocolJSONTask();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < this.s.size(); i++) {
                if (i == 0) {
                    stringBuilder.append((String) this.s.get(i));
                } else {
                    stringBuilder.append("," + ((String) this.s.get(i)));
                }
            }
            readerProtocolJSONTask.setUrl(e.a + "common/dislikerecbook" + "?reasons=" + stringBuilder.toString() + GetVoteUserIconsTask.BID + j);
            g.a().a(readerProtocolJSONTask);
        }

        private int a(HashMap<String, String> hashMap) {
            Resources resources = this.t.getEvnetListener().getFromActivity().getResources();
            return (int) ((((resources.getDimension(R.dimen.common_dp_14) * ((float) (hashMap.size() - 1))) / 2.0f) + (resources.getDimension(R.dimen.personality_btn_height) * ((float) ((hashMap.size() + 1) / 2)))) + ((float) ((int) (((float) ((int) (((float) ((int) resources.getDimension(R.dimen.common_dp_8))) + (resources.getDimension(R.dimen.common_left_right_margin) * 3.0f)))) + resources.getDimension(R.dimen.personality_btn_height)))));
        }
    }

    public PersonalityBooksListBookcard(com.qq.reader.module.bookstore.qnative.page.b bVar, String str) {
        super(bVar, str);
    }

    public s createListItem() {
        return new b(this, this);
    }

    public int getCardItemLayoutId() {
        return R.layout.personality_list_item;
    }

    public void attachView(View view) {
        com.qq.reader.common.monitor.debug.c.a("listbook", "attachView " + System.currentTimeMillis());
        try {
            XListView xListView = (XListView) view;
            this.mAdapter = new com.qq.reader.module.bookstore.qnative.a.i(ReaderApplication.getApplicationImp(), this, this.mIsFromCharis);
            ((com.qq.reader.module.bookstore.qnative.a.i) this.mAdapter).a(getEvnetListener());
            xListView.setAdapter(this.mAdapter);
        } catch (Exception e) {
            f.d("listbook", "Exception " + e);
        }
        i.a("event_F80", null, ReaderApplication.getApplicationImp());
    }

    public boolean hasClicked() {
        return this.mHasClicked;
    }

    public void setClicked() {
        this.mHasClicked = true;
    }

    public boolean addMore(com.qq.reader.module.bookstore.qnative.a aVar) {
        if (!(aVar instanceof BaseListCard)) {
            return false;
        }
        BaseListCard baseListCard = (BaseListCard) aVar;
        List itemList = getItemList();
        for (int i = 0; i < baseListCard.getItemList().size(); i++) {
            b bVar = (b) baseListCard.getItemList().get(i);
            bVar.a(this);
            itemList.add(bVar);
        }
        notifyDataSetChanged();
        return true;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        if (optJSONArray == null) {
            return false;
        }
        int length = optJSONArray.length();
        getItemList().clear();
        if (length <= 0) {
            return false;
        }
        while (i < length) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            if (!jSONObject2.optBoolean("delete")) {
                s createListItem = createListItem();
                createListItem.parseData(jSONObject2);
                addItem(createListItem);
            }
            i++;
        }
        return true;
    }

    public boolean reSaveDataBuild(JSONObject jSONObject) throws JSONException {
        return true;
    }
}
