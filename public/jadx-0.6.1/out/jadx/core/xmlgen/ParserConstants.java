package jadx.core.xmlgen;

import java.util.HashMap;
import java.util.Map;

public class ParserConstants {
    protected static final int ATTR_FEW = ResMakeInternal(8);
    protected static final int ATTR_L10N = ResMakeInternal(3);
    protected static final int ATTR_L10N_NOT_REQUIRED = 0;
    protected static final int ATTR_L10N_SUGGESTED = 1;
    protected static final int ATTR_MANY = ResMakeInternal(9);
    protected static final int ATTR_MAX = ResMakeInternal(2);
    protected static final int ATTR_MIN = ResMakeInternal(1);
    protected static final int ATTR_ONE = ResMakeInternal(TYPE_FRACTION);
    protected static final int ATTR_OTHER = ResMakeInternal(4);
    protected static final int ATTR_TWO = ResMakeInternal(7);
    protected static final int ATTR_TYPE = ResMakeInternal(0);
    protected static final int ATTR_TYPE_ANY = 65535;
    protected static final int ATTR_TYPE_BOOLEAN = 8;
    protected static final int ATTR_TYPE_COLOR = 16;
    protected static final int ATTR_TYPE_DIMENSION = 64;
    protected static final int ATTR_TYPE_ENUM = 65536;
    protected static final int ATTR_TYPE_FLAGS = 131072;
    protected static final int ATTR_TYPE_FLOAT = 32;
    protected static final int ATTR_TYPE_FRACTION = 128;
    protected static final int ATTR_TYPE_INTEGER = 4;
    protected static final int ATTR_TYPE_REFERENCE = 1;
    protected static final int ATTR_TYPE_STRING = 2;
    protected static final int ATTR_ZERO = ResMakeInternal(5);
    protected static final int COMPLEX_MANTISSA_MASK = 16777215;
    protected static final int COMPLEX_MANTISSA_SHIFT = 8;
    protected static final int COMPLEX_RADIX_0p23 = 3;
    protected static final int COMPLEX_RADIX_16p7 = 1;
    protected static final int COMPLEX_RADIX_23p0 = 0;
    protected static final int COMPLEX_RADIX_8p15 = 2;
    protected static final int COMPLEX_RADIX_MASK = 3;
    protected static final int COMPLEX_RADIX_SHIFT = 4;
    protected static final int COMPLEX_UNIT_DIP = 1;
    protected static final int COMPLEX_UNIT_FRACTION = 0;
    protected static final int COMPLEX_UNIT_FRACTION_PARENT = 1;
    protected static final int COMPLEX_UNIT_IN = 4;
    protected static final int COMPLEX_UNIT_MASK = 15;
    protected static final int COMPLEX_UNIT_MM = 5;
    protected static final int COMPLEX_UNIT_PT = 3;
    protected static final int COMPLEX_UNIT_PX = 0;
    protected static final int COMPLEX_UNIT_SHIFT = 0;
    protected static final int COMPLEX_UNIT_SP = 2;
    protected static final int FLAG_COMPLEX = 1;
    protected static final int FLAG_PUBLIC = 2;
    protected static final double MANTISSA_MULT = 0.00390625d;
    protected static final int NO_ENTRY = -1;
    protected static final Map<Integer, String> PLURALS_MAP = new HashMap<Integer, String>() {
        {
            put(Integer.valueOf(ParserConstants.ATTR_OTHER), "other");
            put(Integer.valueOf(ParserConstants.ATTR_ZERO), "zero");
            put(Integer.valueOf(ParserConstants.ATTR_ONE), "one");
            put(Integer.valueOf(ParserConstants.ATTR_TWO), "two");
            put(Integer.valueOf(ParserConstants.ATTR_FEW), "few");
            put(Integer.valueOf(ParserConstants.ATTR_MANY), "many");
        }
    };
    protected static final double[] RADIX_MULTS = new double[]{MANTISSA_MULT, 3.0517578125E-5d, 1.1920928955078125E-7d, 4.6566128730773926E-10d};
    protected static final int RES_NULL_TYPE = 0;
    protected static final int RES_STRING_POOL_TYPE = 1;
    protected static final int RES_TABLE_PACKAGE_TYPE = 512;
    protected static final int RES_TABLE_TYPE = 2;
    protected static final int RES_TABLE_TYPE_SPEC_TYPE = 514;
    protected static final int RES_TABLE_TYPE_TYPE = 513;
    protected static final int RES_XML_CDATA_TYPE = 260;
    protected static final int RES_XML_END_ELEMENT_TYPE = 259;
    protected static final int RES_XML_END_NAMESPACE_TYPE = 257;
    protected static final int RES_XML_FIRST_CHUNK_TYPE = 256;
    protected static final int RES_XML_LAST_CHUNK_TYPE = 383;
    protected static final int RES_XML_RESOURCE_MAP_TYPE = 384;
    protected static final int RES_XML_START_ELEMENT_TYPE = 258;
    protected static final int RES_XML_START_NAMESPACE_TYPE = 256;
    protected static final int RES_XML_TYPE = 3;
    protected static final int SORTED_FLAG = 1;
    protected static final int TYPE_ATTRIBUTE = 2;
    protected static final int TYPE_DIMENSION = 5;
    protected static final int TYPE_FIRST_COLOR_INT = 28;
    protected static final int TYPE_FIRST_INT = 16;
    protected static final int TYPE_FLOAT = 4;
    protected static final int TYPE_FRACTION = 6;
    protected static final int TYPE_INT_BOOLEAN = 18;
    protected static final int TYPE_INT_COLOR_ARGB4 = 30;
    protected static final int TYPE_INT_COLOR_ARGB8 = 28;
    protected static final int TYPE_INT_COLOR_RGB4 = 31;
    protected static final int TYPE_INT_COLOR_RGB8 = 29;
    protected static final int TYPE_INT_DEC = 16;
    protected static final int TYPE_INT_HEX = 17;
    protected static final int TYPE_LAST_COLOR_INT = 31;
    protected static final int TYPE_LAST_INT = 31;
    protected static final int TYPE_NULL = 0;
    protected static final int TYPE_REFERENCE = 1;
    protected static final int TYPE_STRING = 3;
    protected static final int UTF8_FLAG = 256;

    private static int ResMakeInternal(int entry) {
        return 16777216 | (ATTR_TYPE_ANY & entry);
    }

    protected static boolean isResInternalId(int resid) {
        return (-65536 & resid) != 0 && (16711680 & resid) == 0;
    }
}
