package jadx.core.dex.attributes.nodes;

import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.IAttribute;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.utils.Utils;
import java.util.LinkedList;
import java.util.List;

public class DeclareVariablesAttr implements IAttribute {
    private final List<RegisterArg> vars = new LinkedList();

    public Iterable<RegisterArg> getVars() {
        return this.vars;
    }

    public void addVar(RegisterArg arg) {
        this.vars.add(arg);
    }

    public AType<DeclareVariablesAttr> getType() {
        return AType.DECLARE_VARIABLES;
    }

    public String toString() {
        return "DECL_VAR: " + Utils.listToString(this.vars);
    }
}
