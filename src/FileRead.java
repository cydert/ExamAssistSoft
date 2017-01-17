import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileRead {

	// 現在位置のファイル一覧表示
	static ArrayList<String> getFileNameAr(String filePath) {
		File file = new File(filePath);
		File[] fileList = file.listFiles();
		ArrayList<String> ar = new ArrayList<>();
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isFile()) {
				ar.add((fileList[i].getName().split("\\.")[0])); // 拡張子除去して追加
			}
		}
		return ar;
	}

	// 現在位置のdir一覧表示
	static ArrayList<String> getDirNameSt(String filePath) {
		File file = new File(filePath);
		File[] fileList = file.listFiles();
		ArrayList<String> ar = new ArrayList<>();
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isDirectory()) {
				ar.add(fileList[i].toString());
			}
		}
		return ar;
	}

	// 式を返す
	static String readFormula(String filePath) {
		File file = new File(filePath);
		try {
			BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
			String formula = bfr.readLine(); // 1行目
			bfr.close();
			return formula;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	// テスト情報の取得
	static String[] readTestInfo(String filePath) {
		ArrayList<String> testInfo = new ArrayList<>();
		File file = new File(filePath);
		try {
			BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
			bfr.readLine(); // 1行目無視
			String tmp;
			while ((tmp = bfr.readLine()) != null) {
				if (tmp.charAt(0) == 'T') { // test情報
					testInfo.add(tmp.substring(tmp.indexOf(":") + 1)); // :以降追加
				}
			}
			bfr.close();
			return testInfo.toArray(new String[testInfo.size()]);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 平常点情報の取得
	static String[] readHeijou(String filePath) {
		ArrayList<String> heijouInfo = new ArrayList<>();
		File file = new File(filePath);
		try {
			BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
			bfr.readLine(); // 1行目無視
			String tmp;
			while ((tmp = bfr.readLine()) != null) {
				if (tmp.charAt(0) == 'H') { // 平常点情報
					heijouInfo.add(tmp.substring(tmp.indexOf(":") + 1));
				}
			}
			bfr.close();
			return heijouInfo.toArray(new String[heijouInfo.size()]);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	static void checkHostFile(String fileHostPath) {
		File file = new File(fileHostPath);
		if (!file.exists()) {
			file.mkdir();
		}
		System.out.println("hos:" + fileHostPath);
	}
}

class FileWrite {
	static void fileWrite(String filePath, ArrayList<String> tx) {
		try {
			File file = new File(filePath);
			PrintWriter pw = new PrintWriter(
					new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8")));
			for(int i=0; i<tx.size(); i++){
				pw.println(tx.get(i));
			}
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
