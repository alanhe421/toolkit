package com.tencent.theme;

import java.io.IOException;
import java.nio.ByteBuffer;

public class StringBlock {
    private static final int CHUNK_TYPE = 1835009;
    private ByteBuffer mBuffer;
    private int[] m_stringOffsets;
    private String[] m_strings;
    private int[] m_styleOffsets;
    private int[] m_styles;
    private int m_version;

    public static StringBlock read(ByteBuffer byteBuffer) throws IOException {
        ChunkUtil.readCheckType(byteBuffer, CHUNK_TYPE);
        int i = byteBuffer.getInt();
        int i2 = byteBuffer.getInt();
        int i3 = byteBuffer.getInt();
        int i4 = byteBuffer.getInt();
        int i5 = byteBuffer.getInt();
        int i6 = byteBuffer.getInt();
        StringBlock stringBlock = new StringBlock();
        stringBlock.m_stringOffsets = ChunkUtil.readIntArray(byteBuffer, i2);
        if (i3 != 0) {
            stringBlock.m_styleOffsets = ChunkUtil.readIntArray(byteBuffer, i3);
        }
        int position = byteBuffer.position();
        stringBlock.m_version = i4;
        if (i6 == 0) {
            i3 = i;
        } else {
            i3 = i6;
        }
        i3 -= i5;
        if (i3 % 4 != 0) {
            throw new IOException("String data size is not multiple of 4 (" + i3 + ").");
        }
        ByteBuffer wrap = ByteBuffer.wrap(byteBuffer.array(), position, i3);
        wrap.order(byteBuffer.order());
        stringBlock.mBuffer = wrap;
        stringBlock.m_strings = new String[i2];
        ChunkUtil.skip(byteBuffer, i3);
        if (i4 == 0 || i4 == 256) {
            if (i6 != 0) {
                i3 = i - i6;
                if (i3 % 4 != 0) {
                    throw new IOException("Style data size is not multiple of 4 (" + i3 + ").");
                }
                stringBlock.m_styles = ChunkUtil.readIntArray(byteBuffer, i3 / 4);
            }
            return stringBlock;
        }
        throw new IOException("Unknow version xml file: version: " + i4);
    }

    public int getCount() {
        return this.m_stringOffsets != null ? this.m_stringOffsets.length : 0;
    }

    public String getString(int i) {
        if (i < 0 || this.m_stringOffsets == null || i >= this.m_stringOffsets.length) {
            return null;
        }
        String str = this.m_strings[i];
        if (str != null) {
            return str;
        }
        int i2 = this.m_stringOffsets[i];
        this.mBuffer.mark();
        ChunkUtil.skip(this.mBuffer, i2);
        if (this.m_version == 0) {
            char[] cArr = new char[this.mBuffer.getShort()];
            for (i2 = 0; i2 < cArr.length; i2++) {
                cArr[i2] = this.mBuffer.getChar();
            }
            str = new String(cArr);
        } else {
            byte b = this.mBuffer.get();
            this.mBuffer.get();
            str = new String(ChunkUtil.readByteArray(this.mBuffer, b));
        }
        this.m_strings[i] = str;
        this.mBuffer.reset();
        return str;
    }

    public CharSequence get(int i) {
        return getString(i);
    }

    public String getHTML(int i) {
        String string = getString(i);
        if (string == null) {
            return string;
        }
        int[] style = getStyle(i);
        if (style == null) {
            return string;
        }
        StringBuilder stringBuilder = new StringBuilder(string.length() + 32);
        int i2 = 0;
        while (true) {
            int i3 = 0;
            int i4 = -1;
            while (i3 != style.length) {
                if (style[i3 + 1] != -1 && (i4 == -1 || style[i4 + 1] > style[i3 + 1])) {
                    i4 = i3;
                }
                i3 += 3;
            }
            i3 = i4 != -1 ? style[i4 + 1] : string.length();
            int i5 = i2;
            for (i2 = 0; i2 != style.length; i2 += 3) {
                int i6 = style[i2 + 2];
                if (i6 != -1 && i6 < i3) {
                    if (i5 <= i6) {
                        stringBuilder.append(string, i5, i6 + 1);
                        i5 = i6 + 1;
                    }
                    style[i2 + 2] = -1;
                    stringBuilder.append('<');
                    stringBuilder.append('/');
                    stringBuilder.append(getString(style[i2]));
                    stringBuilder.append('>');
                }
            }
            if (i5 < i3) {
                stringBuilder.append(string, i5, i3);
                i2 = i3;
            } else {
                i2 = i5;
            }
            if (i4 == -1) {
                return stringBuilder.toString();
            }
            stringBuilder.append('<');
            stringBuilder.append(getString(style[i4]));
            stringBuilder.append('>');
            style[i4 + 1] = -1;
        }
    }

    public int find(String str) {
        if (str == null) {
            return -1;
        }
        for (int i = 0; i < this.m_strings.length; i++) {
            Object obj = this.m_strings[i];
            if (obj == null) {
                obj = getString(i);
            }
            if (str.equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    private StringBlock() {
    }

    private int[] getStyle(int i) {
        int i2 = 0;
        if (this.m_styleOffsets == null || this.m_styles == null || i >= this.m_styleOffsets.length) {
            return null;
        }
        int i3 = this.m_styleOffsets[i] / 4;
        int i4 = i3;
        int i5 = 0;
        while (i4 < this.m_styles.length && this.m_styles[i4] != -1) {
            i5++;
            i4++;
        }
        if (i5 == 0 || i5 % 3 != 0) {
            return null;
        }
        int[] iArr = new int[i5];
        while (i3 < this.m_styles.length && this.m_styles[i3] != -1) {
            i4 = i2 + 1;
            i5 = i3 + 1;
            iArr[i2] = this.m_styles[i3];
            i2 = i4;
            i3 = i5;
        }
        return iArr;
    }

    private static final int getShort(int[] iArr, int i) {
        int i2 = iArr[i / 4];
        if ((i % 4) / 2 == 0) {
            return i2 & 65535;
        }
        return i2 >>> 16;
    }
}
