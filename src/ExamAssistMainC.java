import javafx.stage.Stage;

public class ExamAssistMainC {
	private ExamAssistMainV view;
	private Stage stage;

	public ExamAssistMainC(Stage stage){
		this.stage = stage;

		view = new ExamAssistMainV(stage);//表示

		//ボタン動作
		view.getButton(0).setOnAction(e -> toMypage());
		view.getButton(1).setOnAction(e -> toCalView());
	}

	public void toMypage(){
		PublicView.showAlert("未実装");
	}
	public void toCalView(){
		new ExamCalC(stage);
	}
}
