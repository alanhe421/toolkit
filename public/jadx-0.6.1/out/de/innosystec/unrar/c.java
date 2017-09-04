package de.innosystec.unrar;

import de.innosystec.unrar.rarfile.g;
import de.innosystec.unrar.unpack.a;
import java.io.File;
import java.io.IOException;

/* compiled from: Volume */
public class c {
    public static boolean a(a aVar, a aVar2) throws IOException {
        g c = aVar2.c();
        if (!(c.s() < com.tencent.qalsdk.base.a.z || c.m() == -1 || aVar2.a() == ((long) (c.m() ^ -1)))) {
            System.err.println("Data Bad CRC");
        }
        boolean z = !aVar.f().v() || aVar.g();
        File file = new File(a(aVar.a().getAbsolutePath(), z));
        b e = aVar.e();
        if ((e != null && !e.a(file)) || !file.exists()) {
            return false;
        }
        aVar.a(file);
        c = aVar.d();
        if (c == null) {
            return false;
        }
        aVar2.a(c);
        return true;
    }

    public static String a(String str, boolean z) {
        int length;
        int i;
        if (z) {
            length = str.length();
            if (length <= 4 || str.charAt(length - 4) != '.') {
                return null;
            }
            StringBuilder stringBuilder = new StringBuilder();
            i = length - 3;
            stringBuilder.append(str, 0, i);
            if (a(str.charAt(i + 1)) && a(str.charAt(i + 2))) {
                char[] cArr = new char[3];
                str.getChars(i, length, cArr, 0);
                i = cArr.length - 1;
                while (true) {
                    char c = (char) (cArr[i] + 1);
                    cArr[i] = c;
                    if (c != ':') {
                        break;
                    }
                    cArr[i] = '0';
                    i--;
                }
                stringBuilder.append(cArr);
            } else {
                stringBuilder.append("r00");
            }
            return stringBuilder.toString();
        }
        int length2 = str.length();
        int i2 = length2 - 1;
        while (i2 >= 0 && !a(str.charAt(i2))) {
            i2--;
        }
        int i3 = i2 + 1;
        length = i2 - 1;
        while (length >= 0 && a(str.charAt(length))) {
            length--;
        }
        if (length < 0) {
            return null;
        }
        i = length + 1;
        StringBuilder stringBuilder2 = new StringBuilder(length2);
        stringBuilder2.append(str, 0, i);
        char[] cArr2 = new char[((i2 - i) + 1)];
        str.getChars(i, i2 + 1, cArr2, 0);
        i = cArr2.length - 1;
        while (i >= 0) {
            char c2 = (char) (cArr2[i] + 1);
            cArr2[i] = c2;
            if (c2 != ':') {
                break;
            }
            cArr2[i] = '0';
            i--;
        }
        if (i < 0) {
            stringBuilder2.append('1');
        }
        stringBuilder2.append(cArr2);
        stringBuilder2.append(str, i3, length2);
        return stringBuilder2.toString();
    }

    private static boolean a(char c) {
        return c >= '0' && c <= '9';
    }
}
