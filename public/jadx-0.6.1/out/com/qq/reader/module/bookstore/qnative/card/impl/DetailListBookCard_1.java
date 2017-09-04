package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class DetailListBookCard_1 extends a {
    private static final String TAG = "ListBookCard_1";
    private int[] authorResIds = new int[]{R.id.tv_author_1, R.id.tv_author_2, R.id.tv_author_3, R.id.tv_author_4};
    private int[] bookLayoutResIds = new int[]{R.id.ll_book_1, R.id.ll_book_2, R.id.ll_book_3, R.id.ll_book_4};
    private int[] bookNameResIds = new int[]{R.id.tv_book_name_1, R.id.tv_book_name_2, R.id.tv_book_name_3, R.id.tv_book_name_4};
    private View booklistView = null;
    private int changeStartPos = 0;
    private int[] coverResIds = new int[]{R.id.img_book_cover_1, R.id.img_book_cover_2, R.id.img_book_cover_3, R.id.img_book_cover_4};
    private int oriBookId = 0;

    static /* synthetic */ int access$312(DetailListBookCard_1 detailListBookCard_1, int i) {
        int i2 = detailListBookCard_1.changeStartPos + i;
        detailListBookCard_1.changeStartPos = i2;
        return i2;
    }

    public DetailListBookCard_1(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localbookcardinfolist_1;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        this.oriBookId = jSONObject.optInt("oribookid");
        JSONArray optJSONArray = jSONObject.optJSONArray("bookList");
        if (optJSONArray == null) {
            return false;
        }
        getItemList().clear();
        int length = optJSONArray.length();
        if (length <= 0 || getItemList() == null || getItemList().size() > 0) {
            return false;
        }
        while (i < length) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            s gVar = new g();
            gVar.b(this.mFromBid);
            gVar.parseData(jSONObject2);
            addItem(gVar);
            i++;
        }
        return true;
    }

    public void attachView() {
        LinearLayout linearLayout = (LinearLayout) getRootView();
        View a = ap.a(getRootView(), R.id.detail_common_title);
        if (a != null) {
            ((TextView) a.findViewById(R.id.title_name)).setText(this.mConfigTitle);
        }
        if (linearLayout.getChildCount() <= 2) {
            List itemList = getItemList();
            if (itemList != null && itemList.size() != 0) {
                if (itemList.size() <= 1) {
                    linearLayout.addView(getSingleItemView((g) ((s) itemList.get(0))));
                    return;
                }
                this.booklistView = getMultiItemView(itemList);
                linearLayout.addView(this.booklistView);
            }
        }
    }

    private View getSingleItemView(final g gVar) {
        View inflate = ((LayoutInflater) ReaderApplication.getApplicationImp().getSystemService("layout_inflater")).inflate(R.layout.localstore_detail_listcard_item, null);
        TextView textView = (TextView) inflate.findViewById(R.id.book_name);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.book_cover);
        TextView textView2 = (TextView) inflate.findViewById(R.id.book_author);
        TextView textView3 = (TextView) inflate.findViewById(R.id.book_info);
        TextView textView4 = (TextView) inflate.findViewById(R.id.book_popularity);
        f.d("listbook", " getview " + gVar + "  name " + gVar.n());
        c.a(getEvnetListener().getFromActivity()).a(gVar.f(), imageView, com.qq.reader.common.imageloader.a.a().j());
        textView.setText(gVar.n());
        if (!TextUtils.isEmpty(gVar.r()) && gVar.i() != -1) {
            String str = gVar.i() == 1 ? "完结共" + gVar.e() + "章" : "连载至" + gVar.e() + "章";
            if (gVar != null && gVar.e() == 0) {
                str = gVar.i() == 1 ? "完结" : "连载";
            }
            textView2.setText(gVar.r() + " | " + str);
        } else if (!TextUtils.isEmpty(gVar.r())) {
            textView2.setText(gVar.r());
        } else if (gVar.i() != -1) {
            CharSequence charSequence = gVar.i() == 1 ? "完结共" + gVar.e() + "章" : "连载至" + gVar.e() + "章";
            if (gVar != null && gVar.e() == 0) {
                charSequence = gVar.i() == 1 ? "完结" : "连载";
            }
            textView2.setText(charSequence);
        } else {
            textView2.setText("");
        }
        textView3.setText(gVar.s());
        inflate.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DetailListBookCard_1 b;

            public void onClick(View view) {
                gVar.a(this.b.getEvnetListener());
                i.a("event_B133", null, ReaderApplication.getApplicationImp());
            }
        });
        return inflate;
    }

    private View getMultiItemView(List<s> list) {
        CharSequence charSequence = null;
        View inflate = ((LayoutInflater) ReaderApplication.getApplicationImp().getSystemService("layout_inflater")).inflate(R.layout.localstore_card_horizon_booklist, null);
        hideAllBookLayout(inflate);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_show_all);
        if (this.mMoreAction != null) {
            charSequence = this.mMoreAction.e;
        }
        textView.setText(this.mMoreAction.e);
        if (charSequence == null) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
            textView.setText(charSequence);
            textView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DetailListBookCard_1 a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.mMoreAction != null) {
                        String string = this.a.mMoreAction.a().a().getString("KEY_ACTION");
                        if ("columbooks".equals(string)) {
                            LinearLayout linearLayout = (LinearLayout) this.a.getRootView();
                            linearLayout.removeView(this.a.booklistView);
                            DetailListBookCard_1.access$312(this.a, 4);
                            this.a.booklistView = this.a.getMultiItemView(this.a.getItemList());
                            linearLayout.addView(this.a.booklistView);
                            i.a("event_C83", null, ReaderApplication.getApplicationImp());
                        } else if ("sameauthorbooks".equals(string)) {
                            this.a.mMoreAction.a().a().putString("KEY_ACTIONID", String.valueOf(this.a.oriBookId));
                            this.a.mMoreAction.a(this.a.getEvnetListener());
                            i.a("event_B134", null, ReaderApplication.getApplicationImp());
                        }
                    }
                }
            });
        }
        int i = 0;
        while (i < list.size() && i < this.coverResIds.length) {
            final g gVar = (g) list.get((this.changeStartPos + i) % list.size());
            TextView textView2 = (TextView) inflate.findViewById(this.bookNameResIds[i]);
            TextView textView3 = (TextView) inflate.findViewById(this.authorResIds[i]);
            RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(this.bookLayoutResIds[i]);
            c.a(getEvnetListener().getFromActivity()).a(gVar.f(), (ImageView) inflate.findViewById(this.coverResIds[i]), com.qq.reader.common.imageloader.a.a().j());
            textView2.setText(gVar.n());
            textView3.setText(gVar.q());
            relativeLayout.setVisibility(0);
            relativeLayout.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DetailListBookCard_1 b;

                public void onClick(View view) {
                    gVar.a(this.b.getEvnetListener());
                    if ("sameauthorbooks".equals(this.b.mMoreAction.a().a().getString("KEY_ACTION"))) {
                        i.a("event_B133", null, ReaderApplication.getApplicationImp());
                    } else {
                        i.a("event_B135", null, ReaderApplication.getApplicationImp());
                    }
                }
            });
            i++;
        }
        if (list.size() > this.coverResIds.length) {
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
        return inflate;
    }

    private void hideAllBookLayout(View view) {
        for (int findViewById : this.bookLayoutResIds) {
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(findViewById);
            relativeLayout.setVisibility(4);
            relativeLayout.setOnClickListener(null);
        }
    }
}
