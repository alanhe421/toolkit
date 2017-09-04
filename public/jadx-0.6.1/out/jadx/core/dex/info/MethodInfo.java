package jadx.core.dex.info;

import com.android.dex.MethodId;
import com.android.dex.ProtoId;
import jadx.core.codegen.TypeGen;
import jadx.core.deobf.Deobfuscator;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.nodes.DexNode;
import jadx.core.utils.Utils;
import java.util.List;

public final class MethodInfo {
    private String alias = this.name;
    private boolean aliasFromPreset = false;
    private final List<ArgType> args;
    private final ClassInfo declClass;
    private final String name;
    private final ArgType retType;
    private final String shortId;

    private MethodInfo(DexNode dex, int mthIndex) {
        MethodId mthId = dex.getMethodId(mthIndex);
        this.name = dex.getString(mthId.getNameIndex());
        this.declClass = ClassInfo.fromDex(dex, mthId.getDeclaringClassIndex());
        ProtoId proto = dex.getProtoId(mthId.getProtoIndex());
        this.retType = dex.getType(proto.getReturnTypeIndex());
        this.args = dex.readParamList(proto.getParametersOffset());
        this.shortId = makeSignature(true);
    }

    public static MethodInfo fromDex(DexNode dex, int mthIndex) {
        MethodInfo mth = dex.getInfoStorage().getMethod(mthIndex);
        if (mth != null) {
            return mth;
        }
        return dex.getInfoStorage().putMethod(mthIndex, new MethodInfo(dex, mthIndex));
    }

    public String makeSignature(boolean includeRetType) {
        StringBuilder signature = new StringBuilder();
        signature.append(this.name);
        signature.append('(');
        for (ArgType arg : this.args) {
            signature.append(TypeGen.signature(arg));
        }
        signature.append(')');
        if (includeRetType) {
            signature.append(TypeGen.signature(this.retType));
        }
        return signature.toString();
    }

    public String getName() {
        return this.name;
    }

    public String getFullName() {
        return this.declClass.getFullName() + Deobfuscator.CLASS_NAME_SEPARATOR + this.name;
    }

    public String getFullId() {
        return this.declClass.getFullName() + Deobfuscator.CLASS_NAME_SEPARATOR + this.shortId;
    }

    public String getShortId() {
        return this.shortId;
    }

    public ClassInfo getDeclClass() {
        return this.declClass;
    }

    public ArgType getReturnType() {
        return this.retType;
    }

    public List<ArgType> getArgumentsTypes() {
        return this.args;
    }

    public int getArgsCount() {
        return this.args.size();
    }

    public boolean isConstructor() {
        return this.name.equals("<init>");
    }

    public boolean isClassInit() {
        return this.name.equals("<clinit>");
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public boolean isRenamed() {
        return !this.name.equals(this.alias);
    }

    public boolean isAliasFromPreset() {
        return this.aliasFromPreset;
    }

    public void setAliasFromPreset(boolean value) {
        this.aliasFromPreset = value;
    }

    public int hashCode() {
        return (((this.declClass.hashCode() * 31) + this.retType.hashCode()) * 31) + this.shortId.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MethodInfo)) {
            return false;
        }
        MethodInfo other = (MethodInfo) obj;
        if (this.shortId.equals(other.shortId) && this.retType.equals(other.retType) && this.declClass.equals(other.declClass)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return this.declClass.getFullName() + Deobfuscator.CLASS_NAME_SEPARATOR + this.name + "(" + Utils.listToString(this.args) + "):" + this.retType;
    }
}
