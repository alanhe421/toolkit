package com.tencent.tinker.android.dx.instruction;

import com.tencent.tinker.android.dex.DexException;
import com.tencent.tinker.android.dex.util.CompareUtils;
import com.tencent.tinker.android.dx.util.Hex;
import java.util.HashSet;
import java.util.Set;

public abstract class InstructionComparator {
    private final InstructionHolder[] insnHolders1;
    private final InstructionHolder[] insnHolders2;
    private final short[] insns1;
    private final short[] insns2;
    private final Set<String> visitedInsnAddrPairs;

    private static class InstructionHolder {
        int a;
        int address;
        int b;
        int c;
        int d;
        int e;
        int index;
        int insnFormat;
        long literal;
        int opcode;
        int registerCount;
        int target;

        private InstructionHolder() {
            this.insnFormat = 0;
            this.address = -1;
            this.opcode = -1;
            this.index = 0;
            this.target = 0;
            this.literal = 0;
            this.registerCount = 0;
            this.a = 0;
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.e = 0;
        }
    }

    private static class FillArrayDataPayloadInstructionHolder extends InstructionHolder {
        Object data;
        int elementWidth;
        int size;

        private FillArrayDataPayloadInstructionHolder() {
            super();
            this.data = null;
            this.size = 0;
            this.elementWidth = 0;
        }
    }

    private static class PackedSwitchPayloadInsntructionHolder extends InstructionHolder {
        int firstKey;
        int[] targets;

        private PackedSwitchPayloadInsntructionHolder() {
            super();
            this.firstKey = 0;
            this.targets = null;
        }
    }

    private static class SparseSwitchPayloadInsntructionHolder extends InstructionHolder {
        int[] keys;
        int[] targets;

        private SparseSwitchPayloadInsntructionHolder() {
            super();
            this.keys = null;
            this.targets = null;
        }
    }

    protected abstract boolean compareField(int i, int i2);

    protected abstract boolean compareMethod(int i, int i2);

    protected abstract boolean compareString(int i, int i2);

    protected abstract boolean compareType(int i, int i2);

    public InstructionComparator(short[] sArr, short[] sArr2) {
        this.insns1 = sArr;
        this.insns2 = sArr2;
        if (sArr != null) {
            this.insnHolders1 = readInstructionsIntoHolders(new ShortArrayCodeInput(sArr), sArr.length);
        } else {
            this.insnHolders1 = null;
        }
        if (sArr2 != null) {
            this.insnHolders2 = readInstructionsIntoHolders(new ShortArrayCodeInput(sArr2), sArr2.length);
        } else {
            this.insnHolders2 = null;
        }
        this.visitedInsnAddrPairs = new HashSet();
    }

    private InstructionHolder[] readInstructionsIntoHolders(ShortArrayCodeInput shortArrayCodeInput, int i) {
        shortArrayCodeInput.reset();
        final InstructionHolder[] instructionHolderArr = new InstructionHolder[i];
        try {
            new InstructionReader(shortArrayCodeInput).accept(new InstructionVisitor(null) {
                public void visitZeroRegisterInsn(int i, int i2, int i3, int i4, int i5, long j) {
                    InstructionHolder instructionHolder = new InstructionHolder();
                    instructionHolder.insnFormat = InstructionCodec.getInstructionFormat(i2);
                    instructionHolder.address = i;
                    instructionHolder.opcode = i2;
                    instructionHolder.index = i3;
                    instructionHolder.target = i5;
                    instructionHolder.literal = j;
                    instructionHolderArr[i] = instructionHolder;
                }

                public void visitOneRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6) {
                    InstructionHolder instructionHolder = new InstructionHolder();
                    instructionHolder.insnFormat = InstructionCodec.getInstructionFormat(i2);
                    instructionHolder.address = i;
                    instructionHolder.opcode = i2;
                    instructionHolder.index = i3;
                    instructionHolder.target = i5;
                    instructionHolder.literal = j;
                    instructionHolder.registerCount = 1;
                    instructionHolder.a = i6;
                    instructionHolderArr[i] = instructionHolder;
                }

                public void visitTwoRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7) {
                    InstructionHolder instructionHolder = new InstructionHolder();
                    instructionHolder.insnFormat = InstructionCodec.getInstructionFormat(i2);
                    instructionHolder.address = i;
                    instructionHolder.opcode = i2;
                    instructionHolder.index = i3;
                    instructionHolder.target = i5;
                    instructionHolder.literal = j;
                    instructionHolder.registerCount = 2;
                    instructionHolder.a = i6;
                    instructionHolder.b = i7;
                    instructionHolderArr[i] = instructionHolder;
                }

                public void visitThreeRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7, int i8) {
                    InstructionHolder instructionHolder = new InstructionHolder();
                    instructionHolder.insnFormat = InstructionCodec.getInstructionFormat(i2);
                    instructionHolder.address = i;
                    instructionHolder.opcode = i2;
                    instructionHolder.index = i3;
                    instructionHolder.target = i5;
                    instructionHolder.literal = j;
                    instructionHolder.registerCount = 3;
                    instructionHolder.a = i6;
                    instructionHolder.b = i7;
                    instructionHolder.c = i8;
                    instructionHolderArr[i] = instructionHolder;
                }

                public void visitFourRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7, int i8, int i9) {
                    InstructionHolder instructionHolder = new InstructionHolder();
                    instructionHolder.insnFormat = InstructionCodec.getInstructionFormat(i2);
                    instructionHolder.address = i;
                    instructionHolder.opcode = i2;
                    instructionHolder.index = i3;
                    instructionHolder.target = i5;
                    instructionHolder.literal = j;
                    instructionHolder.registerCount = 4;
                    instructionHolder.a = i6;
                    instructionHolder.b = i7;
                    instructionHolder.c = i8;
                    instructionHolder.d = i9;
                    instructionHolderArr[i] = instructionHolder;
                }

