package jadx.core.dex.attributes.annotations;

import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.IAttribute;
import jadx.core.utils.Utils;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnnotationsList implements IAttribute {
    public static final AnnotationsList EMPTY = new AnnotationsList(Collections.emptyList());
    private final Map<String, Annotation> map;

    public AnnotationsList(List<Annotation> anList) {
        this.map = new HashMap(anList.size());
        for (Annotation a : anList) {
            this.map.put(a.getAnnotationClass(), a);
        }
    }

    public Annotation get(String className) {
        return (Annotation) this.map.get(className);
    }

    public Collection<Annotation> getAll() {
        return this.map.values();
    }

    public int size() {
        return this.map.size();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public AType<AnnotationsList> getType() {
        return AType.ANNOTATION_LIST;
    }

    public String toString() {
        return Utils.listToString(this.map.values());
    }
}
