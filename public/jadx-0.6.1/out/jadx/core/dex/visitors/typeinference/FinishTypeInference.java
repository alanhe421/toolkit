package jadx.core.dex.visitors.typeinference;

import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.DexNode;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.visitors.AbstractVisitor;

public class FinishTypeInference extends AbstractVisitor {
    public void visit(MethodNode mth) {
        if (!mth.isNoCode()) {
            int i = 0;
            boolean change;
            do {
                change = false;
                for (BlockNode block : mth.getBasicBlocks()) {
                    for (InsnNode insn : block.getInstructions()) {
                        if (PostTypeInference.process(mth, insn)) {
                            change = true;
                        }
                    }
                }
                i++;
                if (i <= 1000) {
                    break;
                }
                break;
            } while (change);
            DexNode dex = mth.dex();
            for (BlockNode block2 : mth.getBasicBlocks()) {
                for (InsnNode insn2 : block2.getInstructions()) {
                    SelectTypeVisitor.visit(dex, insn2);
                }
            }
            for (BlockNode block3 : mth.getBasicBlocks()) {
                for (InsnNode insn22 : block3.getInstructions()) {
                    CheckTypeVisitor.visit(mth, insn22);
                }
            }
        }
    }
}