                public void visitFiveRegisterInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7, int i8, int i9, int i10) {
                    InstructionHolder instructionHolder = new InstructionHolder();
                    instructionHolder.insnFormat = InstructionCodec.getInstructionFormat(i2);
                    instructionHolder.address = i;
                    instructionHolder.opcode = i2;
                    instructionHolder.index = i3;
                    instructionHolder.target = i5;
                    instructionHolder.literal = j;
                    instructionHolder.registerCount = 5;
                    instructionHolder.a = i6;
                    instructionHolder.b = i7;
                    instructionHolder.c = i8;
                    instructionHolder.d = i9;
                    instructionHolder.e = i10;
                    instructionHolderArr[i] = instructionHolder;
                }

                public void visitRegisterRangeInsn(int i, int i2, int i3, int i4, int i5, long j, int i6, int i7) {
                    InstructionHolder instructionHolder = new InstructionHolder();
                    instructionHolder.insnFormat = InstructionCodec.getInstructionFormat(i2);
                    instructionHolder.address = i;
                    instructionHolder.opcode = i2;
                    instructionHolder.index = i3;
                    instructionHolder.target = i5;
                    instructionHolder.literal = j;
                    instructionHolder.registerCount = i7;
                    instructionHolder.a = i6;
                    instructionHolderArr[i] = instructionHolder;
                }

                public void visitSparseSwitchPayloadInsn(int i, int i2, int[] iArr, int[] iArr2) {
                    SparseSwitchPayloadInsntructionHolder sparseSwitchPayloadInsntructionHolder = new SparseSwitchPayloadInsntructionHolder();
                    sparseSwitchPayloadInsntructionHolder.insnFormat = InstructionCodec.getInstructionFormat(i2);
                    sparseSwitchPayloadInsntructionHolder.address = i;
                    sparseSwitchPayloadInsntructionHolder.opcode = i2;
                    sparseSwitchPayloadInsntructionHolder.keys = iArr;
                    sparseSwitchPayloadInsntructionHolder.targets = iArr2;
                    instructionHolderArr[i] = sparseSwitchPayloadInsntructionHolder;
                }

                public void visitPackedSwitchPayloadInsn(int i, int i2, int i3, int[] iArr) {
                    PackedSwitchPayloadInsntructionHolder packedSwitchPayloadInsntructionHolder = new PackedSwitchPayloadInsntructionHolder();
                    packedSwitchPayloadInsntructionHolder.insnFormat = InstructionCodec.getInstructionFormat(i2);
                    packedSwitchPayloadInsntructionHolder.address = i;
                    packedSwitchPayloadInsntructionHolder.opcode = i2;
                    packedSwitchPayloadInsntructionHolder.firstKey = i3;
                    packedSwitchPayloadInsntructionHolder.targets = iArr;
                    instructionHolderArr[i] = packedSwitchPayloadInsntructionHolder;
                }

                public void visitFillArrayDataPayloadInsn(int i, int i2, Object obj, int i3, int i4) {
                    FillArrayDataPayloadInstructionHolder fillArrayDataPayloadInstructionHolder = new FillArrayDataPayloadInstructionHolder();
                    fillArrayDataPayloadInstructionHolder.insnFormat = InstructionCodec.getInstructionFormat(i2);
                    fillArrayDataPayloadInstructionHolder.address = i;
                    fillArrayDataPayloadInstructionHolder.opcode = i2;
                    fillArrayDataPayloadInstructionHolder.data = obj;
                    fillArrayDataPayloadInstructionHolder.size = i3;
                    fillArrayDataPayloadInstructionHolder.elementWidth = i4;
                    instructionHolderArr[i] = fillArrayDataPayloadInstructionHolder;
                }
            });
            return instructionHolderArr;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public final boolean compare() {
        this.visitedInsnAddrPairs.clear();
        if (this.insnHolders1 == null && this.insnHolders2 == null) {
            return true;
        }
        if (this.insnHolders1 == null || this.insnHolders2 == null) {
            return false;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i4 < this.insnHolders1.length && r3 < this.insnHolders2.length) {
            InstructionHolder instructionHolder = null;
            while (i4 < this.insnHolders1.length && instructionHolder == null) {
                int i5 = i4 + 1;
                InstructionHolder instructionHolder2 = this.insnHolders1[i4];
                i4 = i5;
                instructionHolder = instructionHolder2;
            }
            if (instructionHolder == null) {
                break;
            }
            i2++;
            InstructionHolder instructionHolder3 = null;
            while (i3 < this.insnHolders2.length && instructionHolder3 == null) {
                int i6 = i3 + 1;
                instructionHolder2 = this.insnHolders2[i3];
                i3 = i6;
                instructionHolder3 = instructionHolder2;
            }
            if (instructionHolder3 == null) {
                break;
            }
            i++;
            if (instructionHolder.opcode != instructionHolder3.opcode) {
                if (instructionHolder.opcode == 26 && instructionHolder3.opcode == 27) {
                    if (!compareString(instructionHolder.index, instructionHolder3.index)) {
                        return false;
                    }
                } else if (instructionHolder.opcode != 27 || instructionHolder3.opcode != 26) {
                    return false;
                } else {
                    if (!compareString(instructionHolder.index, instructionHolder3.index)) {
                        return false;
                    }
                }
            } else if (!isSameInstruction(instructionHolder.address, instructionHolder3.address)) {
                return false;
            }
        }
        while (i4 < this.insnHolders1.length) {
            i5 = i4 + 1;
            if (this.insnHolders1[i4] != null) {
                return false;
            }
            i4 = i5;
        }
        while (i3 < this.insnHolders2.length) {
            i4 = i3 + 1;
            if (this.insnHolders2[i3] != null) {
                return false;
            }
            i3 = i4;
        }
        if (i2 == i) {
            return true;
        }
        return false;
    }

    public boolean isSameInstruction(int i, int i2) {
        boolean z = true;
        InstructionHolder instructionHolder = this.insnHolders1[i];
        InstructionHolder instructionHolder2 = this.insnHolders2[i2];
        if (instructionHolder == null && instructionHolder2 == null) {
            return true;
        }
        if (instructionHolder == null || instructionHolder2 == null || instructionHolder.opcode != instructionHolder2.opcode) {
            return false;
        }
        int i3 = instructionHolder.opcode;
        int length;
        switch (instructionHolder.insnFormat) {
            case 2:
            case 7:
            case 11:
            case 15:
            case 18:
            case 21:
                return this.visitedInsnAddrPairs.add(new StringBuilder().append(i).append("-").append(i2).toString()) ? isSameInstruction(instructionHolder.target, instructionHolder2.target) : true;
            case 8:
            case 13:
            case 19:
            case 23:
            case 24:
                return compareIndex(i3, instructionHolder.index, instructionHolder2.index);
            case 26:
                FillArrayDataPayloadInstructionHolder fillArrayDataPayloadInstructionHolder = (FillArrayDataPayloadInstructionHolder) instructionHolder;
                FillArrayDataPayloadInstructionHolder fillArrayDataPayloadInstructionHolder2 = (FillArrayDataPayloadInstructionHolder) instructionHolder2;
                if (fillArrayDataPayloadInstructionHolder.elementWidth != fillArrayDataPayloadInstructionHolder2.elementWidth || fillArrayDataPayloadInstructionHolder.size != fillArrayDataPayloadInstructionHolder2.size) {
                    return false;
                }
                i3 = fillArrayDataPayloadInstructionHolder.elementWidth;
                switch (i3) {
                    case 1:
                        return CompareUtils.uArrCompare((byte[]) ((byte[]) fillArrayDataPayloadInstructionHolder.data), (byte[]) ((byte[]) fillArrayDataPayloadInstructionHolder2.data)) == 0;
                    case 2:
                        if (CompareUtils.uArrCompare((short[]) fillArrayDataPayloadInstructionHolder.data, (short[]) fillArrayDataPayloadInstructionHolder2.data) != 0) {
                            z = false;
                        }
                        return z;
                    case 4:
                        if (CompareUtils.uArrCompare((int[]) fillArrayDataPayloadInstructionHolder.data, (int[]) fillArrayDataPayloadInstructionHolder2.data) != 0) {
                            z = false;
                        }
                        return z;
                    case 8:
                        if (CompareUtils.sArrCompare((long[]) fillArrayDataPayloadInstructionHolder.data, (long[]) fillArrayDataPayloadInstructionHolder2.data) != 0) {
                            z = false;
                        }
                        return z;
                    default:
                        throw new DexException("bogus element_width: " + Hex.u2(i3));
                }
            case 27:
                PackedSwitchPayloadInsntructionHolder packedSwitchPayloadInsntructionHolder = (PackedSwitchPayloadInsntructionHolder) instructionHolder;
                PackedSwitchPayloadInsntructionHolder packedSwitchPayloadInsntructionHolder2 = (PackedSwitchPayloadInsntructionHolder) instructionHolder2;
                if (packedSwitchPayloadInsntructionHolder.firstKey != packedSwitchPayloadInsntructionHolder2.firstKey || packedSwitchPayloadInsntructionHolder.targets.length != packedSwitchPayloadInsntructionHolder2.targets.length) {
                    return false;
                }
                length = packedSwitchPayloadInsntructionHolder.targets.length;
                for (i3 = 0; i3 < length; i3++) {
                    if (!isSameInstruction(packedSwitchPayloadInsntructionHolder.targets[i3], packedSwitchPayloadInsntructionHolder2.targets[i3])) {
                        return false;
                    }
                }
                return true;
            case 28:
                SparseSwitchPayloadInsntructionHolder sparseSwitchPayloadInsntructionHolder = (SparseSwitchPayloadInsntructionHolder) instructionHolder;
                SparseSwitchPayloadInsntructionHolder sparseSwitchPayloadInsntructionHolder2 = (SparseSwitchPayloadInsntructionHolder) instructionHolder2;
                if (CompareUtils.uArrCompare(sparseSwitchPayloadInsntructionHolder.keys, sparseSwitchPayloadInsntructionHolder2.keys) != 0 || sparseSwitchPayloadInsntructionHolder.targets.length != sparseSwitchPayloadInsntructionHolder2.targets.length) {
                    return false;
                }
                length = sparseSwitchPayloadInsntructionHolder.targets.length;
                for (i3 = 0; i3 < length; i3++) {
                    if (!isSameInstruction(sparseSwitchPayloadInsntructionHolder.targets[i3], sparseSwitchPayloadInsntructionHolder2.targets[i3])) {
                        return false;
                    }
                }
                return true;
            default:
                if (instructionHolder.literal == instructionHolder2.literal && instructionHolder.registerCount == instructionHolder2.registerCount && instructionHolder.a == instructionHolder2.a && instructionHolder.b == instructionHolder2.b && instructionHolder.c == instructionHolder2.c && instructionHolder.d == instructionHolder2.d && instructionHolder.e == instructionHolder2.e) {
                    return true;
                }
                return false;
        }
    }

    private boolean compareIndex(int i, int i2, int i3) {
        switch (InstructionCodec.getInstructionIndexType(i)) {
            case 2:
                return compareType(i2, i3);
            case 3:
                return compareString(i2, i3);
            case 4:
                return compareMethod(i2, i3);
            case 5:
                return compareField(i2, i3);
            default:
                if (i2 == i3) {
                    return true;
                }
                return false;
        }
    }
}
