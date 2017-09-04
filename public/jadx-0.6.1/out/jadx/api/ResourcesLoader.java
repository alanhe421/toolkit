package jadx.api;

import jadx.api.ResourceFile.ZipRef;
import jadx.core.codegen.CodeWriter;
import jadx.core.utils.Utils;
import jadx.core.utils.exceptions.JadxException;
import jadx.core.utils.files.FileUtils;
import jadx.core.utils.files.InputFile;
import jadx.core.xmlgen.ResContainer;
import jadx.core.xmlgen.ResTableParser;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ResourcesLoader {
    private static final int LOAD_SIZE_LIMIT = 10485760;
    private static final Logger LOG = LoggerFactory.getLogger(ResourcesLoader.class);
    private final JadxDecompiler jadxRef;

    public interface ResourceDecoder {
        ResContainer decode(long j, InputStream inputStream) throws IOException;
    }

    ResourcesLoader(JadxDecompiler jadxRef) {
        this.jadxRef = jadxRef;
    }

    List<ResourceFile> load(List<InputFile> inputFiles) {
        List<ResourceFile> list = new ArrayList(inputFiles.size());
        for (InputFile file : inputFiles) {
            loadFile(list, file.getFile());
        }
        return list;
    }

    public static ResContainer decodeStream(ResourceFile rf, ResourceDecoder decoder) throws JadxException {
        Exception e;
        Throwable th;
        ZipRef zipRef = rf.getZipRef();
        if (zipRef == null) {
            return null;
        }
        ZipFile zipFile = null;
        InputStream inputStream = null;
        try {
            ZipFile zipFile2 = new ZipFile(zipRef.getZipFile());
            try {
                ZipEntry entry = zipFile2.getEntry(zipRef.getEntryName());
                if (entry == null) {
                    throw new IOException("Zip entry not found: " + zipRef);
                }
                InputStream inputStream2 = new BufferedInputStream(zipFile2.getInputStream(entry));
                try {
                    ResContainer result = decoder.decode(entry.getSize(), inputStream2);
                    if (zipFile2 != null) {
                        try {
                            zipFile2.close();
                        } catch (Exception e2) {
                            LOG.error("Error close zip file: {}", zipRef, e2);
                        }
                    }
                    FileUtils.close(inputStream2);
                    return result;
                } catch (Exception e3) {
                    e2 = e3;
                    inputStream = inputStream2;
                    zipFile = zipFile2;
                    try {
                        throw new JadxException("Error decode: " + zipRef.getEntryName(), e2);
                    } catch (Throwable th2) {
                        th = th2;
                        if (zipFile != null) {
                            try {
                                zipFile.close();
                            } catch (Exception e22) {
                                LOG.error("Error close zip file: {}", zipRef, e22);
                            }
                        }
                        FileUtils.close(inputStream);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    inputStream = inputStream2;
                    zipFile = zipFile2;
                    if (zipFile != null) {
                        zipFile.close();
                    }
                    FileUtils.close(inputStream);
                    throw th;
                }
            } catch (Exception e4) {
                e22 = e4;
                zipFile = zipFile2;
                throw new JadxException("Error decode: " + zipRef.getEntryName(), e22);
            } catch (Throwable th4) {
                th = th4;
                zipFile = zipFile2;
                if (zipFile != null) {
                    zipFile.close();
                }
                FileUtils.close(inputStream);
                throw th;
            }
        } catch (Exception e5) {
            e22 = e5;
            throw new JadxException("Error decode: " + zipRef.getEntryName(), e22);
        }
    }

    static ResContainer loadContent(final JadxDecompiler jadxRef, final ResourceFile rf) {
        try {
            return decodeStream(rf, new ResourceDecoder() {
                public ResContainer decode(long size, InputStream is) throws IOException {
                    return ResourcesLoader.loadContent(jadxRef, rf, is, size);
                }
            });
        } catch (JadxException e) {
            LOG.error("Decode error", e);
            CodeWriter cw = new CodeWriter();
            cw.add("Error decode ").add(rf.getType().toString().toLowerCase());
            cw.startLine(Utils.getStackTrace(e.getCause()));
            return ResContainer.singleFile(rf.getName(), cw);
        }
    }

    private static ResContainer loadContent(JadxDecompiler jadxRef, ResourceFile rf, InputStream inputStream, long size) throws IOException {
        switch (rf.getType()) {
            case MANIFEST:
            case XML:
                return ResContainer.singleFile(rf.getName(), jadxRef.getXmlParser().parse(inputStream));
            case ARSC:
                return new ResTableParser().decodeFiles(inputStream);
            case IMG:
                return ResContainer.singleImageFile(rf.getName(), inputStream);
            default:
                if (size <= 10485760) {
                    return ResContainer.singleFile(rf.getName(), loadToCodeWriter(inputStream));
                }
                return ResContainer.singleFile(rf.getName(), new CodeWriter().add("File too big, size: " + String.format("%.2f KB", new Object[]{Double.valueOf(((double) size) / 1024.0d)})));
        }
    }

    private void loadFile(List<ResourceFile> list, File file) {
        Throwable th;
        if (file != null) {
            ZipFile zipFile = null;
            try {
                ZipFile zip = new ZipFile(file);
                try {
                    Enumeration<? extends ZipEntry> entries = zip.entries();
                    while (entries.hasMoreElements()) {
                        addEntry(list, file, (ZipEntry) entries.nextElement());
                    }
                    if (zip != null) {
                        try {
                            zip.close();
                            zipFile = zip;
                            return;
                        } catch (Exception e) {
                            LOG.error("Zip file close error: {}", file.getAbsolutePath(), e);
                            zipFile = zip;
                            return;
                        }
                    }
                } catch (IOException e2) {
                    zipFile = zip;
                    try {
                        LOG.debug("Not a zip file: {}", file.getAbsolutePath());
                        if (zipFile != null) {
                            try {
                                zipFile.close();
                            } catch (Exception e3) {
                                LOG.error("Zip file close error: {}", file.getAbsolutePath(), e3);
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (zipFile != null) {
                            try {
                                zipFile.close();
                            } catch (Exception e32) {
                                LOG.error("Zip file close error: {}", file.getAbsolutePath(), e32);
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    zipFile = zip;
                    if (zipFile != null) {
                        zipFile.close();
                    }
                    throw th;
                }
            } catch (IOException e4) {
                LOG.debug("Not a zip file: {}", file.getAbsolutePath());
                if (zipFile != null) {
                    zipFile.close();
                }
            }
        }
    }

    private void addEntry(List<ResourceFile> list, File zipFile, ZipEntry entry) {
        if (!entry.isDirectory()) {
            String name = entry.getName();
            ResourceFile rf = new ResourceFile(this.jadxRef, name, ResourceType.getFileType(name));
            rf.setZipRef(new ZipRef(zipFile, name));
            list.add(rf);
        }
    }

    public static CodeWriter loadToCodeWriter(InputStream is) throws IOException {
        CodeWriter cw = new CodeWriter();
        ByteArrayOutputStream baos = new ByteArrayOutputStream(FileUtils.READ_BUFFER_SIZE);
        FileUtils.copyStream(is, baos);
        cw.add(baos.toString("UTF-8"));
        return cw;
    }
}
