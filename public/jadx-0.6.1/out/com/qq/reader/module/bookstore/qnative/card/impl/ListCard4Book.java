package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.item.v;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.impl.ag;
import com.qq.reader.qurl.c;
import com.tencent.feedback.proguard.R;
import org.json.JSONArray;
import org.json.JSONObject;

public class ListCard4Book extends ListCardCommon {
    private boolean isComic = false;

    public class a extends v {
        final /* synthetic */ ListCard4Book a;

        public a(ListCard4Book listCard4Book) {
            this.a = listCard4Book;
        }

        public void a(View view, int i, boolean z) {
            super.a(view, i, z);
            b bindPage = this.a.getBindPage();
            if (bindPage != null) {
                View a = ap.a(view, R.id.classify_quick_entrance_view);
                if ((bindPage instanceof ag) && i == 0) {
                    ag agVar = (ag) bindPage;
                    JSONObject y = agVar.y();
                    this.a.isComic = "comicCat".equalsIgnoreCase(agVar.z());
                    if (y != null) {
                        JSONObject optJSONObject = y.optJSONObject("categoryInfo");
                        if (optJSONObject != null) {
                            if (optJSONObject.optBoolean("isShow")) {
                                if (a != null) {
                                    a.setVisibility(0);
                                    ImageView imageView = (ImageView) a.findViewById(R.id.classify_icon);
                                    TextView textView = (TextView) a.findViewById(R.id.classify_book_name);
                                    TextView textView2 = (TextView) a.findViewById(R.id.classify_book_des);
                                    ImageView imageView2 = (ImageView) a.findViewById(R.id.classify_cover_left);
                                    ImageView imageView3 = (ImageView) a.findViewById(R.id.classify_cover_center);
                                    ImageView imageView4 = (ImageView) a.findViewById(R.id.classify_cover_right);
                                    int a2 = com.qq.reader.module.bookstore.qnative.d.a.a(optJSONObject.optString("categoryName"));
                                    if (!(a2 == -1 || imageView == null)) {
                                        imageView.setImageResource(a2);
                                    }
                                    CharSequence optString = optJSONObject.optString("title");
                                    if (textView != null) {
                                        textView.setText(optString);
                                    }
                                    optString = optJSONObject.optString("lastDesc");
                                    if (textView2 != null) {
                                        textView2.setText(optString);
                                    }
                                    JSONArray optJSONArray = optJSONObject.optJSONArray("bids");
                                    if (optJSONArray != null && optJSONArray.length() > 0) {
                                        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                            long optLong = optJSONArray.optLong(i2);
                                            if (i2 == 0) {
                                                a(optLong, imageView2);
                                            } else if (i2 == 1) {
                                                a(optLong, imageView3);
                                            } else if (i2 == 2) {
                                                a(optLong, imageView4);
                                            }
                                        }
                                    }
                                    final String optString2 = optJSONObject.optString("qurl");
                                    a.setOnClickListener(new OnClickListener(this) {
                                        final /* synthetic */ a b;

                                        public void onClick(View view) {
                                            try {
                                                i.a("event_F322", null, ReaderApplication.getApplicationImp().getApplicationContext());
                                                c.a(this.b.a.getEvnetListener().getFromActivity(), optString2, null);
                                            } catch (Exception e) {
                                            }
                                        }
                                    });
                                }
                            } else if (a != null) {
                                a.setVisibility(8);
                            }
                        } else if (a != null) {
                            a.setVisibility(8);
                        }
                    } else if (a != null) {
                        a.setVisibility(8);
                    }
                } else if (a != null) {
                    a.setVisibility(8);
                }
                Bundle j = bindPage.j();
                if (j != null) {
                    int i3 = j.getInt("BOOK_INFO_CATEGORY_MORE_CATE_TYPE");
                    a((TextView) ap.a(view, R.id.concept_category), (TextView) ap.a(view, R.id.concept_order), i3);
                }
            }
        }

        private void a(TextView textView, TextView textView2, int i) {
            textView.setVisibility(0);
            textView.setBackgroundResource(R.drawable.concept_bookitem_tag_level3);
            textView.setTextSize(0, ReaderApplication.getApplicationImp().getResources().getDimension(R.dimen.text_size_class_1));
            textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.common_textcolor_secondary));
            textView2.setVisibility(0);
            switch (i) {
                case 1:
                    a(textView, this.f);
                    if (this.e <= 0) {
                        textView2.setVisibility(8);
                        break;
                    }
                    textView2.setText(m() + "字");
                    textView2.setVisibility(0);
                    textView2.setBackgroundResource(R.drawable.concept_bookitem_tag_level3);
                    textView2.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.common_textcolor_secondary));
                    break;
                case 2:
                    a(textView, this.f);
                    a(textView2, this.g);
                    break;
                case 3:
                    a(textView, this.g);
                    if (this.e <= 0) {
                        textView2.setVisibility(8);
                        break;
                    }
                    textView2.setText(m() + "字");
                    textView2.setVisibility(0);
                    textView2.setBackgroundResource(R.drawable.concept_bookitem_tag_level3);
                    textView2.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.common_textcolor_secondary));
                    break;
                case 4:
                    a(textView, this.g);
                    double d = this.i;
                    textView2.setText(d + "分");
                    if (d < 5.0d) {
                        textView2.setVisibility(8);
                        break;
                    }
                    break;
                case 5:
                    a(textView, this.f);
                    a(textView2, this.g);
                    break;
            }
            CharSequence charSequence = textView.getText().toString();
            CharSequence charSequence2 = textView2.getText().toString();
            if (TextUtils.isEmpty(charSequence)) {
                textView.setVisibility(8);
            }
            if (TextUtils.isEmpty(charSequence2)) {
                textView2.setVisibility(8);
            }
        }

        private void a(TextView textView, String str) {
            if (TextUtils.isEmpty(str)) {
                textView.setVisibility(8);
                return;
            }
            textView.setVisibility(0);
            textView.setText(str);
        }

        private void a(long j, ImageView imageView) {
            if (imageView != null) {
                if (this.a.isComic) {
                    com.qq.reader.common.imageloader.c.a(this.a.getEvnetListener().getFromActivity()).a(ao.h(j), imageView, com.qq.reader.common.imageloader.a.a().n());
                } else {
                    com.qq.reader.common.imageloader.c.a(this.a.getEvnetListener().getFromActivity()).a(ao.g(j), imageView, com.qq.reader.common.imageloader.a.a().n());
                }
            }
        }
    }

    public ListCard4Book(b bVar, String str) {
        super(bVar, str);
    }

    public s createListItem() {
        return new a(this);
    }

    public int getCardItemLayoutId() {
        return R.layout.localstore_listcard_item;
    }
}
