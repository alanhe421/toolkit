package com.tencent.theme;

import android.content.res.XmlResourceParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.xmlpull.v1.XmlPullParserException;

public class AndroidXmlResourceParser implements XmlResourceParser {
    private static final int ATTRIBUTE_IX_NAME = 1;
    private static final int ATTRIBUTE_IX_NAMESPACE_URI = 0;
    private static final int ATTRIBUTE_IX_VALUE_DATA = 4;
    private static final int ATTRIBUTE_IX_VALUE_STRING = 2;
    private static final int ATTRIBUTE_IX_VALUE_TYPE = 3;
    private static final int ATTRIBUTE_LENGHT = 5;
    private static final int CHUNK_AXML_FILE = 524291;
    private static final int CHUNK_RESOURCEIDS = 524672;
    private static final int CHUNK_XML_END_NAMESPACE = 1048833;
    private static final int CHUNK_XML_END_TAG = 1048835;
    private static final int CHUNK_XML_FIRST = 1048832;
    private static final int CHUNK_XML_LAST = 1048836;
    private static final int CHUNK_XML_START_NAMESPACE = 1048832;
    private static final int CHUNK_XML_START_TAG = 1048834;
    private static final int CHUNK_XML_TEXT = 1048836;
    private static final String E_NOT_SUPPORTED = "Method is not supported.";
    private ByteBuffer mBuffer;
    private int[] m_attributes;
    private int m_classAttribute;
    private boolean m_decreaseDepth;
    private int m_event;
    private int m_idAttribute;
    private int m_lineNumber;
    private int m_name;
    private int m_namespaceUri;
    private NamespaceStack m_namespaces = new NamespaceStack();
    private boolean m_operational = false;
    private int[] m_resourceIDs;
    private StringBlock m_strings;
    private int m_styleAttribute;

    public AndroidXmlResourceParser() {
        resetEventInfo();
    }

    public void close() {
        if (this.m_operational) {
            this.m_operational = false;
            this.mBuffer = null;
            this.m_strings = null;
            this.m_resourceIDs = null;
            this.m_namespaces.reset();
            resetEventInfo();
        }
    }

    public int next() throws XmlPullParserException, IOException {
        if (this.mBuffer == null) {
            throw new XmlPullParserException("Parser is not opened.", this, null);
        }
        try {
            doNext();
            return this.m_event;
        } catch (IOException e) {
            close();
            throw e;
        }
    }

    public int nextToken() throws XmlPullParserException, IOException {
        return next();
    }

    public int nextTag() throws XmlPullParserException, IOException {
        int next = next();
        if (next == 4 && isWhitespace()) {
            next = next();
        }
        if (next == 2 || next == 3) {
            return next;
        }
        throw new XmlPullParserException("Expected start or end tag.", this, null);
    }

    public String nextText() throws XmlPullParserException, IOException {
        if (getEventType() != 2) {
            throw new XmlPullParserException("Parser must be on START_TAG to read next text.", this, null);
        }
        int next = next();
        if (next == 4) {
            String text = getText();
            if (next() == 3) {
                return text;
            }
            throw new XmlPullParserException("Event TEXT must be immediately followed by END_TAG.", this, null);
        } else if (next == 3) {
            return "";
        } else {
            throw new XmlPullParserException("Parser must be on START_TAG or TEXT to read text.", this, null);
        }
    }

    public void require(int i, String str, String str2) throws XmlPullParserException, IOException {
        if (i != getEventType() || ((str != null && !str.equals(getNamespace())) || (str2 != null && !str2.equals(getName())))) {
            throw new XmlPullParserException(TYPES[i] + " is expected.", this, null);
        }
    }

    public int getDepth() {
        return this.m_namespaces.getDepth() - 1;
    }

    public int getEventType() throws XmlPullParserException {
        return this.m_event;
    }

    public int getLineNumber() {
        return this.m_lineNumber;
    }

    public String getName() {
        if (this.m_name == -1 || (this.m_event != 2 && this.m_event != 3)) {
            return null;
        }
        return this.m_strings.getString(this.m_name);
    }

    public String getText() {
        if (this.m_name == -1 || this.m_event != 4) {
            return null;
        }
        return this.m_strings.getString(this.m_name);
    }

