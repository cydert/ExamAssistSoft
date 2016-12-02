
import javafx.application.Application;
import javafx.stage.Stage;

public class ExamAssistMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		// 初期設定
		primaryStage.setWidth(800);
		primaryStage.setHeight(400);
		primaryStage.setTitle("定期テスト支援ソフト");
		primaryStage.show();//window表示

		ExamAssistMainC mainC = new ExamAssistMainC(primaryStage);

	}

	public static void main(String[] args) {
		launch(args);
	}
}
