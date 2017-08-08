import java.util.ArrayList;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class ExamCalM {
	private double[] examInput; // ���͂����l
	private boolean[] isExamInput; // ���͂���Ă邩
	private int[] heijou;
	private String goalScore; // �ڕW�_

	private String fileHost = "data\\";
	private String fileViewPath; // �ꗗ�\������filePath
	private String currentPath; // �I�𒆂̐���filePath
	private String fileName; // �I�𒆂�fileName

	private String editFormula;

	ExamCalM() {
		fileViewPath = fileHost;
	}

	void setInit(String goalScore, String[] examInput, int[] heijou) {
		setExamList(examInput, true);
		setGoalScore(goalScore);
		setHeijou(heijou);
	}

	// ���͒l�Z�b�g
	void setExamList(String[] examInput, boolean clearInputFlag) {
		this.examInput = new double[examInput.length]; // ���͂����l
		if (isExamInput == null || clearInputFlag) // ���̓`�F�b�N�ꗗ
			this.isExamInput = new boolean[examInput.length];

		for (int i = 0; i < examInput.length; i++) {
			if (examInput[i] == null || examInput[i].equals("")) { // ������
				if (clearInputFlag)
					isExamInput[i] = false; // ���͗��`�F�b�N
			} else { // ���͍ς�
				if (clearInputFlag)
					isExamInput[i] = true;
				this.examInput[i] = Double.parseDouble(examInput[i]);
			}
		}
	}

	void setGoalScore(String goalScore) {
		this.goalScore = goalScore;
	}

	void setHeijou(int[] heijou) {
		this.heijou = heijou;
	}

	void setFileViewPath(String fileViewPath) {
		this.fileViewPath += fileViewPath;
	}

	void setFileName(String fileName) {
		currentPath = fileViewPath + fileName + ".txt";
		this.fileName = fileName;
	}

	String getFilePath() {
		return currentPath;
	}

	double[] getExamScoreAr() {
		return examInput;
	}

	void clearFileViewPath() {
		fileViewPath = fileHost;
	}

	ArrayList<String> getFileNameAr() {
		return FileRead.getFileNameAr(fileViewPath);
	}

	String getFileName() {
		return fileName;
	}
	String getFileHost(){
		return fileHost;
	}

	String[] getExamInfoList() {
		return FileRead.readTestInfo(currentPath);
	}

	String[] getHeijouInfoList() {
		return FileRead.readHeijou(currentPath);
	}

	String getFormula() {
		return FileRead.readFormula(currentPath);
	}

	boolean[] getExamInput() {
		return isExamInput;
	}

	// �v�Z���Č��ʂ�examInput��
	int cal() {
		String formula = getFormula(); // �����擾
		String formulaTmp = Calculation.replaceHeijouScore(formula, heijou); // ����_�̒u������
		/*
		String formulaTmp2;
		for (int i = 0; i < 1000; i++) {
			formulaTmp2 = Calculation.replaceExamScore(formulaTmp, examInput, isExamInput, i); // ���͒l�u������
			if (Double.parseDouble(goalScore) <= Calculation.calculation(formulaTmp2)) {
				// �v�Z���ʂ��ڕW�_�ȏ�Ȃ��������l��Ԃ�
				return i;
			}
		}*/
		double goal = Double.parseDouble(goalScore);
        int l = 0, c = 0, r = 100;
        double sco;
        boolean f = true;
        while (r - l > 2) {
            c = (r + l) / 2;
            sco = Calculation.calculation(Calculation.replaceExamScore(formulaTmp, examInput, isExamInput, c));
            if (goal < sco) {//���炷
                r = c + 1;
                f = false;
            } else if (f) {//(�����)���₷
                l = c;
                r *= 2;
            } else {//���₷
                l = c;
            }
        }
        double sc = 0;
        for (sc = c - 1; sc < c + 2; sc += 0.1) {
            if (goal <= Calculation.calculation(Calculation.replaceExamScore(formulaTmp, examInput, isExamInput, c))) {
                break;
            }
        }

		return (int) ((sc*100) / 100.0);

	}

	ArrayList<String> makeTxFormula(HBox formulaBox) {
		String tmp = "";
		int exam = 0;
		int heijou = 0;
		ArrayList<String> text = new ArrayList<>();
		text.add("");
		for (int i = 0; i < formulaBox.getChildren().size(); i++) {
			try {
				tmp = ((TextField) (formulaBox.getChildren().get(i))).getText();
				text.set(0, text.get(0) + tmp);
			} catch (ClassCastException e) {
				try {
					// �e�X�g�_
					tmp = ((ExamButton) (formulaBox.getChildren().get(i))).getText();
					text.set(0, text.get(0) + "T[" + exam + "]");
					text.add("T[" + exam + "]:" + tmp);
					exam++;
				} catch (ClassCastException e2) {
					tmp = ((HeijouButton) (formulaBox.getChildren().get(i))).getText();
					text.set(0, text.get(0) + "H[" + heijou + "]");
					text.add("H[" + exam + "]:" + tmp + "," + ((HeijouButton) (formulaBox.getChildren().get(i))).max);
				}
			}
		}
		return text;
	}
}
