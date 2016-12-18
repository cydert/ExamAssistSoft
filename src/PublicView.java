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

}
