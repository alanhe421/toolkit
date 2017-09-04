package jadx.core.dex.attributes.nodes;

import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.IAttribute;
import jadx.core.dex.info.ClassInfo;
import jadx.core.dex.instructions.args.InsnArg;

public class FieldReplaceAttr implements IAttribute {
    private final Object replaceObj;
    private final ReplaceWith replaceType;

    public enum ReplaceWith {
        CLASS_INSTANCE,
        VAR
    }

    public FieldReplaceAttr(ClassInfo cls) {
        this.replaceType = ReplaceWith.CLASS_INSTANCE;
        this.replaceObj = cls;
    }

    public FieldReplaceAttr(InsnArg reg) {
        this.replaceType = ReplaceWith.VAR;
        this.replaceObj = reg;
    }

    public ReplaceWith getReplaceType() {
        return this.replaceType;
    }

    public ClassInfo getClsRef() {
        return (ClassInfo) this.replaceObj;
    }

    public InsnArg getVarRef() {
        return (InsnArg) this.replaceObj;
    }

    public AType<FieldReplaceAttr> getType() {
        return AType.FIELD_REPLACE;
    }

    public String toString() {
        return "REPLACE: " + this.replaceType + " " + this.replaceObj;
    }
}
