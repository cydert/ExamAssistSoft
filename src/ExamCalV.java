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

	private TextField[] examField; // exam���͂����l
	private Slider[] heijouSlider;
	private TextField examGoalScore; // �ڕW�_

	ExamCalV(Stage stage) {
		root = new BorderPane();

		// �{�^��
		button = new Button[4];
		button[0] = new Button("�e���v���[�g");
		button[1] = new Button("���̕ҏW");
		button[2] = new Button("�ۑ�");
		button[3] = new Button("�v�Z");
		// �{�^���̋��ʏ����ݒ�
		for (int i = 0; i < button.length; i++) {
			button[i].setId(i + "");
		}

		// ��̃{�^��
		AnchorPane.setLeftAnchor(button[0], 10.0);
		AnchorPane.setRightAnchor(button[1], 10.0);
		topBar = new AnchorPane();
		topBar.getChildren().addAll(button[0], button[1]);

		// �����̃��C�A�E�g
		vBoxScore = new VBox();
		centerGrid = new GridPane();
		centerGrid.setAlignment(Pos.CENTER);
		centerGrid.setHgap(10);// ���
		centerGrid.setVgap(10);// �s��
		vBoxScore.getChildren().add(centerGrid);

		// ���̃{�^��
		AnchorPane.setLeftAnchor(button[2], 10.0);
		AnchorPane.setRightAnchor(button[3], 10.0);
		bottomBar = new AnchorPane();
		bottomBar.getChildren().addAll(button[2], button[3]);

		// �\��
		root.setTop(topBar);
		root.setCenter(vBoxScore);
		root.setBottom(bottomBar);
		showScoreList(false);
		stage.setScene(new Scene(root));
	}

	//����BOX�̕\��
	void setTestBox() {
		int index=0;	//�ǉ����ꂽ�s��
		String[] examList = calM.getExamInfoList(); // �e�X�g���擾 ��:�O������
		examField = new TextField[examList.length]; // ����box�̔z�� �e�X�g�_�p
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


		//�ڕW�_Box
		examGoalScore = new TextField();
		centerGrid.add(new Label("�ڕW�_"), 0, index + 2);
		centerGrid.add(examGoalScore, 1, index + 2);

	}

	//�����͏ꏊ�Ɍv�Z���ʂ�\��
	void setNewScoewInBox(){
		double[] examScoreAr = calM.getExamScoreAr();
		for(int i=0; i<examScoreAr.length; i++){
			if(! calM.getExamInput()[i]){	//�����͂Ȃ�
				//�\���ݒ�
				examField[i].setPromptText( (int)(examScoreAr[i])+"");
			}
		}
	}

	//�e�X�g�_�̃��C�A�E�g�폜
	void clearTestBox() {
		centerGrid.getChildren().clear();
	}

	void showScoreList(boolean isShow) {
		vBoxScore.setVisible(isShow);
		bottomBar.setVisible(isShow);
	}

	Button getButton(int id) { // �R���g���[���[�p
		for (int i = 0; i < button.length; i++) {
			if (button[i].getId().equals(id + "")) {
				return button[i];
			}
		}
		return null;

	}

	//�ڕW�_�擾
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

	//����_�擾
	int[] getHeijou() {
		int[] heijou = new int[heijouSlider.length];
		for(int i=0; i<heijou.length; i++){
			heijou[i] = (int)heijouSlider[i].getValue();
		}
		return heijou;
	}

	// �Q��model�̐ݒ�
	void bindModel(ExamCalM calM) {
		this.calM = calM;
	}

}