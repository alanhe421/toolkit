package jadx.core.dex.visitors.regions;

import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.nodes.DeclareVariablesAttr;
import jadx.core.dex.instructions.InsnType;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.instructions.args.VarName;
import jadx.core.dex.nodes.IBlock;
import jadx.core.dex.nodes.IBranchRegion;
import jadx.core.dex.nodes.IContainer;
import jadx.core.dex.nodes.IRegion;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.regions.loops.ForLoop;
import jadx.core.dex.regions.loops.LoopRegion;
import jadx.core.dex.regions.loops.LoopType;
import jadx.core.dex.visitors.AbstractVisitor;
import jadx.core.utils.RegionUtils;
import jadx.core.utils.exceptions.JadxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessVariables extends AbstractVisitor {
    private static final Logger LOG = LoggerFactory.getLogger(ProcessVariables.class);

    private static class CollectUsageRegionVisitor extends TracedRegionVisitor {
        private final List<RegisterArg> args = new ArrayList();
        private final Map<Variable, Usage> usageMap;

        public CollectUsageRegionVisitor(Map<Variable, Usage> usageMap) {
            this.usageMap = usageMap;
        }

        public void processBlockTraced(MethodNode mth, IBlock container, IRegion curRegion) {
            regionProcess(mth, curRegion);
            int len = container.getInstructions().size();
            for (int i = 0; i < len; i++) {
                InsnNode insn = (InsnNode) container.getInstructions().get(i);
                if (!insn.contains(AFlag.SKIP)) {
                    this.args.clear();
                    processInsn(insn, curRegion);
                }
            }
        }

        private void regionProcess(MethodNode mth, IRegion region) {
            if (region instanceof LoopRegion) {
                LoopType loopType = ((LoopRegion) region).getType();
                if (loopType instanceof ForLoop) {
                    ForLoop forLoop = (ForLoop) loopType;
                    processInsn(forLoop.getInitInsn(), region);
                    processInsn(forLoop.getIncrInsn(), region);
                }
            }
        }

        void processInsn(InsnNode insn, IRegion curRegion) {
            if (insn != null) {
                RegisterArg result = insn.getResult();
                if (result != null && result.isRegister()) {
                    Usage u = ProcessVariables.addToUsageMap(result, this.usageMap);
                    if (u.getArg() == null) {
                        u.setArg(result);
                        u.setArgRegion(curRegion);
                    }
                    u.getAssigns().add(curRegion);
                }
                this.args.clear();
                insn.getRegisterArgs(this.args);
                for (RegisterArg arg : this.args) {
                    ProcessVariables.addToUsageMap(arg, this.usageMap).getUseRegions().add(curRegion);
                }
            }
        }
    }

    private static class Usage {
        private RegisterArg arg;
        private IRegion argRegion;
        private final Set<IRegion> assigns;
        private final Set<IRegion> usage;
        private VarName varName;

        private Usage() {
            this.usage = new HashSet(2);
            this.assigns = new HashSet(2);
        }

        public void setArg(RegisterArg arg) {
            this.arg = arg;
        }

        public RegisterArg getArg() {
            return this.arg;
        }

        public VarName getVarName() {
            return this.varName;
        }

        public void setVarName(VarName varName) {
            this.varName = varName;
        }

        public void setArgRegion(IRegion argRegion) {
            this.argRegion = argRegion;
        }

        public IRegion getArgRegion() {
            return this.argRegion;
        }

        public Set<IRegion> getAssigns() {
            return this.assigns;
        }

        public Set<IRegion> getUseRegions() {
            return this.usage;
        }

        public String toString() {
            return this.arg + ", a:" + this.assigns + ", u:" + this.usage;
        }
    }

    private static class Variable {
        private final int regNum;
        private final ArgType type;

        public Variable(RegisterArg arg) {
            this.regNum = arg.getRegNum();
            this.type = arg.getType();
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Variable variable = (Variable) o;
            if (this.regNum == variable.regNum && this.type.equals(variable.type)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (this.regNum * 31) + this.type.hashCode();
        }

        public String toString() {
            return this.regNum + " " + this.type;
        }
    }

    public void visit(MethodNode mth) throws JadxException {
        if (!mth.isNoCode()) {
            Usage u;
            Map<Variable, Usage> usageMap = new LinkedHashMap();
            for (RegisterArg arg : mth.getArguments(true)) {
                addToUsageMap(arg, usageMap);
            }
            DepthRegionTraversal.traverse(mth, new CollectUsageRegionVisitor(usageMap));
            for (RegisterArg arg2 : mth.getArguments(true)) {
                usageMap.remove(new Variable(arg2));
            }
            Iterator<Entry<Variable, Usage>> umIt = usageMap.entrySet().iterator();
            while (umIt.hasNext()) {
                u = (Usage) ((Entry) umIt.next()).getValue();
                if (u.getAssigns().isEmpty()) {
                    umIt.remove();
                } else {
                    InsnNode parentInsn = u.getArg().getParentInsn();
                    if (parentInsn == null || parentInsn.getType() == InsnType.MOVE_EXCEPTION) {
                        umIt.remove();
                    }
                }
            }
            if (!usageMap.isEmpty()) {
                Map<IContainer, Integer> regionsOrder = new HashMap();
                calculateOrder(mth.getRegion(), regionsOrder, 0, true);
                Iterator<Entry<Variable, Usage>> it = usageMap.entrySet().iterator();
                while (it.hasNext()) {
                    u = (Usage) ((Entry) it.next()).getValue();
                    for (IRegion assignRegion : u.getAssigns()) {
                        if (u.getArgRegion() == assignRegion && canDeclareInRegion(u, assignRegion, regionsOrder) && declareAtAssign(u)) {
                            it.remove();
                            break;
                        }
                    }
                }
                if (!usageMap.isEmpty()) {
                    for (Entry<Variable, Usage> entry : usageMap.entrySet()) {
                        IRegion parent;
                        u = (Usage) entry.getValue();
                        Set<IRegion> set = u.getUseRegions();
                        Iterator<IRegion> it2 = set.iterator();
                        while (it2.hasNext()) {
                            parent = ((IRegion) it2.next()).getParent();
                            if (parent != null && set.contains(parent)) {
                                it2.remove();
                            }
                        }
                        IRegion region = null;
                        if (!set.isEmpty()) {
                            region = (IRegion) set.iterator().next();
                        } else if (!u.getAssigns().isEmpty()) {
                            region = (IRegion) u.getAssigns().iterator().next();
                        }
                        if (region != null) {
                            parent = region;
                            boolean declared = false;
                            while (parent != null) {
                                if (canDeclareInRegion(u, region, regionsOrder)) {
                                    declareVar(region, u.getArg());
                                    declared = true;
                                    break;
                                }
                                region = parent;
                                parent = region.getParent();
                            }
                            if (!declared) {
                                declareVar(mth.getRegion(), u.getArg());
                            }
                        }
                    }
                }
            }
        }
    }

    private static Usage addToUsageMap(RegisterArg arg, Map<Variable, Usage> usageMap) {
        Variable varId = new Variable(arg);
        Usage usage = (Usage) usageMap.get(varId);
        if (usage == null) {
            usage = new Usage();
            usageMap.put(varId, usage);
        }
        if (usage.getVarName() == null) {
            VarName argVN = arg.getSVar().getVarName();
            if (argVN == null) {
                argVN = new VarName();
                arg.getSVar().setVarName(argVN);
            }
            usage.setVarName(argVN);
        } else {
            arg.getSVar().setVarName(usage.getVarName());
        }
        return usage;
    }

    private static boolean declareAtAssign(Usage u) {
        RegisterArg arg = u.getArg();
        InsnNode parentInsn = arg.getParentInsn();
        if (!arg.equals(parentInsn.getResult())) {
            return false;
        }
        parentInsn.add(AFlag.DECLARE_VAR);
        return true;
    }

    private static void declareVar(IContainer region, RegisterArg arg) {
        DeclareVariablesAttr dv = (DeclareVariablesAttr) region.get(AType.DECLARE_VARIABLES);
        if (dv == null) {
            dv = new DeclareVariablesAttr();
            region.addAttr(dv);
        }
        dv.addVar(arg);
    }

    private static int calculateOrder(IContainer container, Map<IContainer, Integer> regionsOrder, int id, boolean inc) {
        if (!(container instanceof IRegion)) {
            return id;
        }
        IRegion region = (IRegion) container;
        if (((Integer) regionsOrder.put(region, Integer.valueOf(id))) != null) {
            return id;
        }
        for (IContainer c : region.getSubBlocks()) {
            if (c instanceof IBranchRegion) {
                if (inc) {
                    id++;
                }
                id = calculateOrder(c, regionsOrder, id, false);
            } else {
                List<IContainer> handlers = RegionUtils.getExcHandlersForRegion(c);
                if (!handlers.isEmpty()) {
                    for (IContainer handler : handlers) {
                        if (inc) {
                            id++;
                        }
                        id = calculateOrder(handler, regionsOrder, id, inc);
                    }
                }
                if (inc) {
                    id++;
                }
                id = calculateOrder(c, regionsOrder, id, inc);
            }
        }
        return id;
    }

    private static boolean canDeclareInRegion(Usage u, IRegion region, Map<IContainer, Integer> regionsOrder) {
        Integer pos = (Integer) regionsOrder.get(region);
        if (pos == null) {
            LOG.debug("TODO: Not found order for region {} for {}", region, u);
            return false;
        }
        if (region instanceof LoopRegion) {
            for (IRegion r : u.getAssigns()) {
                if (!RegionUtils.isRegionContainsRegion(region, r)) {
                    return false;
                }
            }
        }
        if (isAllRegionsAfter(region, pos.intValue(), u.getAssigns(), regionsOrder) && isAllRegionsAfter(region, pos.intValue(), u.getUseRegions(), regionsOrder)) {
            return true;
        }
        return false;
    }

    private static boolean isAllRegionsAfter(IRegion region, int pos, Set<IRegion> regions, Map<IContainer, Integer> regionsOrder) {
        for (IRegion r : regions) {
            if (r != region) {
                Integer rPos = (Integer) regionsOrder.get(r);
                if (rPos == null) {
                    LOG.debug("TODO: Not found order for region {} in {}", r, regionsOrder);
                    return false;
                } else if (pos > rPos.intValue()) {
                    return false;
                } else {
                    if (pos == rPos.intValue()) {
                        return isAllRegionsAfterRecursive(region, regions);
                    }
                }
            }
        }
        return true;
    }

    private static boolean isAllRegionsAfterRecursive(IRegion region, Set<IRegion> others) {
        for (IRegion r : others) {
            if (!RegionUtils.isRegionContainsRegion(region, r)) {
                return false;
            }
        }
        return true;
    }
}
