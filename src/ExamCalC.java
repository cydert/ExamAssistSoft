import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ExamCalC {
	Stage stage;
	ExamCalV calV; // 計算画面
	ExamCalFileV calFileV; // ファイル管理画面
	ExamCalM calM;

	ExamCalC(Stage stage) {
		this.stage = stage;
		calV = new ExamCalV(stage);
		calM = new ExamCalM();

		calV.bindModel(calM);

		// ボタン時の動作
		calV.getButton(0).setOnAction(e -> selectTemplate());// テンプレボタン
		calV.getButton(1).setOnAction(e -> editFx()); // 式編集ボタン
		calV.getButton(2).setOnAction(e -> save()); // 保存ボタン
		calV.getButton(3).setOnAction(e -> cal()); // 計算ボタン
		calV.getButton(4).setOnAction(e -> showFormula()); // 表示中の式を表示
		calV.getButton(5).setOnAction(e -> backKey());

	}

	// テンプレファイル選択画面
	void selectTemplate() {
		calM.clearFileViewPath();
		calFileV = new ExamCalFileV(); // 画面表示
		calFileV.bindModel(calM);
		calFileV.changeFileList();// ファイル一覧表示
		calFileV.getListView().setOnMouseClicked(e -> selectFile(e));
	}

	void editFx() {
		calV.stackScene();
		ExamCalEditV exV = new ExamCalEditV(stage);
<<<<<<< HEAD

=======
		//exV.getFormula().get(0).setOnMouseClicked(e -> ExamCalEditV.caletI = exV.getFormula().getCaretPosition()); TODO
>>>>>>> 6e37d9d7a89acc0edd198e96548cc771b43afd3b
		for (int i = 0; i < exV.getMathBt().length; i++) {
			String input = exV.getMathBt()[i].getText();
			exV.getMathBt()[i].setOnAction(e -> exV.addFormula(input)); // 入力したものをそのまま表示
		}
		for (int i = 0; i < exV.getCalB().length; i++) {
			String input = exV.getCalB()[i].getText(); // キャレット移動か演算子ボタン
			exV.getCalB()[i].setOnAction(e -> {
				if (input.equals("←")) {
					//ExamCalEditV.caletI--;
<<<<<<< HEAD
					exV.leftCaret();
				} else if (input.equals("→")) {
					//ExamCalEditV.caletI++;
					exV.rightCaret();
=======
					exV.showCallet();
				} else if (input.equals("→")) {
					//ExamCalEditV.caletI++;
					exV.showCallet();
>>>>>>> 6e37d9d7a89acc0edd198e96548cc771b43afd3b
				} else if (input.equals("DEL")) {
					exV.delFormula();
				}else if(input.equals("C")){
					exV.clearFormula();
				}else if(input.equals("テスト点")){
					AddExamButtonConfig config = new AddExamButtonConfig();
					config.getConfirm().setOnAction(ev ->{	//確定ボタン押したら
						exV.addTestBt(config.getText());	//ボタン追加
						config.close();
					});
				}else if(input.equals("平常点")){
					AddHeijouButtonConfig config = new AddHeijouButtonConfig();
					config.getConfirm().setOnAction(ev ->{
						exV.addHeijouBt(config.getText(),config.getMax());
						config.close();
					});
				} else {
					exV.addFormula(input);// 演算子
				}
			});
		}
<<<<<<< HEAD
		exV.getSaveBt()[0].setOnAction(e -> {	//テンプレートとして保存(ファイル書き込み)
=======
		exV.getSaveBt()[0].setOnAction(e -> {
>>>>>>> 6e37d9d7a89acc0edd198e96548cc771b43afd3b
			String fileName = exV.getFileName();
			if(fileName.equals(""))	PublicView.showAlert("教科名を入力してください");
			else{
				FileWrite.fileWrite(calM.getFileHost()+"\\"+fileName+".txt", calM.makeTxFormula(exV.getFormulaHBox()));
				stage.setScene(PublicView.sceneStack.pop());
			}
<<<<<<< HEAD
=======

>>>>>>> 6e37d9d7a89acc0edd198e96548cc771b43afd3b
		});
	}

	void save() {
		PublicView.showAlert("未実装です");
	}

	// 計算ボタン押したら
	void cal() {
		if (calV.getExamGoalScore().equals("")) { // 目標点未入力
			PublicView.showAlert("目標点を入力してください");
		} else {
			// 入力Box取得
			String[] examList = new String[calV.getExamTextField().length]; // テスト入力欄
			for (int i = 0; i < examList.length; i++) {
				examList[i] = calV.getExamTextFiled(i).getText(); // 取得
			}

			// 式、目標点、入力した値を渡す
			calM.setInit(calV.getExamGoalScore(), examList, calV.getHeijou());

			// 計算後の値をもらす
			int ans = calM.cal();
			System.out.println(ans);
			for (int i = 0; i < examList.length; i++) {
				if (!calM.getExamInput()[i])// もともと入力されてないなら
					examList[i] = ans + ""; // 値をセット
			}
			calM.setExamList(examList, false);
			calV.setNewScoewInBox();

		}
	}

	// ファイル選択されたら
	void selectFile(MouseEvent e) {
		// ダブルクリックされたら
		boolean doubleClick = e.getButton().equals(MouseButton.PRIMARY) && (e.getClickCount() == 2);
		if (doubleClick) {
			calV.clearTestBox(); // testBox 初期化
			String selectSt = calFileV.getListView().getSelectionModel().getSelectedItem();// 選択されたもの
			calM.setFileName(selectSt);
			// FilePathを正式名称で登録
			calFileV.getStage().hide(); // Window閉じる

			calV.showScoreList(true);// 入力欄など表示
			calV.setTestBox(); // 入力欄などの作成、表示

		}
	}

	void showFormula() {
		PublicView.showInfoAlert(calM.getFormula());
	}

	void backKey() {
		PublicView.reShow(PublicView.sceneStack.pop());
	}
}
