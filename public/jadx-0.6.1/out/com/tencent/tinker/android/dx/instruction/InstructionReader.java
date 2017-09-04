package com.tencent.tinker.android.dx.instruction;

import com.tencent.tinker.android.dex.DexException;
import com.tencent.tinker.android.dx.util.Hex;
import java.io.EOFException;

public final class InstructionReader {
    private final ShortArrayCodeInput codeIn;

    public InstructionReader(ShortArrayCodeInput shortArrayCodeInput) {
        this.codeIn = shortArrayCodeInput;
    }

    public void accept(InstructionVisitor instructionVisitor) throws EOFException {
        this.codeIn.reset();
        while (this.codeIn.hasMore()) {
            int cursor = this.codeIn.cursor();
            int read = this.codeIn.read();
            int extractOpcodeFromUnit = Opcodes.extractOpcodeFromUnit(read);
            int byte0;
            int byte02;
            int read2;
            int nibble0;
            int nibble3;
            int instructionIndexType;
            int readInt;
            int[] iArr;
            switch (extractOpcodeFromUnit) {
                case -1:
                    instructionVisitor.visitZeroRegisterInsn(cursor, read, 0, 1, 0, 0);
                    break;
                case 0:
                case 14:
                    instructionVisitor.visitZeroRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, 0, (long) InstructionCodec.byte1(read));
                    break;
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
                    instructionVisitor.visitTwoRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, 0, 0, InstructionCodec.nibble2(read), InstructionCodec.nibble3(read));
                    break;
                case 2:
                case 5:
                case 8:
                    instructionVisitor.visitTwoRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, 0, 0, InstructionCodec.byte1(read), this.codeIn.read());
                    break;
                case 3:
                case 6:
                case 9:
                    instructionVisitor.visitTwoRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, 0, (long) InstructionCodec.byte1(read), this.codeIn.read(), this.codeIn.read());
                    break;
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
                    instructionVisitor.visitOneRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, 0, 0, InstructionCodec.byte1(read));
                    break;
                case 18:
                    instructionVisitor.visitOneRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, 0, (long) ((InstructionCodec.nibble3(read) << 28) >> 28), InstructionCodec.nibble2(read));
                    break;
                case 19:
                case 22:
                    instructionVisitor.visitOneRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, 0, (long) ((short) this.codeIn.read()), InstructionCodec.byte1(read));
                    break;
                case 20:
                case 23:
                    instructionVisitor.visitOneRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, 0, (long) this.codeIn.readInt(), InstructionCodec.byte1(read));
                    break;
                case 21:
                case 25:
                    byte0 = InstructionCodec.byte0(read);
                    instructionVisitor.visitOneRegisterInsn(cursor, byte0, 0, 1, 0, ((long) ((short) this.codeIn.read())) << (byte0 == 21 ? 16 : 48), InstructionCodec.byte1(read));
                    break;
                case 24:
                    instructionVisitor.visitOneRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, 0, this.codeIn.readLong(), InstructionCodec.byte1(read));
                    break;
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
                    byte0 = InstructionCodec.byte0(read);
                    instructionVisitor.visitOneRegisterInsn(cursor, byte0, this.codeIn.read(), InstructionCodec.getInstructionIndexType(byte0), 0, 0, InstructionCodec.byte1(read));
                    break;
                case 27:
                    byte0 = InstructionCodec.byte0(read);
                    instructionVisitor.visitOneRegisterInsn(cursor, byte0, this.codeIn.readInt(), InstructionCodec.getInstructionIndexType(byte0), 0, 0, InstructionCodec.byte1(read));
                    break;
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
                    byte02 = InstructionCodec.byte0(read);
                    instructionVisitor.visitTwoRegisterInsn(cursor, byte02, this.codeIn.read(), InstructionCodec.getInstructionIndexType(byte02), 0, 0, InstructionCodec.nibble2(read), InstructionCodec.nibble3(read));
                    break;
                case 36:
                case 110:
                case 111:
                case 112:
                case 113:
                case 114:
                    int byte03 = InstructionCodec.byte0(read);
                    int nibble2 = InstructionCodec.nibble2(read);
                    extractOpcodeFromUnit = InstructionCodec.nibble3(read);
                    read2 = this.codeIn.read();
                    read = this.codeIn.read();
                    nibble0 = InstructionCodec.nibble0(read);
                    byte0 = InstructionCodec.nibble1(read);
                    byte02 = InstructionCodec.nibble2(read);
                    nibble3 = InstructionCodec.nibble3(read);
                    instructionIndexType = InstructionCodec.getInstructionIndexType(byte03);
                    switch (extractOpcodeFromUnit) {
                        case 0:
                            instructionVisitor.visitZeroRegisterInsn(cursor, byte03, read2, instructionIndexType, 0, 0);
                            break;
                        case 1:
                            instructionVisitor.visitOneRegisterInsn(cursor, byte03, read2, instructionIndexType, 0, 0, nibble0);
                            break;
                        case 2:
                            instructionVisitor.visitTwoRegisterInsn(cursor, byte03, read2, instructionIndexType, 0, 0, nibble0, byte0);
                            break;
                        case 3:
                            instructionVisitor.visitThreeRegisterInsn(cursor, byte03, read2, instructionIndexType, 0, 0, nibble0, byte0, byte02);
                            break;
                        case 4:
                            instructionVisitor.visitFourRegisterInsn(cursor, byte03, read2, instructionIndexType, 0, 0, nibble0, byte0, byte02, nibble3);
                            break;
                        case 5:
                            instructionVisitor.visitFiveRegisterInsn(cursor, byte03, read2, instructionIndexType, 0, 0, nibble0, byte0, byte02, nibble3, nibble2);
                            break;
                        default:
                            throw new DexException("bogus registerCount: " + Hex.uNibble(extractOpcodeFromUnit));
                    }
                case 37:
                case 116:
                case Opcodes.INVOKE_SUPER_RANGE /*117*/:
                case 118:
                case Opcodes.INVOKE_STATIC_RANGE /*119*/:
                case 120:
                    byte02 = InstructionCodec.byte0(read);
                    instructionVisitor.visitRegisterRangeInsn(cursor, byte02, this.codeIn.read(), InstructionCodec.getInstructionIndexType(byte02), 0, 0, this.codeIn.read(), InstructionCodec.byte1(read));
                    break;
                case 38:
                case 43:
                case 44:
                    byte0 = InstructionCodec.byte0(read);
                    nibble0 = InstructionCodec.byte1(read);
                    readInt = cursor + this.codeIn.readInt();
                    switch (byte0) {
                        case 43:
                        case 44:
                            this.codeIn.setBaseAddress(readInt + 1, cursor);
                            break;
                    }
                    instructionVisitor.visitOneRegisterInsn(cursor, byte0, 0, 1, readInt, 0, nibble0);
                    break;
                case 40:
                    instructionVisitor.visitZeroRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, cursor + ((byte) InstructionCodec.byte1(read)), 0);
                    break;
                case 41:
                    instructionVisitor.visitZeroRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, cursor + ((short) this.codeIn.read()), (long) InstructionCodec.byte1(read));
                    break;
                case 42:
                    instructionVisitor.visitZeroRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, cursor + this.codeIn.readInt(), (long) InstructionCodec.byte1(read));
                    break;
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
                    nibble3 = InstructionCodec.byte0(read);
                    nibble0 = InstructionCodec.byte1(read);
                    extractOpcodeFromUnit = this.codeIn.read();
                    instructionVisitor.visitThreeRegisterInsn(cursor, nibble3, 0, 1, 0, 0, nibble0, InstructionCodec.byte0(extractOpcodeFromUnit), InstructionCodec.byte1(extractOpcodeFromUnit));
                    break;
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                    instructionVisitor.visitTwoRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, cursor + ((short) this.codeIn.read()), 0, InstructionCodec.nibble2(read), InstructionCodec.nibble3(read));
                    break;
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                    instructionVisitor.visitOneRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, cursor + ((short) this.codeIn.read()), 0, InstructionCodec.byte1(read));
                    break;
                case 208:
                case 209:
                case 210:
                case 211:
                case 212:
                case 213:
                case 214:
                case 215:
                    instructionVisitor.visitTwoRegisterInsn(cursor, InstructionCodec.byte0(read), 0, 1, 0, (long) ((short) this.codeIn.read()), InstructionCodec.nibble2(read), InstructionCodec.nibble3(read));
                    break;
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
                    byte02 = InstructionCodec.byte0(read);
                    nibble0 = InstructionCodec.byte1(read);
                    extractOpcodeFromUnit = this.codeIn.read();
                    instructionVisitor.visitTwoRegisterInsn(cursor, byte02, 0, 1, 0, (long) ((byte) InstructionCodec.byte1(extractOpcodeFromUnit)), nibble0, InstructionCodec.byte0(extractOpcodeFromUnit));
                    break;
                case 256:
                    read2 = this.codeIn.baseAddressForCursor();
                    instructionIndexType = this.codeIn.read();
                    readInt = this.codeIn.readInt();
                    iArr = new int[instructionIndexType];
                    for (extractOpcodeFromUnit = 0; extractOpcodeFromUnit < instructionIndexType; extractOpcodeFromUnit++) {
                        iArr[extractOpcodeFromUnit] = this.codeIn.readInt() + read2;
                    }
                    instructionVisitor.visitPackedSwitchPayloadInsn(cursor, read, readInt, iArr);
                    break;
                case 512:
                    read2 = this.codeIn.baseAddressForCursor();
                    instructionIndexType = this.codeIn.read();
                    int[] iArr2 = new int[instructionIndexType];
                    iArr = new int[instructionIndexType];
                    for (extractOpcodeFromUnit = 0; extractOpcodeFromUnit < instructionIndexType; extractOpcodeFromUnit++) {
                        iArr2[extractOpcodeFromUnit] = this.codeIn.readInt();
                    }
                    for (extractOpcodeFromUnit = 0; extractOpcodeFromUnit < instructionIndexType; extractOpcodeFromUnit++) {
                        iArr[extractOpcodeFromUnit] = this.codeIn.readInt() + read2;
                    }
                    instructionVisitor.visitSparseSwitchPayloadInsn(cursor, read, iArr2, iArr);
                    break;
                case Opcodes.FILL_ARRAY_DATA_PAYLOAD /*768*/:
                    extractOpcodeFromUnit = this.codeIn.read();
                    int readInt2 = this.codeIn.readInt();
                    Object obj;
                    switch (extractOpcodeFromUnit) {
                        case 1:
                            obj = new byte[readInt2];
                            extractOpcodeFromUnit = 0;
                            Object obj2 = 1;
                            readInt = 0;
                            while (readInt < readInt2) {
                                if (obj2 != null) {
                                    extractOpcodeFromUnit = this.codeIn.read();
                                }
                                obj[readInt] = (byte) (extractOpcodeFromUnit & 255);
                                readInt++;
                                obj2 = obj2 == null ? 1 : null;
                                extractOpcodeFromUnit >>= 8;
                            }
                            instructionVisitor.visitFillArrayDataPayloadInsn(cursor, read, obj, obj.length, 1);
                            break;
                        case 2:
                            obj = new short[readInt2];
                            for (extractOpcodeFromUnit = 0; extractOpcodeFromUnit < readInt2; extractOpcodeFromUnit++) {
                                obj[extractOpcodeFromUnit] = (short) this.codeIn.read();
                            }
                            instructionVisitor.visitFillArrayDataPayloadInsn(cursor, read, obj, obj.length, 2);
                            break;
                        case 4:
                            obj = new int[readInt2];
                            for (extractOpcodeFromUnit = 0; extractOpcodeFromUnit < readInt2; extractOpcodeFromUnit++) {
                                obj[extractOpcodeFromUnit] = this.codeIn.readInt();
                            }
                            instructionVisitor.visitFillArrayDataPayloadInsn(cursor, read, obj, obj.length, 4);
                            break;
                        case 8:
                            obj = new long[readInt2];
                            for (extractOpcodeFromUnit = 0; extractOpcodeFromUnit < readInt2; extractOpcodeFromUnit++) {
                                obj[extractOpcodeFromUnit] = this.codeIn.readLong();
                            }
                            instructionVisitor.visitFillArrayDataPayloadInsn(cursor, read, obj, obj.length, 8);
                            break;
                        default:
                            throw new DexException("bogus element_width: " + Hex.u2(extractOpcodeFromUnit));
                    }
                default:
                    throw new IllegalStateException("Unknown opcode: " + Hex.u4(extractOpcodeFromUnit));
            }
        }
    }
}
