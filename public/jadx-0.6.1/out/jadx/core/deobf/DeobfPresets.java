package jadx.core.deobf;

import jadx.core.dex.info.ClassInfo;
import jadx.core.dex.info.FieldInfo;
import jadx.core.dex.info.MethodInfo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DeobfPresets {
    private static final Logger LOG = LoggerFactory.getLogger(DeobfPresets.class);
    private static final String MAP_FILE_CHARSET = "UTF-8";
    private final Map<String, String> clsPresetMap = new HashMap();
    private final File deobfMapFile;
    private final Deobfuscator deobfuscator;
    private final Map<String, String> fldPresetMap = new HashMap();
    private final Map<String, String> mthPresetMap = new HashMap();

    public DeobfPresets(Deobfuscator deobfuscator, File deobfMapFile) {
        this.deobfuscator = deobfuscator;
        this.deobfMapFile = deobfMapFile;
    }

    public void load() {
        if (this.deobfMapFile.exists()) {
            LOG.info("Loading obfuscation map from: {}", this.deobfMapFile.getAbsoluteFile());
            try {
                for (String l : FileUtils.readLines(this.deobfMapFile, MAP_FILE_CHARSET)) {
                    String l2 = l2.trim();
                    if (!(l2.isEmpty() || l2.startsWith("#"))) {
                        String[] va = splitAndTrim(l2);
                        if (va.length == 2) {
                            String origName = va[0];
                            String alias = va[1];
                            if (l2.startsWith("p ")) {
                                this.deobfuscator.addPackagePreset(origName, alias);
                            } else if (l2.startsWith("c ")) {
                                this.clsPresetMap.put(origName, alias);
                            } else if (l2.startsWith("f ")) {
                                this.fldPresetMap.put(origName, alias);
                            } else if (l2.startsWith("m ")) {
                                this.mthPresetMap.put(origName, alias);
                            }
                        } else {
                            continue;
                        }
                    }
                }
            } catch (IOException e) {
                LOG.error("Failed to load deobfuscation map file '{}'", this.deobfMapFile.getAbsolutePath(), e);
            }
        }
    }

    private static String[] splitAndTrim(String str) {
        String[] v = str.substring(2).split("=");
        for (int i = 0; i < v.length; i++) {
            v[i] = v[i].trim();
        }
        return v;
    }

    public void save(boolean forceSave) {
        try {
            if (!this.deobfMapFile.exists()) {
                dumpMapping();
            } else if (forceSave) {
                dumpMapping();
            } else {
                LOG.warn("Deobfuscation map file '{}' exists. Use command line option '--deobf-rewrite-cfg' to rewrite it", this.deobfMapFile.getAbsolutePath());
            }
        } catch (IOException e) {
            LOG.error("Failed to load deobfuscation map file '{}'", this.deobfMapFile.getAbsolutePath(), e);
        }
    }

    private void dumpMapping() throws IOException {
        List<String> list = new ArrayList();
        for (PackageNode p : this.deobfuscator.getRootPackage().getInnerPackages()) {
            for (PackageNode pp : p.getInnerPackages()) {
                dfsPackageName(list, p.getName(), pp);
            }
            if (p.hasAlias()) {
                list.add(String.format("p %s = %s", new Object[]{p.getName(), p.getAlias()}));
            }
        }
        for (DeobfClsInfo deobfClsInfo : this.deobfuscator.getClsMap().values()) {
            if (deobfClsInfo.getAlias() != null) {
                list.add(String.format("c %s = %s", new Object[]{((DeobfClsInfo) r2.next()).getCls().getClassInfo().getFullName(), ((DeobfClsInfo) r2.next()).getAlias()}));
            }
        }
        for (FieldInfo fld : this.deobfuscator.getFldMap().keySet()) {
            list.add(String.format("f %s = %s", new Object[]{fld.getFullId(), fld.getAlias()}));
        }
        for (MethodInfo mth : this.deobfuscator.getMthMap().keySet()) {
            list.add(String.format("m %s = %s", new Object[]{mth.getFullId(), mth.getAlias()}));
        }
        Collections.sort(list);
        FileUtils.writeLines(this.deobfMapFile, MAP_FILE_CHARSET, list);
        list.clear();
    }

    private static void dfsPackageName(List<String> list, String prefix, PackageNode node) {
        for (PackageNode pp : node.getInnerPackages()) {
            dfsPackageName(list, prefix + '.' + node.getName(), pp);
        }
        if (node.hasAlias()) {
            list.add(String.format("p %s.%s = %s", new Object[]{prefix, node.getName(), node.getAlias()}));
        }
    }

    public String getForCls(ClassInfo cls) {
        return (String) this.clsPresetMap.get(cls.getFullName());
    }

    public String getForFld(FieldInfo fld) {
        return (String) this.fldPresetMap.get(fld.getFullId());
    }

    public String getForMth(MethodInfo mth) {
        return (String) this.mthPresetMap.get(mth.getFullId());
    }

    public void clear() {
        this.clsPresetMap.clear();
        this.fldPresetMap.clear();
        this.mthPresetMap.clear();
    }

    public Map<String, String> getClsPresetMap() {
        return this.clsPresetMap;
    }

    public Map<String, String> getFldPresetMap() {
        return this.fldPresetMap;
    }

    public Map<String, String> getMthPresetMap() {
        return this.mthPresetMap;
    }
}
