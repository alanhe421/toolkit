package jadx.core.dex.attributes.nodes;

import jadx.core.utils.InsnUtils;

public class JumpInfo {
    private final int dest;
    private final int src;

    public JumpInfo(int src, int dest) {
        this.src = src;
        this.dest = dest;
    }

    public int getSrc() {
        return this.src;
    }

    public int getDest() {
        return this.dest;
    }

    public int hashCode() {
        return (this.dest * 31) + this.src;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        JumpInfo other = (JumpInfo) obj;
        if (this.dest == other.dest && this.src == other.src) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "JUMP: " + InsnUtils.formatOffset(this.src) + " -> " + InsnUtils.formatOffset(this.dest);
    }
}
