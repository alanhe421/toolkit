package jadx.core.dex.info;

import jadx.api.IJadxArgs;
import jadx.core.dex.attributes.AType;
import jadx.core.dex.instructions.args.LiteralArg;
import jadx.core.dex.instructions.args.PrimitiveType;
import jadx.core.dex.nodes.ClassNode;
import jadx.core.dex.nodes.DexNode;
import jadx.core.dex.nodes.FieldNode;
import jadx.core.dex.nodes.ResRefField;
import jadx.core.dex.nodes.parser.FieldInitAttr;
import jadx.core.dex.nodes.parser.FieldInitAttr.InitType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.Nullable;

public class ConstStorage {
    private final Map<ClassNode, Values> classes = new HashMap();
    private final Values globalValues = new Values();
    private final boolean replaceEnabled;
    private Map<Integer, String> resourcesNames = new HashMap();

    private static final class Values {
        private final Set<Object> duplicates;
        private final Map<Object, FieldNode> values;

        private Values() {
            this.values = new HashMap();
            this.duplicates = new HashSet();
        }

        public Map<Object, FieldNode> getValues() {
            return this.values;
        }

        public FieldNode get(Object key) {
            return (FieldNode) this.values.get(key);
        }

        public boolean put(Object value, FieldNode fld) {
            if (((FieldNode) this.values.put(value, fld)) != null) {
                this.values.remove(value);
                this.duplicates.add(value);
                return true;
            } else if (!this.duplicates.contains(value)) {
                return false;
            } else {
                this.values.remove(value);
                return true;
            }
        }

        public boolean contains(Object value) {
            return this.duplicates.contains(value) || this.values.containsKey(value);
        }
    }

    public ConstStorage(IJadxArgs args) {
        this.replaceEnabled = args.isReplaceConsts();
    }

    public void processConstFields(ClassNode cls, List<FieldNode> staticFields) {
        if (this.replaceEnabled && !staticFields.isEmpty()) {
            for (FieldNode f : staticFields) {
                AccessInfo accFlags = f.getAccessFlags();
                if (accFlags.isStatic() && accFlags.isFinal()) {
                    FieldInitAttr fv = (FieldInitAttr) f.get(AType.FIELD_INIT);
                    if (!(fv == null || fv.getValue() == null || fv.getValueType() != InitType.CONST || fv == FieldInitAttr.NULL_VALUE)) {
                        addConstField(cls, f, fv.getValue(), accFlags.isPublic());
                    }
                }
            }
        }
    }

    private void addConstField(ClassNode cls, FieldNode fld, Object value, boolean isPublic) {
        if (isPublic) {
            this.globalValues.put(value, fld);
        } else {
            getClsValues(cls).put(value, fld);
        }
    }

    private Values getClsValues(ClassNode cls) {
        Values classValues = (Values) this.classes.get(cls);
        if (classValues != null) {
            return classValues;
        }
        classValues = new Values();
        this.classes.put(cls, classValues);
        return classValues;
    }

    @Nullable
    public FieldNode getConstField(ClassNode cls, Object value, boolean searchGlobal) {
        DexNode dex = cls.dex();
        if (value instanceof Integer) {
            String str = (String) this.resourcesNames.get(value);
            if (str != null) {
                return new ResRefField(dex, str.replace('/', '.'));
            }
        }
        if (!this.replaceEnabled) {
            return null;
        }
        boolean foundInGlobal = this.globalValues.contains(value);
        if (foundInGlobal && !searchGlobal) {
            return null;
        }
        ClassNode current = cls;
        while (current != null) {
            Values classValues = (Values) this.classes.get(current);
            if (classValues != null) {
                FieldNode field = classValues.get(value);
                if (field != null) {
                    if (foundInGlobal) {
                        return null;
                    }
                    return field;
                }
            }
            ClassInfo parentClass = current.getClassInfo().getParentClass();
            if (parentClass == null) {
                break;
            }
            current = dex.resolveClass(parentClass);
        }
        if (searchGlobal) {
            return this.globalValues.get(value);
        }
        return null;
    }

    @Nullable
    public FieldNode getConstFieldByLiteralArg(ClassNode cls, LiteralArg arg) {
        PrimitiveType type = arg.getType().getPrimitiveType();
        if (type == null) {
            return null;
        }
        long literal = arg.getLiteral();
        switch (type) {
            case BOOLEAN:
                return getConstField(cls, Boolean.valueOf(literal == 1), false);
            case CHAR:
                return getConstField(cls, Character.valueOf((char) ((int) literal)), Math.abs(literal) > 10);
            case BYTE:
                return getConstField(cls, Byte.valueOf((byte) ((int) literal)), Math.abs(literal) > 10);
            case SHORT:
                return getConstField(cls, Short.valueOf((short) ((int) literal)), Math.abs(literal) > 100);
            case INT:
                return getConstField(cls, Integer.valueOf((int) literal), Math.abs(literal) > 100);
            case LONG:
                return getConstField(cls, Long.valueOf(literal), Math.abs(literal) > 1000);
            case FLOAT:
                float f = Float.intBitsToFloat((int) literal);
                return getConstField(cls, Float.valueOf(f), ((double) f) != 0.0d);
            case DOUBLE:
                double d = Double.longBitsToDouble(literal);
                return getConstField(cls, Double.valueOf(d), d != 0.0d);
            default:
                return null;
        }
    }

    public void setResourcesNames(Map<Integer, String> resourcesNames) {
        this.resourcesNames = resourcesNames;
    }

    public Map<Integer, String> getResourcesNames() {
        return this.resourcesNames;
    }

    public Map<Object, FieldNode> getGlobalConstFields() {
        return this.globalValues.getValues();
    }

    public boolean isReplaceEnabled() {
        return this.replaceEnabled;
    }
}
