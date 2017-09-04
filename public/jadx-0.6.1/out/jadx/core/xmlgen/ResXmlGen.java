package jadx.core.xmlgen;

import jadx.core.codegen.CodeWriter;
import jadx.core.utils.StringUtils;
import jadx.core.xmlgen.entry.RawNamedValue;
import jadx.core.xmlgen.entry.ResourceEntry;
import jadx.core.xmlgen.entry.ValuesParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResXmlGen {
    private static final Logger LOG = LoggerFactory.getLogger(ResXmlGen.class);
    private static final Set<String> SKIP_RES_TYPES = new HashSet(Arrays.asList(new String[]{"layout", "mipmap", "id"}));
    private final ResourceStorage resStorage;
    private final ValuesParser vp;

    public ResXmlGen(ResourceStorage resStorage, ValuesParser vp) {
        this.resStorage = resStorage;
        this.vp = vp;
    }

    public List<ResContainer> makeResourcesXml() {
        Map<String, CodeWriter> contMap = new HashMap();
        for (ResourceEntry ri : this.resStorage.getResources()) {
            if (!SKIP_RES_TYPES.contains(ri.getTypeName())) {
                String fn = getFileName(ri);
                CodeWriter cw = (CodeWriter) contMap.get(fn);
                if (cw == null) {
                    cw = new CodeWriter();
                    cw.add("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
                    cw.startLine("<resources>");
                    cw.incIndent();
                    contMap.put(fn, cw);
                }
                addValue(cw, ri);
            }
        }
        List<ResContainer> files = new ArrayList(contMap.size());
        for (Entry<String, CodeWriter> entry : contMap.entrySet()) {
            String fileName = (String) entry.getKey();
            CodeWriter content = (CodeWriter) entry.getValue();
            content.decIndent();
            content.startLine("</resources>");
            content.finish();
            files.add(ResContainer.singleFile(fileName, content));
        }
        Collections.sort(files);
        return files;
    }

    private void addValue(CodeWriter cw, ResourceEntry ri) {
        if (ri.getSimpleValue() != null) {
            CodeWriter codeWriter = cw;
            addSimpleValue(codeWriter, ri.getTypeName(), "name", ri.getKeyName(), this.vp.decodeValue(ri.getSimpleValue()));
            return;
        }
        cw.startLine();
        cw.add('<').add(ri.getTypeName()).add(' ');
        cw.add("name=\"").add(ri.getKeyName()).add("\">");
        cw.incIndent();
        for (RawNamedValue value : ri.getNamedValues()) {
            addItem(cw, value);
        }
        cw.decIndent();
        cw.startLine().add("</").add(ri.getTypeName()).add('>');
    }

    private void addItem(CodeWriter cw, RawNamedValue value) {
        String keyName = null;
        String keyValue = null;
        int nameRef = value.getNameRef();
        if (ParserConstants.isResInternalId(nameRef)) {
            keyValue = (String) ParserConstants.PLURALS_MAP.get(Integer.valueOf(nameRef));
            if (keyValue != null) {
                keyName = "quantity";
            }
        }
        CodeWriter codeWriter = cw;
        addSimpleValue(codeWriter, "item", keyName, keyValue, this.vp.decodeValue(value.getRawValue()));
    }

    private void addSimpleValue(CodeWriter cw, String typeName, String attrName, String attrValue, String valueStr) {
        cw.startLine();
        cw.add('<').add(typeName);
        if (!(attrName == null || attrValue == null)) {
            cw.add(' ').add(attrName).add("=\"").add(attrValue).add('\"');
        }
        cw.add('>');
        if (typeName.equals("string")) {
            cw.add(StringUtils.escapeResStrValue(valueStr));
        } else {
            cw.add(StringUtils.escapeResValue(valueStr));
        }
        cw.add("</").add(typeName).add('>');
    }

    private String getFileName(ResourceEntry ri) {
        StringBuilder sb = new StringBuilder();
        String locale = ri.getConfig().getLocale();
        sb.append("res/values");
        if (!locale.isEmpty()) {
            sb.append('-').append(locale);
        }
        sb.append('/');
        sb.append(ri.getTypeName());
        if (!ri.getTypeName().endsWith("s")) {
            sb.append('s');
        }
        sb.append(".xml");
        return sb.toString();
    }
}
