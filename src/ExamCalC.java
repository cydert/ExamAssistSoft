import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ExamCalC {
	Stage stage;
	ExamCalV calV; // �v�Z���
	ExamCalFileV calFileV; // �t�@�C���Ǘ����
	ExamCalM calM;

	ExamCalC(Stage stage) {
		this.stage = stage;
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
		calV.stackScene();
		ExamCalEditV exV = new ExamCalEditV(stage);
<<<<<<< HEAD

=======
		//exV.getFormula().get(0).setOnMouseClicked(e -> ExamCalEditV.caletI = exV.getFormula().getCaretPosition()); TODO
>>>>>>> 6e37d9d7a89acc0edd198e96548cc771b43afd3b
		for (int i = 0; i < exV.getMathBt().length; i++) {
			String input = exV.getMathBt()[i].getText();
			exV.getMathBt()[i].setOnAction(e -> exV.addFormula(input)); // ���͂������̂����̂܂ܕ\��
		}
		for (int i = 0; i < exV.getCalB().length; i++) {
			String input = exV.getCalB()[i].getText(); // �L�����b�g�ړ������Z�q�{�^��
			exV.getCalB()[i].setOnAction(e -> {
				if (input.equals("��")) {
					//ExamCalEditV.caletI--;
<<<<<<< HEAD
					exV.leftCaret();
				} else if (input.equals("��")) {
					//ExamCalEditV.caletI++;
					exV.rightCaret();
=======
					exV.showCallet();
				} else if (input.equals("��")) {
					//ExamCalEditV.caletI++;
					exV.showCallet();
>>>>>>> 6e37d9d7a89acc0edd198e96548cc771b43afd3b
				} else if (input.equals("DEL")) {
					exV.delFormula();
				}else if(input.equals("C")){
					exV.clearFormula();
				}else if(input.equals("�e�X�g�_")){
					AddExamButtonConfig config = new AddExamButtonConfig();
					config.getConfirm().setOnAction(ev ->{	//�m��{�^����������
						exV.addTestBt(config.getText());	//�{�^���ǉ�
						config.close();
					});
				}else if(input.equals("����_")){
					AddHeijouButtonConfig config = new AddHeijouButtonConfig();
					config.getConfirm().setOnAction(ev ->{
						exV.addHeijouBt(config.getText(),config.getMax());
						config.close();
					});
				} else {
					exV.addFormula(input);// ���Z�q
				}
			});
		}
<<<<<<< HEAD
		exV.getSaveBt()[0].setOnAction(e -> {	//�e���v���[�g�Ƃ��ĕۑ�(�t�@�C����������)
=======
		exV.getSaveBt()[0].setOnAction(e -> {
>>>>>>> 6e37d9d7a89acc0edd198e96548cc771b43afd3b
			String fileName = exV.getFileName();
			if(fileName.equals(""))	PublicView.showAlert("���Ȗ�����͂��Ă�������");
			else{
				FileWrite.fileWrite(calM.getFileHost()+"\\"+fileName+".txt", calM.makeTxFormula(exV.getFormulaHBox()));
				stage.setScene(PublicView.sceneStack.pop());
			}
<<<<<<< HEAD
=======

>>>>>>> 6e37d9d7a89acc0edd198e96548cc771b43afd3b
		});
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

	void backKey() {
		PublicView.reShow(PublicView.sceneStack.pop());
	}
}
