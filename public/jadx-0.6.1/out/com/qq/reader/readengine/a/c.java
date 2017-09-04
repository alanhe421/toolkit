package com.qq.reader.readengine.a;

import android.graphics.Paint;
import android.text.TextPaint;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.db.handle.g;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.cservice.adv.a;
import com.qq.reader.module.bookchapter.online.OnlineChapter;
import com.qq.reader.readengine.kernel.b.b;
import com.qq.reader.readengine.kernel.c.e;
import com.qq.reader.readengine.model.IBook;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: LineBreaker */
public class c {
    public static Map<Character, String> a = new HashMap();
    public static final char[] b = new char[]{'。', '，', ',', '.', '！', '!', '？', '?', '、', '”', '》', '）', '：', '…', '；', ';', '’', '】', '%'};
    public static final char[] c = new char[]{'，', '！', '!', ',', '.', '？', '?', '》', '）', '：', '；', ';', '】'};
    public static final char[] d = new char[]{'。', '、', '%'};
    public static final char[] e = new char[]{'“', '（', '《', '‘', '【', '('};
    public static final char[] f = new char[]{'”', '’'};

    static {
        a.put(Character.valueOf(''), "NULL");
        a.put(Character.valueOf(''), "NULL");
        a.put(Character.valueOf(''), "NULL");
        a.put(Character.valueOf(''), "NULL");
        a.put(Character.valueOf('\t'), "NULL");
        a.put(Character.valueOf(''), "NULL");
        a.put(Character.valueOf(''), "NULL");
        a.put(Character.valueOf(''), "NULL");
    }

    public static ArrayList<Float> a(com.qq.reader.readengine.fileparse.c cVar, int i, int i2, TextPaint textPaint) {
        return a(cVar, i, i2, textPaint, true);
    }

