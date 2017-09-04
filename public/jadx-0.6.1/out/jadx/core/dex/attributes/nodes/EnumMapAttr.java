package jadx.core.dex.attributes.nodes;

import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.IAttribute;
import jadx.core.dex.nodes.FieldNode;
import java.util.HashMap;
import java.util.Map;

public class EnumMapAttr implements IAttribute {
    private final Map<FieldNode, KeyValueMap> fieldsMap = new HashMap();

    public static class KeyValueMap {
        private final Map<Object, Object> map = new HashMap();

        public Object get(Object key) {
            return this.map.get(key);
        }

        void put(Object key, Object value) {
            this.map.put(key, value);
        }
    }

    public KeyValueMap getMap(FieldNode field) {
        return (KeyValueMap) this.fieldsMap.get(field);
    }

    public void add(FieldNode field, Object key, Object value) {
        KeyValueMap map = getMap(field);
        if (map == null) {
            map = new KeyValueMap();
            this.fieldsMap.put(field, map);
        }
        map.put(key, value);
    }

    public AType<EnumMapAttr> getType() {
        return AType.ENUM_MAP;
    }

    public String toString() {
        return "Enum fields map: " + this.fieldsMap;
    }
}
