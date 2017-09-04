package jadx.api;

import jadx.core.codegen.CodeWriter;
import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.attributes.nodes.LineAttrNode;
import jadx.core.dex.info.AccessInfo;
import jadx.core.dex.nodes.ClassNode;
import jadx.core.dex.nodes.FieldNode;
import jadx.core.dex.nodes.MethodNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.jetbrains.annotations.Nullable;

public final class JavaClass implements JavaNode {
    private final ClassNode cls;
    private final JadxDecompiler decompiler;
    private List<JavaField> fields;
    private List<JavaClass> innerClasses;
    private List<JavaMethod> methods;
    private final JavaClass parent;

    JavaClass(ClassNode classNode, JadxDecompiler decompiler) {
        this.innerClasses = Collections.emptyList();
        this.fields = Collections.emptyList();
        this.methods = Collections.emptyList();
        this.decompiler = decompiler;
        this.cls = classNode;
        this.parent = null;
    }

    JavaClass(ClassNode classNode, JavaClass parent) {
        this.innerClasses = Collections.emptyList();
        this.fields = Collections.emptyList();
        this.methods = Collections.emptyList();
        this.decompiler = null;
        this.cls = classNode;
        this.parent = parent;
    }

    public String getCode() {
        CodeWriter code = this.cls.getCode();
        if (code == null) {
            decompile();
            code = this.cls.getCode();
            if (code == null) {
                return "";
            }
        }
        return code.getCodeStr();
    }

    public synchronized void decompile() {
        if (this.decompiler != null) {
            if (this.cls.getCode() == null) {
                this.decompiler.processClass(this.cls);
                load();
            }
        }
    }

    ClassNode getClassNode() {
        return this.cls;
    }

    private void load() {
        JadxDecompiler rootDecompiler = getRootDecompiler();
        int inClsCount = this.cls.getInnerClasses().size();
        if (inClsCount != 0) {
            List<JavaClass> list = new ArrayList(inClsCount);
            for (ClassNode inner : this.cls.getInnerClasses()) {
                if (!inner.contains(AFlag.DONT_GENERATE)) {
                    JavaClass javaClass = new JavaClass(inner, this);
                    javaClass.load();
                    list.add(javaClass);
                    rootDecompiler.getClassesMap().put(inner, javaClass);
                }
            }
            this.innerClasses = Collections.unmodifiableList(list);
        }
        int fieldsCount = this.cls.getFields().size();
        if (fieldsCount != 0) {
            List<JavaField> flds = new ArrayList(fieldsCount);
            for (FieldNode f : this.cls.getFields()) {
                if (!f.contains(AFlag.DONT_GENERATE)) {
                    JavaField javaField = new JavaField(f, this);
                    flds.add(javaField);
                    rootDecompiler.getFieldsMap().put(f, javaField);
                }
            }
            this.fields = Collections.unmodifiableList(flds);
        }
        int methodsCount = this.cls.getMethods().size();
        if (methodsCount != 0) {
            List<JavaMethod> mths = new ArrayList(methodsCount);
            for (MethodNode m : this.cls.getMethods()) {
                if (!m.contains(AFlag.DONT_GENERATE)) {
                    JavaMethod javaMethod = new JavaMethod(this, m);
                    mths.add(javaMethod);
                    rootDecompiler.getMethodsMap().put(m, javaMethod);
                }
            }
            Collections.sort(mths, new Comparator<JavaMethod>() {
                public int compare(JavaMethod o1, JavaMethod o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
            this.methods = Collections.unmodifiableList(mths);
        }
    }

    private JadxDecompiler getRootDecompiler() {
        if (this.parent != null) {
            return this.parent.getRootDecompiler();
        }
        return this.decompiler;
    }

    private Map<CodePosition, Object> getCodeAnnotations() {
        decompile();
        return this.cls.getCode().getAnnotations();
    }

    public Map<CodePosition, JavaNode> getUsageMap() {
        Map<CodePosition, Object> map = getCodeAnnotations();
        if (map.isEmpty() || this.decompiler == null) {
            return Collections.emptyMap();
        }
        Map<CodePosition, JavaNode> resultMap = new HashMap(map.size());
        for (Entry<CodePosition, Object> entry : map.entrySet()) {
            CodePosition codePosition = (CodePosition) entry.getKey();
            Object obj = entry.getValue();
            if (obj instanceof LineAttrNode) {
                JavaNode node = convertNode(obj);
                if (node != null) {
                    resultMap.put(codePosition, node);
                }
            }
        }
        return resultMap;
    }

    @Nullable
    private JavaNode convertNode(Object obj) {
        if (!(obj instanceof LineAttrNode)) {
            return null;
        }
        if (obj instanceof ClassNode) {
            return (JavaNode) getRootDecompiler().getClassesMap().get(obj);
        }
        if (obj instanceof MethodNode) {
            return (JavaNode) getRootDecompiler().getMethodsMap().get(obj);
        }
        if (obj instanceof FieldNode) {
            return (JavaNode) getRootDecompiler().getFieldsMap().get(obj);
        }
        return null;
    }

    @Nullable
    public JavaNode getJavaNodeAtPosition(int line, int offset) {
        Map<CodePosition, Object> map = getCodeAnnotations();
        if (map.isEmpty()) {
            return null;
        }
        Object obj = map.get(new CodePosition(line, offset));
        if (obj != null) {
            return convertNode(obj);
        }
        return null;
    }

    @Nullable
    public CodePosition getDefinitionPosition(int line, int offset) {
        JavaNode javaNode = getJavaNodeAtPosition(line, offset);
        if (javaNode == null) {
            return null;
        }
        return getDefinitionPosition(javaNode);
    }

    @Nullable
    public CodePosition getDefinitionPosition(JavaNode javaNode) {
        JavaClass jCls = javaNode.getTopParentClass();
        jCls.decompile();
        int defLine = javaNode.getDecompiledLine();
        if (defLine == 0) {
            return null;
        }
        return new CodePosition(jCls, defLine, 0);
    }

    public Integer getSourceLine(int decompiledLine) {
        decompile();
        return (Integer) this.cls.getCode().getLineMapping().get(Integer.valueOf(decompiledLine));
    }

    public String getName() {
        return this.cls.getShortName();
    }

    public String getFullName() {
        return this.cls.getFullName();
    }

    public String getPackage() {
        return this.cls.getPackage();
    }

    public JavaClass getDeclaringClass() {
        return this.parent;
    }

    public JavaClass getTopParentClass() {
        return this.parent == null ? this : this.parent.getTopParentClass();
    }

    public AccessInfo getAccessInfo() {
        return this.cls.getAccessFlags();
    }

    public List<JavaClass> getInnerClasses() {
        decompile();
        return this.innerClasses;
    }

    public List<JavaField> getFields() {
        decompile();
        return this.fields;
    }

    public List<JavaMethod> getMethods() {
        decompile();
        return this.methods;
    }

    public int getDecompiledLine() {
        return this.cls.getDecompiledLine();
    }

    public boolean equals(Object o) {
        return this == o || ((o instanceof JavaClass) && this.cls.equals(((JavaClass) o).cls));
    }

    public int hashCode() {
        return this.cls.hashCode();
    }

    public String toString() {
        return this.cls.getFullName() + "[ " + getFullName() + " ]";
    }
}
