package jadx.core;

import jadx.core.codegen.CodeGen;
import jadx.core.dex.nodes.ClassNode;
import jadx.core.dex.nodes.ProcessState;
import jadx.core.dex.visitors.DepthTraversal;
import jadx.core.dex.visitors.IDexTreeVisitor;
import jadx.core.utils.ErrorsCounter;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ProcessClass {
    private static final Logger LOG = LoggerFactory.getLogger(ProcessClass.class);

    private ProcessClass() {
    }

    public static void process(ClassNode cls, List<IDexTreeVisitor> passes, @Nullable CodeGen codeGen) {
        if (codeGen != null || cls.getState() != ProcessState.PROCESSED) {
            synchronized (cls) {
                try {
                    if (cls.getState() == ProcessState.NOT_LOADED) {
                        cls.load();
                        cls.setState(ProcessState.STARTED);
                        for (IDexTreeVisitor visitor : passes) {
                            DepthTraversal.visit(visitor, cls);
                        }
                        cls.setState(ProcessState.PROCESSED);
                    }
                    if (cls.getState() == ProcessState.PROCESSED && codeGen != null) {
                        processDependencies(cls, passes);
                        codeGen.visit(cls);
                        cls.setState(ProcessState.GENERATED);
                    }
                    if (cls.getState() == ProcessState.GENERATED) {
                        cls.unload();
                        cls.setState(ProcessState.UNLOADED);
                    }
                } catch (Exception e) {
                    ErrorsCounter.classError(cls, e.getClass().getSimpleName(), e);
                    if (cls.getState() == ProcessState.GENERATED) {
                        cls.unload();
                        cls.setState(ProcessState.UNLOADED);
                    }
                } catch (Throwable th) {
                    if (cls.getState() == ProcessState.GENERATED) {
                        cls.unload();
                        cls.setState(ProcessState.UNLOADED);
                    }
                }
            }
        }
    }

    static void processDependencies(ClassNode cls, List<IDexTreeVisitor> passes) {
        for (ClassNode depCls : cls.getDependencies()) {
            process(depCls, passes, null);
        }
    }
}
