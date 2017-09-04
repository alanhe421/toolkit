package jadx.core.dex.nodes.parser;

import com.android.dex.Dex.Section;
import com.android.dex.Leb128;
import jadx.core.dex.info.FieldInfo;
import jadx.core.dex.info.MethodInfo;
import jadx.core.dex.nodes.DexNode;
import jadx.core.utils.exceptions.DecodeException;
import java.util.ArrayList;

public class EncValueParser {
    private static final int ENCODED_ANNOTATION = 29;
    private static final int ENCODED_ARRAY = 28;
    private static final int ENCODED_BOOLEAN = 31;
    private static final int ENCODED_BYTE = 0;
    private static final int ENCODED_CHAR = 3;
    private static final int ENCODED_DOUBLE = 17;
    private static final int ENCODED_ENUM = 27;
    private static final int ENCODED_FIELD = 25;
    private static final int ENCODED_FLOAT = 16;
    private static final int ENCODED_INT = 4;
    private static final int ENCODED_LONG = 6;
    private static final int ENCODED_METHOD = 26;
    private static final int ENCODED_NULL = 30;
    private static final int ENCODED_SHORT = 2;
    private static final int ENCODED_STRING = 23;
    private static final int ENCODED_TYPE = 24;
    private final DexNode dex;
    protected final Section in;

    public EncValueParser(DexNode dex, Section in) {
        this.in = in;
        this.dex = dex;
    }

    public Object parseValue() throws DecodeException {
        boolean z = true;
        int argAndType = readByte();
        int type = argAndType & ENCODED_BOOLEAN;
        int arg = (argAndType & 224) >> 5;
        int size = arg + 1;
        switch (type) {
            case ENCODED_BYTE /*0*/:
                return Byte.valueOf(this.in.readByte());
            case ENCODED_SHORT /*2*/:
                return Short.valueOf((short) ((int) parseNumber(size, true)));
            case ENCODED_CHAR /*3*/:
                return Character.valueOf((char) parseUnsignedInt(size));
            case ENCODED_INT /*4*/:
                return Integer.valueOf((int) parseNumber(size, true));
            case ENCODED_LONG /*6*/:
                return Long.valueOf(parseNumber(size, true));
            case ENCODED_FLOAT /*16*/:
                return Float.valueOf(Float.intBitsToFloat((int) parseNumber(size, false, ENCODED_INT)));
            case ENCODED_DOUBLE /*17*/:
                return Double.valueOf(Double.longBitsToDouble(parseNumber(size, false, 8)));
            case ENCODED_STRING /*23*/:
                return this.dex.getString(parseUnsignedInt(size));
            case ENCODED_TYPE /*24*/:
                return this.dex.getType(parseUnsignedInt(size));
            case ENCODED_FIELD /*25*/:
            case ENCODED_ENUM /*27*/:
                return FieldInfo.fromDex(this.dex, parseUnsignedInt(size));
            case ENCODED_METHOD /*26*/:
                return MethodInfo.fromDex(this.dex, parseUnsignedInt(size));
            case ENCODED_ARRAY /*28*/:
                int count = Leb128.readUnsignedLeb128(this.in);
                Object values = new ArrayList(count);
                for (int i = ENCODED_BYTE; i < count; i++) {
                    values.add(parseValue());
                }
                return values;
            case ENCODED_ANNOTATION /*29*/:
                return AnnotationsParser.readAnnotation(this.dex, this.in, false);
            case ENCODED_NULL /*30*/:
                return null;
            case ENCODED_BOOLEAN /*31*/:
                if (arg != 1) {
                    z = false;
                }
                return Boolean.valueOf(z);
            default:
                throw new DecodeException("Unknown encoded value type: 0x" + Integer.toHexString(type));
        }
    }

    private int parseUnsignedInt(int byteCount) {
        return (int) parseNumber(byteCount, false, ENCODED_BYTE);
    }

    private long parseNumber(int byteCount, boolean isSignExtended) {
        return parseNumber(byteCount, isSignExtended, ENCODED_BYTE);
    }

    private long parseNumber(int byteCount, boolean isSignExtended, int fillOnRight) {
        int i;
        long result = 0;
        long last = 0;
        for (i = ENCODED_BYTE; i < byteCount; i++) {
            last = (long) readByte();
            result |= last << (i * 8);
        }
        if (fillOnRight != 0) {
            for (i = byteCount; i < fillOnRight; i++) {
                result <<= 8;
            }
        } else if (isSignExtended && (128 & last) != 0) {
            for (i = byteCount; i < 8; i++) {
                result |= 255 << (i * 8);
            }
        }
        return result;
    }

    private int readByte() {
        return this.in.readByte() & 255;
    }
}
