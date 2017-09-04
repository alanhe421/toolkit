package jadx.core.dex.instructions.args;

import jadx.core.Consts;
import jadx.core.deobf.Deobfuscator;
import jadx.core.dex.nodes.DexNode;
import jadx.core.dex.nodes.parser.SignatureParser;
import jadx.core.utils.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public abstract class ArgType {
    public static final ArgType BOOLEAN = primitive(PrimitiveType.BOOLEAN);
    public static final ArgType BYTE = primitive(PrimitiveType.BYTE);
    public static final ArgType CHAR = primitive(PrimitiveType.CHAR);
    public static final ArgType CLASS = object(Consts.CLASS_CLASS);
    public static final ArgType DOUBLE = primitive(PrimitiveType.DOUBLE);
    public static final ArgType ENUM = object(Consts.CLASS_ENUM);
    public static final ArgType FLOAT = primitive(PrimitiveType.FLOAT);
    public static final ArgType INT = primitive(PrimitiveType.INT);
    public static final ArgType LONG = primitive(PrimitiveType.LONG);
    public static final ArgType NARROW = unknown(PrimitiveType.INT, PrimitiveType.FLOAT, PrimitiveType.BOOLEAN, PrimitiveType.SHORT, PrimitiveType.BYTE, PrimitiveType.CHAR, PrimitiveType.OBJECT, PrimitiveType.ARRAY);
    public static final ArgType NARROW_NUMBERS = unknown(PrimitiveType.INT, PrimitiveType.FLOAT, PrimitiveType.BOOLEAN, PrimitiveType.SHORT, PrimitiveType.BYTE, PrimitiveType.CHAR);
    public static final ArgType OBJECT = object(Consts.CLASS_OBJECT);
    public static final ArgType SHORT = primitive(PrimitiveType.SHORT);
    public static final ArgType STRING = object(Consts.CLASS_STRING);
    public static final ArgType THROWABLE = object(Consts.CLASS_THROWABLE);
    public static final ArgType UNKNOWN = unknown(PrimitiveType.values());
    public static final ArgType UNKNOWN_OBJECT = unknown(PrimitiveType.OBJECT, PrimitiveType.ARRAY);
    public static final ArgType VOID = primitive(PrimitiveType.VOID);
    public static final ArgType WIDE = unknown(PrimitiveType.LONG, PrimitiveType.DOUBLE);
    protected int hash;

    private static abstract class KnownType extends ArgType {
        private static final PrimitiveType[] EMPTY_POSSIBLES = new PrimitiveType[0];

        private KnownType() {
        }

        public boolean isTypeKnown() {
            return true;
        }

        public boolean contains(PrimitiveType type) {
            return getPrimitiveType() == type;
        }

        public ArgType selectFirst() {
            return null;
        }

        public PrimitiveType[] getPossibleTypes() {
            return EMPTY_POSSIBLES;
        }
    }

    private static final class ArrayArg extends KnownType {
        public static final PrimitiveType[] ARRAY_POSSIBLES = new PrimitiveType[]{PrimitiveType.ARRAY};
        private final ArgType arrayElement;

        public ArrayArg(ArgType arrayElement) {
            super();
            this.arrayElement = arrayElement;
            this.hash = arrayElement.hashCode();
        }

        public ArgType getArrayElement() {
            return this.arrayElement;
        }

        public boolean isArray() {
            return true;
        }

        public PrimitiveType getPrimitiveType() {
            return PrimitiveType.ARRAY;
        }

        public boolean isTypeKnown() {
            return this.arrayElement.isTypeKnown();
        }

        public ArgType selectFirst() {
            return ArgType.array(this.arrayElement.selectFirst());
        }

        public PrimitiveType[] getPossibleTypes() {
            return ARRAY_POSSIBLES;
        }

        public int getArrayDimension() {
            return this.arrayElement.getArrayDimension() + 1;
        }

        public ArgType getArrayRootElement() {
            return this.arrayElement.getArrayRootElement();
        }

        boolean internalEquals(Object obj) {
            return this.arrayElement.equals(((ArrayArg) obj).arrayElement);
        }

        public String toString() {
            return this.arrayElement + "[]";
        }
    }

    private static class ObjectType extends KnownType {
        private final String object;

        public ObjectType(String obj) {
            super();
            this.object = Utils.cleanObjectName(obj);
            this.hash = this.object.hashCode();
        }

        public String getObject() {
            return this.object;
        }

        public boolean isObject() {
            return true;
        }

        public PrimitiveType getPrimitiveType() {
            return PrimitiveType.OBJECT;
        }

        boolean internalEquals(Object obj) {
            return this.object.equals(((ObjectType) obj).object);
        }

        public String toString() {
            return this.object;
        }
    }

    private static class GenericObject extends ObjectType {
        private final ArgType[] generics;
        private final GenericObject outerType;

        public GenericObject(String obj, ArgType[] generics) {
            super(obj);
            this.outerType = null;
            this.generics = generics;
            this.hash = obj.hashCode() + (Arrays.hashCode(generics) * 31);
        }

        public GenericObject(GenericObject outerType, String innerName, ArgType[] generics) {
            super(outerType.getObject() + Deobfuscator.INNER_CLASS_SEPARATOR + innerName);
            this.outerType = outerType;
            this.generics = generics;
            this.hash = (outerType.hashCode() + (innerName.hashCode() * 31)) + (Arrays.hashCode(generics) * 961);
        }

        public boolean isGeneric() {
            return true;
        }

        public ArgType[] getGenericTypes() {
            return this.generics;
        }

        public ArgType getOuterType() {
            return this.outerType;
        }

        boolean internalEquals(Object obj) {
            return super.internalEquals(obj) && Arrays.equals(this.generics, ((GenericObject) obj).generics);
        }

        public String toString() {
            return super.toString() + "<" + Utils.arrayToString(this.generics) + ">";
        }
    }

    private static final class GenericType extends ObjectType {
        public GenericType(String obj) {
            super(obj);
        }

        public boolean isGenericType() {
            return true;
        }
    }

    private static final class PrimitiveArg extends KnownType {
        private final PrimitiveType type;

        public PrimitiveArg(PrimitiveType type) {
            super();
            this.type = type;
            this.hash = type.hashCode();
        }

        public PrimitiveType getPrimitiveType() {
            return this.type;
        }

        public boolean isPrimitive() {
            return true;
        }

        boolean internalEquals(Object obj) {
            return this.type == ((PrimitiveArg) obj).type;
        }

        public String toString() {
            return this.type.toString();
        }
    }

    private static final class UnknownArg extends ArgType {
        private final PrimitiveType[] possibleTypes;

        public UnknownArg(PrimitiveType[] types) {
            this.possibleTypes = types;
            this.hash = Arrays.hashCode(this.possibleTypes);
        }

        public PrimitiveType[] getPossibleTypes() {
            return this.possibleTypes;
        }

        public boolean isTypeKnown() {
            return false;
        }

        public boolean contains(PrimitiveType type) {
            for (PrimitiveType t : this.possibleTypes) {
                if (t == type) {
                    return true;
                }
            }
            return false;
        }

        public ArgType selectFirst() {
            PrimitiveType f = this.possibleTypes[0];
            if (contains(PrimitiveType.OBJECT)) {
                return OBJECT;
            }
            if (contains(PrimitiveType.ARRAY)) {
                return ArgType.array(OBJECT);
            }
            return ArgType.primitive(f);
        }

        boolean internalEquals(Object obj) {
            return Arrays.equals(this.possibleTypes, ((UnknownArg) obj).possibleTypes);
        }

        public String toString() {
            if (this.possibleTypes.length == PrimitiveType.values().length) {
                return "?";
            }
            return "?" + Arrays.toString(this.possibleTypes);
        }
    }

    private static final class WildcardType extends ObjectType {
        private final int bounds;
        private final ArgType type;

        public WildcardType(ArgType obj, int bound) {
            super(OBJECT.getObject());
            this.type = obj;
            this.bounds = bound;
        }

        public boolean isGeneric() {
            return true;
        }

        public ArgType getWildcardType() {
            return this.type;
        }

        public int getWildcardBounds() {
            return this.bounds;
        }

        boolean internalEquals(Object obj) {
            return super.internalEquals(obj) && this.bounds == ((WildcardType) obj).bounds && this.type.equals(((WildcardType) obj).type);
        }

        public String toString() {
            if (this.bounds == 0) {
                return "?";
            }
            return "? " + (this.bounds == -1 ? "super" : "extends") + " " + this.type;
        }
    }

    public abstract boolean contains(PrimitiveType primitiveType);

    public abstract PrimitiveType[] getPossibleTypes();

    abstract boolean internalEquals(Object obj);

    public abstract ArgType selectFirst();

    private static ArgType primitive(PrimitiveType stype) {
        return new PrimitiveArg(stype);
    }

    public static ArgType object(String obj) {
        return new ObjectType(obj);
    }

    public static ArgType genericType(String type) {
        return new GenericType(type);
    }

    public static ArgType wildcard() {
        return new WildcardType(OBJECT, 0);
    }

    public static ArgType wildcard(ArgType obj, int bound) {
        return new WildcardType(obj, bound);
    }

    public static ArgType generic(String sign) {
        return new SignatureParser(sign).consumeType();
    }

    public static ArgType generic(String obj, ArgType[] generics) {
        return new GenericObject(obj, generics);
    }

    public static ArgType genericInner(ArgType genericType, String innerName, ArgType[] generics) {
        return new GenericObject((GenericObject) genericType, innerName, generics);
    }

    public static ArgType array(ArgType vtype) {
        return new ArrayArg(vtype);
    }

    public static ArgType unknown(PrimitiveType... types) {
        return new UnknownArg(types);
    }

    public boolean isTypeKnown() {
        return false;
    }

    public PrimitiveType getPrimitiveType() {
        return null;
    }

    public boolean isPrimitive() {
        return false;
    }

    public String getObject() {
        throw new UnsupportedOperationException("ArgType.getObject(), call class: " + getClass());
    }

    public boolean isObject() {
        return false;
    }

    public boolean isGeneric() {
        return false;
    }

    public boolean isGenericType() {
        return false;
    }

    public ArgType[] getGenericTypes() {
        return null;
    }

    public ArgType getWildcardType() {
        return null;
    }

    public int getWildcardBounds() {
        return 0;
    }

    public ArgType getOuterType() {
        return null;
    }

    public boolean isArray() {
        return false;
    }

    public int getArrayDimension() {
        return 0;
    }

    public ArgType getArrayElement() {
        return null;
    }

    public ArgType getArrayRootElement() {
        return this;
    }

    @Nullable
    public static ArgType merge(@Nullable DexNode dex, ArgType a, ArgType b) {
        if (a == null || b == null) {
            return null;
        }
        if (a.equals(b)) {
            return a;
        }
        ArgType res = mergeInternal(dex, a, b);
        if (res == null) {
            res = mergeInternal(dex, b, a);
        }
        return res;
    }

    private static ArgType mergeInternal(@Nullable DexNode dex, ArgType a, ArgType b) {
        if (a == UNKNOWN) {
            return b;
        }
        if (a.isArray()) {
            return mergeArrays(dex, (ArrayArg) a, b);
        }
        if (b.isArray()) {
            return mergeArrays(dex, (ArrayArg) b, a);
        }
        if (a.isTypeKnown()) {
            if (a.isGenericType()) {
                return a;
            }
            if (b.isGenericType()) {
                return b;
            }
            if (a.isObject() && b.isObject()) {
                String aObj = a.getObject();
                String bObj = b.getObject();
                if (aObj.equals(bObj)) {
                    if (a.getGenericTypes() == null) {
                        a = b;
                    }
                    return a;
                } else if (aObj.equals(Consts.CLASS_OBJECT)) {
                    return b;
                } else {
                    if (bObj.equals(Consts.CLASS_OBJECT)) {
                        return a;
                    }
                    if (dex == null) {
                        return null;
                    }
                    String obj = dex.root().getClsp().getCommonAncestor(aObj, bObj);
                    if (obj != null) {
                        return object(obj);
                    }
                    return null;
                }
            } else if (a.isPrimitive() && b.isPrimitive() && a.getRegCount() == b.getRegCount()) {
                return primitive(PrimitiveType.getSmaller(a.getPrimitiveType(), b.getPrimitiveType()));
            } else {
                return null;
            }
        } else if (!b.isTypeKnown()) {
            List<PrimitiveType> types = new ArrayList();
            for (PrimitiveType type : a.getPossibleTypes()) {
                if (b.contains(type)) {
                    types.add(type);
                }
            }
            if (types.isEmpty()) {
                return null;
            }
            if (types.size() != 1) {
                return unknown((PrimitiveType[]) types.toArray(new PrimitiveType[types.size()]));
            }
            PrimitiveType nt = (PrimitiveType) types.get(0);
            if (nt != PrimitiveType.OBJECT && nt != PrimitiveType.ARRAY) {
                return primitive(nt);
            }
            return unknown(nt);
        } else if (a.contains(b.getPrimitiveType())) {
            return b;
        } else {
            return null;
        }
    }

    private static ArgType mergeArrays(DexNode dex, ArrayArg array, ArgType b) {
        if (b.isArray()) {
            ArgType ea = array.getArrayElement();
            ArgType eb = b.getArrayElement();
            if (ea.isPrimitive() && eb.isPrimitive()) {
                return OBJECT;
            }
            ArgType res = merge(dex, ea, eb);
            if (res != null) {
                return array(res);
            }
            return null;
        } else if (b.contains(PrimitiveType.ARRAY)) {
            return array;
        } else {
            if (b.equals(OBJECT)) {
                return OBJECT;
            }
            return null;
        }
    }

    public static boolean isCastNeeded(DexNode dex, ArgType from, ArgType to) {
        if (from.equals(to)) {
            return false;
        }
        if (from.isObject() && to.isObject() && dex.root().getClsp().isImplements(from.getObject(), to.getObject())) {
            return false;
        }
        return true;
    }

    public static boolean isInstanceOf(DexNode dex, ArgType type, ArgType of) {
        if (type.equals(of)) {
            return true;
        }
        if (type.isObject() && of.isObject()) {
            return dex.root().getClsp().isImplements(type.getObject(), of.getObject());
        }
        return false;
    }

    public static ArgType parse(String type) {
        char f = type.charAt(0);
        switch (f) {
            case 'L':
                return object(type);
            case 'T':
                return genericType(type.substring(1, type.length() - 1));
            case '[':
                return array(parse(type.substring(1)));
            default:
                return parse(f);
        }
    }

    public static ArgType parse(char f) {
        switch (f) {
            case 'B':
                return BYTE;
            case 'C':
                return CHAR;
            case 'D':
                return DOUBLE;
            case 'F':
                return FLOAT;
            case 'I':
                return INT;
            case 'J':
                return LONG;
            case 'S':
                return SHORT;
            case 'V':
                return VOID;
            case 'Z':
                return BOOLEAN;
            default:
                return null;
        }
    }

    public int getRegCount() {
        if (isPrimitive()) {
            PrimitiveType type = getPrimitiveType();
            if (type == PrimitiveType.LONG || type == PrimitiveType.DOUBLE) {
                return 2;
            }
            return 1;
        } else if (isTypeKnown()) {
            return 1;
        } else {
            return 0;
        }
    }

    public String toString() {
        return "ARG_TYPE";
    }

    public int hashCode() {
        return this.hash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && this.hash == obj.hashCode() && getClass() == obj.getClass()) {
            return internalEquals(obj);
        }
        return false;
    }
}
