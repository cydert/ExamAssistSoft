import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ExamCalC {
	ExamCalV calV;
	ExamCalFileV calFileV;
	ExamCalFileM calFileM;
	ExamCalM calM;

	ExamCalC(Stage stage){
		calV = new ExamCalV(stage);
		calFileM = new ExamCalFileM();
		calM = new ExamCalM();

		calV.bindModel(calFileM,calM);


		//ボタン時の動作
		calV.getButton(0).setOnAction(e -> selectTemplate());//テンプレボタン
		calV.getButton(1).setOnAction(e -> editFx());		//式編集ボタン

	}

	//テンプレファイル選択画面
	void selectTemplate(){
		calFileM.clearDirPath();
		calFileV = new ExamCalFileV();
		calFileV.bindModel(calFileM);
		calFileV.changeFileList();//ファイル一覧表示
		calFileV.getListView().setOnMouseClicked(e -> selectFile(e));
	}

	void editFx(){
		PublicView.showAlert("未実装です");
	}

	//ファイル選択されたら
	void selectFile(MouseEvent e){
		//ダブルクリックされたら
		boolean doubleClick = e.getButton().equals(MouseButton.PRIMARY) && (e.getClickCount() == 2);
		if(doubleClick){
			String selectSt = calFileV.getListView().getSelectionModel().getSelectedItem();//選択されたもの
			calFileM.setCurrentFileName(selectSt + ".txt");	//FilePathを正式名称で登録
			calFileV.getStage().hide();			//Window閉じる

			System.out.println(calFileM.getCurrentFileName());
			calV.showScoreList(true);
		}
		//文をもらう
		//Modelへ渡して解析
		//Viewに通知

		calV.setTestBox();
	}
}
