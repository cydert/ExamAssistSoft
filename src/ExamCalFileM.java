import java.io.File;
import java.util.ArrayList;
public class ExamCalFileM {


		private String fileHostPath = "data\\";
		private String fileDirPath = "";	//表示中のdir
		private String currentPath = "";	//選択中の正式Path
		private File[] fileList;

		void reloadFileList(){
			checkHostFile();
			File file = new File(fileHostPath + fileDirPath);
			fileList = file.listFiles();
		}

		//現在位置のファイル一覧表示
		ArrayList<String> getFileNameAr() {
			reloadFileList();
			ArrayList<String> ar = new ArrayList<>();
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].isFile()){
					ar.add((fileList[i].getName().split("\\.")[0]));	//拡張子除去して追加
				}
			}
			return ar;
		}

		ArrayList<String> getDirListSt() {
			if(fileList == null){
				reloadFileList();
				System.out.println("null2");
			}
			ArrayList<String> ar = new ArrayList<>();
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].isDirectory()) {
					ar.add(fileList[i].toString());
				}
			}
			return ar;
		}


		private void checkHostFile(){
			File file = new File(fileHostPath);
			if(!file.exists()){
				file.mkdir();
			}
			System.out.println("hos:" +fileHostPath);
		}

		void setFilePath(String path) {
			fileDirPath = path;
		}
		void setCurrentFileName(String fileName){
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
		void clearDirPath(){
			fileDirPath = "";
		}
		void clearCurrentPath(){
			currentPath = "";
		}
		String getCurrentFileName(){
			if(currentPath.equals("")){
				return "";
			}else {
				int tmpIndex = currentPath.lastIndexOf("\\");
				if( tmpIndex != -1){
					//途中dir変更あれば
					return currentPath.substring(tmpIndex+1, currentPath.lastIndexOf("."));
				}
				return currentPath.substring(0,currentPath.lastIndexOf("."));
			}
		}

}
