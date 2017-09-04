package com.tencent.tinker.android.dx.instruction;

import com.tencent.tinker.android.dex.DexException;
import com.tencent.tinker.android.dx.util.Hex;

public final class InstructionCodec {
    public static final int INDEX_TYPE_FIELD_REF = 5;
    public static final int INDEX_TYPE_METHOD_REF = 4;
    public static final int INDEX_TYPE_NONE = 1;
    public static final int INDEX_TYPE_STRING_REF = 3;
    public static final int INDEX_TYPE_TYPE_REF = 2;
    public static final int INDEX_TYPE_UNKNOWN = 0;
    public static final int INSN_FORMAT_00X = 1;
    public static final int INSN_FORMAT_10T = 2;
    public static final int INSN_FORMAT_10X = 3;
    public static final int INSN_FORMAT_11N = 4;
    public static final int INSN_FORMAT_11X = 5;
    public static final int INSN_FORMAT_12X = 6;
    public static final int INSN_FORMAT_20T = 7;
    public static final int INSN_FORMAT_21C = 8;
    public static final int INSN_FORMAT_21H = 9;
    public static final int INSN_FORMAT_21S = 10;
    public static final int INSN_FORMAT_21T = 11;
    public static final int INSN_FORMAT_22B = 12;
    public static final int INSN_FORMAT_22C = 13;
    public static final int INSN_FORMAT_22S = 14;
    public static final int INSN_FORMAT_22T = 15;
    public static final int INSN_FORMAT_22X = 16;
    public static final int INSN_FORMAT_23X = 17;
    public static final int INSN_FORMAT_30T = 18;
    public static final int INSN_FORMAT_31C = 19;
    public static final int INSN_FORMAT_31I = 20;
    public static final int INSN_FORMAT_31T = 21;
    public static final int INSN_FORMAT_32X = 22;
    public static final int INSN_FORMAT_35C = 23;
    public static final int INSN_FORMAT_3RC = 24;
    public static final int INSN_FORMAT_51L = 25;
    public static final int INSN_FORMAT_FILL_ARRAY_DATA_PAYLOAD = 26;
    public static final int INSN_FORMAT_PACKED_SWITCH_PAYLOAD = 27;
    public static final int INSN_FORMAT_SPARSE_SWITCH_PAYLOAD = 28;
    public static final int INSN_FORMAT_UNKNOWN = 0;

    private InstructionCodec() {
        throw new UnsupportedOperationException();
    }

    public static short codeUnit(int i, int i2) {
        if ((i & -256) != 0) {
            throw new IllegalArgumentException("bogus lowByte");
        } else if ((i2 & -256) == 0) {
            return (short) ((i2 << 8) | i);
        } else {
            throw new IllegalArgumentException("bogus highByte");
        }
    }

    public static short codeUnit(int i, int i2, int i3, int i4) {
        if ((i & -16) != 0) {
            throw new IllegalArgumentException("bogus nibble0");
        } else if ((i2 & -16) != 0) {
            throw new IllegalArgumentException("bogus nibble1");
        } else if ((i3 & -16) != 0) {
            throw new IllegalArgumentException("bogus nibble2");
        } else if ((i4 & -16) == 0) {
            return (short) ((((i2 << 4) | i) | (i3 << 8)) | (i4 << 12));
        } else {
            throw new IllegalArgumentException("bogus nibble3");
        }
    }

    public static int makeByte(int i, int i2) {
        if ((i & -16) != 0) {
            throw new IllegalArgumentException("bogus lowNibble");
        } else if ((i2 & -16) == 0) {
            return (i2 << 4) | i;
        } else {
            throw new IllegalArgumentException("bogus highNibble");
        }
    }

    public static short asUnsignedUnit(int i) {
        if ((-65536 & i) == 0) {
            return (short) i;
        }
        throw new IllegalArgumentException("bogus unsigned code unit");
    }

    public static short unit0(int i) {
        return (short) i;
    }

    public static short unit1(int i) {
        return (short) (i >> 16);
    }

    public static short unit0(long j) {
        return (short) ((int) j);
    }

    public static short unit1(long j) {
        return (short) ((int) (j >> 16));
    }

    public static short unit2(long j) {
        return (short) ((int) (j >> 32));
    }

    public static short unit3(long j) {
        return (short) ((int) (j >> 48));
    }

    public static int byte0(int i) {
        return i & 255;
    }

    public static int byte1(int i) {
        return (i >> 8) & 255;
    }

    public static int nibble0(int i) {
        return i & 15;
    }

