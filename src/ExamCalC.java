import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ExamCalC {
	ExamCalV calV;
	ExamCalFileV calFileV;
	ExamCalFileM calFileM;
	ExamCalM calM;

	ExamCalC(Stage stage) {
		calV = new ExamCalV(stage);
		calFileM = new ExamCalFileM();
		calM = new ExamCalM();

		calV.bindModel(calFileM, calM);

		// ボタン時の動作
		calV.getButton(0).setOnAction(e -> selectTemplate());// テンプレボタン
		calV.getButton(1).setOnAction(e -> editFx()); // 式編集ボタン
		calV.getButton(2).setOnAction(e -> save()); // 保存ボタン
		calV.getButton(3).setOnAction(e -> cal()); // 計算ボタン

	}

	// テンプレファイル選択画面
	void selectTemplate() {
		calFileM.clearDirPath();
		calFileV = new ExamCalFileV();
		calFileV.bindModel(calFileM);
		calFileV.changeFileList();// ファイル一覧表示
		calFileV.getListView().setOnMouseClicked(e -> selectFile(e));
	}

	void editFx() {
		PublicView.showAlert("未実装です");
	}

	void save() {
		PublicView.showAlert("未実装です");
	}

	// 計算ボタン
	void cal() { 
		if (calV.getExamGoalScore().equals("")) {	//目標点未入力
			PublicView.showAlert("目標点を入力してください");
		} else {
			PublicView.showAlert("未実装");
			// 入力Box取得
			String[] examList = new String[calV.getExamTextField().length];	//テスト入力欄
			for (int i = 0; i < examList.length; i++) {
				examList[i] = calV.getExamTextFiled(i).getText();	//取得
			}
			
			// 式、目標点、入力した値を渡す
			calM.setInit(calFileM.getFormula(), calV.getExamGoalScore() , examList);
			
			//計算後の値をもらす
			calM.cal();
		}

	}

	// ファイル選択されたら
	void selectFile(MouseEvent e) {
		// ダブルクリックされたら
		boolean doubleClick = e.getButton().equals(MouseButton.PRIMARY)
				&& (e.getClickCount() == 2);
		if (doubleClick) {
			calV.clearTestBox(); // testBox 初期化
			String selectSt = calFileV.getListView().getSelectionModel()
					.getSelectedItem();// 選択されたもの
			calFileM.setCurrentFileName(selectSt + ".txt"); // FilePathを正式名称で登録
			calFileV.getStage().hide(); // Window閉じる

			calFileM.readFile(); // ファイル解析

			calV.showScoreList(true);// 入力欄など表示
			calV.setTestBox();

		}
		// 文をもらう
		// Modelへ渡して解析
		// Viewに通知

	}
}
