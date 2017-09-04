package jadx.core.dex.attributes;

import jadx.core.utils.Utils;
import java.util.LinkedList;
import java.util.List;

public class AttrList<T> implements IAttribute {
    private final List<T> list = new LinkedList();
    private final AType<AttrList<T>> type;

    public AttrList(AType<AttrList<T>> type) {
        this.type = type;
    }

    public List<T> getList() {
        return this.list;
    }

    public AType<AttrList<T>> getType() {
        return this.type;
    }

    public String toString() {
        return Utils.listToString(this.list);
    }
}
