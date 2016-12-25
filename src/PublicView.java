import java.util.Stack;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class PublicView {
	private static Stage stage;
	public static Stack<Scene> sceneStack = new Stack<>();
	public static Alert showAlert(String msg){
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("íçà”");
		alert.setHeaderText(msg);
		alert.show();
		return alert;
	}

	public static Alert showInfoAlert(String msg){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setHeaderText(msg);
		alert.show();
		return alert;
	}

	public static void reShow(Scene scene){
		stage.setScene(scene);
	}

	public static void bindStage(Stage stage){
		PublicView.stage = stage;
	}

}
