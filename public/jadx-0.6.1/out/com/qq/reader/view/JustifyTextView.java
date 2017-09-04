package com.qq.reader.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.text.Layout;
import android.text.StaticLayout;
import android.util.AttributeSet;
import android.widget.TextView;

public class JustifyTextView extends TextView {
    private int a;
    private int b;

    public JustifyTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onDraw(Canvas canvas) {
        int i = 0;
        Paint paint = getPaint();
        paint.setColor(getCurrentTextColor());
        paint.drawableState = getDrawableState();
        this.b = getMeasuredWidth();
        Object charSequence = getText().toString();
        this.a = 0;
        this.a = (int) (((float) this.a) + getTextSize());
        Layout layout = getLayout();
        if (layout == null) {
            super.onDraw(canvas);
            return;
        }
        FontMetrics fontMetrics = paint.getFontMetrics();
        int ceil = (int) ((((float) ((int) Math.ceil((double) (fontMetrics.descent - fontMetrics.ascent)))) * layout.getSpacingMultiplier()) + layout.getSpacingAdd());
        while (i < layout.getLineCount()) {
            int lineStart = layout.getLineStart(i);
            int lineEnd = layout.getLineEnd(i);
            float desiredWidth = StaticLayout.getDesiredWidth(charSequence, lineStart, lineEnd, getPaint());
            String substring = charSequence.substring(lineStart, lineEnd);
            if (i >= layout.getLineCount() - 1) {
                canvas.drawText(substring, 0.0f, (float) this.a, paint);
            } else if (a(substring)) {
                a(canvas, lineStart, substring, desiredWidth);
            } else {
                canvas.drawText(substring, 0.0f, (float) this.a, paint);
            }
            this.a += ceil;
            i++;
        }
    }

    private void a(Canvas canvas, int i, String str, float f) {
        float f2;
        int i2;
        float f3 = 0.0f;
        if (a(i, str)) {
            CharSequence charSequence = "  ";
            canvas.drawText(charSequence, 0.0f, (float) this.a, getPaint());
            f3 = 0.0f + StaticLayout.getDesiredWidth(charSequence, getPaint());
            str = str.substring(3);
        }
        int length = str.length() - 1;
        if (str.length() > 2 && str.charAt(0) == '　' && str.charAt(1) == '　') {
            Object substring = str.substring(0, 2);
            float desiredWidth = StaticLayout.getDesiredWidth(substring, getPaint());
            canvas.drawText(substring, f3, (float) this.a, getPaint());
            f2 = f3 + desiredWidth;
            i2 = 2;
        } else {
            f2 = f3;
            i2 = 0;
        }
        float f4 = (((float) this.b) - f) / ((float) length);
        for (i2 = 
/*
Method generation error in method: com.qq.reader.view.JustifyTextView.a(android.graphics.Canvas, int, java.lang.String, float):void
jadx.core.utils.exceptions.CodegenException: Error generate insn: PHI: (r0_5 'i2' int) = (r0_4 'i2' int), (r0_8 'i2' int) binds: {(r0_4 'i2' int)=B:9:0x003f, (r0_8 'i2' int)=B:15:0x0086} in method: com.qq.reader.view.JustifyTextView.a(android.graphics.Canvas, int, java.lang.String, float):void
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:184)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:183)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:328)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:265)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:228)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:118)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:83)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:19)
	at jadx.core.ProcessClass.process(ProcessClass.java:43)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: jadx.core.utils.exceptions.CodegenException: PHI can be used only in fallback mode
	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:530)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:514)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:220)
	... 15 more

*/

        private boolean a(int i, String str) {
            return str.length() > 3 && str.charAt(0) == ' ' && str.charAt(1) == ' ';
        }

        private boolean a(String str) {
            if (str == null || str.length() == 0 || str.charAt(str.length() - 1) == '\n') {
                return false;
            }
            return true;
        }
    }
