package net.noncore.fdx.views.filer;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import net.noncore.fdx.domains.file.VFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class FileRow {
    private StringProperty nameProperty;
    private StringProperty updateDateProperty;
    private LongProperty sizeProperty;
    private StringProperty typeProperty;

    public FileRow(VFile file) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh.mm.ss");
        nameProperty = new SimpleStringProperty(file.getName());
        updateDateProperty = new SimpleStringProperty(file.getLastModifiedDateTime().format(formatter));
        sizeProperty = new SimpleLongProperty(file.getSize());
        typeProperty = new SimpleStringProperty(file.getType().toString());
    }

    public StringProperty nameProperty() {
        return nameProperty;
    }

    public StringProperty updateDateProperty() {
        return updateDateProperty;
    }

    public LongProperty sizeProperty() {
        return sizeProperty;
    }

    public StringProperty typeProperty() {
        return typeProperty;
    }
}
