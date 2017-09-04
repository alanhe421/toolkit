package jadx.core.xmlgen.entry;

import jadx.core.xmlgen.ParserConstants;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValuesParser extends ParserConstants {
    private static final Logger LOG = LoggerFactory.getLogger(ValuesParser.class);
    private final Map<Integer, String> resMap;
    private final String[] strings;

    public ValuesParser(String[] strings, Map<Integer, String> resMap) {
        this.strings = strings;
        this.resMap = resMap;
    }

    public String getValueString(ResourceEntry ri) {
        RawValue simpleValue = ri.getSimpleValue();
        if (simpleValue != null) {
            return decodeValue(simpleValue);
        }
        List<RawNamedValue> namedValues = ri.getNamedValues();
        List<String> strList = new ArrayList(namedValues.size());
        for (RawNamedValue value : namedValues) {
            String nameStr = decodeNameRef(value.getNameRef());
            String valueStr = decodeValue(value.getRawValue());
            if (nameStr == null) {
                strList.add(valueStr);
            } else {
                strList.add(nameStr + "=" + valueStr);
            }
        }
        return strList.toString();
    }

    public String decodeValue(RawValue value) {
        return decodeValue(value.getDataType(), value.getData());
    }

    public String decodeValue(int dataType, int data) {
        String ri;
        switch (dataType) {
            case 0:
                return null;
            case 1:
                ri = (String) this.resMap.get(Integer.valueOf(data));
                if (ri == null) {
                    return "?unknown_ref: " + Integer.toHexString(data);
                }
                return "@" + ri;
            case 2:
                ri = (String) this.resMap.get(Integer.valueOf(data));
                if (ri == null) {
                    return "?unknown_attr_ref: " + Integer.toHexString(data);
                }
                return "?" + ri;
            case 3:
                return this.strings[data];
            case 4:
                return Float.toString(Float.intBitsToFloat(data));
            case 5:
                return decodeComplex(data, false);
            case 6:
                return decodeComplex(data, true);
            case 16:
                return Integer.toString(data);
            case 17:
                return Integer.toHexString(data);
            case 18:
                return data == 0 ? "false" : "true";
            case 28:
                return String.format("#%08x", new Object[]{Integer.valueOf(data)});
            case 29:
                return String.format("#%06x", new Object[]{Integer.valueOf(16777215 & data)});
            case 30:
                return String.format("#%04x", new Object[]{Integer.valueOf(65535 & data)});
            case 31:
                return String.format("#%03x", new Object[]{Integer.valueOf(data & 4095)});
            default:
                LOG.warn("Unknown data type: 0x{} {}", Integer.toHexString(dataType), Integer.valueOf(data));
                return "  ?0x" + Integer.toHexString(dataType) + " " + data;
        }
    }

    private String decodeNameRef(int nameRef) {
        int ref = nameRef;
        if (ParserConstants.isResInternalId(nameRef)) {
            ref = nameRef & 65535;
            if (ref == 0) {
                return null;
            }
        }
        String ri = (String) this.resMap.get(Integer.valueOf(ref));
        if (ri != null) {
            return ri.replace('/', '.');
        }
        return "?0x" + Integer.toHexString(nameRef);
    }

    private String decodeComplex(int data, boolean isFraction) {
        String unit;
        double value = ((double) (data & -256)) * RADIX_MULTS[(data >> 4) & 3];
        int unitType = data & 15;
        if (!isFraction) {
            switch (unitType) {
                case 0:
                    unit = "px";
                    break;
                case 1:
                    unit = "dp";
                    break;
                case 2:
                    unit = "sp";
                    break;
                case 3:
                    unit = "pt";
                    break;
                case 4:
                    unit = "in";
                    break;
                case 5:
                    unit = "mm";
                    break;
                default:
                    unit = "?d" + Integer.toHexString(unitType);
                    break;
            }
        }
        value *= 100.0d;
        switch (unitType) {
            case 0:
                unit = "%";
                break;
            case 1:
                unit = "%p";
                break;
            default:
                unit = "?f" + Integer.toHexString(unitType);
                break;
        }
        return doubleToString(value) + unit;
    }

    private static String doubleToString(double value) {
        if (value == Math.ceil(value)) {
            return Integer.toString((int) value);
        }
        NumberFormat f = NumberFormat.getInstance();
        f.setMaximumFractionDigits(4);
        f.setMinimumIntegerDigits(1);
        return f.format(value);
    }
}
