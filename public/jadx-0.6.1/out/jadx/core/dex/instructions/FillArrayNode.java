package jadx.core.dex.instructions;

import com.android.dx.io.instructions.FillArrayDataPayloadDecodedInstruction;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.instructions.args.LiteralArg;
import jadx.core.dex.instructions.args.PrimitiveType;
import jadx.core.dex.nodes.DexNode;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.utils.exceptions.JadxRuntimeException;
import java.util.ArrayList;
import java.util.List;

public final class FillArrayNode extends InsnNode {
    private final Object data;
    private ArgType elemType;
    private final int size;

    public FillArrayNode(int resReg, FillArrayDataPayloadDecodedInstruction payload) {
        ArgType elType;
        super(InsnType.FILL_ARRAY, 0);
        switch (payload.getElementWidthUnit()) {
            case (short) 1:
                elType = ArgType.unknown(PrimitiveType.BOOLEAN, PrimitiveType.BYTE);
                break;
            case (short) 2:
                elType = ArgType.unknown(PrimitiveType.SHORT, PrimitiveType.CHAR);
                break;
            case (short) 4:
                elType = ArgType.unknown(PrimitiveType.INT, PrimitiveType.FLOAT);
                break;
            case (short) 8:
                elType = ArgType.unknown(PrimitiveType.LONG, PrimitiveType.DOUBLE);
                break;
            default:
                throw new JadxRuntimeException("Unknown array element width: " + payload.getElementWidthUnit());
        }
        setResult(InsnArg.reg(resReg, ArgType.array(elType)));
        this.data = payload.getData();
        this.size = payload.getSize();
        this.elemType = elType;
    }

    public Object getData() {
        return this.data;
    }

    public int getSize() {
        return this.size;
    }

    public ArgType getElementType() {
        return this.elemType;
    }

    public void mergeElementType(DexNode dex, ArgType foundElemType) {
        ArgType r = ArgType.merge(dex, this.elemType, foundElemType);
        if (r != null) {
            this.elemType = r;
        }
    }

    public List<LiteralArg> getLiteralArgs() {
        List<LiteralArg> list = new ArrayList(this.size);
        Object array = this.data;
        if (array instanceof int[]) {
            for (int b : (int[]) array) {
                list.add(InsnArg.lit((long) b, this.elemType));
            }
        } else if (array instanceof byte[]) {
            for (byte b2 : (byte[]) array) {
                list.add(InsnArg.lit((long) b2, this.elemType));
            }
        } else if (array instanceof short[]) {
            for (short b3 : (short[]) array) {
                list.add(InsnArg.lit((long) b3, this.elemType));
            }
        } else if (array instanceof long[]) {
            for (long b4 : (long[]) array) {
                list.add(InsnArg.lit(b4, this.elemType));
            }
        } else {
            throw new JadxRuntimeException("Unknown type: " + this.data.getClass() + ", expected: " + this.elemType);
        }
        return list;
    }

    public boolean isSame(InsnNode obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FillArrayNode) || !super.isSame(obj)) {
            return false;
        }
        FillArrayNode other = (FillArrayNode) obj;
        if (this.elemType.equals(other.elemType) && this.data == other.data) {
            return true;
        }
        return false;
    }
}
