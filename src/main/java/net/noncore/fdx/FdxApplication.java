package net.noncore.fdx;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class FdxApplication extends Application {
    private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(FdxApplication.class, args);
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		SpringFXMLLoader loader = context.getBean(SpringFXMLLoader.class);
		Parent root = loader.load("fdx.fxml");
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void stop() throws Exception {
	    context.close();
	}
}
