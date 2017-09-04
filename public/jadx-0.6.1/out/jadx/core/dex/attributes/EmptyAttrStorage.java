package jadx.core.dex.attributes;

import jadx.core.dex.attributes.annotations.Annotation;
import java.util.Collections;
import java.util.List;

public final class EmptyAttrStorage extends AttributeStorage {
    public boolean contains(AFlag flag) {
        return false;
    }

    public <T extends IAttribute> boolean contains(AType<T> aType) {
        return false;
    }

    public <T extends IAttribute> T get(AType<T> aType) {
        return null;
    }

    public Annotation getAnnotation(String cls) {
        return null;
    }

    public <T> List<T> getAll(AType<AttrList<T>> aType) {
        return Collections.emptyList();
    }

    public void clear() {
    }

    public void remove(AFlag flag) {
    }

    public <T extends IAttribute> void remove(AType<T> aType) {
    }

    public void remove(IAttribute attr) {
    }

    public List<String> getAttributeStrings() {
        return Collections.emptyList();
    }

    public boolean isEmpty() {
        return true;
    }

    public String toString() {
        return "";
    }
}