    public static int nibble1(int i) {
        return (i >> 4) & 15;
    }

    public static int nibble2(int i) {
        return (i >> 8) & 15;
    }

    public static int nibble3(int i) {
        return (i >> 12) & 15;
    }

    public static int getTargetByte(int i, int i2) {
        byte target = getTarget(i, i2);
        if (target == ((byte) target)) {
            return target & 255;
        }
        throw new DexException("Target out of range: " + Hex.s4(target) + ", perhaps you need to enable force jumbo mode.");
    }

    public static short getTargetUnit(int i, int i2) {
        short target = getTarget(i, i2);
        if (target == ((short) target)) {
            return (short) target;
        }
        throw new DexException("Target out of range: " + Hex.s4(target) + ", perhaps you need to enable force jumbo mode.");
    }

    public static int getTarget(int i, int i2) {
        return i - i2;
    }

    public static int getLiteralByte(long j) {
        if (j == ((long) ((byte) ((int) j)))) {
            return ((int) j) & 255;
        }
        throw new DexException("Literal out of range: " + Hex.u8(j));
    }

    public static short getLiteralUnit(long j) {
        if (j == ((long) ((short) ((int) j)))) {
            return (short) ((int) j);
        }
        throw new DexException("Literal out of range: " + Hex.u8(j));
    }

    public static int getLiteralInt(long j) {
        if (j == ((long) ((int) j))) {
            return (int) j;
        }
        throw new DexException("Literal out of range: " + Hex.u8(j));
    }

    public static int getLiteralNibble(long j) {
        if (j >= -8 && j <= 7) {
            return ((int) j) & 15;
        }
        throw new DexException("Literal out of range: " + Hex.u8(j));
    }

    public static short getAUnit(int i) {
        if ((-65536 & i) == 0) {
            return (short) i;
        }
        throw new DexException("Register A out of range: " + Hex.u8((long) i));
    }

    public static short getBUnit(int i) {
        if ((-65536 & i) == 0) {
            return (short) i;
        }
        throw new DexException("Register B out of range: " + Hex.u8((long) i));
    }

