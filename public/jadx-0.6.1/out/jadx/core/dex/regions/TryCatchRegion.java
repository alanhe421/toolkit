package jadx.core.dex.regions;

import jadx.core.dex.nodes.IBranchRegion;
import jadx.core.dex.nodes.IContainer;
import jadx.core.dex.nodes.IRegion;
import jadx.core.dex.trycatch.ExceptionHandler;
import jadx.core.dex.trycatch.TryCatchBlock;
import jadx.core.utils.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class TryCatchRegion extends AbstractRegion implements IBranchRegion {
    private Map<ExceptionHandler, IContainer> catchRegions = Collections.emptyMap();
    private IContainer finallyRegion;
    private TryCatchBlock tryCatchBlock;
    private final IContainer tryRegion;

    public TryCatchRegion(IRegion parent, IContainer tryRegion) {
        super(parent);
        this.tryRegion = tryRegion;
    }

    public void setTryCatchBlock(TryCatchBlock tryCatchBlock) {
        this.tryCatchBlock = tryCatchBlock;
        this.catchRegions = new LinkedHashMap(tryCatchBlock.getHandlersCount());
        for (ExceptionHandler handler : tryCatchBlock.getHandlers()) {
            IContainer handlerRegion = handler.getHandlerRegion();
            if (handlerRegion != null) {
                if (handler.isFinally()) {
                    this.finallyRegion = handlerRegion;
                } else {
                    this.catchRegions.put(handler, handlerRegion);
                }
            }
        }
    }

    public IContainer getTryRegion() {
        return this.tryRegion;
    }

    public Map<ExceptionHandler, IContainer> getCatchRegions() {
        return this.catchRegions;
    }

    public TryCatchBlock getTryCatchBlock() {
        return this.tryCatchBlock;
    }

    public IContainer getFinallyRegion() {
        return this.finallyRegion;
    }

    public void setFinallyRegion(IContainer finallyRegion) {
        this.finallyRegion = finallyRegion;
    }

    public List<IContainer> getSubBlocks() {
        List<IContainer> all = new ArrayList(this.catchRegions.size() + 2);
        all.add(this.tryRegion);
        all.addAll(this.catchRegions.values());
        if (this.finallyRegion != null) {
            all.add(this.finallyRegion);
        }
        return Collections.unmodifiableList(all);
    }

    public List<IContainer> getBranches() {
        return getSubBlocks();
    }

    public String baseString() {
        return this.tryRegion.baseString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Try: ").append(this.tryRegion);
        if (!this.catchRegions.isEmpty()) {
            sb.append(" catches: ").append(Utils.listToString(this.catchRegions.values()));
        }
        if (this.finallyRegion != null) {
            sb.append(" finally: ").append(this.finallyRegion);
        }
        return sb.toString();
    }
}
