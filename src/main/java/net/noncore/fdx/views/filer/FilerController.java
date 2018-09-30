package net.noncore.fdx.views.filer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.noncore.fdx.domains.file.VPath;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class FilerController implements Initializable {
    @FXML
    private TableView<FileRow> table;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn updateDateColumn;
    @FXML
    private TableColumn sizeColumn;
    @FXML
    private TableColumn typeColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<FileRow, String>("name"));
        updateDateColumn.setCellValueFactory(new PropertyValueFactory<FileRow, String>("updateDate"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<FileRow, Long>("size"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<FileRow, String>("type"));

        VPath.getUserHome().toFile().getChildren().forEach(file -> {
            table.getItems().add(new FileRow(file));
        });
    }
}
