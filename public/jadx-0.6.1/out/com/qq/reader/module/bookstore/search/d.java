package com.qq.reader.module.bookstore.search;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ap;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.bookstore.search.DropDownEditText.a;
import com.qq.reader.module.bookstore.search.DropDownEditText.b;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: SearchAdapter */
public class d extends BaseAdapter implements a, b {
    boolean a;
    private Context b;
    private ArrayList<AbsSearchWords> c = null;
    private ISearchParamCollection d;
    private String e = null;
    private ListView f;
    private final int g = 0;

    public boolean a() {
        return this.a;
    }

    public d(Context context) {
        this.b = context;
    }

    public int getViewTypeCount() {
        return 4;
    }

    public int getItemViewType(int i) {
        AbsSearchWords absSearchWords = (AbsSearchWords) this.c.get(i);
        if (absSearchWords.mType == 13) {
            return 0;
        }
        if (absSearchWords.mType == 15) {
            return 1;
        }
        if (absSearchWords.mType == 14 || absSearchWords.mType == 16) {
            return 2;
        }
        return 3;
    }

    public void a(ArrayList<? extends AbsSearchWords> arrayList) {
        if (this.c != null) {
            if (this.c.size() > 1) {
                this.c.subList(1, this.c.size()).clear();
            }
            this.c.addAll(arrayList);
        }
    }

    public void b(ArrayList<? extends AbsSearchWords> arrayList) {
        if (this.c == null) {
            this.c = new ArrayList();
        } else {
            b();
        }
        this.c.addAll(arrayList);
    }

    public void a(ISearchParamCollection iSearchParamCollection) {
        this.d = iSearchParamCollection;
    }

    public void a(String str) {
        this.e = str;
    }

    public void b() {
        if (this.c != null) {
            this.c.clear();
        }
    }

    public int getCount() {
        if (this.c != null) {
            return this.c.size();
        }
        return 0;
    }

    public Object getItem(int i) {
        if (this.c == null || this.c.size() < i + 1) {
            return null;
        }
        return this.c.get(i);
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        View a;
        AbsSearchWords absSearchWords = (AbsSearchWords) this.c.get(i);
        int[] iconResIds = absSearchWords.getIconResIds();
        if (view == null) {
            a = a(this.b, absSearchWords, viewGroup);
        } else {
            a = view;
        }
        a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ d c;

            public void onClick(View view) {
                if (this.c.f != null && this.c.f.getOnItemClickListener() != null) {
                    this.c.f.getOnItemClickListener().onItemClick(this.c.f, a, i, this.c.getItemId(i));
                }
            }
        });
        ImageView imageView = (ImageView) ap.a(a, R.id.icon);
        if (imageView != null) {
            imageView.setBackgroundResource(iconResIds[0]);
        }
        TextView textView = (TextView) ap.a(a, R.id.text2);
        if (textView != null) {
            textView.setVisibility(8);
        }
        switch (getItemViewType(i)) {
            case 0:
            case 1:
                imageView = (ImageView) ap.a(a, R.id.book_image);
                ((TextView) ap.a(a, R.id.book_name_txt)).setText(a(absSearchWords.getKeyWord(), this.e));
                String str = "";
                if (absSearchWords instanceof SearchData) {
                    str = ((Mark) absSearchWords.mTag).getImageURI();
                }
                c.a(this.b).a(str, imageView, com.qq.reader.common.imageloader.a.a().j());
                break;
            case 2:
                ((TextView) a).setText(absSearchWords.getKeyWord());
                break;
            default:
                boolean z = absSearchWords instanceof SearchHistory;
                textView = (TextView) ap.a(a, R.id.book_name_txt);
                if (z || i != 0) {
                    textView.setText(a(absSearchWords.getKeyWord(), this.e));
                } else {
                    textView.setText(a(String.format(ReaderApplication.getApplicationImp().getString(R.string.search_by_this_keyword), new Object[]{absSearchWords.getKeyWord()}), this.e));
                    Map hashMap = new HashMap();
                    if (this.d != null) {
                        this.d.submitStaticsParam(hashMap);
                    }
                    i.a("event_B212", hashMap, ReaderApplication.getApplicationImp());
                    StatisticsManager.a().a("event_B212", hashMap);
                }
                imageView = (ImageView) ap.a(a, R.id.label_tag);
                int i2 = iconResIds[1];
                if (i2 == 0 || z) {
                    imageView.setVisibility(8);
                } else {
                    imageView.setVisibility(0);
                    imageView.setBackgroundResource(i2);
                }
                if (absSearchWords instanceof SearchData) {
                    TextView textView2 = (TextView) ap.a(a, R.id.text2);
                    if (((SearchData) absSearchWords).mExtInfo == null) {
                        textView2.setVisibility(8);
                        break;
                    }
                    CharSequence charSequence = ((SearchData) absSearchWords).mExtInfo.a;
                    if (!TextUtils.isEmpty(charSequence)) {
                        textView2.setVisibility(0);
                        textView2.setText(charSequence);
                        break;
                    }
                    textView2.setVisibility(8);
                    break;
                }
                break;
        }
        a.setTag(absSearchWords);
        return a;
    }

    private SpannableStringBuilder a(String str, String str2) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(str);
        if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !str.contains(str2))) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(this.b.getResources().getColor(R.color.text_color_c301)), str.indexOf(str2), str.indexOf(str2) + str2.length(), 33);
        }
        return spannableStringBuilder;
    }

    private View a(Context context, AbsSearchWords absSearchWords, ViewGroup viewGroup) {
        View inflate;
        if (absSearchWords.mType == 13) {
            inflate = LayoutInflater.from(context).inflate(R.layout.search_direct_book_item, viewGroup, false);
            inflate.findViewById(R.id.read_btn).setVisibility(0);
            return inflate;
        } else if (absSearchWords.mType == 15) {
            inflate = LayoutInflater.from(context).inflate(R.layout.search_direct_book_item, viewGroup, false);
            inflate.findViewById(R.id.listen_btn).setVisibility(0);
            return inflate;
        } else if (absSearchWords.mType == 14 || absSearchWords.mType == 16) {
            return LayoutInflater.from(context).inflate(R.layout.search_direct_online_item, viewGroup, false);
        } else {
            return LayoutInflater.from(context).inflate(R.layout.search_keyword_item, viewGroup, false);
        }
    }

    public CharSequence a(int i) {
        AbsSearchWords absSearchWords = (AbsSearchWords) getItem(i);
        if (absSearchWords != null && absSearchWords.mType == 0) {
            return absSearchWords.getKeyWord();
        }
        if (absSearchWords == null || absSearchWords.mType != 14) {
            return "";
        }
        return null;
    }

    public ArrayList<AbsSearchWords> c() {
        return this.c;
    }

    public String d() {
        return this.e;
    }

    public void a(ListView listView) {
        this.f = listView;
    }
}
