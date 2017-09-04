package com.qq.reader.common.utils;

import android.graphics.Color;
import android.text.TextUtils;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;

/* compiled from: RichTextParser */
public class ah {

    /* compiled from: RichTextParser */
    private static class a {
        final StringBuilder a = new StringBuilder();

        public void a(char c) {
            this.a.append(c);
        }

        public char a() {
            int length = this.a.length() - 1;
            char charAt = this.a.charAt(length);
            this.a.setLength(length);
            return charAt;
        }

        public void b() {
            this.a.setLength(0);
        }

        public int c() {
            return this.a.length();
        }

        public char d() {
            int length = this.a.length() - 1;
            if (length >= 0) {
                return this.a.charAt(length);
            }
            return '\u0000';
        }

        public void a(String str) {
            this.a.append(str);
        }

        public String toString() {
            return this.a.toString();
        }
    }

    /* compiled from: RichTextParser */
    public static class b {
        public int a;
        public int b;
        public boolean c;
        public String d;
        public String e;
        public int f;
    }

    private static int b(String str) {
        if (str.contains("/color")) {
            return 4;
        }
        if (str.contains("/size")) {
            return 5;
        }
        if (str.contains("/b") && str.length() == 2) {
            return 6;
        }
        if (str.contains("/url")) {
            return 10;
        }
        if (str.contains("/img")) {
            return 8;
        }
        if (str.contains("/bookid")) {
            return 12;
        }
        if (str.contains("color")) {
            return 1;
        }
        if (str.contains("size")) {
            return 2;
        }
        if (str.contains("b") && str.length() == 1) {
            return 3;
        }
        if (str.contains(SocialConstants.PARAM_URL)) {
            return 9;
        }
        if (str.contains(SocialConstants.PARAM_IMG_URL)) {
            return 7;
        }
        if (str.contains("bookid")) {
            return 11;
        }
        return -1;
    }

    public static List<b> a(String str) {
        char[] toCharArray = str.toCharArray();
        a aVar = new a();
        a aVar2 = new a();
        List arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        String str2 = "";
        boolean z = false;
        int i3 = 0;
        for (char c : toCharArray) {
            if (c == '[') {
                aVar2.a(c);
                aVar.a(c);
            } else if (c != ']') {
                aVar.a(c);
            } else if (aVar2.d() == '[') {
                StringBuilder stringBuilder = new StringBuilder();
                do {
                    stringBuilder.insert(0, aVar.a());
                } while (aVar.d() != '[');
                switch (b(stringBuilder.toString())) {
                    case -1:
                        aVar.a(stringBuilder.toString());
                        aVar.a(c);
                        break;
                    case 1:
                        if (i != 0) {
                            aVar.a(stringBuilder.toString());
                            aVar.a(c);
                            break;
                        }
                        aVar.a();
                        if (aVar.c() > 0) {
                            arrayList.add(a(aVar.toString(), i, i2, i3, z, str2));
                            aVar.b();
                        }
                        i3 = 0;
                        i = Color.parseColor(stringBuilder.substring(6));
                        break;
                    case 2:
                        if (i2 > 0) {
                            aVar.a(stringBuilder.toString());
                            aVar.a(c);
                            break;
                        }
                        aVar.a();
                        if (aVar.c() > 0) {
                            arrayList.add(a(aVar.toString(), i, i2, i3, z, str2));
                            aVar.b();
                        }
                        i3 = 0;
                        i2 = Integer.parseInt(stringBuilder.substring(5, stringBuilder.length() - 2));
                        break;
                    case 3:
                        if (!z) {
                            aVar.a();
                            if (aVar.c() > 0) {
                                arrayList.add(a(aVar.toString(), i, i2, i3, z, str2));
                                aVar.b();
                            }
                            i3 = 0;
                            z = true;
                            break;
                        }
                        aVar.a(stringBuilder.toString());
                        aVar.a(c);
                        break;
                    case 4:
                        aVar.a();
                        if (aVar.c() > 0) {
                            arrayList.add(a(aVar.toString(), i, i2, i3, z, str2));
                            aVar.b();
                        }
                        i = 0;
                        break;
                    case 5:
                        aVar.a();
                        if (aVar.c() > 0) {
                            arrayList.add(a(aVar.toString(), i, i2, i3, z, str2));
                            aVar.b();
                        }
                        i2 = 0;
                        break;
                    case 6:
                        aVar.a();
                        if (aVar.c() > 0) {
                            arrayList.add(a(aVar.toString(), i, i2, i3, z, str2));
                            aVar.b();
                        }
                        z = false;
                        break;
                    case 7:
                        aVar.a();
                        if (aVar.c() > 0) {
                            arrayList.add(a(aVar.toString(), i, i2, i3, z, str2));
                            aVar.b();
                        }
                        i3 = 1;
                        break;
                    case 8:
                        aVar.a();
                        if (aVar.c() > 0) {
                            arrayList.add(a(aVar.toString(), i, i2, i3, z, str2));
                            aVar.b();
                        }
                        i3 = 0;
                        break;
                    case 9:
                        if (!TextUtils.isEmpty(str2)) {
                            aVar.a(stringBuilder.toString());
                            aVar.a(c);
                            break;
                        }
                        aVar.a();
                        if (aVar.c() > 0) {
                            arrayList.add(a(aVar.toString(), i, i2, i3, z, str2));
                            aVar.b();
                        }
                        i3 = 0;
                        str2 = stringBuilder.substring(4);
                        break;
                    case 10:
                        aVar.a();
                        if (aVar.c() > 0) {
                            arrayList.add(a(aVar.toString(), i, i2, i3, z, str2));
                            aVar.b();
                        }
                        str2 = "";
                        break;
                    case 11:
                        aVar.a();
                        if (aVar.c() > 0) {
                            arrayList.add(a(aVar.toString(), i, i2, i3, z, str2));
                            aVar.b();
                        }
                        i3 = 2;
                        break;
                    case 12:
                        aVar.a();
                        if (aVar.c() > 0) {
                            arrayList.add(a(aVar.toString(), i, i2, i3, z, str2));
                            aVar.b();
                        }
                        i3 = 0;
                        break;
                }
                aVar2.a();
            } else {
                aVar.a(c);
            }
        }
        if (aVar.c() > 0) {
            arrayList.add(a(aVar.toString(), i, i2, i3, z, str2));
            aVar.b();
        }
        return arrayList;
    }

    private static b a(String str, int i, int i2, int i3, boolean z, String str2) {
        b bVar = new b();
        bVar.a = i;
        bVar.b = i2;
        bVar.d = str;
        bVar.c = z;
        bVar.e = str2;
        bVar.f = i3;
        return bVar;
    }
}
