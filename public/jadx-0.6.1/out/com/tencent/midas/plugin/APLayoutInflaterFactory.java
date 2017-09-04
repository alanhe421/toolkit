package com.tencent.midas.plugin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater.Factory2;
import android.view.View;
import java.lang.reflect.Constructor;
import java.util.HashMap;

@SuppressLint({"NewApi"})
public class APLayoutInflaterFactory implements Factory2 {
    private final HashMap<String, Constructor<? extends View>> a = new HashMap();
    private final Class<?>[] b = new Class[]{Context.class, AttributeSet.class};
    private final Object[] c = new Object[2];

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View onCreateView(android.view.View r10, java.lang.String r11, android.content.Context r12, android.util.AttributeSet r13) {
        /*
        r9 = this;
        r8 = 1;
        r7 = 0;
        r2 = 0;
        r0 = "view";
        r0 = r11.equals(r0);
        if (r0 == 0) goto L_0x0013;
    L_0x000c:
        r0 = "class";
        r11 = r13.getAttributeValue(r2, r0);
    L_0x0013:
        r0 = -1;
        r1 = 46;
        r1 = r11.indexOf(r1);
        if (r0 != r1) goto L_0x001e;
    L_0x001c:
        r1 = r2;
    L_0x001d:
        return r1;
    L_0x001e:
        r0 = r9.c;
        r0 = r0[r7];
        r0 = (android.content.Context) r0;
        r1 = r9.c;
        r1[r7] = r12;
        r1 = r9.a;
        r1 = r1.get(r11);
        r1 = (java.lang.reflect.Constructor) r1;
        if (r1 != 0) goto L_0x0112;
    L_0x0032:
        r1 = r12.getClassLoader();	 Catch:{ NoSuchMethodException -> 0x0063, ClassCastException -> 0x0093, ClassNotFoundException -> 0x00b9, Exception -> 0x00df }
        r1 = r1.loadClass(r11);	 Catch:{ NoSuchMethodException -> 0x0063, ClassCastException -> 0x0093, ClassNotFoundException -> 0x00b9, Exception -> 0x00df }
        r3 = android.view.View.class;
        r3 = r1.asSubclass(r3);	 Catch:{ NoSuchMethodException -> 0x0063, ClassCastException -> 0x0093, ClassNotFoundException -> 0x00b9, Exception -> 0x00df }
        r1 = r9.b;	 Catch:{ NoSuchMethodException -> 0x0063, ClassCastException -> 0x0093, ClassNotFoundException -> 0x00b9, Exception -> 0x0110 }
        r1 = r3.getConstructor(r1);	 Catch:{ NoSuchMethodException -> 0x0063, ClassCastException -> 0x0093, ClassNotFoundException -> 0x00b9, Exception -> 0x0110 }
        r4 = r9.a;	 Catch:{ NoSuchMethodException -> 0x0063, ClassCastException -> 0x0093, ClassNotFoundException -> 0x00b9, Exception -> 0x0110 }
        r4.put(r11, r1);	 Catch:{ NoSuchMethodException -> 0x0063, ClassCastException -> 0x0093, ClassNotFoundException -> 0x00b9, Exception -> 0x0110 }
    L_0x004b:
        r4 = r9.c;	 Catch:{ NoSuchMethodException -> 0x0063, ClassCastException -> 0x0093, ClassNotFoundException -> 0x00b9, Exception -> 0x0110 }
        r5 = 1;
        r4[r5] = r13;	 Catch:{ NoSuchMethodException -> 0x0063, ClassCastException -> 0x0093, ClassNotFoundException -> 0x00b9, Exception -> 0x0110 }
        r5 = 1;
        r1.setAccessible(r5);	 Catch:{ NoSuchMethodException -> 0x0063, ClassCastException -> 0x0093, ClassNotFoundException -> 0x00b9, Exception -> 0x0110 }
        r1 = r1.newInstance(r4);	 Catch:{ NoSuchMethodException -> 0x0063, ClassCastException -> 0x0093, ClassNotFoundException -> 0x00b9, Exception -> 0x0110 }
        r1 = (android.view.View) r1;	 Catch:{ NoSuchMethodException -> 0x0063, ClassCastException -> 0x0093, ClassNotFoundException -> 0x00b9, Exception -> 0x0110 }
        r3 = r9.c;
        r3[r7] = r0;
        r0 = r9.c;
        r0[r8] = r2;
        goto L_0x001d;
    L_0x0063:
        r1 = move-exception;
        r3 = new android.view.InflateException;	 Catch:{ all -> 0x0089 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0089 }
        r4.<init>();	 Catch:{ all -> 0x0089 }
        r5 = r13.getPositionDescription();	 Catch:{ all -> 0x0089 }
        r4 = r4.append(r5);	 Catch:{ all -> 0x0089 }
        r5 = ": Error inflating class ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0089 }
        r4 = r4.append(r11);	 Catch:{ all -> 0x0089 }
        r4 = r4.toString();	 Catch:{ all -> 0x0089 }
        r3.<init>(r4);	 Catch:{ all -> 0x0089 }
        r3.initCause(r1);	 Catch:{ all -> 0x0089 }
        throw r3;	 Catch:{ all -> 0x0089 }
    L_0x0089:
        r1 = move-exception;
        r3 = r9.c;
        r3[r7] = r0;
        r0 = r9.c;
        r0[r8] = r2;
        throw r1;
    L_0x0093:
        r1 = move-exception;
        r3 = new android.view.InflateException;	 Catch:{ all -> 0x0089 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0089 }
        r4.<init>();	 Catch:{ all -> 0x0089 }
        r5 = r13.getPositionDescription();	 Catch:{ all -> 0x0089 }
        r4 = r4.append(r5);	 Catch:{ all -> 0x0089 }
        r5 = ": Class is not a View ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0089 }
        r4 = r4.append(r11);	 Catch:{ all -> 0x0089 }
        r4 = r4.toString();	 Catch:{ all -> 0x0089 }
        r3.<init>(r4);	 Catch:{ all -> 0x0089 }
        r3.initCause(r1);	 Catch:{ all -> 0x0089 }
        throw r3;	 Catch:{ all -> 0x0089 }
    L_0x00b9:
        r1 = move-exception;
        r3 = new android.view.InflateException;	 Catch:{ all -> 0x0089 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0089 }
        r4.<init>();	 Catch:{ all -> 0x0089 }
        r5 = r13.getPositionDescription();	 Catch:{ all -> 0x0089 }
        r4 = r4.append(r5);	 Catch:{ all -> 0x0089 }
        r5 = ": Class not found ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0089 }
        r4 = r4.append(r11);	 Catch:{ all -> 0x0089 }
        r4 = r4.toString();	 Catch:{ all -> 0x0089 }
        r3.<init>(r4);	 Catch:{ all -> 0x0089 }
        r3.initCause(r1);	 Catch:{ all -> 0x0089 }
        throw r3;	 Catch:{ all -> 0x0089 }
    L_0x00df:
        r1 = move-exception;
        r3 = r2;
    L_0x00e1:
        r4 = new android.view.InflateException;	 Catch:{ all -> 0x0089 }
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0089 }
        r5.<init>();	 Catch:{ all -> 0x0089 }
        r6 = r13.getPositionDescription();	 Catch:{ all -> 0x0089 }
        r5 = r5.append(r6);	 Catch:{ all -> 0x0089 }
        r6 = ": Error inflating class ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x0089 }
        if (r3 != 0) goto L_0x010b;
    L_0x00f9:
        r3 = "<unknown>";
    L_0x00fc:
        r3 = r5.append(r3);	 Catch:{ all -> 0x0089 }
        r3 = r3.toString();	 Catch:{ all -> 0x0089 }
        r4.<init>(r3);	 Catch:{ all -> 0x0089 }
        r4.initCause(r1);	 Catch:{ all -> 0x0089 }
        throw r4;	 Catch:{ all -> 0x0089 }
    L_0x010b:
        r3 = r3.getName();	 Catch:{ all -> 0x0089 }
        goto L_0x00fc;
    L_0x0110:
        r1 = move-exception;
        goto L_0x00e1;
    L_0x0112:
        r3 = r2;
        goto L_0x004b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.midas.plugin.APLayoutInflaterFactory.onCreateView(android.view.View, java.lang.String, android.content.Context, android.util.AttributeSet):android.view.View");
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
