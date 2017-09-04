package org.apache.commons.codec.binary;

import com.etrump.jni.ETConverter;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;

public class Base64 implements BinaryDecoder, BinaryEncoder {
    static final int BASELENGTH = 255;
    static final byte[] CHUNK_SEPARATOR = "\r\n".getBytes();
    static final int CHUNK_SIZE = 76;
    static final int EIGHTBIT = 8;
    static final int FOURBYTE = 4;
    static final int LOOKUPLENGTH = 64;
    static final byte PAD = (byte) 61;
    static final int SIGN = -128;
    static final int SIXTEENBIT = 16;
    static final int TWENTYFOURBITGROUP = 24;
    private static byte[] base64Alphabet = new byte[255];
    private static byte[] lookUpBase64Alphabet = new byte[64];

    static {
        int i;
        int i2 = 0;
        for (i = 0; i < 255; i++) {
            base64Alphabet[i] = (byte) -1;
        }
        for (i = 90; i >= 65; i--) {
            base64Alphabet[i] = (byte) (i - 65);
        }
        for (i = 122; i >= 97; i--) {
            base64Alphabet[i] = (byte) ((i - 97) + 26);
        }
        for (i = 57; i >= 48; i--) {
            base64Alphabet[i] = (byte) ((i - 48) + 52);
        }
        base64Alphabet[43] = (byte) 62;
        base64Alphabet[47] = (byte) 63;
        for (i = 0; i <= 25; i++) {
            lookUpBase64Alphabet[i] = (byte) (i + 65);
        }
        int i3 = 26;
        i = 0;
        while (i3 <= 51) {
            lookUpBase64Alphabet[i3] = (byte) (i + 97);
            i3++;
            i++;
        }
        i = 52;
        while (i <= 61) {
            lookUpBase64Alphabet[i] = (byte) (i2 + 48);
            i++;
            i2++;
        }
        lookUpBase64Alphabet[62] = (byte) 43;
        lookUpBase64Alphabet[63] = (byte) 47;
    }

    private static boolean isBase64(byte b) {
        if (b != PAD && base64Alphabet[b] == (byte) -1) {
            return false;
        }
        return true;
    }

    public static boolean isArrayByteBase64(byte[] bArr) {
        if (r4 == 0) {
            return true;
        }
        for (byte isBase64 : discardWhitespace(bArr)) {
            if (!isBase64(isBase64)) {
                return false;
            }
        }
        return true;
    }

    public static byte[] encodeBase64(byte[] bArr) {
        return encodeBase64(bArr, false);
    }

    public static byte[] encodeBase64Chunked(byte[] bArr) {
        return encodeBase64(bArr, true);
    }

    public Object decode(Object obj) throws DecoderException {
        if (obj instanceof byte[]) {
            return decode((byte[]) obj);
        }
        throw new DecoderException("Parameter supplied to Base64 decode is not a byte[]");
    }

