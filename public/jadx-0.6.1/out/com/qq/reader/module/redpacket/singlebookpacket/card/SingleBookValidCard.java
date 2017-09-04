package com.qq.reader.module.redpacket.singlebookpacket.card;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.k;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class SingleBookValidCard extends RedPacketSingleBookCard {
    private boolean mIsClicked;

    public SingleBookValidCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.redpacket_singlebook_valid_card;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.single_book_valid_packet_message);
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.single_book_valid_packet_icon);
        ImageView imageView2 = (ImageView) ap.a(getRootView(), R.id.single_book_valid_packet_sender_icon);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.single_book_valid_packet_sender_name);
        TextView textView3 = (TextView) ap.a(getRootView(), R.id.single_book_valid_packet_create_time);
        View a = ap.a(getRootView(), R.id.red_packet_single_book_card_divider);
        if (isShowDivider()) {
            a.setVisibility(0);
        } else {
            a.setVisibility(8);
        }
        addQuote(textView, getItem().i());
        imageView.setImageResource(getPacketResource(getItem().j()));
        c.a(getEvnetListener().getFromActivity()).a(getItem().m(), imageView2, a.a().e());
        textView2.setText(getItem().l());
        if (getItem().t() == 1) {
            textView2.setCompoundDrawablesWithIntrinsicBounds(null, null, getRootView().getResources().getDrawable(R.drawable.bookclub_comment_user_tag_author), null);
        } else if (getItem().n() >= 0) {
            textView2.setCompoundDrawablesWithIntrinsicBounds(null, null, getRootView().getResources().getDrawable(getFanLevelIconId(getItem().n())), null);
        } else {
            textView2.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        textView3.setText(k.d(getItem().f()));
        getRootView().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SingleBookValidCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.mIsClicked = true;
                this.a.setUpCardClicked();
                o.a(this.a.getEvnetListener().getFromActivity(), this.a.getItem().d(), this.a.getItem().j());
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, String.valueOf(this.a.getItem().j()));
                i.a("event_D214", hashMap, ReaderApplication.getApplicationImp());
            }
        });
        setUpCardClicked();
    }

    public boolean isIsClicked() {
        return this.mIsClicked;
    }

    public void setIsClicked(boolean z) {
        this.mIsClicked = z;
    }

    private void setUpCardClicked() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.single_book_valid_packet_message);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.single_book_valid_packet_sender_name);
        TextView textView3 = (TextView) ap.a(getRootView(), R.id.single_book_valid_packet_create_time);
        if (this.mIsClicked) {
            textView.setTextColor(Color.parseColor("#cccccc"));
            textView2.setTextColor(Color.parseColor("#cccccc"));
            textView3.setTextColor(Color.parseColor("#cccccc"));
            return;
        }
        textView.setTextColor(textView.getResources().getColor(R.color.text_color_c102));
        textView2.setTextColor(textView.getResources().getColor(R.color.text_color_c101));
        textView3.setTextColor(textView.getResources().getColor(R.color.text_color_c103));
    }

    private int getPacketResource(int i) {
        switch (i) {
            case 1:
                return R.drawable.redpacket_singlebook_month_icon;
            case 2:
                return R.drawable.redpacket_singlebook_recommend_icon;
            default:
                return R.drawable.redpacket_singlebook_normal_icon;
        }
    }

    protected void addQuote(TextView textView, String str) {
        if (str.length() > 40) {
            str = str.substring(0, 30) + "...";
        }
        String str2 = "\" " + str.toString() + " \"";
        CharSequence spannableString = new SpannableString(str2);
        Drawable drawable = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.card_quote_start);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        com.qq.reader.common.emotion.b.b bVar = new com.qq.reader.common.emotion.b.b(drawable);
        drawable = ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.card_quote_end);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        spannableString.setSpan(new com.qq.reader.common.emotion.b.b(drawable), str2.length() - 1, str2.length(), 33);
        spannableString.setSpan(bVar, 0, 1, 33);
        textView.setText(spannableString);
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        return false;
    }
}
