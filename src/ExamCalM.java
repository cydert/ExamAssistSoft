import java.io.File;
import java.util.ArrayList;

public class ExamCalM {
	private String fileHostPath = "data\\";
	private String filePath = "";
	private String[] fileList;

	//File一覧をArrayList<String>で返却
	ArrayList<String> getFileListSt() {
		File[] fileList = getFileList();
		System.out.println(fileList.length);

		ArrayList<String> ar = new ArrayList<>();
		for (int i = 0; i < fileList.length; i++) {
			System.out.println(i);
			if (fileList[i].isFile()){
				ar.add((fileList[i].getName().split("\\.")[0]));	//拡張子除去して追加
			}
		}
		return ar;
	}

	ArrayList<String> getDirListSt() {
		File[] fileList = getFileList();
		ArrayList<String> ar = new ArrayList<>();
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isDirectory()) {
				ar.add(fileList[i].toString());
			}
		}
		return ar;
	}

	File[] getFileList() {
		checkHostFile();
		File[] fileList;
		File file = new File(fileHostPath + filePath);
		fileList = file.listFiles();
		return fileList;
	}
	private void checkHostFile(){
		File file = new File(fileHostPath);
		if(!file.exists()){
			file.mkdir();
		}
		System.out.println("hos:" +fileHostPath);
	}

	void setFilePath(String path) {
		filePath = path;
	}

	String getFilePath() {
		return filePath;
	}

	String getHostPath() {
		return fileHostPath;
	}

	String getAllFilePath() {
		return fileHostPath + filePath;
	}
	void clearFilePath(){
		filePath = "";
	}

}
