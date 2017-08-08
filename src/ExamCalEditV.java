import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ExamCalEditV {
	final int BTSIZE = 60;
	static int caletI[] = { 0, 0 }; // �L�����b�g�̈ʒu

	Stage stage;
	BorderPane root = new BorderPane();
	TextField txName = new TextField();
	HBox formulaH = new HBox();

	ArrayList<FormulaTextField> txFormula = new ArrayList<>();
	GridPane mathGrid = new GridPane();
	GridPane calGrid = new GridPane();
	Button[] mathB;

	Button[] calB;
	Button[] bt = new Button[2];
	Button backBt;	//�߂�

	ExamCalEditV(Stage stage) {
		this.stage = stage;
		txFormula.add(new FormulaTextField(this));
		// �����L�[
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
		// ���Z�L�[
		String[] calS = { "/", "*", "-", "+", "(", ")", "DEL", "C", "��", "��", "�e�X�g�_", "����_" };
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
		backBt = new Button("�߂�");
		HBox topTop = new HBox(backBt);
		topTop.setAlignment(Pos.TOP_LEFT);
		
		VBox topV = new VBox();
		HBox topH = new HBox();
		topH.setAlignment(Pos.CENTER);
		Label lb = new Label("���Ȗ�:");
		topH.getChildren().addAll(lb, txName);
		formulaH.getChildren().add(txFormula.get(0));
		formulaH.setAlignment(Pos.CENTER);
		topV.getChildren().addAll(topTop,topH, formulaH);

		HBox centerH = new HBox();
		centerH.setAlignment(Pos.CENTER);
		centerH.setSpacing(30);
		centerH.getChildren().addAll(mathGrid, calGrid);

		HBox buttomH = new HBox();

		bt[0] = new Button("�e���v���[�g�Ƃ��ĕۑ�");
		bt[1] = new Button("�K�p");

		buttomH.getChildren().addAll(bt[0]);	//�K�p�{�^�������Ȃ�
		buttomH.setAlignment(Pos.CENTER_RIGHT);

		root.setTop(topV);
		root.setCenter(centerH);
		root.setBottom(buttomH);

		stage.setScene(new Scene(root));

	}
	
	Button getBack(){
		return backBt;
	}

	Button[] getMathBt() {
		return mathB;
	}

	Button[] getCalB() {
		return calB;
	}

	Button[] getSaveBt() {
		return bt;
	}

	ArrayList<FormulaTextField> getFormula() {
		return txFormula;
	}

	String getFileName() {
		return txName.getText();
	}

	HBox getFormulaHBox() {
		return formulaH;
	}

	void addFormula(String addTx) {
		txFormula.get(caletI[0]).requestFocus();
		txFormula.get(caletI[0]).positionCaret(caletI[1]);
		txFormula.get(caletI[0]).replaceText(caletI[1], caletI[1], addTx);

	}

	void delFormula() {// 1�����폜 or �{�^���폜
		if (txFormula.get(caletI[0]).getText().equals("")) {
			if (leftCaret()) {
				formulaNextBtDel();
				nextTextFDel();
			}
		} else {
			if (caletI[1] > 0) { // �����폜�\�Ȃ�
				txFormula.get(caletI[0]).replaceText(caletI[1] - 1, caletI[1], "");
				leftCaret();
			}
		}
	}

	void formulaNextBtDel() {
		Node node = searchButtonNext(formulaH);
		if (node != null) {
			System.out.println("not nil");
			formulaH.getChildren().remove(node);
		}
	}

	void nextTextFDel() {
		try {
			Node node = txFormula.get(caletI[0] + 1);
			formulaH.getChildren().remove(node);
			txFormula.remove(caletI[0] + 1);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("�����݂Ȃ�");
		}
	}

	// �����ׂč폜
	void clearFormula() {

		txFormula.clear();
		txFormula.add(new FormulaTextField(this));
		caletI[0] = 0;
		caletI[1] = 0;
		formulaH.getChildren().clear();
		formulaH.getChildren().add(txFormula.get(0));
		txFormula.get(caletI[0]).requestFocus();
		txFormula.get(caletI[0]).positionCaret(caletI[1]);
	}

	// �e�X�g�_�{�^���̒ǉ�
	void addTestBt(String name) {
		ExamButton testBt = new ExamButton(name);
		formulaH.getChildren().add(testBt);
		FormulaTextField txf = new FormulaTextField(this);
		txFormula.add(txf); // text�ꗗlist�ɒǉ�
		formulaH.getChildren().add(txf); // ���C�A�E�g�ǉ�
		rightCaret();
	}

	void addHeijouBt(String name, int max) {
		HeijouButton hBt = new HeijouButton(name); // ����_�{�^��
		hBt.max = max; // �ő�l�ݒ�
		formulaH.getChildren().add(hBt);
		FormulaTextField txf = new FormulaTextField(this);// textField
		txFormula.add(txf); // text�ꗗlist�ɒǉ�
		formulaH.getChildren().add(txf); // ���C�A�E�g�ǉ�
		rightCaret();// caret�E�ړ�
	}

	boolean rightCaret() {
		boolean moveTx = false;
		if (txFormula.get(caletI[0]).getText().length() > caletI[1]) { // ����text���ňړ��ł���Ȃ�
			caletI[1]++;
		} else { // �ʂ�text�Ɉړ��ł��邩
			if (txFormula.size() > caletI[0] + 1) {// �ړ�
				caletI[0]++;
				caletI[1] = 0;
				moveTx = true;
			}
		}
		txFormula.get(caletI[0]).requestFocus();
		txFormula.get(caletI[0]).positionCaret(caletI[1]);
		return moveTx;
	}

	boolean leftCaret() {
		boolean moveTx = false;
		if (caletI[1] > 0) { // ����text��
			caletI[1]--;
		} else if (caletI[0] > 0) { // ��text��
			caletI[0]--;
			caletI[1] = txFormula.get(caletI[0]).getText().length();
			moveTx = true;
		}
		txFormula.get(caletI[0]).requestFocus();
		txFormula.get(caletI[0]).positionCaret(caletI[1]);
		return moveTx;
	}

	private Node searchButtonNext(HBox hbox) {
		boolean foundNowText = false;
		for (int i = 0; i < hbox.getChildren().size(); i++) {
			if (hbox.getChildren().get(i) == txFormula.get(caletI[0])) {
				foundNowText = true;
			}
			if (foundNowText) {
				if (hbox.getChildren().get(i) instanceof Button) {
					return hbox.getChildren().get(i);
				}
			}
		}
		return null;
	}
}

