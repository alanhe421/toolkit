package jadx.core.dex.attributes.nodes;

import jadx.core.dex.attributes.AttrNode;

public abstract class LineAttrNode extends AttrNode {
    private int decompiledLine;
    private int sourceLine;

    public int getSourceLine() {
        return this.sourceLine;
    }

    public void setSourceLine(int sourceLine) {
        this.sourceLine = sourceLine;
    }

    public int getDecompiledLine() {
        return this.decompiledLine;
    }

    public void setDecompiledLine(int decompiledLine) {
        this.decompiledLine = decompiledLine;
    }

    public void copyLines(LineAttrNode lineAttrNode) {
        setSourceLine(lineAttrNode.getSourceLine());
        setDecompiledLine(lineAttrNode.getDecompiledLine());
    }
}