    public static ArrayList<Float> a(com.qq.reader.readengine.fileparse.c cVar, int i, int i2, TextPaint textPaint, boolean z) {
        Object obj;
        ArrayList arrayList = cVar.h;
        ArrayList arrayList2 = cVar.i;
        List j = cVar.j();
        ArrayList arrayList3 = new ArrayList();
        StringBuilder stringBuilder = new StringBuilder(cVar.g);
        List list = cVar.o;
        e eVar = new e();
        eVar.c(cVar.m);
        b bVar = new b();
        boolean a = a.a();
        float f = 0.0f;
        float b = a.b(textPaint);
        float a2 = a.a((Paint) textPaint);
        float c = a.c(textPaint);
        float d = a.d(textPaint);
        float e = a.e(textPaint);
        float F = d.F(ReaderApplication.getApplicationImp());
        float textSize = textPaint.getTextSize();
        if (!a) {
            F = textSize;
            e = a2;
            d = a2;
            c = a2;
        }
        int i3 = 0;
        float f2 = 0.0f;
        ArrayList<Float> arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        Object obj2 = null;
        Object obj3 = null;
        bVar.a();
        if (cVar.f) {
            obj3 = 1;
        }
        if (cVar.d == 0 && cVar.m != 0) {
            i3 = 1;
            textPaint.setTextSize(F);
        }
        if (cVar == null || cVar.m == 0) {
            obj = null;
        } else {
            obj = 1;
        }
        int length = stringBuilder.length();
        Object obj4 = obj3;
        int i4 = 0;
        int i5 = -1;
        while (i4 < length) {
            float f3;
            Object obj5;
            float f4;
            int i6;
            Object obj6;
            Object obj7;
            int i7;
            float[] fArr;
            com.qq.reader.readengine.kernel.e eVar2;
            Object obj8;
            int i8;
            float f5;
            int i9;
            int i10;
            char charAt;
            float f6;
            int i11;
            if (i5 == -1) {
                i5 = i4;
            }
            Object obj9 = 1;
            char a3 = a(stringBuilder.charAt(i4), i4, stringBuilder);
            float a4 = bVar.a(a3, textPaint);
            if (i4 < stringBuilder.length() - 1 && a3 == '。' && stringBuilder.charAt(i4 + 1) == '”') {
                a4 /= 2.0f;
            }
            if (obj2 == null && obj4 == null) {
                Object obj10 = obj4;
                obj4 = obj2;
                f3 = f2;
                obj5 = obj10;
            } else {
                char c2;
                if (!z || i3 == 1) {
                    f4 = a4;
                    c2 = a3;
                    i6 = i4;
                    F = f4;
                } else {
                    if (obj4 != null) {
                        c2 = a3;
                        i6 = i4;
                        while (true) {
                            if ((c2 == ' ' || c2 == '　' || c2 == ' ') && i6 < stringBuilder.length() - 1) {
                                arrayList5.add(new float[]{f2, null});
                                i6++;
                                c2 = a(stringBuilder.charAt(i6), i6, stringBuilder);
                            }
                        }
                        F = bVar.a(c2, textPaint);
                    } else {
                        f4 = a4;
                        c2 = a3;
                        i6 = i4;
                        F = f4;
                    }
                    if (!(c2 == ' ' || c2 == '　')) {
                        f2 += bVar.a('中', textPaint) * 2.0f;
                    }
                }
                f4 = F;
                i4 = i6;
                a3 = c2;
                a4 = f4;
                f3 = f2;
                obj5 = null;
                obj4 = null;
            }
            arrayList5.add(new float[]{f3, f});
            int i12;
            int i13;
            int i14;
            if (a3 == '\n' || a3 == '\r' || a3 == ' ') {
                Object obj11;
                String str;
                boolean z2 = false;
                boolean z3 = false;
                obj4 = 1;
                char c3 = a3;
                i6 = -1;
                while (true) {
                    if ((c3 == '\n' || c3 == '\r' || c3 == ' ' || c3 == ' ' || c3 == '　' || c3 == ' ') && i4 < stringBuilder.length() - 1) {
                        boolean z4;
                        boolean z5;
                        i12 = i4 + 1;
                        char charAt2 = stringBuilder.charAt(i12);
                        if (obj == null || z2 || charAt2 != ' ') {
                            z4 = z2;
                        } else {
                            z4 = a(stringBuilder, i12, z2);
                        }
                        if (obj == null || charAt2 != '　' || z4 || z3) {
                            z5 = z3;
                        } else {
                            z5 = b(stringBuilder, i12, z3);
                        }
                        if (!(obj == null || z4 || charAt2 != ' ')) {
                            i4 = a(stringBuilder, i12);
                            if (i4 <= 0) {
                                i4 = i6;
                            }
                            i6 = i4;
                        }
                        charAt2 = a(charAt2, i12, stringBuilder);
                        arrayList5.add(new float[]{f3, f});
                        z3 = z5;
                        z2 = z4;
                        obj4 = null;
                        c3 = charAt2;
                        i4 = i12;
                    }
                }
                if (i4 == stringBuilder.length() - 1) {
                    obj11 = 1;
                } else {
                    obj11 = obj4;
                }
                if (obj11 != null) {
                    i4++;
                    arrayList5.add(new float[]{f3, f});
                }
                String substring = stringBuilder.substring(i5, i4);
                char[] toCharArray = substring.toCharArray();
                obj6 = null;
                obj7 = null;
                for (i7 = 0; i7 < toCharArray.length; i7++) {
                    char c4 = toCharArray[i7];
                    if (c4 == '　' || c4 == ' ' || c4 == ' ') {
                        toCharArray[i7] = ' ';
                        obj6 = 1;
                    } else if (!(c4 == '\r' || c4 == '\n' || c4 == ' ')) {
                        obj7 = 1;
                    }
                }
                if (obj6 != null) {
                    str = new String(toCharArray);
                } else {
                    str = substring;
                }
                i13 = i4 - 1;
                if (obj7 != null) {
                    a(j, str, f, i3);
                    arrayList.add(new float[]{(float) i5, (float) (i13 + 1), null});
                    arrayList5.remove(arrayList5.size() - 1);
                    obj7 = new float[(str.length() << 1)];
                    Iterator it = arrayList5.iterator();
                    i7 = 0;
                    while (it.hasNext()) {
                        fArr = (float[]) it.next();
                        obj7[i7] = fArr[0];
                        obj7[i7 + 1] = fArr[1];
                        i7 += 2;
                    }
                    arrayList2.add(obj7);
                    obj4 = 1;
                } else {
                    obj4 = null;
                }
                if (obj != null && obj11 == null && z2) {
                    i14 = 10;
                    if (a) {
                        a(j, "", f, 1000);
                        arrayList.add(new float[]{(float) i5, (float) i13, null});
                        arrayList2.add(new float[]{null, f});
                    }
                } else {
                    i14 = i3 == 10 ? 0 : i3;
                }
                if (obj != null && z3 && j.size() > 0) {
                    for (int size = j.size() - 1; size >= 0; size--) {
                        eVar2 = (com.qq.reader.readengine.kernel.e) j.get(size);
                        if (eVar2.g()) {
                            break;
                        }
                        if (eVar2.b()) {
                            eVar2.a(10);
                        }
                    }
                }
                if (i6 > 0) {
                    if (a) {
                        a(j, "", f, i6);
                        arrayList.add(new float[]{(float) i5, (float) i13, null});
                        arrayList2.add(new float[]{null, f});
                    }
                    f.a("addEndLine", "y:" + f + z2 + z2 + i13 + "in:" + stringBuilder.length());
                }
                obj9 = obj4;
                obj8 = 1;
                i8 = i13;
                i7 = i14;
                f5 = f3;
                obj7 = null;
            } else if (f3 + a4 > ((float) i)) {
                Object obj12;
                float f7;
                float f8;
                float f9;
                String substring2;
                Object obj13;
                Iterator it2;
                ArrayList arrayList6;
                Object obj14;
                if (a3 <= ' ' || a3 >= '') {
                    obj14 = null;
                    f5 = f3;
                    i14 = i4;
                    obj12 = null;
                } else {
                    arrayList3.clear();
                    int i15 = i4 - 1;
                    f7 = f3;
                    while (i15 > i5) {
                        char charAt3 = stringBuilder.charAt(i15);
                        if (charAt3 > ' ' && charAt3 < '') {
                            f5 = f7 - bVar.a(charAt3, textPaint);
                            arrayList3.add(0, arrayList5.remove(arrayList5.size() - 1));
                        } else if (d(charAt3)) {
                            f5 = f7 - bVar.a(charAt3, textPaint);
                            arrayList3.add(0, arrayList5.remove(arrayList5.size() - 1));
                        } else {
                            i13 = i15 + 1;
                            if (charAt3 == ' ') {
                                obj12 = 1;
                            } else {
                                obj12 = null;
                            }
                            i14 = i13;
                            obj6 = obj12;
                            obj12 = 1;
                            if (obj12 != null) {
                                arrayList5.addAll(arrayList3);
                                arrayList3.clear();
                                obj14 = obj6;
                                f5 = f3;
                            } else {
                                obj14 = obj6;
                                f5 = f7;
                            }
                        }
                        i15--;
                        f7 = f5;
                    }
                    obj6 = null;
                    i14 = i4;
                    obj12 = null;
                    if (obj12 != null) {
                        obj14 = obj6;
                        f5 = f7;
                    } else {
                        arrayList5.addAll(arrayList3);
                        arrayList3.clear();
                        obj14 = obj6;
                        f5 = f3;
                    }
                }
                if (obj12 != null) {
                    a3 = stringBuilder.charAt(i14);
                    a4 = bVar.a(a3, textPaint);
                }
                if (!a(a3) || f5 + a4 <= ((float) i)) {
                    obj2 = null;
                } else {
                    i14++;
                    obj2 = 1;
                }
                if (obj2 != null) {
                    f8 = (f5 + a4) - ((float) i);
                    obj12 = null;
                    if (i14 < stringBuilder.length() && a(stringBuilder.charAt(i14))) {
                        obj12 = 1;
                    }
                    if ((f8 > 0.0f && r4 != null) || f8 > a4 / 2.0f) {
                        boolean z6 = true;
                        if (i14 < stringBuilder.length()) {
                            z6 = e(stringBuilder.charAt(i14));
                        }
                        if (!z6 || f8 > a4 / 2.0f) {
                            ArrayList arrayList7 = new ArrayList();
                            arrayList7.add(0, arrayList5.remove(arrayList5.size() - 1));
                            for (i12 = i14 - 2; i12 > i5 + 5; i12--) {
                                char charAt4 = stringBuilder.charAt(i12);
                                f7 = ((float[]) arrayList5.get(i12 - i5))[0];
                                if (!a(charAt4)) {
                                    obj7 = 1;
                                    f5 = f7;
                                    obj12 = null;
                                    i9 = i12;
                                    break;
                                }
                                arrayList7.add(0, arrayList5.remove(arrayList5.size() - 1));
                            }
                            obj12 = obj2;
                            i9 = i14;
                            obj7 = null;
                            if (obj7 == null) {
                                i9--;
                                obj12 = null;
                                arrayList5.addAll(arrayList7);
                            }
                            if (obj12 == null) {
                                obj12 = null;
                                if (i9 < stringBuilder.length() && a(stringBuilder.charAt(i9))) {
                                    obj12 = 1;
                                }
                                if (b(a3)) {
                                    f9 = (a4 / 4.0f) * 3.0f;
                                } else if (c(a3)) {
                                    f9 = a4;
                                } else {
                                    f9 = a4 / 2.0f;
                                }
                                a4 = (f5 + f9) - ((float) i);
                                if (a4 > 0.0f || r4 != null) {
                                    i10 = i9 - 1;
                                    obj12 = null;
                                } else {
                                    arrayList.add(new float[]{(float) i5, (float) i9, (-a4) / ((float) ((i9 - i5) - 1))});
                                    substring2 = stringBuilder.substring(i5, i9);
                                    a(j, substring2, f, i3);
                                    i9--;
                                    obj13 = new float[(substring2.length() << 1)];
                                    it2 = arrayList5.iterator();
                                    i6 = 0;
                                    a4 = 0.0f;
                                    while (it2.hasNext()) {
                                        fArr = (float[]) it2.next();
                                        obj13[i6] = fArr[0] + a4;
                                        obj13[i6 + 1] = fArr[1];
                                        i6 += 2;
                                        a4 += f8;
                                    }
                                    arrayList2.add(obj13);
                                    obj12 = 1;
                                    i10 = i9;
                                }
                                obj8 = obj4;
                                i8 = i10;
                                a4 = f9;
                                i7 = i3;
                                obj7 = obj12;
                            } else if (stringBuilder.charAt(i9) != ' ' || r27 == null) {
                                if (i9 > 0) {
                                    charAt = stringBuilder.charAt(i9 - 1);
                                    arrayList6 = new ArrayList();
                                    i6 = i9;
                                    f9 = f5;
                                    while (d(charAt) && i6 > i5) {
                                        arrayList6.add(0, arrayList5.remove(arrayList5.size() - 1));
                                        i6--;
                                        f9 -= bVar.a(charAt, textPaint);
                                        if (i6 > 0) {
                                            break;
                                        }
                                        charAt = stringBuilder.charAt(i6 - 1);
                                    }
                                    i4 = i6;
                                    f8 = f9;
                                    if (i4 > i5) {
                                        arrayList5.addAll(arrayList6);
                                    } else {
                                        i9 = i4;
                                        if ((i9 - i5) - 1 == 0) {
                                            f6 = (((float) i) - f8) / ((float) ((i9 - i5) - 1));
                                        } else {
                                            f6 = 0.0f;
                                        }
                                        arrayList.add(new float[]{(float) i5, (float) i9, f6});
                                        substring2 = stringBuilder.substring(i5, i9);
                                        a(j, substring2, f, i3);
                                        i13 = i9 - 1;
                                        arrayList5.remove(arrayList5.size() - 1);
                                        obj13 = new float[(substring2.length() << 1)];
                                        it2 = arrayList5.iterator();
                                        i14 = 0;
                                        f3 = 0.0f;
                                        while (it2.hasNext()) {
                                            fArr = (float[]) it2.next();
                                            obj13[i14] = fArr[0] + f3;
                                            obj13[i14 + 1] = fArr[1];
                                            i14 += 2;
                                            f3 += f6;
                                        }
                                        arrayList2.add(obj13);
                                        i14 = 1;
                                        obj8 = obj4;
                                        i7 = i3;
                                        f4 = f8;
                                        i8 = i13;
                                        f5 = f4;
                                    }
                                }
                                f8 = f5;
                                if ((i9 - i5) - 1 == 0) {
                                    f6 = 0.0f;
                                } else {
                                    f6 = (((float) i) - f8) / ((float) ((i9 - i5) - 1));
                                }
                                arrayList.add(new float[]{(float) i5, (float) i9, f6});
                                substring2 = stringBuilder.substring(i5, i9);
                                a(j, substring2, f, i3);
                                i13 = i9 - 1;
                                arrayList5.remove(arrayList5.size() - 1);
                                obj13 = new float[(substring2.length() << 1)];
                                it2 = arrayList5.iterator();
                                i14 = 0;
                                f3 = 0.0f;
                                while (it2.hasNext()) {
                                    fArr = (float[]) it2.next();
                                    obj13[i14] = fArr[0] + f3;
                                    obj13[i14 + 1] = fArr[1];
                                    i14 += 2;
                                    f3 += f6;
                                }
                                arrayList2.add(obj13);
                                i14 = 1;
                                obj8 = obj4;
                                i7 = i3;
                                f4 = f8;
                                i8 = i13;
                                f5 = f4;
                            } else {
                                arrayList5.remove(arrayList5.size() - 1);
                                arrayList.add(new float[]{(float) i5, (float) i9, null});
                                substring2 = stringBuilder.substring(i5, i9);
                                a(j, substring2, f, i3);
                                i9--;
                                obj7 = new float[(substring2.length() << 1)];
                                Iterator it3 = arrayList5.iterator();
                                i6 = 0;
                                while (it3.hasNext()) {
                                    fArr = (float[]) it3.next();
                                    obj7[i6] = fArr[0];
                                    obj7[i6 + 1] = fArr[1];
                                    i6 += 2;
                                }
                                arrayList2.add(obj7);
                                i14 = 1;
                                obj8 = obj4;
                                i8 = i9;
                                i7 = i3;
                            }
                        }
                    }
                }
                obj12 = obj2;
                i9 = i14;
                if (obj12 == null) {
                    if (stringBuilder.charAt(i9) != ' ') {
                    }
                    if (i9 > 0) {
                        charAt = stringBuilder.charAt(i9 - 1);
                        arrayList6 = new ArrayList();
                        i6 = i9;
                        f9 = f5;
                        while (d(charAt)) {
                            arrayList6.add(0, arrayList5.remove(arrayList5.size() - 1));
                            i6--;
                            f9 -= bVar.a(charAt, textPaint);
                            if (i6 > 0) {
                                break;
                            }
                            charAt = stringBuilder.charAt(i6 - 1);
                        }
                        i4 = i6;
                        f8 = f9;
                        if (i4 > i5) {
                            i9 = i4;
                            if ((i9 - i5) - 1 == 0) {
                                f6 = (((float) i) - f8) / ((float) ((i9 - i5) - 1));
                            } else {
                                f6 = 0.0f;
                            }
                            arrayList.add(new float[]{(float) i5, (float) i9, f6});
                            substring2 = stringBuilder.substring(i5, i9);
                            a(j, substring2, f, i3);
                            i13 = i9 - 1;
                            arrayList5.remove(arrayList5.size() - 1);
                            obj13 = new float[(substring2.length() << 1)];
                            it2 = arrayList5.iterator();
                            i14 = 0;
                            f3 = 0.0f;
                            while (it2.hasNext()) {
                                fArr = (float[]) it2.next();
                                obj13[i14] = fArr[0] + f3;
                                obj13[i14 + 1] = fArr[1];
                                i14 += 2;
                                f3 += f6;
                            }
                            arrayList2.add(obj13);
                            i14 = 1;
                            obj8 = obj4;
                            i7 = i3;
                            f4 = f8;
                            i8 = i13;
                            f5 = f4;
                        } else {
                            arrayList5.addAll(arrayList6);
                        }
                    }
                    f8 = f5;
                    if ((i9 - i5) - 1 == 0) {
                        f6 = 0.0f;
                    } else {
                        f6 = (((float) i) - f8) / ((float) ((i9 - i5) - 1));
                    }
                    arrayList.add(new float[]{(float) i5, (float) i9, f6});
                    substring2 = stringBuilder.substring(i5, i9);
                    a(j, substring2, f, i3);
                    i13 = i9 - 1;
                    arrayList5.remove(arrayList5.size() - 1);
                    obj13 = new float[(substring2.length() << 1)];
                    it2 = arrayList5.iterator();
                    i14 = 0;
                    f3 = 0.0f;
                    while (it2.hasNext()) {
                        fArr = (float[]) it2.next();
                        obj13[i14] = fArr[0] + f3;
                        obj13[i14 + 1] = fArr[1];
                        i14 += 2;
                        f3 += f6;
                    }
                    arrayList2.add(obj13);
                    i14 = 1;
                    obj8 = obj4;
                    i7 = i3;
                    f4 = f8;
                    i8 = i13;
                    f5 = f4;
                } else {
                    obj12 = null;
                    obj12 = 1;
                    if (b(a3)) {
                        f9 = (a4 / 4.0f) * 3.0f;
                    } else if (c(a3)) {
                        f9 = a4;
                    } else {
                        f9 = a4 / 2.0f;
                    }
                    a4 = (f5 + f9) - ((float) i);
                    if (a4 > 0.0f) {
                    }
                    i10 = i9 - 1;
                    obj12 = null;
                    obj8 = obj4;
                    i8 = i10;
                    a4 = f9;
                    i7 = i3;
                    obj7 = obj12;
                }
            } else {
                obj7 = null;
                obj8 = obj4;
                i8 = i4;
                f5 = f3;
                i7 = i3;
            }
            if (obj7 == null && obj8 == null) {
                f3 = f5 + a4;
                if (i8 == stringBuilder.length() - 1) {
                    arrayList.add(new float[]{(float) i5, (float) (i8 + 1), null});
                    String substring3 = stringBuilder.substring(i5, i8 + 1);
                    a(j, substring3, f, i7);
                    ((com.qq.reader.readengine.kernel.e) j.get(j.size() - 1)).a(b);
                    arrayList4.add(Float.valueOf(f3));
                    obj6 = new float[(substring3.length() << 1)];
                    Iterator it4 = arrayList5.iterator();
                    i10 = 0;
                    while (it4.hasNext()) {
                        fArr = (float[]) it4.next();
                        obj6[i10] = fArr[0];
                        obj6[i10 + 1] = fArr[1];
                        i10 += 2;
                    }
                    arrayList2.add(obj6);
                }
                f5 = f3;
                i11 = i5;
            } else {
                arrayList4.add(Float.valueOf(f5));
                arrayList5.clear();
                f5 = 0.0f;
                i11 = -1;
            }
            i8++;
            if (i2 > 0 && j.size() > 0) {
                if (i8 >= stringBuilder.length()) {
                    i5 = j.size();
                    if (i5 > 0 && eVar.a() < i5) {
                        eVar.b(i5 - 1);
                        list.add(eVar);
                        eVar2 = (com.qq.reader.readengine.kernel.e) j.get(i5 - 1);
                        if (cVar.b != null && a) {
                            if (obj == null || !cVar.b.a(cVar, 0)) {
                                eVar2.a(b);
                            } else {
                                com.qq.reader.readengine.kernel.e bVar2 = new b("");
                                bVar2.b(eVar2.f() + bVar2.j());
                                j.set(i5 - 1, bVar2);
                            }
                        }
                        f.a("end new page", "size:" + i5 + "height:" + eVar2.e() + "lineY:" + eVar2.f());
                    }
                } else if (obj7 != null || obj8 != null) {
                    Boolean valueOf;
                    com.qq.reader.readengine.kernel.e eVar3;
                    Boolean bool;
                    e eVar4;
                    if (obj7 != null && i8 < stringBuilder.length()) {
                        charAt = stringBuilder.charAt(i8);
                        if (charAt == '\n' || charAt == '\r' || charAt == ' ') {
                            obj3 = 1;
                            valueOf = Boolean.valueOf(false);
                            i9 = j.size() - 1;
                            eVar2 = (com.qq.reader.readengine.kernel.e) j.get(i9);
                            if (!eVar2.b() || i9 <= 0) {
                                eVar3 = eVar2;
                                bool = valueOf;
                            } else {
                                i9--;
                                eVar3 = (com.qq.reader.readengine.kernel.e) j.get(i9);
                                bool = Boolean.valueOf(true);
                            }
                            if (obj9 == null) {
                                if (obj3 != null) {
                                    eVar3.a(true);
                                    if (i7 != 1) {
                                        textPaint.setTextSize(textSize);
                                        bVar.a();
                                        i10 = 0;
                                        F = d;
                                    } else {
                                        F = c;
                                        i10 = i7;
                                    }
                                } else if (i7 != 1) {
                                    F = e;
                                    i10 = i7;
                                } else {
                                    F = a2;
                                    i10 = i7;
                                }
                                eVar3.a(F);
                                f6 = F + f;
                            } else {
                                i10 = i7;
                                f6 = f;
                            }
                            if (bool.booleanValue()) {
                                i7 = i9;
                                F = f6;
                                eVar4 = eVar;
                            } else {
                                eVar2 = (com.qq.reader.readengine.kernel.e) j.get(i9 + 1);
                                eVar2.b(f6);
                                f6 += eVar2.e();
                                if (f6 <= ((float) i2)) {
                                    eVar.b(i9);
                                    eVar.a(true);
                                    list.add(eVar);
                                    eVar4 = new e();
                                    eVar4.c(cVar.m);
                                    eVar4.a(i9 + 1);
                                    eVar2.b(0.0f);
                                    F = eVar2.e();
                                } else {
                                    F = f6;
                                    eVar4 = eVar;
                                }
                                i7 = i9 + 1;
                            }
                            if (F + b > ((float) i2)) {
                                eVar4.b(i7);
                                eVar4.a(true);
                                list.add(eVar4);
                                eVar4 = new e();
                                eVar4.c(cVar.m);
                                eVar4.a(i7 + 1);
                                F = 0.0f;
                            }
                            i7 = i10;
                            f = F;
                            eVar = eVar4;
                            obj8 = obj3;
                        }
                    }
                    obj3 = obj8;
                    valueOf = Boolean.valueOf(false);
                    i9 = j.size() - 1;
                    eVar2 = (com.qq.reader.readengine.kernel.e) j.get(i9);
                    if (eVar2.b()) {
                    }
                    eVar3 = eVar2;
                    bool = valueOf;
                    if (obj9 == null) {
                        i10 = i7;
                        f6 = f;
                    } else {
                        if (obj3 != null) {
                            eVar3.a(true);
                            if (i7 != 1) {
                                F = c;
                                i10 = i7;
                            } else {
                                textPaint.setTextSize(textSize);
                                bVar.a();
                                i10 = 0;
                                F = d;
                            }
                        } else if (i7 != 1) {
                            F = a2;
                            i10 = i7;
                        } else {
                            F = e;
                            i10 = i7;
                        }
                        eVar3.a(F);
                        f6 = F + f;
                    }
                    if (bool.booleanValue()) {
                        i7 = i9;
                        F = f6;
                        eVar4 = eVar;
                    } else {
                        eVar2 = (com.qq.reader.readengine.kernel.e) j.get(i9 + 1);
                        eVar2.b(f6);
                        f6 += eVar2.e();
                        if (f6 <= ((float) i2)) {
                            F = f6;
                            eVar4 = eVar;
                        } else {
                            eVar.b(i9);
                            eVar.a(true);
                            list.add(eVar);
                            eVar4 = new e();
                            eVar4.c(cVar.m);
                            eVar4.a(i9 + 1);
                            eVar2.b(0.0f);
                            F = eVar2.e();
                        }
                        i7 = i9 + 1;
                    }
                    if (F + b > ((float) i2)) {
                        eVar4.b(i7);
                        eVar4.a(true);
                        list.add(eVar4);
                        eVar4 = new e();
                        eVar4.c(cVar.m);
                        eVar4.a(i7 + 1);
                        F = 0.0f;
                    }
                    i7 = i10;
                    f = F;
                    eVar = eVar4;
                    obj8 = obj3;
                }
            }
            obj2 = obj8;
            i4 = i8;
            i5 = i11;
            i3 = i7;
            obj4 = obj5;
            f2 = f5;
        }
        a(cVar, textPaint, i2);
        textPaint.setTextSize(textSize);
        bVar.a();
        return arrayList4;
    }

