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

	// ���݈ʒu�̃t�@�C���ꗗ�\��
	static ArrayList<String> getFileNameAr(String filePath) {
		File file = new File(filePath);
		File[] fileList = file.listFiles();
		ArrayList<String> ar = new ArrayList<>();
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isFile()) {
				ar.add((fileList[i].getName().split("\\.")[0])); // �g���q�������Ēǉ�
			}
		}
		return ar;
	}

	// ���݈ʒu��dir�ꗗ�\��
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

	// ����Ԃ�
	static String readFormula(String filePath) {
		File file = new File(filePath);
		try {
			BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
			String formula = bfr.readLine(); // 1�s��
			bfr.close();
			return formula;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	// �e�X�g���̎擾
	static String[] readTestInfo(String filePath) {
		ArrayList<String> testInfo = new ArrayList<>();
		File file = new File(filePath);
		try {
			BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
			bfr.readLine(); // 1�s�ږ���
			String tmp;
			while ((tmp = bfr.readLine()) != null) {
				if (tmp.charAt(0) == 'T') { // test���
					testInfo.add(tmp.substring(tmp.indexOf(":") + 1)); // :�ȍ~�ǉ�
				}
			}
			bfr.close();
			return testInfo.toArray(new String[testInfo.size()]);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// ����_���̎擾
	static String[] readHeijou(String filePath) {
		ArrayList<String> heijouInfo = new ArrayList<>();
		File file = new File(filePath);
		try {
			BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
			bfr.readLine(); // 1�s�ږ���
			String tmp;
			while ((tmp = bfr.readLine()) != null) {
				if (tmp.charAt(0) == 'H') { // ����_���
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
