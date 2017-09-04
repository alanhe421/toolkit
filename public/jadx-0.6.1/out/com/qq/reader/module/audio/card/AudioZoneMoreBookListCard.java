package com.qq.reader.module.audio.card;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.j;
import com.qq.reader.common.utils.k;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class AudioZoneMoreBookListCard extends a {
    public static final int AUDIO_BOOK_LIST_CARD_TYPE_CONUT = 1;
    public static final int AUDIO_BOOK_LIST_CARD_TYPE_DISCOUNT = 3;
    public static final int AUDIO_BOOK_LIST_CARD_TYPE_FINISHED = 4;
    public static final int AUDIO_BOOK_LIST_CARD_TYPE_FREE = 2;
    public static final int AUDIO_BOOK_LIST_CARD_TYPE_LISTENED = 0;
    public static final int AUDIO_BOOK_LIST_CARD_TYPE_ORDER = 5;
    public static final int AUDIO_BOOK_LIST_CARD_TYPE_RANK = 6;
    private View mDivider;
    private com.qq.reader.module.audio.b.a mItemData;

    public AudioZoneMoreBookListCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.audio_zone_book_list_card;
    }

    public void build(JSONObject jSONObject) {
        super.build(jSONObject);
    }

    public void attachView() {
        c.a(ReaderApplication.getApplicationImp().getApplicationContext()).a(this.mItemData.f(), (ImageView) ap.a(getRootView(), R.id.cover_img), com.qq.reader.common.imageloader.a.a().j());
        TextView textView = (TextView) ap.a(getRootView(), R.id.book_content);
        ((TextView) ap.a(getRootView(), R.id.book_name)).setText(this.mItemData.n());
        textView.setText(this.mItemData.s());
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.anchor);
        textView2.setText(this.mItemData.h());
        switch (Integer.parseInt(this.mType)) {
            case 0:
                textView2.setVisibility(8);
                textView2 = (TextView) ap.a(getRootView(), R.id.recent_listen);
                textView2.setText(this.mItemData.b());
                textView2.setVisibility(0);
                break;
            case 1:
                textView2 = (TextView) ap.a(getRootView(), R.id.concept_order);
                if (this.mItemData.a() <= 0) {
                    textView2.setVisibility(8);
                    break;
                }
                textView2.setText(j.a(this.mItemData.a()) + "播放");
                textView2.setVisibility(0);
                break;
            case 2:
            case 3:
                textView2 = (TextView) ap.a(getRootView(), R.id.book_price);
                Object k = this.mItemData.k();
                CharSequence spannableString = new SpannableString(k);
                spannableString.setSpan(new StrikethroughSpan(), 0, k.length(), 33);
                textView2.setText(spannableString);
                textView2.setVisibility(0);
                textView2 = (TextView) ap.a(getRootView(), R.id.book_discount);
                textView2.setText(this.mItemData.l());
                textView2.setVisibility(0);
                break;
            case 5:
            case 6:
                textView2 = (TextView) ap.a(getRootView(), R.id.concept_order);
                textView2.setVisibility(8);
                if (!TextUtils.isEmpty(this.mItemData.B()) && TextUtils.isDigitsOnly(this.mItemData.C()) && Long.parseLong(this.mItemData.C()) > 0) {
                    textView2.setVisibility(0);
                    if ("time".equals(this.mItemData.B())) {
                        if (TextUtils.isDigitsOnly(this.mItemData.C())) {
                            textView2.setText(k.a(Long.parseLong(this.mItemData.C()) * 1000));
                            break;
                        }
                    }
                    textView2.setText(j.a(Long.parseLong(this.mItemData.C())) + this.mItemData.B());
                    break;
                }
                break;
            default:
                textView2 = (TextView) ap.a(getRootView(), R.id.concept_order);
                textView2.setText(this.mItemData.e() + "集");
                textView2.setVisibility(0);
                break;
        }
        this.mDivider = ap.a(getRootView(), R.id.divider);
        this.mDivider.setVisibility(0);
        ap.a(getRootView(), R.id.content_layout).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AudioZoneMoreBookListCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.getEvnetListener() != null) {
                    if (TextUtils.isEmpty(this.a.mItemData.c())) {
                        this.a.mItemData.a(this.a.getEvnetListener());
                    } else {
                        try {
                            com.qq.reader.qurl.c.a(this.a.getEvnetListener().getFromActivity(), this.a.mItemData.c());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (Integer.parseInt(this.a.mType) == 6) {
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, this.a.mItemData.getOrigin());
                        hashMap.put("bid", String.valueOf(this.a.mItemData.m()));
                        i.a("event_B294", hashMap, ReaderApplication.getApplicationImp());
                    }
                }
            }
        });
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject == null) {
            return false;
        }
        this.mItemData = new com.qq.reader.module.audio.b.a();
        this.mItemData.parseData(jSONObject);
        return true;
    }

    public void showDivider(boolean z) {
        this.mDivider.setVisibility(z ? 0 : 8);
    }
}