    public static void a(com.qq.reader.readengine.fileparse.c cVar, TextPaint textPaint, int i) {
        if (a.a()) {
            Object obj = null;
            if (!(cVar == null || cVar.m == 0)) {
                obj = 1;
            }
            if (obj != null) {
                List j = cVar.j();
                ArrayList arrayList = cVar.h;
                ArrayList arrayList2 = cVar.i;
                List list = cVar.o;
                com.qq.reader.readengine.fileparse.d dVar = cVar.b;
                a.c(textPaint);
                if (j.size() > 0 && dVar != null && list.size() > 0 && dVar.a(cVar, 0)) {
                    com.qq.reader.readengine.kernel.e eVar;
                    int i2;
                    com.qq.reader.readengine.kernel.e eVar2;
                    boolean z;
                    a a;
                    IBook t = dVar.t();
                    com.qq.reader.readengine.kernel.e eVar3 = (com.qq.reader.readengine.kernel.e) j.get(j.size() - 1);
                    boolean z2 = false;
                    int i3 = -1;
                    if (eVar3.a() == 104) {
                        int size = j.size() - 3;
                        if (j.size() - 2 >= 0) {
                            int size2 = j.size() - 2;
                            eVar = eVar3;
                            i2 = size2;
                            int i4 = size;
                            eVar2 = (com.qq.reader.readengine.kernel.e) j.get(size2);
                            i3 = i4;
                        } else {
                            i3 = size;
                            i2 = -1;
                            eVar2 = null;
                            eVar = eVar3;
                        }
                    } else if (eVar3.a() == 105) {
                        i3 = j.size() - 2;
                        eVar = null;
                        i2 = j.size() - 1;
                        eVar2 = eVar3;
                    } else {
                        i2 = -1;
                        eVar2 = null;
                        eVar = null;
                    }
                    if (i3 >= 0 && ((com.qq.reader.readengine.kernel.e) j.get(i3)).c()) {
                        z2 = true;
                    }
                    e eVar4 = (e) list.get(list.size() - 1);
                    int a2 = g.a().a(cVar.m, z2);
                    if (eVar2 != null) {
                        OnlineChapter onlineChapter = null;
                        if (t != null) {
                            OnlineChapter a3 = com.qq.reader.common.db.handle.b.a().a(String.valueOf(t.getBookNetId()), cVar.m);
                            if (a3 != null && (eVar2 instanceof b)) {
                                ((b) eVar2).a(a3);
                            }
                            onlineChapter = a3;
                        }
                        float e = eVar2.e();
                        if (a2 == 1) {
                            eVar2.a((float) b.c);
                        } else if (a2 == 2) {
                            eVar2.a((float) b.d);
                        } else if (onlineChapter == null || onlineChapter.getHotCommentList().size() <= 0 || z2) {
                            eVar2.a((float) b.a);
                        } else {
                            eVar2.a((float) b.b);
                        }
                        if (eVar2 instanceof b) {
                            ((b) eVar2).b(z2);
                        }
                        if (e != eVar2.e()) {
                            a(i2, (float) i, cVar);
                        }
                    }
                    if (a2 > 0 || z2) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (t != null) {
                        a = com.qq.reader.cservice.adv.d.a(t.getBookNetId(), cVar.m, z);
                    } else {
                        a = null;
                    }
                    if (a != null) {
                        float f = eVar3.f() + eVar3.e();
                        if (eVar == null) {
                            com.qq.reader.readengine.kernel.e aVar = new com.qq.reader.readengine.kernel.b.a("");
                            ((com.qq.reader.readengine.kernel.b.a) aVar).a(a);
                            float j2 = f + aVar.j();
                            if (aVar.e() + j2 > ((float) i)) {
                                e eVar5 = (e) list.get(list.size() - 1);
                                eVar5.b(j.size() - 1);
                                aVar.b(0.0f);
                                j.add(aVar);
                                eVar4 = new e();
                                eVar4.c(eVar5.c());
                                eVar4.a(j.size() - 1);
                                eVar4.b(j.size() - 1);
                                list.add(eVar4);
                                arrayList.add(arrayList.get(arrayList.size() - 1));
                                arrayList2.add(new float[]{null, null});
                            } else {
                                aVar.b(j2);
                                j.add(aVar);
                                ((e) list.get(list.size() - 1)).b(j.size() - 1);
                                arrayList.add(arrayList.get(arrayList.size() - 1));
                                arrayList2.add(new float[]{null, j2});
                            }
                            i.a("event_B150", null, ReaderApplication.getApplicationImp());
                        }
                    }
                }
            }
        }
    }

