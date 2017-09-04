package com.qq.reader.module.rookie.view;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.common.web.js.JSAddToBookShelf;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class RookieGeneRecBookCard extends a {
    private String authorinfo;
    private String mAuthor;
    private String mBookName;
    private String mBookid;
    private String mCategory;
    private String mCoverUrl;
    private String mDesc;

    public RookieGeneRecBookCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.rookie_gene_recbook_card;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mBookName = jSONObject.optString("title");
        this.mDesc = jSONObject.optString("intro");
        this.mAuthor = jSONObject.optString("author");
        this.mCategory = jSONObject.optString("categoryName");
        this.mBookid = jSONObject.optString("id");
        long j = 0;
        try {
            j = Long.valueOf(this.mBookid).longValue();
        } catch (Exception e) {
        }
        this.mCoverUrl = ao.g(j);
        this.authorinfo = this.mCategory + " | " + this.mAuthor;
        if (((double) com.qq.reader.common.c.a.bZ) <= 1.5d && this.authorinfo != null && this.authorinfo.length() > 12) {
            this.authorinfo = this.authorinfo.substring(0, 12);
            this.authorinfo += "…";
        }
        return true;
    }

    public void attachView() {
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.rookie_book_cover);
        TextView textView = (TextView) ap.a(getRootView(), R.id.rookie_book_title);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.rookie_book_intro);
        TextView textView3 = (TextView) ap.a(getRootView(), R.id.rookie_book_tag);
        TextView textView4 = (TextView) ap.a(getRootView(), R.id.rookie_book_add);
        imageView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RookieGeneRecBookCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_F159", null, ReaderApplication.getApplicationImp());
                o.a(this.a.getEvnetListener().getFromActivity(), this.a.mBookid, null, null, null);
            }
        });
        c.a(getEvnetListener().getFromActivity()).a(this.mCoverUrl, imageView, com.qq.reader.common.imageloader.a.a().j());
        textView.setText(this.mBookName);
        textView2.setText(this.mDesc);
        textView3.setText(this.authorinfo);
        if (com.qq.reader.common.db.handle.i.c().e(this.mBookid) != null) {
            textView4.setText(ReaderApplication.getApplicationContext().getString(R.string.already_on_shelf));
            textView4.setEnabled(false);
        } else {
            textView4.setText(ReaderApplication.getApplicationContext().getString(R.string.add_to_shelf));
            textView4.setEnabled(true);
        }
        getRootView().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RookieGeneRecBookCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                o.a(this.a.getEvnetListener().getFromActivity(), this.a.mBookid, null, null, null);
            }
        });
        textView4.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RookieGeneRecBookCard a;

            {
                this.a = r1;
            }

            public void onClick(final View view) {
                i.a("event_F160", null, ReaderApplication.getApplicationImp());
                new JSAddToBookShelf(this.a.getEvnetListener().getFromActivity()).addByIdWithCallBack(this.a.mBookid, "false", new JSAddToBookShelf.a(this) {
                    final /* synthetic */ AnonymousClass3 b;

                    public void a() {
                        view.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                Bundle bundle = new Bundle();
                                bundle.putInt("function_type", 4);
                                this.a.b.a.getEvnetListener().doFunction(bundle);
                            }
                        });
                    }

                    public void b() {
                    }
                });
            }
        });
    }
}
