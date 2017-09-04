package jadx.core.export;

import jadx.core.utils.files.FileUtils;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TemplateFile {
    private final InputStream template;
    private final String templateName;
    private final Map<String, String> values = new HashMap();

    private static class ParserState {
        private StringBuilder curVariable;
        private boolean skip;
        private State state;

        private ParserState() {
            this.state = State.NONE;
        }
    }

    private enum State {
        NONE,
        START,
        VARIABLE,
        END
    }

    public static TemplateFile fromResources(String path) throws FileNotFoundException {
        InputStream res = TemplateFile.class.getResourceAsStream(path);
        if (res != null) {
            return new TemplateFile(path, res);
        }
        throw new FileNotFoundException("Resource not found: " + path);
    }

    private TemplateFile(String name, InputStream in) throws FileNotFoundException {
        this.templateName = name;
        this.template = in;
    }

    public void add(String name, @NotNull Object value) {
        this.values.put(name, value.toString());
    }

    public String build() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            process(out);
            return out.toString();
        } finally {
            FileUtils.close(out);
        }
    }

    public void save(File outFile) throws IOException {
        OutputStream out = new FileOutputStream(outFile);
        try {
            process(out);
        } finally {
            FileUtils.close(out);
        }
    }

    private void process(OutputStream out) throws IOException {
        Throwable th;
        if (this.template.available() == 0) {
            throw new IOException("Template already processed");
        }
        InputStream in = null;
        try {
            InputStream in2 = new BufferedInputStream(this.template);
            ParserState state = new ParserState();
            while (true) {
                int ch = in2.read();
                if (ch == -1) {
                    FileUtils.close(in2);
                    return;
                }
                try {
                    String str = process(state, (char) ch);
                    if (str != null) {
                        out.write(str.getBytes());
                    } else if (!state.skip) {
                        out.write(ch);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    in = in2;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            FileUtils.close(in);
            throw th;
        }
    }

    @Nullable
    private String process(ParserState parser, char ch) {
        State state = parser.state;
        switch (ch) {
            case '{':
                switch (state) {
                    case START:
                        parser.state = State.VARIABLE;
                        parser.curVariable = new StringBuilder();
                        break;
                    default:
                        parser.state = State.START;
                        break;
                }
                parser.skip = true;
                return null;
            case '}':
                switch (state) {
                    case VARIABLE:
                        parser.state = State.END;
                        parser.skip = true;
                        return null;
                    case END:
                        parser.state = State.NONE;
                        String varName = parser.curVariable.toString();
                        parser.curVariable = new StringBuilder();
                        return processVar(varName);
                    default:
                        break;
                }
            default:
                switch (state) {
                    case START:
                        parser.state = State.NONE;
                        return "{" + ch;
                    case VARIABLE:
                        parser.curVariable.append(ch);
                        parser.skip = true;
                        return null;
                    case END:
                        throw new RuntimeException("Expected variable end: '" + parser.curVariable + "' (missing second '}')");
                }
                break;
        }
        parser.skip = false;
        return null;
    }

    private String processVar(String varName) {
        String str = (String) this.values.get(varName);
        if (str != null) {
            return str;
        }
        throw new RuntimeException("Unknown variable: '" + varName + "' in template: " + this.templateName);
    }
}
