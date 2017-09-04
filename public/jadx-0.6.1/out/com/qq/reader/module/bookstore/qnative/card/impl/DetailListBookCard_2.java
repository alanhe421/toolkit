package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.qurl.c;
import com.tencent.feedback.proguard.R;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class DetailListBookCard_2 extends a {
    private static final String TAG = "DetailListBookCard_2";
    private int[] bookLayoutResIds = new int[]{R.id.ll_book_1, R.id.ll_book_2, R.id.ll_book_3, R.id.ll_book_4};
    private int[] bookNameResIds = new int[]{R.id.tv_book_name_1, R.id.tv_book_name_2, R.id.tv_book_name_3, R.id.tv_book_name_4};
    private View booklistView;
    private int changeStartPos = 0;
    private int[] coverResIds = new int[]{R.id.img_book_cover_1, R.id.img_book_cover_2, R.id.img_book_cover_3, R.id.img_book_cover_4};
    private int[] percentResIds = new int[]{R.id.tv_author_1, R.id.tv_author_2, R.id.tv_author_3, R.id.tv_author_4};

    public DetailListBookCard_2(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localbookcardinfolist_2;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        JSONArray optJSONArray = jSONObject.optJSONArray("bookList");
        if (optJSONArray == null) {
            return false;
        }
        getItemList().clear();
        int length = optJSONArray.length();
        if (length <= 0) {
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
            if (itemList != null && itemList.size() > 0) {
                this.booklistView = getMultiItemView(itemList);
                linearLayout.addView(this.booklistView);
            }
        }
        if (getItemList() == null || getItemList().size() == 0) {
            linearLayout.setVisibility(8);
        } else {
            i.a("event_B221", null, ReaderApplication.getApplicationImp());
        }
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
                final /* synthetic */ DetailListBookCard_2 a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    try {
                        c.a(this.a.getEvnetListener().getFromActivity(), "uniteqqreader://nativepage/client/recommendpage?bid=" + this.a.mFromBid, null);
                    } catch (Exception e) {
                    }
                }
            });
        }
        int i = 0;
        while (i < list.size() && i < this.coverResIds.length) {
            final g gVar = (g) list.get((this.changeStartPos + i) % list.size());
            TextView textView2 = (TextView) inflate.findViewById(this.bookNameResIds[i]);
            TextView textView3 = (TextView) inflate.findViewById(this.percentResIds[i]);
            RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(this.bookLayoutResIds[i]);
            com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(gVar.f(), (ImageView) inflate.findViewById(this.coverResIds[i]), com.qq.reader.common.imageloader.a.a().j());
            textView2.setText(gVar.n());
            textView3.setText(gVar.D() + "%读过");
            relativeLayout.setVisibility(0);
            relativeLayout.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DetailListBookCard_2 b;

                public void onClick(View view) {
                    gVar.a(this.b.getEvnetListener());
                    i.a("event_B136", null, ReaderApplication.getApplicationImp());
                }
            });
            i++;
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