    public char[] getTextCharacters(int[] iArr) {
        String text = getText();
        if (text == null) {
            return null;
        }
        iArr[0] = 0;
        iArr[1] = text.length();
        char[] cArr = new char[text.length()];
        text.getChars(0, text.length(), cArr, 0);
        return cArr;
    }

    public String getNamespace() {
        return this.m_strings.getString(this.m_namespaceUri);
    }

    public String getPrefix() {
        return this.m_strings.getString(this.m_namespaces.findPrefix(this.m_namespaceUri));
    }

    public String getPositionDescription() {
        return "XML line #" + getLineNumber();
    }

    public int getNamespaceCount(int i) throws XmlPullParserException {
        return this.m_namespaces.getAccumulatedCount(i);
    }

    public String getNamespacePrefix(int i) throws XmlPullParserException {
        return this.m_strings.getString(this.m_namespaces.getPrefix(i));
    }

    public String getNamespaceUri(int i) throws XmlPullParserException {
        return this.m_strings.getString(this.m_namespaces.getUri(i));
    }

    public String getClassAttribute() {
        if (this.m_classAttribute == -1) {
            return null;
        }
        return this.m_strings.getString(this.m_attributes[getAttributeOffset(this.m_classAttribute) + 2]);
    }

    public String getIdAttribute() {
        if (this.m_idAttribute == -1) {
            return null;
        }
        return this.m_strings.getString(this.m_attributes[getAttributeOffset(this.m_idAttribute) + 2]);
    }

    public int getIdAttributeResourceValue(int i) {
        if (this.m_idAttribute == -1) {
            return i;
        }
        int attributeOffset = getAttributeOffset(this.m_idAttribute);
        return this.m_attributes[attributeOffset + 3] == 1 ? this.m_attributes[attributeOffset + 4] : i;
    }

    public int getStyleAttribute() {
        if (this.m_styleAttribute == -1) {
            return 0;
        }
        return this.m_attributes[getAttributeOffset(this.m_styleAttribute) + 4];
    }

    public int getAttributeCount() {
        if (this.m_event != 2) {
            return -1;
        }
        return this.m_attributes.length / 5;
    }

    public String getAttributeNamespace(int i) {
        int i2 = this.m_attributes[getAttributeOffset(i) + 0];
        if (i2 == -1) {
            return "";
        }
        return this.m_strings.getString(i2);
    }

    public String getAttributePrefix(int i) {
        int findPrefix = this.m_namespaces.findPrefix(this.m_attributes[getAttributeOffset(i) + 0]);
        if (findPrefix == -1) {
            return "";
        }
        return this.m_strings.getString(findPrefix);
    }

    public String getAttributeName(int i) {
        int i2 = this.m_attributes[getAttributeOffset(i) + 1];
        if (i2 == -1) {
            return "";
        }
        return this.m_strings.getString(i2);
    }

    public int getAttributeNameResource(int i) {
        int i2 = this.m_attributes[getAttributeOffset(i) + 1];
        if (this.m_resourceIDs == null || i2 < 0 || i2 >= this.m_resourceIDs.length) {
            return 0;
        }
        return this.m_resourceIDs[i2];
    }

    public int getAttributeValueType(int i) {
        return this.m_attributes[getAttributeOffset(i) + 3];
    }

    public int getAttributeValueData(int i) {
        return this.m_attributes[getAttributeOffset(i) + 4];
    }

    public String getAttributeValue(int i) {
        int attributeOffset = getAttributeOffset(i);
        if (this.m_attributes[attributeOffset + 3] == 3) {
            return this.m_strings.getString(this.m_attributes[attributeOffset + 2]);
        }
        attributeOffset = this.m_attributes[attributeOffset + 4];
        return "";
    }

    public boolean getAttributeBooleanValue(int i, boolean z) {
        return getAttributeIntValue(i, z ? 1 : 0) != 0;
    }

    public float getAttributeFloatValue(int i, float f) {
        int attributeOffset = getAttributeOffset(i);
        if (this.m_attributes[attributeOffset + 3] == 4) {
            return Float.intBitsToFloat(this.m_attributes[attributeOffset + 4]);
        }
        return f;
    }

    public int getAttributeIntValue(int i, int i2) {
        int attributeOffset = getAttributeOffset(i);
        int i3 = this.m_attributes[attributeOffset + 3];
        if (i3 < 16 || i3 > 31) {
            return i2;
        }
        return this.m_attributes[attributeOffset + 4];
    }

