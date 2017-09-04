package com.tencent.tinker.commons.dexpatcher.util;

import com.tencent.tinker.android.dex.Annotation;
import com.tencent.tinker.android.dex.AnnotationSet;
import com.tencent.tinker.android.dex.AnnotationSetRefList;
import com.tencent.tinker.android.dex.AnnotationsDirectory;
import com.tencent.tinker.android.dex.ClassData;
import com.tencent.tinker.android.dex.ClassData.Field;
import com.tencent.tinker.android.dex.ClassData.Method;
import com.tencent.tinker.android.dex.ClassDef;
import com.tencent.tinker.android.dex.Code;
import com.tencent.tinker.android.dex.Code.CatchHandler;
import com.tencent.tinker.android.dex.DebugInfoItem;
import com.tencent.tinker.android.dex.DexException;
import com.tencent.tinker.android.dex.EncodedValue;
import com.tencent.tinker.android.dex.EncodedValueCodec;
import com.tencent.tinker.android.dex.EncodedValueReader;
import com.tencent.tinker.android.dex.FieldId;
import com.tencent.tinker.android.dex.Leb128;
import com.tencent.tinker.android.dex.MethodId;
import com.tencent.tinker.android.dex.ProtoId;
import com.tencent.tinker.android.dex.TypeList;
import com.tencent.tinker.android.dex.util.ByteInput;
import com.tencent.tinker.android.dex.util.ByteOutput;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;

public abstract class AbstractIndexMap {

    private final class EncodedValueTransformer {
        private final ByteOutput out;

        EncodedValueTransformer(ByteOutput byteOutput) {
            this.out = byteOutput;
        }

        public void transform(EncodedValueReader encodedValueReader) {
            int i = 0;
            switch (encodedValueReader.peek()) {
                case 0:
                    EncodedValueCodec.writeSignedIntegralValue(this.out, 0, (long) encodedValueReader.readByte());
                    return;
                case 2:
                    EncodedValueCodec.writeSignedIntegralValue(this.out, 2, (long) encodedValueReader.readShort());
                    return;
                case 3:
                    EncodedValueCodec.writeUnsignedIntegralValue(this.out, 3, (long) encodedValueReader.readChar());
                    return;
                case 4:
                    EncodedValueCodec.writeSignedIntegralValue(this.out, 4, (long) encodedValueReader.readInt());
                    return;
                case 6:
                    EncodedValueCodec.writeSignedIntegralValue(this.out, 6, encodedValueReader.readLong());
                    return;
                case 16:
                    EncodedValueCodec.writeRightZeroExtendedValue(this.out, 16, ((long) Float.floatToIntBits(encodedValueReader.readFloat())) << 32);
                    return;
                case 17:
                    EncodedValueCodec.writeRightZeroExtendedValue(this.out, 17, Double.doubleToLongBits(encodedValueReader.readDouble()));
                    return;
                case 23:
                    EncodedValueCodec.writeUnsignedIntegralValue(this.out, 23, (long) AbstractIndexMap.this.adjustStringIndex(encodedValueReader.readString()));
                    return;
                case 24:
                    EncodedValueCodec.writeUnsignedIntegralValue(this.out, 24, (long) AbstractIndexMap.this.adjustTypeIdIndex(encodedValueReader.readType()));
                    return;
                case 25:
                    EncodedValueCodec.writeUnsignedIntegralValue(this.out, 25, (long) AbstractIndexMap.this.adjustFieldIdIndex(encodedValueReader.readField()));
                    return;
                case 26:
                    EncodedValueCodec.writeUnsignedIntegralValue(this.out, 26, (long) AbstractIndexMap.this.adjustMethodIdIndex(encodedValueReader.readMethod()));
                    return;
                case 27:
                    EncodedValueCodec.writeUnsignedIntegralValue(this.out, 27, (long) AbstractIndexMap.this.adjustFieldIdIndex(encodedValueReader.readEnum()));
                    return;
                case 28:
                    writeTypeAndArg(28, 0);
                    transformArray(encodedValueReader);
                    return;
                case 29:
                    writeTypeAndArg(29, 0);
                    transformAnnotation(encodedValueReader);
                    return;
                case 30:
                    encodedValueReader.readNull();
                    writeTypeAndArg(30, 0);
                    return;
                case 31:
                    if (encodedValueReader.readBoolean()) {
                        i = 1;
                    }
                    writeTypeAndArg(31, i);
                    return;
                default:
                    throw new DexException("Unexpected type: " + Integer.toHexString(encodedValueReader.peek()));
            }
        }

