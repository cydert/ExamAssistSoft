import java.util.ArrayList;

public class ExamCalM {
	private double[] examInput; // ���͂����l
	private boolean[] isExamInput; // ���͂���Ă邩
	private double[] heijou;
	private String goalScore; // �ڕW�_

	private String fileHost = "data\\";
	private String fileViewPath; // �\������filePath
	private String currentPath; // �I�𒆂̐���filePath
	private ArrayList<String> fileAr;

	ExamCalM() {
		fileViewPath = fileHost;
	}

	void setInit(String goalScore, String[] examInput, String[] heijou) {
		setExamList(examInput);
		setGoalScore(goalScore);
	}

	// ���͒l�Z�b�g
	void setExamList(String[] examInput) {
		this.examInput = new double[examInput.length]; // ���͂����l
		this.isExamInput = new boolean[examInput.length]; // ���͂���Ă邩

		for (int i = 0; i < examInput.length; i++) {
			if (examInput[i] == null || examInput[i].equals("")) { // �����͂Ȃ�
				isExamInput[i] = false;
			} else { // ���͍ς�
				isExamInput[i] = true;
				this.examInput[i] = Double.parseDouble(examInput[i]);
			}
		}
	}

	void setGoalScore(String goalScore) {
		this.goalScore = goalScore;
	}

	void setHeijou(String[] heijou) {
		if (heijou == null) {
			this.heijou = null;
		} else {
			this.heijou = new double[heijou.length];
			for (int i = 0; i < heijou.length; i++) {
				this.heijou[i] = Double.parseDouble(heijou[i]);
			}
		}
	}

	void setFileViewPath(String fileViewPath) {
		this.fileViewPath += fileViewPath;
	}

	void setFileName(String fileName) {
		currentPath = fileViewPath + fileName;
	}

	String getFilePath() {
		return currentPath;
	}

	void clearFileViewPath() {
		fileViewPath = fileHost;
	}

	ArrayList<String> getFileNameAr() {
		return FileRead.getFileNameAr(fileViewPath);
	}

	String[] getExamList() {
		return FileRead.readTestInfo(currentPath);
	}

	String getFormula() {
		return FileRead.readFormula(currentPath);
	}

	// �v�Z���Č��ʂ�examInput��
	void cal() {
		String formula = getFormula(); // �����擾
		String formulaTmp = Calculation.replaceHeijouScore(formula, heijou); // ����_�̒u������
		String formulaTmp2;
		for (int i = 0; i < 100; i++) {
			formulaTmp2 = Calculation.replaceExamScore(formulaTmp, examInput,
					isExamInput, i); // ���͒l�u������
			if (Double.parseDouble(goalScore) < Calculation
					.calculation(formulaTmp2)) {
				System.out.println(i);
				return;
			}
		}
		// if (heijou != null)
		// Calculation.replaceHeijouScore(formula, heijou); // ����_�u������

	}
}
