package jadx.core.export;

import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.nodes.ClassNode;
import jadx.core.dex.nodes.DexNode;
import jadx.core.dex.nodes.RootNode;
import jadx.core.utils.exceptions.JadxRuntimeException;
import jadx.core.utils.files.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExportGradleProject {
    private static final Set<String> IGNORE_CLS_NAMES = new HashSet(Arrays.asList(new String[]{"R", "BuildConfig"}));
    private static final Logger LOG = LoggerFactory.getLogger(ExportGradleProject.class);
    private final File outDir;
    private File resOutDir;
    private final RootNode root;
    private File srcOutDir;

    public ExportGradleProject(RootNode root, File outDir) {
        this.root = root;
        this.outDir = outDir;
        this.srcOutDir = new File(outDir, "src/main/java");
        this.resOutDir = new File(outDir, "src/main");
    }

    public void init() {
        try {
            FileUtils.makeDirsForFile(this.srcOutDir);
            FileUtils.makeDirsForFile(this.resOutDir);
            saveBuildGradle();
            skipGeneratedClasses();
        } catch (Exception e) {
            throw new JadxRuntimeException("Gradle export failed", e);
        }
    }

    private void saveBuildGradle() throws IOException {
        TemplateFile tmpl = TemplateFile.fromResources("/export/build.gradle.tmpl");
        String appPackage = this.root.getAppPackage();
        if (appPackage == null) {
            appPackage = "UNKNOWN";
        }
        tmpl.add("applicationId", appPackage);
        tmpl.add("minSdkVersion", Integer.valueOf(9));
        tmpl.add("targetSdkVersion", Integer.valueOf(21));
        tmpl.save(new File(this.outDir, "build.gradle"));
    }

    private void skipGeneratedClasses() {
        for (DexNode dexNode : this.root.getDexNodes()) {
            for (ClassNode cls : dexNode.getClasses()) {
                if (IGNORE_CLS_NAMES.contains(cls.getClassInfo().getShortName())) {
                    cls.add(AFlag.DONT_GENERATE);
                    LOG.debug("Skip class: {}", cls);
                }
            }
        }
    }

    public File getSrcOutDir() {
        return this.srcOutDir;
    }

    public File getResOutDir() {
        return this.resOutDir;
    }
}
