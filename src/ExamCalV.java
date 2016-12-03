import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ExamCalV {
	ExamCalM calM;
	private Button[] button;
	private BorderPane root;
	private VBox vBoxScore;
	private AnchorPane topBar;
	private AnchorPane bottomBar;

	ExamCalV(Stage stage){
		root = new BorderPane();

		//ボタン
		button = new Button[4];
		button[0] = new Button("テンプレート");
		button[1] = new Button("式の編集");
		button[2] = new Button("保存");
		button[3] = new Button("計算");
		//ボタンの共通初期設定
		for(int i=0; i<button.length; i++){
			button[i].setId(i + "");
		}

		//上のボタン
		AnchorPane.setLeftAnchor(button[0], 10.0);
		AnchorPane.setRightAnchor(button[1], 10.0);
		topBar = new AnchorPane();
		topBar.getChildren().addAll(button[0],button[1]);

		//中央のレイアウト
		vBoxScore = new VBox();

		//下のボタン
		AnchorPane.setLeftAnchor(button[2], 10.0);
		AnchorPane.setRightAnchor(button[3], 10.0);
		bottomBar = new AnchorPane();
		bottomBar.getChildren().addAll(button[2],button[3]);


		//表示
		root.setTop(topBar);
		root.setCenter(vBoxScore);
		root.setBottom(bottomBar);
		showScoreList(false);
		stage.setScene(new Scene(root));
	}
	void showScoreList(boolean isShow){
			vBoxScore.setVisible(isShow);
			bottomBar.setVisible(isShow);
	}
	void showFileList(){
		String[] fileList = calM.getFileList();

		Stage fileStage = new Stage();
		fileStage.setWidth(700);
		fileStage.setHeight(600);
		fileStage.setTitle("ファイルを選択");
		fileStage.initModality(Modality.APPLICATION_MODAL);//他画面選択不可
		fileStage.show();

		VBox root = new VBox();
		ListView<String> listView = new ListView<>();//リストビュー
		listView.setEditable(false);					//編集不可
		listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);	//単体のみ選択可

		ObservableList<String> list = FXCollections.observableArrayList(fileList);//一覧内容
		listView.setItems(list);


		fileStage.setScene(new Scene(root));
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
