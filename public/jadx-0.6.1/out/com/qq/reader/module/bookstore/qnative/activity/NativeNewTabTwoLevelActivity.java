package com.qq.reader.module.bookstore.qnative.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforOther;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.impl.NativeServerTabPageOfEditorRecommend;
import com.qq.reader.module.bookstore.qweb.TabInfo;
import com.tencent.feedback.proguard.R;
import java.util.HashSet;

public class NativeNewTabTwoLevelActivity extends NativeBookStoreTwoLevelActivity {
    private int A = 0;
    private int B = 0;
    private int C = 0;
    private boolean D = false;
    private HashSet<Integer> E = new HashSet();
    private int F = 0;
    private String G;
    BroadcastReceiver k = new BroadcastReceiver(this) {
        final /* synthetic */ NativeNewTabTwoLevelActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getBooleanExtra("loginSuccess", false)) {
                this.a.D = true;
                this.a.E.add(Integer.valueOf(this.a.F));
            }
        }
    };
    private TextView w;
    private TextView x;
    private TextView y;
    private int z = 0;

    public void onCreate(Bundle bundle) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.z = extras.getInt("NEW_TAB_TWO_LEVEL_TITLE_BAR_BG_RES_ID", 0);
            this.A = extras.getInt("NEW_TAB_TWO_LEVEL_TITLE_BAR_SELECTED_TEXTCOLOR", 0);
            this.B = extras.getInt("NEW_TAB_TWO_LEVEL_TITLE_BAR_UNSELECTED_TEXTCOLOR", 0);
            this.C = extras.getInt("NEW_TAB_TWO_LEVEL_LOADING_DRAWABLE_RES_ID", 0);
            if (extras.containsKey("KEY_JUMP_PAGENAME")) {
                this.G = extras.getString("KEY_JUMP_PAGENAME");
            }
        }
        super.onCreate(bundle);
        if (this.z != 0) {
            findViewById(R.id.common_titler).setBackgroundResource(this.z);
        }
        if (this.C != 0) {
            ((ProgressBar) findViewById(R.id.default_progress)).setIndeterminateDrawable(getResources().getDrawable(this.C));
        }
        registerReceiver(this.k, new IntentFilter("com.qq.reader.loginok"));
    }

    public View a(int i) {
        ColorStateList colorStateList;
        View inflate;
        TextView textView;
        TabInfo tabInfo = (TabInfo) this.g.get(i);
        if (this.A == 0 || this.B == 0) {
            colorStateList = null;
        } else {
            int[] iArr = new int[]{this.A, this.A, this.B};
            r3 = new int[3][];
            r3[0] = new int[]{16842908};
            r3[1] = new int[]{16842913};
            r3[2] = new int[0];
            colorStateList = new ColorStateList(r3, iArr);
        }
        if (i == 0) {
            inflate = getLayoutInflater().inflate(R.layout.lbsstackactivtiy_tab_leftitem, null);
            textView = (TextView) inflate.findViewById(R.id.tab_text);
            this.w = textView;
            if (this.g.size() > 2) {
                inflate.findViewById(R.id.left_tab).setBackgroundResource(R.drawable.stacktab_left_short);
            }
        } else if (i == this.g.size() - 1) {
            inflate = getLayoutInflater().inflate(R.layout.lbsstackactivtiy_tab_rightitem, null);
            textView = (TextView) inflate.findViewById(R.id.tab_text);
            this.x = textView;
            if (this.g.size() > 2) {
                inflate.findViewById(R.id.right_tab).setBackgroundResource(R.drawable.stacktab_right_short);
            }
        } else {
            inflate = getLayoutInflater().inflate(R.layout.lbsstackactivtiy_tab_centeritem, null);
            textView = (TextView) inflate.findViewById(R.id.tab_text);
            this.y = textView;
        }
        if (colorStateList != null) {
            textView.setTextColor(colorStateList);
        }
        textView.setText(tabInfo.title);
        if (this.j.size() > i) {
            this.j.set(i, inflate);
        } else {
            while (this.j.size() <= i) {
                this.j.add(null);
            }
            this.j.set(i, inflate);
        }
        return inflate;
    }

    protected void m() {
        super.m();
        this.a.setLineRightAndLeftPadding(0, 0);
        LayoutParams layoutParams = (LayoutParams) this.d.getLayoutParams();
        if (VERSION.SDK_INT >= 19) {
            layoutParams.height = getResources().getDimensionPixelOffset(R.dimen.original_titlerbar_height) + a.ca;
            this.d.setPadding(0, a.ca, 0, 0);
        } else {
            layoutParams.height = getResources().getDimensionPixelOffset(R.dimen.original_titlerbar_height);
            this.d.setPadding(0, 0, 0, 0);
        }
        this.d.setLayoutParams(layoutParams);
        findViewById(R.id.common_titler).setVisibility(8);
        if (this.g.size() > 2) {
            this.a.setIndicatorResource(R.drawable.stacktab_flip_short, getResources().getDimensionPixelOffset(R.dimen.common_dp_5), getResources().getDimensionPixelOffset(R.dimen.common_dp_5));
        } else {
            this.a.setIndicatorResource(R.drawable.stacktab_flip, getResources().getDimensionPixelOffset(R.dimen.common_dp_5), getResources().getDimensionPixelOffset(R.dimen.common_dp_5));
        }
    }

    protected void a(Bundle bundle) {
        super.a(bundle);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.content_wrap_layout);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) relativeLayout.getLayoutParams();
        if (VERSION.SDK_INT >= 19) {
            layoutParams.setMargins(0, -a.ca, 0, 0);
        } else {
            layoutParams.setMargins(0, 0, 0, 0);
        }
        relativeLayout.setLayoutParams(layoutParams);
        this.d.setVisibility(0);
        this.d.setBackgroundResource(R.drawable.titler_bg);
        if (this.z != 0) {
            this.d.setBackgroundResource(this.z);
        } else {
            this.d.setBackgroundResource(R.drawable.titler_bg);
        }
        this.a.setBackgroundColor(getResources().getColor(17170445));
        this.a.setTabPaddingLeftRight(0);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.a.getLayoutParams();
        layoutParams2.width = -2;
        layoutParams2.height = getResources().getDimensionPixelOffset(R.dimen.common_dp_40);
        this.a.setLayoutParams(layoutParams2);
        com.qq.reader.common.widget.a.a((ImageView) this.d.findViewById(R.id.title_left), this);
    }

    public void onPageScrollStateChanged(int i) {
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            TextView textView = (TextView) ((View) this.j.get(i2)).findViewById(R.id.tab_text);
            if (i == 1 || i == 2) {
                textView.setPressed(true);
            } else {
                textView.setPressed(false);
            }
        }
    }

    public void onResume() {
        super.onResume();
        x();
    }

    private void x() {
        if (this.a != null && this.w != null && this.x != null) {
            int currentPagerViewItem = this.a.getCurrentPagerViewItem();
            if (currentPagerViewItem >= 0) {
                int i;
                int i2;
                if (this.A != 0) {
                    i = this.A;
                } else {
                    i = getResources().getColor(R.color.skin_set_localstack_tab_textcolor_startcolor);
                }
                if (this.B != 0) {
                    i2 = this.B;
                } else {
                    i2 = getResources().getColor(R.color.skin_set_localstack_tab_textcolor_endcolor);
                }
                if (currentPagerViewItem == 0) {
                    this.w.setTextColor(i);
                    this.x.setTextColor(i2);
                    if (this.y != null) {
                        this.y.setTextColor(i2);
                    }
                } else if (currentPagerViewItem == 1) {
                    if (this.g.size() > 2) {
                        this.w.setTextColor(i2);
                        this.x.setTextColor(i2);
                        if (this.y != null) {
                            this.y.setTextColor(i);
                            return;
                        }
                        return;
                    }
                    this.w.setTextColor(i2);
                    this.x.setTextColor(i);
                } else if (currentPagerViewItem == 2) {
                    this.w.setTextColor(i2);
                    this.x.setTextColor(i);
                    if (this.y != null) {
                        this.y.setTextColor(i2);
                    }
                }
            }
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        if ((f != 0.0f || f != 1.0f) && this.w != null && this.x != null) {
            int i3;
            int i4;
            c.d("wangyudong", "arg0 = " + i + ", degree = " + f);
            if (this.A != 0) {
                i3 = this.A;
            } else {
                i3 = getResources().getColor(R.color.skin_set_localstack_tab_textcolor_startcolor);
            }
            if (this.B != 0) {
                i4 = this.B;
            } else {
                i4 = getResources().getColor(R.color.skin_set_localstack_tab_textcolor_endcolor);
            }
            int i5 = (i3 >> 16) & 255;
            int i6 = (i4 >> 16) & 255;
            int i7 = (i3 >> 8) & 255;
            int i8 = (i4 >> 8) & 255;
            i3 = (i3 >> 0) & 255;
            i4 = (i4 >> 0) & 255;
            int i9 = ((int) (((float) (i6 - i5)) * f)) + i5;
            int i10 = ((int) (((float) (i8 - i7)) * f)) + i7;
            int i11 = ((int) (((float) (i4 - i3)) * f)) + i3;
            i5 = (i5 + i6) - i9;
            i6 = (i8 + i7) - i10;
            i3 = (i3 + i4) - i11;
            if (this.g.size() == 2) {
                if (i == 0) {
                    this.w.setTextColor(ColorStateList.valueOf(Color.rgb(i9, i10, i11)));
                    this.x.setTextColor(ColorStateList.valueOf(Color.rgb(i5, i6, i3)));
                    return;
                }
                this.x.setTextColor(ColorStateList.valueOf(Color.rgb(i9, i10, i11)));
                this.w.setTextColor(ColorStateList.valueOf(Color.rgb(i5, i6, i3)));
            } else if (this.y == null) {
            } else {
                if (i == 0) {
                    this.w.setTextColor(ColorStateList.valueOf(Color.rgb(i9, i10, i11)));
                    this.y.setTextColor(ColorStateList.valueOf(Color.rgb(i5, i6, i3)));
                    this.x.setTextColor(getResources().getColorStateList(R.color.text_color_c104));
                } else if (i == 1) {
                    this.y.setTextColor(ColorStateList.valueOf(Color.rgb(i9, i10, i11)));
                    this.x.setTextColor(ColorStateList.valueOf(Color.rgb(i5, i6, i3)));
                    this.w.setTextColor(getResources().getColorStateList(R.color.text_color_c104));
                } else if (i == 2) {
                    this.x.setTextColor(ColorStateList.valueOf(Color.rgb(i9, i10, i11)));
                    this.y.setTextColor(ColorStateList.valueOf(Color.rgb(i5, i6, i3)));
                    this.w.setTextColor(getResources().getColorStateList(R.color.text_color_c104));
                }
            }
        }
    }

    public void onPageSelected(int i) {
        this.F = i;
        if (this.m instanceof NativeServerTabPageOfEditorRecommend) {
            ((NativeServerTabPageOfEditorRecommend) this.m).b(i);
        }
        if (!TextUtils.isEmpty(this.G)) {
            if (this.G.equals("common_boutique_zone")) {
                i.a("event_F330", null, ReaderApplication.getApplicationContext());
            } else if (this.G.equals("common_free_books")) {
                i.a("event_F331", null, ReaderApplication.getApplicationContext());
            } else if (this.G.equals("common_pay_month")) {
                i.a("event_F332", null, ReaderApplication.getApplicationContext());
            } else if (this.G.equals("common_finish_books")) {
                i.a("event_F333", null, ReaderApplication.getApplicationContext());
            }
        }
        if (this.D && !this.E.contains(Integer.valueOf(this.F))) {
            this.E.add(Integer.valueOf(this.F));
            NativePageFragmentforOther nativePageFragmentforOther = (NativePageFragmentforOther) d();
            if (nativePageFragmentforOther != null) {
                b bVar = nativePageFragmentforOther.mHoldPage;
                if (bVar != null) {
                    bVar.q();
                }
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        this.D = false;
        this.E.clear();
        unregisterReceiver(this.k);
    }
}
