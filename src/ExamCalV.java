import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ExamCalV {
	ExamCalM calM;
	private Button[] button;
	private BorderPane root;
	private VBox vBoxScore;
	private AnchorPane topBar;
	private AnchorPane bottomBar;
	private GridPane centerGrid;

	private TextField[] examField; // exam入力した値
	private Slider[] heijouSlider;
	private TextField examGoalScore; // 目標点

	ExamCalV(Stage stage) {
		root = new BorderPane();

		// ボタン
		button = new Button[4];
		button[0] = new Button("テンプレート");
		button[1] = new Button("式の編集");
		button[2] = new Button("保存");
		button[3] = new Button("計算");
		// ボタンの共通初期設定
		for (int i = 0; i < button.length; i++) {
			button[i].setId(i + "");
		}

		// 上のボタン
		AnchorPane.setLeftAnchor(button[0], 10.0);
		AnchorPane.setRightAnchor(button[1], 10.0);
		topBar = new AnchorPane();
		topBar.getChildren().addAll(button[0], button[1]);

		// 中央のレイアウト
		vBoxScore = new VBox();
		centerGrid = new GridPane();
		centerGrid.setAlignment(Pos.CENTER);
		centerGrid.setHgap(10);// 列間
		centerGrid.setVgap(10);// 行間
		vBoxScore.getChildren().add(centerGrid);

		// 下のボタン
		AnchorPane.setLeftAnchor(button[2], 10.0);
		AnchorPane.setRightAnchor(button[3], 10.0);
		bottomBar = new AnchorPane();
		bottomBar.getChildren().addAll(button[2], button[3]);

		// 表示
		root.setTop(topBar);
		root.setCenter(vBoxScore);
		root.setBottom(bottomBar);
		showScoreList(false);
		stage.setScene(new Scene(root));
	}

	//入力BOXの表示
	void setTestBox() {
		int index=0;	//追加された行数
		String[] examList = calM.getExamInfoList(); // テスト名取得 例:前期中間
		examField = new TextField[examList.length]; // 入力boxの配列 テスト点用
		for (int i = 0; i < examList.length; i++) {
			centerGrid.add(new Label(examList[i].split(",")[0]), 0, i);
			examField[i] = new TextField();
			examField[i].setId(i + "");
			centerGrid.add(examField[i], 1, i);
			index++;
		}

		index++;
		String[] heijouList = calM.getHeijouInfoList();
		heijouSlider = new Slider[heijouList.length];
		for(int i=0; i<heijouList.length; i++){
			int max = Integer.parseInt(heijouList[i].split(",")[1]);
			String name = heijouList[i].split(",")[0];
			heijouSlider[i] = new Slider(0,max,100);
			centerGrid.add(new Label( name ), 0, index);
			centerGrid.add(heijouSlider[i], 1, index);
		}


		//目標点Box
		examGoalScore = new TextField();
		centerGrid.add(new Label("目標点"), 0, index + 2);
		centerGrid.add(examGoalScore, 1, index + 2);

	}

	//未入力場所に計算結果を表示
	void setNewScoewInBox(){
		double[] examScoreAr = calM.getExamScoreAr();
		for(int i=0; i<examScoreAr.length; i++){
			if(! calM.getExamInput()[i]){	//未入力なら
				//表示設定
				examField[i].setPromptText( (int)(examScoreAr[i])+"");
			}
		}
	}

	//テスト点のレイアウト削除
	void clearTestBox() {
		centerGrid.getChildren().clear();
	}

	void showScoreList(boolean isShow) {
		vBoxScore.setVisible(isShow);
		bottomBar.setVisible(isShow);
	}

	Button getButton(int id) { // コントローラー用
		for (int i = 0; i < button.length; i++) {
			if (button[i].getId().equals(id + "")) {
				return button[i];
			}
		}
		return null;

	}

	//目標点取得
	String getExamGoalScore() {
		return examGoalScore.getText();
	}

	TextField getExamTextFiled(int id) {
		for (int i = 0; i < examField.length; i++) {
			if (examField[i].getId().equals(id + "")) {
				return examField[i];
			}
		}
		return null;
	}

	TextField[] getExamTextField() {
		return examField;
	}

	//平常点取得
	int[] getHeijou() {
		int[] heijou = new int[heijouSlider.length];
		for(int i=0; i<heijou.length; i++){
			heijou[i] = (int)heijouSlider[i].getValue();
		}
		return heijou;
	}

	// 参照modelの設定
	void bindModel(ExamCalM calM) {
		this.calM = calM;
	}

}