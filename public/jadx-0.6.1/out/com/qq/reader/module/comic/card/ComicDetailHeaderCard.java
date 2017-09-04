package com.qq.reader.module.comic.card;

import android.app.Activity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.comic.a;
import com.qq.reader.module.comic.activity.NativeBookStoreComicDetailActivity;
import com.qq.reader.module.comic.entity.e;
import com.qq.reader.module.comic.entity.h;
import com.tencent.feedback.proguard.R;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import org.json.JSONObject;

public class ComicDetailHeaderCard extends ComicDetailPageBaseCard<h> {
    public ComicDetailHeaderCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        if (TextUtils.isEmpty(((h) this.item.c).e())) {
            return R.layout.comic_detail_item_header_smallcover_layout;
        }
        return R.layout.comic_detail_item_header_bigcover_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.item = (e) new Gson().fromJson(jSONObject.toString(), new TypeToken<e<h>>(this) {
            final /* synthetic */ ComicDetailHeaderCard a;

            {
                this.a = r1;
            }
        }.getType());
        return true;
    }

    public void attachView() {
        String str;
        View rootView = getRootView();
        ((TextView) rootView.findViewById(R.id.book_info_name)).setText(((h) this.item.c).b());
        ((TextView) rootView.findViewById(R.id.book_info_author)).setText(((h) this.item.c).d());
        TextView textView = (TextView) rootView.findViewById(R.id.book_info_category_tag);
        if (TextUtils.isEmpty(((h) this.item.c).c())) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
            textView.setText(((h) this.item.c).c());
        }
        textView = (TextView) rootView.findViewById(R.id.tv_book_info_origin_price);
        TextView textView2 = (TextView) rootView.findViewById(R.id.tv_book_info_now_price);
        TextView textView3 = (TextView) rootView.findViewById(R.id.book_discount_message);
        if (((h) this.item.c).j()) {
            textView.setVisibility(8);
            textView3.setVisibility(4);
            textView2.setText("已购买，可全本畅读");
        } else {
            Object obj = doubleFormat(((double) ((h) this.item.c).k()) / 100.0d) + "元/" + ((h) this.item.c).i();
            if (((h) this.item.c).n()) {
                textView.setVisibility(0);
                CharSequence spannableString = new SpannableString(obj);
                spannableString.setSpan(new StrikethroughSpan(), 0, obj.length(), 17);
                textView.setText(spannableString);
            } else {
                textView.setVisibility(8);
            }
            textView2.setText(((h) this.item.c).f());
            textView3.setVisibility(0);
            if (((h) this.item.c).p() != null) {
                if (TextUtils.isEmpty(((h) this.item.c).p().a())) {
                    textView3.setVisibility(8);
                } else {
                    textView3.setText(((h) this.item.c).p().a());
                }
                final Object b = ((h) this.item.c).p().b();
                if (!TextUtils.isEmpty(b)) {
                    textView3.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ ComicDetailHeaderCard b;

                        public void onClick(View view) {
                            Bundle bundle = new Bundle();
                            bundle.putString("KEY_ACTION", "detail_2_openvip");
                            bundle.putString("URL_DATA_QURL", b);
                            this.b.getEvnetListener().doFunction(bundle);
                        }
                    });
                }
            } else {
                textView3.setVisibility(8);
            }
        }
        if (TextUtils.isEmpty(((h) this.item.c).e())) {
            ImageView imageView = (ImageView) rootView.findViewById(R.id.book_info_cover);
            imageView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ComicDetailHeaderCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    a.a().a(this.a.getEvnetListener().getFromActivity(), ((h) this.a.item.c).a());
                }
            });
            ImageView imageView2 = (ImageView) rootView.findViewById(R.id.book_info_status);
            if (((h) this.item.c).o().a()) {
                imageView2.setVisibility(0);
                imageView2.setImageResource(R.drawable.detail_status_bg_month_free);
            } else {
                imageView2.setVisibility(8);
            }
            String a = ao.a(Long.parseLong(((h) this.item.c).a()), imageView.getLayoutParams().width, imageView.getLayoutParams().height);
            c.a(getEvnetListener().getFromActivity()).a(a, imageView, com.qq.reader.common.imageloader.a.a().j());
            str = a;
        } else {
            str = ((h) this.item.c).e();
        }
        setDetailPageImage(str);
    }

    private void setDetailPageImage(String str) {
        Activity fromActivity = getEvnetListener().getFromActivity();
        if (fromActivity instanceof NativeBookStoreComicDetailActivity) {
            ((NativeBookStoreComicDetailActivity) fromActivity).a(str, TextUtils.isEmpty(((h) this.item.c).e()));
        }
    }

    private String doubleFormat(double d) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            decimalFormat.setRoundingMode(RoundingMode.FLOOR);
            String format = decimalFormat.format(d);
            Double valueOf = Double.valueOf(format);
            if (((double) valueOf.intValue()) - valueOf.doubleValue() == 0.0d) {
                return String.valueOf(valueOf.intValue());
            }
            return format;
        } catch (Exception e) {
            e.printStackTrace();
            return String.valueOf(d);
        }
    }

    public h getComicDetailInfo() {
        if (this.item != null) {
            return (h) this.item.c;
        }
        return null;
    }
}
