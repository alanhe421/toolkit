package com.qq.reader.module.bookstore.qnative.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.c;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.d;
import com.qq.reader.module.bookstore.qnative.page.impl.az;
import com.qq.reader.view.EmptyView;
import com.qq.reader.view.FlowLayout;
import com.qq.reader.view.GuideShadowView;
import com.qq.reader.view.web.n;
import com.tencent.feedback.proguard.R;

public class NativeSearchResultsActivity extends NativeBookStoreTwoLevelActivity implements a {
    private ImageButton A;
    private final int B = 0;
    private final int C = 1;
    private final int D = 2;
    private final int E = 3;
    private final int F = 4;
    private View G;
    private View H;
    private TextView I;
    private RelativeLayout J;
    private boolean K;
    private boolean L = true;
    private n M;
    private GuideShadowView N;
    protected Bundle k;
    private Context w;
    private TextView x;
    private LinearLayout y;
    private FlowLayout z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.w = getApplicationContext();
        this.k = getIntent().getExtras();
        this.x = (TextView) findViewById(R.id.search_results_num);
        x();
    }

    protected int f() {
        return R.layout.native_search_results_layout;
    }

    public void x() {
        ((ImageView) findViewById(R.id.profile_header_left_back)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeSearchResultsActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        this.p.setVisibility(8);
        TextView textView = (TextView) findViewById(R.id.profile_header_title);
        textView.setText(R.string.search_result);
        textView.setTextColor(getResources().getColor(R.color.common_titler_title_textcolor));
        this.y = (LinearLayout) findViewById(R.id.search_results_tip);
        this.z = (FlowLayout) findViewById(R.id.flow_layout);
        this.z.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeSearchResultsActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.K) {
                    this.a.finish();
                } else {
                    o.c(this.a, this.a.k, null);
                }
            }
        });
        a(this.z);
        this.A = (ImageButton) findViewById(R.id.profile_header_right_image);
        this.A.setImageResource(R.drawable.search_result_sort_background);
        this.A.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NativeSearchResultsActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_C121", null, ReaderApplication.getApplicationImp());
                StatisticsManager.a().a("event_C121", null);
                this.a.A().a(true);
            }
        });
        this.J = (RelativeLayout) findViewById(R.id.rl_content_bg);
        this.G = findViewById(R.id.result_empty_layout);
        ((EmptyView) this.G).a(new OnClickListener(this) {
            final /* synthetic */ NativeSearchResultsActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_C127", null, ReaderApplication.getApplicationImp());
                StatisticsManager.a().a("event_C127", null);
                if (this.a.K) {
                    this.a.finish();
                } else {
                    o.c(this.a, this.a.k, null);
                }
            }
        });
        this.H = findViewById(R.id.search_success_loading_layout);
        this.I = (TextView) findViewById(R.id.tv_book_count);
        this.K = this.k.getBoolean("PARA_TYPE_BOOLEAN", false);
    }

    private void b(final int i) {
        final int i2 = i / 10;
        Handler anonymousClass5 = new Handler(this) {
            final /* synthetic */ NativeSearchResultsActivity c;

            public void handleMessage(Message message) {
                int intValue = ((Integer) message.obj).intValue();
                if (i - intValue > 0 && i - intValue < 10) {
                    intValue = i;
                }
                this.c.I.setText(String.valueOf(intValue));
                intValue += i2;
                if (intValue < i) {
                    Message obtainMessage = obtainMessage();
                    obtainMessage.obj = Integer.valueOf(intValue);
                    sendMessageDelayed(obtainMessage, 100);
                    return;
                }
                this.c.y();
            }
        };
        this.H.setVisibility(0);
        Message obtainMessage = anonymousClass5.obtainMessage();
        obtainMessage.obj = Integer.valueOf(0);
        anonymousClass5.sendMessage(obtainMessage);
    }

    private void y() {
        float height = (float) this.H.getHeight();
        Animation a = a(0.0f, 0.0f, 0.0f, -height);
        a.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ NativeSearchResultsActivity a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.A.setVisibility(0);
                this.a.H.setVisibility(8);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        this.H.startAnimation(a);
        this.J.startAnimation(a(0.0f, 0.0f, height, 0.0f));
    }

    private Animation a(float f, float f2, float f3, float f4) {
        Animation translateAnimation = new TranslateAnimation(f, f2, f3, f4);
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }

    private void z() {
        if (d.b().a(this.w, this.m, this.mHandler, true)) {
            m();
            t();
            return;
        }
        s();
    }

    public void startActivity(Intent intent) {
        c.a(R.anim.slide_in_right, R.anim.slide_out_left);
        super.startActivity(intent);
    }

    private void a(FlowLayout flowLayout) {
        Object stringExtra = getIntent().getStringExtra("search_option_text");
        if (!TextUtils.isEmpty(stringExtra)) {
            flowLayout.removeAllViews();
            String[] split = stringExtra.split("&");
            int i = 0;
            while (split != null && i < split.length) {
                if (!TextUtils.isEmpty(split[i].trim())) {
                    flowLayout.addView(a(split[i]));
                }
                i++;
            }
        }
    }

    private TextView a(String str) {
        TextView textView = new TextView(this);
        textView.setText(str);
        textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.search_option_flow_bg));
        textView.setGravity(17);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.common_dp_8);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.common_dp_4);
        textView.setPadding(dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize2);
        textView.setIncludeFontPadding(false);
        textView.setTextColor(getResources().getColor(R.color.search_tool_item_textcolor));
        textView.setTextSize(0, (float) getResources().getDimensionPixelSize(R.dimen.text_size_class_3));
        return textView;
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 500000:
            case 500001:
                super.handleMessageImp(message);
                String str = "0";
                if (message.obj != null && (message.obj instanceof az)) {
                    str = String.valueOf(((az) message.obj).y());
                }
                try {
                    int parseInt = Integer.parseInt(str);
                    if (parseInt == 0) {
                        this.G.setVisibility(0);
                        this.z.setVisibility(8);
                        this.y.setVisibility(8);
                        this.A.setVisibility(8);
                        return true;
                    }
                    if (this.L) {
                        b(parseInt);
                    }
                    if (parseInt > 1000) {
                        str = String.valueOf((parseInt / 1000) * 1000) + "+";
                    } else {
                        str = String.valueOf(parseInt);
                    }
                    CharSequence format = String.format(getString(R.string.search_result_tip), new Object[]{str});
                    int indexOf = format.indexOf(str);
                    int length = str.length() + indexOf;
                    CharSequence spannableStringBuilder = new SpannableStringBuilder(format);
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.skin_set_common_textcolor)), indexOf, length, 34);
                    this.x.setText(spannableStringBuilder);
                    this.z.setVisibility(0);
                    this.y.setVisibility(0);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            default:
                return super.handleMessageImp(message);
        }
    }

    private n A() {
        if (this.M == null) {
            this.M = new n(this, R.layout.webpage_popup_menu);
            this.M.c().a((int) R.id.readpage_topbar_popup);
            this.M.a(new OnCancelListener(this) {
                final /* synthetic */ NativeSearchResultsActivity a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    this.a.p.setImageResource(R.drawable.bookstore_title_arrow_white);
                    if (this.a.N != null) {
                        ((ViewGroup) this.a.getWindow().getDecorView()).removeView(this.a.N);
                    }
                }
            });
            B();
            this.M.a(0, getResources().getString(R.string.sort_by_popularity), null);
            this.M.a(1, getResources().getString(R.string.sort_by_sale), null);
            this.M.a(2, getResources().getString(R.string.sort_by_updatetime), null);
            this.M.a(3, getResources().getString(R.string.sort_by_favorite), null);
            this.M.a(4, getResources().getString(R.string.sort_by_wordscount), null);
            this.M.a(new n.a(this) {
                final /* synthetic */ NativeSearchResultsActivity a;

                {
                    this.a = r1;
                }

                public boolean b(int i, Bundle bundle) {
                    if (this.a.k()) {
                        return true;
                    }
                    int h = this.a.M.h();
                    if (!(h == -1 || h == i)) {
                        switch (i) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                this.a.M.b(i);
                                this.a.L = false;
                                this.a.d(i);
                                this.a.q();
                                this.a.c(i);
                                return true;
                        }
                    }
                    return false;
                }
            });
        }
        return this.M;
    }

    @TargetApi(8)
    private void B() {
        if (this.M != null) {
            this.M.a(new OnShowListener(this) {
                final /* synthetic */ NativeSearchResultsActivity a;

                {
                    this.a = r1;
                }

                public void onShow(DialogInterface dialogInterface) {
                    if (!com.qq.reader.appconfig.a.d.n) {
                        if (this.a.N == null) {
                            this.a.N = new GuideShadowView(this.a);
                        }
                        this.a.N.setHighLightRect(this.a.w());
                        ((ViewGroup) this.a.getWindow().getDecorView()).addView(this.a.N);
                    }
                }
            });
        }
    }

    public void q() {
        this.g.clear();
        c();
        this.m.a(1001);
        z();
    }

    private void c(int i) {
        switch (i) {
            case 0:
                i.a("event_C122", null, ReaderApplication.getApplicationImp());
                StatisticsManager.a().a("event_C122", null);
                return;
            case 1:
                i.a("event_C123", null, ReaderApplication.getApplicationImp());
                StatisticsManager.a().a("event_C123", null);
                return;
            case 2:
                i.a("event_C124", null, ReaderApplication.getApplicationImp());
                StatisticsManager.a().a("event_C124", null);
                return;
            case 3:
                i.a("event_C125", null, ReaderApplication.getApplicationImp());
                StatisticsManager.a().a("event_C125", null);
                return;
            case 4:
                i.a("event_C126", null, ReaderApplication.getApplicationImp());
                StatisticsManager.a().a("event_C126", null);
                return;
            default:
                return;
        }
    }

    private void d(int i) {
        int i2;
        switch (i) {
            case 0:
                i2 = 1;
                break;
            case 1:
                i2 = 7;
                break;
            case 2:
                i2 = 3;
                break;
            case 3:
                i2 = 5;
                break;
            case 4:
                i2 = 9;
                break;
            default:
                i2 = 1;
                break;
        }
        com.qq.reader.appconfig.a.d.E((Context) this, i2);
        if (this.m != null) {
            ((az) this.m).x().putInt("search_result_order", i2);
        }
    }
}
