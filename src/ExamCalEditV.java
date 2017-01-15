import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ExamCalEditV {
	final int BTSIZE = 60;
	static int caletI = 0; // キャレットの位置
	Stage stage;
	BorderPane root = new BorderPane();
	TextField txName = new TextField();
	FormulaTextField txFormula = new FormulaTextField();
	GridPane mathGrid = new GridPane();
	GridPane calGrid = new GridPane();
	Button[] mathB;

	Button[] calB;
	Button[] bt = new Button[2];

	ExamCalEditV(Stage stage) {
		this.stage = stage;
		// 数字キー
		mathB = new Button[11];
		for (int i = 9, x = 2, y = 0; i > 0; i--) {
			mathB[i] = new Button(i + "");
			mathB[i].setMinWidth(BTSIZE);
			mathB[i].setMinHeight(BTSIZE);
			mathGrid.add(mathB[i], x, y);
			x--;
			if (x == -1) {
				y++;
				x = 2;
			}
		}
		mathB[0] = new Button("0");
		mathB[0].setMinWidth(BTSIZE);
		mathB[0].setMinHeight(BTSIZE);
		mathGrid.add(mathB[0], 0, 3);
		mathB[10] = new Button(".");
		mathB[10].setMinWidth(BTSIZE);
		mathB[10].setMinHeight(BTSIZE);
		mathGrid.add(mathB[10], 1, 3);
		// 演算キー
		String[] calS = { "/", "*", "-", "+", "(", ")", "DEL", "C", "テスト点", "平常点" };
		calB = new Button[calS.length];
		for (int i = 0, x = 0, y = 0; i < calB.length; i++) {
			calB[i] = new Button(calS[i]);
			calB[i].setMinWidth(BTSIZE);
			calB[i].setMinHeight(BTSIZE);
			calGrid.add(calB[i], x, y);
			x++;
			if (x == 4) {
				y++;
				x = 0;
			}
		}

		VBox topV = new VBox();
		HBox topH = new HBox();
		topH.setAlignment(Pos.CENTER);
		Label lb = new Label("教科名:");
		topH.getChildren().addAll(lb, txName);
		topV.getChildren().addAll(topH, txFormula);
		root.setTop(topV);
		HBox centerH = new HBox();
		centerH.setAlignment(Pos.CENTER);
		centerH.setSpacing(30);
		centerH.getChildren().addAll(mathGrid, calGrid);
		root.setCenter(centerH);

		stage.setScene(new Scene(root));

	}

	Button[] getMathBt() {
		return mathB;
	}

	Button[] getCalB() {
		return calB;
	}

	TextField getFormula() {
		return txFormula;
	}

	void addFormula(String addTx) {
		txFormula.requestFocus();
		txFormula.selectPositionCaret(caletI);
		txFormula.replaceText(caletI, caletI, addTx);

	}

	void delFormula() {
		if(caletI > 0) caletI --;
		txFormula.requestFocus();
		txFormula.selectPositionCaret(caletI);
		if (txFormula.getText().equals(""))
			caletI = 0;
		else {
			txFormula.replaceText(caletI, caletI+1, "");
		}
	}
	void clearFormula(){
		txFormula.setText("");
		caletI =0;
		txFormula.requestFocus();
		txFormula.selectPositionCaret(caletI);
	}

	void showCallet() {
		if (caletI < 0)
			caletI = 0;
		else if (caletI > txFormula.getText().length())
			caletI = txFormula.getText().length();
		txFormula.requestFocus();
		txFormula.selectPositionCaret(caletI);
	}
}

// 式専用入力欄
class FormulaTextField extends TextField {
	@Override
	public void replaceText(final int start, final int end, final String text) {
		System.out.println(start + " " + end + " " + text);
		if (validate(text)) {
			super.replaceText(start, end, text);// 文字追加
			if (start < end && ExamCalEditV.caletI != -1)
				ExamCalEditV.caletI--;
			ExamCalEditV.caletI++;

			if (getCaretPosition() != 0)
				ExamCalEditV.caletI = getCaretPosition();
		} else if (cal(text)) { // 演算文字なら
			if (getCaretPosition() != 0)
				ExamCalEditV.caletI = getCaretPosition();
			if (start != 0 && super.getText(start - 1, end).matches("[/\\*\\-\\+\\.\\(]")) { // 演算子連続なら
				super.replaceText(start - 1, end, text);// 1文字置き換え
			} else {
				super.replaceText(start, end, text);// 文字追加
				ExamCalEditV.caletI++;
			}
		}
		this.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.RIGHT) {
				ExamCalEditV.caletI = getCaretPosition();
			}
			if (e.getCode() == KeyCode.LEFT) {
				ExamCalEditV.caletI = getCaretPosition();
			}
			if (e.getCode() == KeyCode.BACK_SPACE) {
				ExamCalEditV.caletI = getCaretPosition();
			}

		});

	}

	@Override
	public void replaceSelection(final String text) {
		if (validate(text) || cal(text)) {
			super.replaceSelection(text);
		}
	}

	private boolean validate(final String text) {
		return text.matches("\\d||\\(|\\)");
	}

	private boolean cal(final String text) {
		return text.matches("[/\\*\\-\\+\\.]");
	}
}