    private static int a(StringBuilder stringBuilder, int i) {
        if (i >= stringBuilder.length() - 6) {
            return 0;
        }
        int i2 = i + 1;
        char charAt = stringBuilder.charAt(i2);
        i2++;
        char charAt2 = stringBuilder.charAt(i2);
        i2++;
        char charAt3 = stringBuilder.charAt(i2);
        if (charAt != ' ' || charAt2 != ' ' || charAt3 != ' ') {
            return 0;
        }
        int i3 = i2;
        i2 = 0;
        while (true) {
            i3++;
            if (stringBuilder.charAt(i3) != '　') {
                break;
            }
            i2++;
        }
        switch (i2) {
            case 0:
                return 0;
            case 2:
                return 100;
            case 3:
                return 103;
            default:
                return 101;
        }
    }

    private static boolean a(StringBuilder stringBuilder, int i, boolean z) {
        if (i >= stringBuilder.length() - 2) {
            return z;
        }
        int i2 = i + 1;
        char charAt = stringBuilder.charAt(i2);
        char charAt2 = stringBuilder.charAt(i2 + 1);
        if (charAt == '　' && charAt2 == ' ') {
            return true;
        }
        return z;
    }

    private static boolean b(StringBuilder stringBuilder, int i, boolean z) {
        if (i >= stringBuilder.length() - 2) {
            return z;
        }
        int i2 = i + 1;
        char charAt = stringBuilder.charAt(i2);
        char charAt2 = stringBuilder.charAt(i2 + 1);
        if (charAt == ' ' && charAt2 == '　') {
            return true;
        }
        return z;
    }

