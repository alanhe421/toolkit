package jadx.core.xmlgen;

import java.io.IOException;
import java.util.Arrays;

public class CommonBinaryParser extends ParserConstants {
    protected ParserStream is;

    protected String[] parseStringPool() throws IOException {
        this.is.checkInt16(1, "String pool expected");
        return parseStringPoolNoType();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected java.lang.String[] parseStringPoolNoType() throws java.io.IOException {
        /*
        r28 = this;
        r0 = r28;
        r0 = r0.is;
        r24 = r0;
        r24 = r24.getPos();
        r26 = 2;
        r10 = r24 - r26;
        r0 = r28;
        r0 = r0.is;
        r24 = r0;
        r25 = 28;
        r26 = "String pool header size not 0x001c";
        r24.checkInt16(r25, r26);
        r0 = r28;
        r0 = r0.is;
        r24 = r0;
        r8 = r24.readUInt32();
        r4 = r10 + r8;
        r0 = r28;
        r0 = r0.is;
        r24 = r0;
        r13 = r24.readInt32();
        r0 = r28;
        r0 = r0.is;
        r24 = r0;
        r20 = r24.readInt32();
        r0 = r28;
        r0 = r0.is;
        r24 = r0;
        r6 = r24.readInt32();
        r0 = r28;
        r0 = r0.is;
        r24 = r0;
        r24 = r24.readInt32();
        r0 = r24;
        r0 = (long) r0;
        r18 = r0;
        r0 = r28;
        r0 = r0.is;
        r24 = r0;
        r24 = r24.readInt32();
        r0 = r24;
        r0 = (long) r0;
        r22 = r0;
        r0 = r28;
        r0 = r0.is;
        r24 = r0;
        r0 = r24;
        r15 = r0.readInt32Array(r13);
        r0 = r28;
        r0 = r0.is;
        r24 = r0;
        r0 = r24;
        r1 = r20;
        r21 = r0.readInt32Array(r1);
        r0 = r28;
        r0 = r0.is;
        r24 = r0;
        r26 = r10 + r18;
        r25 = "Expected strings start";
        r0 = r24;
        r1 = r26;
        r3 = r25;
        r0.checkPos(r1, r3);
        r24 = 0;
        r24 = (r22 > r24 ? 1 : (r22 == r24 ? 0 : -1));
        if (r24 != 0) goto L_0x00cb;
    L_0x0096:
        r16 = r4;
    L_0x0098:
        r14 = new java.lang.String[r13];
        r0 = r28;
        r0 = r0.is;
        r24 = r0;
        r0 = r28;
        r0 = r0.is;
        r25 = r0;
        r26 = r25.getPos();
        r26 = r16 - r26;
        r0 = r26;
        r0 = (int) r0;
        r25 = r0;
        r12 = r24.readInt8Array(r25);
        r0 = r6 & 256;
        r24 = r0;
        if (r24 == 0) goto L_0x00ce;
    L_0x00bb:
        r7 = 0;
    L_0x00bc:
        if (r7 >= r13) goto L_0x00de;
    L_0x00be:
        r24 = r15[r7];
        r0 = r24;
        r24 = extractString8(r12, r0);
        r14[r7] = r24;
        r7 = r7 + 1;
        goto L_0x00bc;
    L_0x00cb:
        r16 = r10 + r22;
        goto L_0x0098;
    L_0x00ce:
        r7 = 0;
    L_0x00cf:
        if (r7 >= r13) goto L_0x00de;
    L_0x00d1:
        r24 = r15[r7];
        r0 = r24;
        r24 = extractString16(r12, r0);
        r14[r7] = r24;
        r7 = r7 + 1;
        goto L_0x00cf;
    L_0x00de:
        r24 = 0;
        r24 = (r22 > r24 ? 1 : (r22 == r24 ? 0 : -1));
        if (r24 == 0) goto L_0x00f9;
    L_0x00e4:
        r0 = r28;
        r0 = r0.is;
        r24 = r0;
        r26 = r10 + r22;
        r25 = "Expected styles start";
        r0 = r24;
        r1 = r26;
        r3 = r25;
        r0.checkPos(r1, r3);
        if (r20 == 0) goto L_0x00f9;
    L_0x00f9:
        r0 = r28;
        r0 = r0.is;
        r24 = r0;
        r25 = "Skip string pool padding";
        r0 = r24;
        r1 = r25;
        r0.skipToPos(r4, r1);
        return r14;
        */
        throw new UnsupportedOperationException("Method not decompiled: jadx.core.xmlgen.CommonBinaryParser.parseStringPoolNoType():java.lang.String[]");
    }

    private static String extractString8(byte[] strArray, int offset) {
        int start = offset + skipStrLen8(strArray, offset);
        int start2 = start + 1;
        int len = strArray[start];
        if (len == 0) {
            start = start2;
            return "";
        }
        if ((len & 128) != 0) {
            start = start2 + 1;
            len = ((len & 127) << 8) | (strArray[start2] & 255);
        } else {
            start = start2;
        }
        return new String(Arrays.copyOfRange(strArray, start, start + len), ParserStream.STRING_CHARSET_UTF8);
    }

    private static String extractString16(byte[] strArray, int offset) {
        int len = strArray.length;
        int start = offset + skipStrLen16(strArray, offset);
        int end = start;
        while (end + 1 < len && (strArray[end] != (byte) 0 || strArray[end + 1] != (byte) 0)) {
            end += 2;
        }
        return new String(Arrays.copyOfRange(strArray, start, end), ParserStream.STRING_CHARSET_UTF16);
    }

    private static int skipStrLen8(byte[] strArray, int offset) {
        return (strArray[offset] & 128) == 0 ? 1 : 2;
    }

    private static int skipStrLen16(byte[] strArray, int offset) {
        return (strArray[offset + 1] & 128) == 0 ? 2 : 4;
    }

    protected void die(String message) throws IOException {
        throw new IOException("Decode error: " + message + ", position: 0x" + Long.toHexString(this.is.getPos()));
    }
}
