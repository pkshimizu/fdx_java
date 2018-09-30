package net.noncore.fdx.domains.file.local;

import net.noncore.fdx.domains.file.FileException;
import net.noncore.fdx.domains.file.VFile;
import net.noncore.fdx.domains.file.VFileSystem;
import net.noncore.fdx.domains.file.VPath;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class LocalFileSystem implements VFileSystem {
    @Override
    public String getScheme() {
        return "file";
    }

    @Override
    public boolean isAccept(String path) {
        try {
            Paths.get(path);
            return true;
        } catch (InvalidPathException e) {
            return false;
        }
    }

    @Override
    public VFile getFile(VPath path) {
        return new LocalFile(path);
    }

    @Override
    public VFile createFile(VPath path) {
        try {
            Files.createFile(Paths.get(path.toString()));
            return getFile(path);
        } catch (IOException e) {
            throw new FileException(e);
        }
    }

    @Override
    public VFile createDirectory(VPath path) {
        try {
            Files.createDirectory(Paths.get(path.toString()));
            return getFile(path);
        } catch (IOException e) {
            throw new FileException(e);
        }
    }

    @Override
    public List<VPath> getRoots() {
        return StreamSupport.stream(FileSystems.getDefault().getRootDirectories().spliterator(), false)
                .map(path -> new VPath(path.toString()))
                .collect(Collectors.toList());
    }
}
