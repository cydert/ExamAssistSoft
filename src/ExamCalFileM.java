import java.io.File;
import java.util.ArrayList;

public class ExamCalFileM {

	private String fileHostPath = "data\\";
	private String fileDirPath = ""; // �\������dir
	private String currentPath = ""; // �I�𒆂̐���Path
	private File[] fileList;

	private String formula; // �� (File���擾
	private ArrayList<String> testInfo; // �e�X�g����Box�̍쐬���̏��
	private ArrayList<String> heijouInfo; // ����_����Box

	void setFilePath(String path) {
		fileDirPath = path;
	}

	void setCurrentFileName(String fileName) {
		currentPath = fileHostPath + fileDirPath + fileName;
	}

	String getDirPath() {
		return fileDirPath;
	}

	String getHostPath() {
		return fileHostPath;
	}

	String getAllFilePath() {
		return fileHostPath + fileDirPath + "fileName";
	}

	void clearDirPath() {
		fileDirPath = "";
	}

	void clearCurrentPath() {
		currentPath = "";
	}

	String getCurrentFileName() {
		if (currentPath.equals("")) {
			return "";
		} else {
			int tmpIndex = currentPath.lastIndexOf("\\");
			if (tmpIndex != -1) {
				// �r��dir�ύX�����
				return currentPath.substring(tmpIndex + 1, currentPath.lastIndexOf("."));
			}
			return currentPath.substring(0, currentPath.lastIndexOf("."));
		}
	}

	String getFormula() {
		return formula;
	}

	String[] getExamList() {
		return testInfo.toArray(new String[testInfo.size()]);
	}

}
