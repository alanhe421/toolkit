package jadx.core.dex.instructions;

import com.android.dex.Code;
import com.android.dx.io.OpcodeInfo;
import com.android.dx.io.instructions.DecodedInstruction;
import com.android.dx.io.instructions.FillArrayDataPayloadDecodedInstruction;
import com.android.dx.io.instructions.PackedSwitchPayloadDecodedInstruction;
import com.android.dx.io.instructions.ShortArrayCodeInput;
import com.android.dx.io.instructions.SparseSwitchPayloadDecodedInstruction;
import jadx.core.dex.info.FieldInfo;
import jadx.core.dex.info.MethodInfo;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.instructions.args.PrimitiveType;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.nodes.DexNode;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.utils.InsnUtils;
import jadx.core.utils.exceptions.DecodeException;

public class InsnDecoder {
    private final DexNode dex = this.method.dex();
    private DecodedInstruction[] insnArr;
    private final MethodNode method;

    public InsnDecoder(MethodNode mthNode) {
        this.method = mthNode;
    }

    public void decodeInsns(Code mthCode) throws DecodeException {
        short[] encodedInstructions = mthCode.getInstructions();
        DecodedInstruction[] decoded = new DecodedInstruction[encodedInstructions.length];
        ShortArrayCodeInput in = new ShortArrayCodeInput(encodedInstructions);
        while (in.hasMore()) {
            try {
                decoded[in.cursor()] = DecodedInstruction.decode(in);
            } catch (Exception e) {
                throw new DecodeException(this.method, "", e);
            }
        }
        this.insnArr = decoded;
    }

    public InsnNode[] process() throws DecodeException {
        InsnNode[] instructions = new InsnNode[this.insnArr.length];
        for (int i = 0; i < this.insnArr.length; i++) {
            DecodedInstruction rawInsn = this.insnArr[i];
            if (rawInsn != null) {
                InsnNode insn = decode(rawInsn, i);
                if (insn != null) {
                    insn.setOffset(i);
                }
                instructions[i] = insn;
            } else {
                instructions[i] = null;
            }
        }
        this.insnArr = null;
        return instructions;
    }

