import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Calculation {

	// 入力点置き換え
	static String replaceExamScore(String formula, double[] examInput, boolean[] isExamInput, double newData) {
		for (int i = 0; i < examInput.length; i++) {
			if (isExamInput[i]) {
				formula = changeSt(formula, 'T', examInput[i], i);
			} else {
				formula = changeSt(formula, 'T', newData, i);
			}
		}
		return formula;
	}

	// 平常点置き換え
	static String replaceHeijouScore(String formula, int[] input) {
		if (input == null)
			return formula;

		for (int i = 0; i < input.length; i++) {
			formula = changeSt(formula, 'H', input[i],i);
		}
		return formula;
	}

	static double calculation(String formula) {
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("JavaScript");
		System.out.println("式:"+formula);
		try {
			double result = (Double) engine.eval(formula);
			return result;
		} catch (ScriptException e) {
			e.printStackTrace();
			return -1; // 異常終了
		}

	}

	// 文字の出現回数
	private static int inChar(String text, char check) {
		int cnt = 0;
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == '(')
				cnt++;
		}
		return cnt;
	}

	// 括弧の中身を取り出す
	private static String getFirstSt(String formula) {
		int end = formula.indexOf(")");
		String tmp = formula.substring(0, end);
		int first = tmp.lastIndexOf("(");
		tmp = tmp.substring(first);
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

	static String changeSt(String formula, char oldChar, String newD) {
		String tmp;
		int index0 = formula.indexOf(oldChar);
		tmp = formula.substring(0, index0);

		int index1 = formula.indexOf("]");
		tmp = tmp + newD + formula.substring(index1 + 1);
		System.out.println("tmp" + tmp);
		return tmp;
	}
}
