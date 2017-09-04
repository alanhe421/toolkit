package jadx.core.dex.visitors;

import jadx.core.dex.nodes.ClassNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.nodes.RootNode;
import jadx.core.utils.exceptions.JadxException;

public interface IDexTreeVisitor {
    void init(RootNode rootNode) throws JadxException;

    void visit(MethodNode methodNode) throws JadxException;

    boolean visit(ClassNode classNode) throws JadxException;
}
