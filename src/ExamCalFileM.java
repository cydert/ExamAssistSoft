import java.io.File;
import java.util.ArrayList;

public class ExamCalFileM {

	private String fileHostPath = "data\\";
	private String fileDirPath = ""; // 表示中のdir
	private String currentPath = ""; // 選択中の正式Path
	private File[] fileList;

	private String formula; // 式 (Fileより取得
	private ArrayList<String> testInfo; // テスト入力Boxの作成時の情報
	private ArrayList<String> heijouInfo; // 平常点入力Box

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
				// 途中dir変更あれば
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
