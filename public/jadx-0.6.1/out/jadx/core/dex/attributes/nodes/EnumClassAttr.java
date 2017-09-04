package jadx.core.dex.attributes.nodes;

import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.IAttribute;
import jadx.core.dex.info.FieldInfo;
import jadx.core.dex.instructions.mods.ConstructorInsn;
import jadx.core.dex.nodes.ClassNode;
import jadx.core.dex.nodes.MethodNode;
import java.util.ArrayList;
import java.util.List;

public class EnumClassAttr implements IAttribute {
    private final List<EnumField> fields;
    private MethodNode staticMethod;

    public static class EnumField {
        private ClassNode cls;
        private final ConstructorInsn constrInsn;
        private final FieldInfo field;
        private final int startArg;

        public EnumField(FieldInfo field, ConstructorInsn co, int startArg) {
            this.field = field;
            this.constrInsn = co;
            this.startArg = startArg;
        }

        public FieldInfo getField() {
            return this.field;
        }

        public ConstructorInsn getConstrInsn() {
            return this.constrInsn;
        }

        public int getStartArg() {
            return this.startArg;
        }

        public ClassNode getCls() {
            return this.cls;
        }

        public void setCls(ClassNode cls) {
            this.cls = cls;
        }

        public String toString() {
            return this.field + "(" + this.constrInsn + ") " + this.cls;
        }
    }

    public EnumClassAttr(int fieldsCount) {
        this.fields = new ArrayList(fieldsCount);
    }

    public List<EnumField> getFields() {
        return this.fields;
    }

    public MethodNode getStaticMethod() {
        return this.staticMethod;
    }

    public void setStaticMethod(MethodNode staticMethod) {
        this.staticMethod = staticMethod;
    }

    public AType<EnumClassAttr> getType() {
        return AType.ENUM_CLASS;
    }

    public String toString() {
        return "Enum fields: " + this.fields;
    }
}