    public static int getInstructionIndexType(int i) {
        switch (i) {
            case -1:
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 29:
            case 30:
            case 33:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case Opcodes.AGET /*68*/:
            case 69:
            case Opcodes.AGET_OBJECT /*70*/:
            case Opcodes.AGET_BOOLEAN /*71*/:
            case Opcodes.AGET_BYTE /*72*/:
            case Opcodes.AGET_CHAR /*73*/:
            case Opcodes.AGET_SHORT /*74*/:
            case Opcodes.APUT /*75*/:
            case Opcodes.APUT_WIDE /*76*/:
            case Opcodes.APUT_OBJECT /*77*/:
            case Opcodes.APUT_BOOLEAN /*78*/:
            case Opcodes.APUT_BYTE /*79*/:
            case Opcodes.APUT_CHAR /*80*/:
            case Opcodes.APUT_SHORT /*81*/:
            case Opcodes.NEG_INT /*123*/:
            case Opcodes.NOT_INT /*124*/:
            case Opcodes.NEG_LONG /*125*/:
            case Opcodes.NOT_LONG /*126*/:
            case Opcodes.NEG_FLOAT /*127*/:
            case 128:
            case Opcodes.INT_TO_LONG /*129*/:
            case Opcodes.INT_TO_FLOAT /*130*/:
            case Opcodes.INT_TO_DOUBLE /*131*/:
            case Opcodes.LONG_TO_INT /*132*/:
            case Opcodes.LONG_TO_FLOAT /*133*/:
            case Opcodes.LONG_TO_DOUBLE /*134*/:
            case Opcodes.FLOAT_TO_INT /*135*/:
            case Opcodes.FLOAT_TO_LONG /*136*/:
            case Opcodes.FLOAT_TO_DOUBLE /*137*/:
            case Opcodes.DOUBLE_TO_INT /*138*/:
            case Opcodes.DOUBLE_TO_LONG /*139*/:
            case Opcodes.DOUBLE_TO_FLOAT /*140*/:
            case Opcodes.INT_TO_BYTE /*141*/:
            case Opcodes.INT_TO_CHAR /*142*/:
            case Opcodes.INT_TO_SHORT /*143*/:
            case Opcodes.ADD_INT /*144*/:
            case Opcodes.SUB_INT /*145*/:
            case Opcodes.MUL_INT /*146*/:
            case Opcodes.DIV_INT /*147*/:
            case Opcodes.REM_INT /*148*/:
            case Opcodes.AND_INT /*149*/:
            case Opcodes.OR_INT /*150*/:
            case Opcodes.XOR_INT /*151*/:
            case Opcodes.SHL_INT /*152*/:
            case Opcodes.SHR_INT /*153*/:
            case Opcodes.USHR_INT /*154*/:
            case Opcodes.ADD_LONG /*155*/:
            case Opcodes.SUB_LONG /*156*/:
            case Opcodes.MUL_LONG /*157*/:
            case Opcodes.DIV_LONG /*158*/:
            case Opcodes.REM_LONG /*159*/:
            case 160:
            case Opcodes.OR_LONG /*161*/:
            case Opcodes.XOR_LONG /*162*/:
            case Opcodes.SHL_LONG /*163*/:
            case Opcodes.SHR_LONG /*164*/:
            case Opcodes.USHR_LONG /*165*/:
            case Opcodes.ADD_FLOAT /*166*/:
            case Opcodes.SUB_FLOAT /*167*/:
            case Opcodes.MUL_FLOAT /*168*/:
            case Opcodes.DIV_FLOAT /*169*/:
            case Opcodes.REM_FLOAT /*170*/:
            case Opcodes.ADD_DOUBLE /*171*/:
            case Opcodes.SUB_DOUBLE /*172*/:
            case Opcodes.MUL_DOUBLE /*173*/:
            case Opcodes.DIV_DOUBLE /*174*/:
            case Opcodes.REM_DOUBLE /*175*/:
            case Opcodes.ADD_INT_2ADDR /*176*/:
            case Opcodes.SUB_INT_2ADDR /*177*/:
            case Opcodes.MUL_INT_2ADDR /*178*/:
            case Opcodes.DIV_INT_2ADDR /*179*/:
            case 180:
            case Opcodes.AND_INT_2ADDR /*181*/:
            case Opcodes.OR_INT_2ADDR /*182*/:
            case Opcodes.XOR_INT_2ADDR /*183*/:
            case Opcodes.SHL_INT_2ADDR /*184*/:
            case Opcodes.SHR_INT_2ADDR /*185*/:
            case Opcodes.USHR_INT_2ADDR /*186*/:
            case Opcodes.ADD_LONG_2ADDR /*187*/:
            case Opcodes.SUB_LONG_2ADDR /*188*/:
            case Opcodes.MUL_LONG_2ADDR /*189*/:
            case Opcodes.DIV_LONG_2ADDR /*190*/:
            case Opcodes.REM_LONG_2ADDR /*191*/:
            case Opcodes.AND_LONG_2ADDR /*192*/:
            case Opcodes.OR_LONG_2ADDR /*193*/:
            case Opcodes.XOR_LONG_2ADDR /*194*/:
            case Opcodes.SHL_LONG_2ADDR /*195*/:
            case Opcodes.SHR_LONG_2ADDR /*196*/:
            case Opcodes.USHR_LONG_2ADDR /*197*/:
            case Opcodes.ADD_FLOAT_2ADDR /*198*/:
            case Opcodes.SUB_FLOAT_2ADDR /*199*/:
            case 200:
            case 201:
            case 202:
            case 203:
            case 204:
            case 205:
            case 206:
            case 207:
            case 208:
            case 209:
            case 210:
            case 211:
            case 212:
            case 213:
            case 214:
            case 215:
            case 216:
            case 217:
            case 218:
            case 219:
            case 220:
            case 221:
            case Opcodes.OR_INT_LIT8 /*222*/:
            case Opcodes.XOR_INT_LIT8 /*223*/:
            case Opcodes.SHL_INT_LIT8 /*224*/:
            case Opcodes.SHR_INT_LIT8 /*225*/:
            case Opcodes.USHR_INT_LIT8 /*226*/:
            case 256:
            case 512:
            case Opcodes.FILL_ARRAY_DATA_PAYLOAD /*768*/:
                return 1;
            case 26:
            case 27:
                return 3;
            case 28:
            case 31:
            case 32:
            case 34:
            case 35:
            case 36:
            case 37:
                return 2;
            case Opcodes.IGET /*82*/:
            case Opcodes.IGET_WIDE /*83*/:
            case Opcodes.IGET_OBJECT /*84*/:
            case Opcodes.IGET_BOOLEAN /*85*/:
            case Opcodes.IGET_BYTE /*86*/:
            case Opcodes.IGET_CHAR /*87*/:
            case Opcodes.IGET_SHORT /*88*/:
            case Opcodes.IPUT /*89*/:
            case Opcodes.IPUT_WIDE /*90*/:
            case Opcodes.IPUT_OBJECT /*91*/:
            case Opcodes.IPUT_BOOLEAN /*92*/:
            case Opcodes.IPUT_BYTE /*93*/:
            case Opcodes.IPUT_CHAR /*94*/:
            case Opcodes.IPUT_SHORT /*95*/:
            case Opcodes.SGET /*96*/:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
                return 5;
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 116:
            case Opcodes.INVOKE_SUPER_RANGE /*117*/:
            case 118:
            case Opcodes.INVOKE_STATIC_RANGE /*119*/:
            case 120:
                return 4;
            default:
                return 0;
        }
    }

