
import javafx.application.Application;
import javafx.stage.Stage;

public class ExamAssistMain extends Application {
	private ExamAssistMainV view;

	@Override
	public void start(Stage primaryStage) {
		// 初期設定
		primaryStage.setWidth(800);
		primaryStage.setHeight(400);
		primaryStage.setTitle("定期テスト支援ソフト");
		primaryStage.show();//window表示
		PublicView.bindStage(primaryStage);

		view = new ExamAssistMainV(primaryStage);//メイン表示

		//ボタン動作
		view.getButton(0).setOnAction(e -> toMypage());
		view.getButton(1).setOnAction(e -> new ExamCalC(primaryStage));	//計算画面管理へ

	}

	public void toMypage(){
		PublicView.showAlert("未実装");
	}
	public static void main(String[] args) {
		launch(args);
	}
}