    private static char a(char c, int i, StringBuilder stringBuilder) {
        if (c == '?') {
            stringBuilder.setCharAt(i, '？');
            return '？';
        } else if (!a.containsKey(Character.valueOf(c))) {
            return c;
        } else {
            stringBuilder.setCharAt(i, ' ');
            return ' ';
        }
    }

    protected static float a(List<com.qq.reader.readengine.kernel.e> list, String str, float f, int i) {
        com.qq.reader.readengine.kernel.e eVar = new com.qq.reader.readengine.kernel.e(str);
        eVar.b(f);
        eVar.a(i);
        list.add(eVar);
        return eVar.e();
    }

    public static boolean a(char c) {
        for (char c2 : b) {
            if (c == c2) {
                return true;
            }
        }
        return false;
    }

    public static boolean b(char c) {
        for (char c2 : c) {
            if (c == c2) {
                return true;
            }
        }
        return false;
    }

    public static boolean c(char c) {
        for (char c2 : d) {
            if (c == c2) {
                return true;
            }
        }
        return false;
    }

    public static boolean d(char c) {
        for (char c2 : e) {
            if (c == c2) {
                return true;
            }
        }
        return false;
    }

    public static boolean e(char c) {
        for (char c2 : f) {
            if (c == c2) {
                return true;
            }
        }
        return false;
    }

