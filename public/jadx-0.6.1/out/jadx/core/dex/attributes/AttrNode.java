package jadx.core.dex.attributes;

import jadx.core.dex.attributes.annotations.Annotation;
import java.util.List;

public abstract class AttrNode implements IAttributeNode {
    private static final AttributeStorage EMPTY_ATTR_STORAGE = new EmptyAttrStorage();
    private AttributeStorage storage = EMPTY_ATTR_STORAGE;

    public void add(AFlag flag) {
        initStorage().add(flag);
    }

    public void addAttr(IAttribute attr) {
        initStorage().add(attr);
    }

    public <T> void addAttr(AType<AttrList<T>> type, T obj) {
        initStorage().add(type, obj);
    }

    public void copyAttributesFrom(AttrNode attrNode) {
        AttributeStorage copyFrom = attrNode.storage;
        if (!copyFrom.isEmpty()) {
            initStorage().addAll(copyFrom);
        }
    }

    private AttributeStorage initStorage() {
        AttributeStorage store = this.storage;
        if (store != EMPTY_ATTR_STORAGE) {
            return store;
        }
        store = new AttributeStorage();
        this.storage = store;
        return store;
    }

    public boolean contains(AFlag flag) {
        return this.storage.contains(flag);
    }

    public <T extends IAttribute> boolean contains(AType<T> type) {
        return this.storage.contains((AType) type);
    }

    public <T extends IAttribute> T get(AType<T> type) {
        return this.storage.get(type);
    }

    public Annotation getAnnotation(String cls) {
        return this.storage.getAnnotation(cls);
    }

    public <T> List<T> getAll(AType<AttrList<T>> type) {
        return this.storage.getAll(type);
    }

    public void remove(AFlag flag) {
        this.storage.remove(flag);
    }

    public <T extends IAttribute> void remove(AType<T> type) {
        this.storage.remove((AType) type);
    }

    public void removeAttr(IAttribute attr) {
        this.storage.remove(attr);
    }

    public void clearAttributes() {
        this.storage.clear();
    }

    public List<String> getAttributesStringsList() {
        return this.storage.getAttributeStrings();
    }

    public String getAttributesString() {
        return this.storage.toString();
    }
}
