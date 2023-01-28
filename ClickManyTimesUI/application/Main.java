package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
//			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClickManyTimes.fxml"));
			SampleController.root = (VBox) FXMLLoader.load(getClass().getResource("ClickManyTimes.fxml"));
			Scene scene = new Scene(SampleController.root, 600, 400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
