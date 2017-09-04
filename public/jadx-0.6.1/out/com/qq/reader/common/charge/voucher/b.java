package com.qq.reader.common.charge.voucher;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.AlertDialog.a;
import com.tencent.feedback.proguard.R;

/* compiled from: UserBalanceHelper */
public final class b {
    private static b a = new b();
    private volatile AlertDialog b;

    private b() {
    }

    public void a(Activity activity, final OnDismissListener onDismissListener, com.qq.reader.common.charge.voucher.a.b bVar) {
        if (activity != null && !activity.isFinishing() && this.b == null && bVar != null && bVar.e != null && bVar.e.size() > 0) {
            a aVar = new a(activity);
            this.b = new a(activity).a(aVar.b()).a((int) R.string.voucher_dialog_detail_title).a((int) R.string.readerpage_ok, new OnClickListener(this) {
                final /* synthetic */ b b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.b.b = null;
                    if (onDismissListener != null) {
                        onDismissListener.onDismiss(dialogInterface);
                    }
                }
            }).a(new OnCancelListener(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    this.a.b = null;
                }
            }).a();
            if (this.b != null) {
                aVar.a(bVar);
                i.a("event_F164", null, ReaderApplication.getApplicationImp());
                this.b.show();
            }
        }
    }

    public static b a() {
        return a;
    }

    public static int b() {
        return R.drawable.audio_question_quiz_questionmark;
    }

    public static void a(final Activity activity, TextView textView, final View.OnClickListener onClickListener, final OnDismissListener onDismissListener, final com.qq.reader.common.charge.voucher.a.b bVar) {
        CharSequence spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append("#");
        Drawable drawable = textView.getResources().getDrawable(b());
        drawable.setBounds(c(), 0, c() + drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        spannableStringBuilder.setSpan(new ImageSpan(drawable, 0), 0, spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                i.a("event_F163", null, ReaderApplication.getApplicationImp());
                b.a().a(activity, onDismissListener, bVar);
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
            }

            public void updateDrawState(TextPaint textPaint) {
                textPaint.setColor(textPaint.bgColor);
                textPaint.setUnderlineText(false);
            }
        }, 0, spannableStringBuilder.length(), 33);
        textView.append(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setHighlightColor(0);
    }

    public static int c() {
        return ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.common_dp_2);
    }

    public static void a(Button button, int i) {
        Object string = button.getResources().getString(R.string.chapter_buy_charge_ensure_money);
        CharSequence format = String.format(button.getResources().getString(R.string.chapter_buy_charge_need_coin_count), new Object[]{Integer.valueOf(i)});
        CharSequence spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(string);
        spannableStringBuilder.append("  ");
        spannableStringBuilder.append(format);
        spannableStringBuilder.setSpan(new UserBalanceHelper$4(0.8f, button), string.length(), spannableStringBuilder.length(), 33);
        c.d("BALANCE", spannableStringBuilder.toString());
        button.setText(spannableStringBuilder);
    }

    public static void a(Button button, Button button2, boolean z) {
        LayoutParams layoutParams = (LayoutParams) button.getLayoutParams();
        if (z) {
            button2.setVisibility(8);
            layoutParams.weight = 3.0f;
        } else {
            button2.setVisibility(0);
            layoutParams.weight = 1.0f;
        }
        button.getParent().requestLayout();
    }

    public static SpannableStringBuilder a(Context context, int i) {
        return a(i + "", i + " " + "抵扣券", context.getResources().getDimensionPixelSize(R.dimen.text_size_class_3), context.getResources().getDimensionPixelSize(R.dimen.text_size_class_4), context.getResources().getColor(R.color.text_color_c101), context.getResources().getColor(R.color.text_color_c101));
    }

    public static SpannableStringBuilder a(Context context, String str) {
        return a(str, String.format(ReaderApplication.getApplicationImp().getString(R.string.voucher_detail_limit), new Object[]{str}), context.getResources().getDimensionPixelSize(R.dimen.text_size_class_2), context.getResources().getDimensionPixelSize(R.dimen.text_size_class_2), context.getResources().getColor(R.color.text_color_c102), context.getResources().getColor(R.color.text_color_c301));
    }

    public static SpannableStringBuilder a(String str, String str2, int i, int i2, int i3, int i4) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(str2);
        int indexOf = str2.indexOf(str);
        int length = str.length() + indexOf;
        if (indexOf > 0) {
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(i), 0, indexOf, 33);
        }
        if (str2.length() > length) {
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(i), length, str2.length(), 33);
        }
        if (length > indexOf) {
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(i2), indexOf, length, 33);
        }
        if (indexOf > 0) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(i3), 0, indexOf, 18);
        }
        if (str2.length() > length) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(i3), length, str2.length(), 18);
        }
        if (length > indexOf) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(i4), indexOf, length, 33);
        }
        return spannableStringBuilder;
    }

    public static String a(int i, int i2, int i3) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(i).append("书币");
        if (i2 > 0) {
            stringBuffer.append(" + ").append(i2).append("书券");
        }
        if (i3 > 0) {
            stringBuffer.append(" + ").append(i3).append("抵扣券");
        }
        return stringBuffer.toString().trim();
    }
}
