import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ExamCalC {
	ExamCalV calV; // �v�Z���
	ExamCalFileV calFileV; // �t�@�C���Ǘ����
	ExamCalM calM;

	ExamCalC(Stage stage) {
		calV = new ExamCalV(stage);
		calM = new ExamCalM();

		calV.bindModel(calM);

		// �{�^�����̓���
		calV.getButton(0).setOnAction(e -> selectTemplate());// �e���v���{�^��
		calV.getButton(1).setOnAction(e -> editFx()); // ���ҏW�{�^��
		calV.getButton(2).setOnAction(e -> save()); // �ۑ��{�^��
		calV.getButton(3).setOnAction(e -> cal()); // �v�Z�{�^��
		calV.getButton(4).setOnAction(e -> showFormula()); // �\�����̎���\��
		calV.getButton(5).setOnAction(e -> backKey());

	}

	// �e���v���t�@�C���I�����
	void selectTemplate() {
		calM.clearFileViewPath();
		calFileV = new ExamCalFileV(); // ��ʕ\��
		calFileV.bindModel(calM);
		calFileV.changeFileList();// �t�@�C���ꗗ�\��
		calFileV.getListView().setOnMouseClicked(e -> selectFile(e));
	}

	void editFx() {
		PublicView.showAlert("�������ł�");
	}

	void save() {
		PublicView.showAlert("�������ł�");
	}

	// �v�Z�{�^����������
	void cal() {
		if (calV.getExamGoalScore().equals("")) { // �ڕW�_������
			PublicView.showAlert("�ڕW�_����͂��Ă�������");
		} else {
			// ����Box�擾
			String[] examList = new String[calV.getExamTextField().length]; // �e�X�g���͗�
			for (int i = 0; i < examList.length; i++) {
				examList[i] = calV.getExamTextFiled(i).getText(); // �擾
			}

			// ���A�ڕW�_�A���͂����l��n��
			calM.setInit(calV.getExamGoalScore(), examList, calV.getHeijou());

			// �v�Z��̒l�����炷
			int ans = calM.cal();
			System.out.println(ans);
			for (int i = 0; i < examList.length; i++) {
				if (!calM.getExamInput()[i])// ���Ƃ��Ɠ��͂���ĂȂ��Ȃ�
					examList[i] = ans + ""; // �l���Z�b�g
			}
			calM.setExamList(examList, false);
			calV.setNewScoewInBox();

		}
	}

	// �t�@�C���I�����ꂽ��
	void selectFile(MouseEvent e) {
		// �_�u���N���b�N���ꂽ��
		boolean doubleClick = e.getButton().equals(MouseButton.PRIMARY) && (e.getClickCount() == 2);
		if (doubleClick) {
			calV.clearTestBox(); // testBox ������
			String selectSt = calFileV.getListView().getSelectionModel().getSelectedItem();// �I�����ꂽ����
			calM.setFileName(selectSt);
			// FilePath�𐳎����̂œo�^
			calFileV.getStage().hide(); // Window����

			calV.showScoreList(true);// ���͗��ȂǕ\��
			calV.setTestBox(); // ���͗��Ȃǂ̍쐬�A�\��

		}
	}

	void showFormula() {
		PublicView.showInfoAlert(calM.getFormula());
	}

	void backKey(){
		PublicView.reShow(PublicView.sceneStack.pop());
	}
}
