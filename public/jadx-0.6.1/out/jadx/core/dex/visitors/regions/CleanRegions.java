package jadx.core.dex.visitors.regions;

import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.IContainer;
import jadx.core.dex.nodes.IRegion;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.regions.Region;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CleanRegions {
    private static final Logger LOG = LoggerFactory.getLogger(CleanRegions.class);

    private CleanRegions() {
    }

    public static void process(MethodNode mth) {
        if (!mth.isNoCode() && !mth.getBasicBlocks().isEmpty()) {
            DepthRegionTraversal.traverse(mth, new AbstractRegionVisitor() {
                public boolean enterRegion(MethodNode mth, IRegion region) {
                    if (region instanceof Region) {
                        Iterator<IContainer> it = region.getSubBlocks().iterator();
                        while (it.hasNext()) {
                            IContainer container = (IContainer) it.next();
                            if (container instanceof BlockNode) {
                                if (((BlockNode) container).getInstructions().isEmpty()) {
                                    try {
                                        it.remove();
                                    } catch (UnsupportedOperationException e) {
                                        CleanRegions.LOG.warn("Can't remove block: {} from: {}, mth: {}", new Object[]{block, region, mth});
                                    }
                                }
                            }
                        }
                    }
                    return true;
                }
            });
        }
    }
}
