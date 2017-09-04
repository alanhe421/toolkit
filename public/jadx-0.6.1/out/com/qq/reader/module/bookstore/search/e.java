package com.qq.reader.module.bookstore.search;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.cservice.adv.b;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.tencent.feedback.proguard.R;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;

/* compiled from: SearchDefViewHandler */
public class e implements OnClickListener {
    ViewGroup a;
    SearchHotWordsHandlerView b;
    View c;
    View d;
    View e;
    ImageView f;
    Reference<a> g;
    TextView h;
    TextView i;
    TextView j;
    View k;

    public void a(ViewGroup viewGroup, a aVar, ISearchParamCollection iSearchParamCollection) {
        ISearchParamCollection a = ao.a(iSearchParamCollection);
        this.a = viewGroup;
        this.k = this.a.findViewById(R.id.tv_search_hot_word_title);
        this.b = (SearchHotWordsHandlerView) this.a.findViewById(R.id.search_hot_words);
        this.b.setSearchMode(a);
        this.c = this.a.findViewById(R.id.search_rank);
        this.d = this.a.findViewById(R.id.search_tools);
        this.e = this.a.findViewById(R.id.search_adv_item);
        this.f = (ImageView) this.a.findViewById(R.id.search_adv_pic);
        this.g = new WeakReference(aVar);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.h = (TextView) this.c.findViewById(R.id.tv_title);
        this.i = (TextView) this.d.findViewById(R.id.tv_title);
        this.j = (TextView) this.e.findViewById(R.id.tv_title);
        this.e.setVisibility(8);
        this.f.setVisibility(8);
        a(a);
        if (a.getSearchType() != 1) {
            View findViewById = viewGroup.findViewById(R.id.search_extra_widget_layout);
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
        }
    }

    private void a(ISearchParamCollection iSearchParamCollection) {
        int i;
        ISearchParamCollection a = ao.a(iSearchParamCollection);
        this.h.setText(ReaderApplication.getApplicationImp().getString(R.string.search_rank_of_this_week));
        this.h.setCompoundDrawablesWithIntrinsicBounds(R.drawable.hot_search_week, 0, 0, 0);
        this.i.setText(ReaderApplication.getApplicationImp().getString(R.string.search_tool));
        this.i.setCompoundDrawablesWithIntrinsicBounds(R.drawable.search_page_search_tool, 0, 0, 0);
        this.j.setCompoundDrawablesWithIntrinsicBounds(R.drawable.search_def_adv_txt_icon, 0, 0, 0);
        this.j.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c301));
        List b = b.a(ReaderApplication.getApplicationImp()).b("102925");
        if (b != null && b.size() > 0) {
            com.qq.reader.cservice.adv.a aVar = (com.qq.reader.cservice.adv.a) b.get(0);
            Object g = aVar.g();
            if (TextUtils.isEmpty(g)) {
                this.j.setText(String.valueOf(aVar.e()));
                this.j.setTag(aVar.h());
                this.e.setVisibility(0);
                this.f.setVisibility(8);
                i.a("event_C266", null, ReaderApplication.getApplicationImp());
                StatisticsManager.a().a("event_C266", null);
            } else {
                this.f.setTag(aVar.h());
                c.a(ReaderApplication.getApplicationImp()).a(g, this.f, com.qq.reader.common.imageloader.a.a().k());
                this.f.setVisibility(0);
                this.e.setVisibility(8);
                i.a("event_C266", null, ReaderApplication.getApplicationImp());
                StatisticsManager.a().a("event_C266", null);
            }
        }
        List a2 = d.a(ReaderApplication.getApplicationImp(), a);
        this.b.setSearchHotWords(a2, ((a) this.g.get()).getFromActivity());
        View view = this.k;
        if (a2.size() > 0) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
    }

    public void a(List<SearchHotWords> list) {
        if (this.b != null) {
            a aVar = (a) this.g.get();
            if (aVar != null) {
                this.b.setSearchHotWords(list, aVar.getFromActivity());
            }
        }
    }

    public void onClick(View view) {
        a aVar;
        switch (view.getId()) {
            case R.id.search_adv_pic:
            case R.id.search_adv_item:
                String str = (String) view.getTag();
                a aVar2 = (a) this.g.get();
                if (aVar2 != null && str != null) {
                    try {
                        com.qq.reader.qurl.c.a(aVar2.getFromActivity(), str);
                        i.a("event_C267", null, ReaderApplication.getApplicationImp());
                        StatisticsManager.a().a("event_C267", null);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                return;
            case R.id.search_rank:
                aVar = (a) this.g.get();
                if (aVar != null) {
                    String str2;
                    String aX = d.aX(ReaderApplication.getApplicationImp());
                    if ("boy".equals(aX)) {
                        str2 = "502256";
                    } else if ("girl".equals(aX)) {
                        str2 = "502257";
                    } else {
                        str2 = "502255";
                    }
                    o.b(aVar.getFromActivity(), ReaderApplication.getApplicationImp().getString(R.string.search_rank_of_this_week), str2, aX, null);
                    i.a("event_C261", null, ReaderApplication.getApplicationImp());
                    StatisticsManager.a().a("event_C261", null);
                    return;
                }
                return;
            case R.id.search_tools:
                aVar = (a) this.g.get();
                if (aVar != null) {
                    o.r(aVar.getFromActivity(), null);
                    i.a("event_C263", null, ReaderApplication.getApplicationImp());
                    StatisticsManager.a().a("event_C263", null);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void a() {
        this.a.setVisibility(8);
    }

    public void b() {
        this.a.setVisibility(0);
    }

    public boolean c() {
        return this.f.isShown() || this.e.isShown();
    }
}