    private InsnNode decode(DecodedInstruction insn, int offset) throws DecodeException {
        InsnNode node;
        FieldInfo field;
        switch (insn.getOpcode()) {
            case 0:
            case 256:
            case 512:
            case 768:
                return null;
            case 1:
            case 2:
            case 3:
                return insn(InsnType.MOVE, InsnArg.reg(insn, 0, ArgType.NARROW), InsnArg.reg(insn, 1, ArgType.NARROW));
            case 4:
            case 5:
            case 6:
                return insn(InsnType.MOVE, InsnArg.reg(insn, 0, ArgType.WIDE), InsnArg.reg(insn, 1, ArgType.WIDE));
            case 7:
            case 8:
            case 9:
                return insn(InsnType.MOVE, InsnArg.reg(insn, 0, ArgType.UNKNOWN_OBJECT), InsnArg.reg(insn, 1, ArgType.UNKNOWN_OBJECT));
            case 10:
            case 11:
            case 12:
                return new InsnNode(InsnType.NOP, 0);
            case 13:
                return insn(InsnType.MOVE_EXCEPTION, InsnArg.reg(insn, 0, ArgType.unknown(PrimitiveType.OBJECT)));
            case 14:
                return new InsnNode(InsnType.RETURN, 0);
            case 15:
            case 16:
            case 17:
                return insn(InsnType.RETURN, null, InsnArg.reg(insn, 0, this.method.getReturnType()));
            case 18:
            case 19:
            case 20:
            case 21:
                return insn(InsnType.CONST, InsnArg.reg(insn, 0, ArgType.NARROW), InsnArg.lit(insn, ArgType.NARROW));
            case 22:
            case 23:
            case 24:
            case 25:
                return insn(InsnType.CONST, InsnArg.reg(insn, 0, ArgType.WIDE), InsnArg.lit(insn, ArgType.WIDE));
            case 26:
            case 27:
                node = new ConstStringNode(this.dex.getString(insn.getIndex()));
                node.setResult(InsnArg.reg(insn, 0, ArgType.STRING));
                return node;
            case 28:
                node = new ConstClassNode(this.dex.getType(insn.getIndex()));
                node.setResult(InsnArg.reg(insn, 0, ArgType.CLASS));
                return node;
            case 29:
                return insn(InsnType.MONITOR_ENTER, null, InsnArg.reg(insn, 0, ArgType.UNKNOWN_OBJECT));
            case 30:
                return insn(InsnType.MONITOR_EXIT, null, InsnArg.reg(insn, 0, ArgType.UNKNOWN_OBJECT));
            case 31:
                ArgType castType = this.dex.getType(insn.getIndex());
                node = new IndexInsnNode(InsnType.CHECK_CAST, castType, 1);
                node.setResult(InsnArg.reg(insn, 0, castType));
                node.addArg(InsnArg.reg(insn, 0, ArgType.UNKNOWN_OBJECT));
                return node;
            case 32:
                node = new IndexInsnNode(InsnType.INSTANCE_OF, this.dex.getType(insn.getIndex()), 1);
                node.setResult(InsnArg.reg(insn, 0, ArgType.BOOLEAN));
                node.addArg(InsnArg.reg(insn, 1, ArgType.UNKNOWN_OBJECT));
                return node;
            case 33:
                node = new InsnNode(InsnType.ARRAY_LENGTH, 1);
                node.setResult(InsnArg.reg(insn, 0, ArgType.INT));
                node.addArg(InsnArg.reg(insn, 1, ArgType.array(ArgType.UNKNOWN)));
                return node;
            case 34:
                return insn(InsnType.NEW_INSTANCE, InsnArg.reg(insn, 0, this.dex.getType(insn.getIndex())));
            case 35:
                ArgType arrType = this.dex.getType(insn.getIndex());
                return new NewArrayNode(arrType, InsnArg.reg(insn, 0, arrType), InsnArg.reg(insn, 1, ArgType.INT));
            case 36:
                return filledNewArray(insn, offset, false);
            case 37:
                return filledNewArray(insn, offset, true);
            case 38:
                return fillArray(insn);
            case 39:
                return insn(InsnType.THROW, null, InsnArg.reg(insn, 0, ArgType.unknown(PrimitiveType.OBJECT)));
            case 40:
            case 41:
            case 42:
                return new GotoNode(insn.getTarget());
            case 43:
                return decodeSwitch(insn, offset, true);
            case 44:
                return decodeSwitch(insn, offset, false);
            case 45:
                return cmp(insn, InsnType.CMP_L, ArgType.FLOAT);
            case 46:
                return cmp(insn, InsnType.CMP_G, ArgType.FLOAT);
            case 47:
                return cmp(insn, InsnType.CMP_L, ArgType.DOUBLE);
            case 48:
                return cmp(insn, InsnType.CMP_G, ArgType.DOUBLE);
            case 49:
                return cmp(insn, InsnType.CMP_L, ArgType.LONG);
            case 50:
            case 56:
                return new IfNode(insn, IfOp.EQ);
            case 51:
            case 57:
                return new IfNode(insn, IfOp.NE);
            case 52:
            case 58:
                return new IfNode(insn, IfOp.LT);
            case 53:
            case 59:
                return new IfNode(insn, IfOp.GE);
            case 54:
            case 60:
                return new IfNode(insn, IfOp.GT);
            case 55:
            case 61:
                return new IfNode(insn, IfOp.LE);
            case 68:
                return arrayGet(insn, ArgType.NARROW);
            case 69:
                return arrayGet(insn, ArgType.WIDE);
            case 70:
                return arrayGet(insn, ArgType.UNKNOWN_OBJECT);
            case 71:
                return arrayGet(insn, ArgType.BOOLEAN);
            case 72:
                return arrayGet(insn, ArgType.BYTE);
            case 73:
                return arrayGet(insn, ArgType.CHAR);
            case 74:
                return arrayGet(insn, ArgType.SHORT);
            case 75:
                return arrayPut(insn, ArgType.NARROW);
            case 76:
                return arrayPut(insn, ArgType.WIDE);
            case 77:
                return arrayPut(insn, ArgType.UNKNOWN_OBJECT);
            case 78:
                return arrayPut(insn, ArgType.BOOLEAN);
            case 79:
                return arrayPut(insn, ArgType.BYTE);
            case 80:
                return arrayPut(insn, ArgType.CHAR);
            case 81:
                return arrayPut(insn, ArgType.SHORT);
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
                field = FieldInfo.fromDex(this.dex, insn.getIndex());
                node = new IndexInsnNode(InsnType.IGET, field, 1);
                node.setResult(InsnArg.reg(insn, 0, field.getType()));
                node.addArg(InsnArg.reg(insn, 1, field.getDeclClass().getType()));
                return node;
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
                field = FieldInfo.fromDex(this.dex, insn.getIndex());
                node = new IndexInsnNode(InsnType.IPUT, field, 2);
                node.addArg(InsnArg.reg(insn, 0, field.getType()));
                node.addArg(InsnArg.reg(insn, 1, field.getDeclClass().getType()));
                return node;
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 102:
                field = FieldInfo.fromDex(this.dex, insn.getIndex());
                node = new IndexInsnNode(InsnType.SGET, field, 0);
                node.setResult(InsnArg.reg(insn, 0, field.getType()));
                return node;
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
                field = FieldInfo.fromDex(this.dex, insn.getIndex());
                node = new IndexInsnNode(InsnType.SPUT, field, 1);
                node.addArg(InsnArg.reg(insn, 0, field.getType()));
                return node;
            case 110:
                return invoke(insn, offset, InvokeType.VIRTUAL, false);
            case 111:
                return invoke(insn, offset, InvokeType.SUPER, false);
            case 112:
                return invoke(insn, offset, InvokeType.DIRECT, false);
            case 113:
                return invoke(insn, offset, InvokeType.STATIC, false);
            case 114:
                return invoke(insn, offset, InvokeType.INTERFACE, false);
            case 116:
                return invoke(insn, offset, InvokeType.VIRTUAL, true);
            case 117:
                return invoke(insn, offset, InvokeType.SUPER, true);
            case 118:
                return invoke(insn, offset, InvokeType.DIRECT, true);
            case 119:
                return invoke(insn, offset, InvokeType.STATIC, true);
            case 120:
                return invoke(insn, offset, InvokeType.INTERFACE, true);
            case 123:
                return neg(insn, ArgType.INT);
            case 125:
                return neg(insn, ArgType.LONG);
            case 127:
                return neg(insn, ArgType.FLOAT);
            case 128:
                return neg(insn, ArgType.DOUBLE);
            case 129:
                return cast(insn, ArgType.INT, ArgType.LONG);
            case 130:
                return cast(insn, ArgType.INT, ArgType.FLOAT);
            case 131:
                return cast(insn, ArgType.INT, ArgType.DOUBLE);
            case 132:
                return cast(insn, ArgType.LONG, ArgType.INT);
            case 133:
                return cast(insn, ArgType.LONG, ArgType.FLOAT);
            case 134:
                return cast(insn, ArgType.LONG, ArgType.DOUBLE);
            case 135:
                return cast(insn, ArgType.FLOAT, ArgType.INT);
            case 136:
                return cast(insn, ArgType.FLOAT, ArgType.LONG);
            case 137:
                return cast(insn, ArgType.FLOAT, ArgType.DOUBLE);
            case 138:
                return cast(insn, ArgType.DOUBLE, ArgType.INT);
            case 139:
                return cast(insn, ArgType.DOUBLE, ArgType.LONG);
            case 140:
                return cast(insn, ArgType.DOUBLE, ArgType.FLOAT);
            case 141:
                return cast(insn, ArgType.INT, ArgType.BYTE);
            case 142:
                return cast(insn, ArgType.INT, ArgType.CHAR);
            case 143:
                return cast(insn, ArgType.INT, ArgType.SHORT);
            case 144:
            case 176:
                return arith(insn, ArithOp.ADD, ArgType.INT);
            case 145:
            case 177:
                return arith(insn, ArithOp.SUB, ArgType.INT);
            case 146:
            case 178:
                return arith(insn, ArithOp.MUL, ArgType.INT);
            case 147:
            case 179:
                return arith(insn, ArithOp.DIV, ArgType.INT);
            case 148:
            case 180:
                return arith(insn, ArithOp.REM, ArgType.INT);
            case 149:
            case 181:
                return arith(insn, ArithOp.AND, ArgType.INT);
            case 150:
            case 182:
                return arith(insn, ArithOp.OR, ArgType.INT);
            case 151:
            case 183:
                return arith(insn, ArithOp.XOR, ArgType.INT);
            case 152:
            case 184:
                return arith(insn, ArithOp.SHL, ArgType.INT);
            case 153:
            case 185:
                return arith(insn, ArithOp.SHR, ArgType.INT);
            case 154:
            case 186:
                return arith(insn, ArithOp.USHR, ArgType.INT);
            case 155:
            case 187:
                return arith(insn, ArithOp.ADD, ArgType.LONG);
            case 156:
            case 188:
                return arith(insn, ArithOp.SUB, ArgType.LONG);
            case 157:
            case 189:
                return arith(insn, ArithOp.MUL, ArgType.LONG);
            case 158:
            case 190:
                return arith(insn, ArithOp.DIV, ArgType.LONG);
            case 159:
            case 191:
                return arith(insn, ArithOp.REM, ArgType.LONG);
            case 160:
            case 192:
                return arith(insn, ArithOp.AND, ArgType.LONG);
            case 161:
            case 193:
                return arith(insn, ArithOp.OR, ArgType.LONG);
            case 162:
            case 194:
                return arith(insn, ArithOp.XOR, ArgType.LONG);
            case 163:
            case 195:
                return arith(insn, ArithOp.SHL, ArgType.LONG);
            case 164:
            case 196:
                return arith(insn, ArithOp.SHR, ArgType.LONG);
            case 165:
            case 197:
                return arith(insn, ArithOp.USHR, ArgType.LONG);
            case 166:
            case 198:
                return arith(insn, ArithOp.ADD, ArgType.FLOAT);
            case 167:
            case 199:
                return arith(insn, ArithOp.SUB, ArgType.FLOAT);
            case 168:
            case 200:
                return arith(insn, ArithOp.MUL, ArgType.FLOAT);
            case 169:
            case 201:
                return arith(insn, ArithOp.DIV, ArgType.FLOAT);
            case 170:
            case 202:
                return arith(insn, ArithOp.REM, ArgType.FLOAT);
            case 171:
            case 203:
                return arith(insn, ArithOp.ADD, ArgType.DOUBLE);
            case 172:
            case 204:
                return arith(insn, ArithOp.SUB, ArgType.DOUBLE);
            case 173:
            case 205:
                return arith(insn, ArithOp.MUL, ArgType.DOUBLE);
            case 174:
            case 206:
                return arith(insn, ArithOp.DIV, ArgType.DOUBLE);
            case 175:
            case 207:
                return arith(insn, ArithOp.REM, ArgType.DOUBLE);
            case 208:
            case 216:
                return arithLit(insn, ArithOp.ADD, ArgType.INT);
            case 209:
            case 217:
                return new ArithNode(ArithOp.SUB, InsnArg.reg(insn, 0, ArgType.INT), InsnArg.lit(insn, ArgType.INT), InsnArg.reg(insn, 1, ArgType.INT));
            case 210:
            case 218:
                return arithLit(insn, ArithOp.MUL, ArgType.INT);
            case 211:
            case 219:
                return arithLit(insn, ArithOp.DIV, ArgType.INT);
            case 212:
            case 220:
                return arithLit(insn, ArithOp.REM, ArgType.INT);
            case 213:
            case 221:
                return arithLit(insn, ArithOp.AND, ArgType.INT);
            case 214:
            case 222:
                return arithLit(insn, ArithOp.OR, ArgType.INT);
            case 215:
            case 223:
                return arithLit(insn, ArithOp.XOR, ArgType.INT);
            case 224:
                return arithLit(insn, ArithOp.SHL, ArgType.INT);
            case 225:
                return arithLit(insn, ArithOp.SHR, ArgType.INT);
            case 226:
                return arithLit(insn, ArithOp.USHR, ArgType.INT);
            default:
                throw new DecodeException("Unknown instruction: " + OpcodeInfo.getName(insn.getOpcode()));
        }
    }

