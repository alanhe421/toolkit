package de.innosystec.unrar.rarfile;

import com.tencent.tinker.android.dx.instruction.Opcodes;

/* compiled from: FileNameDecoder */
public class h {
    public static int a(byte[] bArr, int i) {
        return bArr[i] & 255;
    }

    public static String b(byte[] bArr, int i) {
        int i2 = 0;
        int i3 = i + 1;
        int a = a(bArr, i);
        StringBuffer stringBuffer = new StringBuffer();
        int i4 = 0;
        int i5 = i3;
        i3 = 0;
        while (i5 < bArr.length) {
            int i6;
            int a2;
            if (i2 == 0) {
                i6 = i5 + 1;
                a2 = a(bArr, i5);
                i3 = i6;
                i6 = 8;
            } else {
                i6 = i2;
                a2 = i3;
                i3 = i5;
            }
            switch (a2 >> 6) {
                case 0:
                    i5 = i3 + 1;
                    stringBuffer.append((char) a(bArr, i3));
                    i4++;
                    break;
                case 1:
                    i5 = i3 + 1;
                    stringBuffer.append((char) (a(bArr, i3) + (a << 8)));
                    i4++;
                    break;
                case 2:
                    stringBuffer.append((char) (a(bArr, i3) + (a(bArr, i3 + 1) << 8)));
                    i4++;
                    i5 = i3 + 2;
                    break;
                case 3:
                    i2 = i3 + 1;
                    i3 = a(bArr, i3);
                    if ((i3 & 128) == 0) {
                        i3 += 2;
                        while (i3 > 0 && i4 < bArr.length) {
                            stringBuffer.append((char) a(bArr, i4));
                            i3--;
                            i4++;
                        }
                        i5 = i2;
                        break;
                    }
                    i5 = i2 + 1;
                    int a3 = a(bArr, i2);
                    i2 = (i3 & Opcodes.NEG_FLOAT) + 2;
                    while (i2 > 0 && i4 < bArr.length) {
                        stringBuffer.append((char) (((a(bArr, i4) + a3) & 255) + (a << 8)));
                        i2--;
                        i4++;
                    }
                    break;
                default:
                    i5 = i3;
                    break;
            }
            i3 = (a2 << 2) & 255;
            i2 = i6 - 2;
        }
        return stringBuffer.toString();
    }
}
