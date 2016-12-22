import java.util.ArrayList;

public class ExamCalM {
	private double[] examInput; // 入力した値
	private boolean[] isExamInput; // 入力されてるか
	private double[] heijou;
	private String goalScore; // 目標点

	private String fileHost = "data\\";
	private String fileViewPath; // 表示中のfilePath
	private String currentPath; // 選択中の正式filePath
	private ArrayList<String> fileAr;

	ExamCalM() {
		fileViewPath = fileHost;
	}

	void setInit(String goalScore, String[] examInput, String[] heijou) {
		setExamList(examInput);
		setGoalScore(goalScore);
	}

	// 入力値セット
	void setExamList(String[] examInput) {
		this.examInput = new double[examInput.length]; // 入力した値
		this.isExamInput = new boolean[examInput.length]; // 入力されてるか

		for (int i = 0; i < examInput.length; i++) {
			if (examInput[i] == null || examInput[i].equals("")) { // 未入力なら
				isExamInput[i] = false;
			} else { // 入力済み
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
	boolean[] getExamInput(){
		return isExamInput;
	}

	// 計算して結果をexamInputへ
	int cal() {
		String formula = getFormula(); // 式を取得
		String formulaTmp = Calculation.replaceHeijouScore(formula, heijou); // 平常点の置き換え
		String formulaTmp2;
		for (int i = 0; i < 1000; i++) {
			formulaTmp2 = Calculation.replaceExamScore(formulaTmp, examInput,
					isExamInput, i); // 入力値置き換え
			if (Double.parseDouble(goalScore) < Calculation.calculation(formulaTmp2)) {
				return i;
			}
		}
		return 1000;

	}
}