    public int getAttributeUnsignedIntValue(int i, int i2) {
        return getAttributeIntValue(i, i2);
    }

    public int getAttributeResourceValue(int i, int i2) {
        int attributeOffset = getAttributeOffset(i);
        if (this.m_attributes[attributeOffset + 3] == 1) {
            return this.m_attributes[attributeOffset + 4];
        }
        return i2;
    }

    public String getAttributeValue(String str, String str2) {
        int findAttribute = findAttribute(str, str2);
        if (findAttribute == -1) {
            return null;
        }
        return getAttributeValue(findAttribute);
    }

    public boolean getAttributeBooleanValue(String str, String str2, boolean z) {
        int findAttribute = findAttribute(str, str2);
        return findAttribute == -1 ? z : getAttributeBooleanValue(findAttribute, z);
    }

    public float getAttributeFloatValue(String str, String str2, float f) {
        int findAttribute = findAttribute(str, str2);
        return findAttribute == -1 ? f : getAttributeFloatValue(findAttribute, f);
    }

    public int getAttributeIntValue(String str, String str2, int i) {
        int findAttribute = findAttribute(str, str2);
        return findAttribute == -1 ? i : getAttributeIntValue(findAttribute, i);
    }

    public int getAttributeUnsignedIntValue(String str, String str2, int i) {
        int findAttribute = findAttribute(str, str2);
        return findAttribute == -1 ? i : getAttributeUnsignedIntValue(findAttribute, i);
    }

    public int getAttributeResourceValue(String str, String str2, int i) {
        int findAttribute = findAttribute(str, str2);
        return findAttribute == -1 ? i : getAttributeResourceValue(findAttribute, i);
    }

    public int getAttributeListValue(int i, String[] strArr, int i2) {
        return 0;
    }

    public int getAttributeListValue(String str, String str2, String[] strArr, int i) {
        return 0;
    }

    public String getAttributeType(int i) {
        return "CDATA";
    }

    public boolean isAttributeDefault(int i) {
        return false;
    }

