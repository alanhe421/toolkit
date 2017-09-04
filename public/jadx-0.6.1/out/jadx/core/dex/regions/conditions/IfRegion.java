package jadx.core.dex.regions.conditions;

import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.IBranchRegion;
import jadx.core.dex.nodes.IContainer;
import jadx.core.dex.nodes.IRegion;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.regions.AbstractRegion;
import jadx.core.utils.exceptions.JadxRuntimeException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class IfRegion extends AbstractRegion implements IBranchRegion {
    private IfCondition condition;
    private IContainer elseRegion;
    private final BlockNode header;
    private IContainer thenRegion;

    public IfRegion(IRegion parent, BlockNode header) {
        super(parent);
        if (header.getInstructions().size() != 1) {
            throw new JadxRuntimeException("Expected only one instruction in 'if' header");
        }
        this.header = header;
        this.condition = IfCondition.fromIfBlock(header);
    }

    public IfCondition getCondition() {
        return this.condition;
    }

    public void setCondition(IfCondition condition) {
        this.condition = condition;
    }

    public IContainer getThenRegion() {
        return this.thenRegion;
    }

    public void setThenRegion(IContainer thenRegion) {
        this.thenRegion = thenRegion;
    }

    public IContainer getElseRegion() {
        return this.elseRegion;
    }

    public void setElseRegion(IContainer elseRegion) {
        this.elseRegion = elseRegion;
    }

    public BlockNode getHeader() {
        return this.header;
    }

    public boolean simplifyCondition() {
        IfCondition cond = IfCondition.simplify(this.condition);
        if (cond == this.condition) {
            return false;
        }
        this.condition = cond;
        return true;
    }

    public void invert() {
        this.condition = IfCondition.invert(this.condition);
        IContainer tmp = this.thenRegion;
        this.thenRegion = this.elseRegion;
        this.elseRegion = tmp;
    }

    public int getSourceLine() {
        if (this.header.getInstructions().isEmpty()) {
            return 0;
        }
        return ((InsnNode) this.header.getInstructions().get(0)).getSourceLine();
    }

    public List<IContainer> getSubBlocks() {
        List<IContainer> all = new ArrayList(3);
        all.add(this.header);
        if (this.thenRegion != null) {
            all.add(this.thenRegion);
        }
        if (this.elseRegion != null) {
            all.add(this.elseRegion);
        }
        return Collections.unmodifiableList(all);
    }

    public List<IContainer> getBranches() {
        List<IContainer> branches = new ArrayList(2);
        branches.add(this.thenRegion);
        branches.add(this.elseRegion);
        return Collections.unmodifiableList(branches);
    }

    public boolean replaceSubBlock(IContainer oldBlock, IContainer newBlock) {
        if (oldBlock == this.thenRegion) {
            this.thenRegion = newBlock;
            return true;
        } else if (oldBlock != this.elseRegion) {
            return false;
        } else {
            this.elseRegion = newBlock;
            return true;
        }
    }

    public String baseString() {
        StringBuilder sb = new StringBuilder();
        if (this.thenRegion != null) {
            sb.append(this.thenRegion.baseString());
        }
        if (this.elseRegion != null) {
            sb.append(this.elseRegion.baseString());
        }
        return sb.toString();
    }

    public String toString() {
        return "IF " + this.header + " then " + this.thenRegion + " else " + this.elseRegion;
    }
}
