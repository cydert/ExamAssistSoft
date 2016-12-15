public class ExamCalM {
	private double[] examInput; // 入力した値
	private boolean[] isExamInput; // 入力されてるか
	private String formula;	//式
	private String goalScore;	//目標点

	void setExamList(String[] examInput) {
		this.examInput = new double[examInput.length];		//入力した値
		this.isExamInput = new boolean[examInput.length];	//入力されてるか
		
		for (int i = 0; i < examInput.length; i++) {
			if (examInput[i] == null || examInput[i].equals("")) {	//未入力なら
				isExamInput[i] = false;
			} else {			//入力済み
				isExamInput[i] = true;
				this.examInput[i] = Double.parseDouble(examInput[i]);
			}
		}
	}
	void setInit(String formula, String goalScore, String[] examInput){
		setExamList(examInput);
		setFormula(formula);
		setGoalScore(goalScore);
	}
	
	void setFormula(String formula){
		this.formula = formula;
	}
	
	void setGoalScore(String goalScore){
		this.goalScore = goalScore;
	}
	
	//式、値を利用して計算
	void cal(){
		
	}
	
	int getNotInput(){
		int cnt = 0;
		for(int i=0; i<isExamInput.length; i++){
			cnt ++;
		}
		return cnt;
	}
	
	
	

}
