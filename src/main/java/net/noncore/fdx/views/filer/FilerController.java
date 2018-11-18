package net.noncore.fdx.views.filer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import net.noncore.fdx.domains.file.VFile;
import net.noncore.fdx.domains.file.VFileType;
import net.noncore.fdx.domains.file.VPath;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

@Component
public class FilerController implements Initializable {
    @FXML
    private ListView<FileRow> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.setCellFactory(param -> new FileCell());

        Comparator<VFile> comparator = (vFile1, vFile2) -> {
            if (vFile1.getType() == vFile2.getType()) {
                return vFile1.getPath().compareTo(vFile2.getPath());
            }
            return vFile1.getType() == VFileType.DIRECTORY ? -1 : 1;
        };
        VPath.getUserHome().toFile().getChildren(comparator).forEach(file -> {
            list.getItems().add(new FileRow(file));
        });
    }
}
