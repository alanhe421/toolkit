package com.tencent.midas.comm;

import com.etrump.jni.ETConverter;
import com.qq.taf.jce.JceStruct;
import com.tencent.qalsdk.base.a;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.ByteArrayOutputStream;

public class APBase64 {
    private static final char[] a = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static byte[] b = new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 62, (byte) -1, (byte) -1, (byte) -1, (byte) 63, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, JceStruct.STRUCT_END, JceStruct.ZERO_TAG, JceStruct.SIMPLE_LIST, (byte) 14, (byte) 15, (byte) 16, (byte) 17, (byte) 18, (byte) 19, a.z, (byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 25, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 26, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1};

    public static byte[] decode(String str) {
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(length);
        int i = 0;
        while (i < length) {
            while (true) {
                int i2 = i + 1;
                byte b = b[bytes[i]];
                byte b2;
                byte b3;
                byte b4;
                if (i2 >= length || b != (byte) -1) {
                    if (b != (byte) -1) {
                        while (true) {
                            i = i2 + 1;
                            b2 = b[bytes[i2]];
                            if (i >= length || b2 != (byte) -1) {
                                if (b2 == (byte) -1) {
                                    break;
                                }
                                byteArrayOutputStream.write((b << 2) | ((b2 & 48) >>> 4));
                                while (true) {
                                    i2 = i + 1;
                                    b3 = bytes[i];
                                    if (b3 != (byte) 61) {
                                        return byteArrayOutputStream.toByteArray();
                                    }
                                    b = b[b3];
                                    if (i2 >= length || b != (byte) -1) {
                                        if (b == (byte) -1) {
                                            break;
                                        }
                                        byteArrayOutputStream.write(((b2 & 15) << 4) | ((b & 60) >>> 2));
                                        while (true) {
                                            i = i2 + 1;
                                            b4 = bytes[i2];
                                            if (b4 == (byte) 61) {
                                                return byteArrayOutputStream.toByteArray();
                                            }
                                            b4 = b[b4];
                                            if (i >= length || b4 != (byte) -1) {
                                                if (b4 == (byte) -1) {
                                                    break;
                                                }
                                                byteArrayOutputStream.write(b4 | ((b & 3) << 6));
                                            } else {
                                                i2 = i;
                                            }
                                        }
                                        if (b4 == (byte) -1) {
                                            break;
                                        }
                                        byteArrayOutputStream.write(b4 | ((b & 3) << 6));
                                    } else {
                                        i = i2;
                                    }
                                }
                                if (b == (byte) -1) {
                                    byteArrayOutputStream.write(((b2 & 15) << 4) | ((b & 60) >>> 2));
                                    while (true) {
                                        i = i2 + 1;
                                        b4 = bytes[i2];
                                        if (b4 == (byte) 61) {
                                            b4 = b[b4];
                                            if (i >= length) {
                                                break;
                                            }
                                            break;
                                        }
                                        return byteArrayOutputStream.toByteArray();
                                        i2 = i;
                                    }
                                    if (b4 == (byte) -1) {
                                        break;
                                    }
                                    byteArrayOutputStream.write(b4 | ((b & 3) << 6));
                                } else {
                                    break;
                                }
                            }
                            i2 = i;
                        }
                        if (b2 == (byte) -1) {
                            byteArrayOutputStream.write((b << 2) | ((b2 & 48) >>> 4));
                            while (true) {
                                i2 = i + 1;
                                b3 = bytes[i];
                                if (b3 != (byte) 61) {
                                    b = b[b3];
                                    if (i2 >= length) {
                                        break;
                                    }
                                    break;
                                }
                                return byteArrayOutputStream.toByteArray();
                                i = i2;
                            }
                            if (b == (byte) -1) {
                                break;
                            }
                            byteArrayOutputStream.write(((b2 & 15) << 4) | ((b & 60) >>> 2));
                            while (true) {
                                i = i2 + 1;
                                b4 = bytes[i2];
                                if (b4 == (byte) 61) {
                                    b4 = b[b4];
                                    if (i >= length) {
                                        break;
                                    }
                                    break;
                                }
                                return byteArrayOutputStream.toByteArray();
                                i2 = i;
                            }
                            if (b4 == (byte) -1) {
                                break;
                            }
                            byteArrayOutputStream.write(b4 | ((b & 3) << 6));
                        } else {
                            break;
                        }
                    }
                    break;
                }
                i = i2;
            }
            if (b != (byte) -1) {
                break;
            }
            while (true) {
                i = i2 + 1;
                b2 = b[bytes[i2]];
                if (i >= length) {
                    break;
                }
                break;
                i2 = i;
            }
            if (b2 == (byte) -1) {
                break;
            }
            byteArrayOutputStream.write((b << 2) | ((b2 & 48) >>> 4));
            while (true) {
                i2 = i + 1;
                b3 = bytes[i];
                if (b3 != (byte) 61) {
                    b = b[b3];
                    if (i2 >= length) {
                        break;
                    }
                    break;
                }
                return byteArrayOutputStream.toByteArray();
                i = i2;
            }
            if (b == (byte) -1) {
                byteArrayOutputStream.write(((b2 & 15) << 4) | ((b & 60) >>> 2));
                while (true) {
                    i = i2 + 1;
                    b4 = bytes[i2];
                    if (b4 == (byte) 61) {
                        b4 = b[b4];
                        if (i >= length) {
                            break;
                        }
                        break;
                    }
                    return byteArrayOutputStream.toByteArray();
                    i2 = i;
                }
                if (b4 == (byte) -1) {
                    break;
                }
                byteArrayOutputStream.write(b4 | ((b & 3) << 6));
            } else {
                break;
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static String encode(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = bArr.length;
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            int i3 = bArr[i] & 255;
            if (i2 == length) {
                stringBuffer.append(a[i3 >>> 2]);
                stringBuffer.append(a[(i3 & 3) << 4]);
                stringBuffer.append("==");
                break;
            }
            int i4 = i2 + 1;
            i2 = bArr[i2] & 255;
            if (i4 == length) {
                stringBuffer.append(a[i3 >>> 2]);
                stringBuffer.append(a[((i3 & 3) << 4) | ((i2 & ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK) >>> 4)]);
                stringBuffer.append(a[(i2 & 15) << 2]);
                stringBuffer.append("=");
                break;
            }
            i = i4 + 1;
            i4 = bArr[i4] & 255;
            stringBuffer.append(a[i3 >>> 2]);
            stringBuffer.append(a[((i3 & 3) << 4) | ((i2 & ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK) >>> 4)]);
            stringBuffer.append(a[((i2 & 15) << 2) | ((i4 & Opcodes.AND_LONG_2ADDR) >>> 6)]);
            stringBuffer.append(a[i4 & 63]);
        }
        return stringBuffer.toString();
    }
}
