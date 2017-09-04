package jadx.core.dex.attributes;

import jadx.core.dex.attributes.annotations.Annotation;
import java.util.List;

public interface IAttributeNode {
    void add(AFlag aFlag);

    <T> void addAttr(AType<AttrList<T>> aType, T t);

    void addAttr(IAttribute iAttribute);

    void clearAttributes();

    boolean contains(AFlag aFlag);

    <T extends IAttribute> boolean contains(AType<T> aType);

    void copyAttributesFrom(AttrNode attrNode);

    <T extends IAttribute> T get(AType<T> aType);

    <T> List<T> getAll(AType<AttrList<T>> aType);

    Annotation getAnnotation(String str);

    String getAttributesString();

    List<String> getAttributesStringsList();

    void remove(AFlag aFlag);

    <T extends IAttribute> void remove(AType<T> aType);

    void removeAttr(IAttribute iAttribute);
}
