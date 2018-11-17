package net.noncore.fdx.domains.file;

import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface VFile {
    String getName();
    VPath getPath();
    InputStream getInputStream();
    OutputStream getOutputStream();
    default Collection<VFile> getChildren() {
        return getChildren(Comparator.comparing(VFile::getPath));
    }
    List<VFile> getChildren(Comparator<VFile> comparator);
    VFile getParent();
    boolean isReadable();
    boolean isWritable();
    boolean isExecutable();
    boolean isHidden();
    boolean exists();
    long getSize();
    LocalDateTime getLastModifiedDateTime();
    VFileType getType();
    void copy(VPath path);
    void move(VPath path);
    void remove();
}
