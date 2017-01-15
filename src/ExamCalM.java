import java.util.ArrayList;

public class ExamCalM {
	private double[] examInput; // ���͂����l
	private boolean[] isExamInput; // ���͂���Ă邩
	private int[] heijou;
	private String goalScore; // �ڕW�_

	private String fileHost = "data\\";
	private String fileViewPath; // �ꗗ�\������filePath
	private String currentPath; // �I�𒆂̐���filePath
	private String fileName;	//�I�𒆂�fileName

	private String editFormula;

	ExamCalM() {
		fileViewPath = fileHost;
	}

	void setInit(String goalScore, String[] examInput, int[] heijou) {
		setExamList(examInput,true);
		setGoalScore(goalScore);
		setHeijou(heijou);
	}

	// ���͒l�Z�b�g
	void setExamList(String[] examInput,boolean clearInputFlag) {
		this.examInput = new double[examInput.length]; // ���͂����l
		if(isExamInput == null || clearInputFlag)	//���̓`�F�b�N�ꗗ
			this.isExamInput = new boolean[examInput.length];

		for (int i = 0; i < examInput.length; i++) {
			if (examInput[i] == null || examInput[i].equals("")) { // ������
				if(clearInputFlag)	isExamInput[i] = false;	//���͗��`�F�b�N
			} else { // ���͍ς�
				if(clearInputFlag)	isExamInput[i] = true;
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

	double[] getExamScoreAr(){
		return examInput;
	}

	void clearFileViewPath() {
		fileViewPath = fileHost;
	}

	ArrayList<String> getFileNameAr() {
		return FileRead.getFileNameAr(fileViewPath);
	}

	String getFileName(){
		return fileName;
	}
	String[] getExamInfoList() {
		return FileRead.readTestInfo(currentPath);
	}

	String[] getHeijouInfoList(){
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
		String formulaTmp2;
		for (int i = 0; i < 1000; i++) {
			formulaTmp2 = Calculation.replaceExamScore(formulaTmp, examInput, isExamInput, i); // ���͒l�u������
			if (Double.parseDouble(goalScore) <= Calculation.calculation(formulaTmp2)) {
				//�v�Z���ʂ��ڕW�_�ȏ�Ȃ��������l��Ԃ�
				return i;
			}
		}
		return 1000;

	}

	void makeTxFormula(){

	}
}
