package jadx.core.dex.nodes;

import jadx.api.IJadxArgs;
import jadx.api.ResourceFile;
import jadx.api.ResourceType;
import jadx.api.ResourcesLoader;
import jadx.api.ResourcesLoader.ResourceDecoder;
import jadx.core.clsp.ClspGraph;
import jadx.core.dex.info.ClassInfo;
import jadx.core.dex.info.ConstStorage;
import jadx.core.utils.ErrorsCounter;
import jadx.core.utils.StringUtils;
import jadx.core.utils.android.AndroidResourcesUtils;
import jadx.core.utils.exceptions.DecodeException;
import jadx.core.utils.exceptions.JadxException;
import jadx.core.utils.files.DexFile;
import jadx.core.utils.files.InputFile;
import jadx.core.xmlgen.ResContainer;
import jadx.core.xmlgen.ResTableParser;
import jadx.core.xmlgen.ResourceStorage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RootNode {
    private static final Logger LOG = LoggerFactory.getLogger(RootNode.class);
    @Nullable
    private String appPackage;
    private ClassNode appResClass;
    private final IJadxArgs args;
    private ClspGraph clsp;
    private final ConstStorage constValues;
    private List<DexNode> dexNodes;
    private final ErrorsCounter errorsCounter = new ErrorsCounter();
    private final StringUtils stringUtils;

    public RootNode(IJadxArgs args) {
        this.args = args;
        this.stringUtils = new StringUtils(args);
        this.constValues = new ConstStorage(args);
    }

    public void load(List<InputFile> inputFiles) throws DecodeException {
        this.dexNodes = new ArrayList();
        for (InputFile input : inputFiles) {
            for (DexFile dexFile : input.getDexFiles()) {
                try {
                    LOG.debug("Load: {}", dexFile);
                    this.dexNodes.add(new DexNode(this, dexFile));
                } catch (Throwable e) {
                    throw new DecodeException("Error decode file: " + dexFile, e);
                }
            }
        }
        for (DexNode dexNode : this.dexNodes) {
            dexNode.loadClasses();
        }
        initInnerClasses();
    }

    public void loadResources(List<ResourceFile> resources) {
        ResourceFile arsc = null;
        for (ResourceFile rf : resources) {
            if (rf.getType() == ResourceType.ARSC) {
                arsc = rf;
                break;
            }
        }
        if (arsc == null) {
            LOG.debug("'.arsc' file not found");
            return;
        }
        final ResTableParser parser = new ResTableParser();
        try {
            ResourcesLoader.decodeStream(arsc, new ResourceDecoder() {
                public ResContainer decode(long size, InputStream is) throws IOException {
                    parser.decode(is);
                    return null;
                }
            });
            ResourceStorage resStorage = parser.getResStorage();
            this.constValues.setResourcesNames(resStorage.getResourcesNames());
            this.appPackage = resStorage.getAppPackage();
        } catch (JadxException e) {
            LOG.error("Failed to parse '.arsc' file", e);
        }
    }

    public void initAppResClass() {
        this.appResClass = AndroidResourcesUtils.searchAppResClass(this);
    }

    public void initClassPath() throws DecodeException {
        try {
            if (this.clsp == null) {
                ClspGraph clsp = new ClspGraph();
                clsp.load();
                List<ClassNode> classes = new ArrayList();
                for (DexNode dexNode : this.dexNodes) {
                    classes.addAll(dexNode.getClasses());
                }
                clsp.addApp(classes);
                this.clsp = clsp;
            }
        } catch (Throwable e) {
            throw new DecodeException("Error loading classpath", e);
        }
    }

    private void initInnerClasses() {
        for (DexNode dexNode : this.dexNodes) {
            dexNode.initInnerClasses();
        }
    }

    public List<ClassNode> getClasses(boolean includeInner) {
        List<ClassNode> classes = new ArrayList();
        for (DexNode dex : this.dexNodes) {
            if (includeInner) {
                classes.addAll(dex.getClasses());
            } else {
                for (ClassNode cls : dex.getClasses()) {
                    if (!cls.getClassInfo().isInner()) {
                        classes.add(cls);
                    }
                }
            }
        }
        return classes;
    }

    public ClassNode searchClassByName(String fullName) {
        for (DexNode dexNode : this.dexNodes) {
            ClassNode cls = dexNode.resolveClass(ClassInfo.fromName(dexNode, fullName));
            if (cls != null) {
                return cls;
            }
        }
        return null;
    }

    public List<ClassNode> searchClassByShortName(String shortName) {
        List<ClassNode> list = new ArrayList();
        for (DexNode dexNode : this.dexNodes) {
            for (ClassNode cls : dexNode.getClasses()) {
                if (cls.getClassInfo().getShortName().equals(shortName)) {
                    list.add(cls);
                }
            }
        }
        return list;
    }

    public List<DexNode> getDexNodes() {
        return this.dexNodes;
    }

    public ClspGraph getClsp() {
        return this.clsp;
    }

    public ErrorsCounter getErrorsCounter() {
        return this.errorsCounter;
    }

    @Nullable
    public String getAppPackage() {
        return this.appPackage;
    }

    public ClassNode getAppResClass() {
        return this.appResClass;
    }

    public IJadxArgs getArgs() {
        return this.args;
    }

    public StringUtils getStringUtils() {
        return this.stringUtils;
    }

    public ConstStorage getConstValues() {
        return this.constValues;
    }
}
