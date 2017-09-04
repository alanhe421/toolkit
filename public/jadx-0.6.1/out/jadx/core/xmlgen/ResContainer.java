package jadx.core.xmlgen;

import jadx.core.codegen.CodeWriter;
import jadx.core.utils.exceptions.JadxRuntimeException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ResContainer implements Comparable<ResContainer> {
    @Nullable
    private CodeWriter content;
    @Nullable
    private BufferedImage image;
    private final String name;
    private final List<ResContainer> subFiles;

    private ResContainer(String name, List<ResContainer> subFiles) {
        this.name = name;
        this.subFiles = subFiles;
    }

    public static ResContainer singleFile(String name, CodeWriter content) {
        ResContainer resContainer = new ResContainer(name, Collections.emptyList());
        resContainer.content = content;
        return resContainer;
    }

    public static ResContainer singleImageFile(String name, InputStream content) {
        ResContainer resContainer = new ResContainer(name, Collections.emptyList());
        try {
            resContainer.image = ImageIO.read(content);
            return resContainer;
        } catch (Exception e) {
            throw new JadxRuntimeException("Image load error", e);
        }
    }

    public static ResContainer multiFile(String name) {
        return new ResContainer(name, new ArrayList());
    }

    public String getName() {
        return this.name;
    }

    public String getFileName() {
        return this.name.replace("/", File.separator);
    }

    @Nullable
    public CodeWriter getContent() {
        return this.content;
    }

    public void setContent(@Nullable CodeWriter content) {
        this.content = content;
    }

    @Nullable
    public BufferedImage getImage() {
        return this.image;
    }

    public List<ResContainer> getSubFiles() {
        return this.subFiles;
    }

    public int compareTo(@NotNull ResContainer o) {
        return this.name.compareTo(o.name);
    }

    public String toString() {
        return "Res{" + this.name + ", subFiles=" + this.subFiles + "}";
    }
}
