import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PublicView {
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

}
