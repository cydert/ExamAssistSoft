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
	}

	void editFx(){
		PublicView.showAlert("未実装です");
	}
}
