package jadx.core.dex.nodes.parser;

import jadx.core.Consts;
import jadx.core.dex.attributes.IAttributeNode;
import jadx.core.dex.attributes.annotations.Annotation;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.utils.exceptions.JadxRuntimeException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignatureParser {
    private static final Logger LOG = LoggerFactory.getLogger(SignatureParser.class);
    private static final char STOP_CHAR = '\u0000';
    private final int end = this.sign.length();
    private int mark = 0;
    private int pos = -1;
    private final String sign;

    public SignatureParser(String signature) {
        this.sign = signature;
    }

    public static SignatureParser fromNode(IAttributeNode node) {
        Annotation a = node.getAnnotation(Consts.DALVIK_SIGNATURE);
        if (a == null) {
            return null;
        }
        return new SignatureParser(mergeSignature((List) a.getDefaultValue()));
    }

    private char next() {
        this.pos++;
        if (this.pos >= this.end) {
            return '\u0000';
        }
        return this.sign.charAt(this.pos);
    }

    private boolean lookAhead(char ch) {
        int next = this.pos + 1;
        return next < this.end && this.sign.charAt(next) == ch;
    }

    private void mark() {
        this.mark = this.pos;
    }

    private String slice() {
        if (this.mark >= this.pos) {
            return "";
        }
        return this.sign.substring(this.mark, this.pos);
    }

    private String inclusiveSlice() {
        if (this.mark >= this.pos) {
            return "";
        }
        return this.sign.substring(this.mark, this.pos + 1);
    }

    private boolean forwardTo(char lastChar) {
        int startPos = this.pos;
        char ch;
        do {
            ch = next();
            if (ch == '\u0000') {
                this.pos = startPos;
                return false;
            }
        } while (ch != lastChar);
        return true;
    }

    private void consume(char exp) {
        char c = next();
        if (exp != c) {
            throw new JadxRuntimeException("Consume wrong char: '" + c + "' != '" + exp + "', sign: " + debugString());
        }
    }

    private boolean tryConsume(char exp) {
        if (!lookAhead(exp)) {
            return false;
        }
        next();
        return true;
    }

    private String consumeUntil(char lastChar) {
        mark();
        return forwardTo(lastChar) ? slice() : null;
    }

    public ArgType consumeType() {
        char ch = next();
        mark();
        switch (ch) {
            case '\u0000':
                return null;
            case 'L':
                ArgType obj = consumeObjectType(false);
                if (obj != null) {
                    return obj;
                }
                break;
            case 'T':
                next();
                mark();
                if (forwardTo(';')) {
                    return ArgType.genericType(slice());
                }
                break;
            case '[':
                return ArgType.array(consumeType());
            default:
                ArgType type = ArgType.parse(ch);
                if (type != null) {
                    return type;
                }
                break;
        }
        throw new JadxRuntimeException("Can't parse type: " + debugString());
    }

    private ArgType consumeObjectType(boolean incompleteType) {
        mark();
        int ch;
        do {
            ch = next();
            if (ch != 0) {
                if (ch == 60) {
                    break;
                }
            } else {
                return null;
            }
        } while (ch != 59);
        String obj;
        if (ch == 59) {
            if (incompleteType) {
                obj = slice().replace('/', '.');
            } else {
                obj = inclusiveSlice();
            }
            return ArgType.object(obj);
        }
        obj = slice();
        if (!incompleteType) {
            obj = obj + ";";
        }
        ArgType[] genArr = consumeGenericArgs();
        consume('>');
        ArgType genericType = ArgType.generic(obj, genArr);
        if (lookAhead('.')) {
            consume('.');
            next();
            ArgType inner = consumeObjectType(true);
            return ArgType.genericInner(genericType, inner.getObject(), inner.getGenericTypes());
        }
        consume(';');
        return genericType;
    }

    private ArgType[] consumeGenericArgs() {
        List<ArgType> list = new LinkedList();
        do {
            ArgType type;
            if (lookAhead('*')) {
                next();
                type = ArgType.wildcard();
            } else if (lookAhead('+')) {
                next();
                type = ArgType.wildcard(consumeType(), 1);
            } else if (lookAhead('-')) {
                next();
                type = ArgType.wildcard(consumeType(), -1);
            } else {
                type = consumeType();
            }
            if (type != null) {
                list.add(type);
            }
            if (type == null) {
                break;
            }
        } while (!lookAhead('>'));
        return (ArgType[]) list.toArray(new ArgType[list.size()]);
    }

    public Map<ArgType, List<ArgType>> consumeGenericMap() {
        if (!lookAhead('<')) {
            return Collections.emptyMap();
        }
        Map<ArgType, List<ArgType>> map = new LinkedHashMap(2);
        consume('<');
        while (!lookAhead('>') && next() != '\u0000') {
            String id = consumeUntil(':');
            if (id == null) {
                LOG.error("Can't parse generic map: {}", this.sign);
                return Collections.emptyMap();
            }
            tryConsume(':');
            map.put(ArgType.genericType(id), consumeExtendsTypesList());
        }
        consume('>');
        return map;
    }

    private List<ArgType> consumeExtendsTypesList() {
        List types = Collections.emptyList();
        boolean next;
        do {
            ArgType argType = consumeType();
            if (!argType.equals(ArgType.OBJECT)) {
                if (types.isEmpty()) {
                    types = new LinkedList();
                }
                types.add(argType);
            }
            next = lookAhead(':');
            if (next) {
                consume(':');
                continue;
            }
        } while (next);
        return types;
    }

    public List<ArgType> consumeMethodArgs() {
        consume('(');
        if (lookAhead(')')) {
            consume(')');
            return Collections.emptyList();
        }
        List<ArgType> args = new LinkedList();
        do {
            args.add(consumeType());
        } while (!lookAhead(')'));
        consume(')');
        return args;
    }

    private static String mergeSignature(List<String> list) {
        if (list.size() == 1) {
            return (String) list.get(0);
        }
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }
        return sb.toString();
    }

    private String debugString() {
        return this.sign + " at position " + this.pos + " ('" + this.sign.charAt(this.pos) + "')";
    }

    public String toString() {
        if (this.pos == -1) {
            return this.sign;
        }
        return this.sign.substring(0, this.mark) + '{' + this.sign.substring(this.mark, this.pos) + '}' + this.sign.substring(this.pos);
    }
}