        private void transformAnnotation(EncodedValueReader encodedValueReader) {
            int readAnnotation = encodedValueReader.readAnnotation();
            Leb128.writeUnsignedLeb128(this.out, AbstractIndexMap.this.adjustTypeIdIndex(encodedValueReader.getAnnotationType()));
            Leb128.writeUnsignedLeb128(this.out, readAnnotation);
            for (int i = 0; i < readAnnotation; i++) {
                Leb128.writeUnsignedLeb128(this.out, AbstractIndexMap.this.adjustStringIndex(encodedValueReader.readAnnotationName()));
                transform(encodedValueReader);
            }
        }

        private void transformArray(EncodedValueReader encodedValueReader) {
            int readArray = encodedValueReader.readArray();
            Leb128.writeUnsignedLeb128(this.out, readArray);
            for (int i = 0; i < readArray; i++) {
                transform(encodedValueReader);
            }
        }

        private void writeTypeAndArg(int i, int i2) {
            this.out.writeByte((i2 << 5) | i);
        }
    }

    public abstract int adjustAnnotationOffset(int i);

    public abstract int adjustAnnotationSetOffset(int i);

    public abstract int adjustAnnotationSetRefListOffset(int i);

    public abstract int adjustAnnotationsDirectoryOffset(int i);

    public abstract int adjustClassDataOffset(int i);

    public abstract int adjustCodeOffset(int i);

    public abstract int adjustDebugInfoItemOffset(int i);

    public abstract int adjustFieldIdIndex(int i);

    public abstract int adjustMethodIdIndex(int i);

    public abstract int adjustProtoIdIndex(int i);

    public abstract int adjustStaticValuesOffset(int i);

    public abstract int adjustStringIndex(int i);

    public abstract int adjustTypeIdIndex(int i);

    public abstract int adjustTypeListOffset(int i);

    public TypeList adjust(TypeList typeList) {
        if (typeList == TypeList.EMPTY) {
            return typeList;
        }
        short[] sArr = new short[typeList.types.length];
        for (int i = 0; i < sArr.length; i++) {
            sArr[i] = (short) adjustTypeIdIndex(typeList.types[i]);
        }
        return new TypeList(typeList.off, sArr);
    }

    public MethodId adjust(MethodId methodId) {
        return new MethodId(methodId.off, adjustTypeIdIndex(methodId.declaringClassIndex), adjustProtoIdIndex(methodId.protoIndex), adjustStringIndex(methodId.nameIndex));
    }

    public FieldId adjust(FieldId fieldId) {
        return new FieldId(fieldId.off, adjustTypeIdIndex(fieldId.declaringClassIndex), adjustTypeIdIndex(fieldId.typeIndex), adjustStringIndex(fieldId.nameIndex));
    }

    public ProtoId adjust(ProtoId protoId) {
        return new ProtoId(protoId.off, adjustStringIndex(protoId.shortyIndex), adjustTypeIdIndex(protoId.returnTypeIndex), adjustTypeListOffset(protoId.parametersOffset));
    }

    public ClassDef adjust(ClassDef classDef) {
        return new ClassDef(classDef.off, adjustTypeIdIndex(classDef.typeIndex), classDef.accessFlags, adjustTypeIdIndex(classDef.supertypeIndex), adjustTypeListOffset(classDef.interfacesOffset), adjustStringIndex(classDef.sourceFileIndex), adjustAnnotationsDirectoryOffset(classDef.annotationsOffset), adjustClassDataOffset(classDef.classDataOffset), adjustStaticValuesOffset(classDef.staticValuesOffset));
    }