    private InsnNode decodeSwitch(DecodedInstruction insn, int offset, boolean packed) {
        int[] targets;
        Object[] keys;
        int i;
        int payloadOffset = insn.getTarget();
        DecodedInstruction payload = this.insnArr[payloadOffset];
        if (packed) {
            PackedSwitchPayloadDecodedInstruction ps = (PackedSwitchPayloadDecodedInstruction) payload;
            targets = ps.getTargets();
            keys = new Object[targets.length];
            int k = ps.getFirstKey();
            i = 0;
            while (i < keys.length) {
                int k2 = k + 1;
                keys[i] = Integer.valueOf(k);
                i++;
                k = k2;
            }
        } else {
            SparseSwitchPayloadDecodedInstruction ss = (SparseSwitchPayloadDecodedInstruction) payload;
            targets = ss.getTargets();
            keys = new Object[targets.length];
            for (i = 0; i < keys.length; i++) {
                keys[i] = Integer.valueOf(ss.getKeys()[i]);
            }
        }
        for (i = 0; i < targets.length; i++) {
            targets[i] = (targets[i] - payloadOffset) + offset;
        }
        return new SwitchNode(InsnArg.reg(insn, 0, ArgType.NARROW), keys, targets, getNextInsnOffset(this.insnArr, offset));
    }

