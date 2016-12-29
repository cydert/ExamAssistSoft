import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ExamCalV {
	ExamCalM calM;
	private Button[] button;
	private BorderPane root;
	private BorderPane topBar;
	private AnchorPane bottomBar;
	private GridPane centerGrid;

	private NumTextField[] examField; // exam���͂����l
	private Slider[] heijouSlider;
	private TextField examGoalScore; // �ڕW�_

	private MenuBar menubar;

	ExamCalV(Stage stage) {
		root = new BorderPane();
		root.setOnMouseClicked(e -> root.requestFocus()); // �t�H�[�J�X��textField����O���悤��

		// �{�^��
		button = new Button[6];
		button[0] = new Button("�e���v���[�g");
		button[1] = new Button("���̕ҏW");
		button[2] = new Button("�ۑ�");
		button[3] = new Button("�v�Z");
		button[4] = new Button("�Ȗږ�");
		button[5] = new Button("��");
		// �{�^���̋��ʏ����ݒ�
		for (int i = 0; i < button.length; i++) {
			button[i].setId(i + "");
		}

		// ��̃{�^��
		HBox topLeft = new HBox(), topCenter = new HBox(), topRight = new HBox();
		topCenter.setAlignment(Pos.CENTER);
		topBar = new BorderPane();
		topLeft.getChildren().addAll(button[5], button[0]);
		topCenter.getChildren().addAll(button[4]);
		topRight.getChildren().addAll(button[1]);
		topBar.setLeft(topLeft);
		topBar.setCenter(topCenter);
		topBar.setRight(topRight);

		// �����̃��C�A�E�g
		centerGrid = new GridPane();
		centerGrid.setAlignment(Pos.CENTER);
		centerGrid.setHgap(10);// ���
		centerGrid.setVgap(10);// �s��

		// ���̃{�^��
		AnchorPane.setLeftAnchor(button[2], 10.0);
		AnchorPane.setRightAnchor(button[3], 10.0);
		bottomBar = new AnchorPane();
		bottomBar.getChildren().addAll(button[2], button[3]);

		// �\��
		root.setTop(topBar);
		root.setCenter(centerGrid);
		root.setBottom(bottomBar);
		showScoreList(false);
		PublicView.sceneStack.push(stage.getScene());
		stage.setScene(new Scene(root));

	}

	/* ����BOX�̕\�� */
	void setTestBox() {
		int index = 0; // �ǉ����ꂽ�s��
		String[] examList = calM.getExamInfoList(); // �e�X�g���擾 ��:�O������
		examField = new NumTextField[examList.length]; // ����box�̔z�� �e�X�g�_�p
		for (int i = 0; i < examList.length; i++) {
			centerGrid.add(new Label(examList[i].split(",")[0]), 0, i);
			examField[i] = new NumTextField();
			examField[i].setId(i + "");
			centerGrid.add(examField[i], 1, i);
			index++;
		}

		index++;// 1�s�J����
		// ����_��Slider�ݒu
		String[] heijouList = calM.getHeijouInfoList();
		heijouSlider = new Slider[heijouList.length];
		for (int i = 0; i < heijouList.length; i++) {
			int max = Integer.parseInt(heijouList[i].split(",")[1]);
			String name = heijouList[i].split(",")[0];
			heijouSlider[i] = new Slider(0, max, 100);
			centerGrid.add(new Label(name), 0, index);
			centerGrid.add(heijouSlider[i], 1, index);
			index++;
		}

		// �ڕW�_Box�̐���,�\��
		examGoalScore = new TextField();
		centerGrid.add(new Label("�ڕW�]���_"), 0, index + 2);
		centerGrid.add(examGoalScore, 1, index + 2);

		// ���Ȗ���\��
		button[4].setText(calM.getFileName());
	}

	/* �����͏ꏊ�Ɍv�Z���ʂ�\�� */
	void setNewScoewInBox() {
		double[] examScoreAr = calM.getExamScoreAr();
		for (int i = 0; i < examScoreAr.length; i++) {
			if (!calM.getExamInput()[i]) { // �����͂Ȃ�
				// �\���ݒ�
				examField[i].setPromptText((int) (examScoreAr[i]) + "");
			}
		}
	}

	/* �e�X�g�_�̃��C�A�E�g�폜 */
	void clearTestBox() {
		centerGrid.getChildren().clear();
	}

	void showScoreList(boolean isShow) {
		centerGrid.setVisible(isShow);
		bottomBar.setVisible(isShow);
		button[4].setVisible(isShow);
	}

	/* �R���g���[���[�p �{�^���̃\�[�X��Ԃ� */
	Button getButton(int id) {
		for (int i = 0; i < button.length; i++) {
			if (button[i].getId().equals(id + "")) {
				return button[i];
			}
		}
		return null;

	}

	/* ���͂��ꂽ�ڕW�_�擾 */
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

	// ����_�擾
	int[] getHeijou() {
		int[] heijou = new int[heijouSlider.length];
		for (int i = 0; i < heijou.length; i++) {
			heijou[i] = (int) heijouSlider[i].getValue();
		}
		return heijou;
	}

	// �Q��model�̐ݒ�
	void bindModel(ExamCalM calM) {
		this.calM = calM;
	}

}

//������pTextField
class NumTextField extends TextField {
	@Override
	public void replaceText(final int start, final int end, final String text) {
		if (validate(text)) {
			super.replaceText(start, end, text);
		}
	}

	@Override
	public void replaceSelection(final String text) {
		if (validate(text)) {
			super.replaceSelection(text);
		}
	}

	private boolean validate(final String text) {
		return text.matches("[0-9]*");
	}
}
