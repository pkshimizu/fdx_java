package net.noncore.fdx.domains.file;

import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.Collection;

public interface VFile {
    String getName();
    VPath getPath();
    InputStream getInputStream();
    OutputStream getOutputStream();
    Collection<VFile> getChildren();
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
