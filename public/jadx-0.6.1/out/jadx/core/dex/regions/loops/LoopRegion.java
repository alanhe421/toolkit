package jadx.core.dex.regions.loops;

import jadx.core.dex.attributes.nodes.LoopInfo;
import jadx.core.dex.instructions.IfNode;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.IContainer;
import jadx.core.dex.nodes.IRegion;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.regions.AbstractRegion;
import jadx.core.dex.regions.conditions.IfCondition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class LoopRegion extends AbstractRegion {
    private IRegion body;
    private IfCondition condition;
    private final boolean conditionAtEnd;
    private final BlockNode conditionBlock;
    private final LoopInfo info;
    private BlockNode preCondition;
    private LoopType type;

    public LoopRegion(IRegion parent, LoopInfo info, BlockNode header, boolean reversed) {
        super(parent);
        this.info = info;
        this.conditionBlock = header;
        this.condition = IfCondition.fromIfBlock(header);
        this.conditionAtEnd = reversed;
    }

    public LoopInfo getInfo() {
        return this.info;
    }

    public IfCondition getCondition() {
        return this.condition;
    }

    public void setCondition(IfCondition condition) {
        this.condition = condition;
    }

    public BlockNode getHeader() {
        return this.conditionBlock;
    }

    public IRegion getBody() {
        return this.body;
    }

    public void setBody(IRegion body) {
        this.body = body;
    }

    public boolean isConditionAtEnd() {
        return this.conditionAtEnd;
    }

    public void setPreCondition(BlockNode preCondition) {
        this.preCondition = preCondition;
    }

    private IfNode getIfInsn() {
        return (IfNode) this.conditionBlock.getInstructions().get(0);
    }

    public boolean checkPreCondition() {
        List<InsnNode> insns = this.preCondition.getInstructions();
        if (insns.isEmpty()) {
            return true;
        }
        IfNode ifInsn = getIfInsn();
        int size = insns.size();
        for (int i = 0; i < size; i++) {
            InsnNode insn = (InsnNode) insns.get(i);
            if (insn.getResult() == null) {
                return false;
            }
            RegisterArg res = insn.getResult();
            if (res.getSVar().getUseCount() > 1) {
                return false;
            }
            boolean found = false;
            for (int j = i + 1; j < size; j++) {
                if (((InsnNode) insns.get(i)).containsArg(res)) {
                    found = true;
                }
            }
            if (!found && ifInsn.containsArg(res)) {
                found = true;
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    public void mergePreCondition() {
        if (this.preCondition != null && this.conditionBlock != null) {
            List<InsnNode> condInsns = this.conditionBlock.getInstructions();
            List<InsnNode> preCondInsns = this.preCondition.getInstructions();
            preCondInsns.addAll(condInsns);
            condInsns.clear();
            condInsns.addAll(preCondInsns);
            preCondInsns.clear();
            this.preCondition = null;
        }
    }

    public LoopType getType() {
        return this.type;
    }

    public void setType(LoopType type) {
        this.type = type;
    }

    public List<IContainer> getSubBlocks() {
        List<IContainer> all = new ArrayList(3);
        if (this.preCondition != null) {
            all.add(this.preCondition);
        }
        if (this.conditionBlock != null) {
            all.add(this.conditionBlock);
        }
        if (this.body != null) {
            all.add(this.body);
        }
        return Collections.unmodifiableList(all);
    }

    public boolean replaceSubBlock(IContainer oldBlock, IContainer newBlock) {
        return false;
    }

    public String baseString() {
        return this.body == null ? "-" : this.body.baseString();
    }

    public String toString() {
        return "LOOP:" + this.info.getId() + ": " + baseString();
    }
}
