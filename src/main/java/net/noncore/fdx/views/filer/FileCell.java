package net.noncore.fdx.views.filer;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class FileCell extends ListCell<FileRow> {
    private static final String FONT_NAME = "Osaka-Mono";
    private static final int FONT_SIZE = 16;
    private AnchorPane container;
    private Label name;
    private Label size;
    private Label date;

    public FileCell() {
        container = new AnchorPane();
        container.setMinWidth(0);
        container.setPrefWidth(1);
        name = createLabel(0.0, 0.0, 250.0);
        size = createLabel(0.0, null, 170.0);
        date = createLabel(0.0, null, 0.0);
        container.getChildren().addAll(name, size, date);
    }

    private Label createLabel(Double top, Double left, Double right) {
        Label label = new Label();
        label.setTextOverrun(OverrunStyle.CENTER_ELLIPSIS);
        label.setEllipsisString("...");
        AnchorPane.setTopAnchor(label, top);
        AnchorPane.setLeftAnchor(label, left);
        AnchorPane.setRightAnchor(label, right);
        label.setFont(new Font(FONT_NAME, FONT_SIZE));
        return label;
    }

    @Override
    protected void updateItem(FileRow item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            name.setText(item.nameProperty().get());
            size.setText(item.sizeProperty().get());
            date.setText(item.updateDateProperty().get());
            setGraphic(container);
        }
    }
}
