import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ExamCalC {
	ExamCalV calV;
	ExamCalM calM;
	ExamCalFileV calFileV;

	ExamCalC(Stage stage){
		calV = new ExamCalV(stage);
		calM = new ExamCalM();

		calV.bindModel(calM);

		//ボタン時の動作
		calV.getButton(0).setOnAction(e -> selectTemplate());//テンプレボタン
		calV.getButton(1).setOnAction(e -> editFx());		//式編集ボタン

	}

	void selectTemplate(){
		calFileV = new ExamCalFileV();
		calFileV.bindModel(calM);
		calFileV.changeFileList();
		calFileV.getListView().setOnMouseClicked(e -> selectFile(e));
	}

	void editFx(){
		PublicView.showAlert("未実装です");
	}
	void selectFile(MouseEvent e){
		boolean doubleClick = e.getButton().equals(MouseButton.PRIMARY) && (e.getClickCount() == 2);
		if(doubleClick){
			String selectSt = calFileV.getListView().getSelectionModel().getSelectedItem();

			System.out.println(selectSt);
		}
	}
}
