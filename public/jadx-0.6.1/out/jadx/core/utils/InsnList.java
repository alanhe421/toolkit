package jadx.core.utils;

import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.InsnNode;
import java.util.Iterator;
import java.util.List;

public final class InsnList implements Iterable<InsnNode> {
    private final List<InsnNode> list;

    public InsnList(List<InsnNode> list) {
        this.list = list;
    }

    public static void remove(List<InsnNode> list, InsnNode insn) {
        Iterator<InsnNode> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (((InsnNode) iterator.next()) == insn) {
                iterator.remove();
                return;
            }
        }
    }

    public static void remove(BlockNode block, InsnNode insn) {
        remove(block.getInstructions(), insn);
    }

    public static int getIndex(List<InsnNode> list, InsnNode insn) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i) == insn) {
                return i;
            }
        }
        return -1;
    }

    public int getIndex(InsnNode insn) {
        return getIndex(this.list, insn);
    }

    public boolean contains(InsnNode insn) {
        return getIndex(insn) != -1;
    }

    public void remove(InsnNode insn) {
        remove(this.list, insn);
    }

    public Iterator<InsnNode> iterator() {
        return this.list.iterator();
    }

    public InsnNode get(int index) {
        return (InsnNode) this.list.get(index);
    }

    public int size() {
        return this.list.size();
    }
}
