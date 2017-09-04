package com.tencent.tinker.android.dx.instruction;

import com.tencent.tinker.android.dex.DexException;
import com.tencent.tinker.android.dx.util.Hex;
import com.tencent.tinker.android.utils.SparseIntArray;

public final class InstructionPromoter extends InstructionVisitor {
    private final SparseIntArray addressMap = new SparseIntArray();
    private int currentPromotedAddress = 0;

    public InstructionPromoter() {
        super(null);
    }

    private void mapAddressIfNeeded(int i) {
        if (i != this.currentPromotedAddress) {
            this.addressMap.append(i, this.currentPromotedAddress);
        }
    }

    public int getPromotedAddress(int i) {
        int indexOfKey = this.addressMap.indexOfKey(i);
        return indexOfKey < 0 ? i : this.addressMap.valueAt(indexOfKey);
    }

    public int getPromotedAddressCount() {
        return this.addressMap.size();
    }

    public void visitZeroRegisterInsn(int i, int i2, int i3, int i4, int i5, long j) {
        mapAddressIfNeeded(i);
        switch (i2) {
            case -1:
            case 0:
            case 14:
                this.currentPromotedAddress++;
                return;
            case 36:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.currentPromotedAddress += 3;
                return;
            case 40:
                byte target = InstructionCodec.getTarget(i5, this.currentPromotedAddress);
                if (target == ((byte) target)) {
                    this.currentPromotedAddress++;
                    return;
                } else if (target != ((short) target)) {
                    this.currentPromotedAddress += 3;
                    return;
                } else {
                    this.currentPromotedAddress += 2;
                    return;
                }
            case 41:
                short target2 = InstructionCodec.getTarget(i5, this.currentPromotedAddress);
                if (target2 != ((short) target2)) {
                    this.currentPromotedAddress += 3;
                    return;
                } else {
                    this.currentPromotedAddress += 2;
                    return;
                }
            case 42:
                this.currentPromotedAddress += 3;
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitOneRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6) {
        mapAddressIfNeeded(i);
        switch (i2) {
            case 10:
            case 11:
            case 12:
            case 13:
            case 15:
            case 16:
            case 17:
            case 18:
            case 29:
            case 30:
            case 39:
                this.currentPromotedAddress++;
                return;
            case 19:
            case 21:
            case 22:
            case 25:
            case 28:
            case 31:
            case 34:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
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
                this.currentPromotedAddress += 2;
                return;
            case 20:
            case 23:
            case 36:
            case 38:
            case 43:
            case 44:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.currentPromotedAddress += 3;
                return;
            case 24:
                this.currentPromotedAddress += 5;
                return;
            case 26:
                if (i3 > 65535) {
                    this.currentPromotedAddress += 3;
                    return;
                } else {
                    this.currentPromotedAddress += 2;
                    return;
                }
            case 27:
                this.currentPromotedAddress += 3;
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitTwoRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7) {
        mapAddressIfNeeded(i);
        switch (i2) {
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
                this.currentPromotedAddress++;
                return;
            case 2:
            case 5:
            case 8:
                this.currentPromotedAddress += 2;
                return;
            case 3:
            case 6:
            case 9:
            case 36:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.currentPromotedAddress += 3;
                return;
            case 32:
            case 35:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
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
                this.currentPromotedAddress += 2;
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitThreeRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7, int i8) {
        mapAddressIfNeeded(i);
        switch (i2) {
            case 36:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.currentPromotedAddress += 3;
                return;
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
                this.currentPromotedAddress += 2;
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitFourRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7, int i8, int i9) {
        mapAddressIfNeeded(i);
        switch (i2) {
            case 36:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.currentPromotedAddress += 3;
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitFiveRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7, int i8, int i9, int i10) {
        mapAddressIfNeeded(i);
        switch (i2) {
            case 36:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.currentPromotedAddress += 3;
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitRegisterRangeInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7) {
        mapAddressIfNeeded(i);
        switch (i2) {
            case 37:
            case 116:
            case Opcodes.INVOKE_SUPER_RANGE /*117*/:
            case 118:
            case Opcodes.INVOKE_STATIC_RANGE /*119*/:
            case 120:
                this.currentPromotedAddress += 3;
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitSparseSwitchPayloadInsn(int i, int i2, int[] iArr, int[] iArr2) {
        mapAddressIfNeeded(i);
        this.currentPromotedAddress += 2;
        this.currentPromotedAddress += iArr.length * 2;
        this.currentPromotedAddress += iArr2.length * 2;
    }

    public void visitPackedSwitchPayloadInsn(int i, int i2, int i3, int[] iArr) {
        mapAddressIfNeeded(i);
        this.currentPromotedAddress += 4;
        this.currentPromotedAddress += iArr.length * 2;
    }

    public void visitFillArrayDataPayloadInsn(int i, int i2, Object obj, int i3, int i4) {
        mapAddressIfNeeded(i);
        this.currentPromotedAddress += 4;
        switch (i4) {
            case 1:
                int length = ((byte[]) obj).length;
                this.currentPromotedAddress = ((length & 1) + (length >> 1)) + this.currentPromotedAddress;
                return;
            case 2:
                this.currentPromotedAddress += ((short[]) obj).length * 1;
                return;
            case 4:
                this.currentPromotedAddress += ((int[]) obj).length * 2;
                return;
            case 8:
                this.currentPromotedAddress += ((long[]) obj).length * 4;
                return;
            default:
                throw new DexException("bogus element_width: " + Hex.u2(i4));
        }
    }
}
