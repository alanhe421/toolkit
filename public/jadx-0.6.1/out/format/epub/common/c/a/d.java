package format.epub.common.c.a;

import android.text.TextUtils;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import format.epub.common.b.c;
import format.epub.common.utils.o;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* compiled from: StyleSheetParser */
public class d {
    protected String a;
    protected int b;
    protected Map<String, String> c = new LinkedHashMap();
    List<String> d = new ArrayList();
    boolean e;
    private StringBuilder f = new StringBuilder();
    private String g;
    private String h;

    public d(String str) {
        this.a = str;
        a();
    }

    public void a() {
        this.f.setLength(0);
        this.g = "";
        this.b = 0;
        this.h = "";
        this.c.clear();
        this.d.clear();
        this.e = false;
    }

    public void a(InputStream inputStream) {
        c aVar = new a(new InputStreamReader(inputStream));
        if (aVar.a()) {
            char[] cArr = new char[1024];
            while (true) {
                int a = aVar.a(cArr, 1024);
                if (a <= 0) {
                    aVar.b();
                    return;
                }
                int i;
                if (cArr[0] == '﻿') {
                    i = 1;
                } else {
                    i = 0;
                }
                a(cArr, i, a - i, false);
            }
        }
    }

    public void a(char[] cArr, int i, int i2, boolean z) {
        int i3 = i;
        for (int i4 = i; i4 < i + i2; i4++) {
            char c = cArr[i4];
            if (this.b != 5 && Character.isWhitespace(c)) {
                if (i3 != i4) {
                    this.f.append(cArr, i3, i4 - i3);
                }
                c(this.f.toString());
                this.f.setLength(0);
                i3 = i4 + 1;
            } else if (a(c)) {
                if (i3 != i4) {
                    this.f.append(cArr, i3, i4 - i3);
                }
                c(this.f.toString());
                this.f.setLength(0);
                b(c);
                i3 = i4 + 1;
            }
        }
        if (i3 < i + i2) {
            this.f.append(cArr, i3, (i + i2) - i3);
            if (z) {
                c(this.f.toString());
                this.f.setLength(0);
            }
        }
    }

    private boolean a(char c) {
        boolean z = true;
        switch (this.b) {
            case 2:
                if (c != ';') {
                    z = false;
                }
                return z;
            case 3:
                if (c == '}' || c == ':' || c == '：') {
                    return true;
                }
                return false;
            case 4:
                if (c == ':' || c == '：') {
                    return true;
                }
                return false;
            case 5:
                return c == '}' || c == ';';
            default:
                if (c == '{' || c == ';') {
                    return true;
                }
                return false;
        }
    }

    protected void a(String str, Map<String, String> map) {
    }

    protected String a(String str) {
        if (o.b(str, "url(") && o.a(str, ")")) {
            str = str.substring(4, str.length() - 5);
        }
        if (str.length() > 1 && ((str.charAt(0) == '\"' || str.charAt(0) == '\'') && str.charAt(0) == str.charAt(str.length() - 1))) {
            str = str.substring(1, str.length() - 2);
        }
        return this.a + format.epub.common.utils.d.b(str);
    }

    protected void b(String str) {
    }

    private void b(char c) {
        switch (this.b) {
            case 1:
                switch (c) {
                    case ';':
                        this.b = 0;
                        this.h = "";
                        return;
                    case Opcodes.NEG_INT /*123*/:
                        this.b = 3;
                        this.e = true;
                        return;
                    default:
                        return;
                }
            case 2:
                if (c == ';') {
                    if (!this.d.isEmpty()) {
                        if (!this.e) {
                            b(a((String) this.d.get(0)));
                        }
                        this.d.clear();
                    }
                    this.b = 0;
                    return;
                }
                return;
            case 3:
                if (c == '}') {
                    this.b = 0;
                    a(this.h, this.c);
                    this.h = "";
                    this.c.clear();
                    return;
                }
                return;
            case 4:
                if (c == ':' || c == '：') {
                    this.b = 5;
                    return;
                }
                return;
            case 5:
                if (c == ';') {
                    this.b = 3;
                    return;
                } else if (c == '}') {
                    this.b = 0;
                    a(this.h, this.c);
                    this.h = "";
                    this.c.clear();
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    private void c(String str) {
        if (str != null && str.length() != 0) {
            switch (this.b) {
                case 0:
                    this.h = str;
                    if (str.equals("@import")) {
                        this.b = 2;
                        return;
                    } else {
                        this.b = 1;
                        return;
                    }
                case 1:
                    this.h += ' ' + str;
                    return;
                case 2:
                    this.d.add(str);
                    return;
                case 3:
                    this.b = 4;
                    break;
                case 4:
                    break;
                case 5:
                    o.a(str);
                    String str2 = (String) this.c.get(this.g);
                    if (!TextUtils.isEmpty(str2)) {
                        str = str2 + ' ' + str;
                    }
                    this.c.put(this.g, str);
                    return;
                default:
                    return;
            }
            this.g = str;
            this.c.remove(this.g);
        }
    }
}
