package net.noncore.fdx.domains.file.local;

import net.noncore.fdx.domains.file.FileException;
import net.noncore.fdx.domains.file.VFile;
import net.noncore.fdx.domains.file.VFileType;
import net.noncore.fdx.domains.file.VPath;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class LocalFile implements VFile {
    private VPath vpath;
    private Path path;

    public LocalFile(VPath vpath) {
        this.vpath = vpath;
        path = Paths.get(vpath.toString());
    }

    @Override
    public String getName() {
        Path fileName = path.getFileName();
        if (fileName == null) {
            return vpath.toString();
        }
        String name = fileName.toString();
        if (name == null) {
            return vpath.toString();
        }
        return name;
    }

    @Override
    public VPath getPath() {
        return vpath;
    }

    @Override
    public InputStream getInputStream() {
        try {
            return new FileInputStream(path.toString());
        } catch (FileNotFoundException e) {
            throw new FileException(e);
        }
    }

    @Override
    public OutputStream getOutputStream() {
        try {
            return new FileOutputStream(path.toString());
        } catch (FileNotFoundException e) {
            throw new FileException(e);
        }
    }

    @Override
    public List<VFile> getChildren(Comparator<VFile> comparator) {
        try (DirectoryStream<Path> dir = Files.newDirectoryStream(path)) {
            Spliterator<Path> iterator = Spliterators.spliteratorUnknownSize(dir.iterator(), 0);
            return StreamSupport.stream(iterator, false)
                    .map(path -> path.toString())
                    .map(VPath::new)
                    .map(LocalFile::new)
                    .sorted(comparator)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileException(e);
        }
    }

    @Override
    public VFile getParent() {
        Path parent = path.getParent();
        if (parent == null) {
            return this;
        }
        return new LocalFile(new VPath(parent.toString()));
    }

    @Override
    public boolean isReadable() {
        return Files.isReadable(path);
    }

    @Override
    public boolean isWritable() {
        return Files.isWritable(path);
    }

    @Override
    public boolean isExecutable() {
        return Files.isExecutable(path);
    }

    @Override
    public boolean isHidden() {
        try {
            return Files.isHidden(path);
        } catch (IOException e) {
            throw new FileException(e);
        }
    }

    @Override
    public boolean exists() {
        return Files.exists(path);
    }

    @Override
    public long getSize() {
        try {
            return Files.size(path);
        } catch (IOException e) {
            throw new FileException(e);
        }
    }

    @Override
    public LocalDateTime getLastModifiedDateTime() {
        try {
            return LocalDateTime.ofInstant(Files.getLastModifiedTime(path).toInstant(), ZoneId.systemDefault());
        } catch (IOException e) {
            throw new FileException(e);
        }
    }

    @Override
    public VFileType getType() {
        if (Files.isRegularFile(path)) {
            return VFileType.FILE;
        } else if (Files.isDirectory(path)) {
            return VFileType.DIRECTORY;
        }
        return VFileType.OTHER;
    }

    @Override
    public void copy(VPath path) {
        try {
            Files.copy(this.path, Paths.get(path.toString()));
        } catch (IOException e) {
            throw new FileException(e);
        }
    }

    @Override
    public void move(VPath path) {
        try {
            Files.move(this.path, Paths.get(path.toString()));
        } catch (IOException e) {
            throw new FileException(e);
        }
    }

    @Override
    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new FileException(e);
        }
    }
}
