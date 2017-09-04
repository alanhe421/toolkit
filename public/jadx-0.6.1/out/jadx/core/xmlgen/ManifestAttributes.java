package jadx.core.xmlgen;

import jadx.core.utils.exceptions.JadxException;
import jadx.core.utils.files.FileUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ManifestAttributes {
    private static final String ATTR_XML = "/android/attrs.xml";
    private static final Logger LOG = LoggerFactory.getLogger(ManifestAttributes.class);
    private static final String MANIFEST_ATTR_XML = "/android/attrs_manifest.xml";
    private final Map<String, MAttr> attrMap = new HashMap();

    private static class MAttr {
        private final MAttrType type;
        private final Map<Long, String> values = new LinkedHashMap();

        public MAttr(MAttrType type) {
            this.type = type;
        }

        public MAttrType getType() {
            return this.type;
        }

        public Map<Long, String> getValues() {
            return this.values;
        }

        public String toString() {
            return "[" + this.type + ", " + this.values + "]";
        }
    }

    private enum MAttrType {
        ENUM,
        FLAG
    }

    public void parseAll() throws Exception {
        parse(loadXML(ATTR_XML));
        parse(loadXML(MANIFEST_ATTR_XML));
        LOG.debug("Loaded android attributes count: {}", Integer.valueOf(this.attrMap.size()));
    }

    private Document loadXML(String xml) throws JadxException, ParserConfigurationException, SAXException, IOException {
        InputStream xmlStream = null;
        try {
            xmlStream = ManifestAttributes.class.getResourceAsStream(xml);
            if (xmlStream == null) {
                throw new JadxException(xml + " not found in classpath");
            }
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlStream);
            return doc;
        } finally {
            FileUtils.close(xmlStream);
        }
    }

    private void parse(Document doc) {
        NodeList nodeList = doc.getChildNodes();
        for (int count = 0; count < nodeList.getLength(); count++) {
            Node node = nodeList.item(count);
            if (node.getNodeType() == (short) 1 && node.hasChildNodes()) {
                parseAttrList(node.getChildNodes());
            }
        }
    }

    private void parseAttrList(NodeList nodeList) {
        for (int count = 0; count < nodeList.getLength(); count++) {
            Node tempNode = nodeList.item(count);
            if (tempNode.getNodeType() == (short) 1 && tempNode.hasAttributes() && tempNode.hasChildNodes()) {
                String name = null;
                NamedNodeMap nodeMap = tempNode.getAttributes();
                for (int i = 0; i < nodeMap.getLength(); i++) {
                    Node node = nodeMap.item(i);
                    if (node.getNodeName().equals("name")) {
                        name = node.getNodeValue();
                        break;
                    }
                }
                if (name == null || !tempNode.getNodeName().equals("attr")) {
                    parseAttrList(tempNode.getChildNodes());
                } else {
                    parseValues(name, tempNode.getChildNodes());
                }
            }
        }
    }

    private void parseValues(String name, NodeList nodeList) {
        MAttr attr = null;
        for (int count = 0; count < nodeList.getLength(); count++) {
            Node tempNode = nodeList.item(count);
            if (tempNode.getNodeType() == (short) 1 && tempNode.hasAttributes()) {
                if (attr == null) {
                    if (tempNode.getNodeName().equals("enum")) {
                        attr = new MAttr(MAttrType.ENUM);
                    } else if (tempNode.getNodeName().equals("flag")) {
                        attr = new MAttr(MAttrType.FLAG);
                    }
                    if (attr != null) {
                        this.attrMap.put(name, attr);
                    } else {
                        return;
                    }
                }
                NamedNodeMap attributes = tempNode.getAttributes();
                Node nameNode = attributes.getNamedItem("name");
                if (nameNode != null) {
                    Node valueNode = attributes.getNamedItem("value");
                    if (valueNode != null) {
                        try {
                            long key;
                            String nodeValue = valueNode.getNodeValue();
                            if (nodeValue.startsWith("0x")) {
                                key = Long.parseLong(nodeValue.substring(2), 16);
                            } else {
                                key = Long.parseLong(nodeValue);
                            }
                            attr.getValues().put(Long.valueOf(key), nameNode.getNodeValue());
                        } catch (NumberFormatException e) {
                            LOG.debug("Failed parse manifest number", e);
                        }
                    }
                }
            }
        }
    }

    public String decode(String attrName, long value) {
        MAttr attr = (MAttr) this.attrMap.get(attrName);
        if (attr == null) {
            return null;
        }
        if (attr.getType() == MAttrType.ENUM) {
            String name = (String) attr.getValues().get(Long.valueOf(value));
            if (name != null) {
                return name;
            }
        } else if (attr.getType() == MAttrType.FLAG) {
            StringBuilder sb = new StringBuilder();
            for (Entry<Long, String> entry : attr.getValues().entrySet()) {
                if ((((Long) entry.getKey()).longValue() & value) != 0) {
                    sb.append((String) entry.getValue()).append('|');
                }
            }
            if (sb.length() != 0) {
                return sb.deleteCharAt(sb.length() - 1).toString();
            }
        }
        return "UNKNOWN_DATA_0x" + Long.toHexString(value);
    }
}
