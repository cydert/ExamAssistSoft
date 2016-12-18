import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ExamAssistMainV {
	Button[] button;
	ExamAssistMainV(Stage stage) {
		VBox root = new VBox();

		//�{�^��
		button = new Button[2];
		button[0] = new Button("�}�C�y�[�W");
		button[1] = new Button("�e�X�g�_�v�Z");
		for(int i=0; i<button.length; i++){
			button[i].setId(i + "");
		}

		root.getChildren().addAll(button);
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


