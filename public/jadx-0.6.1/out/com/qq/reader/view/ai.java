package com.qq.reader.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.f;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;

/* compiled from: SeekBarDialog */
public class ai extends BaseDialog {
    boolean a = false;
    private String b = "SeekBarDialog";
    private SeekBar c;
    private Button d;
    private Button e;
    private TextView i;
    private TextView j;
    private RelativeLayout k;
    private LinearLayout l;
    private LinearLayout m;
    private a n;
    private final int o = 1000000;
    private final int p = Constants.ERRORCODE_UNKNOWN;
    private final int q = 1000;
    private int r = 0;
    private double s = -1.0d;
    private final Context t;
    private Toast u;
    private int v;
    private int w = -1;
    private double x = 0.0d;
    private Handler y = new Handler(this) {
        final /* synthetic */ ai a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 800:
                    synchronized (this.a.c) {
                        double max;
                        if (this.a.v == R.string.jump_text_local_page) {
                            max = (double) Math.max(this.a.c.getProgress(), 1);
                        } else {
                            max = this.a.c(this.a.c.getProgress()) / 100.0d;
                        }
                        if (this.a.s != max) {
                            this.a.s = max;
                            this.a.n.a(max);
                        }
                    }
                    return;
                case 801:
                    CharSequence format;
                    CharSequence d;
                    double b = this.a.c(this.a.c.getProgress());
                    String string = this.a.t.getResources().getString(this.a.v);
                    if (this.a.v != R.string.jump_text_local_page) {
                        format = String.format(string, new Object[]{Double.valueOf(b)});
                        b /= 100.0d;
                    } else {
                        b = (double) Math.max(this.a.c.getProgress(), 1);
                        format = String.format(string, new Object[]{Integer.valueOf((int) b), Integer.valueOf(this.a.c.getMax())});
                    }
                    if (this.a.a) {
                        d = this.a.n.d();
                    } else {
                        d = this.a.n.b(b);
                    }
                    if (d == null || d.length() <= 0) {
                        this.a.j.setVisibility(8);
                    } else {
                        this.a.j.setText(d);
                        this.a.j.setVisibility(0);
                    }
                    this.a.i.setText(format);
                    return;
                case 802:
                    this.a.d.setEnabled(false);
                    return;
                case 803:
                    this.a.e.setEnabled(false);
                    return;
                case 804:
                    this.a.j();
                    return;
                default:
                    return;
            }
        }
    };

    /* compiled from: SeekBarDialog */
    public interface a {
        void a();

        void a(double d);

        String b(double d);

        void b();

        Double c();

        String d();
    }

    public ai(final Activity activity, int i, int i2) {
        d(false);
        this.t = activity;
        this.v = i;
        this.r = i2;
        if (this.f == null) {
            this.x = 10000.0d;
            a(activity, null, R.layout.seekbardialog, true, false, true);
            this.f.setOnCancelListener(new OnCancelListener(this) {
                final /* synthetic */ ai a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    this.a.h();
                }
            });
            this.c = (SeekBar) this.f.findViewById(R.id.progress);
            this.m = (LinearLayout) this.f.findViewById(R.id.popup_box_view);
            this.c.setFocusable(false);
            this.c.setOnSeekBarChangeListener(new OnSeekBarChangeListener(this) {
                final /* synthetic */ ai a;

                {
                    this.a = r1;
                }

                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                    this.a.i();
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                    this.a.i();
                    this.a.w = this.a.c.getProgress();
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                    if (!this.a.y.hasMessages(804)) {
                        this.a.y.sendEmptyMessage(804);
                    }
                    this.a.y.sendEmptyMessage(800);
                }
            });
            this.d = (Button) this.f.findViewById(R.id.left_button);
            this.e = (Button) this.f.findViewById(R.id.right_button);
            if (this.r == 1) {
                this.d.setBackgroundDrawable(null);
                this.e.setBackgroundDrawable(null);
                this.d.setText("上一章");
                this.e.setText("下一章");
            } else {
                if (d.n) {
                    this.d.setBackgroundResource(R.drawable.seekbar_button_left_night);
                    this.e.setBackgroundResource(R.drawable.seekbar_button_right_night);
                } else {
                    this.d.setBackgroundResource(R.drawable.seekbar_button_left);
                    this.e.setBackgroundResource(R.drawable.seekbar_button_right);
                }
                this.d.setText("");
                this.e.setText("");
            }
            this.d.setOnLongClickListener(new OnLongClickListener(this) {
                final /* synthetic */ ai b;

                public boolean onLongClick(View view) {
                    if (this.b.n == null) {
                        return false;
                    }
                    this.b.e.setEnabled(true);
                    new Thread(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            while (this.a.b.d.isPressed()) {
                                try {
                                    AnonymousClass1.sleep(50);
                                    if (!this.a.b.b(activity)) {
                                        this.a.b.y.sendEmptyMessage(802);
                                        break;
                                    }
                                } catch (InterruptedException e) {
                                    f.b("longClick Left Exception", e.toString());
                                }
                            }
                            if (!this.a.b.y.hasMessages(804)) {
                                this.a.b.y.sendEmptyMessage(804);
                            }
                            this.a.b.y.removeMessages(800);
                            this.a.b.y.sendEmptyMessageDelayed(800, 1000);
                        }
                    }.start();
                    return true;
                }
            });
            this.e.setOnLongClickListener(new OnLongClickListener(this) {
                final /* synthetic */ ai b;

                public boolean onLongClick(View view) {
                    if (this.b.n == null) {
                        return false;
                    }
                    this.b.d.setEnabled(true);
                    new Thread(this) {
                        final /* synthetic */ AnonymousClass4 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            while (this.a.b.e.isPressed()) {
                                try {
                                    AnonymousClass1.sleep(50);
                                    if (!this.a.b.a(activity)) {
                                        this.a.b.y.sendEmptyMessage(803);
                                        break;
                                    }
                                } catch (InterruptedException e) {
                                    f.b("longClick Left Exception", e.toString());
                                }
                            }
                            if (!this.a.b.y.hasMessages(804)) {
                                this.a.b.y.sendEmptyMessage(804);
                            }
                            this.a.b.y.removeMessages(800);
                            this.a.b.y.sendEmptyMessageDelayed(800, 1000);
                        }
                    }.start();
                    return true;
                }
            });
            this.d.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ai b;

                public void onClick(View view) {
                    if (this.b.n == null) {
                        return;
                    }
                    if (this.b.r == 1) {
                        this.b.n.b();
                        return;
                    }
                    this.b.b(activity);
                    if (!this.b.y.hasMessages(804)) {
                        this.b.y.sendEmptyMessage(804);
                    }
                    this.b.y.removeMessages(800);
                    this.b.y.sendEmptyMessageDelayed(800, 1000);
                }
            });
            this.e.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ai b;

                public void onClick(View view) {
                    if (this.b.n == null) {
                        return;
                    }
                    if (this.b.r == 1) {
                        this.b.n.a();
                        return;
                    }
                    this.b.a(activity);
                    if (!this.b.y.hasMessages(804)) {
                        this.b.y.sendEmptyMessage(804);
                    }
                    this.b.y.removeMessages(800);
                    this.b.y.sendEmptyMessageDelayed(800, 1000);
                }
            });
            this.i = (TextView) this.f.findViewById(R.id.infotext);
            this.j = (TextView) this.f.findViewById(R.id.chaptertext);
            this.k = (RelativeLayout) this.f.findViewById(R.id.line1);
            this.k.setClickable(true);
            this.k.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ai a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                }
            });
            this.l = (LinearLayout) this.f.findViewById(R.id.jump);
            this.l.setClickable(true);
            this.l.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ai a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.h();
                }
            });
        }
        a(d.n);
    }

    private boolean a(Activity activity) {
        boolean z = false;
        int progress = this.c.getProgress();
        if (progress < this.c.getMax()) {
            if (this.v == R.string.jump_text_local_page) {
                this.c.setProgress((int) (((double) progress) + this.x));
            } else if (((double) progress) < ((double) this.c.getMax()) - this.x) {
                this.c.setProgress((int) (((double) progress) + this.x));
            } else {
                this.c.setProgress(this.c.getMax());
            }
            this.c.postInvalidate();
            z = true;
        }
        i();
        return z;
    }

    private boolean b(Activity activity) {
        boolean z = false;
        int progress = this.c.getProgress();
        if (progress != 0) {
            if (((double) progress) > this.x) {
                this.c.setProgress((int) (((double) progress) - this.x));
            } else if (this.v != R.string.jump_text_local_page) {
                this.c.setProgress(0);
            }
            this.c.postInvalidate();
            z = true;
        }
        i();
        return z;
    }

    public void a(double d) {
        i();
        this.c.setMax(100000000);
        this.c.setProgress(b(d));
        this.s = c(this.c.getProgress()) / 100.0d;
        c().a(R.id.line1);
        this.f.show();
        this.w = -1;
        j();
        this.a = true;
    }

    public void b(int i) {
        if (i != 0) {
            this.x = (double) (100000000 / i);
        }
    }

    public void g() {
        Double c = this.n.c();
        i();
        this.c.setProgress(b(c.doubleValue() * 100.0d));
        this.s = c(this.c.getProgress()) / 100.0d;
        j();
        this.a = true;
    }

    private void f(boolean z) {
        Drawable drawable = this.t.getResources().getDrawable(R.drawable.seekbar_style);
        Drawable drawable2 = this.t.getResources().getDrawable(R.drawable.seekbar_thumb);
        if (z) {
            drawable = this.t.getResources().getDrawable(R.drawable.seekbar_style_night);
            drawable2 = this.t.getResources().getDrawable(R.drawable.seekbar_thumb_night);
        }
        drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight());
        if (VERSION.SDK_INT < 14) {
            drawable.setBounds(this.c.getProgressDrawable().getBounds());
            this.c.setProgressDrawable(drawable);
            if (this.c.getProgress() == 0) {
                this.c.setProgress(1);
                this.c.setProgress(0);
                return;
            }
            this.c.setProgress(this.c.getProgress() - 1);
            this.c.setProgress(this.c.getProgress() + 1);
            return;
        }
        this.c.setProgressDrawable(drawable);
        this.c.setThumb(drawable2);
    }

    private boolean h() {
        if (this.f.isShowing()) {
            this.f.cancel();
        }
        if (this.u != null) {
            this.u.cancel();
        }
        this.w = -1;
        return true;
    }

    public void a(a aVar) {
        this.n = aVar;
    }

    private final double c(int i) {
        return ((double) i) / 1000000.0d;
    }

    private final int b(double d) {
        return (int) (1000000.0d * d);
    }

    private final void i() {
        this.a = false;
        this.y.sendEmptyMessageDelayed(801, 0);
    }

    public void a(boolean z) {
        if (z) {
            this.d.setTextColor(this.t.getResources().getColorStateList(R.color.skin_set_read_page_menu_item_nightmode_selector));
            this.e.setTextColor(this.t.getResources().getColorStateList(R.color.skin_set_read_page_menu_item_nightmode_selector));
            this.i.setTextColor(this.t.getResources().getColorStateList(R.color.skin_set_read_page_menu_item_nightmode_selector));
            this.j.setTextColor(this.t.getResources().getColorStateList(R.color.skin_set_read_page_menu_item_nightmode_selector));
            if (this.r == 1) {
                this.d.setBackgroundDrawable(null);
                this.e.setBackgroundDrawable(null);
            } else {
                this.d.setBackgroundResource(R.drawable.seekbar_button_left_night);
                this.e.setBackgroundResource(R.drawable.seekbar_button_right_night);
            }
            this.m.setBackgroundResource(R.drawable.popup_box_night);
            this.k.setBackgroundResource(R.color.commonsetting_bg_color_night);
            f(true);
            return;
        }
        this.d.setTextColor(this.t.getResources().getColorStateList(R.color.skin_set_read_page_menu_item_daymode_textcolor_selector));
        this.e.setTextColor(this.t.getResources().getColorStateList(R.color.skin_set_read_page_menu_item_daymode_textcolor_selector));
        this.i.setTextColor(this.t.getResources().getColorStateList(R.color.skin_set_read_page_menu_item_daymode_textcolor_selector));
        this.j.setTextColor(this.t.getResources().getColorStateList(R.color.skin_set_read_page_menu_item_daymode_textcolor_selector));
        if (this.r == 1) {
            this.d.setBackgroundDrawable(null);
            this.e.setBackgroundDrawable(null);
        } else {
            this.d.setBackgroundResource(R.drawable.seekbar_button_left);
            this.e.setBackgroundResource(R.drawable.seekbar_button_right);
        }
        this.m.setBackgroundResource(R.drawable.popup_box);
        this.k.setBackgroundResource(R.color.commonsetting_bg_color);
        f(false);
    }

    public void a(boolean z, boolean z2) {
        if (this.r == 1) {
            this.d.setEnabled(z);
            this.e.setEnabled(z2);
        }
    }

    private void j() {
        synchronized (this.c) {
            this.d.setEnabled(true);
            this.e.setEnabled(true);
            if (this.c.getMax() == this.c.getProgress()) {
                this.e.setEnabled(false);
            } else if ((this.c.getProgress() == 0 && this.v != R.string.jump_text_local_page) || (this.v == R.string.jump_text_local_page && 1 == Math.max(this.c.getProgress(), 1))) {
                this.d.setEnabled(false);
            }
        }
    }
}
