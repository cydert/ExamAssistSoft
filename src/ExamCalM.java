import java.util.ArrayList;

public class ExamCalM {
	private double[] examInput; // 入力した値
	private boolean[] isExamInput; // 入力されてるか
	private int[] heijou;
	private String goalScore; // 目標点

	private String fileHost = "data\\";
	private String fileViewPath; // 一覧表示中のfilePath
	private String currentPath; // 選択中の正式filePath
	private String fileName;	//選択中のfileName

	private String editFormula;

	ExamCalM() {
		fileViewPath = fileHost;
	}

	void setInit(String goalScore, String[] examInput, int[] heijou) {
		setExamList(examInput,true);
		setGoalScore(goalScore);
		setHeijou(heijou);
	}

	// 入力値セット
	void setExamList(String[] examInput,boolean clearInputFlag) {
		this.examInput = new double[examInput.length]; // 入力した値
		if(isExamInput == null || clearInputFlag)	//入力チェック一覧
			this.isExamInput = new boolean[examInput.length];

		for (int i = 0; i < examInput.length; i++) {
			if (examInput[i] == null || examInput[i].equals("")) { // 未入力
				if(clearInputFlag)	isExamInput[i] = false;	//入力欄チェック
			} else { // 入力済み
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

	// 計算して結果をexamInputへ
	int cal() {
		String formula = getFormula(); // 式を取得
		String formulaTmp = Calculation.replaceHeijouScore(formula, heijou); // 平常点の置き換え
		String formulaTmp2;
		for (int i = 0; i < 1000; i++) {
			formulaTmp2 = Calculation.replaceExamScore(formulaTmp, examInput, isExamInput, i); // 入力値置き換え
			if (Double.parseDouble(goalScore) <= Calculation.calculation(formulaTmp2)) {
				//計算結果が目標点以上なら代入した値を返す
				return i;
			}
		}
		return 1000;

	}

	void makeTxFormula(){

	}
}
