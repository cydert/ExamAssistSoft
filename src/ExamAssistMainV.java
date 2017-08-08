import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ExamAssistMainV {
	Button[] button;
	ExamAssistMainV(Stage stage) {
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(50);

		//ボタン
		button = new Button[2];
		button[0] = new Button("マイページ");	//非表示中
		button[1] = new Button("テスト点計算");
		button[1].setFont(new Font(30));

		for(int i=0; i<button.length; i++){
			button[i].setId(i + "");
			button[i].setPrefSize(200,100);

		}
		Label label = new Label("定期試験　計算ソフト");
		label.setFont(new Font(40));
		root.getChildren().add(label);
		root.getChildren().addAll(button[1]);

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


}