    private static void a(int i, float f, com.qq.reader.readengine.fileparse.c cVar) {
        e eVar;
        Object obj;
        int size = cVar.o.size();
        List j = cVar.j();
        int size2 = j.size();
        com.qq.reader.readengine.kernel.e eVar2 = (com.qq.reader.readengine.kernel.e) j.get(i);
        int i2 = size - 1;
        e eVar3 = null;
        while (i2 >= 0) {
            eVar3 = (e) cVar.o.get(i2);
            if (eVar3.d(i)) {
                eVar = eVar3;
                obj = 1;
                break;
            }
            i2--;
        }
        eVar = eVar3;
        obj = null;
        if (obj != null) {
            float f2 = eVar2.f();
            int i3 = i;
            while (i3 <= eVar.b()) {
                eVar2 = (com.qq.reader.readengine.kernel.e) j.get(i3);
                if (i3 != i) {
                    f2 += eVar2.j();
                }
                if (eVar2.e() + f2 > f) {
                    eVar.b(i3 - 1);
                    int i4 = i2 + 1;
                    int i5 = 0;
                    i2 = i3;
                    int i6 = size;
                    size = i4;
                    while (i2 < size2) {
                        if (size >= i6) {
                            e eVar4 = new e();
                            eVar4.c(((e) cVar.o.get(i6 - 1)).c());
                            cVar.o.add(eVar4);
                            eVar4.a(i2);
                            i3 = i6 + 1;
                        } else {
                            i3 = i6;
                        }
                        e eVar5 = (e) cVar.o.get(size);
                        if (i5 == 0) {
                            eVar5.a(i2);
                        }
                        com.qq.reader.readengine.kernel.e eVar6 = (com.qq.reader.readengine.kernel.e) j.get(i2);
                        eVar6.b((float) i5);
                        if (i5 > 0) {
                            i6 = (int) ((eVar6.j() + eVar6.e()) + ((float) i5));
                        } else {
                            i6 = (int) (eVar6.e() + ((float) i5));
                        }
                        if (((float) i6) > f) {
                            i6 = size + 1;
                            eVar5.b(i2 - 1);
                            size = i6;
                            i5 = 0;
                            i6 = i3;
                        } else {
                            eVar5.b(i2);
                            i5 = i6;
                            i2++;
                            i6 = i3;
                        }
                    }
                    return;
                }
                if (f2 > 0.0f) {
                    eVar2.b(f2);
                }
                i3++;
                f2 = eVar2.e() + f2;
            }
        }
    }
}
