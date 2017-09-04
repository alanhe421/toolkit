package com.tencent.tinker.android.dx.instruction;

import com.tencent.tinker.android.dex.DexException;
import com.tencent.tinker.android.dx.util.Hex;

public final class InstructionWriter extends InstructionVisitor {
    private final ShortArrayCodeOutput codeOut;
    private final boolean hasPromoter;
    private final InstructionPromoter insnPromoter;

    public InstructionWriter(ShortArrayCodeOutput shortArrayCodeOutput, InstructionPromoter instructionPromoter) {
        super(null);
        this.codeOut = shortArrayCodeOutput;
        this.insnPromoter = instructionPromoter;
        this.hasPromoter = instructionPromoter != null;
    }

    public void visitZeroRegisterInsn(int i, int i2, int i3, int i4, int i5, long j) {
        if (this.hasPromoter) {
            i5 = this.insnPromoter.getPromotedAddress(i5);
        }
        int target;
        switch (i2) {
            case -1:
            case 0:
            case 14:
                this.codeOut.write((short) i2);
                return;
            case 36:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.codeOut.write(InstructionCodec.codeUnit(i2, InstructionCodec.makeByte(0, 0)), (short) i3, InstructionCodec.codeUnit(0, 0, 0, 0));
                return;
            case 40:
                if (this.hasPromoter) {
                    target = InstructionCodec.getTarget(i5, this.codeOut.cursor());
                    if (target == ((byte) target)) {
                        this.codeOut.write(InstructionCodec.codeUnit(i2, target & 255));
                        return;
                    } else if (target != ((short) target)) {
                        this.codeOut.write((short) 42, InstructionCodec.unit0(target), InstructionCodec.unit1(target));
                        return;
                    } else {
                        this.codeOut.write((short) 41, (short) target);
                        return;
                    }
                }
                this.codeOut.write(InstructionCodec.codeUnit(i2, InstructionCodec.getTargetByte(i5, this.codeOut.cursor())));
                return;
            case 41:
                short s;
                if (this.hasPromoter) {
                    target = InstructionCodec.getTarget(i5, this.codeOut.cursor());
                    if (target != ((short) target)) {
                        this.codeOut.write((short) 42, InstructionCodec.unit0(target), InstructionCodec.unit1(target));
                        return;
                    }
                    s = (short) i2;
                    this.codeOut.write(s, (short) target);
                    return;
                }
                s = (short) i2;
                this.codeOut.write(s, InstructionCodec.getTargetUnit(i5, this.codeOut.cursor()));
                return;
            case 42:
                target = InstructionCodec.getTarget(i5, this.codeOut.cursor());
                this.codeOut.write((short) i2, InstructionCodec.unit0(target), InstructionCodec.unit1(target));
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitOneRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6) {
        if (this.hasPromoter) {
            i5 = this.insnPromoter.getPromotedAddress(i5);
        }
        int literalInt;
        switch (i2) {
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
                this.codeOut.write(InstructionCodec.codeUnit(i2, i6));
                return;
            case 18:
                this.codeOut.write(InstructionCodec.codeUnit((short) i2, InstructionCodec.makeByte(i6, InstructionCodec.getLiteralNibble(j))));
                return;
            case 19:
            case 22:
                this.codeOut.write(InstructionCodec.codeUnit(i2, i6), InstructionCodec.getLiteralUnit(j));
                return;
            case 20:
            case 23:
                literalInt = InstructionCodec.getLiteralInt(j);
                this.codeOut.write(InstructionCodec.codeUnit(i2, i6), InstructionCodec.unit0(literalInt), InstructionCodec.unit1(literalInt));
                return;
            case 21:
            case 25:
                this.codeOut.write(InstructionCodec.codeUnit(i2, i6), (short) ((int) (j >> (i2 == 21 ? 16 : 48))));
                return;
            case 24:
                this.codeOut.write(InstructionCodec.codeUnit(i2, i6), InstructionCodec.unit0(j), InstructionCodec.unit1(j), InstructionCodec.unit2(j), InstructionCodec.unit3(j));
                return;
            case 26:
                if (this.hasPromoter) {
                    if (i3 > 65535) {
                        this.codeOut.write(InstructionCodec.codeUnit(27, i6), InstructionCodec.unit0(i3), InstructionCodec.unit1(i3));
                        return;
                    } else {
                        this.codeOut.write(InstructionCodec.codeUnit(i2, i6), (short) i3);
                        return;
                    }
                } else if (i3 > 65535) {
                    throw new DexException("string index out of bound: " + Hex.u4(i3) + ", perhaps you need to enable force jumbo mode.");
                } else {
                    this.codeOut.write(InstructionCodec.codeUnit(i2, i6), (short) i3);
                    return;
                }
            case 27:
                this.codeOut.write(InstructionCodec.codeUnit(i2, i6), InstructionCodec.unit0(i3), InstructionCodec.unit1(i3));
                return;
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
                this.codeOut.write(InstructionCodec.codeUnit(i2, i6), (short) i3);
                return;
            case 36:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.codeOut.write(InstructionCodec.codeUnit(i2, InstructionCodec.makeByte(0, 1)), (short) i3, InstructionCodec.codeUnit(i6, 0, 0, 0));
                return;
            case 38:
            case 43:
            case 44:
                switch (i2) {
                    case 43:
                    case 44:
                        this.codeOut.setBaseAddress(i5, this.codeOut.cursor());
                        break;
                }
                literalInt = InstructionCodec.getTarget(i5, this.codeOut.cursor());
                this.codeOut.write(InstructionCodec.codeUnit(i2, i6), InstructionCodec.unit0(literalInt), InstructionCodec.unit1(literalInt));
                return;
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
                this.codeOut.write(InstructionCodec.codeUnit(i2, i6), InstructionCodec.getTargetUnit(i5, this.codeOut.cursor()));
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitTwoRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7) {
        if (this.hasPromoter) {
            i5 = this.insnPromoter.getPromotedAddress(i5);
        }
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
                this.codeOut.write(InstructionCodec.codeUnit((short) i2, InstructionCodec.makeByte(i6, i7)));
                return;
            case 2:
            case 5:
            case 8:
                this.codeOut.write(InstructionCodec.codeUnit(i2, i6), InstructionCodec.getBUnit(i7));
                return;
            case 3:
            case 6:
            case 9:
                this.codeOut.write((short) i2, InstructionCodec.getAUnit(i6), InstructionCodec.getBUnit(i7));
                return;
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
                this.codeOut.write(InstructionCodec.codeUnit(i2, InstructionCodec.makeByte(i6, i7)), (short) i3);
                return;
            case 36:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.codeOut.write(InstructionCodec.codeUnit(i2, InstructionCodec.makeByte(0, 2)), (short) i3, InstructionCodec.codeUnit(i6, i7, 0, 0));
                return;
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
                this.codeOut.write(InstructionCodec.codeUnit(i2, InstructionCodec.makeByte(i6, i7)), InstructionCodec.getTargetUnit(i5, this.codeOut.cursor()));
                return;
            case 208:
            case 209:
            case 210:
            case 211:
            case 212:
            case 213:
            case 214:
            case 215:
                this.codeOut.write(InstructionCodec.codeUnit(i2, InstructionCodec.makeByte(i6, i7)), InstructionCodec.getLiteralUnit(j));
                return;
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
                this.codeOut.write(InstructionCodec.codeUnit(i2, i6), InstructionCodec.codeUnit(i7, InstructionCodec.getLiteralByte(j)));
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitThreeRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7, int i8) {
        switch (i2) {
            case 36:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.codeOut.write(InstructionCodec.codeUnit(i2, InstructionCodec.makeByte(0, 3)), (short) i3, InstructionCodec.codeUnit(i6, i7, i8, 0));
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
                this.codeOut.write(InstructionCodec.codeUnit(i2, i6), InstructionCodec.codeUnit(i7, i8));
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitFourRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7, int i8, int i9) {
        switch (i2) {
            case 36:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.codeOut.write(InstructionCodec.codeUnit(i2, InstructionCodec.makeByte(0, 4)), (short) i3, InstructionCodec.codeUnit(i6, i7, i8, i9));
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitFiveRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7, int i8, int i9, int i10) {
        switch (i2) {
            case 36:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
                this.codeOut.write(InstructionCodec.codeUnit(i2, InstructionCodec.makeByte(i10, 5)), (short) i3, InstructionCodec.codeUnit(i6, i7, i8, i9));
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitRegisterRangeInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7) {
        switch (i2) {
            case 37:
            case 116:
            case Opcodes.INVOKE_SUPER_RANGE /*117*/:
            case 118:
            case Opcodes.INVOKE_STATIC_RANGE /*119*/:
            case 120:
                this.codeOut.write(InstructionCodec.codeUnit(i2, i7), (short) i3, InstructionCodec.getAUnit(i6));
                return;
            default:
                throw new IllegalStateException("unexpected opcode: " + Hex.u2or4(i2));
        }
    }

    public void visitSparseSwitchPayloadInsn(int i, int i2, int[] iArr, int[] iArr2) {
        int length;
        int i3 = 0;
        int baseAddressForCursor = this.codeOut.baseAddressForCursor();
        this.codeOut.write((short) i2);
        this.codeOut.write(InstructionCodec.asUnsignedUnit(iArr2.length));
        for (int writeInt : iArr) {
            this.codeOut.writeInt(writeInt);
        }
        if (this.hasPromoter) {
            length = iArr2.length;
            while (i3 < length) {
                this.codeOut.writeInt(this.insnPromoter.getPromotedAddress(iArr2[i3]) - baseAddressForCursor);
                i3++;
            }
            return;
        }
        length = iArr2.length;
        while (i3 < length) {
            this.codeOut.writeInt(iArr2[i3] - baseAddressForCursor);
            i3++;
        }
    }

    public void visitPackedSwitchPayloadInsn(int i, int i2, int i3, int[] iArr) {
        int i4 = 0;
        int baseAddressForCursor = this.codeOut.baseAddressForCursor();
        this.codeOut.write((short) i2);
        this.codeOut.write(InstructionCodec.asUnsignedUnit(iArr.length));
        this.codeOut.writeInt(i3);
        int length;
        if (this.hasPromoter) {
            length = iArr.length;
            while (i4 < length) {
                this.codeOut.writeInt(this.insnPromoter.getPromotedAddress(iArr[i4]) - baseAddressForCursor);
                i4++;
            }
            return;
        }
        length = iArr.length;
        while (i4 < length) {
            this.codeOut.writeInt(iArr[i4] - baseAddressForCursor);
            i4++;
        }
    }

    public void visitFillArrayDataPayloadInsn(int i, int i2, Object obj, int i3, int i4) {
        this.codeOut.write((short) i2);
        this.codeOut.write((short) i4);
        this.codeOut.writeInt(i3);
        switch (i4) {
            case 1:
                this.codeOut.write((byte[]) obj);
                return;
            case 2:
                this.codeOut.write((short[]) obj);
                return;
            case 4:
                this.codeOut.write((int[]) obj);
                return;
            case 8:
                this.codeOut.write((long[]) obj);
                return;
            default:
                throw new DexException("bogus element_width: " + Hex.u2(i4));
        }
    }
}
