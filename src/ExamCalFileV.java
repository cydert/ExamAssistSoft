import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ExamCalFileV {
	private ExamCalM calM;
	private Stage stage;
	private ListView<String> listView;

	ExamCalFileV() {
		// window�����ݒ�
		stage = new Stage();
		stage.setWidth(700);
		stage.setHeight(600);
		stage.setTitle("�t�@�C����I��");
		stage.initModality(Modality.APPLICATION_MODAL);// ����ʑI��s��
		stage.show();

		VBox root = new VBox();

		listView = new ListView<>();// ���X�g�r���[
		listView.setEditable(false); // �ҏW�s��
		listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); // �P�̂̂ݑI����

		root.getChildren().addAll(listView);
		stage.setScene(new Scene(root));// �\��
	}

	// (�{�^�������ꂽ���Ȃ�)Model�̃t�@�C�����X�g�����ĕ\�����e�ύX
	void changeFileList() {
		ArrayList<String> fileList = calM.getFileNameAr();
		ObservableList<String> list = FXCollections.observableArrayList(fileList);// �ꗗ���e
		listView.setItems(list);
		// �vdir��
	}

	ListView<String> getListView() {
		return listView;
	}

	void bindModel(ExamCalM calM) {
		this.calM = calM;
	}

	Stage getStage() {
		return stage;
	}

}