    private InsnNode fillArray(DecodedInstruction insn) {
        return new FillArrayNode(insn.getA(), (FillArrayDataPayloadDecodedInstruction) this.insnArr[insn.getTarget()]);
    }

    private InsnNode filledNewArray(DecodedInstruction insn, int offset, boolean isRange) {
        int resReg = getMoveResultRegister(this.insnArr, offset);
        ArgType arrType = this.dex.getType(insn.getIndex());
        ArgType elType = arrType.getArrayElement();
        boolean typeImmutable = elType.isPrimitive();
        int regsCount = insn.getRegisterCount();
        InsnArg[] regs = new InsnArg[regsCount];
        int i;
        if (isRange) {
            int r = insn.getA();
            for (i = 0; i < regsCount; i++) {
                regs[i] = InsnArg.reg(r, elType, typeImmutable);
                r++;
            }
        } else {
            for (i = 0; i < regsCount; i++) {
                regs[i] = InsnArg.reg(InsnUtils.getArg(insn, i), elType, typeImmutable);
            }
        }
        InsnNode node = new FilledNewArrayNode(elType, regs.length);
        node.setResult(resReg == -1 ? null : InsnArg.reg(resReg, arrType));
        for (InsnArg arg : regs) {
            node.addArg(arg);
        }
        return node;
    }

