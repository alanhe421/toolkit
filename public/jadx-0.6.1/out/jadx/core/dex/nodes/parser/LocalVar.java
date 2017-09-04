package jadx.core.dex.nodes.parser;

import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.nodes.DexNode;
import jadx.core.utils.InsnUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class LocalVar {
    private static final Logger LOG = LoggerFactory.getLogger(LocalVar.class);
    private int endAddr;
    private boolean isEnd;
    private String name;
    private final int regNum;
    private int startAddr;
    private ArgType type;

    public LocalVar(DexNode dex, int rn, int nameId, int typeId, int signId) {
        String sign = null;
        this.regNum = rn;
        String name = nameId == -1 ? null : dex.getString(nameId);
        ArgType type = typeId == -1 ? null : dex.getType(typeId);
        if (signId != -1) {
            sign = dex.getString(signId);
        }
        init(name, type, sign);
    }

    public LocalVar(RegisterArg arg) {
        this.regNum = arg.getRegNum();
        init(arg.getName(), arg.getType(), null);
    }

    private void init(String name, ArgType type, String sign) {
        if (sign != null) {
            try {
                ArgType gType = ArgType.generic(sign);
                if (checkSignature(type, sign, gType)) {
                    type = gType;
                }
            } catch (Exception e) {
                LOG.error("Can't parse signature for local variable: {}", sign, e);
            }
        }
        this.name = name;
        this.type = type;
    }

    private boolean checkSignature(ArgType type, String sign, ArgType gType) {
        ArgType el = gType.getArrayRootElement();
        if (!el.isGeneric()) {
            return el.isGenericType();
        }
        if (!type.getArrayRootElement().getObject().equals(el.getObject())) {
            LOG.warn("Generic type in debug info not equals: {} != {}", type, gType);
        }
        return true;
    }

    public void start(int addr, int line) {
        this.isEnd = false;
        this.startAddr = addr;
    }

    public boolean end(int addr, int line) {
        if (this.isEnd) {
            return false;
        }
        this.isEnd = true;
        this.endAddr = addr;
        return true;
    }

    public int getRegNum() {
        return this.regNum;
    }

    public String getName() {
        return this.name;
    }

    public ArgType getType() {
        return this.type;
    }

    public boolean isEnd() {
        return this.isEnd;
    }

    public int getStartAddr() {
        return this.startAddr;
    }

    public int getEndAddr() {
        return this.endAddr;
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return super.toString() + " " + (this.isEnd ? "end: " + InsnUtils.formatOffset(this.startAddr) + "-" + InsnUtils.formatOffset(this.endAddr) : "active: " + InsnUtils.formatOffset(this.startAddr));
    }
}
