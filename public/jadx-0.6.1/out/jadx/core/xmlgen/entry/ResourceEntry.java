package jadx.core.xmlgen.entry;

import jadx.core.deobf.Deobfuscator;
import java.util.List;

public final class ResourceEntry {
    private EntryConfig config;
    private final int id;
    private final String keyName;
    private List<RawNamedValue> namedValues;
    private int parentRef;
    private final String pkgName;
    private RawValue simpleValue;
    private final String typeName;

    public ResourceEntry(int id, String pkgName, String typeName, String keyName) {
        this.id = id;
        this.pkgName = pkgName;
        this.typeName = typeName;
        this.keyName = keyName;
    }

    public ResourceEntry(int id) {
        this(id, "", "", "");
    }

    public int getId() {
        return this.id;
    }

    public String getPkgName() {
        return this.pkgName;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public String getKeyName() {
        return this.keyName;
    }

    public void setParentRef(int parentRef) {
        this.parentRef = parentRef;
    }

    public int getParentRef() {
        return this.parentRef;
    }

    public RawValue getSimpleValue() {
        return this.simpleValue;
    }

    public void setSimpleValue(RawValue simpleValue) {
        this.simpleValue = simpleValue;
    }

    public void setNamedValues(List<RawNamedValue> namedValues) {
        this.namedValues = namedValues;
    }

    public List<RawNamedValue> getNamedValues() {
        return this.namedValues;
    }

    public void setConfig(EntryConfig config) {
        this.config = config;
    }

    public EntryConfig getConfig() {
        return this.config;
    }

    public String toString() {
        return "  0x" + Integer.toHexString(this.id) + " (" + this.id + ")" + this.config + " = " + this.typeName + Deobfuscator.CLASS_NAME_SEPARATOR + this.keyName;
    }
}
