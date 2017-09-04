package org.apache.commons.codec.binary;

import com.etrump.jni.ETConverter;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;

public class Hex implements BinaryDecoder, BinaryEncoder {
    private static final char[] DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static byte[] decodeHex(char[] cArr) throws DecoderException {
        int i = 0;
        int length = cArr.length;
        if ((length & 1) != 0) {
            throw new DecoderException("Odd number of characters.");
        }
        byte[] bArr = new byte[(length >> 1)];
        int i2 = 0;
        while (i < length) {
            i++;
            i++;
            bArr[i2] = (byte) (((toDigit(cArr[i], i) << 4) | toDigit(cArr[i], i)) & 255);
            i2++;
        }
        return bArr;
    }

    protected static int toDigit(char c, int i) throws DecoderException {
        int digit = Character.digit(c, 16);
        if (digit != -1) {
            return digit;
        }
        throw new DecoderException("Illegal hexadecimal charcter " + c + " at index " + i);
    }

    public static char[] encodeHex(byte[] bArr) {
        int i = 0;
        int length = bArr.length;
        char[] cArr = new char[(length << 1)];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr[i] = DIGITS[(bArr[i2] & ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK) >>> 4];
            i = i3 + 1;
            cArr[i3] = DIGITS[bArr[i2] & 15];
        }
        return cArr;
    }

    public byte[] decode(byte[] bArr) throws DecoderException {
        return decodeHex(new String(bArr).toCharArray());
    }

    public Object decode(Object obj) throws DecoderException {
        try {
            if (obj instanceof String) {
                obj = ((String) obj).toCharArray();
            } else {
                char[] cArr = (char[]) obj;
            }
            return decodeHex(obj);
        } catch (ClassCastException e) {
            throw new DecoderException(e.getMessage());
        }
    }

    public byte[] encode(byte[] bArr) {
        return new String(encodeHex(bArr)).getBytes();
    }

    public Object encode(Object obj) throws EncoderException {
        try {
            if (obj instanceof String) {
                obj = ((String) obj).getBytes();
            } else {
                byte[] bArr = (byte[]) obj;
            }
            return encodeHex(obj);
        } catch (ClassCastException e) {
            throw new EncoderException(e.getMessage());
        }
    }
}
