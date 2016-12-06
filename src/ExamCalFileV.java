import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ExamCalFileV {
	private ExamCalFileM calFileM;
	private Stage stage;
	private ListView<String> listView;

	ExamCalFileV(){
		//window初期設定
		stage = new Stage();
		stage.setWidth(700);
		stage.setHeight(600);
		stage.setTitle("ファイルを選択");
		stage.initModality(Modality.APPLICATION_MODAL);//他画面選択不可
		stage.show();

		VBox root = new VBox();

		listView = new ListView<>();//リストビュー
		listView.setEditable(false);					//編集不可
		listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);	//単体のみ選択可

		root.getChildren().addAll(listView);
		stage.setScene(new Scene(root));//表示
	}

	//(ボタン押された時など)Modelのファイルリストを見て表示内容変更
	void changeFileList(){
		ArrayList<String> fileList = calFileM.getFileNameAr();
		ObservableList<String> list = FXCollections.observableArrayList(fileList);//一覧内容
		listView.setItems(list);

		//要dir版
	}

	ListView<String> getListView(){
		return listView;
	}

	void bindModel(ExamCalFileM calFileM){
		this.calFileM = calFileM;
	}
	Stage getStage(){
		return stage;
	}


}