    private InsnNode cmp(DecodedInstruction insn, InsnType itype, ArgType argType) {
        InsnNode inode = new InsnNode(itype, 2);
        inode.setResult(InsnArg.reg(insn, 0, ArgType.INT));
        inode.addArg(InsnArg.reg(insn, 1, argType));
        inode.addArg(InsnArg.reg(insn, 2, argType));
        return inode;
    }

    private InsnNode cast(DecodedInstruction insn, ArgType from, ArgType to) {
        InsnNode inode = new IndexInsnNode(InsnType.CAST, to, 1);
        inode.setResult(InsnArg.reg(insn, 0, to));
        inode.addArg(InsnArg.reg(insn, 1, from));
        return inode;
    }

    private InsnNode invoke(DecodedInstruction insn, int offset, InvokeType type, boolean isRange) {
        return new InvokeNode(MethodInfo.fromDex(this.dex, insn.getIndex()), insn, type, isRange, getMoveResultRegister(this.insnArr, offset));
    }

    private InsnNode arrayGet(DecodedInstruction insn, ArgType argType) {
        InsnNode inode = new InsnNode(InsnType.AGET, 2);
        inode.setResult(InsnArg.reg(insn, 0, argType));
        inode.addArg(InsnArg.reg(insn, 1, ArgType.unknown(PrimitiveType.ARRAY)));
        inode.addArg(InsnArg.reg(insn, 2, ArgType.INT));
        return inode;
    }

    private InsnNode arrayPut(DecodedInstruction insn, ArgType argType) {
        InsnNode inode = new InsnNode(InsnType.APUT, 3);
        inode.addArg(InsnArg.reg(insn, 1, ArgType.unknown(PrimitiveType.ARRAY)));
        inode.addArg(InsnArg.reg(insn, 2, ArgType.INT));
        inode.addArg(InsnArg.reg(insn, 0, argType));
        return inode;
    }

    private InsnNode arith(DecodedInstruction insn, ArithOp op, ArgType type) {
        return new ArithNode(insn, op, type, false);
    }

    private InsnNode arithLit(DecodedInstruction insn, ArithOp op, ArgType type) {
        return new ArithNode(insn, op, type, true);
    }

    private InsnNode neg(DecodedInstruction insn, ArgType type) {
        InsnNode inode = new InsnNode(InsnType.NEG, 1);
        inode.setResult(InsnArg.reg(insn, 0, type));
        inode.addArg(InsnArg.reg(insn, 1, type));
        return inode;
    }

    private InsnNode insn(InsnType type, RegisterArg res) {
        InsnNode node = new InsnNode(type, 0);
        node.setResult(res);
        return node;
    }

    private InsnNode insn(InsnType type, RegisterArg res, InsnArg arg) {
        InsnNode node = new InsnNode(type, 1);
        node.setResult(res);
        node.addArg(arg);
        return node;
    }

    private int getMoveResultRegister(DecodedInstruction[] insnArr, int offset) {
        int nextOffset = getNextInsnOffset(insnArr, offset);
        if (nextOffset >= 0) {
            DecodedInstruction next = insnArr[nextOffset];
            int opc = next.getOpcode();
            if (opc == 10 || opc == 11 || opc == 12) {
                return next.getA();
            }
        }
        return -1;
    }

    public static int getPrevInsnOffset(Object[] insnArr, int offset) {
        int i = offset - 1;
        while (i >= 0 && insnArr[i] == null) {
            i--;
        }
        if (i < 0) {
            return -1;
        }
        return i;
    }

    public static int getNextInsnOffset(Object[] insnArr, int offset) {
        int i = offset + 1;
        while (i < insnArr.length && insnArr[i] == null) {
            i++;
        }
        if (i >= insnArr.length) {
            return -1;
        }
        return i;
    }
}
