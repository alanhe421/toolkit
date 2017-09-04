package jadx.core.dex.attributes.annotations;

import jadx.core.dex.instructions.args.ArgType;
import java.util.Map;

public class Annotation {
    private final ArgType atype;
    private final Map<String, Object> values;
    private final Visibility visibility;

    public enum Visibility {
        BUILD,
        RUNTIME,
        SYSTEM
    }

    public Annotation(Visibility visibility, ArgType type, Map<String, Object> values) {
        this.visibility = visibility;
        this.atype = type;
        this.values = values;
    }

    public Visibility getVisibility() {
        return this.visibility;
    }

    public ArgType getType() {
        return this.atype;
    }

    public String getAnnotationClass() {
        return this.atype.getObject();
    }

    public Map<String, Object> getValues() {
        return this.values;
    }

    public Object getDefaultValue() {
        return this.values.get("value");
    }

    public String toString() {
        return "Annotation[" + this.visibility + ", " + this.atype + ", " + this.values + "]";
    }
}
