package net.noncore.fdx.views.filer;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import net.noncore.fdx.domains.file.VFile;
import net.noncore.fdx.domains.file.VFileType;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;

public class FileRow {
    private VFile file;
    private StringProperty nameProperty;
    private StringProperty updateDateProperty;
    private StringProperty sizeProperty;

    public FileRow(VFile file) {
        this.file = file;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh.mm.ss");
        nameProperty = new SimpleStringProperty(file.getName());
        updateDateProperty = new SimpleStringProperty(file.getLastModifiedDateTime().format(formatter));
        if (file.getType() == VFileType.DIRECTORY) {
            sizeProperty = new SimpleStringProperty("<DIR>");
        } else {
            NumberFormat format = NumberFormat.getNumberInstance();
            sizeProperty = new SimpleStringProperty(format.format(file.getSize()));
        }
    }

    public StringProperty nameProperty() {
        return nameProperty;
    }

    public StringProperty updateDateProperty() {
        return updateDateProperty;
    }

    public StringProperty sizeProperty() {
        return sizeProperty;
    }
}
