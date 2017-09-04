package jadx.core.dex.nodes;

import com.android.dex.ClassData;
import com.android.dex.ClassData.Field;
import com.android.dex.ClassData.Method;
import com.android.dex.ClassDef;
import jadx.core.Consts;
import jadx.core.codegen.CodeWriter;
import jadx.core.deobf.Deobfuscator;
import jadx.core.dex.attributes.annotations.Annotation;
import jadx.core.dex.attributes.nodes.JadxErrorAttr;
import jadx.core.dex.attributes.nodes.LineAttrNode;
import jadx.core.dex.attributes.nodes.SourceFileAttr;
import jadx.core.dex.info.AccessInfo;
import jadx.core.dex.info.AccessInfo.AFType;
import jadx.core.dex.info.ClassInfo;
import jadx.core.dex.info.FieldInfo;
import jadx.core.dex.info.MethodInfo;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.instructions.args.LiteralArg;
import jadx.core.dex.nodes.parser.AnnotationsParser;
import jadx.core.dex.nodes.parser.FieldInitAttr;
import jadx.core.dex.nodes.parser.SignatureParser;
import jadx.core.dex.nodes.parser.StaticValuesParser;
import jadx.core.utils.exceptions.DecodeException;
import jadx.core.utils.exceptions.JadxRuntimeException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassNode extends LineAttrNode implements ILoadable, IDexNode {
    private static final Logger LOG = LoggerFactory.getLogger(ClassNode.class);
    private final AccessInfo accessFlags;
    private final ClassInfo clsInfo;
    private CodeWriter code;
    private final Set<ClassNode> dependencies = new HashSet();
    private final DexNode dex;
    private final List<FieldNode> fields;
    private Map<ArgType, List<ArgType>> genericMap;
    private List<ClassNode> innerClasses = Collections.emptyList();
    private List<ArgType> interfaces;
    private final List<MethodNode> methods;
    private Map<MethodInfo, MethodNode> mthInfoMap = Collections.emptyMap();
    private ClassNode parentClass;
    private ProcessState state = ProcessState.NOT_LOADED;
    private ArgType superClass;

    public ClassNode(DexNode dex, ClassDef cls) throws DecodeException {
        this.dex = dex;
        this.clsInfo = ClassInfo.fromDex(dex, cls.getTypeIndex());
        try {
            int accFlagsValue;
            if (cls.getSupertypeIndex() == -1) {
                this.superClass = null;
            } else {
                this.superClass = dex.getType(cls.getSupertypeIndex());
            }
            this.interfaces = new ArrayList(cls.getInterfaces().length);
            for (short interfaceIdx : cls.getInterfaces()) {
                this.interfaces.add(dex.getType(interfaceIdx));
            }
            if (cls.getClassDataOffset() != 0) {
                ClassData clsData = dex.readClassData(cls);
                int fieldsCount = clsData.getStaticFields().length + clsData.getInstanceFields().length;
                this.methods = new ArrayList(clsData.getDirectMethods().length + clsData.getVirtualMethods().length);
                this.fields = new ArrayList(fieldsCount);
                for (Method mth : clsData.getDirectMethods()) {
                    this.methods.add(new MethodNode(this, mth, false));
                }
                for (Method mth2 : clsData.getVirtualMethods()) {
                    this.methods.add(new MethodNode(this, mth2, true));
                }
                for (Field f : clsData.getStaticFields()) {
                    this.fields.add(new FieldNode(this, f));
                }
                loadStaticValues(cls, this.fields);
                for (Field f2 : clsData.getInstanceFields()) {
                    this.fields.add(new FieldNode(this, f2));
                }
            } else {
                this.methods = Collections.emptyList();
                this.fields = Collections.emptyList();
            }
            loadAnnotations(cls);
            parseClassSignature();
            setFieldsTypesFromSignature();
            int sfIdx = cls.getSourceFileIndex();
            if (sfIdx != -1) {
                addSourceFilenameAttr(dex.getString(sfIdx));
            }
            Annotation a = getAnnotation(Consts.DALVIK_INNER_CLASS);
            if (a != null) {
                accFlagsValue = ((Integer) a.getValues().get("accessFlags")).intValue();
            } else {
                accFlagsValue = cls.getAccessFlags();
            }
            this.accessFlags = new AccessInfo(accFlagsValue, AFType.CLASS);
            buildCache();
        } catch (Throwable e) {
            throw new DecodeException("Error decode class: " + this.clsInfo, e);
        }
    }

    public ClassNode(DexNode dex, ClassInfo clsInfo) {
        this.dex = dex;
        this.clsInfo = clsInfo;
        this.interfaces = Collections.emptyList();
        this.methods = Collections.emptyList();
        this.fields = Collections.emptyList();
        this.accessFlags = new AccessInfo(4097, AFType.CLASS);
        this.parentClass = this;
    }

    private void loadAnnotations(ClassDef cls) {
        int offset = cls.getAnnotationsOffset();
        if (offset != 0) {
            try {
                new AnnotationsParser(this).parse(offset);
            } catch (Exception e) {
                LOG.error("Error parsing annotations in {}", this, e);
            }
        }
    }

    private void loadStaticValues(ClassDef cls, List<FieldNode> staticFields) throws DecodeException {
        for (FieldNode f : staticFields) {
            if (f.getAccessFlags().isFinal()) {
                f.addAttr(FieldInitAttr.NULL_VALUE);
            }
        }
        int offset = cls.getStaticValuesOffset();
        if (offset != 0) {
            new StaticValuesParser(this.dex, this.dex.openSection(offset)).processFields(staticFields);
            root().getConstValues().processConstFields(this, staticFields);
        }
    }

    private void parseClassSignature() {
        SignatureParser sp = SignatureParser.fromNode(this);
        if (sp != null) {
            try {
                this.genericMap = sp.consumeGenericMap();
                this.superClass = sp.consumeType();
                int i = 0;
                while (i < this.interfaces.size()) {
                    ArgType type = sp.consumeType();
                    if (type != null) {
                        this.interfaces.set(i, type);
                        i++;
                    } else {
                        return;
                    }
                }
            } catch (JadxRuntimeException e) {
                LOG.error("Class signature parse error: {}", this, e);
            }
        }
    }

    private void setFieldsTypesFromSignature() {
        for (FieldNode field : this.fields) {
            SignatureParser sp = SignatureParser.fromNode(field);
            if (sp != null) {
                try {
                    ArgType gType = sp.consumeType();
                    if (gType != null) {
                        field.setType(gType);
                    }
                } catch (JadxRuntimeException e) {
                    LOG.error("Field signature parse error: {}", field, e);
                }
            }
        }
    }

    private void addSourceFilenameAttr(String fileName) {
        if (fileName != null) {
            if (fileName.endsWith(".java")) {
                fileName = fileName.substring(0, fileName.length() - 5);
            }
            if (!fileName.isEmpty() && !fileName.equals("SourceFile") && !fileName.equals("\"")) {
                if (this.clsInfo != null) {
                    String name = this.clsInfo.getShortName();
                    if (!fileName.equals(name)) {
                        if (!fileName.contains(Deobfuscator.INNER_CLASS_SEPARATOR) || !fileName.endsWith(Deobfuscator.INNER_CLASS_SEPARATOR + name)) {
                            ClassInfo parentClass = this.clsInfo.getTopParentClass();
                            if (parentClass != null && fileName.equals(parentClass.getShortName())) {
                                return;
                            }
                        }
                        return;
                    }
                    return;
                }
                addAttr(new SourceFileAttr(fileName));
                LOG.debug("Class '{}' compiled from '{}'", this, fileName);
            }
        }
    }

    public void load() {
        for (MethodNode mth : getMethods()) {
            try {
                mth.load();
            } catch (Exception e) {
                LOG.error("Method load error: {}", mth, e);
                mth.addAttr(new JadxErrorAttr(e));
            }
        }
        for (ClassNode innerCls : getInnerClasses()) {
            innerCls.load();
        }
    }

    public void unload() {
        for (MethodNode mth : getMethods()) {
            mth.unload();
        }
        for (ClassNode innerCls : getInnerClasses()) {
            innerCls.unload();
        }
    }

    private void buildCache() {
        this.mthInfoMap = new HashMap(this.methods.size());
        for (MethodNode mth : this.methods) {
            this.mthInfoMap.put(mth.getMethodInfo(), mth);
        }
    }

    @Nullable
    public ArgType getSuperClass() {
        return this.superClass;
    }

    public List<ArgType> getInterfaces() {
        return this.interfaces;
    }

    public Map<ArgType, List<ArgType>> getGenericMap() {
        return this.genericMap;
    }

    public List<MethodNode> getMethods() {
        return this.methods;
    }

    public List<FieldNode> getFields() {
        return this.fields;
    }

    public FieldNode getConstField(Object obj) {
        return getConstField(obj, true);
    }

    @Nullable
    public FieldNode getConstField(Object obj, boolean searchGlobal) {
        return root().getConstValues().getConstField(this, obj, searchGlobal);
    }

    @Nullable
    public FieldNode getConstFieldByLiteralArg(LiteralArg arg) {
        return root().getConstValues().getConstFieldByLiteralArg(this, arg);
    }

    public FieldNode searchFieldById(int id) {
        return searchField(FieldInfo.fromDex(this.dex, id));
    }

    public FieldNode searchField(FieldInfo field) {
        for (FieldNode f : this.fields) {
            if (f.getFieldInfo().equals(field)) {
                return f;
            }
        }
        return null;
    }

    public FieldNode searchFieldByName(String name) {
        for (FieldNode f : this.fields) {
            if (f.getName().equals(name)) {
                return f;
            }
        }
        return null;
    }

    public MethodNode searchMethod(MethodInfo mth) {
        return (MethodNode) this.mthInfoMap.get(mth);
    }

    public MethodNode searchMethodByName(String shortId) {
        for (MethodNode m : this.methods) {
            if (m.getMethodInfo().getShortId().equals(shortId)) {
                return m;
            }
        }
        return null;
    }

    public MethodNode searchMethodById(int id) {
        return searchMethodByName(MethodInfo.fromDex(this.dex, id).getShortId());
    }

    public ClassNode getParentClass() {
        if (this.parentClass == null) {
            if (this.clsInfo.isInner()) {
                ClassNode parent = dex().resolveClass(this.clsInfo.getParentClass());
                if (parent == null) {
                    parent = this;
                }
                this.parentClass = parent;
            } else {
                this.parentClass = this;
            }
        }
        return this.parentClass;
    }

    public ClassNode getTopParentClass() {
        ClassNode parent = getParentClass();
        return parent == this ? this : parent.getParentClass();
    }

    public List<ClassNode> getInnerClasses() {
        return this.innerClasses;
    }

    public void addInnerClass(ClassNode cls) {
        if (this.innerClasses.isEmpty()) {
            this.innerClasses = new ArrayList(3);
        }
        this.innerClasses.add(cls);
    }

    public boolean isEnum() {
        return getAccessFlags().isEnum() && getSuperClass() != null && getSuperClass().getObject().equals(ArgType.ENUM.getObject());
    }

    public boolean isAnonymous() {
        return this.clsInfo.isInner() && this.clsInfo.getAlias().getShortName().startsWith(Consts.ANONYMOUS_CLASS_PREFIX) && getDefaultConstructor() != null;
    }

    @Nullable
    public MethodNode getClassInitMth() {
        return searchMethodByName("<clinit>()V");
    }

    @Nullable
    public MethodNode getDefaultConstructor() {
        for (MethodNode mth : this.methods) {
            if (mth.isDefaultConstructor()) {
                return mth;
            }
        }
        return null;
    }

    public AccessInfo getAccessFlags() {
        return this.accessFlags;
    }

    public DexNode dex() {
        return this.dex;
    }

    public RootNode root() {
        return this.dex.root();
    }

    public String getRawName() {
        return this.clsInfo.getRawName();
    }

    public ClassInfo getClassInfo() {
        return this.clsInfo;
    }

    public ClassInfo getAlias() {
        return this.clsInfo.getAlias();
    }

    public String getShortName() {
        return this.clsInfo.getAlias().getShortName();
    }

    public String getFullName() {
        return this.clsInfo.getAlias().getFullName();
    }

    public String getPackage() {
        return this.clsInfo.getAlias().getPackage();
    }

    public void setCode(CodeWriter code) {
        this.code = code;
    }

    public CodeWriter getCode() {
        return this.code;
    }

    public ProcessState getState() {
        return this.state;
    }

    public void setState(ProcessState state) {
        this.state = state;
    }

    public Set<ClassNode> getDependencies() {
        return this.dependencies;
    }

    public int hashCode() {
        return this.clsInfo.hashCode();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClassNode)) {
            return false;
        }
        return this.clsInfo.equals(((ClassNode) o).clsInfo);
    }

    public String toString() {
        return this.clsInfo.getFullName();
    }
}