    public ClassData adjust(ClassData classData) {
        return new ClassData(classData.off, adjustFields(classData.staticFields), adjustFields(classData.instanceFields), adjustMethods(classData.directMethods), adjustMethods(classData.virtualMethods));
    }

    public Code adjust(Code code) {
        return new Code(code.off, code.registersSize, code.insSize, code.outsSize, adjustDebugInfoItemOffset(code.debugInfoOffset), adjustInstructions(code.instructions), code.tries, adjustCatchHandlers(code.catchHandlers));
    }

    private short[] adjustInstructions(short[] sArr) {
        return (sArr == null || sArr.length == 0) ? sArr : new InstructionTransformer(this).transform(sArr);
    }

    private CatchHandler[] adjustCatchHandlers(CatchHandler[] catchHandlerArr) {
        if (catchHandlerArr == null || catchHandlerArr.length == 0) {
            return catchHandlerArr;
        }
        CatchHandler[] catchHandlerArr2 = new CatchHandler[catchHandlerArr.length];
        for (int i = 0; i < catchHandlerArr.length; i++) {
            CatchHandler catchHandler = catchHandlerArr[i];
            int length = catchHandler.typeIndexes.length;
            int[] iArr = new int[length];
            for (int i2 = 0; i2 < length; i2++) {
                iArr[i2] = adjustTypeIdIndex(catchHandler.typeIndexes[i2]);
            }
            catchHandlerArr2[i] = new CatchHandler(iArr, catchHandler.addresses, catchHandler.catchAllAddress, catchHandler.offset);
        }
        return catchHandlerArr2;
    }

    private Field[] adjustFields(Field[] fieldArr) {
        Field[] fieldArr2 = new Field[fieldArr.length];
        for (int i = 0; i < fieldArr.length; i++) {
            Field field = fieldArr[i];
            fieldArr2[i] = new Field(adjustFieldIdIndex(field.fieldIndex), field.accessFlags);
        }
        return fieldArr2;
    }

    private Method[] adjustMethods(Method[] methodArr) {
        Method[] methodArr2 = new Method[methodArr.length];
        for (int i = 0; i < methodArr.length; i++) {
            Method method = methodArr[i];
            methodArr2[i] = new Method(adjustMethodIdIndex(method.methodIndex), method.accessFlags, adjustCodeOffset(method.codeOffset));
        }
        return methodArr2;
    }

    public DebugInfoItem adjust(DebugInfoItem debugInfoItem) {
        return new DebugInfoItem(debugInfoItem.off, debugInfoItem.lineStart, adjustParameterNames(debugInfoItem.parameterNames), adjustDebugInfoItemSTM(debugInfoItem.infoSTM));
    }

