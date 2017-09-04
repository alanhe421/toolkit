package jadx.core.dex.regions;

import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.IBranchRegion;
import jadx.core.dex.nodes.IContainer;
import jadx.core.dex.nodes.IRegion;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SwitchRegion extends AbstractRegion implements IBranchRegion {
    private final List<IContainer> cases = new ArrayList();
    private IContainer defCase;
    private final BlockNode header;
    private final List<List<Object>> keys = new ArrayList();

    public SwitchRegion(IRegion parent, BlockNode header) {
        super(parent);
        this.header = header;
    }

    public BlockNode getHeader() {
        return this.header;
    }

    public void addCase(List<Object> keysList, IContainer c) {
        this.keys.add(keysList);
        this.cases.add(c);
    }

    public void setDefaultCase(IContainer block) {
        this.defCase = block;
    }

    public IContainer getDefaultCase() {
        return this.defCase;
    }

    public List<List<Object>> getKeys() {
        return this.keys;
    }

    public List<IContainer> getCases() {
        return this.cases;
    }

    public List<IContainer> getSubBlocks() {
        List<IContainer> all = new ArrayList(this.cases.size() + 2);
        all.add(this.header);
        all.addAll(this.cases);
        if (this.defCase != null) {
            all.add(this.defCase);
        }
        return Collections.unmodifiableList(all);
    }

    public List<IContainer> getBranches() {
        List<IContainer> branches = new ArrayList(this.cases.size() + 1);
        branches.addAll(this.cases);
        branches.add(this.defCase);
        return Collections.unmodifiableList(branches);
    }

    public String baseString() {
        return this.header.baseString();
    }

    public String toString() {
        return "Switch: " + this.cases.size() + ", default: " + this.defCase;
    }
}
