package jadx.core.codegen;

import jadx.api.IJadxArgs;
import jadx.core.dex.nodes.ClassNode;
import jadx.core.dex.visitors.AbstractVisitor;
import jadx.core.utils.exceptions.CodegenException;

public class CodeGen extends AbstractVisitor {
    private final IJadxArgs args;

    public CodeGen(IJadxArgs args) {
        this.args = args;
    }

    public boolean visit(ClassNode cls) throws CodegenException {
        CodeWriter clsCode = new ClassGen(cls, this.args).makeClass();
        clsCode.finish();
        cls.setCode(clsCode);
        return false;
    }
}
