package jadx.core.codegen;

import jadx.core.Consts;
import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.IAttributeNode;
import jadx.core.dex.attributes.annotations.Annotation;
import jadx.core.dex.attributes.annotations.AnnotationsList;
import jadx.core.dex.attributes.annotations.MethodParameters;
import jadx.core.dex.info.FieldInfo;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.nodes.ClassNode;
import jadx.core.dex.nodes.FieldNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.utils.StringUtils;
import jadx.core.utils.exceptions.JadxRuntimeException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class AnnotationGen {
    private final ClassGen classGen;
    private final ClassNode cls;

    public AnnotationGen(ClassNode cls, ClassGen classGen) {
        this.cls = cls;
        this.classGen = classGen;
    }

    public void addForClass(CodeWriter code) {
        add(this.cls, code);
    }

    public void addForMethod(CodeWriter code, MethodNode mth) {
        add(mth, code);
    }

    public void addForField(CodeWriter code, FieldNode field) {
        add(field, code);
    }

    public void addForParameter(CodeWriter code, MethodParameters paramsAnnotations, int n) {
        List<AnnotationsList> paramList = paramsAnnotations.getParamList();
        if (n < paramList.size()) {
            AnnotationsList aList = (AnnotationsList) paramList.get(n);
            if (aList != null && !aList.isEmpty()) {
                for (Annotation a : aList.getAll()) {
                    formatAnnotation(code, a);
                    code.add(' ');
                }
            }
        }
    }

    private void add(IAttributeNode node, CodeWriter code) {
        AnnotationsList aList = (AnnotationsList) node.get(AType.ANNOTATION_LIST);
        if (aList != null && !aList.isEmpty()) {
            for (Annotation a : aList.getAll()) {
                if (!a.getAnnotationClass().startsWith(Consts.DALVIK_ANNOTATION_PKG)) {
                    code.startLine();
                    formatAnnotation(code, a);
                }
            }
        }
    }

    private void formatAnnotation(CodeWriter code, Annotation a) {
        code.add('@');
        this.classGen.useType(code, a.getType());
        Map<String, Object> vl = a.getValues();
        if (!vl.isEmpty()) {
            code.add('(');
            if (vl.size() == 1 && vl.containsKey("value")) {
                encodeValue(code, vl.get("value"));
            } else {
                Iterator<Entry<String, Object>> it = vl.entrySet().iterator();
                while (it.hasNext()) {
                    Entry<String, Object> e = (Entry) it.next();
                    code.add((String) e.getKey());
                    code.add(" = ");
                    encodeValue(code, e.getValue());
                    if (it.hasNext()) {
                        code.add(", ");
                    }
                }
            }
            code.add(')');
        }
    }

    public void addThrows(MethodNode mth, CodeWriter code) {
        Annotation an = mth.getAnnotation(Consts.DALVIK_THROWS);
        if (an != null) {
            Object exs = an.getDefaultValue();
            code.add(" throws ");
            Iterator<ArgType> it = ((List) exs).iterator();
            while (it.hasNext()) {
                this.classGen.useType(code, (ArgType) it.next());
                if (it.hasNext()) {
                    code.add(", ");
                }
            }
        }
    }

    public Object getAnnotationDefaultValue(String name) {
        Annotation an = this.cls.getAnnotation(Consts.DALVIK_ANNOTATION_DEFAULT);
        if (an != null) {
            return ((Annotation) an.getDefaultValue()).getValues().get(name);
        }
        return null;
    }

    public void encodeValue(CodeWriter code, Object val) {
        if (val == null) {
            code.add("null");
        } else if (val instanceof String) {
            code.add(getStringUtils().unescapeString((String) val));
        } else if (val instanceof Integer) {
            code.add(TypeGen.formatInteger(((Integer) val).intValue()));
        } else if (val instanceof Character) {
            code.add(getStringUtils().unescapeChar(((Character) val).charValue()));
        } else if (val instanceof Boolean) {
            code.add(Boolean.TRUE.equals(val) ? "true" : "false");
        } else if (val instanceof Float) {
            code.add(TypeGen.formatFloat(((Float) val).floatValue()));
        } else if (val instanceof Double) {
            code.add(TypeGen.formatDouble(((Double) val).doubleValue()));
        } else if (val instanceof Long) {
            code.add(TypeGen.formatLong(((Long) val).longValue()));
        } else if (val instanceof Short) {
            code.add(TypeGen.formatShort(((Short) val).shortValue()));
        } else if (val instanceof Byte) {
            code.add(TypeGen.formatByte(((Byte) val).byteValue()));
        } else if (val instanceof ArgType) {
            this.classGen.useType(code, (ArgType) val);
            code.add(".class");
        } else if (val instanceof FieldInfo) {
            InsnGen.makeStaticFieldAccess(code, (FieldInfo) val, this.classGen);
        } else if (val instanceof Iterable) {
            code.add('{');
            Iterator<?> it = ((Iterable) val).iterator();
            while (it.hasNext()) {
                encodeValue(code, it.next());
                if (it.hasNext()) {
                    code.add(", ");
                }
            }
            code.add('}');
        } else if (val instanceof Annotation) {
            formatAnnotation(code, (Annotation) val);
        } else {
            throw new JadxRuntimeException("Can't decode value: " + val + " (" + val.getClass() + ")");
        }
    }

    private StringUtils getStringUtils() {
        return this.cls.dex().root().getStringUtils();
    }
}