    public static int getInstructionFormat(int i) {
        switch (i) {
            case -1:
                return 1;
            case 0:
            case 14:
                return 3;
            case 1:
            case 4:
            case 7:
            case 33:
            case Opcodes.NEG_INT /*123*/:
            case Opcodes.NOT_INT /*124*/:
            case Opcodes.NEG_LONG /*125*/:
            case Opcodes.NOT_LONG /*126*/:
            case Opcodes.NEG_FLOAT /*127*/:
            case 128:
            case Opcodes.INT_TO_LONG /*129*/:
            case Opcodes.INT_TO_FLOAT /*130*/:
            case Opcodes.INT_TO_DOUBLE /*131*/:
            case Opcodes.LONG_TO_INT /*132*/:
            case Opcodes.LONG_TO_FLOAT /*133*/:
            case Opcodes.LONG_TO_DOUBLE /*134*/:
            case Opcodes.FLOAT_TO_INT /*135*/:
            case Opcodes.FLOAT_TO_LONG /*136*/:
            case Opcodes.FLOAT_TO_DOUBLE /*137*/:
            case Opcodes.DOUBLE_TO_INT /*138*/:
            case Opcodes.DOUBLE_TO_LONG /*139*/:
            case Opcodes.DOUBLE_TO_FLOAT /*140*/:
            case Opcodes.INT_TO_BYTE /*141*/:
            case Opcodes.INT_TO_CHAR /*142*/:
            case Opcodes.INT_TO_SHORT /*143*/:
            case Opcodes.ADD_INT_2ADDR /*176*/:
            case Opcodes.SUB_INT_2ADDR /*177*/:
            case Opcodes.MUL_INT_2ADDR /*178*/:
            case Opcodes.DIV_INT_2ADDR /*179*/:
            case 180:
            case Opcodes.AND_INT_2ADDR /*181*/:
            case Opcodes.OR_INT_2ADDR /*182*/:
            case Opcodes.XOR_INT_2ADDR /*183*/:
            case Opcodes.SHL_INT_2ADDR /*184*/:
            case Opcodes.SHR_INT_2ADDR /*185*/:
            case Opcodes.USHR_INT_2ADDR /*186*/:
            case Opcodes.ADD_LONG_2ADDR /*187*/:
            case Opcodes.SUB_LONG_2ADDR /*188*/:
            case Opcodes.MUL_LONG_2ADDR /*189*/:
            case Opcodes.DIV_LONG_2ADDR /*190*/:
            case Opcodes.REM_LONG_2ADDR /*191*/:
            case Opcodes.AND_LONG_2ADDR /*192*/:
            case Opcodes.OR_LONG_2ADDR /*193*/:
            case Opcodes.XOR_LONG_2ADDR /*194*/:
            case Opcodes.SHL_LONG_2ADDR /*195*/:
            case Opcodes.SHR_LONG_2ADDR /*196*/:
            case Opcodes.USHR_LONG_2ADDR /*197*/:
            case Opcodes.ADD_FLOAT_2ADDR /*198*/:
            case Opcodes.SUB_FLOAT_2ADDR /*199*/:
            case 200:
            case 201:
            case 202:
            case 203:
            case 204:
            case 205:
            case 206:
            case 207:
                return 6;
            case 2:
            case 5:
            case 8:
                return 16;
            case 3:
            case 6:
            case 9:
                return 22;
            case 10:
            case 11:
            case 12:
            case 13:
            case 15:
            case 16:
            case 17:
            case 29:
            case 30:
            case 39:
                return 5;
            case 18:
                return 4;
            case 19:
            case 22:
                return 10;
            case 20:
            case 23:
                return 20;
            case 21:
            case 25:
                return 9;
            case 24:
                return 25;
            case 26:
            case 28:
            case 31:
            case 34:
            case Opcodes.SGET /*96*/:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
                return 8;
            case 27:
                return 19;
            case 32:
            case 35:
            case Opcodes.IGET /*82*/:
            case Opcodes.IGET_WIDE /*83*/:
            case Opcodes.IGET_OBJECT /*84*/:
            case Opcodes.IGET_BOOLEAN /*85*/:
            case Opcodes.IGET_BYTE /*86*/:
            case Opcodes.IGET_CHAR /*87*/:
            case Opcodes.IGET_SHORT /*88*/:
            case Opcodes.IPUT /*89*/:
            case Opcodes.IPUT_WIDE /*90*/:
            case Opcodes.IPUT_OBJECT /*91*/:
            case Opcodes.IPUT_BOOLEAN /*92*/:
            case Opcodes.IPUT_BYTE /*93*/:
            case Opcodes.IPUT_CHAR /*94*/:
            case Opcodes.IPUT_SHORT /*95*/:
                return 13;
            case 36:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                return 23;
            case 37:
            case 116:
            case Opcodes.INVOKE_SUPER_RANGE /*117*/:
            case 118:
            case Opcodes.INVOKE_STATIC_RANGE /*119*/:
            case 120:
                return 24;
            case 38:
            case 43:
            case 44:
                return 21;
            case 40:
                return 2;
            case 41:
                return 7;
            case 42:
                return 18;
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case Opcodes.AGET /*68*/:
            case 69:
            case Opcodes.AGET_OBJECT /*70*/:
            case Opcodes.AGET_BOOLEAN /*71*/:
            case Opcodes.AGET_BYTE /*72*/:
            case Opcodes.AGET_CHAR /*73*/:
            case Opcodes.AGET_SHORT /*74*/:
            case Opcodes.APUT /*75*/:
            case Opcodes.APUT_WIDE /*76*/:
            case Opcodes.APUT_OBJECT /*77*/:
            case Opcodes.APUT_BOOLEAN /*78*/:
            case Opcodes.APUT_BYTE /*79*/:
            case Opcodes.APUT_CHAR /*80*/:
            case Opcodes.APUT_SHORT /*81*/:
            case Opcodes.ADD_INT /*144*/:
            case Opcodes.SUB_INT /*145*/:
            case Opcodes.MUL_INT /*146*/:
            case Opcodes.DIV_INT /*147*/:
            case Opcodes.REM_INT /*148*/:
            case Opcodes.AND_INT /*149*/:
            case Opcodes.OR_INT /*150*/:
            case Opcodes.XOR_INT /*151*/:
            case Opcodes.SHL_INT /*152*/:
            case Opcodes.SHR_INT /*153*/:
            case Opcodes.USHR_INT /*154*/:
            case Opcodes.ADD_LONG /*155*/:
            case Opcodes.SUB_LONG /*156*/:
            case Opcodes.MUL_LONG /*157*/:
            case Opcodes.DIV_LONG /*158*/:
            case Opcodes.REM_LONG /*159*/:
            case 160:
            case Opcodes.OR_LONG /*161*/:
            case Opcodes.XOR_LONG /*162*/:
            case Opcodes.SHL_LONG /*163*/:
            case Opcodes.SHR_LONG /*164*/:
            case Opcodes.USHR_LONG /*165*/:
            case Opcodes.ADD_FLOAT /*166*/:
            case Opcodes.SUB_FLOAT /*167*/:
            case Opcodes.MUL_FLOAT /*168*/:
            case Opcodes.DIV_FLOAT /*169*/:
            case Opcodes.REM_FLOAT /*170*/:
            case Opcodes.ADD_DOUBLE /*171*/:
            case Opcodes.SUB_DOUBLE /*172*/:
            case Opcodes.MUL_DOUBLE /*173*/:
            case Opcodes.DIV_DOUBLE /*174*/:
            case Opcodes.REM_DOUBLE /*175*/:
                return 17;
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
                return 15;
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
                return 11;
            case 208:
            case 209:
            case 210:
            case 211:
            case 212:
            case 213:
            case 214:
            case 215:
                return 14;
            case 216:
            case 217:
            case 218:
            case 219:
            case 220:
            case 221:
            case Opcodes.OR_INT_LIT8 /*222*/:
            case Opcodes.XOR_INT_LIT8 /*223*/:
            case Opcodes.SHL_INT_LIT8 /*224*/:
            case Opcodes.SHR_INT_LIT8 /*225*/:
            case Opcodes.USHR_INT_LIT8 /*226*/:
                return 12;
            case 256:
                return 27;
            case 512:
                return 28;
            case Opcodes.FILL_ARRAY_DATA_PAYLOAD /*768*/:
                return 26;
            default:
                return 0;
        }
    }
}
