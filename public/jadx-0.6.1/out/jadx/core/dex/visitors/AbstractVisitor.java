package jadx.core.dex.visitors;

import jadx.core.dex.nodes.ClassNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.nodes.RootNode;
import jadx.core.utils.exceptions.JadxException;

public class AbstractVisitor implements IDexTreeVisitor {
    public void init(RootNode root) throws JadxException {
    }

    public boolean visit(ClassNode cls) throws JadxException {
        return true;
    }

    public void visit(MethodNode mth) throws JadxException {
    }
}
