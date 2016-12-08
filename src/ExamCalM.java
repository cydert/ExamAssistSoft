public class ExamCalM {
	private double[] examInput; // 入力した値
	private boolean[] isExamInput; // 入力されてるか
	private String formula;	//式
	private String goalScore;

	void setExamList(String[] examInput) {
		this.examInput = new double[examInput.length];
		this.isExamInput = new boolean[examInput.length];
		
		for (int i = 0; i < examInput.length; i++) {
			if (examInput[i] == null || examInput[i].equals("")) {	//未入力
				isExamInput[i] = false;
			} else {			//入力済み
				isExamInput[i] = true;
				this.examInput[i] = Double.parseDouble(examInput[i]);
			}
		}
	}
	
	void setFormula(String formula){
		this.formula = formula;
	}
	
	void setGoalScore(String goalScore){
		this.goalScore = goalScore;
	}
	
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
