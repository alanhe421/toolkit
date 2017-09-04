package jadx.core.dex.regions.conditions;

import jadx.core.dex.instructions.IfNode;
import jadx.core.dex.instructions.IfOp;
import jadx.core.dex.instructions.InsnType;
import jadx.core.dex.instructions.args.InsnWrapArg;
import jadx.core.dex.instructions.args.LiteralArg;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.utils.exceptions.JadxRuntimeException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class IfCondition {
    private final List<IfCondition> args;
    private final Compare compare;
    private final Mode mode;

    public enum Mode {
        COMPARE,
        TERNARY,
        NOT,
        AND,
        OR
    }

    private IfCondition(Compare compare) {
        this.mode = Mode.COMPARE;
        this.compare = compare;
        this.args = Collections.emptyList();
    }

    private IfCondition(Mode mode, List<IfCondition> args) {
        this.mode = mode;
        this.args = args;
        this.compare = null;
    }

    private IfCondition(IfCondition c) {
        this.mode = c.mode;
        this.compare = c.compare;
        if (c.mode == Mode.COMPARE) {
            this.args = Collections.emptyList();
        } else {
            this.args = new ArrayList(c.args);
        }
    }

    public static IfCondition fromIfBlock(BlockNode header) {
        if (header == null) {
            return null;
        }
        return fromIfNode((IfNode) header.getInstructions().get(0));
    }

    public static IfCondition fromIfNode(IfNode insn) {
        return new IfCondition(new Compare(insn));
    }

    public static IfCondition ternary(IfCondition a, IfCondition b, IfCondition c) {
        return new IfCondition(Mode.TERNARY, Arrays.asList(new IfCondition[]{a, b, c}));
    }

    public static IfCondition merge(Mode mode, IfCondition a, IfCondition b) {
        if (a.getMode() == mode) {
            IfCondition n = new IfCondition(a);
            n.addArg(b);
            return n;
        }
        return new IfCondition(mode, Arrays.asList(new IfCondition[]{a, b}));
    }

    public Mode getMode() {
        return this.mode;
    }

    public List<IfCondition> getArgs() {
        return this.args;
    }

    public IfCondition first() {
        return (IfCondition) this.args.get(0);
    }

    public IfCondition second() {
        return (IfCondition) this.args.get(1);
    }

    public IfCondition third() {
        return (IfCondition) this.args.get(2);
    }

    public void addArg(IfCondition c) {
        this.args.add(c);
    }

    public boolean isCompare() {
        return this.mode == Mode.COMPARE;
    }

    public Compare getCompare() {
        return this.compare;
    }

    public static IfCondition invert(IfCondition cond) {
        Mode mode = cond.getMode();
        switch (mode) {
            case COMPARE:
                return new IfCondition(cond.getCompare().invert());
            case TERNARY:
                return ternary(not(cond.first()), cond.third(), cond.second());
            case NOT:
                return cond.first();
            case AND:
            case OR:
                List<IfCondition> args = cond.getArgs();
                List<IfCondition> newArgs = new ArrayList(args.size());
                for (IfCondition arg : args) {
                    newArgs.add(invert(arg));
                }
                return new IfCondition(mode == Mode.AND ? Mode.OR : Mode.AND, newArgs);
            default:
                throw new JadxRuntimeException("Unknown mode for invert: " + mode);
        }
    }

    public static IfCondition not(IfCondition cond) {
        if (cond.getMode() == Mode.NOT) {
            return cond.first();
        }
        return new IfCondition(Mode.NOT, Collections.singletonList(cond));
    }

    public static IfCondition simplify(IfCondition cond) {
        if (cond.isCompare()) {
            Compare c = cond.getCompare();
            simplifyCmpOp(c);
            if (c.getOp() == IfOp.EQ && c.getB().isLiteral() && c.getB().equals(LiteralArg.FALSE)) {
                return not(new IfCondition(c.invert()));
            }
            c.normalize();
            return cond;
        }
        List<IfCondition> args = null;
        for (int i = 0; i < cond.getArgs().size(); i++) {
            IfCondition arg = (IfCondition) cond.getArgs().get(i);
            IfCondition simpl = simplify(arg);
            if (simpl != arg) {
                if (args == null) {
                    args = new ArrayList(cond.getArgs());
                }
                args.set(i, simpl);
            }
        }
        if (args != null) {
            cond = new IfCondition(cond.getMode(), args);
        }
        if (cond.getMode() == Mode.NOT && cond.first().getMode() == Mode.NOT) {
            cond = invert(cond.first());
        }
        if (cond.getMode() == Mode.TERNARY && cond.first().getMode() == Mode.NOT) {
            cond = invert(cond);
        }
        if (cond.getMode() != Mode.OR && cond.getMode() != Mode.AND) {
            return cond;
        }
        int count = cond.getArgs().size();
        if (count <= 1) {
            return cond;
        }
        int negCount = 0;
        for (IfCondition arg2 : cond.getArgs()) {
            if (arg2.getMode() == Mode.NOT || (arg2.isCompare() && arg2.getCompare().getOp() == IfOp.NE)) {
                negCount++;
            }
        }
        if (negCount > count / 2) {
            return not(invert(cond));
        }
        return cond;
    }

    private static void simplifyCmpOp(Compare c) {
        if (c.getA().isInsnWrap() && c.getB().isLiteral() && ((LiteralArg) c.getB()).getLiteral() == 0) {
            InsnNode wrapInsn = ((InsnWrapArg) c.getA()).getWrapInsn();
            InsnType type = wrapInsn.getType();
            if (type == InsnType.CMP_L || type == InsnType.CMP_G) {
                IfNode insn = c.getInsn();
                insn.changeCondition(insn.getOp(), wrapInsn.getArg(0), wrapInsn.getArg(1));
            }
        }
    }

    public List<RegisterArg> getRegisterArgs() {
        List<RegisterArg> list = new LinkedList();
        if (this.mode == Mode.COMPARE) {
            this.compare.getInsn().getRegisterArgs(list);
        } else {
            for (IfCondition arg : this.args) {
                list.addAll(arg.getRegisterArgs());
            }
        }
        return list;
    }

    public String toString() {
        switch (this.mode) {
            case COMPARE:
                return this.compare.toString();
            case TERNARY:
                return first() + " ? " + second() + " : " + third();
            case NOT:
                return "!" + first();
            case AND:
            case OR:
                String op = this.mode == Mode.OR ? " || " : " && ";
                StringBuilder sb = new StringBuilder();
                sb.append('(');
                Iterator<IfCondition> it = this.args.iterator();
                while (it.hasNext()) {
                    sb.append((IfCondition) it.next());
                    if (it.hasNext()) {
                        sb.append(op);
                    }
                }
                sb.append(')');
                return sb.toString();
            default:
                return "??";
        }
    }
}
