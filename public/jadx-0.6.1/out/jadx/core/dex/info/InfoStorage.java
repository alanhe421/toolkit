package jadx.core.dex.info;

import jadx.core.dex.instructions.args.ArgType;
import java.util.HashMap;
import java.util.Map;

public class InfoStorage {
    private final Map<ArgType, ClassInfo> classes = new HashMap();
    private final Map<FieldInfo, FieldInfo> fields = new HashMap();
    private final Map<Integer, MethodInfo> methods = new HashMap();

    public ClassInfo getCls(ArgType type) {
        return (ClassInfo) this.classes.get(type);
    }

    public ClassInfo putCls(ClassInfo cls) {
        synchronized (this.classes) {
            ClassInfo prev = (ClassInfo) this.classes.put(cls.getType(), cls);
            if (prev != null) {
                cls = prev;
            }
        }
        return cls;
    }

    public MethodInfo getMethod(int mtdId) {
        return (MethodInfo) this.methods.get(Integer.valueOf(mtdId));
    }

    public MethodInfo putMethod(int mthId, MethodInfo mth) {
        synchronized (this.methods) {
            MethodInfo prev = (MethodInfo) this.methods.put(Integer.valueOf(mthId), mth);
            if (prev != null) {
                mth = prev;
            }
        }
        return mth;
    }

    public FieldInfo getField(FieldInfo field) {
        synchronized (this.fields) {
            FieldInfo f = (FieldInfo) this.fields.get(field);
            if (f != null) {
                return f;
            }
            this.fields.put(field, field);
            return field;
        }
    }
}
