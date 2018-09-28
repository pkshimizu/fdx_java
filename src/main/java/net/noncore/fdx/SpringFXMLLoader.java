package net.noncore.fdx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SpringFXMLLoader {

    @Autowired
    private ApplicationContext context;

    public Parent load(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(context::getBean);
        return loader.load(SpringFXMLLoader.class.getClassLoader().getResourceAsStream(path));
    }
}
