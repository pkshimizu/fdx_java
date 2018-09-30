package net.noncore.fdx.domains.file;

import java.util.List;

public interface VFileSystem {
    String getScheme();
    boolean isAccept(final String path);
    VFile getFile(final VPath path);
    VFile createFile(final VPath path);
    VFile createDirectory(final VPath path);
    List<VPath> getRoots();
}
