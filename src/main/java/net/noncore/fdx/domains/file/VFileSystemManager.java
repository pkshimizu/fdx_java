package net.noncore.fdx.domains.file;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.noncore.fdx.domains.file.local.LocalFileSystem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VFileSystemManager {
    private final static List<VFileSystem> fileSystems = Arrays.asList(
            new LocalFileSystem()
    );

    public static VFileSystem find(final String path) {
        return fileSystems
                .stream()
                .filter(fs -> fs.isAccept(path))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public static Map<String, List<VPath>> getRoots() {
        Map<String, List<VPath>> roots = new HashMap<>();
        fileSystems.forEach(fs -> roots.put(fs.getScheme(), fs.getRoots()));
        return roots;
    }
}