    private int[] adjustParameterNames(int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i = 0; i < length; i++) {
            iArr2[i] = adjustStringIndex(iArr[i]);
        }
        return iArr2;
    }

    private byte[] adjustDebugInfoItemSTM(byte[] bArr) {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ByteInput anonymousClass1 = new ByteInput() {
            public byte readByte() {
                return (byte) (byteArrayInputStream.read() & 255);
            }
        };
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length + 512);
        ByteOutput anonymousClass2 = new ByteOutput() {
            public void writeByte(int i) {
                byteArrayOutputStream.write(i);
            }
        };
        while (true) {
            int read = byteArrayInputStream.read() & 255;
            byteArrayOutputStream.write(read);
            switch (read) {
                case 0:
                    return byteArrayOutputStream.toByteArray();
                case 1:
                    Leb128.writeUnsignedLeb128(anonymousClass2, Leb128.readUnsignedLeb128(anonymousClass1));
                    break;
                case 2:
                    Leb128.writeSignedLeb128(anonymousClass2, Leb128.readSignedLeb128(anonymousClass1));
                    break;
                case 3:
                case 4:
                    Leb128.writeUnsignedLeb128(anonymousClass2, Leb128.readUnsignedLeb128(anonymousClass1));
                    Leb128.writeUnsignedLeb128p1(anonymousClass2, adjustStringIndex(Leb128.readUnsignedLeb128p1(anonymousClass1)));
                    Leb128.writeUnsignedLeb128p1(anonymousClass2, adjustTypeIdIndex(Leb128.readUnsignedLeb128p1(anonymousClass1)));
                    if (read != 4) {
                        break;
                    }
                    Leb128.writeUnsignedLeb128p1(anonymousClass2, adjustStringIndex(Leb128.readUnsignedLeb128p1(anonymousClass1)));
                    break;
                case 5:
                case 6:
                    Leb128.writeUnsignedLeb128(anonymousClass2, Leb128.readUnsignedLeb128(anonymousClass1));
                    break;
                case 9:
                    Leb128.writeUnsignedLeb128p1(anonymousClass2, adjustStringIndex(Leb128.readUnsignedLeb128p1(anonymousClass1)));
                    break;
                default:
                    break;
            }
        }
    }

    public EncodedValue adjust(EncodedValue encodedValue) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(encodedValue.data.length);
        new EncodedValueTransformer(new ByteOutput() {
            public void writeByte(int i) {
                byteArrayOutputStream.write(i);
            }
        }).transformArray(new EncodedValueReader(encodedValue, 28));
        return new EncodedValue(encodedValue.off, byteArrayOutputStream.toByteArray());
    }

    public Annotation adjust(Annotation annotation) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(annotation.encodedAnnotation.data.length);
        new EncodedValueTransformer(new ByteOutput() {
            public void writeByte(int i) {
                byteArrayOutputStream.write(i);
            }
        }).transformAnnotation(annotation.getReader());
        return new Annotation(annotation.off, annotation.visibility, new EncodedValue(annotation.encodedAnnotation.off, byteArrayOutputStream.toByteArray()));
    }

    public AnnotationSet adjust(AnnotationSet annotationSet) {
        int length = annotationSet.annotationOffsets.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = adjustAnnotationOffset(annotationSet.annotationOffsets[i]);
        }
        return new AnnotationSet(annotationSet.off, iArr);
    }

    public AnnotationSetRefList adjust(AnnotationSetRefList annotationSetRefList) {
        int length = annotationSetRefList.annotationSetRefItems.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = adjustAnnotationSetOffset(annotationSetRefList.annotationSetRefItems[i]);
        }
        return new AnnotationSetRefList(annotationSetRefList.off, iArr);
    }

    public AnnotationsDirectory adjust(AnnotationsDirectory annotationsDirectory) {
        int i;
        int adjustAnnotationSetOffset = adjustAnnotationSetOffset(annotationsDirectory.classAnnotationsOffset);
        int[][] iArr = (int[][]) Array.newInstance(Integer.TYPE, new int[]{annotationsDirectory.fieldAnnotations.length, 2});
        for (i = 0; i < iArr.length; i++) {
            iArr[i][0] = adjustFieldIdIndex(annotationsDirectory.fieldAnnotations[i][0]);
            iArr[i][1] = adjustAnnotationSetOffset(annotationsDirectory.fieldAnnotations[i][1]);
        }
        int[][] iArr2 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{annotationsDirectory.methodAnnotations.length, 2});
        for (i = 0; i < iArr2.length; i++) {
            iArr2[i][0] = adjustMethodIdIndex(annotationsDirectory.methodAnnotations[i][0]);
            iArr2[i][1] = adjustAnnotationSetOffset(annotationsDirectory.methodAnnotations[i][1]);
        }
        int[][] iArr3 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{annotationsDirectory.parameterAnnotations.length, 2});
        for (i = 0; i < iArr3.length; i++) {
            iArr3[i][0] = adjustMethodIdIndex(annotationsDirectory.parameterAnnotations[i][0]);
            iArr3[i][1] = adjustAnnotationSetRefListOffset(annotationsDirectory.parameterAnnotations[i][1]);
        }
        return new AnnotationsDirectory(annotationsDirectory.off, adjustAnnotationSetOffset, iArr, iArr2, iArr3);
    }
}