// ����p���͗�
class FormulaTextField extends TextField {
	private int width = 40;
	private int height = 20;
	private ExamCalEditV exV;

	public FormulaTextField(ExamCalEditV exV) {
		super();
		this.exV = exV;
		setPrefWidth(width);
		setPrefHeight(height);
		setPromptText("��");
		this.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.RIGHT) {
				exV.rightCaret();
			}
			if (e.getCode() == KeyCode.LEFT) {
				exV.leftCaret();
			}
			if (e.getCode() == KeyCode.BACK_SPACE) {
				if (exV.leftCaret()) {
					exV.nextTextFDel();
					exV.formulaNextBtDel();
				}
			}
		});
	}

	@Override
	public void replaceText(final int start, final int end, final String text) {
		System.out.println(start + " " + end + " " + text);
		if (validate(text)) {
			super.replaceText(start, end, text);// �����ǉ�

			if (start < end) {// �폜
				width -= 8;
				setPrefWidth(width);
			} else {
				exV.rightCaret();
				width += 8;
				setPrefWidth(width);
			}

		} else if (cal(text)) { // ���Z�����Ȃ�

			if (start != 0 && super.getText(start - 1, end).matches("[/\\*\\-\\+\\.\\(]")) { // ���Z�q�A���Ȃ�
				super.replaceText(start - 1, end, text);// 1�����u������
			} else {
				super.replaceText(start, end, text);// �����ǉ�

				// ExamCalEditV.caletI[1]++;
				width += 8;
				setPrefWidth(width);
				exV.rightCaret();
			}
		}
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

class ExamButton extends Button {
	ExamButton() {
		super();
		this.setOnAction(e -> {
			AddExamButtonConfig config = new AddExamButtonConfig(getText());
			config.getConfirm().setOnAction(ev -> {
				this.setText(config.getText());
				config.close();
			});
		});
	}

	ExamButton(String tex) {
		super(tex);
		this.setOnAction(e -> {
			AddExamButtonConfig config = new AddExamButtonConfig(getText());
			config.getConfirm().setOnAction(ev -> {
				this.setText(config.getText());
				config.close();
			});
		});
	}
}

class HeijouButton extends Button {
	int max = 0;

	HeijouButton() {
		super();
		this.setOnAction(e -> {
			AddExamButtonConfig config = new AddExamButtonConfig(getText());
			config.getConfirm().setOnAction(ev -> {
				this.setText(config.getText());
				config.close();
			});
		});
	}

	HeijouButton(String tex) {
		super(tex);
		this.setOnAction(e -> {
			AddHeijouButtonConfig config = new AddHeijouButtonConfig(getText(), max);
			config.getConfirm().setOnAction(ev -> {
				this.setText(config.getText());
				this.max = config.getMax();
				config.close();
			});
		});
	}
}

// �e�X�g�_�ݒ���
class AddExamButtonConfig {
	Stage stage;
	Button confirm = new Button("�m��");
	TextField txF = new TextField();

	public AddExamButtonConfig() {
		stage = new Stage();
		stage.setWidth(600);
		stage.setHeight(300);
		stage.setTitle("�ݒ�");
		stage.initModality(Modality.APPLICATION_MODAL);// ����ʑI��s��
		stage.show();

		VBox root = new VBox();
		HBox under = new HBox();
		under.setAlignment(Pos.TOP_RIGHT);
		under.getChildren().add(confirm);
		HBox center = new HBox();

		center.setAlignment(Pos.CENTER);
		center.getChildren().addAll(new Label("�e�X�g��:"), txF);
		root.getChildren().addAll(center, under);
		stage.setScene(new Scene(root));
	}

	public AddExamButtonConfig(String text) {
		stage = new Stage();
		stage.setWidth(600);
		stage.setHeight(300);
		stage.setTitle("�ݒ�");
		stage.initModality(Modality.APPLICATION_MODAL);// ����ʑI��s��
		stage.show();

		VBox root = new VBox();
		HBox under = new HBox();
		under.setAlignment(Pos.TOP_RIGHT);
		under.getChildren().add(confirm);
		HBox center = new HBox();
		center.setAlignment(Pos.CENTER);
		txF.setText(text);
		center.getChildren().addAll(new Label("�e�X�g��:"), txF);
		root.getChildren().addAll(center, under);
		stage.setScene(new Scene(root));
	}

	Button getConfirm() {
		return confirm;
	}

	String getText() {
		return txF.getText();
	}

	void close() {
		stage.close();
	}
}

// ����_�ݒ���
class AddHeijouButtonConfig {
	Stage stage;
	Button confirm = new Button("�m��");
	TextField txF = new TextField();
	NumTextField numTF = new NumTextField();

	public AddHeijouButtonConfig() {
		stage = new Stage();
		stage.setWidth(600);
		stage.setHeight(300);
		stage.setTitle("�ݒ�");
		stage.initModality(Modality.APPLICATION_MODAL);// ����ʑI��s��
		stage.show();

		VBox root = new VBox();
		HBox under = new HBox();
		under.setAlignment(Pos.TOP_RIGHT);
		under.getChildren().add(confirm);

		HBox[] center = { new HBox(), new HBox() };
		center[0].setAlignment(Pos.CENTER);
		center[1].setAlignment(Pos.CENTER);
		center[0].getChildren().addAll(new Label("����_��:"), txF);
		center[1].getChildren().addAll(new Label("�ő�l:"), numTF);

		root.getChildren().addAll(center[0], center[1], under);
		stage.setScene(new Scene(root));
	}

	public AddHeijouButtonConfig(String text, int max) {

		stage = new Stage();
		stage.setWidth(600);
		stage.setHeight(300);
		stage.setTitle("�ݒ�");
		stage.initModality(Modality.APPLICATION_MODAL);// ����ʑI��s��
		stage.show();

		txF.setText(text);

		numTF.setText(max + "");

		VBox root = new VBox();
		HBox under = new HBox();
		under.setAlignment(Pos.TOP_RIGHT);
		under.getChildren().add(confirm);

		HBox[] center = { new HBox(), new HBox() };
		center[0].setAlignment(Pos.CENTER);
		center[1].setAlignment(Pos.CENTER);
		center[0].getChildren().addAll(new Label("����_��:"), txF);
		center[1].getChildren().addAll(new Label("�ő�l:"), numTF);

		root.getChildren().addAll(center[0], center[1], under);
		stage.setScene(new Scene(root));
	}

	Button getConfirm() {
		return confirm;
	}

	String getText() {
		return txF.getText();
	}

	int getMax() {
		return Integer.parseInt(numTF.getText());
	}

	void close() {
		stage.close();
	}
}