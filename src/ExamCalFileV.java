import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ExamCalFileV {
	private ExamCalM calM;
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

		stage.setScene(new Scene(root));//表示
	}

	//表示内容変更
	void changeFileList(){
		String[] fileList = calM.getFileList();
		ObservableList<String> list = FXCollections.observableArrayList(fileList);//一覧内容
		listView.setItems(list);
	}

	ListView<String> getListView(){
		return listView;
	}

	void bindModel(ExamCalM calM){
		this.calM = calM;
	}


}
