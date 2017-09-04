package jadx.core.dex.attributes;

import jadx.core.dex.attributes.annotations.Annotation;
import jadx.core.dex.attributes.annotations.AnnotationsList;
import jadx.core.utils.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AttributeStorage {
    private final Map<AType<?>, IAttribute> attributes = new IdentityHashMap();
    private final Set<AFlag> flags = EnumSet.noneOf(AFlag.class);

    public void add(AFlag flag) {
        this.flags.add(flag);
    }

    public void add(IAttribute attr) {
        this.attributes.put(attr.getType(), attr);
    }

    public <T> void add(AType<AttrList<T>> type, T obj) {
        AttrList<T> list = (AttrList) get(type);
        if (list == null) {
            list = new AttrList(type);
            add((IAttribute) list);
        }
        list.getList().add(obj);
    }

    public void addAll(AttributeStorage otherList) {
        this.flags.addAll(otherList.flags);
        this.attributes.putAll(otherList.attributes);
    }

    public boolean contains(AFlag flag) {
        return this.flags.contains(flag);
    }

    public <T extends IAttribute> boolean contains(AType<T> type) {
        return this.attributes.containsKey(type);
    }

    public <T extends IAttribute> T get(AType<T> type) {
        return (IAttribute) this.attributes.get(type);
    }

    public Annotation getAnnotation(String cls) {
        AnnotationsList aList = (AnnotationsList) get(AType.ANNOTATION_LIST);
        return aList == null ? null : aList.get(cls);
    }

    public <T> List<T> getAll(AType<AttrList<T>> type) {
        AttrList<T> attrList = (AttrList) get(type);
        if (attrList == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(attrList.getList());
    }

    public void remove(AFlag flag) {
        this.flags.remove(flag);
    }

    public <T extends IAttribute> void remove(AType<T> type) {
        this.attributes.remove(type);
    }

    public void remove(IAttribute attr) {
        AType<?> type = attr.getType();
        if (((IAttribute) this.attributes.get(type)) == attr) {
            this.attributes.remove(type);
        }
    }

    public void clear() {
        this.flags.clear();
        this.attributes.clear();
    }

    public List<String> getAttributeStrings() {
        int size = (this.flags.size() + this.attributes.size()) + this.attributes.size();
        if (size == 0) {
            return Collections.emptyList();
        }
        List<String> list = new ArrayList(size);
        for (AFlag a : this.flags) {
            list.add(a.toString());
        }
        for (IAttribute a2 : this.attributes.values()) {
            list.add(a2.toString());
        }
        return list;
    }

    public boolean isEmpty() {
        return this.flags.isEmpty() && this.attributes.isEmpty();
    }

    public String toString() {
        List<String> list = getAttributeStrings();
        if (list.isEmpty()) {
            return "";
        }
        return "A:{" + Utils.listToString(list) + "}";
    }
}
