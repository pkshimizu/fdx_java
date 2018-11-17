package net.noncore.fdx.domains.file;

public enum VFileType {
    FILE("File"),
    DIRECTORY("Dir"),
    OTHER("Other");

    private String name;

    VFileType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
