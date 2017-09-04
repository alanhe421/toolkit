package jadx.core.xmlgen;

import jadx.core.utils.Utils;
import jadx.core.xmlgen.entry.ResourceEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceStorage {
    private static final Comparator<ResourceEntry> COMPARATOR = new Comparator<ResourceEntry>() {
        public int compare(ResourceEntry a, ResourceEntry b) {
            return Utils.compare(a.getId(), b.getId());
        }
    };
    private String appPackage;
    private final List<ResourceEntry> list = new ArrayList();

    public Collection<ResourceEntry> getResources() {
        return this.list;
    }

    public void add(ResourceEntry ri) {
        this.list.add(ri);
    }

    public void finish() {
        Collections.sort(this.list, COMPARATOR);
    }

    public ResourceEntry getByRef(int refId) {
        int index = Collections.binarySearch(this.list, new ResourceEntry(refId), COMPARATOR);
        if (index < 0) {
            return null;
        }
        return (ResourceEntry) this.list.get(index);
    }

    public String getAppPackage() {
        return this.appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public Map<Integer, String> getResourcesNames() {
        Map<Integer, String> map = new HashMap();
        for (ResourceEntry entry : this.list) {
            map.put(Integer.valueOf(entry.getId()), entry.getTypeName() + "/" + entry.getKeyName());
        }
        return map;
    }
}