    public byte[] decode(byte[] bArr) {
        return decodeBase64(bArr);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z) {
        int i;
        int i2;
        int length = bArr.length * 8;
        int i3 = length % 24;
        int i4 = length / 24;
        byte[] bArr2 = (byte[]) null;
        length = 0;
        if (i3 != 0) {
            i = (i4 + 1) * 4;
        } else {
            i = i4 * 4;
        }
        if (z) {
            if (CHUNK_SEPARATOR.length == 0) {
                length = 0;
            } else {
                length = (int) Math.ceil((double) (((float) i) / 76.0f));
            }
            i += CHUNK_SEPARATOR.length * length;
        }
        Object obj = new byte[i];
        int i5 = 76;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i7 < i4) {
            int i9;
            int i10;
            i2 = i7 * 3;
            byte b = bArr[i2];
            byte b2 = bArr[i2 + 1];
            byte b3 = bArr[i2 + 2];
            byte b4 = (byte) (b2 & 15);
            byte b5 = (byte) (b & 3);
            if ((b & SIGN) == 0) {
                i9 = (byte) (b >> 2);
            } else {
                byte b6 = (byte) ((b >> 2) ^ Opcodes.AND_LONG_2ADDR);
            }
            if ((b2 & SIGN) == 0) {
                i10 = (byte) (b2 >> 4);
            } else {
                b = (byte) ((b2 >> 4) ^ ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK);
            }
            if ((b3 & SIGN) == 0) {
                i2 = (byte) (b3 >> 6);
            } else {
                i2 = (byte) ((b3 >> 6) ^ 252);
            }
            obj[i8] = lookUpBase64Alphabet[i9];
            obj[i8 + 1] = lookUpBase64Alphabet[i10 | (b5 << 4)];
            obj[i8 + 2] = lookUpBase64Alphabet[i2 | (b4 << 2)];
            obj[i8 + 3] = lookUpBase64Alphabet[b3 & 63];
            i10 = i8 + 4;
            if (z && i10 == i5) {
                System.arraycopy(CHUNK_SEPARATOR, 0, obj, i10, CHUNK_SEPARATOR.length);
                i2 = i6 + 1;
                i6 = ((i2 + 1) * 76) + (CHUNK_SEPARATOR.length * i2);
                i5 = CHUNK_SEPARATOR.length + i10;
            } else {
                i2 = i6;
                i6 = i5;
                i5 = i10;
            }
            i7++;
            i8 = i5;
            i5 = i6;
            i6 = i2;
        }
        i2 = i7 * 3;
        byte b7;
        if (i3 == 8) {
            byte b8 = bArr[i2];
            b7 = (byte) (b8 & 3);
            obj[i8] = lookUpBase64Alphabet[(b8 & SIGN) == 0 ? (byte) (b8 >> 2) : (byte) ((b8 >> 2) ^ Opcodes.AND_LONG_2ADDR)];
            obj[i8 + 1] = lookUpBase64Alphabet[b7 << 4];
            obj[i8 + 2] = PAD;
            obj[i8 + 3] = PAD;
        } else if (i3 == 16) {
            b7 = bArr[i2];
            b = bArr[i2 + 1];
            b6 = (byte) (b & 15);
            byte b9 = (byte) (b7 & 3);
            if ((b7 & SIGN) == 0) {
                i5 = (byte) (b7 >> 2);
            } else {
                b7 = (byte) ((b7 >> 2) ^ Opcodes.AND_LONG_2ADDR);
            }
            i2 = (b & SIGN) == 0 ? (byte) (b >> 4) : (byte) ((b >> 4) ^ ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK);
            obj[i8] = lookUpBase64Alphabet[i5];
            obj[i8 + 1] = lookUpBase64Alphabet[i2 | (b9 << 4)];
            obj[i8 + 2] = lookUpBase64Alphabet[b6 << 2];
            obj[i8 + 3] = PAD;
        }
        if (z && i6 < r2) {
            System.arraycopy(CHUNK_SEPARATOR, 0, obj, i - CHUNK_SEPARATOR.length, CHUNK_SEPARATOR.length);
        }
        return obj;
    }

    public static byte[] decodeBase64(byte[] bArr) {
        int i = 0;
        byte[] discardNonBase64 = discardNonBase64(bArr);
        if (discardNonBase64.length == 0) {
            return new byte[0];
        }
        int length = discardNonBase64.length / 4;
        byte[] bArr2 = (byte[]) null;
        int length2 = discardNonBase64.length;
        while (discardNonBase64[length2 - 1] == PAD) {
            length2--;
            if (length2 == 0) {
                return new byte[0];
            }
        }
        bArr2 = new byte[(length2 - length)];
        int i2 = 0;
        while (i < length) {
            int i3 = i * 4;
            byte b = discardNonBase64[i3 + 2];
            byte b2 = discardNonBase64[i3 + 3];
            byte b3 = base64Alphabet[discardNonBase64[i3]];
            byte b4 = base64Alphabet[discardNonBase64[i3 + 1]];
            if (b != PAD && b2 != PAD) {
                b = base64Alphabet[b];
                b2 = base64Alphabet[b2];
                bArr2[i2] = (byte) ((b3 << 2) | (b4 >> 4));
                bArr2[i2 + 1] = (byte) (((b4 & 15) << 4) | ((b >> 2) & 15));
                bArr2[i2 + 2] = (byte) ((b << 6) | b2);
            } else if (b == PAD) {
                bArr2[i2] = (byte) ((b4 >> 4) | (b3 << 2));
            } else if (b2 == PAD) {
                b = base64Alphabet[b];
                bArr2[i2] = (byte) ((b3 << 2) | (b4 >> 4));
                bArr2[i2 + 1] = (byte) (((b4 & 15) << 4) | ((b >> 2) & 15));
            }
            i2 += 3;
            i++;
        }
        return bArr2;
    }

    static byte[] discardWhitespace(byte[] bArr) {
        Object obj = new byte[bArr.length];
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            switch (bArr[i2]) {
                case (byte) 9:
                case (byte) 10:
                case (byte) 13:
                case (byte) 32:
                    break;
                default:
                    int i3 = i + 1;
                    obj[i] = bArr[i2];
                    i = i3;
                    break;
            }
        }
        Object obj2 = new byte[i];
        System.arraycopy(obj, 0, obj2, 0, i);
        return obj2;
    }

    static byte[] discardNonBase64(byte[] bArr) {
        Object obj = new byte[bArr.length];
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            if (isBase64(bArr[i2])) {
                int i3 = i + 1;
                obj[i] = bArr[i2];
                i = i3;
            }
        }
        Object obj2 = new byte[i];
        System.arraycopy(obj, 0, obj2, 0, i);
        return obj2;
    }

    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof byte[]) {
            return encode((byte[]) obj);
        }
        throw new EncoderException("Parameter supplied to Base64 encode is not a byte[]");
    }

    public byte[] encode(byte[] bArr) {
        return encodeBase64(bArr, false);
    }
}
