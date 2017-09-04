package jadx.core.deobf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class PackageNode {
    private static final char SEPARATOR_CHAR = '.';
    private String cachedPackageFullAlias;
    private String cachedPackageFullName;
    private List<PackageNode> innerPackages = Collections.emptyList();
    private String packageAlias;
    private final String packageName;
    private PackageNode parentPackage;

    public PackageNode(String packageName) {
        this.packageName = packageName;
        this.parentPackage = this;
    }

    public String getName() {
        return this.packageName;
    }

    public String getFullName() {
        if (this.cachedPackageFullName == null) {
            Stack<PackageNode> pp = getParentPackages();
            StringBuilder result = new StringBuilder();
            result.append(((PackageNode) pp.pop()).getName());
            while (pp.size() > 0) {
                result.append(SEPARATOR_CHAR);
                result.append(((PackageNode) pp.pop()).getName());
            }
            this.cachedPackageFullName = result.toString();
        }
        return this.cachedPackageFullName;
    }

    public String getAlias() {
        if (this.packageAlias != null) {
            return this.packageAlias;
        }
        return this.packageName;
    }

    public void setAlias(String alias) {
        this.packageAlias = alias;
    }

    public boolean hasAlias() {
        return this.packageAlias != null;
    }

    public String getFullAlias() {
        if (this.cachedPackageFullAlias == null) {
            Stack<PackageNode> pp = getParentPackages();
            StringBuilder result = new StringBuilder();
            if (pp.size() > 0) {
                result.append(((PackageNode) pp.pop()).getAlias());
                while (pp.size() > 0) {
                    result.append(SEPARATOR_CHAR);
                    result.append(((PackageNode) pp.pop()).getAlias());
                }
            } else {
                result.append(getAlias());
            }
            this.cachedPackageFullAlias = result.toString();
        }
        return this.cachedPackageFullAlias;
    }

    public PackageNode getParentPackage() {
        return this.parentPackage;
    }

    public List<PackageNode> getInnerPackages() {
        return this.innerPackages;
    }

    public void addInnerPackage(PackageNode pkg) {
        if (this.innerPackages.isEmpty()) {
            this.innerPackages = new ArrayList();
        }
        this.innerPackages.add(pkg);
        pkg.parentPackage = this;
    }

    public PackageNode getInnerPackageByName(String name) {
        for (PackageNode p : this.innerPackages) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    private Stack<PackageNode> getParentPackages() {
        Stack<PackageNode> pp = new Stack();
        PackageNode parentP = getParentPackage();
        PackageNode currentP;
        while (currentP != parentP) {
            pp.push(currentP);
            currentP = parentP;
            parentP = currentP.getParentPackage();
        }
        return pp;
    }
}
