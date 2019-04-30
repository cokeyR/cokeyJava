package cn.wx.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Te {

	public static void copyFileToDir(String srcFilePath, String targetDirPath) {
		File srcFile = new File(srcFilePath);
		File targetDir = new File(targetDirPath);
		if(!targetDir.exists())
			targetDir.mkdirs();
		if (!srcFile.isFile())
			return;
		if(!targetDir.isDirectory())
			return;
		File targetFile=new File(targetDirPath+File.separator+srcFile.getName());
		if(targetFile.exists())
			targetFile.delete();
		InputStream input=null;
		OutputStream output=null;
		try {
			targetFile.createNewFile();
			input=new FileInputStream(srcFile);
			output=new FileOutputStream(targetFile);
			byte[] buffer=new byte[2048];
			int len=-1;
			while((len=input.read(buffer))!=-1)
				output.write(buffer,0,len);
		} catch (IOException e) {
			
		}
	}
	public static void main(String[] args) {
		File f=new File("D:/123.txt");
		copyFileToDir(f.getAbsolutePath(), "D:/supervise");
	}
}
