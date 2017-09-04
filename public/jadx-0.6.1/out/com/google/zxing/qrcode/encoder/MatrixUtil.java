package com.google.zxing.qrcode.encoder;

import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Version;
import com.tencent.tinker.android.dx.instruction.Opcodes;

final class MatrixUtil {
    private static final int[][] POSITION_ADJUSTMENT_PATTERN = new int[][]{new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};
    private static final int[][] POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE = new int[][]{new int[]{-1, -1, -1, -1, -1, -1, -1}, new int[]{6, 18, -1, -1, -1, -1, -1}, new int[]{6, 22, -1, -1, -1, -1, -1}, new int[]{6, 26, -1, -1, -1, -1, -1}, new int[]{6, 30, -1, -1, -1, -1, -1}, new int[]{6, 34, -1, -1, -1, -1, -1}, new int[]{6, 22, 38, -1, -1, -1, -1}, new int[]{6, 24, 42, -1, -1, -1, -1}, new int[]{6, 26, 46, -1, -1, -1, -1}, new int[]{6, 28, 50, -1, -1, -1, -1}, new int[]{6, 30, 54, -1, -1, -1, -1}, new int[]{6, 32, 58, -1, -1, -1, -1}, new int[]{6, 34, 62, -1, -1, -1, -1}, new int[]{6, 26, 46, 66, -1, -1, -1}, new int[]{6, 26, 48, 70, -1, -1, -1}, new int[]{6, 26, 50, 74, -1, -1, -1}, new int[]{6, 30, 54, 78, -1, -1, -1}, new int[]{6, 30, 56, 82, -1, -1, -1}, new int[]{6, 30, 58, 86, -1, -1, -1}, new int[]{6, 34, 62, 90, -1, -1, -1}, new int[]{6, 28, 50, 72, 94, -1, -1}, new int[]{6, 26, 50, 74, 98, -1, -1}, new int[]{6, 30, 54, 78, 102, -1, -1}, new int[]{6, 28, 54, 80, 106, -1, -1}, new int[]{6, 32, 58, 84, 110, -1, -1}, new int[]{6, 30, 58, 86, 114, -1, -1}, new int[]{6, 34, 62, 90, 118, -1, -1}, new int[]{6, 26, 50, 74, 98, 122, -1}, new int[]{6, 30, 54, 78, 102, Opcodes.NOT_LONG, -1}, new int[]{6, 26, 52, 78, 104, Opcodes.INT_TO_FLOAT, -1}, new int[]{6, 30, 56, 82, 108, Opcodes.LONG_TO_DOUBLE, -1}, new int[]{6, 34, 60, 86, 112, Opcodes.DOUBLE_TO_INT, -1}, new int[]{6, 30, 58, 86, 114, Opcodes.INT_TO_CHAR, -1}, new int[]{6, 34, 62, 90, 118, Opcodes.MUL_INT, -1}, new int[]{6, 30, 54, 78, 102, Opcodes.NOT_LONG, Opcodes.OR_INT}, new int[]{6, 24, 50, 76, 102, 128, Opcodes.USHR_INT}, new int[]{6, 28, 54, 80, 106, Opcodes.LONG_TO_INT, Opcodes.DIV_LONG}, new int[]{6, 32, 58, 84, 110, Opcodes.FLOAT_TO_LONG, Opcodes.XOR_LONG}, new int[]{6, 26, 54, 82, 110, Opcodes.DOUBLE_TO_INT, Opcodes.ADD_FLOAT}, new int[]{6, 30, 58, 86, 114, Opcodes.INT_TO_CHAR, Opcodes.REM_FLOAT}};
    private static final int[][] POSITION_DETECTION_PATTERN = new int[][]{new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1, 1, 1}};
    private static final int[][] TYPE_INFO_COORDINATES = new int[][]{new int[]{8, 0}, new int[]{8, 1}, new int[]{8, 2}, new int[]{8, 3}, new int[]{8, 4}, new int[]{8, 5}, new int[]{8, 7}, new int[]{8, 8}, new int[]{7, 8}, new int[]{5, 8}, new int[]{4, 8}, new int[]{3, 8}, new int[]{2, 8}, new int[]{1, 8}, new int[]{0, 8}};
    private static final int TYPE_INFO_MASK_PATTERN = 21522;
    private static final int TYPE_INFO_POLY = 1335;
    private static final int VERSION_INFO_POLY = 7973;

    private MatrixUtil() {
    }

    static void clearMatrix(ByteMatrix byteMatrix) {
        byteMatrix.clear((byte) -1);
    }

    static void buildMatrix(BitArray bitArray, ErrorCorrectionLevel errorCorrectionLevel, Version version, int i, ByteMatrix byteMatrix) throws WriterException {
        clearMatrix(byteMatrix);
        embedBasicPatterns(version, byteMatrix);
        embedTypeInfo(errorCorrectionLevel, i, byteMatrix);
        maybeEmbedVersionInfo(version, byteMatrix);
        embedDataBits(bitArray, i, byteMatrix);
    }

    static void embedBasicPatterns(Version version, ByteMatrix byteMatrix) throws WriterException {
        embedPositionDetectionPatternsAndSeparators(byteMatrix);
        embedDarkDotAtLeftBottomCorner(byteMatrix);
        maybeEmbedPositionAdjustmentPatterns(version, byteMatrix);
        embedTimingPatterns(byteMatrix);
    }

    static void embedTypeInfo(ErrorCorrectionLevel errorCorrectionLevel, int i, ByteMatrix byteMatrix) throws WriterException {
        BitArray bitArray = new BitArray();
        makeTypeInfoBits(errorCorrectionLevel, i, bitArray);
        for (int i2 = 0; i2 < bitArray.getSize(); i2++) {
            boolean z = bitArray.get((bitArray.getSize() - 1) - i2);
            byteMatrix.set(TYPE_INFO_COORDINATES[i2][0], TYPE_INFO_COORDINATES[i2][1], z);
            if (i2 < 8) {
                byteMatrix.set((byteMatrix.getWidth() - i2) - 1, 8, z);
            } else {
                byteMatrix.set(8, (byteMatrix.getHeight() - 7) + (i2 - 8), z);
            }
        }
    }

    static void maybeEmbedVersionInfo(Version version, ByteMatrix byteMatrix) throws WriterException {
        if (version.getVersionNumber() >= 7) {
            BitArray bitArray = new BitArray();
            makeVersionInfoBits(version, bitArray);
            int i = 17;
            int i2 = 0;
            while (i2 < 6) {
                int i3 = i;
                for (i = 0; i < 3; i++) {
                    boolean z = bitArray.get(i3);
                    i3--;
                    byteMatrix.set(i2, (byteMatrix.getHeight() - 11) + i, z);
                    byteMatrix.set((byteMatrix.getHeight() - 11) + i, i2, z);
                }
                i2++;
                i = i3;
            }
        }
    }

    static void embedDataBits(BitArray bitArray, int i, ByteMatrix byteMatrix) throws WriterException {
        int width = byteMatrix.getWidth() - 1;
        int height = byteMatrix.getHeight() - 1;
        int i2 = -1;
        int i3 = 0;
        while (width > 0) {
            int i4;
            int i5;
            if (width == 6) {
                i4 = height;
                i5 = width - 1;
                height = i3;
            } else {
                i4 = height;
                i5 = width;
                height = i3;
            }
            while (i4 >= 0 && i4 < byteMatrix.getHeight()) {
                for (i3 = 0; i3 < 2; i3++) {
                    int i6 = i5 - i3;
                    if (isEmpty(byteMatrix.get(i6, i4))) {
                        boolean z;
                        if (height < bitArray.getSize()) {
                            boolean z2 = bitArray.get(height);
                            width = height + 1;
                            z = z2;
                        } else {
                            width = height;
                            z = false;
                        }
                        if (i != -1 && MaskUtil.getDataMaskBit(i, i6, i4)) {
                            if (z) {
                                z = false;
                            } else {
                                z = true;
                            }
                        }
                        byteMatrix.set(i6, i4, z);
                        height = width;
                    }
                }
                i4 += i2;
            }
            i2 = -i2;
            width = i5 - 2;
            i3 = height;
            height = i4 + i2;
        }
        if (i3 != bitArray.getSize()) {
            throw new WriterException("Not all bits consumed: " + i3 + '/' + bitArray.getSize());
        }
    }

    static int findMSBSet(int i) {
        int i2 = 0;
        while (i != 0) {
            i >>>= 1;
            i2++;
        }
        return i2;
    }

    static int calculateBCHCode(int i, int i2) {
        if (i2 == 0) {
            throw new IllegalArgumentException("0 polynomial");
        }
        int findMSBSet = findMSBSet(i2);
        int i3 = i << (findMSBSet - 1);
        while (findMSBSet(i3) >= findMSBSet) {
            i3 ^= i2 << (findMSBSet(i3) - findMSBSet);
        }
        return i3;
    }

    static void makeTypeInfoBits(ErrorCorrectionLevel errorCorrectionLevel, int i, BitArray bitArray) throws WriterException {
        if (QRCode.isValidMaskPattern(i)) {
            int bits = (errorCorrectionLevel.getBits() << 3) | i;
            bitArray.appendBits(bits, 5);
            bitArray.appendBits(calculateBCHCode(bits, TYPE_INFO_POLY), 10);
            BitArray bitArray2 = new BitArray();
            bitArray2.appendBits(TYPE_INFO_MASK_PATTERN, 15);
            bitArray.xor(bitArray2);
            if (bitArray.getSize() != 15) {
                throw new WriterException("should not happen but we got: " + bitArray.getSize());
            }
            return;
        }
        throw new WriterException("Invalid mask pattern");
    }

    static void makeVersionInfoBits(Version version, BitArray bitArray) throws WriterException {
        bitArray.appendBits(version.getVersionNumber(), 6);
        bitArray.appendBits(calculateBCHCode(version.getVersionNumber(), VERSION_INFO_POLY), 12);
        if (bitArray.getSize() != 18) {
            throw new WriterException("should not happen but we got: " + bitArray.getSize());
        }
    }

    private static boolean isEmpty(int i) {
        return i == -1;
    }

    private static void embedTimingPatterns(ByteMatrix byteMatrix) {
        for (int i = 8; i < byteMatrix.getWidth() - 8; i++) {
            int i2 = (i + 1) % 2;
            if (isEmpty(byteMatrix.get(i, 6))) {
                byteMatrix.set(i, 6, i2);
            }
            if (isEmpty(byteMatrix.get(6, i))) {
                byteMatrix.set(6, i, i2);
            }
        }
    }

    private static void embedDarkDotAtLeftBottomCorner(ByteMatrix byteMatrix) throws WriterException {
        if (byteMatrix.get(8, byteMatrix.getHeight() - 8) == (byte) 0) {
            throw new WriterException();
        }
        byteMatrix.set(8, byteMatrix.getHeight() - 8, 1);
    }

    private static void embedHorizontalSeparationPattern(int i, int i2, ByteMatrix byteMatrix) throws WriterException {
        int i3 = 0;
        while (i3 < 8) {
            if (isEmpty(byteMatrix.get(i + i3, i2))) {
                byteMatrix.set(i + i3, i2, 0);
                i3++;
            } else {
                throw new WriterException();
            }
        }
    }

    private static void embedVerticalSeparationPattern(int i, int i2, ByteMatrix byteMatrix) throws WriterException {
        int i3 = 0;
        while (i3 < 7) {
            if (isEmpty(byteMatrix.get(i, i2 + i3))) {
                byteMatrix.set(i, i2 + i3, 0);
                i3++;
            } else {
                throw new WriterException();
            }
        }
    }

    private static void embedPositionAdjustmentPattern(int i, int i2, ByteMatrix byteMatrix) {
        for (int i3 = 0; i3 < 5; i3++) {
            for (int i4 = 0; i4 < 5; i4++) {
                byteMatrix.set(i + i4, i2 + i3, POSITION_ADJUSTMENT_PATTERN[i3][i4]);
            }
        }
    }

    private static void embedPositionDetectionPattern(int i, int i2, ByteMatrix byteMatrix) {
        for (int i3 = 0; i3 < 7; i3++) {
            for (int i4 = 0; i4 < 7; i4++) {
                byteMatrix.set(i + i4, i2 + i3, POSITION_DETECTION_PATTERN[i3][i4]);
            }
        }
    }

    private static void embedPositionDetectionPatternsAndSeparators(ByteMatrix byteMatrix) throws WriterException {
        int length = POSITION_DETECTION_PATTERN[0].length;
        embedPositionDetectionPattern(0, 0, byteMatrix);
        embedPositionDetectionPattern(byteMatrix.getWidth() - length, 0, byteMatrix);
        embedPositionDetectionPattern(0, byteMatrix.getWidth() - length, byteMatrix);
        embedHorizontalSeparationPattern(0, 7, byteMatrix);
        embedHorizontalSeparationPattern(byteMatrix.getWidth() - 8, 7, byteMatrix);
        embedHorizontalSeparationPattern(0, byteMatrix.getWidth() - 8, byteMatrix);
        embedVerticalSeparationPattern(7, 0, byteMatrix);
        embedVerticalSeparationPattern((byteMatrix.getHeight() - 7) - 1, 0, byteMatrix);
        embedVerticalSeparationPattern(7, byteMatrix.getHeight() - 7, byteMatrix);
    }

    private static void maybeEmbedPositionAdjustmentPatterns(Version version, ByteMatrix byteMatrix) {
        if (version.getVersionNumber() >= 2) {
            int versionNumber = version.getVersionNumber() - 1;
            int[] iArr = POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE[versionNumber];
            int length = POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE[versionNumber].length;
            for (int i = 0; i < length; i++) {
                for (versionNumber = 0; versionNumber < length; versionNumber++) {
                    int i2 = iArr[i];
                    int i3 = iArr[versionNumber];
                    if (!(i3 == -1 || i2 == -1 || !isEmpty(byteMatrix.get(i3, i2)))) {
                        embedPositionAdjustmentPattern(i3 - 2, i2 - 2, byteMatrix);
                    }
                }
            }
        }
    }
}
