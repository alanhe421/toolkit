package jadx.core.utils.android;

import jadx.core.codegen.ClassGen;
import jadx.core.codegen.CodeWriter;
import jadx.core.dex.info.ClassInfo;
import jadx.core.dex.nodes.ClassNode;
import jadx.core.dex.nodes.DexNode;
import jadx.core.dex.nodes.RootNode;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AndroidResourcesUtils {
    private static final Logger LOG = LoggerFactory.getLogger(AndroidResourcesUtils.class);

    public static ClassNode searchAppResClass(RootNode root) {
        String appPackage = root.getAppPackage();
        String fullName = appPackage != null ? appPackage + ".R" : "R";
        ClassNode resCls = root.searchClassByName(fullName);
        if (resCls != null) {
            return resCls;
        }
        List<ClassNode> candidates = root.searchClassByShortName("R");
        if (candidates.size() == 1) {
            return (ClassNode) candidates.get(0);
        }
        if (!candidates.isEmpty()) {
            LOG.info("Found several 'R' class candidates: {}", candidates);
        }
        LOG.warn("Unknown 'R' class, create references to '{}'", fullName);
        return makeClass(root, fullName);
    }

    public static boolean handleAppResField(CodeWriter code, ClassGen clsGen, ClassInfo declClass) {
        ClassInfo parentClass = declClass.getParentClass();
        if (parentClass == null || !parentClass.getShortName().equals("R")) {
            return false;
        }
        clsGen.useClass(code, parentClass);
        code.add('.');
        code.add(declClass.getAlias().getShortName());
        return true;
    }

    private static ClassNode makeClass(RootNode root, String clsName) {
        DexNode firstDex = (DexNode) root.getDexNodes().get(0);
        return new ClassNode(firstDex, ClassInfo.fromName(firstDex, clsName));
    }
}
