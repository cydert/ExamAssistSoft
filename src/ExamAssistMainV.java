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

		//�{�^��
		button = new Button[2];
		button[0] = new Button("�}�C�y�[�W");	//��\����
		button[1] = new Button("�e�X�g�_�v�Z");
		button[1].setFont(new Font(30));

		for(int i=0; i<button.length; i++){
			button[i].setId(i + "");
			button[i].setPrefSize(200,100);

		}
		Label label = new Label("��������@�v�Z�\�t�g");
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


