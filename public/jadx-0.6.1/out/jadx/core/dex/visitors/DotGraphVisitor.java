package jadx.core.dex.visitors;

import jadx.core.codegen.CodeWriter;
import jadx.core.codegen.MethodGen;
import jadx.core.deobf.Deobfuscator;
import jadx.core.dex.attributes.IAttributeNode;
import jadx.core.dex.instructions.IfNode;
import jadx.core.dex.instructions.InsnType;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.IBlock;
import jadx.core.dex.nodes.IContainer;
import jadx.core.dex.nodes.IRegion;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.trycatch.ExceptionHandler;
import jadx.core.utils.InsnUtils;
import jadx.core.utils.RegionUtils;
import jadx.core.utils.StringUtils;
import jadx.core.utils.Utils;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DotGraphVisitor extends AbstractVisitor {
    private static final Logger LOG = LoggerFactory.getLogger(DotGraphVisitor.class);
    private static final String NL = "\\l";
    private static final boolean PRINT_DOMINATORS = false;
    private final File dir;
    private final boolean rawInsn;
    private final boolean useRegions;

    private class DumpDotGraph {
        private final CodeWriter conn;
        private final CodeWriter dot;

        private DumpDotGraph() {
            this.dot = new CodeWriter();
            this.conn = new CodeWriter();
        }

        public void process(MethodNode mth) {
            this.dot.startLine("digraph \"CFG for");
            this.dot.add(escape(mth.getParentClass() + Deobfuscator.CLASS_NAME_SEPARATOR + mth.getMethodInfo().getShortId()));
            this.dot.add("\" {");
            if (!DotGraphVisitor.this.useRegions) {
                for (BlockNode block : mth.getBasicBlocks()) {
                    processBlock(mth, block, false);
                }
            } else if (mth.getRegion() != null) {
                processMethodRegion(mth);
            } else {
                return;
            }
            this.dot.startLine("MethodNode[shape=record,label=\"{");
            this.dot.add(escape(mth.getAccessFlags().makeString()));
            this.dot.add(escape(mth.getReturnType() + " " + mth.getParentClass() + Deobfuscator.CLASS_NAME_SEPARATOR + mth.getName() + "(" + Utils.listToString(mth.getArguments(true)) + ") "));
            String attrs = attributesString(mth);
            if (attrs.length() != 0) {
                this.dot.add(" | ").add(attrs);
            }
            this.dot.add("}\"];");
            this.dot.startLine("MethodNode -> ").add(makeName(mth.getEnterBlock())).add(';');
            this.dot.add(this.conn.toString());
            this.dot.startLine('}');
            this.dot.startLine();
            this.dot.save(DotGraphVisitor.this.dir, mth.getParentClass().getClassInfo().getFullPath() + "_graphs", StringUtils.escape(mth.getMethodInfo().getShortId()) + (DotGraphVisitor.this.useRegions ? ".regions" : "") + (DotGraphVisitor.this.rawInsn ? ".raw" : "") + ".dot");
        }

        private void processMethodRegion(MethodNode mth) {
            processRegion(mth, mth.getRegion());
            for (ExceptionHandler h : mth.getExceptionHandlers()) {
                if (h.getHandlerRegion() != null) {
                    processRegion(mth, h.getHandlerRegion());
                }
            }
            Set<IBlock> regionsBlocks = new HashSet(mth.getBasicBlocks().size());
            RegionUtils.getAllRegionBlocks(mth.getRegion(), regionsBlocks);
            for (ExceptionHandler handler : mth.getExceptionHandlers()) {
                IContainer handlerRegion = handler.getHandlerRegion();
                if (handlerRegion != null) {
                    RegionUtils.getAllRegionBlocks(handlerRegion, regionsBlocks);
                }
            }
            for (BlockNode block : mth.getBasicBlocks()) {
                if (!regionsBlocks.contains(block)) {
                    processBlock(mth, block, true);
                }
            }
        }

        private void processRegion(MethodNode mth, IContainer region) {
            if (region instanceof IRegion) {
                IRegion r = (IRegion) region;
                this.dot.startLine("subgraph " + makeName(region) + " {");
                this.dot.startLine("label = \"").add(r.toString());
                String attrs = attributesString(r);
                if (attrs.length() != 0) {
                    this.dot.add(" | ").add(attrs);
                }
                this.dot.add("\";");
                this.dot.startLine("node [shape=record,color=blue];");
                for (IContainer c : r.getSubBlocks()) {
                    processRegion(mth, c);
                }
                this.dot.startLine('}');
            } else if (region instanceof BlockNode) {
                processBlock(mth, (BlockNode) region, false);
            } else if (region instanceof IBlock) {
                processIBlock(mth, (IBlock) region, false);
            }
        }

        private void processBlock(MethodNode mth, BlockNode block, boolean error) {
            String attrs = attributesString(block);
            this.dot.startLine(makeName(block));
            this.dot.add(" [shape=record,");
            if (error) {
                this.dot.add("color=red,");
            }
            this.dot.add("label=\"{");
            this.dot.add(String.valueOf(block.getId())).add("\\:\\ ");
            this.dot.add(InsnUtils.formatOffset(block.getStartOffset()));
            if (attrs.length() != 0) {
                this.dot.add('|').add(attrs);
            }
            String insns = insertInsns(mth, block);
            if (insns.length() != 0) {
                this.dot.add('|').add(insns);
            }
            this.dot.add("}\"];");
            BlockNode falsePath = null;
            List<InsnNode> list = block.getInstructions();
            if (!list.isEmpty() && ((InsnNode) list.get(0)).getType() == InsnType.IF) {
                falsePath = ((IfNode) list.get(0)).getElseBlock();
            }
            for (BlockNode next : block.getSuccessors()) {
                addEdge(block, next, next == falsePath ? "[style=dashed]" : "");
            }
        }

        private void processIBlock(MethodNode mth, IBlock block, boolean error) {
            String attrs = attributesString(block);
            this.dot.startLine(makeName(block));
            this.dot.add(" [shape=record,");
            if (error) {
                this.dot.add("color=red,");
            }
            this.dot.add("label=\"{");
            if (attrs.length() != 0) {
                this.dot.add(attrs);
            }
            String insns = insertInsns(mth, block);
            if (insns.length() != 0) {
                this.dot.add('|').add(insns);
            }
            this.dot.add("}\"];");
        }

        private void addEdge(BlockNode from, BlockNode to, String style) {
            this.conn.startLine(makeName(from)).add(" -> ").add(makeName(to));
            this.conn.add(style);
            this.conn.add(';');
        }

        private String attributesString(IAttributeNode block) {
            StringBuilder attrs = new StringBuilder();
            for (String attr : block.getAttributesStringsList()) {
                attrs.append(escape(attr)).append(DotGraphVisitor.NL);
            }
            return attrs.toString();
        }

        private String makeName(IContainer c) {
            if (c instanceof BlockNode) {
                return "Node_" + ((BlockNode) c).getId();
            }
            if (c instanceof IBlock) {
                return "Node_" + c.getClass().getSimpleName() + "_" + c.hashCode();
            }
            return "cluster_" + c.getClass().getSimpleName() + "_" + c.hashCode();
        }

        private String insertInsns(MethodNode mth, IBlock block) {
            StringBuilder str;
            if (DotGraphVisitor.this.rawInsn) {
                str = new StringBuilder();
                for (InsnNode insn : block.getInstructions()) {
                    str.append(escape(insn + " " + insn.getAttributesString()));
                    str.append(DotGraphVisitor.NL);
                }
                return str.toString();
            }
            CodeWriter code = new CodeWriter();
            List<InsnNode> instructions = block.getInstructions();
            MethodGen.addFallbackInsns(code, mth, (InsnNode[]) instructions.toArray(new InsnNode[instructions.size()]), false);
            str = escape(code.newLine().toString());
            if (str.startsWith(DotGraphVisitor.NL)) {
                return str.substring(DotGraphVisitor.NL.length());
            }
            return str;
        }

        private String escape(String string) {
            return string.replace("\\", "").replace("/", "\\/").replace(">", "\\>").replace("<", "\\<").replace("{", "\\{").replace("}", "\\}").replace("\"", "\\\"").replace("-", "\\-").replace("|", "\\|").replace("\n", DotGraphVisitor.NL);
        }
    }

    public static DotGraphVisitor dump(File outDir) {
        return new DotGraphVisitor(outDir, false, false);
    }

    public static DotGraphVisitor dumpRaw(File outDir) {
        return new DotGraphVisitor(outDir, false, true);
    }

    public static DotGraphVisitor dumpRegions(File outDir) {
        return new DotGraphVisitor(outDir, true, false);
    }

    public static DotGraphVisitor dumpRawRegions(File outDir) {
        return new DotGraphVisitor(outDir, true, true);
    }

    private DotGraphVisitor(File outDir, boolean useRegions, boolean rawInsn) {
        this.dir = outDir;
        this.useRegions = useRegions;
        this.rawInsn = rawInsn;
        Logger logger = LOG;
        String str = "DOT {}{}graph dump dir: {}";
        Object[] objArr = new Object[3];
        objArr[0] = useRegions ? "regions " : "";
        objArr[1] = rawInsn ? "raw " : "";
        objArr[2] = outDir.getAbsolutePath();
        logger.debug(str, objArr);
    }

    public void visit(MethodNode mth) {
        if (!mth.isNoCode()) {
            new DumpDotGraph().process(mth);
        }
    }
}
