package jadx.core.codegen;

import jadx.api.CodePosition;
import jadx.core.dex.attributes.nodes.LineAttrNode;
import jadx.core.utils.files.FileUtils;
import java.io.File;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodeWriter {
    private static final boolean ADD_LINE_NUMBERS = false;
    public static final String INDENT = "    ";
    private static final String[] INDENT_CACHE = new String[]{"", INDENT, "        ", "            ", "                ", "                    "};
    private static final Logger LOG = LoggerFactory.getLogger(CodeWriter.class);
    public static final String NL = System.getProperty("line.separator");
    private Map<CodePosition, Object> annotations = Collections.emptyMap();
    private StringBuilder buf = new StringBuilder();
    @Nullable
    private String code;
    private int indent = 0;
    private String indentStr = "";
    private int line = 1;
    private Map<Integer, Integer> lineMap = Collections.emptyMap();
    private int offset = 0;

    private static class DefinitionWrapper {
        private final LineAttrNode node;

        private DefinitionWrapper(LineAttrNode node) {
            this.node = node;
        }

        public LineAttrNode getNode() {
            return this.node;
        }
    }

    public CodeWriter startLine() {
        addLine();
        addLineIndent();
        return this;
    }

    public CodeWriter startLine(char c) {
        addLine();
        addLineIndent();
        add(c);
        return this;
    }

    public CodeWriter startLine(String str) {
        addLine();
        addLineIndent();
        add(str);
        return this;
    }

    public CodeWriter startLineWithNum(int sourceLine) {
        if (sourceLine == 0) {
            startLine();
        } else {
            startLine();
            attachSourceLine(sourceLine);
        }
        return this;
    }

    public CodeWriter add(String str) {
        this.buf.append(str);
        this.offset += str.length();
        return this;
    }

    public CodeWriter add(char c) {
        this.buf.append(c);
        this.offset++;
        return this;
    }

    CodeWriter add(CodeWriter code) {
        this.line--;
        for (Entry<CodePosition, Object> entry : code.annotations.entrySet()) {
            CodePosition pos = (CodePosition) entry.getKey();
            attachAnnotation(entry.getValue(), new CodePosition(this.line + pos.getLine(), pos.getOffset()));
        }
        for (Entry<Integer, Integer> entry2 : code.lineMap.entrySet()) {
            attachSourceLine(this.line + ((Integer) entry2.getKey()).intValue(), ((Integer) entry2.getValue()).intValue());
        }
        this.line += code.line;
        this.offset = code.offset;
        this.buf.append(code.buf);
        return this;
    }

    public CodeWriter newLine() {
        addLine();
        return this;
    }

    public CodeWriter addIndent() {
        add(INDENT);
        return this;
    }

    private void addLine() {
        this.buf.append(NL);
        this.line++;
        this.offset = 0;
    }

    private CodeWriter addLineIndent() {
        this.buf.append(this.indentStr);
        this.offset += this.indentStr.length();
        return this;
    }

    private void updateIndent() {
        int curIndent = this.indent;
        if (curIndent < INDENT_CACHE.length) {
            this.indentStr = INDENT_CACHE[curIndent];
            return;
        }
        StringBuilder s = new StringBuilder(INDENT.length() * curIndent);
        for (int i = 0; i < curIndent; i++) {
            s.append(INDENT);
        }
        this.indentStr = s.toString();
    }

    public void incIndent() {
        incIndent(1);
    }

    public void decIndent() {
        decIndent(1);
    }

    public void incIndent(int c) {
        this.indent += c;
        updateIndent();
    }

    public void decIndent(int c) {
        this.indent -= c;
        if (this.indent < 0) {
            LOG.warn("Indent < 0");
            this.indent = 0;
        }
        updateIndent();
    }

    public int getIndent() {
        return this.indent;
    }

    public int getLine() {
        return this.line;
    }

    public void attachDefinition(LineAttrNode obj) {
        attachAnnotation(obj);
        attachAnnotation(new DefinitionWrapper(obj), new CodePosition(this.line, this.offset));
    }

    public void attachAnnotation(Object obj) {
        attachAnnotation(obj, new CodePosition(this.line, this.offset + 1));
    }

    private Object attachAnnotation(Object obj, CodePosition pos) {
        if (this.annotations.isEmpty()) {
            this.annotations = new HashMap();
        }
        return this.annotations.put(pos, obj);
    }

    public Map<CodePosition, Object> getAnnotations() {
        return this.annotations;
    }

    public void attachSourceLine(int sourceLine) {
        if (sourceLine != 0) {
            attachSourceLine(this.line, sourceLine);
        }
    }

    private void attachSourceLine(int decompiledLine, int sourceLine) {
        if (this.lineMap.isEmpty()) {
            this.lineMap = new TreeMap();
        }
        this.lineMap.put(Integer.valueOf(decompiledLine), Integer.valueOf(sourceLine));
    }

    public Map<Integer, Integer> getLineMapping() {
        return this.lineMap;
    }

    public void finish() {
        removeFirstEmptyLine();
        this.buf.trimToSize();
        this.code = this.buf.toString();
        this.buf = null;
        Iterator<Entry<CodePosition, Object>> it = this.annotations.entrySet().iterator();
        while (it.hasNext()) {
            Entry<CodePosition, Object> entry = (Entry) it.next();
            Object v = entry.getValue();
            if (v instanceof DefinitionWrapper) {
                ((DefinitionWrapper) v).getNode().setDecompiledLine(((CodePosition) entry.getKey()).getLine());
                it.remove();
            }
        }
    }

    private void removeFirstEmptyLine() {
        if (this.buf.indexOf(NL) == 0) {
            this.buf.delete(0, NL.length());
        }
    }

    public int bufLength() {
        return this.buf.length();
    }

    public String getCodeStr() {
        return this.code;
    }

    public String toString() {
        return this.buf == null ? this.code : this.buf.toString();
    }

    public void save(File dir, String subDir, String fileName) {
        save(dir, new File(subDir, fileName).getPath());
    }

    public void save(File dir, String fileName) {
        save(new File(dir, fileName));
    }

    public void save(File file) {
        Exception e;
        Throwable th;
        if (this.code == null) {
            finish();
        }
        PrintWriter out = null;
        try {
            PrintWriter out2 = new PrintWriter(FileUtils.prepareFile(file), "UTF-8");
            try {
                out2.println(this.code);
                FileUtils.close(out2);
                out = out2;
            } catch (Exception e2) {
                e = e2;
                out = out2;
                try {
                    LOG.error("Save file error", e);
                    FileUtils.close(out);
                } catch (Throwable th2) {
                    th = th2;
                    FileUtils.close(out);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                out = out2;
                FileUtils.close(out);
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            LOG.error("Save file error", e);
            FileUtils.close(out);
        }
    }
}
