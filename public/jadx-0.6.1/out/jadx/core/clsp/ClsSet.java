package jadx.core.clsp;

import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.nodes.ClassNode;
import jadx.core.dex.nodes.RootNode;
import jadx.core.utils.exceptions.DecodeException;
import jadx.core.utils.exceptions.JadxRuntimeException;
import jadx.core.utils.files.FileUtils;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClsSet {
    private static final String CLST_EXTENSION = ".jcst";
    private static final String CLST_FILENAME = "core.jcst";
    private static final String CLST_PKG_PATH = ClsSet.class.getPackage().getName().replace('.', '/');
    private static final String JADX_CLS_SET_HEADER = "jadx-cst";
    private static final Logger LOG = LoggerFactory.getLogger(ClsSet.class);
    private static final String STRING_CHARSET = "US-ASCII";
    private static final int VERSION = 1;
    private NClass[] classes;

    public void load(RootNode root) {
        List<ClassNode> list = root.getClasses(true);
        Map<String, NClass> names = new HashMap(list.size());
        int k = 0;
        for (ClassNode cls : list) {
            String clsRawName = cls.getRawName();
            if (!cls.getAccessFlags().isPublic()) {
                names.put(clsRawName, null);
            } else if (names.put(clsRawName, new NClass(clsRawName, k)) != null) {
                throw new JadxRuntimeException("Duplicate class: " + clsRawName);
            } else {
                k += VERSION;
            }
        }
        this.classes = new NClass[k];
        k = 0;
        for (ClassNode cls2 : list) {
            if (cls2.getAccessFlags().isPublic()) {
                NClass nClass = getCls(cls2.getRawName(), names);
                if (nClass == null) {
                    throw new JadxRuntimeException("Missing class: " + cls2);
                }
                nClass.setParents(makeParentsArray(cls2, names));
                this.classes[k] = nClass;
                k += VERSION;
            }
        }
    }

    public static NClass[] makeParentsArray(ClassNode cls, Map<String, NClass> names) {
        NClass c;
        List<NClass> parents = new ArrayList(cls.getInterfaces().size() + VERSION);
        ArgType superClass = cls.getSuperClass();
        if (superClass != null) {
            c = getCls(superClass.getObject(), names);
            if (c != null) {
                parents.add(c);
            }
        }
        for (ArgType iface : cls.getInterfaces()) {
            c = getCls(iface.getObject(), names);
            if (c != null) {
                parents.add(c);
            }
        }
        return (NClass[]) parents.toArray(new NClass[parents.size()]);
    }

    private static NClass getCls(String fullName, Map<String, NClass> names) {
        NClass id = (NClass) names.get(fullName);
        if (id == null && !names.containsKey(fullName)) {
            LOG.debug("Class not found: {}", fullName);
        }
        return id;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void save(java.io.File r7) throws java.io.IOException {
        /*
        r6 = this;
        jadx.core.utils.files.FileUtils.makeDirsForFile(r7);
        r2 = new java.io.BufferedOutputStream;
        r3 = new java.io.FileOutputStream;
        r3.<init>(r7);
        r2.<init>(r3);
        r1 = r7.getName();	 Catch:{ all -> 0x0057 }
        r3 = ".jcst";
        r3 = r1.endsWith(r3);	 Catch:{ all -> 0x0057 }
        if (r3 == 0) goto L_0x0020;
    L_0x0019:
        r6.save(r2);	 Catch:{ all -> 0x0057 }
    L_0x001c:
        jadx.core.utils.files.FileUtils.close(r2);
        return;
    L_0x0020:
        r3 = ".jar";
        r3 = r1.endsWith(r3);	 Catch:{ all -> 0x0057 }
        if (r3 == 0) goto L_0x0061;
    L_0x0028:
        r0 = new java.util.zip.ZipOutputStream;	 Catch:{ all -> 0x0057 }
        r0.<init>(r2);	 Catch:{ all -> 0x0057 }
        r3 = new java.util.zip.ZipEntry;	 Catch:{ all -> 0x005c }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x005c }
        r4.<init>();	 Catch:{ all -> 0x005c }
        r5 = CLST_PKG_PATH;	 Catch:{ all -> 0x005c }
        r4 = r4.append(r5);	 Catch:{ all -> 0x005c }
        r5 = "/";
        r4 = r4.append(r5);	 Catch:{ all -> 0x005c }
        r5 = "core.jcst";
        r4 = r4.append(r5);	 Catch:{ all -> 0x005c }
        r4 = r4.toString();	 Catch:{ all -> 0x005c }
        r3.<init>(r4);	 Catch:{ all -> 0x005c }
        r0.putNextEntry(r3);	 Catch:{ all -> 0x005c }
        r6.save(r0);	 Catch:{ all -> 0x005c }
        jadx.core.utils.files.FileUtils.close(r0);	 Catch:{ all -> 0x0057 }
        goto L_0x001c;
    L_0x0057:
        r3 = move-exception;
        jadx.core.utils.files.FileUtils.close(r2);
        throw r3;
    L_0x005c:
        r3 = move-exception;
        jadx.core.utils.files.FileUtils.close(r0);	 Catch:{ all -> 0x0057 }
        throw r3;	 Catch:{ all -> 0x0057 }
    L_0x0061:
        r3 = new jadx.core.utils.exceptions.JadxRuntimeException;	 Catch:{ all -> 0x0057 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0057 }
        r4.<init>();	 Catch:{ all -> 0x0057 }
        r5 = "Unknown file format: ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0057 }
        r4 = r4.append(r1);	 Catch:{ all -> 0x0057 }
        r4 = r4.toString();	 Catch:{ all -> 0x0057 }
        r3.<init>(r4);	 Catch:{ all -> 0x0057 }
        throw r3;	 Catch:{ all -> 0x0057 }
        */
        throw new UnsupportedOperationException("Method not decompiled: jadx.core.clsp.ClsSet.save(java.io.File):void");
    }

    public void save(OutputStream output) throws IOException {
        DataOutputStream out = new DataOutputStream(output);
        try {
            int i$;
            out.writeBytes(JADX_CLS_SET_HEADER);
            out.writeByte(VERSION);
            LOG.info("Classes count: {}", Integer.valueOf(this.classes.length));
            out.writeInt(this.classes.length);
            NClass[] arr$ = this.classes;
            int len$ = arr$.length;
            for (i$ = 0; i$ < len$; i$ += VERSION) {
                writeString(out, arr$[i$].getName());
            }
            arr$ = this.classes;
            len$ = arr$.length;
            for (int i = 0; i < len$; i += VERSION) {
                NClass[] parents = arr$[i].getParents();
                out.writeByte(parents.length);
                NClass[] arr$2 = parents;
                int len$2 = arr$2.length;
                for (i$ = 0; i$ < len$2; i$ += VERSION) {
                    out.writeInt(arr$2[i$].getId());
                }
            }
        } finally {
            FileUtils.close(out);
        }
    }

    public void load() throws IOException, DecodeException {
        InputStream input = getClass().getResourceAsStream(CLST_FILENAME);
        if (input == null) {
            throw new JadxRuntimeException("Can't load classpath file: core.jcst");
        }
        try {
            load(input);
        } finally {
            FileUtils.close(input);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void load(java.io.File r8) throws java.io.IOException, jadx.core.utils.exceptions.DecodeException {
        /*
        r7 = this;
        r3 = r8.getName();
        r2 = new java.io.FileInputStream;
        r2.<init>(r8);
        r4 = ".jcst";
        r4 = r3.endsWith(r4);	 Catch:{ all -> 0x0043 }
        if (r4 == 0) goto L_0x0018;
    L_0x0011:
        r7.load(r2);	 Catch:{ all -> 0x0043 }
    L_0x0014:
        jadx.core.utils.files.FileUtils.close(r2);
        return;
    L_0x0018:
        r4 = ".jar";
        r4 = r3.endsWith(r4);	 Catch:{ all -> 0x0043 }
        if (r4 == 0) goto L_0x004d;
    L_0x0020:
        r1 = new java.util.zip.ZipInputStream;	 Catch:{ all -> 0x0043 }
        r1.<init>(r2);	 Catch:{ all -> 0x0043 }
        r0 = r1.getNextEntry();	 Catch:{ all -> 0x0048 }
    L_0x0029:
        if (r0 == 0) goto L_0x003f;
    L_0x002b:
        r4 = r0.getName();	 Catch:{ all -> 0x0048 }
        r5 = ".jcst";
        r4 = r4.endsWith(r5);	 Catch:{ all -> 0x0048 }
        if (r4 == 0) goto L_0x003a;
    L_0x0037:
        r7.load(r1);	 Catch:{ all -> 0x0048 }
    L_0x003a:
        r0 = r1.getNextEntry();	 Catch:{ all -> 0x0048 }
        goto L_0x0029;
    L_0x003f:
        jadx.core.utils.files.FileUtils.close(r1);	 Catch:{ all -> 0x0043 }
        goto L_0x0014;
    L_0x0043:
        r4 = move-exception;
        jadx.core.utils.files.FileUtils.close(r2);
        throw r4;
    L_0x0048:
        r4 = move-exception;
        jadx.core.utils.files.FileUtils.close(r1);	 Catch:{ all -> 0x0043 }
        throw r4;	 Catch:{ all -> 0x0043 }
    L_0x004d:
        r4 = new jadx.core.utils.exceptions.JadxRuntimeException;	 Catch:{ all -> 0x0043 }
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0043 }
        r5.<init>();	 Catch:{ all -> 0x0043 }
        r6 = "Unknown file format: ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x0043 }
        r5 = r5.append(r3);	 Catch:{ all -> 0x0043 }
        r5 = r5.toString();	 Catch:{ all -> 0x0043 }
        r4.<init>(r5);	 Catch:{ all -> 0x0043 }
        throw r4;	 Catch:{ all -> 0x0043 }
        */
        throw new UnsupportedOperationException("Method not decompiled: jadx.core.clsp.ClsSet.load(java.io.File):void");
    }

    public void load(InputStream input) throws IOException, DecodeException {
        DataInputStream in = new DataInputStream(input);
        try {
            byte[] header = new byte[JADX_CLS_SET_HEADER.length()];
            int readHeaderLength = in.read(header);
            int version = in.readByte();
            if (readHeaderLength == JADX_CLS_SET_HEADER.length() && JADX_CLS_SET_HEADER.equals(new String(header, STRING_CHARSET)) && version == VERSION) {
                int i;
                int count = in.readInt();
                this.classes = new NClass[count];
                for (i = 0; i < count; i += VERSION) {
                    this.classes[i] = new NClass(readString(in), i);
                }
                for (i = 0; i < count; i += VERSION) {
                    int pCount = in.readByte();
                    NClass[] parents = new NClass[pCount];
                    for (int j = 0; j < pCount; j += VERSION) {
                        parents[j] = this.classes[in.readInt()];
                    }
                    this.classes[i].setParents(parents);
                }
                return;
            }
            throw new DecodeException("Wrong jadx class set header");
        } finally {
            FileUtils.close(in);
        }
    }

    private void writeString(DataOutputStream out, String name) throws IOException {
        byte[] bytes = name.getBytes(STRING_CHARSET);
        out.writeByte(bytes.length);
        out.write(bytes);
    }

    private static String readString(DataInputStream in) throws IOException {
        int len = in.readByte();
        byte[] bytes = new byte[len];
        int count = in.read(bytes);
        while (count != len) {
            int res = in.read(bytes, count, len - count);
            if (res == -1) {
                throw new IOException("String read error");
            }
            count += res;
        }
        return new String(bytes, STRING_CHARSET);
    }

    public int getClassesCount() {
        return this.classes.length;
    }

    public void addToMap(Map<String, NClass> nameMap) {
        NClass[] arr$ = this.classes;
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$ += VERSION) {
            NClass cls = arr$[i$];
            nameMap.put(cls.getName(), cls);
        }
    }
}
