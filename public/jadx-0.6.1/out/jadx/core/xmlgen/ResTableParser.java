package jadx.core.xmlgen;

import jadx.core.codegen.CodeWriter;
import jadx.core.xmlgen.entry.EntryConfig;
import jadx.core.xmlgen.entry.RawNamedValue;
import jadx.core.xmlgen.entry.RawValue;
import jadx.core.xmlgen.entry.ResourceEntry;
import jadx.core.xmlgen.entry.ValuesParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResTableParser extends CommonBinaryParser {
    private static final Logger LOG = LoggerFactory.getLogger(ResTableParser.class);
    private final ResourceStorage resStorage = new ResourceStorage();
    private String[] strings;

    private static final class PackageChunk {
        private final int id;
        private final String[] keyStrings;
        private final String name;
        private final String[] typeStrings;

        private PackageChunk(int id, String name, String[] typeStrings, String[] keyStrings) {
            this.id = id;
            this.name = name;
            this.typeStrings = typeStrings;
            this.keyStrings = keyStrings;
        }

        public int getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public String[] getTypeStrings() {
            return this.typeStrings;
        }

        public String[] getKeyStrings() {
            return this.keyStrings;
        }
    }

    public void decode(InputStream inputStream) throws IOException {
        this.is = new ParserStream(inputStream);
        decodeTableChunk();
        this.resStorage.finish();
    }

    public ResContainer decodeFiles(InputStream inputStream) throws IOException {
        decode(inputStream);
        ResXmlGen resGen = new ResXmlGen(this.resStorage, new ValuesParser(this.strings, this.resStorage.getResourcesNames()));
        ResContainer res = ResContainer.multiFile("res");
        res.setContent(makeDump());
        res.getSubFiles().addAll(resGen.makeResourcesXml());
        return res;
    }

    public CodeWriter makeDump() throws IOException {
        CodeWriter writer = new CodeWriter();
        writer.add("app package: ").add(this.resStorage.getAppPackage());
        writer.startLine();
        ValuesParser vp = new ValuesParser(this.strings, this.resStorage.getResourcesNames());
        for (ResourceEntry ri : this.resStorage.getResources()) {
            writer.startLine(ri + ": " + vp.getValueString(ri));
        }
        writer.finish();
        return writer;
    }

    public ResourceStorage getResStorage() {
        return this.resStorage;
    }

    void decodeTableChunk() throws IOException {
        this.is.checkInt16(2, "Not a table chunk");
        this.is.checkInt16(12, "Unexpected table header size");
        this.is.readInt32();
        int pkgCount = this.is.readInt32();
        this.strings = parseStringPool();
        for (int i = 0; i < pkgCount; i++) {
            parsePackage();
        }
    }

    private PackageChunk parsePackage() throws IOException {
        long start = this.is.getPos();
        this.is.checkInt16(512, "Not a table chunk");
        int headerSize = this.is.readInt16();
        if (!(headerSize == 284 || headerSize == 288)) {
            die("Unexpected package header size");
        }
        long endPos = start + this.is.readUInt32();
        int id = this.is.readInt32();
        String name = this.is.readString16Fixed(128);
        long typeStringsOffset = start + ((long) this.is.readInt32());
        this.is.readInt32();
        long keyStringsOffset = start + ((long) this.is.readInt32());
        this.is.readInt32();
        if (headerSize == 288) {
            this.is.readInt32();
        }
        String[] typeStrings = null;
        if (typeStringsOffset != 0) {
            this.is.skipToPos(typeStringsOffset, "Expected typeStrings string pool");
            typeStrings = parseStringPool();
        }
        String[] keyStrings = null;
        if (keyStringsOffset != 0) {
            this.is.skipToPos(keyStringsOffset, "Expected keyStrings string pool");
            keyStrings = parseStringPool();
        }
        PackageChunk pkg = new PackageChunk(id, name, typeStrings, keyStrings);
        if (id == 127) {
            this.resStorage.setAppPackage(name);
        }
        while (this.is.getPos() < endPos) {
            long chunkStart = this.is.getPos();
            int type = this.is.readInt16();
            if (type != 0) {
                if (type == 514) {
                    parseTypeSpecChunk();
                } else if (type == 513) {
                    parseTypeChunk(chunkStart, pkg);
                }
            }
        }
        return pkg;
    }

    private void parseTypeSpecChunk() throws IOException {
        this.is.checkInt16(16, "Unexpected type spec header size");
        this.is.readInt32();
        int id = this.is.readInt8();
        this.is.skip(3);
        int entryCount = this.is.readInt32();
        for (int i = 0; i < entryCount; i++) {
            this.is.readInt32();
        }
    }

    private void parseTypeChunk(long start, PackageChunk pkg) throws IOException {
        int i;
        this.is.readInt16();
        this.is.readInt32();
        int id = this.is.readInt8();
        this.is.checkInt8(0, "type chunk, res0");
        this.is.checkInt16(0, "type chunk, res1");
        int entryCount = this.is.readInt32();
        long entriesStart = start + ((long) this.is.readInt32());
        EntryConfig config = parseConfig();
        int[] entryIndexes = new int[entryCount];
        for (i = 0; i < entryCount; i++) {
            entryIndexes[i] = this.is.readInt32();
        }
        this.is.checkPos(entriesStart, "Expected entry start");
        for (i = 0; i < entryCount; i++) {
            if (entryIndexes[i] != -1) {
                parseEntry(pkg, id, i, config);
            }
        }
    }

    private void parseEntry(PackageChunk pkg, int typeId, int entryId, EntryConfig config) throws IOException {
        this.is.readInt16();
        int flags = this.is.readInt16();
        int key = this.is.readInt32();
        ResourceEntry ri = new ResourceEntry(((pkg.getId() << 24) | (typeId << 16)) | entryId, pkg.getName(), pkg.getTypeStrings()[typeId - 1], pkg.getKeyStrings()[key]);
        ri.setConfig(config);
        if ((flags & 1) == 0) {
            ri.setSimpleValue(parseValue());
        } else {
            ri.setParentRef(this.is.readInt32());
            int count = this.is.readInt32();
            List<RawNamedValue> values = new ArrayList(count);
            for (int i = 0; i < count; i++) {
                values.add(parseValueMap());
            }
            ri.setNamedValues(values);
        }
        this.resStorage.add(ri);
    }

    private RawNamedValue parseValueMap() throws IOException {
        return new RawNamedValue(this.is.readInt32(), parseValue());
    }

    private RawValue parseValue() throws IOException {
        this.is.checkInt16(8, "value size");
        this.is.checkInt8(0, "value res0 not 0");
        return new RawValue(this.is.readInt8(), this.is.readInt32());
    }

    private EntryConfig parseConfig() throws IOException {
        long start = this.is.getPos();
        int size = this.is.readInt32();
        EntryConfig config = new EntryConfig();
        this.is.readInt16();
        this.is.readInt16();
        config.setLanguage(parseLocale());
        config.setCountry(parseLocale());
        int orientation = this.is.readInt8();
        int touchscreen = this.is.readInt8();
        int density = this.is.readInt16();
        this.is.skipToPos(((long) size) + start, "Skip config parsing");
        return config;
    }

    private String parseLocale() throws IOException {
        int b1 = this.is.readInt8();
        int b2 = this.is.readInt8();
        if (b1 == 0 || b2 == 0) {
            return null;
        }
        if ((b1 & 128) == 0) {
            return new String(new char[]{(char) b1, (char) b2});
        }
        LOG.warn("TODO: parse locale: 0x{}{}", Integer.toHexString(b1), Integer.toHexString(b2));
        return null;
    }
}
