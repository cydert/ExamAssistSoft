import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ExamCalV {
	ExamCalM calM;
	private Button[] button;

	ExamCalV(Stage stage){
		VBox root = new VBox();

		button = new Button[2];
		button[0] = new Button("テンプレート");
		button[1] = new Button("式の編集");
		//ボタンの共通初期設定
		for(int i=0; i<button.length; i++){
			button[i].setId(i + "");
		}

		HBox hBox = new HBox();
		hBox.getChildren().addAll(button);

		//表示
		root.getChildren().addAll(hBox);
		stage.setScene(new Scene(root));
	}

	Button getButton(int id){
		for(int i=0; i<button.length; i++){
			if(button[i].getId().equals(id + "")){
				return button[i];
			}
		}
		return null;

	}

	//参照modelの設定
	void bindModel(ExamCalM calM){
		this.calM = calM;
	}

}
