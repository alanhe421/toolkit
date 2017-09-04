package jadx.core.xmlgen;

import jadx.api.ResourcesLoader;
import jadx.core.codegen.CodeWriter;
import jadx.core.deobf.Deobfuscator;
import jadx.core.dex.info.ConstStorage;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.nodes.FieldNode;
import jadx.core.dex.nodes.RootNode;
import jadx.core.utils.StringUtils;
import jadx.core.utils.exceptions.JadxRuntimeException;
import jadx.core.xmlgen.entry.ValuesParser;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinaryXMLParser extends CommonBinaryParser {
    private static final String ANDROID_R_STYLE_CLS = "android.R$style";
    private static final boolean ATTR_NEW_LINE = false;
    private static final Logger LOG = LoggerFactory.getLogger(BinaryXMLParser.class);
    private final ManifestAttributes attributes;
    private String currentTag = "ERROR";
    private boolean firstElement;
    private final Map<Integer, FieldNode> localStyleMap = new HashMap();
    private String nsPrefix = "ERROR";
    private String nsURI = "ERROR";
    private final Map<Integer, String> resNames;
    private String[] strings;
    private final Map<Integer, String> styleMap = new HashMap();
    private ValuesParser valuesParser;
    private boolean wasOneLiner = false;
    private CodeWriter writer;

    public BinaryXMLParser(RootNode root) {
        try {
            for (Field f : Class.forName(ANDROID_R_STYLE_CLS).getFields()) {
                this.styleMap.put(Integer.valueOf(f.getInt(f.getType())), f.getName());
            }
        } catch (Throwable th) {
            try {
                LOG.error("R class loading failed", th);
            } catch (Exception e) {
                throw new JadxRuntimeException("BinaryXMLParser init error", e);
            }
        }
        ConstStorage constStorage = root.getConstValues();
        for (Entry<Object, FieldNode> entry : constStorage.getGlobalConstFields().entrySet()) {
            Object key = entry.getKey();
            FieldNode field = (FieldNode) entry.getValue();
            if (field.getType().equals(ArgType.INT) && (key instanceof Integer)) {
                this.localStyleMap.put((Integer) key, field);
            }
        }
        this.resNames = constStorage.getResourcesNames();
        this.attributes = new ManifestAttributes();
        this.attributes.parseAll();
    }

    public synchronized CodeWriter parse(InputStream inputStream) throws IOException {
        CodeWriter codeWriter;
        this.is = new ParserStream(inputStream);
        if (isBinaryXml()) {
            this.writer = new CodeWriter();
            this.writer.add("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            this.firstElement = true;
            decode();
            this.writer.finish();
            codeWriter = this.writer;
        } else {
            codeWriter = ResourcesLoader.loadToCodeWriter(inputStream);
        }
        return codeWriter;
    }

    private boolean isBinaryXml() throws IOException {
        this.is.mark(4);
        int v = this.is.readInt16();
        int h = this.is.readInt16();
        if (v == 3 && h == 8) {
            return true;
        }
        this.is.reset();
        return false;
    }

    void decode() throws IOException {
        int size = this.is.readInt32();
        while (this.is.getPos() < ((long) size)) {
            int type = this.is.readInt16();
            switch (type) {
                case 0:
                    break;
                case 1:
                    this.strings = parseStringPoolNoType();
                    this.valuesParser = new ValuesParser(this.strings, this.resNames);
                    break;
                case 256:
                    parseNameSpace();
                    break;
                case 257:
                    parseNameSpaceEnd();
                    break;
                case 258:
                    parseElement();
                    break;
                case 259:
                    parseElementEnd();
                    break;
                case 260:
                    parseCData();
                    break;
                case 384:
                    parseResourceMap();
                    break;
                default:
                    die("Type: 0x" + Integer.toHexString(type) + " not yet implemented");
                    break;
            }
        }
    }

    private void parseResourceMap() throws IOException {
        if (this.is.readInt16() != 8) {
            die("Header size of resmap is not 8!");
        }
        int[] ids = new int[((this.is.readInt32() - 8) / 4)];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = this.is.readInt32();
        }
    }

    private void parseNameSpace() throws IOException {
        if (this.is.readInt16() != 16) {
            die("NAMESPACE header is not 0x0010");
        }
        if (this.is.readInt32() != 24) {
            die("NAMESPACE header chunk is not 0x18 big");
        }
        int beginLineNumber = this.is.readInt32();
        int comment = this.is.readInt32();
        this.nsPrefix = this.strings[this.is.readInt32()];
        this.nsURI = this.strings[this.is.readInt32()];
    }

    private void parseNameSpaceEnd() throws IOException {
        if (this.is.readInt16() != 16) {
            die("NAMESPACE header is not 0x0010");
        }
        if (this.is.readInt32() != 24) {
            die("NAMESPACE header chunk is not 0x18 big");
        }
        int endLineNumber = this.is.readInt32();
        int comment = this.is.readInt32();
        this.nsPrefix = this.strings[this.is.readInt32()];
        this.nsURI = this.strings[this.is.readInt32()];
    }

    private void parseCData() throws IOException {
        if (this.is.readInt16() != 16) {
            die("CDATA header is not 0x10");
        }
        if (this.is.readInt32() != 28) {
            die("CDATA header chunk is not 0x1C");
        }
        int lineNumber = this.is.readInt32();
        this.is.skip(4);
        String str = this.strings[this.is.readInt32()];
        this.writer.startLine().addIndent();
        this.writer.attachSourceLine(lineNumber);
        this.writer.add(StringUtils.escapeXML(str.trim()));
        this.is.skip((long) (this.is.readInt16() - 2));
    }

    private void parseElement() throws IOException {
        if (this.firstElement) {
            this.firstElement = false;
        } else {
            this.writer.incIndent();
        }
        if (this.is.readInt16() != 16) {
            die("ELEMENT HEADER SIZE is not 0x10");
        }
        this.is.readInt32();
        int elementBegLineNumber = this.is.readInt32();
        int comment = this.is.readInt32();
        int startNS = this.is.readInt32();
        int startNSName = this.is.readInt32();
        if (!(this.wasOneLiner || "ERROR".equals(this.currentTag) || this.currentTag.equals(this.strings[startNSName]))) {
            this.writer.add(">");
        }
        this.wasOneLiner = false;
        this.currentTag = this.strings[startNSName];
        this.writer.startLine("<").add(this.currentTag);
        this.writer.attachSourceLine(elementBegLineNumber);
        if (this.is.readInt16() != 20) {
            die("startNS's attributeStart is not 0x14");
        }
        if (this.is.readInt16() != 20) {
            die("startNS's attributeSize is not 0x14");
        }
        int attributeCount = this.is.readInt16();
        int idIndex = this.is.readInt16();
        int classIndex = this.is.readInt16();
        int styleIndex = this.is.readInt16();
        if ("manifest".equals(this.currentTag) || this.writer.getIndent() == 0) {
            this.writer.add(" xmlns:android=\"").add(this.nsURI).add("\"");
        }
        int i = attributeCount == 1 ? 0 : 0;
        for (i = 0; i < attributeCount; i++) {
            parseAttribute(i, false);
        }
    }

    private void parseAttribute(int i, boolean newLine) throws IOException {
        int attributeNS = this.is.readInt32();
        int attributeName = this.is.readInt32();
        int attributeRawValue = this.is.readInt32();
        if (this.is.readInt16() != 8) {
            die("attrValSize != 0x08 not supported");
        }
        if (this.is.readInt8() != 0) {
            die("res0 is not 0");
        }
        int attrValDataType = this.is.readInt8();
        int attrValData = this.is.readInt32();
        String attrName = this.strings[attributeName];
        if (newLine) {
            this.writer.startLine().addIndent();
        } else {
            this.writer.add(' ');
        }
        if (attributeNS != -1) {
            this.writer.add(this.nsPrefix).add(':');
        }
        this.writer.add(attrName).add("=\"");
        String decodedAttr = this.attributes.decode(attrName, (long) attrValData);
        if (decodedAttr != null) {
            this.writer.add(decodedAttr);
        } else {
            decodeAttribute(attributeNS, attrValDataType, attrValData);
        }
        this.writer.add('\"');
    }

    private void decodeAttribute(int attributeNS, int attrValDataType, int attrValData) {
        if (attrValDataType == 1) {
            String name = (String) this.styleMap.get(Integer.valueOf(attrValData));
            if (name != null) {
                this.writer.add("@*");
                if (attributeNS != -1) {
                    this.writer.add(this.nsPrefix).add(':');
                }
                this.writer.add("style/").add(name.replaceAll("_", Deobfuscator.CLASS_NAME_SEPARATOR));
                return;
            }
            FieldNode field = (FieldNode) this.localStyleMap.get(Integer.valueOf(attrValData));
            if (field != null) {
                String cls = field.getParentClass().getShortName().toLowerCase();
                this.writer.add("@");
                if ("id".equals(cls)) {
                    this.writer.add('+');
                }
                this.writer.add(cls).add("/").add(field.getName());
                return;
            }
            String resName = (String) this.resNames.get(Integer.valueOf(attrValData));
            if (resName != null) {
                this.writer.add("@").add(resName);
                return;
            } else {
                this.writer.add("0x").add(Integer.toHexString(attrValData));
                return;
            }
        }
        String str = this.valuesParser.decodeValue(attrValDataType, attrValData);
        CodeWriter codeWriter = this.writer;
        if (str == null) {
            str = "null";
        }
        codeWriter.add(str);
    }

    private void parseElementEnd() throws IOException {
        if (this.is.readInt16() != 16) {
            die("ELEMENT END header is not 0x10");
        }
        if (this.is.readInt32() != 24) {
            die("ELEMENT END header chunk is not 0x18 big");
        }
        int endLineNumber = this.is.readInt32();
        int comment = this.is.readInt32();
        int elementNS = this.is.readInt32();
        int elementName = this.is.readInt32();
        if (this.currentTag.equals(this.strings[elementName])) {
            this.writer.add(" />");
            this.wasOneLiner = true;
        } else {
            this.writer.startLine("</");
            this.writer.attachSourceLine(endLineNumber);
            if (elementNS != -1) {
                this.writer.add(this.strings[elementNS]).add(':');
            }
            this.writer.add(this.strings[elementName]).add(">");
        }
        if (this.writer.getIndent() != 0) {
            this.writer.decIndent();
        }
    }
}
