package jadx.core.dex.attributes.nodes;

import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.AttrList;
import jadx.core.dex.attributes.IAttribute;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.InsnNode;

public class EdgeInsnAttr implements IAttribute {
    private final BlockNode end;
    private final InsnNode insn;
    private final BlockNode start;

    public static void addEdgeInsn(BlockNode start, BlockNode end, InsnNode insn) {
        EdgeInsnAttr edgeInsnAttr = new EdgeInsnAttr(start, end, insn);
        start.addAttr(AType.EDGE_INSN, edgeInsnAttr);
        end.addAttr(AType.EDGE_INSN, edgeInsnAttr);
    }

    public EdgeInsnAttr(BlockNode start, BlockNode end, InsnNode insn) {
        this.start = start;
        this.end = end;
        this.insn = insn;
    }

    public AType<AttrList<EdgeInsnAttr>> getType() {
        return AType.EDGE_INSN;
    }

    public BlockNode getStart() {
        return this.start;
    }

    public BlockNode getEnd() {
        return this.end;
    }

    public InsnNode getInsn() {
        return this.insn;
    }

    public String toString() {
        return "EDGE_INSN: " + this.start + "->" + this.end + " " + this.insn;
    }
}
