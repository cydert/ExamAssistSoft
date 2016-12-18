
public class Calculation {

	// 入力点置き換え
	static String replaceExamScore(String formula, double[] examInput, boolean[] isExamInput) {
		System.out.println("tmp0:" + formula);
		for (int i = 0; i < examInput.length; i++) {
			if (isExamInput[i]) {
				formula = changeSt(formula, 'T', examInput[i], i);
			}else{
				formula = changeSt(formula, 'T', 50, i);	//TODO 変動置き換え
			}
		}
		// editFormula = changeSt(editFormula,'T',5);
		return formula;
	}

	// 平常点置き換え
	static String replaceHeijouScore(String formula, double[] input) {
		for (int i = 0; i < input.length; i++) {
			formula = changeSt(formula, 'H', input[i]);
		}
		return formula;
	}

	private static String changeSt(String formula, char oldChar, double newD) {
		String tmp;
		int index0 = formula.indexOf(oldChar);
		tmp = formula.substring(0, index0);

		int index1 = formula.indexOf("]");
		tmp = tmp + newD + formula.substring(index1 + 1);
		System.out.println("tmp" + tmp);
		return tmp;
	}

	private static String changeSt(String formula, char oldChar, double newD, int id) {
		String tmp;
		String changeSt = oldChar + "[" + id + "]";
		int index0 = formula.indexOf(changeSt);
		tmp = formula.substring(0, index0);

		int index1 = index0 + changeSt.length();
		tmp = tmp + newD + formula.substring(index1);
		System.out.println("tmp2:" + tmp);
		return tmp;
	}
}
