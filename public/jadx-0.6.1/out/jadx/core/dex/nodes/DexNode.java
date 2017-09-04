package jadx.core.dex.nodes;

import com.android.dex.ClassData;
import com.android.dex.ClassData.Method;
import com.android.dex.ClassDef;
import com.android.dex.Code;
import com.android.dex.Dex;
import com.android.dex.Dex.Section;
import com.android.dex.FieldId;
import com.android.dex.MethodId;
import com.android.dex.ProtoId;
import com.android.dex.TypeList;
import jadx.core.dex.info.ClassInfo;
import jadx.core.dex.info.FieldInfo;
import jadx.core.dex.info.InfoStorage;
import jadx.core.dex.info.MethodInfo;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.utils.exceptions.DecodeException;
import jadx.core.utils.files.DexFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DexNode implements IDexNode {
    public static final int NO_INDEX = -1;
    private final List<ClassNode> classes = new ArrayList();
    private final Map<ClassInfo, ClassNode> clsMap = new HashMap();
    private final Dex dexBuf;
    private final DexFile file;
    private final InfoStorage infoStorage = new InfoStorage();
    private final RootNode root;

    public DexNode(RootNode root, DexFile input) {
        this.root = root;
        this.file = input;
        this.dexBuf = input.getDexBuf();
    }

    public void loadClasses() throws DecodeException {
        for (ClassDef cls : this.dexBuf.classDefs()) {
            ClassNode clsNode = new ClassNode(this, cls);
            this.classes.add(clsNode);
            this.clsMap.put(clsNode.getClassInfo(), clsNode);
        }
    }

    void initInnerClasses() {
        List<ClassNode> inner = new ArrayList();
        for (ClassNode cls : this.classes) {
            if (cls.getClassInfo().isInner()) {
                inner.add(cls);
            }
        }
        for (ClassNode cls2 : inner) {
            ClassInfo clsInfo = cls2.getClassInfo();
            ClassNode parent = resolveClass(clsInfo.getParentClass());
            if (parent == null) {
                this.clsMap.remove(clsInfo);
                clsInfo.notInner(cls2.dex());
                this.clsMap.put(clsInfo, cls2);
            } else {
                parent.addInnerClass(cls2);
            }
        }
    }

    public List<ClassNode> getClasses() {
        return this.classes;
    }

    @Nullable
    public ClassNode resolveClass(ClassInfo clsInfo) {
        return (ClassNode) this.clsMap.get(clsInfo);
    }

    @Nullable
    public ClassNode resolveClass(@NotNull ArgType type) {
        if (type.isGeneric()) {
            type = ArgType.object(type.getObject());
        }
        return resolveClass(ClassInfo.fromType(this, type));
    }

    @Nullable
    public MethodNode resolveMethod(@NotNull MethodInfo mth) {
        ClassNode cls = resolveClass(mth.getDeclClass());
        if (cls != null) {
            return cls.searchMethod(mth);
        }
        return null;
    }

    @Nullable
    public MethodNode deepResolveMethod(@NotNull MethodInfo mth) {
        ClassNode cls = resolveClass(mth.getDeclClass());
        if (cls == null) {
            return null;
        }
        return deepResolveMethod(cls, mth.makeSignature(false));
    }

    @Nullable
    private MethodNode deepResolveMethod(@NotNull ClassNode cls, String signature) {
        MethodNode found;
        for (MethodNode m : cls.getMethods()) {
            if (m.getMethodInfo().getShortId().startsWith(signature)) {
                return m;
            }
        }
        ArgType superClass = cls.getSuperClass();
        if (superClass != null) {
            ClassNode superNode = resolveClass(superClass);
            if (superNode != null) {
                found = deepResolveMethod(superNode, signature);
                if (found != null) {
                    return found;
                }
            }
        }
        for (ArgType iFaceType : cls.getInterfaces()) {
            ClassNode iFaceNode = resolveClass(iFaceType);
            if (iFaceNode != null) {
                found = deepResolveMethod(iFaceNode, signature);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    @Nullable
    public FieldNode resolveField(FieldInfo field) {
        ClassNode cls = resolveClass(field.getDeclClass());
        if (cls != null) {
            return cls.searchField(field);
        }
        return null;
    }

    public InfoStorage getInfoStorage() {
        return this.infoStorage;
    }

    public DexFile getDexFile() {
        return this.file;
    }

    public String getString(int index) {
        return (String) this.dexBuf.strings().get(index);
    }

    public ArgType getType(int index) {
        return ArgType.parse(getString(((Integer) this.dexBuf.typeIds().get(index)).intValue()));
    }

    public MethodId getMethodId(int mthIndex) {
        return (MethodId) this.dexBuf.methodIds().get(mthIndex);
    }

    public FieldId getFieldId(int fieldIndex) {
        return (FieldId) this.dexBuf.fieldIds().get(fieldIndex);
    }

    public ProtoId getProtoId(int protoIndex) {
        return (ProtoId) this.dexBuf.protoIds().get(protoIndex);
    }

    public ClassData readClassData(ClassDef cls) {
        return this.dexBuf.readClassData(cls);
    }

    public List<ArgType> readParamList(int parametersOffset) {
        TypeList paramList = this.dexBuf.readTypeList(parametersOffset);
        List<ArgType> args = new ArrayList(paramList.getTypes().length);
        for (short t : paramList.getTypes()) {
            args.add(getType(t));
        }
        return Collections.unmodifiableList(args);
    }

    public Code readCode(Method mth) {
        return this.dexBuf.readCode(mth);
    }

    public Section openSection(int offset) {
        return this.dexBuf.open(offset);
    }

    public RootNode root() {
        return this.root;
    }

    public DexNode dex() {
        return this;
    }

    public String toString() {
        return "DEX";
    }
}
