package com.qq.reader.common.emotion;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.EditText;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;
import com.tencent.theme.SkinnableBitmapDrawable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: EmoUtils */
public class b {
    private static final String a = b.class.getSimpleName();
    private static HashMap<String, Drawable> b = new HashMap();

    /* compiled from: EmoUtils */
    public static class a extends ImageSpan {
        public a(Drawable drawable) {
            super(drawable);
        }

        public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
            Rect bounds = getDrawable().getBounds();
            if (fontMetricsInt != null) {
                FontMetricsInt fontMetricsInt2 = paint.getFontMetricsInt();
                int i3 = fontMetricsInt2.bottom - fontMetricsInt2.top;
                int i4 = bounds.bottom - bounds.top;
                int i5 = (i4 / 2) - (i3 / 4);
                i3 = (i3 / 4) + (i4 / 2);
                fontMetricsInt.ascent = -i3;
                fontMetricsInt.top = -i3;
                fontMetricsInt.bottom = i5;
                fontMetricsInt.descent = i5;
            }
            return bounds.right;
        }

        public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
            Drawable drawable = getDrawable();
            canvas.save();
            canvas.translate(f, (float) i3);
            drawable.draw(canvas);
            canvas.restore();
        }
    }

    /* compiled from: EmoUtils */
    public static class b extends ImageSpan {
        public b(Drawable drawable) {
            super(drawable);
        }

        public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
            Rect bounds = getDrawable().getBounds();
            if (fontMetricsInt != null) {
                FontMetricsInt fontMetricsInt2 = paint.getFontMetricsInt();
                int i3 = fontMetricsInt2.bottom - fontMetricsInt2.top;
                int i4 = bounds.bottom - bounds.top;
                int i5 = (i4 / 2) - (i3 / 4);
                i3 = (i3 / 4) + (i4 / 2);
                fontMetricsInt.ascent = -i3;
                fontMetricsInt.top = -i3;
                fontMetricsInt.bottom = i5;
                fontMetricsInt.descent = i5;
            }
            return bounds.right;
        }

        public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
            Drawable drawable = getDrawable();
            canvas.save();
            canvas.translate(f, (float) ((((i5 - i3) - drawable.getBounds().bottom) / 2) + i3));
            drawable.draw(canvas);
            canvas.restore();
        }
    }

    public static Drawable a(Context context, String str) {
        Bitmap bitmap = null;
        Drawable drawable = (Drawable) b.get(str);
        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof SkinnableBitmapDrawable) {
            bitmap = ((SkinnableBitmapDrawable) drawable).getBitmap();
        }
        if (bitmap == null || bitmap.isRecycled()) {
            try {
                drawable = Drawable.createFromStream(context.getAssets().open(str), null);
            } catch (Exception e) {
                e.printStackTrace();
                drawable = context.getResources().getDrawable(R.drawable.default_emo_icon);
            }
            b.put(str, drawable);
        }
        return drawable;
    }

    public static void a(Context context, EditText editText, d dVar) {
        a(context, editText, dVar, 1.0f);
    }

    public static void a(Context context, EditText editText, d dVar, float f) {
        if (dVar != null && dVar.a == 1) {
            Object imageSpan;
            i iVar = (i) dVar;
            Object obj = iVar.d;
            Drawable newDrawable = iVar.a(context, context.getResources().getDisplayMetrics().density).mutate().getConstantState().newDrawable();
            newDrawable.setBounds(0, 0, (int) (((float) a(editText.getTextSize())) * f), (int) (((float) a(editText.getTextSize())) * f));
            if (b(editText.getText()) > 0) {
                imageSpan = new ImageSpan(newDrawable, 1);
            } else {
                imageSpan = new ImageSpan(newDrawable, 0);
            }
            CharSequence spannableString = new SpannableString(editText.getText().insert(editText.getSelectionStart(), obj));
            spannableString.setSpan(imageSpan, editText.getSelectionStart() - obj.length(), editText.getSelectionStart(), 33);
            int selectionStart = editText.getSelectionStart();
            editText.setText(spannableString);
            editText.requestFocus();
            editText.setSelection(selectionStart);
            newDrawable.setCallback(null);
        }
    }

    public static int a(float f) {
        Paint paint = new Paint();
        paint.setTextSize(f);
        FontMetrics fontMetrics = paint.getFontMetrics();
        return ((int) Math.ceil((double) (fontMetrics.descent - fontMetrics.top))) + 2;
    }

    public static Drawable b(Context context, String str) {
        String[] split = str.replace("[", "").replace("]", "").split(",");
        if (split != null && split.length == 2) {
            String substring = split[0].substring(split[0].indexOf("=") + 1);
            String replace = split[1].replace("/", "");
            if (substring.equals("default")) {
                return a(context, String.format(i.e + "/%s.png", new Object[]{replace}));
            }
        }
        return null;
    }

    public static boolean a(CharSequence charSequence) {
        return b(charSequence) > 0;
    }

    public static int b(CharSequence charSequence) {
        return Pattern.compile("\\[\\bemot=default,\\b.{1,3}/\\]").matcher(charSequence.toString()).replaceAll("").length();
    }

    public static CharSequence a(Context context, CharSequence charSequence, float f, ArrayList<Drawable> arrayList, int[] iArr) {
        return a(context, ao.a(charSequence, iArr, (ArrayList) arrayList), f, 3);
    }

    public static CharSequence a(Context context, CharSequence charSequence, float f) {
        int i = 0;
        if (b(charSequence) > 0) {
            i = 1;
        }
        return a(context, charSequence, f, 1.0f, i);
    }

    public static String c(CharSequence charSequence) {
        return Pattern.compile("\\[\\bemot=default,\\b.{1,3}/\\]").matcher(charSequence.toString()).replaceAll("");
    }

    private static CharSequence a(Context context, CharSequence charSequence, float f, int i) {
        return a(context, charSequence, f, 1.0f, i);
    }

    public static CharSequence a(Context context, CharSequence charSequence, float f, float f2, int i) {
        CharSequence charSequence2;
        if (charSequence instanceof SpannableString) {
            charSequence2 = (SpannableString) charSequence;
        } else {
            charSequence2 = new SpannableString(charSequence);
        }
        Matcher matcher = Pattern.compile("\\[\\bemot=default,\\b.{1,3}/\\]").matcher(charSequence.toString());
        while (matcher.find()) {
            Object bVar;
            Drawable newDrawable = b(context, matcher.group()).mutate().getConstantState().newDrawable();
            newDrawable.setBounds(0, 0, (int) (((float) a(f)) * f2), (int) (((float) a(f)) * f2));
            if (i == 3) {
                bVar = new b(newDrawable);
            } else {
                bVar = new ImageSpan(newDrawable, i);
            }
            charSequence2.setSpan(bVar, matcher.start(), matcher.end(), 33);
        }
        return charSequence2;
    }
}
