package net.noncore.fdx.views.filer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
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
    private TableView<FileRow> table;
    @FXML
    private TableColumn<FileRow, String> nameColumn;
    @FXML
    private TableColumn<FileRow, String> updateDateColumn;
    @FXML
    private TableColumn<FileRow, Long> sizeColumn;
    @FXML
    private TableColumn<FileRow, String> typeColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.widthProperty().addListener((observable, oldValue, newValue) -> {
            // Hide Table Column Header
            Pane header = (Pane) table.lookup("TableHeaderRow");
            if (header.isVisible()) {
                header.setMaxHeight(0);
                header.setMinHeight(0);
                header.setPrefHeight(0);
                header.setVisible(false);
            }
        });

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        updateDateColumn.setCellValueFactory(new PropertyValueFactory<>("updateDate"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));

        Comparator<VFile> comparator = (vfile1, vfile2) -> {
            if (vfile1.getType() == vfile2.getType()) {
                return vfile1.getPath().compareTo(vfile2.getPath());
            }
            return vfile1.getType() == VFileType.DIRECTORY ? -1 : 1;
        };
        VPath.getUserHome().toFile().getChildren(comparator).forEach(file -> {
            table.getItems().add(new FileRow(file));
        });
    }
}
