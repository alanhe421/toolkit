package com.qq.reader.module.redpacket.square.card;

import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.k;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.redpacket.model.RedPacket;
import com.qq.reader.module.redpacket.square.data.b;
import com.qq.reader.widget.UserCircleImageView;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class SquareCommonCard extends a {
    private boolean mIsOffical = false;
    private RedPacket mRedPacket;

    public SquareCommonCard(String str) {
        super(null, str);
    }

    public void attachView() {
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.square_packet_icon);
        ImageView imageView2 = (ImageView) ap.a(getRootView(), R.id.square_offical_packet_icon);
        final TextView textView = (TextView) ap.a(getRootView(), R.id.square_book_name);
        final TextView textView2 = (TextView) ap.a(getRootView(), R.id.square_time);
        final TextView textView3 = (TextView) ap.a(getRootView(), R.id.square_common_des);
        final TextView textView4 = (TextView) ap.a(getRootView(), R.id.square_offical_des);
        UserCircleImageView userCircleImageView = (UserCircleImageView) ap.a(getRootView(), R.id.square_sender_icon);
        final TextView textView5 = (TextView) ap.a(getRootView(), R.id.square_sender_name);
        ImageView imageView3 = (ImageView) ap.a(getRootView(), R.id.square_packet_type_icon);
        if (this.mRedPacket != null) {
            if (this.mRedPacket.o() == 1) {
                this.mIsOffical = true;
            } else {
                this.mIsOffical = false;
            }
            if (this.mIsOffical) {
                imageView.setVisibility(4);
                imageView2.setVisibility(0);
                textView.setVisibility(8);
                textView3.setVisibility(8);
                textView4.setVisibility(0);
                userCircleImageView.setVisibility(8);
                textView5.setVisibility(8);
                imageView3.setVisibility(8);
                if (this.mRedPacket.b()) {
                    c.a(getEvnetListener().getFromActivity()).a(this.mRedPacket.m(), imageView2, com.qq.reader.common.imageloader.a.a().j());
                } else {
                    imageView2.setImageResource(R.drawable.redpacket_square_official_packet_icon);
                }
                addQuote(textView4, this.mRedPacket.i());
                textView2.setText(k.d(this.mRedPacket.f()));
                if (b.b().a(this.mRedPacket.d())) {
                    textView2.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c801));
                    textView4.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c801));
                } else {
                    textView2.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c103));
                    textView4.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c102));
                }
                getRootView().setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ SquareCommonCard c;

                    public void onClick(View view) {
                        i.a("event_D227", null, ReaderApplication.getApplicationImp().getApplicationContext());
                        if (this.c.mRedPacket.b()) {
                            try {
                                com.qq.reader.qurl.c.a(this.c.getEvnetListener().getFromActivity(), this.c.mRedPacket.q());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            o.a(this.c.getEvnetListener().getFromActivity(), this.c.mRedPacket.d(), this.c.mRedPacket.j());
                        }
                        b.b().b(this.c.mRedPacket.d());
                        textView4.postDelayed(new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                textView2.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c801));
                                textView4.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c801));
                            }
                        }, 1000);
                    }
                });
                return;
            }
            imageView.setVisibility(0);
            imageView2.setVisibility(8);
            textView.setVisibility(0);
            textView3.setVisibility(0);
            textView4.setVisibility(8);
            userCircleImageView.setVisibility(0);
            textView5.setVisibility(0);
            imageView3.setVisibility(0);
            textView.setText(this.mRedPacket.h());
            addQuote(textView3, this.mRedPacket.i());
            textView5.setText(this.mRedPacket.l());
            textView2.setText(k.d(this.mRedPacket.f()));
            int j = this.mRedPacket.j();
            if (j == 0) {
                imageView3.setImageResource(R.drawable.redpacket_square_normal_packet_icon);
            } else if (j == 1) {
                imageView3.setImageResource(R.drawable.redpacket_square_month_packet_icon);
            } else if (j == 2) {
                imageView3.setImageResource(R.drawable.redpacket_square_recommend_packet_icon);
            }
            c.a(getEvnetListener().getFromActivity()).a(ao.g(this.mRedPacket.e()), imageView, com.qq.reader.common.imageloader.a.a().j());
            if (b.b().a(this.mRedPacket.d())) {
                textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c801));
                textView2.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c801));
                textView3.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c801));
                textView5.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c801));
            } else {
                textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c103));
                textView2.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c103));
                textView3.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c102));
                textView5.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c103));
            }
            getRootView().setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SquareCommonCard e;

                public void onClick(View view) {
                    i.a("event_D227", null, ReaderApplication.getApplicationImp().getApplicationContext());
                    o.a(this.e.getEvnetListener().getFromActivity(), this.e.mRedPacket.h(), this.e.mRedPacket.e(), this.e.mRedPacket.u(), false, null);
                    b.b().b(this.e.mRedPacket.d());
                    textView3.postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            textView.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c801));
                            textView2.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c801));
                            textView3.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c801));
                            textView5.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c801));
                        }
                    }, 1000);
                }
            });
            setAvatarImage(userCircleImageView, this.mRedPacket.m(), "", null);
        }
    }

    public int getResLayoutId() {
        return R.layout.redpacket_square_common_packet_card;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        return false;
    }

    public void setRedPacket(RedPacket redPacket) {
        this.mRedPacket = redPacket;
    }

    public RedPacket getPacket() {
        return this.mRedPacket;
    }

    public void addQuote(TextView textView, String str) {
        if (str.length() > 35) {
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
}
