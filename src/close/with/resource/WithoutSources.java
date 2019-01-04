package close.with.resource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WithoutSources {
	public static void main(String[] args){
		
		//冗长
		//finally块中抛出的异常，会覆盖try中抛出的一场
		BufferedWriter writer=null;
		try {
			writer=new BufferedWriter(new FileWriter(new File("test.txt")));
			writer.write("hhh");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (writer!=null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
