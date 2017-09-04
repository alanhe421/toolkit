package jadx.core.dex.visitors.ssa;

import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.utils.exceptions.JadxRuntimeException;
import java.util.BitSet;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LiveVarAnalysis {
    private static final Logger LOG = LoggerFactory.getLogger(LiveVarAnalysis.class);
    private BitSet[] assignBlocks;
    private BitSet[] defs;
    private BitSet[] liveIn;
    private final MethodNode mth;
    private BitSet[] uses;

    public LiveVarAnalysis(MethodNode mth) {
        this.mth = mth;
    }

    public void runAnalysis() {
        int bbCount = this.mth.getBasicBlocks().size();
        int regsCount = this.mth.getRegsCount();
        this.uses = initBitSetArray(bbCount, regsCount);
        this.defs = initBitSetArray(bbCount, regsCount);
        this.assignBlocks = initBitSetArray(regsCount, bbCount);
        fillBasicBlockInfo();
        processLiveInfo();
    }

    public BitSet getAssignBlocks(int regNum) {
        return this.assignBlocks[regNum];
    }

    public boolean isLive(int blockId, int regNum) {
        if (blockId < this.liveIn.length) {
            return this.liveIn[blockId].get(regNum);
        }
        LOG.warn("LiveVarAnalysis: out of bounds block: {}, max: {}", Integer.valueOf(blockId), Integer.valueOf(this.liveIn.length));
        return false;
    }

    public boolean isLive(BlockNode block, int regNum) {
        return isLive(block.getId(), regNum);
    }

    private void fillBasicBlockInfo() {
        for (BlockNode block : this.mth.getBasicBlocks()) {
            int blockId = block.getId();
            BitSet gen = this.uses[blockId];
            BitSet kill = this.defs[blockId];
            for (InsnNode insn : block.getInstructions()) {
                for (InsnArg arg : insn.getArguments()) {
                    int regNum;
                    if (arg.isRegister()) {
                        regNum = ((RegisterArg) arg).getRegNum();
                        if (!kill.get(regNum)) {
                            gen.set(regNum);
                        }
                    }
                }
                RegisterArg result = insn.getResult();
                if (result != null) {
                    regNum = result.getRegNum();
                    kill.set(regNum);
                    this.assignBlocks[regNum].set(blockId);
                }
            }
        }
    }

    private void processLiveInfo() {
        int bbCount = this.mth.getBasicBlocks().size();
        int regsCount = this.mth.getRegsCount();
        BitSet[] liveIn = initBitSetArray(bbCount, regsCount);
        List<BlockNode> blocks = this.mth.getBasicBlocks();
        int blocksSize = blocks.size();
        int k = 0;
        while (true) {
            boolean changed = false;
            for (int i = 0; i < blocksSize; i++) {
                BlockNode block = (BlockNode) blocks.get(i);
                int blockId = block.getId();
                BitSet prevIn = liveIn[blockId];
                BitSet newIn = new BitSet(regsCount);
                List<BlockNode> successors = block.getSuccessors();
                int successorsSize = successors.size();
                for (int s = 0; s < successorsSize; s++) {
                    newIn.or(liveIn[((BlockNode) successors.get(s)).getId()]);
                }
                newIn.andNot(this.defs[blockId]);
                newIn.or(this.uses[blockId]);
                if (!prevIn.equals(newIn)) {
                    changed = true;
                    liveIn[blockId] = newIn;
                }
            }
            int k2 = k + 1;
            if (k > 1000) {
                throw new JadxRuntimeException("Live variable analysis reach iterations limit");
            } else if (changed) {
                k = k2;
            } else {
                this.liveIn = liveIn;
                return;
            }
        }
    }

    private static BitSet[] initBitSetArray(int length, int bitsCount) {
        BitSet[] array = new BitSet[length];
        for (int i = 0; i < length; i++) {
            array[i] = new BitSet(bitsCount);
        }
        return array;
    }
}
