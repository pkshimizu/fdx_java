package net.noncore.fdx.domains.file;

public class VPath implements Comparable<VPath> {
    private final String path;
    private final VFileSystem fileSystem;

    public VPath(final String path) {
        this.path = path;
        fileSystem = VFileSystemManager.find(path);
    }

    public VFile toFile() {
        return fileSystem.getFile(this);
    }

    public VFile createFile() {
        return fileSystem.createFile(this);
    }

    public VFile createDirectory() {
        return fileSystem.createDirectory(this);
    }

    @Override
    public int compareTo(VPath other) {
        return path.compareToIgnoreCase(other.toString());
    }

    @Override
    public String toString() {
        return path;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof VPath) {
            return path.equals(other.toString());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }

    public static VPath getUserHome() {
        return new VPath(System.getProperty("user.home"));
    }
}