    public void setInput(InputStream inputStream, String str) throws XmlPullParserException {
        try {
            close();
            if (inputStream != null) {
                byte[] bArr = new byte[inputStream.available()];
                inputStream.read(bArr);
                inputStream.close();
                this.mBuffer = ByteBuffer.wrap(bArr);
                this.mBuffer.order(ByteOrder.LITTLE_ENDIAN);
            }
        } catch (Throwable e) {
            new XmlPullParserException("error load stream").initCause(e);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void setInput(Reader reader) throws XmlPullParserException {
        throw new XmlPullParserException(E_NOT_SUPPORTED);
    }

    public String getInputEncoding() {
        return null;
    }

    public int getColumnNumber() {
        return -1;
    }

    public boolean isEmptyElementTag() throws XmlPullParserException {
        return false;
    }

    public boolean isWhitespace() throws XmlPullParserException {
        return false;
    }

    public void defineEntityReplacementText(String str, String str2) throws XmlPullParserException {
        throw new XmlPullParserException(E_NOT_SUPPORTED);
    }

    public String getNamespace(String str) {
        throw new RuntimeException(E_NOT_SUPPORTED);
    }

    public Object getProperty(String str) {
        return null;
    }

    public void setProperty(String str, Object obj) throws XmlPullParserException {
        throw new XmlPullParserException(E_NOT_SUPPORTED);
    }

    public boolean getFeature(String str) {
        return false;
    }

    public void setFeature(String str, boolean z) throws XmlPullParserException {
        throw new XmlPullParserException(E_NOT_SUPPORTED);
    }

    final StringBlock getStrings() {
        return this.m_strings;
    }

    private final int getAttributeOffset(int i) {
        if (this.m_event != 2) {
            throw new IndexOutOfBoundsException("Current event is not START_TAG.");
        }
        int i2 = i * 5;
        if (i2 < this.m_attributes.length) {
            return i2;
        }
        throw new IndexOutOfBoundsException("Invalid attribute index (" + i + ").");
    }

    private final int findAttribute(String str, String str2) {
        if (this.m_strings == null || str2 == null) {
            return -1;
        }
        int find = this.m_strings.find(str2);
        if (find == -1) {
            return -1;
        }
        int find2 = str != null ? this.m_strings.find(str) : -1;
        int i = 0;
        while (i != this.m_attributes.length) {
            if (find == this.m_attributes[i + 1] && (find2 == -1 || find2 == this.m_attributes[i + 0])) {
                return i / 5;
            }
            i++;
        }
        return -1;
    }

    private final void resetEventInfo() {
        this.m_event = -1;
        this.m_lineNumber = -1;
        this.m_name = -1;
        this.m_namespaceUri = -1;
        this.m_attributes = null;
        this.m_idAttribute = -1;
        this.m_classAttribute = -1;
        this.m_styleAttribute = -1;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void doNext() throws java.io.IOException {
        /*
        r9 = this;
        r2 = 1048834; // 0x100102 float:1.46973E-39 double:5.18193E-318;
        r8 = 1048832; // 0x100100 float:1.469727E-39 double:5.18192E-318;
        r0 = 3;
        r7 = 1;
        r6 = 4;
        r1 = r9.m_strings;
        if (r1 != 0) goto L_0x0029;
    L_0x000d:
        r1 = r9.mBuffer;
        r3 = 524291; // 0x80003 float:7.34688E-40 double:2.59034E-318;
        com.tencent.theme.ChunkUtil.readCheckType(r1, r3);
        r1 = r9.mBuffer;
        com.tencent.theme.ChunkUtil.skip(r1, r6);
        r1 = r9.mBuffer;
        r1 = com.tencent.theme.StringBlock.read(r1);
        r9.m_strings = r1;
        r1 = r9.m_namespaces;
        r1.increaseDepth();
        r9.m_operational = r7;
    L_0x0029:
        r1 = r9.m_event;
        if (r1 != r7) goto L_0x002e;
    L_0x002d:
        return;
    L_0x002e:
        r3 = r9.m_event;
        r9.resetEventInfo();
    L_0x0033:
        r1 = r9.m_decreaseDepth;
        if (r1 == 0) goto L_0x003f;
    L_0x0037:
        r1 = 0;
        r9.m_decreaseDepth = r1;
        r1 = r9.m_namespaces;
        r1.decreaseDepth();
    L_0x003f:
        if (r3 != r0) goto L_0x0054;
    L_0x0041:
        r1 = r9.m_namespaces;
        r1 = r1.getDepth();
        if (r1 != r7) goto L_0x0054;
    L_0x0049:
        r1 = r9.m_namespaces;
        r1 = r1.getCurrentCount();
        if (r1 != 0) goto L_0x0054;
    L_0x0051:
        r9.m_event = r7;
        goto L_0x002d;
    L_0x0054:
        if (r3 != 0) goto L_0x008b;
    L_0x0056:
        r1 = r2;
    L_0x0057:
        r4 = 524672; // 0x80180 float:7.35222E-40 double:2.592224E-318;
        if (r1 != r4) goto L_0x009f;
    L_0x005c:
        r1 = r9.mBuffer;
        r1 = r1.getInt();
        r4 = 8;
        if (r1 < r4) goto L_0x006a;
    L_0x0066:
        r4 = r1 % 4;
        if (r4 == 0) goto L_0x0092;
    L_0x006a:
        r0 = new java.io.IOException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Invalid resource ids size (";
        r2 = r2.append(r3);
        r1 = r2.append(r1);
        r2 = ").";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x008b:
        r1 = r9.mBuffer;
        r1 = r1.getInt();
        goto L_0x0057;
    L_0x0092:
        r4 = r9.mBuffer;
        r1 = r1 / 4;
        r1 = r1 + -2;
        r1 = com.tencent.theme.ChunkUtil.readIntArray(r4, r1);
        r9.m_resourceIDs = r1;
        goto L_0x0033;
    L_0x009f:
        if (r1 < r8) goto L_0x00a6;
    L_0x00a1:
        r4 = 1048836; // 0x100104 float:1.469732E-39 double:5.18194E-318;
        if (r1 <= r4) goto L_0x00c7;
    L_0x00a6:
        r0 = new java.io.IOException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Invalid chunk type (";
        r2 = r2.append(r3);
        r1 = r2.append(r1);
        r2 = ").";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x00c7:
        if (r1 != r2) goto L_0x00d1;
    L_0x00c9:
        r4 = -1;
        if (r3 != r4) goto L_0x00d1;
    L_0x00cc:
        r0 = 0;
        r9.m_event = r0;
        goto L_0x002d;
    L_0x00d1:
        r4 = r9.mBuffer;
        com.tencent.theme.ChunkUtil.skip(r4, r6);
        r4 = r9.mBuffer;
        r4 = r4.getInt();
        r5 = r9.mBuffer;
        com.tencent.theme.ChunkUtil.skip(r5, r6);
        if (r1 == r8) goto L_0x00e8;
    L_0x00e3:
        r5 = 1048833; // 0x100101 float:1.469728E-39 double:5.181924E-318;
        if (r1 != r5) goto L_0x010e;
    L_0x00e8:
        if (r1 != r8) goto L_0x00fd;
    L_0x00ea:
        r1 = r9.mBuffer;
        r1 = r1.getInt();
        r4 = r9.mBuffer;
        r4 = r4.getInt();
        r5 = r9.m_namespaces;
        r5.push(r1, r4);
        goto L_0x0033;
    L_0x00fd:
        r1 = r9.mBuffer;
        com.tencent.theme.ChunkUtil.skip(r1, r6);
        r1 = r9.mBuffer;
        com.tencent.theme.ChunkUtil.skip(r1, r6);
        r1 = r9.m_namespaces;
        r1.pop();
        goto L_0x0033;
    L_0x010e:
        r9.m_lineNumber = r4;
        if (r1 != r2) goto L_0x0177;
    L_0x0112:
        r1 = r9.mBuffer;
        r1 = r1.getInt();
        r9.m_namespaceUri = r1;
        r1 = r9.mBuffer;
        r1 = r1.getInt();
        r9.m_name = r1;
        r1 = r9.mBuffer;
        com.tencent.theme.ChunkUtil.skip(r1, r6);
        r1 = r9.mBuffer;
        r1 = r1.getInt();
        r2 = r1 >>> 16;
        r2 = r2 + -1;
        r9.m_idAttribute = r2;
        r2 = 65535; // 0xffff float:9.1834E-41 double:3.23786E-319;
        r1 = r1 & r2;
        r2 = r9.mBuffer;
        r2 = r2.getInt();
        r9.m_classAttribute = r2;
        r2 = r9.m_classAttribute;
        r2 = r2 >>> 16;
        r2 = r2 + -1;
        r9.m_styleAttribute = r2;
        r2 = r9.m_classAttribute;
        r3 = 65535; // 0xffff float:9.1834E-41 double:3.23786E-319;
        r2 = r2 & r3;
        r2 = r2 + -1;
        r9.m_classAttribute = r2;
        r2 = r9.mBuffer;
        r1 = r1 * 5;
        r1 = com.tencent.theme.ChunkUtil.readIntArray(r2, r1);
        r9.m_attributes = r1;
    L_0x015b:
        r1 = r9.m_attributes;
        r1 = r1.length;
        if (r0 >= r1) goto L_0x016d;
    L_0x0160:
        r1 = r9.m_attributes;
        r2 = r9.m_attributes;
        r2 = r2[r0];
        r2 = r2 >>> 24;
        r1[r0] = r2;
        r0 = r0 + 5;
        goto L_0x015b;
    L_0x016d:
        r0 = r9.m_namespaces;
        r0.increaseDepth();
        r0 = 2;
        r9.m_event = r0;
        goto L_0x002d;
    L_0x0177:
        r4 = 1048835; // 0x100103 float:1.469731E-39 double:5.181933E-318;
        if (r1 != r4) goto L_0x0192;
    L_0x017c:
        r1 = r9.mBuffer;
        r1 = r1.getInt();
        r9.m_namespaceUri = r1;
        r1 = r9.mBuffer;
        r1 = r1.getInt();
        r9.m_name = r1;
        r9.m_event = r0;
        r9.m_decreaseDepth = r7;
        goto L_0x002d;
    L_0x0192:
        r4 = 1048836; // 0x100104 float:1.469732E-39 double:5.18194E-318;
        if (r1 != r4) goto L_0x0033;
    L_0x0197:
        r0 = r9.mBuffer;
        r0 = r0.getInt();
        r9.m_name = r0;
        r0 = r9.mBuffer;
        com.tencent.theme.ChunkUtil.skip(r0, r6);
        r0 = r9.mBuffer;
        com.tencent.theme.ChunkUtil.skip(r0, r6);
        r9.m_event = r6;
        goto L_0x002d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.theme.AndroidXmlResourceParser.doNext():void");
    }
}
