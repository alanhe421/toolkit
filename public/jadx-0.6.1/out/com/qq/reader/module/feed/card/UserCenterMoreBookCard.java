package com.qq.reader.module.feed.card;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.qq.reader.qurl.c;
import com.qq.reader.view.QRImageView;
import com.tencent.feedback.proguard.R;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import org.json.JSONObject;

public class UserCenterMoreBookCard extends FeedBaseCard {
    private String mBookAuthor;
    private long mBookBid;
    private int mBookComic;
    private String mBookCover;
    private int mBookListen;
    private String mBookName;
    private String mBookQurl;
    private int mBookSecret;

    public UserCenterMoreBookCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.user_center_more_book_item;
    }

    public void attachView() {
        String str;
        QRImageView qRImageView = (QRImageView) ap.a(getRootView(), R.id.book_cover);
        ((TextView) ap.a(getRootView(), R.id.book_name)).setText(this.mBookName);
        ((TextView) ap.a(getRootView(), R.id.book_author)).setText(this.mBookAuthor);
        getRootView().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserCenterMoreBookCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                try {
                    c.a(this.a.getEvnetListener().getFromActivity(), this.a.mBookQurl, null, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.book_type);
        String h;
        if (this.mBookComic == 1) {
            h = ao.h(this.mBookBid);
            imageView.setImageResource(R.drawable.comic_book_icon);
            imageView.setVisibility(0);
            str = h;
        } else if (this.mBookListen == 1) {
            h = ao.a(this.mBookBid, false, Opcodes.OR_INT);
            imageView.setImageResource(R.drawable.listen_book_icon);
            imageView.setVisibility(0);
            str = h;
        } else {
            h = ao.g(this.mBookBid);
            imageView.setVisibility(8);
            str = h;
        }
        com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(str, qRImageView, a.a().j());
        ImageView imageView2 = (ImageView) ap.a(getRootView(), R.id.book_secret);
        if (this.mBookSecret == 0) {
            imageView2.setVisibility(0);
        } else {
            imageView2.setVisibility(8);
        }
    }

    public boolean parseData(JSONObject jSONObject) {
        this.mBookCover = jSONObject.optString("bookCover");
        this.mBookName = jSONObject.optString("bookName");
        this.mBookAuthor = jSONObject.optString("author");
        this.mBookQurl = jSONObject.optString("qurl");
        this.mBookListen = jSONObject.optInt("isListen");
        this.mBookSecret = jSONObject.optInt("isSecret");
        this.mBookBid = jSONObject.optLong("bid");
        this.mBookComic = jSONObject.optInt("isComic");
        return true;
    }
}
