package jadx.core.xmlgen;

import jadx.api.ResourceFile;
import jadx.api.ResourceType;
import jadx.core.codegen.CodeWriter;
import jadx.core.utils.files.FileUtils;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourcesSaver implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(ResourcesSaver.class);
    private File outDir;
    private final ResourceFile resourceFile;

    public ResourcesSaver(File outDir, ResourceFile resourceFile) {
        this.resourceFile = resourceFile;
        this.outDir = outDir;
    }

    public void run() {
        if (ResourceType.isSupportedForUnpack(this.resourceFile.getType())) {
            ResContainer rc = this.resourceFile.loadContent();
            if (rc != null) {
                saveResources(rc);
            }
        }
    }

    private void saveResources(ResContainer rc) {
        if (rc != null) {
            List<ResContainer> subFiles = rc.getSubFiles();
            if (subFiles.isEmpty()) {
                save(rc, this.outDir);
                return;
            }
            for (ResContainer subFile : subFiles) {
                saveResources(subFile);
            }
        }
    }

    private void save(ResContainer rc, File outDir) {
        File outFile = new File(outDir, rc.getFileName());
        BufferedImage image = rc.getImage();
        if (image != null) {
            try {
                ImageIO.write(image, FilenameUtils.getExtension(outFile.getName()), FileUtils.prepareFile(outFile));
                return;
            } catch (IOException e) {
                LOG.error("Failed to save image: {}", rc.getName(), e);
                return;
            }
        }
        CodeWriter cw = rc.getContent();
        if (cw != null) {
            cw.save(outFile);
        } else {
            LOG.warn("Resource '{}' not saved, unknown type", rc.getName());
        }
    }
}
