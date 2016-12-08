public class ExamCalM {
	private double[] examInput; // 入力した値
	private boolean[] isExamInput; // 入力されてるか

	void setExamList(String[] examInput) {
		
		
		this.examInput = new double[examInput.length];
		this.isExamInput = new boolean[examInput.length];

		for (int i = 0; i < examInput.length; i++) {
			if (examInput[i] == null || examInput[i].equals("")) {
				isExamInput[i] = false;
			} else {
				isExamInput[i] = true;
				this.examInput[i] = Double.parseDouble(examInput[i]);
			}
		}
		
	}

}
