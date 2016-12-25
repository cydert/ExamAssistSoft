
import javafx.application.Application;
import javafx.stage.Stage;

public class ExamAssistMain extends Application {
	private ExamAssistMainV view;

	@Override
	public void start(Stage primaryStage) {
		// �����ݒ�
		primaryStage.setWidth(800);
		primaryStage.setHeight(400);
		primaryStage.setTitle("����e�X�g�x���\�t�g");
		primaryStage.show();//window�\��
		PublicView.bindStage(primaryStage);

		view = new ExamAssistMainV(primaryStage);//���C���\��

		//�{�^������
		view.getButton(0).setOnAction(e -> toMypage());
		view.getButton(1).setOnAction(e -> new ExamCalC(primaryStage));	//�v�Z��ʊǗ���

	}

	public void toMypage(){
		PublicView.showAlert("������");
	}
	public static void main(String[] args) {
		launch(args);
	}
}
